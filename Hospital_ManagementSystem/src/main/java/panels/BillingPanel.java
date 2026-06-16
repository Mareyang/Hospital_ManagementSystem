package panels;

import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.ViewInvoiceDialog;    
import dialogs.ProcessPaymentDialog; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BillingPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlRevenue, pnlPending, pnlCompleted, pnlVoided;
    private JLabel lblDetails, lblBilling;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnPay, btnView, btnVoid;
    private TablePanel tblBilling;
    
    private static final String[] columns = {"Invoice ID", "Patient Name", "Date Issued", "Total Amount", "Status"};
    
    public BillingPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        // --- BUTTON LAYOUT ADJUSTED FOR 3 BUTTONS ---
        btnView = new JButton("View Details");
        btnView.setBounds(990, 40, 150, 45); 
        btnView.setFont(FontsTheme.Buttons);
        btnView.setBackground(ColorsTheme.View);
        btnView.setForeground(ColorsTheme.Text_White);
        btnView.setFocusPainted(false);
        add(btnView);
        
        btnVoid = new JButton("Void / Refund");
        btnVoid.setBounds(1150, 40, 170, 45); 
        btnVoid.setFont(FontsTheme.Buttons);
        btnVoid.setBackground(ColorsTheme.Delete);
        btnVoid.setForeground(ColorsTheme.Text_White);
        btnVoid.setFocusPainted(false);
        add(btnVoid);
        
        btnPay = new JButton("Process Payment");
        btnPay.setBounds(1330, 40, 200, 45); 
        btnPay.setFont(FontsTheme.Buttons);
        btnPay.setBackground(ColorsTheme.Add);
        btnPay.setForeground(ColorsTheme.Text_White);
        btnPay.setFocusPainted(false);
        add(btnPay);
        
        txtSearch = new JTextField("Search by patient name or invoice id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        btnSearch.setFocusPainted(false);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        btnRefresh.setFocusPainted(false);
        pnlSearch.add(btnRefresh);
        
        lblBilling = new JLabel("Billing & Payments");
        lblBilling.setBounds(30, 30, 500, 40);
        lblBilling.setFont(FontsTheme.Bold_Texts);
        lblBilling.setForeground(ColorsTheme.Text_Black);
        add(lblBilling);

        lblDetails = new JLabel("Manage patient invoices and process transactions.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        Object[][] data = fetchBilling("");
        tblBilling = new TablePanel("Active Invoices", columns, data, 560);
        tblBilling.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblBilling);

        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnView.addActionListener(this);
        btnVoid.addActionListener(this);
        btnPay.addActionListener(this);
    }
    
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchBilling(searchKeyword);
        pnlMiddle.remove(tblBilling);
        tblBilling = new TablePanel(sectionTitle, columns, freshData, 560);
        tblBilling.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblBilling);
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchBilling(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        
        String sql = "SELECT b.billing_id, p.first_name, p.last_name, b.billing_date, b.net_amount, s.status_name " +
                     "FROM billing b " +
                     "JOIN patients p ON b.patient_id = p.patient_id " +
                     "JOIN billing_status s ON b.status_id = s.status_id ";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or invoice id...");
        if (hasSearchFilter) {
            sql += " WHERE p.first_name LIKE ? OR p.last_name LIKE ? OR b.billing_id LIKE ? ";
        }
        sql += " ORDER BY b.billing_id DESC"; 

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.toUpperCase().replace("INV-", "").trim();
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("billing_id");
                String displayId = String.format("INV-%04d", rawId); 
                String name = result.getString("first_name") + " " + result.getString("last_name");
                
                String displayDate = "N/A";
                java.sql.Date dbDate = result.getDate("billing_date");
                if (dbDate != null) {
                    displayDate = dbDate.toString(); 
                }
                
                String amount = String.format("PHP %,.2f", result.getDouble("net_amount"));
                String status = result.getString("status_name");

                rowsList.add(new Object[]{displayId, name, displayDate, amount, status});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Billing table not set up yet! " + ex.getMessage());
        }

        refreshSummaryCards();
        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards() {
        if (pnlRevenue != null) remove(pnlRevenue);
        if (pnlPending != null) remove(pnlPending);
        if (pnlCompleted != null) remove(pnlCompleted);
        if (pnlVoided != null) remove(pnlVoided);

        double todaysRevenue = 0.0;
        double totalPending = 0.0;
        int completedCount = 0;
        int voidedCount = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String revSql = "SELECT SUM(amount_paid) FROM payments WHERE DATE(payment_date) = CURDATE()";
            try (PreparedStatement stmt = conn.prepareStatement(revSql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) todaysRevenue = rs.getDouble(1);
            }
            
            String pendSql = "SELECT SUM(net_amount) FROM billing WHERE status_id = 1 OR status_id = 2";
            try (PreparedStatement stmt = conn.prepareStatement(pendSql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) totalPending = rs.getDouble(1);
            }
            
            String compSql = "SELECT COUNT(*) FROM billing WHERE status_id = 3";
            try (PreparedStatement stmt = conn.prepareStatement(compSql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) completedCount = rs.getInt(1);
            }
            
            String voidSql = "SELECT COUNT(*) FROM billing WHERE status_id = 4";
            try (PreparedStatement stmt = conn.prepareStatement(voidSql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) voidedCount = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Metrics not available yet.");
        }

        pnlRevenue = new PanelCard("Today's Revenue", String.format("PHP %,.2f", todaysRevenue), ColorsTheme.Green);
        pnlRevenue.setBounds(70, 130, 350, 110);
        add(pnlRevenue);
        
        pnlPending = new PanelCard("Pending Receivables", String.format("PHP %,.2f", totalPending), ColorsTheme.Orange);
        pnlPending.setBounds(450, 130, 350, 110);
        add(pnlPending);
        
        pnlCompleted = new PanelCard("Fully Paid Invoices", String.valueOf(completedCount), ColorsTheme.Blue);
        pnlCompleted.setBounds(830, 130, 350, 110);
        add(pnlCompleted);
        
        pnlVoided = new PanelCard("Voided / Refunded", String.valueOf(voidedCount), ColorsTheme.Red);
        pnlVoided.setBounds(1210, 130, 350, 110);
        add(pnlVoided);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or invoice id...");
            updateTable("Active Invoices", "");
        }
        else if (e.getSource() == btnView) {
            JTable table = tblBilling.getTable();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an invoice first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String displayId = table.getValueAt(selectedRow, 0).toString();
            int rawId = Integer.parseInt(displayId.replace("INV-", ""));
            
            new ViewInvoiceDialog(rawId).setVisible(true);
        }
        // --- VOID / REFUND LOGIC ---
        else if (e.getSource() == btnVoid) {
            JTable table = tblBilling.getTable();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an invoice to void.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String status = table.getValueAt(selectedRow, 4).toString();
            if (status.equalsIgnoreCase("Voided / Refunded")) {
                JOptionPane.showMessageDialog(this, "This invoice is already voided.", "Action Blocked", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String displayId = table.getValueAt(selectedRow, 0).toString();
            int rawId = Integer.parseInt(displayId.replace("INV-", ""));
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to Void/Refund this invoice?\nIf the patient has already paid, a refund record will be created.\nThis action cannot be undone.", 
                "Confirm Void/Refund", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
                    conn.setAutoCommit(false); 
                    
                    // 1. Calculate how much has been paid so far
                    double totalPaid = 0;
                    String checkPaidSql = "SELECT COALESCE(SUM(amount_paid), 0) FROM payments WHERE billing_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(checkPaidSql)) {
                        stmt.setInt(1, rawId);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) totalPaid = rs.getDouble(1);
                    }
                    
                    // 2. If money was paid, issue a negative refund payment to balance the ledger
                    if (totalPaid > 0) {
                        String refundSql = "INSERT INTO payments (billing_id, payment_method, amount_paid, processed_by_id) VALUES (?, 'Refund', ?, 1)";
                        try (PreparedStatement stmt = conn.prepareStatement(refundSql)) {
                            stmt.setInt(1, rawId);
                            stmt.setDouble(2, -totalPaid); // Negative value!
                            stmt.executeUpdate();
                        }
                    }
                    
                    // 3. Update the invoice status to 4 (Voided)
                    String voidSql = "UPDATE billing SET status_id = 4 WHERE billing_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(voidSql)) {
                        stmt.setInt(1, rawId);
                        stmt.executeUpdate();
                    }
                    
                    conn.commit(); 
                    JOptionPane.showMessageDialog(this, "Invoice voided and ledger updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    String searchKeyword = txtSearch.getText().trim();
                    if (searchKeyword.equals("Search by patient name or invoice id...")) searchKeyword = "";
                    updateTable("Active Invoices", searchKeyword);
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Database write failed.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource() == btnPay) {
            JTable table = tblBilling.getTable();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an invoice to process.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String status = table.getValueAt(selectedRow, 4).toString();
            if (status.equalsIgnoreCase("Fully Paid") || status.equalsIgnoreCase("Voided / Refunded")) {
                JOptionPane.showMessageDialog(this, "This invoice is already " + status + ".", "Action Blocked", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String displayId = table.getValueAt(selectedRow, 0).toString();
            int rawId = Integer.parseInt(displayId.replace("INV-", ""));
            
            new ProcessPaymentDialog(rawId).setVisible(true);
            
            String searchKeyword = txtSearch.getText().trim();
            if (searchKeyword.equals("Search by patient name or invoice id...")) searchKeyword = "";
            updateTable("Active Invoices", searchKeyword);
        }
    }
}