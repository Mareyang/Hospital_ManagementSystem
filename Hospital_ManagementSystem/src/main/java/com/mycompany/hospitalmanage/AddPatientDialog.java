/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class AddPatientDialog extends JDialog {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblID, lblName, lblAge, lblNumber, lblGender, lblStatus, lblEmail, lblAddress, 
            lblHistory, lblBlood, lblRoom, lblMarital;
    private JTextField txtID, txtName, txtAge, txtNumber, txtEmail, txtAddress, txtBlood, txtRoom, txtBirth;
    private JButton btnInfo, btnHistory, btnAddInfo,btnCancel;
    private JComboBox<String> cmbStatus, cmbGender, cmbMarital;
    private JTextArea txaHistory;
    private JScrollPane scrollHistory;
    
    
    
    
    AddPatientDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Information");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Black);
        add(lblSubtitle);
        
        
        btnInfo = new JButton("Personal Information");
        btnInfo.setBounds(40, 100, 250, 40);
        btnInfo.setFont(FontsTheme.Buttons);
        btnInfo.setForeground(ColorsTheme.Text_White);
        btnInfo.setBackground(ColorsTheme.Search_Button);
        add(btnInfo);
        
        btnHistory = new JButton("Medical History");
        btnHistory.setBounds(290, 100, 250, 40);
        btnHistory.setFont(FontsTheme.Buttons);
        btnHistory.setForeground(ColorsTheme.Text_White);
        btnHistory.setBackground(ColorsTheme.Search_Button);
        add(btnHistory);
        
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        lblID = new JLabel("Patient ID : ");
        lblID.setBounds(40, 40, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
        
        txtID = new JTextField("");
        txtID.setBounds(220, 40, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        lblName = new JLabel("Name : ");
        lblName.setBounds(40, 80, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        lblAge = new JLabel("Age : ");
        lblAge.setBounds(40, 120, 200, 30);
        lblAge.setFont(FontsTheme.Plain_Texts);
        lblAge.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAge);
        
        txtAge = new JTextField("");
        txtAge.setBounds(220, 120, 230, 30);
        txtAge.setFont(FontsTheme.Plain_Texts);
        txtAge.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAge);
        
        lblBirth = new JLabel("Birthday : ");
        lblBirth.setBounds(40, 160, 200, 30);
        lblBirth.setFont(FontsTheme.Plain_Texts);
        lblBirth.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblBirth);
        
        txtBirth = new JTextField("");
        txtBirth.setBounds(220, 160, 230, 30);
        txtBirth.setFont(FontsTheme.Plain_Texts);
        txtBirth.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtBirth);
        
        lblGender = new JLabel("Gender : ");
        lblGender.setBounds(40, 200, 200, 30);
        lblGender.setFont(FontsTheme.Plain_Texts);
        lblGender.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblGender);
        
        //BUTTONS FOR MALE AND FEMALE
        cmbGender = new JComboBox<>(new String[]{
        " ", "Male", "Female", "Prefer not to say"
        });
        cmbGender.setBounds(220, 200, 230, 30);
        cmbGender.setFont(FontsTheme.Plain_Texts);
        cmbGender.setForeground(ColorsTheme.Text_Black);
        cmbGender.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbGender);
        
        lblNumber = new JLabel("Contact Number : ");
        lblNumber.setBounds(40, 240, 200, 30);
        lblNumber.setFont(FontsTheme.Plain_Texts);
        lblNumber.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNumber);
        
        txtNumber = new JTextField("");
        txtNumber.setBounds(220, 240, 230, 30);
        txtNumber.setFont(FontsTheme.Plain_Texts);
        txtNumber.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtNumber);
        
        
        // LEFT SIDE
        
        lblMarital = new JLabel("Marital Status : ");
        lblMarital.setBounds(510, 40, 200, 30);
        lblMarital.setFont(FontsTheme.Plain_Texts);
        lblMarital.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMarital);
        
        cmbMarital = new JComboBox<>(new String[]{
        " ", "Single", "Married", "Divorced", "Widowed"
        });
        cmbMarital.setBounds(690, 40, 230, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts);
        cmbMarital.setForeground(ColorsTheme.Text_Black);
        cmbMarital.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbMarital);
        
        lblEmail = new JLabel("Email Address : ");
        lblEmail.setBounds(510, 80, 200, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblEmail);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(690, 80, 230, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtEmail);
        
        lblAddress = new JLabel("Home Address : ");
        lblAddress.setBounds(510, 120, 200, 30);
        lblAddress.setFont(FontsTheme.Plain_Texts);
        lblAddress.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAddress);
        
        txtAddress = new JTextField("");
        txtAddress.setBounds(690, 120, 230, 30);
        txtAddress.setFont(FontsTheme.Plain_Texts);
        txtAddress.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAddress);
        
        lblBlood = new JLabel("Blood Type : ");
        lblBlood.setBounds(510, 160, 200, 30);
        lblBlood.setFont(FontsTheme.Plain_Texts);
        lblBlood.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblBlood);
        
        txtBlood = new JTextField("");
        txtBlood.setBounds(690, 160, 230, 30);
        txtBlood.setFont(FontsTheme.Plain_Texts);
        txtBlood.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtBlood);
        
        lblStatus = new JLabel("Patient Status : ");
        lblStatus.setBounds(510, 200, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStatus);

        cmbStatus = new JComboBox<>(new String[]{
        " ", "Admitted", "Discharged", "Observation"
        });
        cmbStatus.setBounds(690, 200, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        cmbStatus.setForeground(ColorsTheme.Text_Black);
        cmbStatus.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbStatus);
        
        lblRoom = new JLabel("Room Number : ");
        lblRoom.setBounds(510, 240, 200, 30);
        lblRoom.setFont(FontsTheme.Plain_Texts);
        lblRoom.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRoom);
        
        txtRoom = new JTextField("");
        txtRoom.setBounds(690, 240, 230, 30);
        txtRoom.setFont(FontsTheme.Plain_Texts);
        txtRoom.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtRoom);
        

        btnAddInfo = new JButton("Save Information");
        btnAddInfo.setBounds(580, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        add(btnAddInfo);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(790, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        add(btnCancel);


    
    }
}
