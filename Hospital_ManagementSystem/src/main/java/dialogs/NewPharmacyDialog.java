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

public class NewPharmacyDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblCode, lblName, lblGeneric, lblType, lblDosage, lblStrength, lblCurrent, lblReorder, lblPrice, lblExpire, lblTitle, lblSubtitle;
    private JTextField txtCode, txtName, txtGeneric, txtStrength, txtCurrent, txtReorder, txtPrice, txtExpire;
    private JComboBox<String> cmbType, cmbDosage; 
    private JButton btnMedicationInfo, btnCancel, btnAddInfo;
    
    private static final String[] dosages = {"Select Form...", "Tablet", "Capsule", "Syrup", "Injection", "Ointment", "Inhaler", "IV"};
    
    public NewPharmacyDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);

        lblTitle = new JLabel("Medication Inventory");
        lblTitle.setBounds(30, 10, 400, 35);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblSubtitle = new JLabel("Add a new medication to the inventory.");
        lblSubtitle.setBounds(30, 40, 450, 30);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);

        btnMedicationInfo = new JButton("Medication Form");
        btnMedicationInfo.setBounds(40, 100, 250, 40);
        btnMedicationInfo.setFont(FontsTheme.Buttons);
        btnMedicationInfo.setForeground(ColorsTheme.Text_White);
        btnMedicationInfo.setBackground(ColorsTheme.Header);
        btnMedicationInfo.setFocusPainted(false);
        add(btnMedicationInfo);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnAddInfo = new JButton("Save Medication");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Green);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        
        // LEFT SECTION
        lblCode = new JLabel("Medication Code : ");
        lblCode.setBounds(40, 40, 200, 30);
        lblCode.setFont(FontsTheme.Plain_Texts);
        lblCode.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCode);
        
        txtCode = new JTextField("Auto-generated");
        txtCode.setBounds(220, 40, 230, 30);
        txtCode.setFont(FontsTheme.Plain_Texts);
        txtCode.setEditable(false);
        pnlContent.add(txtCode);
        
        lblName = new JLabel("Brand Name : ");
        lblName.setBounds(40, 80, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtName);
        
        lblGeneric = new JLabel("Generic Name : ");
        lblGeneric.setBounds(40, 120, 200, 30);
        lblGeneric.setFont(FontsTheme.Plain_Texts);
        lblGeneric.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblGeneric);
        
        txtGeneric = new JTextField(""); 
        txtGeneric.setBounds(220, 120, 230, 30);
        txtGeneric.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtGeneric);
        
        lblType = new JLabel("Category : ");
        lblType.setBounds(40, 160, 300, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
        
        cmbType = new JComboBox<>(); 
        cmbType.setBounds(220, 160, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        lblDosage = new JLabel("Dosage Form : ");
        lblDosage.setBounds(40, 200, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
        
        cmbDosage = new JComboBox<>(dosages);
        cmbDosage.setBounds(220, 200, 230, 30);
        cmbDosage.setFont(FontsTheme.Plain_Texts);
        cmbDosage.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDosage);
        
        // RIGHT SECTION
        lblStrength = new JLabel("Dosage Size : ");
        lblStrength.setBounds(510, 40, 200, 30);
        lblStrength.setFont(FontsTheme.Plain_Texts);
        lblStrength.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStrength);
        
        txtStrength = new JTextField("(e.g., 500mg/ml)");
        txtStrength.setBounds(690, 40, 230, 30);
        txtStrength.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtStrength);
        
        lblCurrent = new JLabel("Current Stock : ");
        lblCurrent.setBounds(510, 80, 200, 30);
        lblCurrent.setFont(FontsTheme.Plain_Texts);
        lblCurrent.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCurrent);
        
        txtCurrent = new JTextField("0");
        txtCurrent.setBounds(690, 80, 230, 30);
        txtCurrent.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtCurrent);
        
        lblReorder = new JLabel("Reorder Level : ");
        lblReorder.setBounds(510, 120, 300, 30);
        lblReorder.setFont(FontsTheme.Plain_Texts);
        lblReorder.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblReorder);
        
        txtReorder = new JTextField("20");
        txtReorder.setBounds(690, 120, 230, 30);
        txtReorder.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtReorder);
        
        lblPrice = new JLabel("Unit Price : ");
        lblPrice.setBounds(510, 160, 200, 30);
        lblPrice.setFont(FontsTheme.Plain_Texts);
        lblPrice.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPrice);
        
        txtPrice = new JTextField("");
        txtPrice.setBounds(690, 160, 230, 30);
        txtPrice.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtPrice);
        
        lblExpire = new JLabel("Expiration Date : ");
        lblExpire.setBounds(510, 200, 300, 30);
        lblExpire.setFont(FontsTheme.Plain_Texts);
        lblExpire.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblExpire);
        
        txtExpire = new JTextField("(YYYY-MM-DD)");
        txtExpire.setBounds(690, 200, 230, 30);
        txtExpire.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtExpire);
        
        // Add listeners
        txtStrength.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtStrength.getText().equals("(e.g., 500mg)")) {
                    txtStrength.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtStrength.getText().isEmpty()) {
                    txtStrength.setText("(e.g., 500mg)");
                }
            }
        });
        
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
        
        loadCategories();
    }
    
    private void loadCategories() {
        cmbType.addItem("Select Category...");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String sql = "SELECT category_id, category_name FROM medication_categories ORDER BY category_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String formattedCategory = rs.getInt("category_id") + " - " + rs.getString("category_name");
                    cmbType.addItem(formattedCategory);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not load category list: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            String genericName = txtGeneric.getText().trim();
            String brandName = txtName.getText().trim();
            String dosageForm = cmbDosage.getSelectedItem().toString();
            String strength = txtStrength.getText().trim();
            
            if (strength.equals("(e.g., 500mg)")) strength = ""; // Clear placeholder if ignored
            
            if (genericName.isEmpty() || cmbType.getSelectedIndex() == 0 || dosageForm.equals("Select Form...")) {
                JOptionPane.showMessageDialog(this, "Generic Name, Category, and Dosage Form are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
                
                int stockToAdd = txtCurrent.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCurrent.getText().trim());
                
                String checkSql = "SELECT medication_id, item_code, current_stock FROM pharmacy WHERE LOWER(generic_name) = LOWER(?) AND LOWER(brand_name) = LOWER(?) AND dosage_form = ? AND LOWER(strength) = LOWER(?)";
                
                try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                    checkStmt.setString(1, genericName);
                    checkStmt.setString(2, brandName);
                    checkStmt.setString(3, dosageForm);
                    checkStmt.setString(4, strength);
                    
                    ResultSet rs = checkStmt.executeQuery();
                    
                    if (rs.next()) {
                        int existingId = rs.getInt("medication_id");
                        String existingCode = rs.getString("item_code");
                        int existingStock = rs.getInt("current_stock");
                        
                        int newTotalStock = existingStock + stockToAdd;
                        
                        String updateSql = "UPDATE pharmacy SET current_stock = ?, status_id = 1 WHERE medication_id = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                            updateStmt.setInt(1, newTotalStock);
                            updateStmt.setInt(2, existingId);
                            updateStmt.executeUpdate();
                        }
                        
                        JOptionPane.showMessageDialog(this, 
                            "Medication already exists (" + existingCode + ").\n" +
                            "Stock automatically incremented by " + stockToAdd + ".\n" +
                            "New Total Stock: " + newTotalStock, 
                            "Inventory Updated", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        return; 
                    }
                }

                String generatedCode = "MED-001";
                String codeSql = "SELECT MAX(medication_id) FROM pharmacy";
                try (PreparedStatement stmtCode = connection.prepareStatement(codeSql); ResultSet rsCode = stmtCode.executeQuery()) {
                    if (rsCode.next()) {
                        int nextId = rsCode.getInt(1) + 1;
                        generatedCode = String.format("MED-%03d", nextId);
                    }
                }

                String sql = "INSERT INTO pharmacy (item_code, brand_name, generic_name, category_id, dosage_form, strength, current_stock, reorder_level, unit_price, expiration_date, status_id) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

                try (PreparedStatement insert = connection.prepareStatement(sql)) {
                    
                    String categoryInput = cmbType.getSelectedItem().toString();
                    int categoryId = Integer.parseInt(categoryInput.split(" - ")[0].trim());
                    
                    insert.setString(1, generatedCode); 
                    insert.setString(2, brandName);
                    insert.setString(3, genericName);
                    insert.setInt(4, categoryId);
                    insert.setString(5, dosageForm);
                    insert.setString(6, strength);
                    
                    int reorderLevel = txtReorder.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtReorder.getText().trim());
                    double unitPrice = txtPrice.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrice.getText().trim());
                    
                    insert.setInt(7, stockToAdd);
                    insert.setInt(8, reorderLevel);
                    insert.setDouble(9, unitPrice);
                    
                    String expireDate = txtExpire.getText().trim();
                    if (expireDate.equals("(YYYY-MM-DD)") || expireDate.isEmpty()) {
                        insert.setNull(10, java.sql.Types.DATE);
                    } else {
                        insert.setString(10, expireDate);
                    }

                    int rows = insert.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "New medication saved successfully!\nCode generated: " + generatedCode, "Pharmacy Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
                JOptionPane.showMessageDialog(this, "Stock, Reorder Level, Unit Price must be valid numbers, and Category must be selected.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}