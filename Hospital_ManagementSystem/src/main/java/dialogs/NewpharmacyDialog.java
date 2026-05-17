/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author eiros
 */
public class NewPharmacyDialog extends JDialog implements ActionListener {
    
    private JLabel lblDialogTitle, lblDialogDetails, lblCode, lblName, lblGeneric, lblStrength, 
            lblDosage, lblType, lblReorder, lblCurrent, lblPrice, lblExpire;
    private JTextField txtCode, txtName, txtStrength, txtCurrent, txtReorder, txtPrice, txtExpire;
    private JComboBox<String> cmbDepart, cmbType, cmbGeneric;
    private JButton btnMedicationInfo, btnCancel, btnAddInfo;
    private JPanel pnlContent;
    
    
    
    
    public NewPharmacyDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

       
        lblDialogTitle = new JLabel("Patient Medications");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Add a new medication to the inventory.");
        lblDialogDetails.setBounds(30, 40, 450, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

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
        
        

        //ActionListener
        btnMedicationInfo.addActionListener(this);
        btnCancel.addActionListener(this);

        showMedicationInfo();

        
    }

    
    public void showMedicationInfo() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
       
        lblCode = new JLabel("Medication Code : ");
        lblCode.setBounds(40, 40, 200, 30);
        lblCode.setFont(FontsTheme.Plain_Texts);
        lblCode.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCode);
        
        txtCode = new JTextField("");
        txtCode.setBounds(220, 40, 230, 30);
        txtCode.setFont(FontsTheme.Plain_Texts);
        txtCode.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtCode);
        
        lblName = new JLabel("Brand Name : ");
        lblName.setBounds(40, 80, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        lblGeneric = new JLabel("Generic Name : ");
        lblGeneric.setBounds(40, 120, 200, 30);
        lblGeneric.setFont(FontsTheme.Plain_Texts);
        lblGeneric.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblGeneric);
        
        cmbGeneric = new JComboBox<>(new String[]{
        " ", "Paracetamol", "Salbutamol", "Amoxicillin", "Mefenamic",
        });
        cmbGeneric.setBounds(220, 120, 230, 30);
        cmbGeneric.setFont(FontsTheme.Plain_Texts);
        cmbGeneric.setForeground(ColorsTheme.Text_Black);
        cmbGeneric.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbGeneric);
        
        lblType = new JLabel("Category : ");
        lblType.setBounds(40, 160, 300, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
        
        cmbType = new JComboBox<>(new String[]{
        " ", "Antibiotic", "Analgesic/Painkiller", "Antihypertensive", "Antiviral",
        });
        cmbType.setBounds(220, 160, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setForeground(ColorsTheme.Text_Black);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        
        lblDosage = new JLabel("Dosage Form : ");
        lblDosage.setBounds(40, 200, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
        
        
        cmbDepart = new JComboBox<>(new String[]{
        " ", "Tablet", "Capsule", "Syrup", "Injection ", "Ointment", "Inhaler",
        });
        cmbDepart.setBounds(220, 200, 230, 30);
        cmbDepart.setFont(FontsTheme.Plain_Texts);
        cmbDepart.setForeground(ColorsTheme.Text_Black);
        cmbDepart.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDepart);
        
       
        //Left
        lblStrength = new JLabel("Strength : ");
        lblStrength.setBounds(510, 40, 200, 30);
        lblStrength.setFont(FontsTheme.Plain_Texts);
        lblStrength.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStrength);
        
        txtStrength = new JTextField("");
        txtStrength.setBounds(690, 40, 230, 30);
        txtStrength.setFont(FontsTheme.Plain_Texts);
        txtStrength.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtStrength);
        
        lblCurrent = new JLabel("Current Stock : ");
        lblCurrent.setBounds(510, 80, 200, 30);
        lblCurrent.setFont(FontsTheme.Plain_Texts);
        lblCurrent.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCurrent);
        
        
        txtCurrent = new JTextField("");
        txtCurrent.setBounds(690, 80, 230, 30);
        txtCurrent.setFont(FontsTheme.Plain_Texts);
        txtCurrent.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtCurrent);
        
       
        lblReorder = new JLabel("Reorder Level : ");
        lblReorder.setBounds(510, 120, 300, 30);
        lblReorder.setFont(FontsTheme.Plain_Texts);
        lblReorder.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblReorder);
        
        txtReorder = new JTextField("");
        txtReorder.setBounds(690, 120, 230, 30);
        txtReorder.setFont(FontsTheme.Plain_Texts);
        txtReorder.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtReorder);
        
        lblPrice = new JLabel("Unit Price/Cost : ");
        lblPrice.setBounds(510, 160, 200, 30);
        lblPrice.setFont(FontsTheme.Plain_Texts);
        lblPrice.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPrice);
        
        
        txtPrice = new JTextField("");
        txtPrice.setBounds(690, 160, 230, 30);
        txtPrice.setFont(FontsTheme.Plain_Texts);
        txtPrice.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtPrice);
        
       
        lblExpire = new JLabel("Expiration Date : ");
        lblExpire.setBounds(510, 200, 300, 30);
        lblExpire.setFont(FontsTheme.Plain_Texts);
        lblExpire.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblExpire);
        
        txtExpire = new JTextField("");
        txtExpire.setBounds(690, 200, 230, 30);
        txtExpire.setFont(FontsTheme.Plain_Texts);
        txtExpire.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtExpire);
        
    
    
    
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnMedicationInfo) {
            showMedicationInfo();
        }
        else if(e.getSource() == btnCancel);
            dispose();
        }

    }
  
        
        
