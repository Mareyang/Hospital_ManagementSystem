/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import dialogs.NewPharmacyDialog;

/**
 *
 * 
 */
public class PharmacyPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlIn, pnlLow, pnlCrit;
    private JLabel lblDetails, lblPharmacy;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblPharmacy;
    
    
    public PharmacyPanel() {
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
        
        btnAdd = new JButton("+  Add Medication");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        //Search Bar
        txtSearch = new JTextField("Search medication name, item code, or category...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnRefresh);
        
       

        lblPharmacy = new JLabel("Pharmacy");
        lblPharmacy.setBounds(30, 30, 500, 40);
        lblPharmacy.setFont(FontsTheme.Bold_Texts);
        lblPharmacy.setForeground(ColorsTheme.Text_Black);
        add(lblPharmacy);

        lblDetails = new JLabel("Manage medications and inventory.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        pnlTotal = new PanelCard("Total Items", "1,245", ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlIn = new PanelCard("In Stock", "1,180", ColorsTheme.Green);
        pnlIn.setBounds(450, 130, 350, 110);
        add(pnlIn);
        
       
        pnlLow = new PanelCard("Low Stock", "48", ColorsTheme.Yellow);
        pnlLow.setBounds(830, 130, 350, 110);
        add(pnlLow);
        
        
        pnlCrit = new PanelCard("Critical", "17", ColorsTheme.Red);
        pnlCrit.setBounds(1210, 130, 350, 110);
        add(pnlCrit);
        
        
        
        //Tables
        String[] columns = {"Medication", "Item Code", "Category", "Stock", "Reorder Level", "Status", "Actions"};
        Object[][] data = {
                {"Paracetamol 500mg", "MED-001", "Pain Reliever", "320", "100", "In Stock", " "},
                {"Amoxicillin 500mg", "MED-014", "Antibiotic", "42", "50", "Low Stock", " "},
                {"Salbutamol Inhaler", "MED-022", "Respiratory", "12", "30", "Critical", " "},
                {"Cetirizine 10mg", "MED-031", "Antihistamine", "180", "60", "In Stock", " "},
                {"Metformin 500mg", "MED-047", "Diabetes", "65", "80", "Low Stock", " "},
                {"Losartan 50mg", "MED-053", "Hypertension", "210", "70", "In Stock", " "},
                {"Omeprazole 20mg", "MED-066", "Gastrointestinal", "25", "40", "Critical", " "},
                {"Ibuprofen 200mg", "MED-078", "Pain Reliever", "140", "50", "In Stock", " "}
        };
        
//        tblPharmacy = new JTable (data, columns);
//        tblPharmacy.getTableHeader().setFont(FontsTheme.Title_Texts);
//        tblPharmacy.setFont(FontsTheme.Info_Texts);
//        tblPharmacy.setRowHeight(50);
//        tblPharmacy.setDefaultEditor(Object.class, null);
//        tblPharmacy.getTableHeader().setReorderingAllowed(false);
//        tblPharmacy.getTableHeader().setBackground(ColorsTheme.Header); 
//        tblPharmacy.getTableHeader().setForeground(ColorsTheme.Text_White);
//        
//        scrollPhar = new JScrollPane(tblPharmacy);
//        scrollPhar.setBounds(0, 60, 1500, 560);
//        pnlMiddle.add(scrollPhar);
//        
//        lblHead = new JLabel("Medication Inventory");
//        lblHead.setBounds(30, 20, 300, 30);
//        lblHead.setFont(FontsTheme.Title_Texts);
//        lblHead.setForeground(ColorsTheme.Text_Black);
//        pnlMiddle.add(lblHead);


        tblPharmacy = new TablePanel("Medication Inventory", columns, data, 560);
        tblPharmacy.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPharmacy);
    
        
        
        //ActionListener
        btnAdd.addActionListener(this);
        
    }

    
   
        
       

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd) {
            NewPharmacyDialog pharmacy = new NewPharmacyDialog();
            pharmacy.setVisible(true);
        }
    }
    
} 
        
        

    
    
    

