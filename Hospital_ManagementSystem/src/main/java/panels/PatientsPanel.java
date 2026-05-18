/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import dialogs.AddPatientDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class PatientsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private JTable tblPatient;
    private JLabel lblPatient, lblDetails, lblTitle;
    private JTextField txtSearch;
    private JButton btnAdd, btnSearch, btnRefresh;
    private JScrollPane scrollPatient;
    
    
    public PatientsPanel() {
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
        
        
        lblPatient = new JLabel("Patient Management");
        lblPatient.setBounds(30, 30, 500, 40);
        lblPatient.setFont(FontsTheme.Bold_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        add(lblPatient);

        lblDetails = new JLabel("Manage patient records and information.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        btnAdd = new JButton("+  Add Patient");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
        add(btnAdd);

        
        txtSearch = new JTextField("Search by patient name or patient id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        btnSearch.setFocusPainted(false);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        btnRefresh.setFocusPainted(false);
        pnlSearch.add(btnRefresh);
        
        
        //Table
        String[] columns = {"Patient Name", "Patient ID", "Age/Gender", "Contact", "Department", "Actions"};
        Object[][] data = {
                {"Maria Leonora", "000021", "24 yrs - Female", "+6395874569214", "Emergency", " "},
                {"Jose Felipe", "000054", "35 yrs - Male", "+6354124785965", "Laboratory", " "},
                {"Angela Cruz", "000078", "29 yrs - Female", "+639174563821", "Pharmacy", " "},
                {"Mark Anthony", "000103", "41 yrs - Male", "+639285471236", "Billing", " "},
                {"Sophia Reyes", "000115", "19 yrs - Female", "+639167845213", "Appointments", " "},
                {"Daniel Garcia", "000126", "52 yrs - Male", "+639458721364", "Medical Records", " "},
                {"Christine Mae", "000138", "31 yrs - Female", "+639876541239", "Emergency", " "},
                {"Nathaniel Ong", "000142", "45 yrs - Male", "+639234875612", "Laboratory", " "},
                {"Francis Mendoza", "000189", "60 yrs - Male", "+639523478165", "Emergency", " "},
                {"Jasmine Aquino", "000193", "26 yrs - Female", "+639678123457", "Medical Records", " "},
                {"Miguel Santos", "000205", "33 yrs - Male", "+639234561789", "Laboratory", " "},
                {"Ella Villanueva", "000219", "28 yrs - Female", "+639854123676", "Pharmacy", " "},
                {"Adrian Torres", "000224", "47 yrs - Male", "+639741258963", "Billing", " "},

          };
        
        tblPatient = new JTable (data, columns);
        tblPatient.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblPatient.setFont(FontsTheme.Info_Texts);
        tblPatient.setRowHeight(50);
        tblPatient.setDefaultEditor(Object.class, null);
        tblPatient.getTableHeader().setReorderingAllowed(false);
        tblPatient.getTableHeader().setBackground(ColorsTheme.Header); 
        tblPatient.getTableHeader().setForeground(ColorsTheme.Text_White);
        
        scrollPatient = new JScrollPane(tblPatient);
        scrollPatient.setBounds(0, 60, 1500, 560);
        pnlMiddle.add(scrollPatient);
        
        lblTitle = new JLabel("Recent Admissions");
        lblTitle.setBounds(30, 20, 300, 30);
        lblTitle.setFont(FontsTheme.Title_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTitle);
        
        


        //ActionListener
        btnAdd.addActionListener(this);
 
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
        AddPatientDialog patient = new AddPatientDialog();
        patient.setVisible(true);
        }

        
        
        
    }
}   