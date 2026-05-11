/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class PrescriptionsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlDispense, pnlCancel, cardPanel, TopPanel;
    private JLabel lblDetails, lblPrescription, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table;
    private JScrollPane scrollPane;
   // private ImagePanel imgPatient;
    
    
    PrescriptionsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        btnAdd = new JButton("+  New Prescription");
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
        
        
        
        
        lblPrescription = new JLabel("Prescriptions");
        lblPrescription.setBounds(30, 30, 500, 40);
        lblPrescription.setFont(FontsTheme.Bold_Texts);
        lblPrescription.setForeground(ColorsTheme.Text_Black);
        add(lblPrescription);

        lblDetails = new JLabel("Manage and dispense prescriptions");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlPending = createCard(
                "Pending",
                "30");
        pnlPending.setBounds(170, 130, 400, 110);
        add(pnlPending);
        
        
        pnlDispense = createCard(
                "Dispensed Today",
                "17");
        pnlDispense.setBounds(620, 130, 400, 110);
        add(pnlDispense);
        
       
        pnlCancel = createCard(
                "Cancelled",
                "4");
        pnlCancel.setBounds(1070, 130, 400, 110);
        add(pnlCancel);
        
        String[] columns = {"Patient", "Doctor", "Date", "Medications", "Status"};
        
        Object[][] data = {
            {"John Smith", "Dr. Chen", "2024-01-15", "3 items", "Pending"},
            {"Sarah Johnson", "Dr. Williams", "2024-01-14", "2 items", "Dispensed"}
        };
        
        table = new JTable(data, columns);
        table.getTableHeader().setFont(FontsTheme.Bold_Texts); // set ng font sa header ng table
        table.setFont(FontsTheme.Plain_Texts);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null); // para di ma edit mga text sa mga cells
        table.getTableHeader().setReorderingAllowed(false); //para di ma galaw yung header ng table
        table.getTableHeader().setBackground(ColorsTheme.Top_Line); 
        table.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,1500,500);

        pnlMiddle.add(scrollPane);
        
        
        
    }

    
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 400, 10);
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
        lblValue.setForeground(Color.BLACK);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
    }
        

   

