/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import javax.swing.*;

/**
 *
 * @author eiros
 */
public class NewpatientDialog extends JDialog {

    NewpatientDialog() {
        setLayout(null);
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        JLabel lblDialogTitle = new JLabel("Patient Information");
        lblDialogTitle.setBounds(40, 25, 350, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        JLabel lblDialogDetails = new JLabel("Complete all the required fields to add a record.");
        lblDialogDetails.setBounds(40, 60, 520, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDialogDetails);

        JPanel pnlTabPersonal = createTab("Personal Information");
        pnlTabPersonal.setBounds(50, 115, 250, 40);
        add(pnlTabPersonal);

        JPanel pnlTabMedical = createTab("Medical History");
        pnlTabMedical.setBounds(300, 115, 250, 40);
        add(pnlTabMedical);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(50, 155, 950, 325);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);

        JLabel lblPatientID = createFormLabel("Patient ID :");
        lblPatientID.setBounds(40, 40, 170, 30);
        pnlForm.add(lblPatientID);

        JTextField txtPatientID = createFormTextField();
        txtPatientID.setBounds(220, 40, 230, 30);
        pnlForm.add(txtPatientID);

        JLabel lblName = createFormLabel("Name :");
        lblName.setBounds(40, 80, 170, 30);
        pnlForm.add(lblName);

        JTextField txtName = createFormTextField();
        txtName.setBounds(220, 80, 230, 30);
        pnlForm.add(txtName);

        JLabel lblAge = createFormLabel("Age :");
        lblAge.setBounds(40, 120, 170, 30);
        pnlForm.add(lblAge);

        JTextField txtAge = createFormTextField();
        txtAge.setBounds(220, 120, 230, 30);
        pnlForm.add(txtAge);

        JLabel lblBirthday = createFormLabel("Birthday :");
        lblBirthday.setBounds(40, 160, 170, 30);
        pnlForm.add(lblBirthday);

        JTextField txtBirthday = createFormTextField();
        txtBirthday.setBounds(220, 160, 230, 30);
        pnlForm.add(txtBirthday);

        JLabel lblGender = createFormLabel("Gender :");
        lblGender.setBounds(40, 200, 170, 30);
        pnlForm.add(lblGender);

        JComboBox<String> cmbGender = createFormComboBox(new String[]{"", "Female", "Male"});
        cmbGender.setBounds(220, 200, 230, 30);
        pnlForm.add(cmbGender);

        JLabel lblContact = createFormLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        pnlForm.add(lblContact);

        JTextField txtContact = createFormTextField();
        txtContact.setBounds(220, 240, 230, 30);
        pnlForm.add(txtContact);

        JLabel lblMarital = createFormLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        pnlForm.add(lblMarital);

        JComboBox<String> cmbMarital = createFormComboBox(new String[]{"", "Single", "Married", "Widowed", "Separated"});
        cmbMarital.setBounds(720, 40, 180, 30);
        pnlForm.add(cmbMarital);

        JLabel lblEmail = createFormLabel("Email Address :");
        lblEmail.setBounds(540, 80, 170, 30);
        pnlForm.add(lblEmail);

        JTextField txtEmail = createFormTextField();
        txtEmail.setBounds(720, 80, 180, 30);
        pnlForm.add(txtEmail);

        JLabel lblAddress = createFormLabel("Home Address :");
        lblAddress.setBounds(540, 120, 170, 30);
        pnlForm.add(lblAddress);

        JTextField txtAddress = createFormTextField();
        txtAddress.setBounds(720, 120, 180, 30);
        pnlForm.add(txtAddress);

        JLabel lblBlood = createFormLabel("Blood Type :");
        lblBlood.setBounds(540, 160, 170, 30);
        pnlForm.add(lblBlood);

        JTextField txtBlood = createFormTextField();
        txtBlood.setBounds(720, 160, 180, 30);
        pnlForm.add(txtBlood);

        JLabel lblStatus = createFormLabel("Patient Status :");
        lblStatus.setBounds(540, 200, 170, 30);
        pnlForm.add(lblStatus);

        JComboBox<String> cmbStatus = createFormComboBox(new String[]{"", "Admitted", "Outpatient", "Discharged"});
        cmbStatus.setBounds(720, 200, 180, 30);
        pnlForm.add(cmbStatus);

        JLabel lblRoom = createFormLabel("Room Number :");
        lblRoom.setBounds(540, 240, 170, 30);
        pnlForm.add(lblRoom);

        JTextField txtRoom = createFormTextField();
        txtRoom.setBounds(720, 240, 180, 30);
        pnlForm.add(txtRoom);

        JButton btnSave = new JButton("Save Information");
        btnSave.setBounds(800, 495, 200, 40);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(635, 495, 150, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        setVisible(true);
    }

    private JPanel createTab(String text) {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(ColorsTheme.Search_Button);

        JLabel label = new JLabel(text);
        label.setBounds(0, 0, 250, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(FontsTheme.Buttons);
        label.setForeground(ColorsTheme.Text_White);
        tab.add(label);

        return tab;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FontsTheme.Plain_Texts);
        label.setForeground(ColorsTheme.Text_Black);
        return label;
    }

    private JTextField createFormTextField() {
        JTextField textField = new JTextField();
        textField.setFont(FontsTheme.Info_Texts);
        return textField;
    }

    private JComboBox<String> createFormComboBox(String[] values) {
        JComboBox<String> comboBox = new JComboBox<>(values);
        comboBox.setFont(FontsTheme.Info_Texts);
        return comboBox;
    }
}
