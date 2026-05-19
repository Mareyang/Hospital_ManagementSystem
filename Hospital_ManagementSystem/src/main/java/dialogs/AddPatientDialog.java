/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class AddPatientDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblID, lblName, lblAge, lblNumber, lblGender, lblStatus, lblEmail, lblAddress, 
            lblSurgery, lblBlood, lblRoom, lblMarital, lblChronic, lblChronic1, lblChronic2, lblChronic3, lblChronic4, lblAllergy,
            lblAllergy1, lblAllergy2, lblAllergy3, lblAllergy4, lblMedi, lblNote;
    private JTextField txtID, txtName, txtAge, txtNumber, txtEmail, txtAddress, txtBlood, txtBirth, txtChronic1, txtChronic2, txtChronic3,
            txtChronic4, txtAllergy1, txtAllergy2, txtAllergy3, txtAllergy4;
    private JButton btnPersonal, btnHistory, btnAddInfo,btnCancel;
    private JComboBox<String> cmbStatus, cmbGender, cmbMarital, cmbRoom;
    private JTextArea txaHistory, txaHistory1, txaNote;
    private JScrollPane scrollHistory, scrollHistory1, scrollNote;
    
    
    
    
    public AddPatientDialog() {
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
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        
        btnPersonal = new JButton("Personal Information");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Header);
        btnPersonal.setFocusPainted(false);
        add(btnPersonal);
        
        btnHistory = new JButton("Medical History");
        btnHistory.setBounds(290, 100, 250, 40);
        btnHistory.setFont(FontsTheme.Buttons);
        btnHistory.setForeground(ColorsTheme.Text_White);
        btnHistory.setBackground(ColorsTheme.Header);
        btnHistory.setFocusPainted(false);
        add(btnHistory);
        
        
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
        
        btnAddInfo = new JButton("Save Information");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Green);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        
        
        
        
        //ActionListener
        btnPersonal.addActionListener(this);
        btnHistory.addActionListener(this);
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);

        showPersonalInfo();
    
    }
    
    
    public void showPersonalInfo() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
       
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
        
        cmbRoom = new JComboBox<>(new String[]{
        " ", "ER-01", "ER-02", "LAB-01", "LAB-02", "RM-201", "RM-202", "XRAY-01", "ICU-01", "ICU-02", "OR-01"
        });
        cmbRoom.setBounds(690, 240, 230, 30);
        cmbRoom.setFont(FontsTheme.Plain_Texts);
        cmbRoom.setForeground(ColorsTheme.Text_Black);
        cmbRoom.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbRoom);
        
       

    }

    
    public void showMedicalHistory() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblChronic = new JLabel("Chronic Illnesses");
        lblChronic.setBounds(70, 10, 200, 30);
        lblChronic.setFont(FontsTheme.Title_Texts);
        lblChronic.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblChronic);
        
        
        lblChronic1 = new JLabel("Diabetes : ");
        lblChronic1.setBounds(40, 50, 100, 30);
        lblChronic1.setFont(FontsTheme.Dialog_Texts);
        lblChronic1.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblChronic1);
        
        txtChronic1 = new JTextField(" ");
        txtChronic1.setBounds(170, 50, 100, 20);
        txtChronic1.setFont(FontsTheme.Dialog_Texts);
        txtChronic1.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtChronic1);
        
        lblChronic2 = new JLabel("Hypertension : ");
        lblChronic2.setBounds(40, 80, 100, 30);
        lblChronic2.setFont(FontsTheme.Dialog_Texts);
        lblChronic2.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblChronic2);
        
        txtChronic2 = new JTextField(" ");
        txtChronic2.setBounds(170, 80, 100, 20);
        txtChronic2.setFont(FontsTheme.Dialog_Texts);
        txtChronic2.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtChronic2);
        
        lblChronic3 = new JLabel("Asthma : ");
        lblChronic3.setBounds(40, 110, 100, 30);
        lblChronic3.setFont(FontsTheme.Dialog_Texts);
        lblChronic3.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblChronic3);
        
        txtChronic3 = new JTextField(" ");
        txtChronic3.setBounds(170, 110, 100, 20);
        txtChronic3.setFont(FontsTheme.Dialog_Texts);
        txtChronic3.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtChronic3);
        
        lblChronic4 = new JLabel("Other : ");
        lblChronic4.setBounds(40, 140, 100, 30);
        lblChronic4.setFont(FontsTheme.Dialog_Texts);
        lblChronic4.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblChronic4);
        
        txtChronic4 = new JTextField(" ");
        txtChronic4.setBounds(170, 140, 100, 20);
        txtChronic4.setFont(FontsTheme.Dialog_Texts);
        txtChronic4.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtChronic4);
        
        
        lblAllergy = new JLabel("Allergies");
        lblAllergy.setBounds(380, 10, 200, 30);
        lblAllergy.setFont(FontsTheme.Title_Texts);
        lblAllergy.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAllergy);
        
        lblAllergy1 = new JLabel("Medication : ");
        lblAllergy1.setBounds(330, 50, 100, 30);
        lblAllergy1.setFont(FontsTheme.Dialog_Texts);
        lblAllergy1.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAllergy1);
        
        txtAllergy1 = new JTextField(" ");
        txtAllergy1.setBounds(460, 50, 100, 20);
        txtAllergy1.setFont(FontsTheme.Dialog_Texts);
        txtAllergy1.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAllergy1);
        
        lblAllergy2 = new JLabel("Environmental : ");
        lblAllergy2.setBounds(330, 80, 120, 30);
        lblAllergy2.setFont(FontsTheme.Dialog_Texts);
        lblAllergy2.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAllergy2);
        
        txtAllergy2 = new JTextField(" ");
        txtAllergy2.setBounds(460, 80, 100, 20);
        txtAllergy2.setFont(FontsTheme.Dialog_Texts);
        txtAllergy2.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAllergy2);
        
        lblAllergy3 = new JLabel("Food : ");
        lblAllergy3.setBounds(330, 110, 100, 30);
        lblAllergy3.setFont(FontsTheme.Dialog_Texts);
        lblAllergy3.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAllergy3);
        
        txtAllergy3 = new JTextField(" ");
        txtAllergy3.setBounds(460, 110, 100, 20);
        txtAllergy3.setFont(FontsTheme.Dialog_Texts);
        txtAllergy3.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAllergy3);
        
        lblAllergy4 = new JLabel("Other : ");
        lblAllergy4.setBounds(330, 140, 100, 30);
        lblAllergy4.setFont(FontsTheme.Dialog_Texts);
        lblAllergy4.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblAllergy4);
        
        txtAllergy4 = new JTextField(" ");
        txtAllergy4.setBounds(460, 140, 100, 20);
        txtAllergy4.setFont(FontsTheme.Dialog_Texts);
        txtAllergy4.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAllergy4);
        
        
        lblSurgery = new JLabel("Past Hospitalizations");
        lblSurgery.setBounds(660, 10, 200, 30);
        lblSurgery.setFont(FontsTheme.Title_Texts);
        lblSurgery.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblSurgery);
        
        txaHistory = new JTextArea(" ");
        txaHistory.setText("Write here...");
        txaHistory.setEditable(true);
        txaHistory.setFont(FontsTheme.Dialog_Texts);
        txaHistory.setForeground(ColorsTheme.Text_Gray);
        txaHistory.setLineWrap(true);
        txaHistory.setWrapStyleWord(true);
        
        scrollHistory = new JScrollPane(txaHistory);
        scrollHistory.setBounds(620, 50, 300, 80);
        pnlContent.setLayout(null);
        pnlContent.add(scrollHistory);
        
        
        lblMedi = new JLabel("Current Medications");
        lblMedi.setBounds(660, 150, 200, 30);
        lblMedi.setFont(FontsTheme.Title_Texts);
        lblMedi.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMedi);
        
        txaHistory1 = new JTextArea(" ");
        txaHistory1.setText("Write here...");
        txaHistory1.setEditable(true);
        txaHistory1.setFont(FontsTheme.Dialog_Texts);
        txaHistory1.setForeground(ColorsTheme.Text_Gray);
        txaHistory1.setLineWrap(true);
        txaHistory1.setWrapStyleWord(true);
        
        scrollHistory1 = new JScrollPane(txaHistory1);
        scrollHistory1.setBounds(620, 190, 300, 80);
        pnlContent.setLayout(null);
        pnlContent.add(scrollHistory1);
        
        
        lblNote = new JLabel("Additional Note");
        lblNote.setBounds(70, 180, 200, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(" ");
        txaNote.setText("Write here...");
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 210, 520, 60);
        pnlContent.setLayout(null);
        pnlContent.add(scrollNote);
    
    
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnPersonal) {
            showPersonalInfo();
        }
        else if(e.getSource() == btnHistory) {
            showMedicalHistory();
        }
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnAddInfo) {
            JOptionPane.showMessageDialog(this, "Patient record added successfully!", 
                    "Patient Record Success", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

}