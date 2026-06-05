package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class NewPharmacyDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblCode, lblName, lblGeneric, lblType, lblDosage, lblStrength, lblCurrent, lblReorder, lblPrice, lblExpire, lblTitle, lblSubtitle;
    private JTextField txtCode, txtName, txtStrength, txtCurrent, txtReorder, txtPrice, txtExpire;
    private JComboBox<String> cmbGeneric, cmbType, cmbDosage;
    private JButton btnMedicationInfo, btnCancel, btnAddInfo;
    
    private static final String[] generics = {" ", "Paracetamol", "Salbutamol", "Amoxicillin", "Mefenamic"};
    private static final String[] types = {" ", "Antibiotic", "Analgesic/Painkiller", "Antihypertensive", "Antiviral"};
    private static final String[] dosages = {" ", "Tablet", "Capsule", "Syrup", "Injection", "Ointment", "Inhaler"};
    
    
    public NewPharmacyDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        lblTitle = new JLabel("Patient Medications");
        lblTitle.setBounds(30, 10, 300, 35);
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
        
        txtCode = new JTextField("");
        txtCode.setBounds(220, 40, 230, 30);
        txtCode.setFont(FontsTheme.Plain_Texts);
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
        
        cmbGeneric = new JComboBox<>(generics);
        cmbGeneric.setBounds(220, 120, 230, 30);
        cmbGeneric.setFont(FontsTheme.Plain_Texts);
        cmbGeneric.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbGeneric);
        
        lblType = new JLabel("Category : ");
        lblType.setBounds(40, 160, 300, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
        
        cmbType = new JComboBox<>(types);
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
        
        txtCurrent = new JTextField("");
        txtCurrent.setBounds(690, 80, 230, 30);
        txtCurrent.setFont(FontsTheme.Plain_Texts);
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
        btnAddInfo.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            if (txtCode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Medication Code and Brand Name are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO pharmacy (item_code, brand_name, generic_name, category, dosage_form, strength, current_stock, reorder_level, unit_price, expiration_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = connection.prepareStatement(sql)) {
                
                insert.setString(1, txtCode.getText().trim());
                insert.setString(2, txtName.getText().trim());
                insert.setString(3, cmbGeneric.getSelectedItem().toString());
                insert.setString(4, cmbType.getSelectedItem().toString());
                insert.setString(5, cmbDosage.getSelectedItem().toString());
                insert.setString(6, txtStrength.getText().trim());
                
                int currentStock = txtCurrent.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCurrent.getText().trim());
                int reorderLevel = txtReorder.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtReorder.getText().trim());
                double unitPrice = txtPrice.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrice.getText().trim());
                
                insert.setInt(7, currentStock);
                insert.setInt(8, reorderLevel);
                insert.setDouble(9, unitPrice);
                insert.setString(10, txtExpire.getText().trim());
                
                String status = "In Stock";
                if (currentStock <= 0) status = "Critical";
                else if (currentStock <= reorderLevel) status = "Low Stock";
                
                insert.setString(11, status);

                int rows = insert.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Medication inventory saved successfully!", "Pharmacy Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Stock, Reorder Level, and Unit Price must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}