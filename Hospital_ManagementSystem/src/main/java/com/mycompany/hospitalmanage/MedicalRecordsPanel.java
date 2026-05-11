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
public class MedicalRecordsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table;
    private JScrollPane scrollPane;
    
    
    
    MedicalRecordsPanel() {
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
        
        btnAdd = new JButton("+  New Record");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        //        imgPatient = new ImagePanel("/patient.png"); 
//        imgPatient.setBounds(80, 150, 380, 450);
//        add(imgPatient);
        
        lblMedical = new JLabel("Medical Records");
        lblMedical.setBounds(30, 30, 500, 40);
        lblMedical.setFont(FontsTheme.Bold_Texts);
        lblMedical.setForeground(ColorsTheme.Text_Black);
        add(lblMedical);

        lblDetails = new JLabel("Access and manage patient medical records");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);


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
        
        String[] columns = {"Patient", "MRN", "Type", "Doctor", "Date"};
        
        Object[][] data = {
            {"John Smith", "MRN000001", "Consultation", "Dr. Chen", "2024-01-15", "Cardiology"},
            {"Sarah Johnson", "MRN000002", "Lab Result", "Dr. Williams", "2024-01-14", "Laboratory"}
        };
        
        table = new JTable(data, columns);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null); // para di ma edit mga text sa mga cells
        table.getTableHeader().setReorderingAllowed(false); //para di ma galaw yung header ng table
        table.getTableHeader().setFont(FontsTheme.Title_Texts);
        table.setFont(FontsTheme.Info_Texts);
        table.getTableHeader().setBackground(ColorsTheme.Header); 
        table.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,1500,620);

        pnlMiddle.add(scrollPane);
    
        }
}
