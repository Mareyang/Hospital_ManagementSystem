/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.PanelCard;
import dialogs.AddPrescriptionDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class PrescriptionsPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlDispense, pnlCancel;
    private JLabel lblDetails, lblPrescription;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblPrescription;
    
    
    public PrescriptionsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        //Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //Button for adding new prescription
        btnAdd = new JButton("+  New Prescription");
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
        
        
        //Title and subtitle label for prescription section
        lblPrescription = new JLabel("Prescription");
        lblPrescription.setBounds(30, 30, 500, 40);
        lblPrescription.setFont(FontsTheme.Bold_Texts);
        lblPrescription.setForeground(ColorsTheme.Text_Black);
        add(lblPrescription);

        lblDetails = new JLabel("Manage and dispense prescriptions.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Summary Panel Cards
        pnlPending = new PanelCard("Pending", "30", ColorsTheme.Orange);
        pnlPending.setBounds(170, 130, 400, 110);
        add(pnlPending);
        
        pnlDispense = new PanelCard("Dispensed Today", "17", ColorsTheme.Green);
        pnlDispense.setBounds(620, 130, 400, 110);
        add(pnlDispense);
       
        pnlCancel = new PanelCard("Cancelled", "4", ColorsTheme.Red);
        pnlCancel.setBounds(1070, 130, 400, 110);
        add(pnlCancel);
        
        
        
        
        //Table column names
        String[] columns = {"Patient Name", "Doctor", "Date", "Medications", "Status", "Actions"};
        
        //Sample records
        Object[][] data = {
            {"John Smith", "Dr. Chen", "May 13, 2026", "3x daily / 20 days", "Pending"," "},
            {"Sarah Johnson", "Dr. Williams", "May 14, 2026", "2x daily / 3 days", "Dispensed"," "},
            {"Maria Leonora", "Dr. Robert Chen", "May 15, 2026", "Amoxicillin 500mg (3x daily / 7 days)", "Active"," "},
            {"Jose Felipe", "Dr. Sarah Jenkins", "May 15, 2026", "Metformin 850mg (2x daily / 30 days)", "Active"," "},
            {"Angela Cruz", "Dr. Alan Reyes", "May 14, 2026", "Paracetamol 500mg (As needed for pain)", "Completed"," "},
            {"Mark Anthony", "Dr. Grace Torres", "May 14, 2026", "Losartan 50mg (Once daily / 60 days)", "Active"," "},
            {"Sophia Reyes", "Dr. Robert Chen", "May 13, 2026", "Cetirizine 10mg (Once nightly / 10 days)", "Completed"," "},
            {"Daniel Garcia", "Dr. David Kim", "May 12, 2026", "Ibuprofen 400mg (Every 8 hours / 5 days)", "Cancelled"," "},
            {"Christine Mae", "Dr. Sarah Jenkins", "May 12, 2026", "Atorvastatin 20mg (Once nightly / 30 days)", "Active"," "},
            {"Nathaniel Ong", "Dr. Alan Reyes", "May 10, 2026", "Omeprazole 20mg (Before breakfast / 14 days)", "Completed"," "},
            {"Francis Mendoza", "Dr. Grace Torres", "May 09, 2026", "Salbutamol Inhaler (1-2 puffs as needed)", "Active"," "},
            {"Jasmine Aquino", "Dr. David Kim", "May 08, 2026", "Prednisone 5mg (Once daily / 5 days)", "Completed"," "}
        };
        

        //Table Panel for records
        tblPrescription = new TablePanel("Recent Prescription", columns, data, 440);
        tblPrescription.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPrescription);



        
        //ActionListener
        btnAdd.addActionListener(this);
    }

    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens prescription form dialog
        if (e.getSource() == btnAdd) {
        AddPrescriptionDialog prescription = new AddPrescriptionDialog();
        prescription.setVisible(true);
        }
    }
    }
        

   

