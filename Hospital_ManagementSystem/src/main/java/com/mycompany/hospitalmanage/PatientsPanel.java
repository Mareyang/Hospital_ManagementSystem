/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class PatientsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblPatient, lblDetails, lblID, lblName, lblAge, lblBirth, lblNumber, lblGender, lblStatus, lblEmail, lblMarital, lblAddress, 
            lblHistory, lblBlood, lblRoom;
    private JTextField txtID, txtName, txtAge, txtBirth, txtNumber, txtEmail, txtAddress, txtBlood, txtRoom, txtSearch;
    private JButton btnAdd, btnSearch, btnRefresh;
    private JComboBox<String> cmbMarital, cmbStatus, cmbGender;
    private JTextArea txaHistory;
    private JScrollPane scrollHistory;
    //private ImagePanel imgPatient;
    
    
    PatientsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 250, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 130, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //        imgPatient = new ImagePanel("/patient.png"); 
//        imgPatient.setBounds(80, 150, 380, 450);
//        add(imgPatient);
        
        lblPatient = new JLabel("Patient Management");
        lblPatient.setBounds(30, 30, 500, 40);
        lblPatient.setFont(FontsTheme.Bold_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        add(lblPatient);

        lblDetails = new JLabel("Manage patient records and information");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+  Add Patient");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);


        //Search Bar
        txtSearch = new JTextField("Search by patient name or patient id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search_Button);
        btnSearch.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnRefresh);
        


        // RIGHT SIDE
        
        

        
        // BUTTONS 
//        btnUpdate = new JButton("Update Info");
//        btnUpdate.setBounds(740, 650, 200, 50); 
//        btnUpdate.setFont(FontsTheme.Plain_Texts);
//        btnUpdate.setBackground(Color.decode("#F08D39"));
//        btnUpdate.setForeground(Color.WHITE);
//        pnlMiddle.add(btnUpdate);
//
//        btnSearch = new JButton("Clear Patient");
//        btnSearch.setBounds(1000, 650, 200, 50); 
//        btnSearch.setFont(FontsTheme.Plain_Texts);
//        btnSearch.setBackground(Color.decode("#9E9E9E"));
//        btnSearch.setForeground(Color.WHITE);
//        pnlMiddle.add(btnSearch);
        
        
    }
             }   
    

