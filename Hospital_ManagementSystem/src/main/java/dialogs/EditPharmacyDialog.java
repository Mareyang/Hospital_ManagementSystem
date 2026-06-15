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

public class EditPharmacyDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblCode, lblName, lblGeneric, lblType, lblDosage, lblStrength, lblCurrent, lblReorder, lblPrice, lblExpire, lblTitle, lblSubtitle;
    private JTextField txtCode, txtName, txtGeneric, txtStrength, txtCurrent, txtReorder, txtPrice, txtExpire;
    private JComboBox<String> cmbType, cmbDosage; 
    private JButton btnMedicationInfo, btnCancel, btnSaveInfo;
    
    private String currentItemCode;
    
    private static final String[] dosages = {"Select Form...", "Tablet", "Capsule", "Syrup", "Injection", "Ointment", "Inhaler", "IV"};
    
    public EditPharmacyDialog(String itemCode) {
        this.currentItemCode = itemCode;
        
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);

        lblTitle = new JLabel("Edit Medication");
        lblTitle.setBounds(30, 10, 400, 35);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblSubtitle = new JLabel("Update metadata for " + itemCode + ".");
        lblSubtitle.setBounds(30, 40, 450, 30);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);

        btnMedicationInfo = new JButton("Update Form");
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
        
        btnSaveInfo = new JButton("Save Changes");
        btnSaveInfo.setBounds(790, 450, 200, 30);
        btnSaveInfo.setFont(FontsTheme.Buttons);
        btnSaveInfo.setForeground(ColorsTheme.Text_White);
        btnSaveInfo.setBackground(ColorsTheme.Green); 
        btnSaveInfo.setFocusPainted(false);
        add(btnSaveInfo);
        
        // LEFT SECTION
        lblCode = new JLabel("Medication Code : ");
        lblCode.setBounds(40, 40, 200, 30);
        lblCode.setFont(FontsTheme.Plain_Texts);
        lblCode.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCode);
        
        // Locked Item Code
        txtCode = new JTextField(itemCode);
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
        lblStrength = new JLabel("Strength : ");
        lblStrength.setBounds(510, 40, 200, 30);
        lblStrength.setFont(FontsTheme.Plain_Texts);
        lblStrength.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStrength);
        
        txtStrength = new JTextField("");
        txtStrength.setBounds(690, 40, 230, 30);
        txtStrength.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtStrength);
        
        lblCurrent = new JLabel("Current Stock : ");
        lblCurrent.setBounds(510, 80, 200, 30);
        lblCurrent.setFont(FontsTheme.Plain_Texts);
        lblCurrent.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCurrent);
        
        // Locked Current Stock - Enforces proper inventory workflow!
        txtCurrent = new JTextField("");
        txtCurrent.setBounds(690, 80, 230, 30);
        txtCurrent.setFont(FontsTheme.Plain_Texts);
        txtCurrent.setEditable(false);
        pnlContent.add(txtCurrent);
        
        lblReorder = new JLabel("Reorder Level : ");
        lblReorder.setBounds(510, 120, 300, 30);
        lblReorder.setFont(FontsTheme.Plain_Texts);
        lblReorder.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblReorder);
        
        txtReorder = new JTextField("");
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
        
        txtExpire = new JTextField("");
        txtExpire.setBounds(690, 200, 230, 30);
        txtExpire.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtExpire);
        
        btnCancel.addActionListener(this);
        btnSaveInfo.addActionListener(this);
        
        loadCategories();
        loadMedicationData();
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
    
    private void loadMedicationData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String sql = "SELECT * FROM pharmacy WHERE item_code = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, currentItemCode);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    txtName.setText(rs.getString("brand_name") != null ? rs.getString("brand_name") : "");
                    txtGeneric.setText(rs.getString("generic_name"));
                    txtStrength.setText(rs.getString("strength") != null ? rs.getString("strength") : "");
                    txtCurrent.setText(String.valueOf(rs.getInt("current_stock")));
                    txtReorder.setText(String.valueOf(rs.getInt("reorder_level")));
                    txtPrice.setText(String.valueOf(rs.getDouble("unit_price")));
                    
                    java.sql.Date expDate = rs.getDate("expiration_date");
                    txtExpire.setText(expDate != null ? expDate.toString() : "(YYYY-MM-DD)");
                    
                    // Match Dosage Dropdown
                    String dbDosage = rs.getString("dosage_form");
                    if (dbDosage != null) cmbDosage.setSelectedItem(dbDosage);
                    
                    // Match Category Dropdown
                    int catId = rs.getInt("category_id");
                    for (int i = 0; i < cmbType.getItemCount(); i++) {
                        if (cmbType.getItemAt(i).startsWith(catId + " - ")) {
                            cmbType.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not load medication data: " + ex.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnSaveInfo) {
            if (txtGeneric.getText().trim().isEmpty() || cmbType.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Generic Name and Category are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // NOTE: We do NOT update current_stock here!
            String sql = "UPDATE pharmacy SET brand_name = ?, generic_name = ?, category_id = ?, dosage_form = ?, strength = ?, reorder_level = ?, unit_price = ?, expiration_date = ? WHERE item_code = ?";

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement update = connection.prepareStatement(sql)) {
                
                String categoryInput = cmbType.getSelectedItem().toString();
                int categoryId = Integer.parseInt(categoryInput.split(" - ")[0].trim());
                
                update.setString(1, txtName.getText().trim());
                update.setString(2, txtGeneric.getText().trim());
                update.setInt(3, categoryId);
                update.setString(4, cmbDosage.getSelectedItem().toString());
                update.setString(5, txtStrength.getText().trim());
                
                int reorderLevel = txtReorder.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtReorder.getText().trim());
                double unitPrice = txtPrice.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrice.getText().trim());
                
                update.setInt(6, reorderLevel);
                update.setDouble(7, unitPrice);
                
                String expireDate = txtExpire.getText().trim();
                if (expireDate.equals("(YYYY-MM-DD)") || expireDate.isEmpty()) {
                    update.setNull(8, java.sql.Types.DATE);
                } else {
                    update.setString(8, expireDate);
                }
                
                update.setString(9, currentItemCode); // The WHERE clause

                int rows = update.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Medication details updated successfully!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Reorder Level and Unit Price must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}