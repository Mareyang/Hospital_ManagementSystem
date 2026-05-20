/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.TablePanel;
import constants.PanelCard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.hospitalmanage.*;
import dialogs.AddBillingDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.*;

/**
 *
 * 
 */
public class BillingPanel extends JPanel implements ActionListener  {
    
    private JPanel pnlMiddle, pnlSearch, pnlRevenue, pnlOverdue, pnlPending, pnlInsurance;
    private JLabel lblDetails, lblBilling;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblBill;

    
    
    public BillingPanel() {
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
        
        //Button for adding new billing
        btnAdd = new JButton("+  New Invoice");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
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
        btnSearch.setBackground(ColorsTheme.Search_Button);
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
        
        
        //Title and subtitle label for billing section
        lblBilling = new JLabel("Billing");
        lblBilling.setBounds(30, 30, 500, 40);
        lblBilling.setFont(FontsTheme.Bold_Texts);
        lblBilling.setForeground(ColorsTheme.Text_Black);
        add(lblBilling);

        lblDetails = new JLabel("Manage invoices and payments.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Summary Panel Cards
        pnlRevenue = new PanelCard("Today's Revenue", "₱12,500", ColorsTheme.Blue);
        pnlRevenue.setBounds(70, 130, 350, 110);
        add(pnlRevenue);
        
        pnlPending = new PanelCard("Pending", "₱8,250", ColorsTheme.Yellow);
        pnlPending.setBounds(450, 130, 350, 110);
        add(pnlPending);
       
        pnlOverdue = new PanelCard("Overdue", "₱3,670", ColorsTheme.Red);
        pnlOverdue.setBounds(830, 130, 350, 110);
        add(pnlOverdue);
        
        pnlInsurance = new PanelCard("Insurance Claims", "₱18,700", ColorsTheme.Green);
        pnlInsurance.setBounds(1210, 130, 350, 110);
        add(pnlInsurance);
       
        
        //Table column names
        String[] columns = {"Patient Name", "Patient ID", "Total Amount", "Due Date", "Status", "Actions"};
        
        //Sample records
        Object[][] data = {
                {"Maria Leonora", "000021", "₱4,500.00", "May 15, 2026", "Unpaid", " "},
                {"Jose Felipe", "000054", "₱12,850.00", "May 15, 2026", "Partial", " "},
                {"Angela Cruz", "000078", "₱1,200.00", "May 14, 2026", "Paid", " "},
                {"Mark Anthony", "000103", "₱28,400.00", "May 14, 2026", "Unpaid", " "},
                {"Sophia Reyes", "000115", "₱3,500.00", "May 13, 2026", "Paid", " "},
                {"Daniel Garcia", "000126", "₱2,150.00", "May 12, 2026", "Paid", " "},
                {"Christine Mae", "000138", "₱8,900.00", "May 12, 2026", "Partial", " "},
                {"Nathaniel Ong", "000142", "₱15,600.00", "May 10, 2026", "Paid", " "},
                {"Francis Mendoza", "000189", "₱45,200.00", "May 09, 2026", "Unpaid", " "},
                {"Jasmine Aquino", "000193", "₱6,300.00", "May 08, 2026", "Paid", " "},
                {"Sophia Reyes", "000115", "₱3,500.00", "May 13, 2026", "Paid", " "},
                {"Daniel Garcia", "000126", "₱2,150.00", "May 12, 2026", "Paid", " "},
                {"Christine Mae", "000138", "₱8,900.00", "May 12, 2026", "Partial", " "},
                {"Nathaniel Ong", "000142", "₱15,600.00", "May 10, 2026", "Paid", " "},
                {"Francis Mendoza", "000189", "₱45,200.00", "May 09, 2026", "Unpaid", " "},
                {"Jasmine Aquino", "000193", "₱6,300.00", "May 08, 2026", "Paid", " "}
                
          };
        

        //Table Panel for records
        tblBill = new TablePanel("Recent Billings", columns, data, 440);
        tblBill.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblBill);
        
        
            
            
        //ActionListener    
        btnAdd.addActionListener(this);
   
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens billing form dialog
        if(e.getSource() == btnAdd);
        AddBillingDialog bill = new AddBillingDialog();
        bill.setVisible(true);
    }
}
            
             
    
            
    

