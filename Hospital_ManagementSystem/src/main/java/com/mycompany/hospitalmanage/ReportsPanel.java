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
public class ReportsPanel extends JPanel {
    
    private JPanel pnlMiddle;
    private JLabel lblDetails, lblReports;
    private JButton btnAdd;
    
    
    ReportsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 130, 1500, 750);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        lblReports = new JLabel("Reports");
        lblReports.setBounds(30, 30, 500, 40);
        lblReports.setFont(FontsTheme.Bold_Texts);
        lblReports.setForeground(ColorsTheme.Text_Black);
        add(lblReports);

        lblDetails = new JLabel("Generate and view hospital reports");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+  Generate Report");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
    }   
}
    

