package com.mycompany.main;

import com.mycompany.hospitalmanage.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

    public class emergencyDialog extends JDialog implements ActionListener {

    JLabel lblId,lblName,lblType,lblContact,lblLocation,lblUnit,lblPriority, lblDispatch,lblETA, lblStatus,lblRemarks, lblTitle,lblSubtitle,lblInvoice;
    JTextField txtId,txtName, txtType, txtContact,txtLocation,txtUnit,txtETA,txtDispatch,txtRemarks;
    JComboBox<String> cmbStatus, cmbPriority;
    JButton btnSave, btnCancel,btnPersonal,btnHistory;
    JPanel pnlContent;
    public emergencyDialog() {

        
        setSize(1050, 550);
        setLocationRelativeTo(null);
        setLayout(null); 
        setModal(true);

       
        lblTitle = new JLabel("Emergency Dispatcch");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Black);
        add(lblSubtitle);
        
        btnPersonal = new JButton("Personal Information");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setFocusPainted(false);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Search_Button);
        add(btnPersonal);
        
        btnHistory = new JButton("Dispatch History");
        btnHistory.setBounds(290, 100, 250, 40);
        btnHistory.setFont(FontsTheme.Buttons);
        btnHistory.setFocusPainted(false);
        btnHistory.setForeground(ColorsTheme.Text_White);
        btnHistory.setBackground(ColorsTheme.Search_Button);
        add(btnHistory);
        
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
    
        //ActionListener
        btnPersonal.addActionListener(this);
        btnHistory.addActionListener(this);
        
        showPersonalInfo();
    }
        public void showPersonalInfo() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblId = new JLabel("Emergency ID:");
        lblId.setBounds(40, 40, 200, 30);
        lblId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblId);
        
        txtId = new JTextField();
        txtId.setBounds(220, 40, 230, 30);
        pnlContent.add(txtId);
        
        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 80, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(220, 80, 230, 30);
        pnlContent.add(txtName);

        lblType = new JLabel("Emergency Type:");
        lblType.setBounds(40, 120, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblType);
        
        txtType = new JTextField();
        txtType.setBounds(220, 120, 230, 30);
        pnlContent.add(txtType);
        
        lblContact = new JLabel("Contact Number:");
        lblContact.setBounds(40, 160, 200, 30);
        lblContact.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblContact);
        
        txtContact = new JTextField();
        txtContact.setBounds(220, 160, 230, 30);
        pnlContent.add(txtContact);
        
        lblLocation = new JLabel("Location:");
        lblLocation.setBounds(40, 200, 200, 30);
        lblLocation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblLocation);
        
        txtLocation = new JTextField();
        txtLocation.setBounds(220, 200, 230, 30);
        pnlContent.add(txtLocation);        
        
        lblUnit = new JLabel("Ambulance Unit:");
        lblUnit.setBounds(40, 240, 200, 30);
        lblUnit.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblUnit);
        
        txtUnit = new JTextField();
        txtUnit.setBounds(220, 240, 230, 30);
        pnlContent.add(txtUnit);
        
        // Left Side
        
        lblPriority = new JLabel("Priority Level:");
        lblPriority.setBounds(510, 40, 200, 30);
        lblPriority.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPriority);
        
        String[] payment = {"Low", "High", "Critical"};
        cmbPriority = new JComboBox<>(payment);
        cmbPriority.setBounds(690, 40, 230, 30);
        pnlContent.add(cmbPriority);
        
        lblStatus = new JLabel("Dispatch Status:");
        lblStatus.setBounds(510, 80, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
   
        String[] status = {"Available", "Dispatched","Returning"};
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(690, 80, 230, 30);
        pnlContent.add(cmbStatus);

        lblETA = new JLabel("Estimated Arrival:");
        lblETA.setBounds(510, 120, 200, 30);
        lblETA.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblETA);
        
        txtETA = new JTextField();
        txtETA.setBounds(690, 120, 230, 30);
        pnlContent.add(txtETA);
        
        lblDispatch = new JLabel("Current Time:");
        lblDispatch.setBounds(510, 160, 200, 30);
        lblDispatch.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDispatch);
        
        txtDispatch = new JTextField();
        txtDispatch.setBounds(690, 160, 230, 30);
        pnlContent.add(txtDispatch);
        
        lblRemarks = new JLabel("Additional Details:");
        lblRemarks.setBounds(510, 200, 200, 30);
        lblRemarks.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblRemarks);
        
        txtRemarks = new JTextField();
        txtRemarks.setBounds(690, 200, 230, 30);
        pnlContent.add(txtRemarks);

        btnSave = new JButton("Save Information");
        btnSave.setBounds(790, 450, 200, 30);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFont(FontsTheme.Buttons);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setFocusPainted(false);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        add(btnCancel);
        
       
    }
        public void showInvoiceHistory() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblInvoice = new JLabel("Dispatch History");
        lblInvoice.setBounds(10, 10, 200, 30);
        lblInvoice.setFont(FontsTheme.Plain_Texts);
        lblInvoice.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblInvoice);
     
        
        
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

      
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Emergency Dispatch Added Successfully!"
                );

                dispose();
            }
        });
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnPersonal) {
            showPersonalInfo();
        }
        else if(e.getSource() == btnHistory) {
            showInvoiceHistory();
        }
        
    }
        
}