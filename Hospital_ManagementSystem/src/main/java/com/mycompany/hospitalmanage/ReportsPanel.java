/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
<<<<<<< HEAD:Hospital_ManagementSystem/src/main/java/panels/ReportsPanel.java
import dialogs.NewreportDialog;
import dialogs.NewreportDialog;
=======
import com.mycompany.hospitalmanage.NewreportDialog;
>>>>>>> parent of 720ed23 (meow):Hospital_ManagementSystem/src/main/java/com/mycompany/hospitalmanage/ReportsPanel.java

/**
 *
 * @author Arabella
 */
public class ReportsPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlPatient, pnlBilling, pnlLab, pnlPharmacy, cardPanel, TopPanel;
    private JLabel lblDetails, lblReports, lblTitle, lblValue, lblSubtitle, lblTableTitle;
    private JButton btnAdd;
    private JTable tblReports;
    private JScrollPane scrollReports;
    
    
    public ReportsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 130, 1500, 750);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        JLabel lblReportsIcon = createIconLabel("/icons/reports.png");
        lblReportsIcon.setBounds(30, 25, 72, 72);
        add(lblReportsIcon);

        lblReports = new JLabel("Reports");
        lblReports.setBounds(120, 30, 500, 40);
        lblReports.setFont(FontsTheme.Bold_Texts);
        lblReports.setForeground(ColorsTheme.Text_Black);
        add(lblReports);

        lblDetails = new JLabel("Generate and view hospital reports");
        lblDetails.setBounds(120, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+  Generate Report");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
        pnlPatient = createCard(
                "Patient Reports",
                "24",
                "Records summary",
                ColorsTheme.Top_Line);
        pnlPatient.setBounds(25, 25, 330, 110);
        pnlMiddle.add(pnlPatient);
        
        pnlBilling = createCard(
                "Billing Reports",
                "18",
                "Revenue and invoices",
                ColorsTheme.Add_Confirm);
        pnlBilling.setBounds(385, 25, 330, 110);
        pnlMiddle.add(pnlBilling);
        
        pnlLab = createCard(
                "Lab Reports",
                "32",
                "Test results",
                ColorsTheme.Search_Button);
        pnlLab.setBounds(745, 25, 330, 110);
        pnlMiddle.add(pnlLab);
        
        pnlPharmacy = createCard(
                "Pharmacy Reports",
                "15",
                "Inventory movement",
                ColorsTheme.Update_Pending);
        pnlPharmacy.setBounds(1105, 25, 330, 110);
        pnlMiddle.add(pnlPharmacy);
        
        createReportsTable();
        
    }
    
    public JPanel createCard(String title, String value, String subtitle, Color accentColor) {
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 330, 10);
        TopPanel.setBackground(accentColor);
        cardPanel.add(TopPanel);
        
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblTitle);
        
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);
        
        lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setBounds(20, 88, 250, 20);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        lblSubtitle.setFont(FontsTheme.Info_Texts);
        cardPanel.add(lblSubtitle);
        
        return cardPanel;
    }
    
    private void createReportsTable() {
        lblTableTitle = new JLabel("Recent Reports");
        lblTableTitle.setBounds(25, 170, 400, 30);
        lblTableTitle.setFont(FontsTheme.Title_Texts);
        lblTableTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTableTitle);
        
        String[] columns = {"Report Name", "Type", "Generated By", "Date Generated", "Status", "Actions"};
        Object[][] data = {
                {"Daily Patient Census", "Patient", "Admin", "May 12, 2026", "Ready", "View"},
                {"Monthly Revenue Summary", "Billing", "Cashier", "May 11, 2026", "Ready", "View"},
                {"Laboratory Test Summary", "Laboratory", "Lab Staff", "May 11, 2026", "Processing", "View"},
                {"Low Stock Inventory", "Pharmacy", "Pharmacist", "May 10, 2026", "Ready", "View"},
                {"Bed Occupancy Report", "Bed Management", "Nurse Station", "May 10, 2026", "Ready", "View"},
                {"Emergency Case Summary", "Emergency", "ER Staff", "May 09, 2026", "Failed", "Retry"},
                {"Appointment Summary", "Appointments", "Reception", "May 09, 2026", "Ready", "View"}
        };
        
        tblReports = new JTable(data, columns);
        tblReports.setFont(FontsTheme.Info_Texts);
        tblReports.setRowHeight(48);
        tblReports.setDefaultEditor(Object.class, null);
        tblReports.setShowGrid(false);
        tblReports.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        JTableHeader tableHeader = tblReports.getTableHeader();
        tableHeader.setFont(FontsTheme.Title_Texts);
        tableHeader.setBackground(ColorsTheme.Header);
        tableHeader.setForeground(ColorsTheme.Text_White);
        tableHeader.setReorderingAllowed(false);
        
        tblReports.getColumnModel().getColumn(4).setCellRenderer(createReportStatusRenderer());
        
        scrollReports = new JScrollPane(tblReports);
        scrollReports.setBounds(25, 215, 1450, 500);
        pnlMiddle.add(scrollReports);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (btnAdd == e.getSource()) {
            NewreportDialog rep = new NewreportDialog();
            rep.setVisible(true);
        }
        
    }
    
    private DefaultTableCellRenderer createReportStatusRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value.toString();
                
                changeReportStatusColor(cell, status);
                
                return cell;
            }
        };
        
        return renderer;
    }
    
    private void changeReportStatusColor(java.awt.Component cell, String status) {
        cell.setForeground(ColorsTheme.Text_Black);
        
        if (status.equals("Ready")) {
            cell.setForeground(ColorsTheme.Add_Confirm);
        } else if (status.equals("Processing")) {
            cell.setForeground(ColorsTheme.Search_Button);
        } else if (status.equals("Failed")) {
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
    

