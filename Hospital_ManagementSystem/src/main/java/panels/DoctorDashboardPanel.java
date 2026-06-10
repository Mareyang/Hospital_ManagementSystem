/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import controls.DoctorDashboard;
import dialogs.AddMedicalRecordDialog;
import dialogs.AddPrescriptionDialog;
import dialogs.ViewPatientDialog;
import java.awt.Color;
//import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class DoctorDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlSurgery, pnlPending, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    //Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview, pnlImage, pnlMetrics;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    private JTable tblLogs;
    private DefaultTableModel tableModel;
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";
    
    
    public DoctorDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        String totalAppointments = String.valueOf(getTableRowCount("appointments", ""));
        
       
        int remainingCount = getTableRowCount("appointments", "WHERE LOWER(status) = 'scheduled'");
        String remainingText = remainingCount + " remaining";
        
        String pendingDiagnoses = String.valueOf(getTableRowCount("diagnoses", "WHERE LOWER(status) = 'pending'"));
        String upcomingSurgeries = String.valueOf(getTableRowCount("surgeries", "WHERE LOWER(status) = 'scheduled'"));
        String totalPatients = String.valueOf(getTableRowCount("patients", ""));
        
        
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
                totalAppointments,
                remainingText,
                ColorsTheme.Yellow);
        pnlAppointments.setBounds(70, 150, 350, 140);
        add(pnlAppointments);
        
        pnlPending = new PanelCard2(
                "Pending Diagnoses",
                pendingDiagnoses,
                "Pending ",
                ColorsTheme.Orange);
        pnlPending.setBounds(450, 150, 350, 140);
        add(pnlPending);
        
        pnlSurgery = new PanelCard2(
                "Upcoming Surgeries",
                upcomingSurgeries,
                "For Today",
                ColorsTheme.Blue);
        pnlSurgery.setBounds(830, 150, 350, 140);
        add(pnlSurgery);
        
        pnlPatients = new PanelCard2(
                "Total Patients",
                totalPatients,
                "Active Records",
                ColorsTheme.Green);
        pnlPatients.setBounds(1210, 150, 350, 140);
        add(pnlPatients);
        
        

        JPanel pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card); 
        pnlQuickActions.setBounds(1140, 0, 350, 600); 
        pnlMiddle.add(pnlQuickActions);

        JLabel lblQuickTitle = new JLabel("Quick Actions");
        lblQuickTitle.setFont(FontsTheme.Title_Texts);
        lblQuickTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickTitle.setBounds(32, 8, 350, 50);
        pnlQuickActions.add(lblQuickTitle);
        
         pnlQuickActions.add(createQuickActionButton("View Patient Record", () -> {
   
            Window topWindow = SwingUtilities.getWindowAncestor(this);
            if (topWindow instanceof DoctorDashboard) {
                DoctorDashboard dashboard = (DoctorDashboard) topWindow;

                dashboard.switchView("patients", dashboard.btnPatients);
            }
        }, 45, 80, ColorsTheme.Blue, ColorsTheme.Text_White));

        pnlQuickActions.add(createQuickActionButton("Appointments", () -> {

            Window topWindow = SwingUtilities.getWindowAncestor(this);
            if (topWindow instanceof DoctorDashboard) {
                DoctorDashboard dashboard = (DoctorDashboard) topWindow;

                dashboard.switchView("appointments", dashboard.btnAppointments);
            }
        }, 45, 155, ColorsTheme.Orange, ColorsTheme.Text_White));

        pnlQuickActions.add(createQuickActionButton("Medical Records", () -> new AddMedicalRecordDialog().setVisible(true), 45, 230, ColorsTheme.Green, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Prescribe Medication", () -> new AddPrescriptionDialog().setVisible(true), 45, 305, ColorsTheme.Top_Line, ColorsTheme.Text_White));
//        // Line 1
//        JButton btnPatient = new JButton("View Patient Record");
//        btnPatient.setBounds(75, 70, 200, 70);
//        btnPatient.setBackground(ColorsTheme.Blue); 
//        btnPatient.setFont(FontsTheme.Info_Texts);
//        btnPatient.setForeground(Color.WHITE);
//        btnPatient.setFocusPainted(false);
//        pnlQuickActions.add(btnPatient);
//        
//        JButton btnDiagnosis = new JButton("Create Diagnosis");
//        btnDiagnosis.setBounds(75, 150, 200, 70);
//        btnDiagnosis.setBackground(ColorsTheme.Green);
//        btnDiagnosis.setForeground(Color.WHITE);
//        btnDiagnosis.setFont(FontsTheme.Info_Texts);
//        btnDiagnosis.setFocusPainted(false);
//        pnlQuickActions.add(btnDiagnosis);
//        
//       
//        JButton btnMedication = new JButton("Prescribe Medication");
//        btnMedication.setBounds(75, 230, 200, 70);
//        btnMedication.setBackground(ColorsTheme.Orange);
//        btnMedication.setForeground(Color.WHITE);
//        btnMedication.setFont(FontsTheme.Info_Texts);
//        btnMedication.setFocusPainted(false);
//        pnlQuickActions.add(btnMedication);
//        
//        JButton btnLaboratory = new JButton("Request Laboratory");
//        btnLaboratory.setBounds(75, 310, 200, 70);
//        btnLaboratory.setBackground(ColorsTheme.Yellow);
//        btnLaboratory.setForeground(Color.BLACK);
//        btnLaboratory.setFont(FontsTheme.Info_Texts);
//        btnLaboratory.setFocusPainted(false);
//        pnlQuickActions.add(btnLaboratory);
//        
//       
//        JButton btnUpdate = new JButton("Update Treatment Plan");
//        btnUpdate.setBounds(75, 390, 200, 70);
//        btnUpdate.setBackground(ColorsTheme.Top_Line);
//        btnUpdate.setForeground(Color.WHITE);
//        btnUpdate.setFont(FontsTheme.Info_Texts);
//        btnUpdate.setFocusPainted(false);
//        pnlQuickActions.add(btnUpdate);

        pnlOverview = new JPanel();
        pnlOverview.setLayout(null);
        pnlOverview.setBounds(0, 0, 1120, 550); 
        pnlOverview.setBackground(ColorsTheme.Main_Card); 
        pnlMiddle.add(pnlOverview);

        lblOverviewTitle = new JLabel("Today's Schedule");
        lblOverviewTitle.setFont(FontsTheme.Title_Texts);
        lblOverviewTitle.setForeground(ColorsTheme.Text_Black);
        lblOverviewTitle.setBounds(30, 20, 500, 35);
        pnlOverview.add(lblOverviewTitle);

        String[] logColumns = {"Time", "Patient", "Type", "Status"};
        tableModel = new DefaultTableModel(logColumns, 0);
        
        
        tblLogs = new JTable(tableModel);
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

        // Center the text so it doesn't hug the left borders
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
        logScrollPane.setBounds(20, 70, 1080, 400);
        logScrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        logScrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY, 1)); 
        pnlOverview.add(logScrollPane);
        
        loadTodaySchedule();
            

        }
    
    

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private int getTableRowCount(String tableName, String queryFilters) {
        int rowsCount = 0;
        String sql = "SELECT COUNT(*) FROM `" + tableName + "` " + queryFilters;
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                rowsCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsCount;
    }
    private JButton createQuickActionButton(String text, Runnable action, int x, int y, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 260, 55);
        button.setFont(FontsTheme.Plain_Texts);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> action.run());
        return button;
    }

    private void loadTodaySchedule() {
       tableModel.setRowCount(0);
    
    
    String sql = "SELECT appointment_time, patient_name, visit_type, status FROM appointments ORDER BY appt_id ASC";
    
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            String time = rs.getString("appointment_time");
            String patient = rs.getString("patient_name");
            String type = rs.getString("visit_type"); // Matches your column name cleanly
            String status = rs.getString("status");
            
            tableModel.addRow(new Object[]{time, patient, type, status});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        tableModel.addRow(new Object[]{"N/A", "Error connecting to DB", "N/A", "N/A"});
    }
    
    
    
    
    
    
}
}