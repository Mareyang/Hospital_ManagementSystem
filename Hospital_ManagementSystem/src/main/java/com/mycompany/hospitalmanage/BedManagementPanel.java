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
public class BedManagementPanel extends JPanel {
    
    private JPanel pnlTotal, pnlOccupied, pnlAvail, pnlMaintenance, cardPanel, TopPanel;
    private JLabel lblDetails, lblBed, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
   // private ImagePanel imgPatient;
    
    
    BedManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        lblBed = new JLabel("Bed Management");
        lblBed.setBounds(30, 30, 500, 40);
        lblBed.setFont(FontsTheme.Bold_Texts);
        lblBed.setForeground(ColorsTheme.Text_Black);
        add(lblBed);

        lblDetails = new JLabel("Monitor and manage bed availability");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlTotal = createCard(
                "Total Beds",
                "200");
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlOccupied = createCard(
                "Occupied",
                "150");
        pnlOccupied.setBounds(450, 130, 350, 110);
        add(pnlOccupied);
        
       
        pnlAvail = createCard(
                "Available",
                "43");
        pnlAvail.setBounds(830, 130, 350, 110);
        add(pnlAvail);
        
        
        pnlMaintenance = createCard(
                "Maintenance",
                "7");
        pnlMaintenance.setBounds(1210, 130, 350, 110);
        add(pnlMaintenance);
        
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
    

