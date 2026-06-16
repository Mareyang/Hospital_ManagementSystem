package panels;

import constants.TablePanel;
import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.NewPrescriptionDialog;
import dialogs.ViewPrescriptionDialog; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlPending, pnlDispense, pnlCancel;
    private JLabel lblDetails, lblPrescription;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnDispense, btnCancelBtn, btnView; 
    private TablePanel tblPrescription;
    
    private static final String[] columns = {"Rx ID", "Patient Name", "Doctor", "Date", "Medications", "Status"};
    
    private boolean canPrescribe;
    private boolean canDispense;
    
    public PrescriptionsPanel(boolean canPrescribe, boolean canDispense) {
        this.canPrescribe = canPrescribe;
        this.canDispense = canDispense;
        
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
        
        btnAdd = new JButton("Add");
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        
        btnDispense = new JButton("Dispense");
        btnDispense.setFont(FontsTheme.Buttons);
        btnDispense.setBackground(ColorsTheme.Add);
        btnDispense.setForeground(ColorsTheme.Text_White);
        btnDispense.setFocusPainted(false);

        btnCancelBtn = new JButton("Cancel");
        btnCancelBtn.setFont(FontsTheme.Buttons);
        btnCancelBtn.setBackground(ColorsTheme.Delete);
        btnCancelBtn.setForeground(ColorsTheme.Text_White);
        btnCancelBtn.setFocusPainted(false);
        
        btnView = new JButton("View");
        btnView.setFont(FontsTheme.Buttons);
        btnView.setBackground(ColorsTheme.View); 
        btnView.setForeground(ColorsTheme.Text_White);
        btnView.setFocusPainted(false);
        
        List<JButton> visibleButtons = new ArrayList<>();
        
        if (canPrescribe) visibleButtons.add(btnAdd);
        visibleButtons.add(btnView); 
        
        if (canDispense) visibleButtons.add(btnDispense);
        if (canPrescribe) visibleButtons.add(btnCancelBtn);

        int[] slots = {845, 1005, 1165, 1325}; 
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
            visibleButtons.get(i).addActionListener(this);
        }
        
        txtSearch = new JTextField("Search by patient name or patient id...");
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
                
        lblPrescription = new JLabel("Prescription");
        lblPrescription.setBounds(30, 30, 500, 40);
        lblPrescription.setFont(FontsTheme.Bold_Texts);
        lblPrescription.setForeground(ColorsTheme.Text_Black);
        add(lblPrescription);

        lblDetails = new JLabel("Manage and dispense prescriptions.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        Object[][] data = fetchPrescriptions("");
        tblPrescription = new TablePanel("Recent Prescription", columns, data, 440);
        tblPrescription.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPrescription);

        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPrescriptions(searchKeyword);
        pnlMiddle.remove(tblPrescription);
        tblPrescription = new TablePanel(sectionTitle, columns, freshData, 440);
        tblPrescription.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPrescription);
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchPrescriptions(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        
        String sql = "SELECT pr.*, p.first_name, p.last_name, u.lastname AS doc_last, s.status_name, " +
                     "ph.brand_name, ph.generic_name, ph.strength, pd.dosage " +
                     "FROM prescriptions pr " +
                     "LEFT JOIN patients p ON pr.patient_id = p.patient_id " +
                     "LEFT JOIN users u ON pr.doctor_id = u.user_id " +
                     "LEFT JOIN prescription_status s ON pr.status_id = s.status_id " +
                     "LEFT JOIN prescription_details pd ON pr.prescription_id = pd.prescription_id " +
                     "LEFT JOIN pharmacy ph ON pd.medication_id = ph.medication_id";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or patient id...");
        if (hasSearchFilter) {
            sql += " WHERE p.first_name LIKE ? OR p.last_name LIKE ? OR pr.prescription_id LIKE ? OR pr.patient_id LIKE ?";
        }
        sql += " ORDER BY pr.prescription_id DESC"; 

        int countPending = 0;
        int countDispensed = 0;
        int countCancelled = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.toUpperCase().replace("RX-", "").replace("PAT-", "").trim();
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + cleanSearch + "%");
                statement.setString(4, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("prescription_id");
                String displayId = String.format("RX-%03d", rawId); 
                
                String name = result.getString("first_name") + " " + result.getString("last_name");
                String doctor = "Dr. " + result.getString("doc_last");
                
                String displayDate = "N/A";
                java.sql.Date dbDate = result.getDate("prescription_date");
                if (dbDate != null) {
                    displayDate = dbDate.toString(); 
                }
                
                String brandName = result.getString("brand_name");
                String genericName = result.getString("generic_name");
                String strength = result.getString("strength");
                String dosage = result.getString("dosage");
                
                String medName = (brandName != null && !brandName.isEmpty() ? brandName + " (" + genericName + ") " : genericName + " ") + (strength != null ? strength : "");
                String medications = medName.trim() + " (" + (dosage != null ? dosage : "N/A") + ")";
                
                String status = result.getString("status_name");

                if ("Pending Pharmacy".equalsIgnoreCase(status)) countPending++;
                if ("Dispensed".equalsIgnoreCase(status)) countDispensed++;
                if ("Cancelled".equalsIgnoreCase(status)) countCancelled++;

                rowsList.add(new Object[]{displayId, name, doctor, displayDate, medications, status});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load prescriptions:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countPending, countDispensed, countCancelled);
        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int pending, int dispensed, int cancelled) {
        if (pnlPending != null) remove(pnlPending);
        if (pnlDispense != null) remove(pnlDispense);
        if (pnlCancel != null) remove(pnlCancel);

        pnlPending = new PanelCard("Pending", String.valueOf(pending), ColorsTheme.Orange);
        pnlPending.setBounds(170, 130, 350, 110);
        add(pnlPending);
        
        pnlDispense = new PanelCard("Dispensed Today", String.valueOf(dispensed), ColorsTheme.Green);
        pnlDispense.setBounds(620, 130, 350, 110);
        add(pnlDispense);
        
        pnlCancel = new PanelCard("Cancelled", String.valueOf(cancelled), ColorsTheme.Red);
        pnlCancel.setBounds(1070, 130, 350, 110);
        add(pnlCancel);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewPrescriptionDialog prescription = new NewPrescriptionDialog();
            prescription.setVisible(true); 
            updateTable("Recent Prescription", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or patient id...");
            updateTable("Recent Prescription", "");
        }
        else if (e.getSource() == btnDispense) {
            dispensePrescription(); 
        }
        else if (e.getSource() == btnCancelBtn) {
            updatePrescriptionStatus(3); // 3 = Cancelled
        }
        else if (e.getSource() == btnView) {
            JTable table = tblPrescription.getTable();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a prescription first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String displayId = table.getValueAt(selectedRow, 0).toString();
            int rawId = Integer.parseInt(displayId.replace("RX-", ""));
            
            ViewPrescriptionDialog viewDialog = new ViewPrescriptionDialog(rawId);
            viewDialog.setVisible(true);
        }
    }
    
    private void updatePrescriptionStatus(int newStatusId) {
        JTable table = tblPrescription.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a prescription first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String displayId = table.getValueAt(selectedRow, 0).toString();
        int rawId = Integer.parseInt(displayId.replace("RX-", ""));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement("UPDATE prescriptions SET status_id = ? WHERE prescription_id = ?")) {
            stmt.setInt(1, newStatusId);
            stmt.setInt(2, rawId);
            stmt.executeUpdate();
            
            String searchKeyword = txtSearch.getText().trim();
            if (searchKeyword.equals("Search by patient name or patient id...")) searchKeyword = "";
            updateTable("Recent Prescription", searchKeyword);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update prescription status.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dispensePrescription() {
        JTable table = tblPrescription.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a prescription to dispense.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String currentStatus = table.getValueAt(selectedRow, 5).toString();
        if (currentStatus.equals("Dispensed") || currentStatus.equals("Cancelled")) {
            JOptionPane.showMessageDialog(this, "This prescription is already " + currentStatus + ".", "Action Blocked", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String displayId = table.getValueAt(selectedRow, 0).toString();
        int prescriptionId = Integer.parseInt(displayId.replace("RX-", ""));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            conn.setAutoCommit(false); 
            
            // select unit_price and patient_id for billing
            String checkSql = "SELECT pd.medication_id, pd.quantity, ph.current_stock, ph.reorder_level, ph.brand_name, ph.generic_name, ph.unit_price, pr.patient_id " +
                              "FROM prescription_details pd " +
                              "JOIN pharmacy ph ON pd.medication_id = ph.medication_id " +
                              "JOIN prescriptions pr ON pd.prescription_id = pr.prescription_id " +
                              "WHERE pd.prescription_id = ?";
                              
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, prescriptionId);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next()) {
                    int medId = rs.getInt("medication_id");
                    int qtyRequired = rs.getInt("quantity");
                    int currentStock = rs.getInt("current_stock");
                    int reorderLevel = rs.getInt("reorder_level");
                    String medName = rs.getString("brand_name") + " (" + rs.getString("generic_name") + ")";
                    
                    double unitPrice = rs.getDouble("unit_price");
                    int patientId = rs.getInt("patient_id");
                    
                    if (currentStock < qtyRequired) {
                        JOptionPane.showMessageDialog(this, 
                            "Insufficient Stock!\n\nPrescription requires: " + qtyRequired + 
                            "\nCurrent Stock: " + currentStock + 
                            "\n\nPlease request the Admin to restock " + medName + ".", 
                            "Out of Stock", JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
                    
                    // 1. Deduct Inventory
                    int newStock = currentStock - qtyRequired;
                    String deductSql = "UPDATE pharmacy SET current_stock = ? WHERE medication_id = ?";
                    try (PreparedStatement deductStmt = conn.prepareStatement(deductSql)) {
                        deductStmt.setInt(1, newStock);
                        deductStmt.setInt(2, medId);
                        deductStmt.executeUpdate();
                    }
                    
                    // 2. Update Prescription Status
                    String updateRxSql = "UPDATE prescriptions SET status_id = 2 WHERE prescription_id = ?";
                    try (PreparedStatement updateRxStmt = conn.prepareStatement(updateRxSql)) {
                        updateRxStmt.setInt(1, prescriptionId);
                        updateRxStmt.executeUpdate();
                    }
                    
                    // --- 3. AUTO-BILLING ---
                    double lineItemTotal = unitPrice * qtyRequired;

                    int activeBillingId = -1;
                    String checkBillSql = "SELECT billing_id FROM billing WHERE patient_id = ? AND status_id = 1 LIMIT 1";
                    try (PreparedStatement checkBillStmt = conn.prepareStatement(checkBillSql)) {
                        checkBillStmt.setInt(1, patientId);
                        ResultSet billRs = checkBillStmt.executeQuery();
                        if (billRs.next()) {
                            activeBillingId = billRs.getInt("billing_id");
                        } else {
                            String createBillSql = "INSERT INTO billing (patient_id, total_amount, net_amount, status_id) VALUES (?, 0, 0, 1)";
                            try (PreparedStatement createBillStmt = conn.prepareStatement(createBillSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                                createBillStmt.setInt(1, patientId);
                                createBillStmt.executeUpdate();
                                ResultSet keys = createBillStmt.getGeneratedKeys();
                                if (keys.next()) activeBillingId = keys.getInt(1);
                            }
                        }
                    }

                    String insertItemSql = "INSERT INTO billing_items (billing_id, description, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement itemStmt = conn.prepareStatement(insertItemSql)) {
                        itemStmt.setInt(1, activeBillingId);
                        itemStmt.setString(2, "Pharmacy: " + medName);
                        itemStmt.setInt(3, qtyRequired);
                        itemStmt.setDouble(4, unitPrice);
                        itemStmt.setDouble(5, lineItemTotal);
                        itemStmt.executeUpdate();
                    }

                    String updateTotalSql = "UPDATE billing SET total_amount = total_amount + ?, net_amount = net_amount + ? WHERE billing_id = ?";
                    try (PreparedStatement totalStmt = conn.prepareStatement(updateTotalSql)) {
                        totalStmt.setDouble(1, lineItemTotal);
                        totalStmt.setDouble(2, lineItemTotal);
                        totalStmt.setInt(3, activeBillingId);
                        totalStmt.executeUpdate();
                    }
                   
                    
                    conn.commit(); 
                    
                    if (newStock <= reorderLevel) {
                        JOptionPane.showMessageDialog(this, 
                            "Prescription dispensed successfully.\n\n⚠️ LOW STOCK ALERT: " + medName + 
                            " is down to " + newStock + " units.\nPlease notify Admin to restock.", 
                            "Low Stock Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Prescription dispensed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    String searchKeyword = txtSearch.getText().trim();
                    if (searchKeyword.equals("Search by patient name or patient id...")) searchKeyword = "";
                    updateTable("Recent Prescription", searchKeyword);
                }
            } catch (SQLException ex) {
                conn.rollback(); 
                throw ex;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error during dispensing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}