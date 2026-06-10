/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class DoctorDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlSurgery, pnlPending, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    //Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview, pnlImage, pnlMetrics;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    
    
    
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
        
        
        
//        //Department Distribution Panel
//        pnlDistribution = new JPanel();
//        pnlDistribution.setLayout(null);
//        pnlDistribution.setBackground(ColorsTheme.Middle_Panel);
//        pnlDistribution.setBounds(950, 0, 550, 500);
//        pnlMiddle.add(pnlDistribution);
//
//        lblDistributionTitle = new JLabel("Department Distribution");
//        lblDistributionTitle.setFont(FontsTheme.Title_Texts);
//        lblDistributionTitle.setForeground(ColorsTheme.Text_Black);
//        lblDistributionTitle.setBounds(25, 20, 350, 35);
//        pnlDistribution.add(lblDistributionTitle);
//        
//        
//        //Progress Bars
//        barCardiology = createCustomProgressBar(25, 80, 450, 28, 25, ColorsTheme.Cardiology_Color);
//        barOrthopedics = createCustomProgressBar(25, 140, 450, 28, 22, ColorsTheme.Orthophedics_Color);
//        barEmergency = createCustomProgressBar(25, 200, 450, 28, 20, ColorsTheme.Emergency_Color);
//        barNeurology = createCustomProgressBar(25, 260, 450, 28, 18, ColorsTheme.Neurology_Color);
//        barPediatrics = createCustomProgressBar(25, 320, 450, 28, 15, ColorsTheme.Pediatrics_Color);
//
//        pnlDistribution.add(barCardiology);
//        pnlDistribution.add(barOrthopedics);
//        pnlDistribution.add(barEmergency);
//        pnlDistribution.add(barNeurology);
//        pnlDistribution.add(barPediatrics);
//        
        
//        //Legend Items
//        JPanel leg1 = createLegendItem(30, 390, "Cardiology", ColorsTheme.Cardiology_Color);
//        JPanel leg2 = createLegendItem(250, 390, "Orthopedics", ColorsTheme.Orthophedics_Color);
//        JPanel leg3 = createLegendItem(30, 425, "Emergency", ColorsTheme.Emergency_Color);
//        JPanel leg4 = createLegendItem(250, 425, "Neurology", ColorsTheme.Neurology_Color);
//        JPanel leg5 = createLegendItem(30, 460, "Pediatrics", ColorsTheme.Pediatrics_Color);
//
//        pnlDistribution.add(leg1);
//        pnlDistribution.add(leg2);
//        pnlDistribution.add(leg3);
//        pnlDistribution.add(leg4);
//        pnlDistribution.add(leg5);
        
        
//        //Background Image Panel
//        pnlImage = new JPanel();
//        pnlImage.setLayout(null);
//        pnlImage.setBounds(0, 0, 950, 500);
//        pnlImage.setBackground(ColorsTheme.Blue);
//        pnlMiddle.add(pnlImage);
//        
//        JLabel lblIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/hospi.png")));
//        lblIcon.setBounds(0, 0, 950, 500);
//        pnlImage.add(lblIcon);
//    }

//     Creates a customized progress bar for department distribution.
//    private JProgressBar createCustomProgressBar(int x, int y, int width, int height, int value, Color filledColor) {
//        
//        JProgressBar bar = new JProgressBar(0, 100);
//        bar.setBounds(x, y, width, height);
//        bar.setValue(value);
//        bar.setStringPainted(true);
//        bar.setFont(FontsTheme.Progress_Bar_Font);
//        bar.setForeground(filledColor);
//        bar.setBackground(ColorsTheme.Main_Card);
//        bar.setBorderPainted(false);
//
//        return bar;
//    }
//
//    //Creates a legend item (color box + label) for the distribution chart.
//    private JPanel createLegendItem(int x, int y, String text, Color color) {
//        JPanel item = new JPanel();
//        item.setLayout(null);
//        item.setOpaque(false);
//        item.setBounds(x, y, 300, 28);
//
//        //Color indicator square
//        JPanel cube = new JPanel();
//        cube.setBackground(color);
//        cube.setBounds(0, 6, 16, 16);
//        item.add(cube);
//
//        JLabel label = new JLabel(text);
//        label.setFont(FontsTheme.Info_Texts);
//        label.setBounds(20, 2, 260, 24);
//        item.add(label);
//
//        return item;
        JPanel pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card); 
        pnlQuickActions.setBounds(960, 0, 520, 480); 
        pnlMiddle.add(pnlQuickActions);

        JLabel lblQuickTitle = new JLabel("Quick Actions");
        lblQuickTitle.setFont(FontsTheme.Title_Texts);
        lblQuickTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickTitle.setBounds(32, 25, 350, 50);
        pnlQuickActions.add(lblQuickTitle);
        
        // Line 1
        JButton btnPatient = new JButton("View Patient Record");
        btnPatient.setBounds(40, 110, 200, 70);
        btnPatient.setBackground(ColorsTheme.Blue); 
        btnPatient.setForeground(Color.WHITE);
        btnPatient.setFont(FontsTheme.Info_Texts);
        btnPatient.setFocusPainted(false);
        pnlQuickActions.add(btnPatient);
        
        JButton btnDiagnosis = new JButton("Create Diagnosis");
        btnDiagnosis.setBounds(280, 110, 200, 70);
        btnDiagnosis.setBackground(ColorsTheme.Green);
        btnDiagnosis.setForeground(Color.WHITE);
        btnDiagnosis.setFont(FontsTheme.Info_Texts);
        btnDiagnosis.setFocusPainted(false);
        pnlQuickActions.add(btnDiagnosis);
        
        // Line 2
        JButton btnMedication = new JButton("Prescribe Medication");
        btnMedication.setBounds(40, 230, 200, 70);
        btnMedication.setBackground(ColorsTheme.Orange);
        btnMedication.setForeground(Color.WHITE);
        btnMedication.setFont(FontsTheme.Info_Texts);
        btnMedication.setFocusPainted(false);
        pnlQuickActions.add(btnMedication);
        
        JButton btnLaboratory = new JButton("Request Laboratory");
        btnLaboratory.setBounds(280, 230, 200, 70);
        btnLaboratory.setBackground(ColorsTheme.Yellow);
        btnLaboratory.setForeground(Color.BLACK);
        btnLaboratory.setFont(FontsTheme.Info_Texts);
        btnLaboratory.setFocusPainted(false);
        pnlQuickActions.add(btnLaboratory);
        
        // Line 3
        JButton btnUpdate = new JButton("Update Treatment Plan");
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
        
        
        JTable tblLogs = new JTable(logData, logColumns);
        tblLogs.setRowHeight(50);
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
        
        JScrollPane logScrollPane = new JScrollPane(tblLogs);
        logScrollPane.setBounds(20, 70, 900, 390);
        logScrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        logScrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY, 1)); 
        pnlOverview.add(logScrollPane);
        

        











    }
}