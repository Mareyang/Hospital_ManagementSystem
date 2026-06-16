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

public class ProcessPaymentDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblTotal, lblPaid, lblBalance, lblTendered, lblMethod;
    private JTextField txtTotal, txtPaid, txtBalance, txtTendered;
    private JComboBox<String> cmbMethod;
    private JButton btnCancel, btnPay;
    private JSeparator separator;
    
    private int currentBillingId;
    private double remainingBalance = 0.0;
    private int processedById = 1; // Simulated Admin ID

    public ProcessPaymentDialog(int billingId) {
        this.currentBillingId = billingId;
        
        setSize(550, 500); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        String displayId = String.format("INV-%04d", billingId);
        
        lblTitle = new JLabel("Process Payment: " + displayId);
        lblTitle.setBounds(30, 10, 400, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Record a payment against the patient's balance.");
        lblSubtitle.setBounds(30, 40, 450, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 90, 450, 280); 
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        // --- READ ONLY DATA ---
        lblTotal = new JLabel("Total Bill Amount:");
        lblTotal.setBounds(30, 30, 200, 30);
        lblTotal.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTotal);
        
        txtTotal = new JTextField("");
        txtTotal.setBounds(210, 30, 210, 30);
        txtTotal.setFont(FontsTheme.Plain_Texts);
        txtTotal.setEditable(false);
        pnlContent.add(txtTotal);
        
        lblPaid = new JLabel("Already Paid:");
        lblPaid.setBounds(30, 70, 200, 30);
        lblPaid.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPaid);
        
        txtPaid = new JTextField("");
        txtPaid.setBounds(210, 70, 210, 30);
        txtPaid.setFont(FontsTheme.Plain_Texts);
        txtPaid.setEditable(false);
        pnlContent.add(txtPaid);
        
        lblBalance = new JLabel("Remaining Balance:");
        lblBalance.setBounds(30, 110, 300, 30);
        lblBalance.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBalance);
        
        txtBalance = new JTextField("");
        txtBalance.setBounds(210, 110, 210, 30);
        txtBalance.setFont(FontsTheme.Bold_Texts);
        txtBalance.setForeground(ColorsTheme.Text_Black);
        txtBalance.setEditable(false);
        pnlContent.add(txtBalance);
        
        // --- INPUTS ---
        separator = new JSeparator();
        separator.setBounds(20, 160, 400, 10);
        pnlContent.add(separator);

        lblTendered = new JLabel("Amount to Pay:");
        lblTendered.setBounds(30, 180, 200, 30);
        lblTendered.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTendered);
        
        txtTendered = new JTextField("");
        txtTendered.setBounds(210, 180, 210, 30);
        txtTendered.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTendered);
        
        lblMethod = new JLabel("Payment Method:");
        lblMethod.setBounds(30, 220, 200, 30);
        lblMethod.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblMethod);
        
        String[] methods = {"Cash", "Credit Card", "Insurance", "Bank Transfer"};
        cmbMethod = new JComboBox<>(methods);
        cmbMethod.setBounds(210, 220, 210, 30);
        cmbMethod.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbMethod);
        
        // --- BUTTONS ---
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(50, 380, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnPay = new JButton("Confirm Payment");
        btnPay.setBounds(290, 380, 200, 30);
        btnPay.setFont(FontsTheme.Buttons);
        btnPay.setForeground(ColorsTheme.Text_White);
        btnPay.setBackground(ColorsTheme.Green);
        btnPay.setFocusPainted(false);
        add(btnPay);
        
        btnCancel.addActionListener(this);
        btnPay.addActionListener(this);
        
        calculateBalance();
    }
    
    private void calculateBalance() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String sql = "SELECT b.net_amount, COALESCE(SUM(p.amount_paid), 0) AS total_paid " +
                         "FROM billing b LEFT JOIN payments p ON b.billing_id = p.billing_id " +
                         "WHERE b.billing_id = ? GROUP BY b.billing_id";
                         
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, currentBillingId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double netAmount = rs.getDouble("net_amount");
                    double totalPaid = rs.getDouble("total_paid");
                    remainingBalance = netAmount - totalPaid;
                    
                    txtTotal.setText(String.format("PHP %,.2f", netAmount));
                    txtPaid.setText(String.format("PHP %,.2f", totalPaid));
                    txtBalance.setText(String.format("PHP %,.2f", remainingBalance));
                    
                    txtTendered.setText(String.valueOf(remainingBalance));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load balance.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnPay) {
            String tenderedStr = txtTendered.getText().trim();
            if (tenderedStr.isEmpty()) return;
            
            try {
                double amountPaying = Double.parseDouble(tenderedStr);
                if (amountPaying <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (amountPaying > remainingBalance) {
                    JOptionPane.showMessageDialog(this, "Payment cannot exceed remaining balance.", "Excess Amount", JOptionPane.WARNING_MESSAGE);
                    return; 
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
                    conn.setAutoCommit(false); 
                    
                    String insertPay = "INSERT INTO payments (billing_id, payment_method, amount_paid, processed_by_id) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(insertPay)) {
                        stmt.setInt(1, currentBillingId);
                        stmt.setString(2, cmbMethod.getSelectedItem().toString());
                        stmt.setDouble(3, amountPaying);
                        stmt.setInt(4, processedById);
                        stmt.executeUpdate();
                    }
                    
                    int newStatusId = (amountPaying >= remainingBalance) ? 3 : 2;
                    
                    String updateBill = "UPDATE billing SET status_id = ? WHERE billing_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(updateBill)) {
                        stmt.setInt(1, newStatusId);
                        stmt.setInt(2, currentBillingId);
                        stmt.executeUpdate();
                    }
                    
                    conn.commit(); 
                    JOptionPane.showMessageDialog(this, "Payment processed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Database write failed.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for payment.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}