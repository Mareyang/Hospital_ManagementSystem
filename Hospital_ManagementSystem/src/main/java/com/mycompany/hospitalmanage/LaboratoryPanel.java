/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import com.mycompany.hospitalmanage.NewlabDialog;
/**
 *
 * @author Arabella
 */
public class LaboratoryPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlProcessing, pnlCompleted, pnlStats, cardPanel, TopPanel;
    private JLabel lblDetails, lblAppointment, lblTitle, lblValue, lblSubtitle, lblTableTitle;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable tblLabOrders;
    private JScrollPane scrollLabOrders;
   // private ImagePanel imgPatient;
    
    
    LaboratoryPanel() {
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
        
        btnAdd = new JButton("+  New Lab Order");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
        //Search Bar
        txtSearch = new JTextField("Search patient name, patient ID, or test type...");
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
        
        
        
        
        JLabel lblLabIcon = createIconLabel("/icons/lab.png");
        lblLabIcon.setBounds(30, 25, 72, 72);
        add(lblLabIcon);

        lblAppointment = new JLabel("Laboratory");
        lblAppointment.setBounds(120, 30, 500, 40);
        lblAppointment.setFont(FontsTheme.Bold_Texts);
        lblAppointment.setForeground(ColorsTheme.Text_Black);
        add(lblAppointment);

        lblDetails = new JLabel("Manage lab tests and results");
        lblDetails.setBounds(120, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlPending = createCard(
                "Pending",
                "10",
                "Awaiting sample",
                ColorsTheme.Update_Pending);
        pnlPending.setBounds(70, 130, 350, 110);
        add(pnlPending);
        
        
        pnlProcessing = createCard(
                "Processing",
                "12",
                "Tests in progress",
                ColorsTheme.Search_Button);
        pnlProcessing.setBounds(450, 130, 350, 110);
        add(pnlProcessing);
        
       
        pnlCompleted = createCard(
                "Completed",
                "45",
                "Results released",
                ColorsTheme.Add_Confirm);
        pnlCompleted.setBounds(830, 130, 350, 110);
        add(pnlCompleted);
        
        
        pnlStats = createCard(
                "STAT Orders",
                "5",
                "Urgent priority",
                ColorsTheme.Delete_Urgent);
        pnlStats.setBounds(1210, 130, 350, 110);
        add(pnlStats);
        
        createLabOrdersTable();
        
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
        lblValue.setForeground(ColorsTheme.Text_Black);
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
    
    private void createLabOrdersTable() {
        lblTableTitle = new JLabel("Recent Lab Orders");
        lblTableTitle.setBounds(25, 20, 400, 30);
        lblTableTitle.setFont(FontsTheme.Title_Texts);
        lblTableTitle.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblTableTitle);
        
        String[] columns = {"Patient Name", "Patient ID", "Test Type", "Priority", "Status", "Requested Date", "Actions"};
        Object[][] data = {
                {"Maria Leonora", "000021", "Complete Blood Count", "Routine", "Pending", "May 12, 2026", "View"},
                {"Jose Felipe", "000054", "Chest X-Ray", "STAT", "Processing", "May 12, 2026", "View"},
                {"Angela Cruz", "000078", "Urinalysis", "Routine", "Completed", "May 11, 2026", "Print"},
                {"Nathaniel Ong", "000142", "Blood Chemistry", "Routine", "Pending", "May 11, 2026", "View"},
                {"Miguel Santos", "000205", "ECG", "STAT", "Processing", "May 10, 2026", "View"},
                {"Ella Villanueva", "000219", "Pregnancy Test", "Routine", "Completed", "May 10, 2026", "Print"},
                {"Sophia Reyes", "000115", "Lipid Profile", "Routine", "Completed", "May 09, 2026", "Print"},
                {"Daniel Garcia", "000126", "Fasting Blood Sugar", "Routine", "Pending", "May 09, 2026", "View"}
        };
        
        tblLabOrders = new JTable(data, columns);
        tblLabOrders.setFont(FontsTheme.Info_Texts);
        tblLabOrders.setRowHeight(48);
        tblLabOrders.setDefaultEditor(Object.class, null);
        tblLabOrders.setShowGrid(false);
        tblLabOrders.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        JTableHeader tableHeader = tblLabOrders.getTableHeader();
        tableHeader.setFont(FontsTheme.Title_Texts);
        tableHeader.setBackground(ColorsTheme.Header);
        tableHeader.setForeground(ColorsTheme.Text_White);
        tableHeader.setReorderingAllowed(false);
        
        tblLabOrders.getColumnModel().getColumn(3).setCellRenderer(new StatusColor());
        tblLabOrders.getColumnModel().getColumn(4).setCellRenderer(new StatusColor());

        scrollLabOrders = new JScrollPane(tblLabOrders);
        scrollLabOrders.setBounds(25, 65, 1450, 405);
        pnlMiddle.add(scrollLabOrders);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (btnAdd == e.getSource()) {
            
            NewlabDialog dialog = new NewlabDialog();
            dialog.setVisible(true);
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
            } else if (status.equals("Processing")) {
                cell.setForeground(ColorsTheme.Search_Button);
            } else if (status.equals("Completed")) {
                cell.setForeground(ColorsTheme.Add_Confirm);
            }
            
            return cell;
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
        ImageIcon icon = resource == null
                ? new ImageIcon("src/main/resources" + path)
                : new ImageIcon(resource);
        Image scaledImage = icon.getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }
    
    

    }

