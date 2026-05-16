/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import dialogs.NewpharmacyDialog;
<<<<<<< HEAD
import dialogs.NewpharmacyDialog;
=======
>>>>>>> Agulto

/**
 *
 * @author Arabella
 */
public class PharmacyPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlIn, pnlLow, pnlCrit, cardPanel, TopPanel;
    private JLabel lblDetails, lblPharmacy, lblTitle, lblValue, lblSubtitle, lblTableTitle;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable tblInventory;
    private JScrollPane scrollInventory;
   // private ImagePanel imgPatient;
    
    
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
        btnAdd.addActionListener(this);
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
        
        
        
        
        JLabel lblPharmacyIcon = createIconLabel("/icons/pharmacy.png");
        lblPharmacyIcon.setBounds(30, 25, 72, 72);
        add(lblPharmacyIcon);

        lblPharmacy = new JLabel("Pharmacy");
        lblPharmacy.setBounds(120, 30, 500, 40);
        lblPharmacy.setFont(FontsTheme.Bold_Texts);
        lblPharmacy.setForeground(ColorsTheme.Text_Black);
        add(lblPharmacy);

        lblDetails = new JLabel("Manage medications and inventory");
        lblDetails.setBounds(120, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlTotal = createCard(
                "Total Items",
                "1,245",
                "All medicines",
                ColorsTheme.Top_Line);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlIn = createCard(
                "In Stock",
                "1,180",
                "Available items",
                ColorsTheme.Add_Confirm);
        pnlIn.setBounds(450, 130, 350, 110);
        add(pnlIn);
        
       
        pnlLow = createCard(
                "Low Stock",
                "48",
                "Needs reorder",
                ColorsTheme.Update_Pending);
        pnlLow.setBounds(830, 130, 350, 110);
        add(pnlLow);
        
        
        pnlCrit = createCard(
                "Critical",
                "17",
                "Urgent restock",
                ColorsTheme.Delete_Urgent);
        pnlCrit.setBounds(1210, 130, 350, 110);
        add(pnlCrit);
        
        createInventoryTable();
        
    }

    
    public JPanel createCard(String title, String value, String subtitle, Color accentColor) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(accentColor);
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
        
        //Subtitle
        lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setBounds(20, 88, 250, 20);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        lblSubtitle.setFont(FontsTheme.Info_Texts);
        cardPanel.add(lblSubtitle);


        return cardPanel;
        
        
            }
    
    private void createInventoryTable() {
        lblTableTitle = new JLabel("Medication Inventory");
        lblTableTitle.setBounds(25, 20, 400, 30);
        lblTableTitle.setFont(FontsTheme.Title_Texts);
        lblTableTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTableTitle);
        
        String[] columns = {"Medication", "Item Code", "Category", "Stock", "Reorder Level", "Expiry Date", "Status"};
        Object[][] data = {
                {"Paracetamol 500mg", "MED-001", "Pain Reliever", "320", "100", "Dec 20, 2026", "In Stock"},
                {"Amoxicillin 500mg", "MED-014", "Antibiotic", "42", "50", "Aug 15, 2026", "Low Stock"},
                {"Salbutamol Inhaler", "MED-022", "Respiratory", "12", "30", "Oct 03, 2026", "Critical"},
                {"Cetirizine 10mg", "MED-031", "Antihistamine", "180", "60", "Jan 10, 2027", "In Stock"},
                {"Metformin 500mg", "MED-047", "Diabetes", "65", "80", "Nov 29, 2026", "Low Stock"},
                {"Losartan 50mg", "MED-053", "Hypertension", "210", "70", "Mar 18, 2027", "In Stock"},
                {"Omeprazole 20mg", "MED-066", "Gastrointestinal", "25", "40", "Jul 02, 2026", "Critical"},
                {"Ibuprofen 200mg", "MED-078", "Pain Reliever", "140", "50", "Sep 25, 2026", "In Stock"}
        };
        
        tblInventory = new JTable(data, columns);
        tblInventory.setFont(FontsTheme.Info_Texts);
        tblInventory.setRowHeight(48);
        tblInventory.setDefaultEditor(Object.class, null);
        tblInventory.setShowGrid(false);
        tblInventory.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        JTableHeader tableHeader = tblInventory.getTableHeader();
        tableHeader.setFont(FontsTheme.Title_Texts);
        tableHeader.setBackground(ColorsTheme.Header);
        tableHeader.setForeground(ColorsTheme.Text_White);
        tableHeader.setReorderingAllowed(false);
        
        tblInventory.getColumnModel().getColumn(6).setCellRenderer(createStockStatusRenderer());
        
        scrollInventory = new JScrollPane(tblInventory);
        scrollInventory.setBounds(25, 65, 1450, 405);
        pnlMiddle.add(scrollInventory);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (btnAdd == e.getSource()) {
            NewpharmacyDialog phar = new NewpharmacyDialog();
            phar.setVisible(true);
        }
    }
    
    private DefaultTableCellRenderer createStockStatusRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value.toString();
                
                changeStockStatusColor(cell, status);
                
                return cell;
            }
        };
        
        return renderer;
    }
    
    private void changeStockStatusColor(java.awt.Component cell, String status) {
        cell.setForeground(ColorsTheme.Text_Black);
        
        if (status.equals("In Stock")) {
            cell.setForeground(ColorsTheme.Add_Confirm);
        } else if (status.equals("Low Stock")) {
            cell.setForeground(new Color(180, 120, 0));
        } else if (status.equals("Critical")) {
            cell.setForeground(ColorsTheme.Delete_Urgent);
        }
    }
    
    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FontsTheme.Plain_Texts);
        label.setForeground(ColorsTheme.Text_Black);
        return label;
    }
    
    private JTextField createFormTextField() {
        JTextField textField = new JTextField();
        textField.setFont(FontsTheme.Info_Texts);
        return textField;
    }

    private JLabel createIconLabel(String path) {
        java.net.URL resource = getClass().getResource(path);
        ImageIcon icon;
        
        if (resource != null) {
            icon = new ImageIcon(resource);
        } else {
            icon = new ImageIcon("src/main/resources" + path);
        }
        
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(72, 72, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        
        return label;
    }
    
    }
        
    
    

