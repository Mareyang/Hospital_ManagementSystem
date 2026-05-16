/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

<<<<<<< HEAD
import dialogs.AddMedicalRecordDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
>>>>>>> parent of 720ed23 (meow)

/**
 *
 * @author Arabella
 */
<<<<<<< HEAD
public class MedicalRecordsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails, lblTitle;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table;
    private JScrollPane scrollTable;
    
    
    
    public MedicalRecordsPanel() {
=======
public class MedicalRecordsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnSearch1;
    
    
    
    MedicalRecordsPanel() {
>>>>>>> parent of 720ed23 (meow)
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
<<<<<<< HEAD
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
=======
        add(btnAdd);
        
        //        imgPatient = new ImagePanel("/patient.png"); 
//        imgPatient.setBounds(80, 150, 380, 450);
//        add(imgPatient);
        
>>>>>>> parent of 720ed23 (meow)
        lblMedical = new JLabel("Medical Records");
        lblMedical.setBounds(30, 30, 500, 40);
        lblMedical.setFont(FontsTheme.Bold_Texts);
        lblMedical.setForeground(ColorsTheme.Text_Black);
        add(lblMedical);

<<<<<<< HEAD
        lblDetails = new JLabel("Access and manage patient medical records.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);

=======
        lblDetails = new JLabel("Access and manage patient medical records");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);


        //Search Bar
>>>>>>> parent of 720ed23 (meow)
        txtSearch = new JTextField("Search by patient name or patient id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
<<<<<<< HEAD
=======
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/search.png"));

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnSearch1 = new JButton(scaledIcon); // use your class variable
        btnSearch1.setBounds(40, 20, 40, 39);
        btnSearch1.setBackground(Color.decode("#3A2A75"));
        btnSearch1.setBorder(BorderFactory.createEmptyBorder());
        
        pnlSearch.add(btnSearch1);
>>>>>>> parent of 720ed23 (meow)
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
<<<<<<< HEAD
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        btnSearch.setFocusPainted(false);
=======
        btnSearch.setBackground(ColorsTheme.Search_Button);
        btnSearch.setForeground(ColorsTheme.Text_White);
>>>>>>> parent of 720ed23 (meow)
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
<<<<<<< HEAD
        btnRefresh.setFocusPainted(false);
        pnlSearch.add(btnRefresh);
        
        String[] columns = {"Patient Name", "Patient ID", "Type", "Doctor", "Date"};
        
        Object[][] data = {
            {"Maria Leonora", "000021", "New Consultation", "Dr. Robert Chen", "May 15, 2026"},
            {"Jose Felipe", "000054", "Follow-up Visit", "Dr. Sarah Jenkins", "May 15, 2026"},
            {"Angela Cruz", "000078", "Routine Check-up", "Dr. Alan Reyes", "May 14, 2026"},
            {"Mark Anthony", "000103", "Emergency Visit", "Dr. Grace Torres", "May 14, 2026"},
            {"Sophia Reyes", "000115", "Diagnostic/Lab Test", "Dr. Robert Chen", "May 13, 2026"},
            {"Daniel Garcia", "000126", "Follow-up Visit", "Dr. David Kim", "May 12, 2026"},
            {"Christine Mae", "000138", "New Consultation", "Dr. Sarah Jenkins", "May 12, 2026"},
            {"Nathaniel Ong", "000142", "Pre-Surgical Clearance", "Dr. Alan Reyes", "May 10, 2026"},
            {"Francis Mendoza", "000189", "Emergency Visit", "Dr. Grace Torres", "May 09, 2026"},
            {"Jasmine Aquino", "000193", "Post-Operative Check", "Dr. David Kim", "May 08, 2026"}
        };
        
        table = new JTable(data, columns);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(FontsTheme.Title_Texts);
        table.setFont(FontsTheme.Info_Texts);
        table.getTableHeader().setBackground(ColorsTheme.Header); 
        table.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollTable = new JScrollPane(table);
        scrollTable.setBounds(0, 60, 1500, 560);
        pnlMiddle.add(scrollTable);
        
        lblTitle = new JLabel("Recent Medical Records");
        lblTitle.setBounds(30, 20, 300, 30);
        lblTitle.setFont(FontsTheme.Title_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTitle);
    
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnAdd) {
        AddMedicalRecordDialog record = new AddMedicalRecordDialog();
        record.setVisible(true);
        }
        
    }
=======
        pnlSearch.add(btnRefresh);
    
        }
>>>>>>> parent of 720ed23 (meow)
}
