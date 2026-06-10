/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import dialogs.AddMedicalRecordDialog;
import dialogs.AddPatientDialog;
import dialogs.NewAppointmentDialog;
import dialogs.NewPharmacyDialog;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class NurseDashboardPanel extends JPanel {
    // Unified database configuration pointing to your active MySQL server port
    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblPatientMonitoringTitle, lblQuickActionsTitle;
    
    private JPanel pnlPatientMonitoring, pnlQuickActions;
    private JTable tblPatientMonitoring;
    private JScrollPane spPatientMonitoring;
    private DefaultTableModel patientTableModel;
    
    
    
    
    public NurseDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Middle Panel Container
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel);
        add(pnlMiddle);

        //Greeting section
        lblGreet = new JLabel("Welcome to CareLink, Nurse!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        refreshSummaryCards();
        
        
        
        // Patient Monitoring List table
        pnlPatientMonitoring = new JPanel();
        pnlPatientMonitoring.setLayout(null);
        pnlPatientMonitoring.setBounds(0, 0, 1070, 500);
        pnlPatientMonitoring.setBackground(ColorsTheme.Main_Card);
        pnlMiddle.add(pnlPatientMonitoring);

        lblPatientMonitoringTitle = new JLabel("Patient Monitoring List");
        lblPatientMonitoringTitle.setFont(FontsTheme.Title_Texts);
        lblPatientMonitoringTitle.setForeground(ColorsTheme.Text_Black);
        lblPatientMonitoringTitle.setBounds(15, 10, 400, 30);
        pnlPatientMonitoring.add(lblPatientMonitoringTitle);

        String[] patientColumns = {"Patient Name", "Room / Bed", "Condition", "Last Updated"};
        patientTableModel = new DefaultTableModel(patientColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPatientMonitoring = createSimpleTable(patientTableModel);
        tblPatientMonitoring.getColumnModel().getColumn(0).setPreferredWidth(320);
        tblPatientMonitoring.getColumnModel().getColumn(1).setPreferredWidth(220);
        tblPatientMonitoring.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblPatientMonitoring.getColumnModel().getColumn(3).setPreferredWidth(260);

        spPatientMonitoring = new JScrollPane(tblPatientMonitoring);
        spPatientMonitoring.setBounds(15, 50, 1040, 435);
        spPatientMonitoring.getViewport().setBackground(ColorsTheme.Main_Card);
        spPatientMonitoring.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY));
        pnlPatientMonitoring.add(spPatientMonitoring);
        
        // Populate patient monitoring table from the database
        FetchPatientMonitoring(patientTableModel);

        // Quick Actions panel
        pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBounds(1100, 0, 380, 500);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card);
        pnlMiddle.add(pnlQuickActions);

        lblQuickActionsTitle = new JLabel("Quick Actions");
        lblQuickActionsTitle.setFont(FontsTheme.Title_Texts);
        lblQuickActionsTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickActionsTitle.setBounds(30, 20, 250, 35);
        pnlQuickActions.add(lblQuickActionsTitle);

        pnlQuickActions.add(createQuickActionButton("Add Patient", () -> new AddPatientDialog().setVisible(true), 60, 80, ColorsTheme.Blue, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Appointments", () -> new NewAppointmentDialog().setVisible(true), 60, 155, ColorsTheme.Orange, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Medical Records", () -> new AddMedicalRecordDialog().setVisible(true), 60, 230, ColorsTheme.Green, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Medication", () -> new NewPharmacyDialog().setVisible(true), 60, 305, ColorsTheme.Top_Line, ColorsTheme.Text_White));
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshDashboardData();
            }
        });
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
        button.addActionListener(e -> {
            action.run();
            refreshDashboardData();
        });
        return button;
    }

    // --- DATABASE EXTRACTION OPERATIONS ---
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    private void refreshDashboardData() {
        refreshSummaryCards();
        if (patientTableModel != null) {
            FetchPatientMonitoring(patientTableModel);
        }
    }
    
    private void refreshSummaryCards() {
        if (pnlPatients != null) remove(pnlPatients);
        if (pnlAppointments != null) remove(pnlAppointments);
        if (pnlBeds != null) remove(pnlBeds);
        if (pnlRevenue != null) remove(pnlRevenue);
        
        // Fetch real-time live metrics directly using matching database logic
        String patientsAssigned = String.valueOf(getTableRowCount("patients", "WHERE status <> 'Discharged'"));
        String todaysAppointments = String.valueOf(getTableRowCount("appointments", ""));
        String medicationDue = String.valueOf(getTableRowCount("prescriptions", "WHERE LOWER(status) = 'pending'"));
        String criticalAlerts = String.valueOf(getTableRowCount("patients", "WHERE LOWER(status) IN ('critical', 'emergency', 'serious')"));
        
        pnlPatients = new PanelCard2("Patients Assigned", patientsAssigned, "Under your care", ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 150, 350, 140);
        add(pnlPatients);
        
        pnlAppointments = new PanelCard2("Today's Appointments", todaysAppointments, "Scheduled today", ColorsTheme.Orange);
        pnlAppointments.setBounds(450, 150, 350, 140);
        add(pnlAppointments);
        
        pnlBeds = new PanelCard2("Medication Due", medicationDue, "Pending prescriptions", ColorsTheme.Blue);
        pnlBeds.setBounds(830, 150, 350, 140);
        add(pnlBeds);
        
        pnlRevenue = new PanelCard2("Critical Alerts", criticalAlerts, "Needs attention", ColorsTheme.Green);
        pnlRevenue.setBounds(1210, 150, 350, 140);
        add(pnlRevenue);
        
        repaint();
        revalidate();
    }

    private void FetchPatientMonitoring(DefaultTableModel model) {
        // Clear existing data before loading new records
        model.setRowCount(0);
        
        String sql = "SELECT p.first_name, p.last_name, p.room_number, p.status, "
                + "(SELECT CONCAT(mr.record_date, ' ', mr.record_time) "
                + "FROM medical_records mr "
                + "WHERE mr.patient_id = p.patient_id "
                + "ORDER BY mr.record_id DESC LIMIT 1) AS last_updated "
                + "FROM patients p "
                + "WHERE p.status <> 'Discharged' "
                + "ORDER BY p.patient_id ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                String room = rs.getString("room_number");
                String status = rs.getString("status");
                String lastUpdated = rs.getString("last_updated");

                model.addRow(new Object[]{
                    fullName,
                    room == null || room.trim().isEmpty() ? "Unassigned" : room,
                    status == null || status.trim().isEmpty() ? "No status" : status,
                    lastUpdated == null || lastUpdated.trim().isEmpty() ? "No record" : lastUpdated
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            model.addRow(new Object[]{"ERR", "Database", "Error", "Connection failed"});
        }
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

    private JTable createSimpleTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(FontsTheme.Info_Texts);
        table.setForeground(ColorsTheme.Text_Black);
        table.setBackground(ColorsTheme.Main_Card);
        table.setRowHeight(SystemSettings.tableRowHeight);
        table.setGridColor(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setSelectionBackground(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
        table.setSelectionForeground(ColorsTheme.Text_Black);
        table.getTableHeader().setFont(FontsTheme.Bold);
        table.getTableHeader().setForeground(ColorsTheme.Text_White);
        table.getTableHeader().setBackground(ColorsTheme.isDarkMode ? Color.decode("#2E2E38") : ColorsTheme.Header);
        table.getTableHeader().setReorderingAllowed(false);

        // Guarantee cell renderer foreground and background colors
        javax.swing.table.DefaultTableCellRenderer cellRenderer = new javax.swing.table.DefaultTableCellRenderer() {
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
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        return table;
    }

}
