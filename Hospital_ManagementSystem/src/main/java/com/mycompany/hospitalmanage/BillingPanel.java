/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<< HEAD
<<<<<<< HEAD:Hospital_ManagementSystem/src/main/java/panels/BillingPanel.java
package panels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.hospitalmanage.*;
import dialogs.AddBillingDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
=======
package com.mycompany.hospitalmanage;

>>>>>>> parent of 720ed23 (meow):Hospital_ManagementSystem/src/main/java/com/mycompany/hospitalmanage/BillingPanel.java
import java.awt.Color;
=======
package com.mycompany.hospitalmanage;

import java.awt.Color;
import java.awt.Image;
>>>>>>> parent of 720ed23 (meow)
import javax.swing.*;

/**
 *
 * @author Arabella
 */
<<<<<<< HEAD
public class BillingPanel extends JPanel implements ActionListener  {
    
    private JPanel pnlMiddle, pnlSearch, pnlRevenue, pnlOverdue, pnlPending, pnlInsurance, pnlCard, pnlTop;
    private JLabel lblDetails, lblBilling, lblTitle, lblValue,lblTableTitle;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable tblBill;
    private JScrollPane scrollBill;
   // private ImagePanel imgPatient;
    
    
    public BillingPanel() {
=======
public class BillingPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch, pnlRevenue, pnlOverdue, pnlPending, pnlInsurance, cardPanel, TopPanel;
    private JLabel lblDetails, lblBilling, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnSearch1;
   // private ImagePanel imgPatient;
    
    
    BillingPanel() {
>>>>>>> parent of 720ed23 (meow)
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
        
        btnAdd = new JButton("+  New Invoice");
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
        
>>>>>>> parent of 720ed23 (meow)
        //Search Bar
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
        btnSearch.setBackground(ColorsTheme.Search_Button);
        btnSearch.setForeground(ColorsTheme.Text_White);
<<<<<<< HEAD
        btnSearch.setFocusPainted(false);
=======
>>>>>>> parent of 720ed23 (meow)
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
<<<<<<< HEAD
        btnRefresh.setFocusPainted(false);
=======
>>>>>>> parent of 720ed23 (meow)
        pnlSearch.add(btnRefresh);
        
        
        
        
        lblBilling = new JLabel("Billing");
        lblBilling.setBounds(30, 30, 500, 40);
        lblBilling.setFont(FontsTheme.Bold_Texts);
        lblBilling.setForeground(ColorsTheme.Text_Black);
        add(lblBilling);

<<<<<<< HEAD
        lblDetails = new JLabel("Manage invoices and payments.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        pnlRevenue = createCard("Today's Revenue", "₱12,500", ColorsTheme.Blue);
=======
        lblDetails = new JLabel("Manage invoices and payments");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlRevenue = createCard(
                "Today's Revenue",
                "₱12,500");
>>>>>>> parent of 720ed23 (meow)
        pnlRevenue.setBounds(70, 130, 350, 110);
        add(pnlRevenue);
        
        
<<<<<<< HEAD
        pnlPending = createCard("Pending", "₱8,250", ColorsTheme.Yellow);
=======
        pnlPending = createCard(
                "Pending",
                "₱8,250");
>>>>>>> parent of 720ed23 (meow)
        pnlPending.setBounds(450, 130, 350, 110);
        add(pnlPending);
        
       
<<<<<<< HEAD
        pnlOverdue = createCard("Overdue", "₱3,670", ColorsTheme.Red);
=======
        pnlOverdue = createCard(
                "Overdue",
                "₱3,670");
>>>>>>> parent of 720ed23 (meow)
        pnlOverdue.setBounds(830, 130, 350, 110);
        add(pnlOverdue);
        
        
<<<<<<< HEAD
        pnlInsurance = createCard("Insurance Claims", "₱18,700", ColorsTheme.Green);
        pnlInsurance.setBounds(1210, 130, 350, 110);
        add(pnlInsurance);
       
        
        //Table
        String[] columns = {"Patient Name", "Patient ID", "Total Amount", "Due Date", "Status"};
        Object[][] data = {
                {"Maria Leonora", "000021", "₱4,500.00", "May 15, 2026", "Unpaid"},
                {"Jose Felipe", "000054", "₱12,850.00", "May 15, 2026", "Partial"},
                {"Angela Cruz", "000078", "₱1,200.00", "May 14, 2026", "Paid"},
                {"Mark Anthony", "000103", "₱28,400.00", "May 14, 2026", "Unpaid"},
                {"Sophia Reyes", "000115", "₱3,500.00", "May 13, 2026", "Paid"},
                {"Daniel Garcia", "000126", "₱2,150.00", "May 12, 2026", "Paid"},
                {"Christine Mae", "000138", "₱8,900.00", "May 12, 2026", "Partial"},
                {"Nathaniel Ong", "000142", "₱15,600.00", "May 10, 2026", "Paid"},
                {"Francis Mendoza", "000189", "₱45,200.00", "May 09, 2026", "Unpaid"},
                {"Jasmine Aquino", "000193", "₱6,300.00", "May 08, 2026", "Paid"}
          };
        
        tblBill = new JTable (data, columns);
        tblBill.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblBill.setFont(FontsTheme.Info_Texts);
        tblBill.setRowHeight(50);
        tblBill.setDefaultEditor(Object.class, null);
        tblBill.getTableHeader().setReorderingAllowed(false);
        tblBill.getTableHeader().setBackground(ColorsTheme.Header); 
        tblBill.getTableHeader().setForeground(ColorsTheme.Text_White);
        
        scrollBill = new JScrollPane(tblBill);
        scrollBill.setBounds(0, 60, 1500, 560);
        pnlMiddle.add(scrollBill);
        
        lblTitle = new JLabel("Recent Billings");
        lblTitle.setBounds(30, 20, 300, 30);
        lblTitle.setFont(FontsTheme.Title_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTitle);
        
        
        
        btnAdd.addActionListener(this);
   
    }

    
    public JPanel createCard(String title, String value, Color topColor) {

        pnlCard = new JPanel();
        pnlCard.setLayout(null);
        pnlCard.setBackground(ColorsTheme.Main_Card);
        
        pnlTop = new JPanel();
        pnlTop.setBounds(0, 0, 350, 10);
        pnlTop.setBackground(topColor);
        pnlCard.add(pnlTop);
=======
        pnlInsurance = createCard(
                "Insurance Claims",
                "₱18,700");
        pnlInsurance.setBounds(1210, 130, 350, 110);
        add(pnlInsurance);
        
    }

    
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);
>>>>>>> parent of 720ed23 (meow)


        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
<<<<<<< HEAD
        pnlCard.add(lblTitle);
=======
        cardPanel.add(lblTitle);
>>>>>>> parent of 720ed23 (meow)


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
<<<<<<< HEAD
        pnlCard.add(lblValue);


        return pnlCard;
        
        
            } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd);
        AddBillingDialog bill = new AddBillingDialog();
        bill.setVisible(true);
    }
}
            
             
    
            
    
=======
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
    }
>>>>>>> parent of 720ed23 (meow)

