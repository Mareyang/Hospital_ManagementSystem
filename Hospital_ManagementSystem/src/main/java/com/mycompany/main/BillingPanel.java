/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.hospitalmanage.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Arabella
 */
public class BillingPanel extends JPanel implements ActionListener  {
    
    private JPanel pnlMiddle, pnlSearch, pnlRevenue, pnlOverdue, pnlPending, pnlInsurance, cardPanel, TopPanel;
    private JLabel lblDetails, lblBilling, lblTitle, lblValue,lblTableTitle;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table,tblBill;
    private JScrollPane scrollPane;
   // private ImagePanel imgPatient;
    
    
    BillingPanel() {
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
        btnAdd.addActionListener(this);
        add(btnAdd);
        
       
        
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
        
        
        
        
        lblBilling = new JLabel("Billing");
        lblBilling.setBounds(30, 30, 500, 40);
        lblBilling.setFont(FontsTheme.Bold_Texts);
        lblBilling.setForeground(ColorsTheme.Text_Black);
        add(lblBilling);

        lblDetails = new JLabel("Manage invoices and payments");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlRevenue = createCard(
                "Today's Revenue",
                "₱12,500");
           TopPanel.setBackground(Color.green);
        pnlRevenue.setBounds(70, 130, 350, 110);
        add(pnlRevenue);
        
        
        pnlPending = createCard(
                "Pending",
                "₱8,250");
        TopPanel.setBackground(Color.orange);
        pnlPending.setBounds(450, 130, 350, 110);
        add(pnlPending);
        
       
        pnlOverdue = createCard(
                "Overdue",
                "₱3,670");
        TopPanel.setBackground(Color.red);
        pnlOverdue.setBounds(830, 130, 350, 110);
        add(pnlOverdue);
        
        
        pnlInsurance = createCard(
                "Insurance Claims",
                "₱18,700");
        TopPanel.setBackground(Color.blue);
        pnlInsurance.setBounds(1210, 130, 350, 110);
        add(pnlInsurance);
       
        billTable();
   
    }

    
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
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
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            } 
    
    
    private void billTable() {

    lblTableTitle = new JLabel("Recent Emergency Services");
    lblTableTitle.setBounds(25, 20, 400, 30);
    lblTableTitle.setFont(FontsTheme.Title_Texts);
    lblTableTitle.setForeground(ColorsTheme.Text_Black);
    pnlMiddle.add(lblTableTitle);

    String[] columns = {
        "Patient Id",
        "Name",
        "Date",
        "Amount",
        "Status",
        "Payment Method"
    };

    Object[][] data = {
        {"001", "Kurt Redondo", "12/05/26", "₱53,222", "Paid", "Cash"},
        {"002", "Karlo Alatiit", "12/05/26", "₱103,222", "Pending", "GCash"},
        {"003", "Karlo Alatiit", "12/05/26", "₱103,222", "Overdue", "GCash"}
    };

    table = new JTable(data, columns);

    table.getTableHeader().setFont(FontsTheme.Bold_Texts);
    table.setFont(FontsTheme.Plain_Texts);
    table.setRowHeight(50);
    table.setDefaultEditor(Object.class, null);

    table.setShowGrid(false);
    table.setIntercellSpacing(new java.awt.Dimension(0, 0));

    JTableHeader header = table.getTableHeader();
    header.setBackground(ColorsTheme.Header);
    header.setForeground(ColorsTheme.Text_White);
    header.setReorderingAllowed(false);

    table.getColumnModel().getColumn(4)
            .setCellRenderer(new StatusColor());

    scrollPane = new JScrollPane(table);
    scrollPane.setBounds(0, 60, 1500, 420);

    pnlMiddle.add(scrollPane);
}  
           @Override
            public void actionPerformed(ActionEvent e) { 
                if (e.getSource()== btnAdd) {
                    BillDialog bill = new BillDialog ();
                    bill.setVisible(true);
                }
            }
            
            
             private static class StatusColor extends DefaultTableCellRenderer {
            
              @Override
            
              public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            
            java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = value.toString();
            
            cell.setForeground(ColorsTheme.Text_Black);
            
            if (status.equals("STAT")) {
                cell.setForeground(ColorsTheme.Delete_Urgent);
            } else if (status.equals("Pending")) {
                cell.setForeground(new Color(180, 120, 0));
            } else if (status.equals("Overdue")) {
                cell.setForeground(Color.red);
            } else if (status.equals("Paid")) {
                cell.setForeground(ColorsTheme.Add_Confirm);
            }
            
            return cell;
        }
    }
            
    }

