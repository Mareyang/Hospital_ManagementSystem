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
public class EmergencyPanel extends JPanel {
    
    private JPanel pnlActive, pnlDispatch, pnlAvail, pnlReturn, cardPanel, TopPanel;
    private JLabel lblDetails, lblEmergency, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
   // private ImagePanel imgPatient;
    
    
    EmergencyPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        lblEmergency = new JLabel("Emergency Services");
        lblEmergency.setBounds(30, 30, 500, 40);
        lblEmergency.setFont(FontsTheme.Bold_Texts);
        lblEmergency.setForeground(ColorsTheme.Text_Black);
        add(lblEmergency);

        lblDetails = new JLabel("Manage ambulance dispatch and emergency cases");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+ Emergency Dispatch");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Delete_Urgent);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        pnlAvail = createCard(
                "Available",
                "3");
        pnlAvail.setBounds(70, 130, 350, 110);
        add(pnlAvail);
        
        
        pnlDispatch = createCard(
                "Dispatched",
                "1");
        pnlDispatch.setBounds(450, 130, 350, 110);
        add(pnlDispatch);
        
       
        pnlReturn = createCard(
                "Returning",
                "1");
        pnlReturn.setBounds(830, 130, 350, 110);
        add(pnlReturn);
        
        
        pnlActive = createCard(
                "Active Cases",
                "2");
        pnlActive.setBounds(1210, 130, 350, 110);
        add(pnlActive);
        
    }

    
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);


        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblTitle);


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
    }
    

