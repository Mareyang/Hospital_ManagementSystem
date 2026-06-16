package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewInvoiceDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblPatient, lblDate, lblStatus, lblTotal;
    private JTextField txtPatient, txtDate, txtStatus, txtTotal;
    private JTable tblItems;
    private DefaultTableModel tableModel;
    private JScrollPane scrollItems;
    private JButton btnClose;
    private int currentBillingId;
    
    public ViewInvoiceDialog(int billingId) {
        this.currentBillingId = billingId;
        
        setSize(800, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        String displayId = String.format("INV-%03d", billingId);
        
        lblTitle = new JLabel("Invoice Details: " + displayId);
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Itemized receipt for patient charges.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 90, 700, 400); 
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        // --- HEADER INFO ---
        lblPatient = new JLabel("Patient Name:");
        lblPatient.setBounds(30, 20, 150, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatient);
        
        txtPatient = new JTextField("");
        txtPatient.setBounds(160, 20, 200, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setEditable(false);
        pnlContent.add(txtPatient);
        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(390, 20, 100, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(460, 20, 220, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setEditable(false);
        pnlContent.add(txtDate);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(30, 60, 150, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
        
        txtStatus = new JTextField("");
        txtStatus.setBounds(160, 60, 200, 30);
        txtStatus.setFont(FontsTheme.Plain_Texts);
        txtStatus.setEditable(false);
        pnlContent.add(txtStatus);

        // --- ITEMIZED TABLE ---
        String[] columns = {"Description", "Qty", "Unit Price", "Total"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblItems = new JTable(tableModel);
        tblItems.setFont(FontsTheme.Info_Texts);
        tblItems.getTableHeader().setFont(FontsTheme.Bold);
        tblItems.getTableHeader().setBackground(ColorsTheme.Header);
        tblItems.getTableHeader().setForeground(ColorsTheme.Text_White);
        
        scrollItems = new JScrollPane(tblItems);
        scrollItems.setBounds(30, 110, 640, 220);
        pnlContent.add(scrollItems);
        
        lblTotal = new JLabel("Grand Total: ");
        lblTotal.setBounds(300, 350, 200, 30);
        lblTotal.setFont(FontsTheme.Bold_Texts);
        pnlContent.add(lblTotal);
        
        txtTotal = new JTextField("");
        txtTotal.setBounds(490, 350, 180, 30);
        txtTotal.setFont(FontsTheme.Bold_Texts);
        txtTotal.setEditable(false);
        pnlContent.add(txtTotal);

        btnClose = new JButton("Close");
        btnClose.setBounds(540, 510, 200, 30);
        btnClose.setFont(FontsTheme.Buttons);
        btnClose.setForeground(ColorsTheme.Text_White);
        btnClose.setBackground(ColorsTheme.Search);
        btnClose.setFocusPainted(false);
        add(btnClose);
        
        btnClose.addActionListener(this);
        
        loadInvoiceData();
    }
    
    private void loadInvoiceData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String headerSql = "SELECT b.billing_date, b.net_amount, s.status_name, p.first_name, p.last_name " +
                               "FROM billing b JOIN patients p ON b.patient_id = p.patient_id " +
                               "JOIN billing_status s ON b.status_id = s.status_id WHERE b.billing_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(headerSql)) {
                stmt.setInt(1, currentBillingId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    txtPatient.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                    java.sql.Date dbDate = rs.getDate("billing_date");
                    txtDate.setText(dbDate != null ? dbDate.toString() : "N/A");
                    txtStatus.setText(rs.getString("status_name"));
                    txtTotal.setText(String.format("PHP %,.2f", rs.getDouble("net_amount")));
                }
            }
            
            String itemSql = "SELECT description, quantity, unit_price, total_price FROM billing_items WHERE billing_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(itemSql)) {
                stmt.setInt(1, currentBillingId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String desc = rs.getString("description");
                    int qty = rs.getInt("quantity");
                    String unit = String.format("PHP %,.2f", rs.getDouble("unit_price"));
                    String total = String.format("PHP %,.2f", rs.getDouble("total_price"));
                    tableModel.addRow(new Object[]{desc, qty, unit, total});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            dispose();
        } 
    }
}