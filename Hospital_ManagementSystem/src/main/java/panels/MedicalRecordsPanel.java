/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import dialogs.AddMedicalRecordDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * 
 */
public class MedicalRecordsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblMedical;
    
    
    
    public MedicalRecordsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 250, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        //Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 130, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //Button for adding new record
        btnAdd = new JButton("+  New Record");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        add(btnAdd);
        
        //Search Bar including search and refresh buttons
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
        
        
        //Title and subtitle label for medical section
        lblMedical = new JLabel("Medical Records");
        lblMedical.setBounds(30, 30, 500, 40);
        lblMedical.setFont(FontsTheme.Bold_Texts);
        lblMedical.setForeground(ColorsTheme.Text_Black);
        add(lblMedical);

        lblDetails = new JLabel("Access and manage patient medical records.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);

        
        
        
        
        //Table column names
        String[] columns = {"Patient Name", "Patient ID", "Type", "Doctor", "Date", "Actions"};
        
        //Sample records
        Object[][] data = {
            {"Maria Leonora", "000021", "New Consultation", "Dr. Robert Chen", "May 15, 2026"," "},
            {"Jose Felipe", "000054", "Follow-up Visit", "Dr. Sarah Jenkins", "May 15, 2026"," "},
            {"Angela Cruz", "000078", "Routine Check-up", "Dr. Alan Reyes", "May 14, 2026"," "},
            {"Mark Anthony", "000103", "Emergency Visit", "Dr. Grace Torres", "May 14, 2026"," "},
            {"Sophia Reyes", "000115", "Diagnostic/Lab Test", "Dr. Robert Chen", "May 13, 2026"," "},
            {"Daniel Garcia", "000126", "Follow-up Visit", "Dr. David Kim", "May 12, 2026"," "},
            {"Christine Mae", "000138", "New Consultation", "Dr. Sarah Jenkins", "May 12, 2026"," "},
            {"Nathaniel Ong", "000142", "Pre-Surgical Clearance", "Dr. Alan Reyes", "May 10, 2026"," "},
            {"Francis Mendoza", "000189", "Emergency Visit", "Dr. Grace Torres", "May 09, 2026"," "},
            {"Jasmine Aquino", "000193", "Post-Operative Check", "Dr. David Kim", "May 08, 2026"," "},
            {"Christine Mae", "000138", "New Consultation", "Dr. Sarah Jenkins", "May 12, 2026"," "},
            {"Nathaniel Ong", "000142", "Pre-Surgical Clearance", "Dr. Alan Reyes", "May 10, 2026"," "},
            {"Francis Mendoza", "000189", "Emergency Visit", "Dr. Grace Torres", "May 09, 2026"," "},
            {"Jasmine Aquino", "000193", "Post-Operative Check", "Dr. David Kim", "May 08, 2026"," "}
        };
        

        //Table Panel for records
        tblMedical = new TablePanel("Recent Medical Records", columns, data, 560);
        tblMedical.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblMedical);
        
        
        
        //ActionListener
        btnAdd.addActionListener(this);
        
        
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens medical form dialog
        if (e.getSource() == btnAdd) {
        AddMedicalRecordDialog record = new AddMedicalRecordDialog();
        record.setVisible(true);
        }
        
    }
}
