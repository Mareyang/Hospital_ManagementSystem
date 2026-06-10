/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class DoctorDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlSurgery, pnlPending, pnlMiddle, pnlOverview,
            pnlQuickActions;
    private JLabel lblGreet, lblDescrip, lblOverviewTitle, lblQuickTitle;
    private JButton btnPatient, btnDiagnosis,btnMedication, btnLaboratory, btnUpdate;
    private JTable tblLogs;
    private JScrollPane logScrollPane;
    
    
    
    public DoctorDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Middle Panel Container
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel);
        add(pnlMiddle);

        //Greeting section
        lblGreet = new JLabel("Welcome to CareLink, Doctor!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        
        //Summary Panel Cards
        pnlAppointments = new PanelCard2(     
                "Today's Appointments",
                "156",
                "27 remaining",
                ColorsTheme.Yellow);
        pnlAppointments.setBounds(70, 150, 350, 140);
        add(pnlAppointments);
        
        pnlPending = new PanelCard2(
                "Pending Diagnoses",
                "32",
                "Pending ",
                ColorsTheme.Orange);
        pnlPending.setBounds(450, 150, 350, 140);
        add(pnlPending);
        
        pnlSurgery = new PanelCard2(
                "Upcoming Surgeries",
                "5",
                "For Today",
                ColorsTheme.Blue);
        pnlSurgery.setBounds(830, 150, 350, 140);
        add(pnlSurgery);
        
        pnlPatients = new PanelCard2(
                "Total Patients",
                "2,847",
                "Active Records",
                ColorsTheme.Green);
        pnlPatients.setBounds(1210, 150, 350, 140);
        add(pnlPatients);
        
        
        
        pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card); 
        pnlQuickActions.setBounds(960, 0, 520, 480); 
        pnlMiddle.add(pnlQuickActions);

        lblQuickTitle = new JLabel("Quick Actions");
        lblQuickTitle.setFont(FontsTheme.Title_Texts);
        lblQuickTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickTitle.setBounds(32, 25, 350, 50);
        pnlQuickActions.add(lblQuickTitle);
        
        // Line 1
        btnPatient = new JButton("View Patient Record");
        btnPatient.setBounds(40, 110, 200, 70);
        btnPatient.setBackground(ColorsTheme.Blue); 
        btnPatient.setForeground(Color.WHITE);
        btnPatient.setFont(FontsTheme.Info_Texts);
        btnPatient.setFocusPainted(false);
        pnlQuickActions.add(btnPatient);
        
        btnDiagnosis = new JButton("Create Diagnosis");
        btnDiagnosis.setBounds(280, 110, 200, 70);
        btnDiagnosis.setBackground(ColorsTheme.Green);
        btnDiagnosis.setForeground(Color.WHITE);
        btnDiagnosis.setFont(FontsTheme.Info_Texts);
        btnDiagnosis.setFocusPainted(false);
        pnlQuickActions.add(btnDiagnosis);
        
        // Line 2
        btnMedication = new JButton("Prescribe Medication");
        btnMedication.setBounds(40, 230, 200, 70);
        btnMedication.setBackground(ColorsTheme.Orange);
        btnMedication.setForeground(Color.WHITE);
        btnMedication.setFont(FontsTheme.Info_Texts);
        btnMedication.setFocusPainted(false);
        pnlQuickActions.add(btnMedication);
        
        btnLaboratory = new JButton("Request Laboratory");
        btnLaboratory.setBounds(280, 230, 200, 70);
        btnLaboratory.setBackground(ColorsTheme.Yellow);
        btnLaboratory.setForeground(Color.BLACK);
        btnLaboratory.setFont(FontsTheme.Info_Texts);
        btnLaboratory.setFocusPainted(false);
        pnlQuickActions.add(btnLaboratory);
        
        // Line 3
        btnUpdate = new JButton("Update Treatment Plan");
        btnUpdate.setBounds(160, 350, 200, 70);
        btnUpdate.setBackground(ColorsTheme.Top_Line);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(FontsTheme.Info_Texts);
        btnUpdate.setFocusPainted(false);
        pnlQuickActions.add(btnUpdate);

        pnlOverview = new JPanel();
        pnlOverview.setLayout(null);
        pnlOverview.setBounds(0, 0, 920, 480); 
        pnlOverview.setBackground(ColorsTheme.Main_Card); 
        pnlMiddle.add(pnlOverview);

        lblOverviewTitle = new JLabel("Today's Schedule");
        lblOverviewTitle.setFont(FontsTheme.Title_Texts);
        lblOverviewTitle.setForeground(ColorsTheme.Text_Black);
        lblOverviewTitle.setBounds(30, 20, 500, 35);
        pnlOverview.add(lblOverviewTitle);

        String[] logColumns = {"Time", "Patient", "Type", "Status"};
        Object[][] logData = {
            {"10:00 AM", "Kurt Paolo", "Consultation", "Completed"},
            {"12:00 PM", "Karlo Alatiit", "Follow Up", "Scheduled"},
            {"3:00 PM", "AJ Aviles", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"},
            {"5:00 PM", "Mark Igloso", "Consultation", "Scheduled"}
           
        };
        
        
        tblLogs = new JTable(logData, logColumns);
        tblLogs.setRowHeight(SystemSettings.tableRowHeight);
        tblLogs.setFont(FontsTheme.Info_Texts);
        tblLogs.setDefaultEditor(Object.class, null);
        tblLogs.setBackground(ColorsTheme.Main_Card);
        tblLogs.setForeground(ColorsTheme.Text_Black);
        tblLogs.setGridColor(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY);
        tblLogs.setSelectionBackground(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
        tblLogs.setSelectionForeground(ColorsTheme.Text_Black);
        tblLogs.getTableHeader().setReorderingAllowed(false);
        tblLogs.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblLogs.getTableHeader().setBackground(ColorsTheme.isDarkMode ? Color.decode("#2E2E38") : ColorsTheme.Header);
        tblLogs.getTableHeader().setForeground(ColorsTheme.Text_White);

        // Center the text so it doesn't hug the left borders, and guarantee cell colors apply correctly
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblLogs.getColumnCount(); i++) {
            tblLogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        logScrollPane = new JScrollPane(tblLogs);
        logScrollPane.setBounds(20, 70, 900, 390);
        logScrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        logScrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY, 1)); 
        pnlOverview.add(logScrollPane);
        

        











    }
}