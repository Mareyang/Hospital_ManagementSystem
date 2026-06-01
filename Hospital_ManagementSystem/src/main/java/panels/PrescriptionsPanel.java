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
        String[] columns = {"Rx ID", "Patient Name", "Doctor", "Date", "Medications", "Status", "Actions"};
        
        //Sample records
        Object[][] data = {
            
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
        

   

