package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import dialogs.NewMedicalRecordDialog;
import dialogs.NewAppointmentDialog;
import dialogs.NewPatientDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NurseDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, pnlMiddle, pnlPatientMonitoring, pnlQuickActions;
    private JLabel lblGreet, lblDescrip, lblPatientMonitoringTitle, lblQuickActionsTitle;
    private JTable tblPatientMonitoring;
    private JScrollPane spPatientMonitoring;
    private DefaultTableModel patientTableModel;
    
    // Database Credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";
    
    public NurseDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel);
        add(pnlMiddle);

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

        String[] patientColumns = {"Patient ID", "Patient Name", "Status", "Last Updated"};
        patientTableModel = new DefaultTableModel(patientColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPatientMonitoring = createSimpleTable(patientTableModel);
        
        tblPatientMonitoring.getColumnModel().getColumn(0).setPreferredWidth(150); 
        tblPatientMonitoring.getColumnModel().getColumn(1).setPreferredWidth(320); 
        tblPatientMonitoring.getColumnModel().getColumn(2).setPreferredWidth(200); 
        tblPatientMonitoring.getColumnModel().getColumn(3).setPreferredWidth(250); 

        spPatientMonitoring = new JScrollPane(tblPatientMonitoring);
        spPatientMonitoring.setBounds(15, 50, 1040, 435);
        spPatientMonitoring.getViewport().setBackground(ColorsTheme.Main_Card);
        spPatientMonitoring.setBorder(BorderFactory.createLineBorder(ColorsTheme.Gray));
        pnlPatientMonitoring.add(spPatientMonitoring);
        
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

        // THE FIX: Removed the Pharmacy button, spaced the remaining 3 securely
        pnlQuickActions.add(createQuickActionButton("Add Patient", () -> new NewPatientDialog().setVisible(true), 60, 80, ColorsTheme.Blue, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Appointments", () -> new NewAppointmentDialog().setVisible(true), 60, 180, ColorsTheme.Orange, ColorsTheme.Text_White));
        pnlQuickActions.add(createQuickActionButton("Medical Records", () -> new NewMedicalRecordDialog().setVisible(true), 60, 280, ColorsTheme.Green, ColorsTheme.Text_White));
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshDashboardData();
            }
        });

        refreshDashboardData();
    }

    private JButton createQuickActionButton(String text, Runnable action, int x, int y, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 260, 55);
        button.setFont(FontsTheme.Plain_Texts);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(ColorsTheme.Gray));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            action.run();
            refreshDashboardData();
        });
        return button;
    }
    
    private JTable createSimpleTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(FontsTheme.Info_Texts);
        table.setForeground(ColorsTheme.Text_Black);
        table.setBackground(ColorsTheme.Main_Card);
        table.setRowHeight(SystemSettings.tableRowHeight);
        
        table.setGridColor(ColorsTheme.Gray);
        table.setShowGrid(true);
        table.setSelectionBackground(ColorsTheme.Active_Button);
        table.setSelectionForeground(ColorsTheme.Text_White);
        
        table.getTableHeader().setFont(FontsTheme.Bold);
        table.getTableHeader().setForeground(ColorsTheme.Text_White);
        table.getTableHeader().setBackground(ColorsTheme.Header);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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
        
        // THE FIX: Added actual backend logic to filter these numbers correctly
        String patientsAssigned = String.valueOf(getTableRowCount("patients", "WHERE status_id = 2")); // Only admitted
        String todaysAppointments = String.valueOf(getTableRowCount("appointments", "WHERE DATE(appointment_date) = CURDATE()")); // Only today
        String medicationDue = String.valueOf(getTableRowCount("prescriptions", "WHERE status_id = 1")); // Only pending
        String criticalAlerts = "0"; 
        
        pnlPatients = new PanelCard2("Admitted Patients", patientsAssigned, "Under your care", ColorsTheme.Yellow);
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
        model.setRowCount(0);
        
        // THE FIX: Properly joined the status table so we don't have to hardcode "Admitted"
        String sql = "SELECT p.patient_id, p.first_name, p.last_name, s.status_name, "
                + "(SELECT mr.record_datetime "
                + "FROM medical_records mr "
                + "WHERE mr.patient_id = p.patient_id "
                + "ORDER BY mr.record_datetime DESC LIMIT 1) AS last_updated "
                + "FROM patients p "
                + "LEFT JOIN patient_status s ON p.status_id = s.status_id "
                + "ORDER BY p.patient_id ASC LIMIT " + SystemSettings.dashboardRecordLimit;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String displayId = String.format("PAT-%03d", rs.getInt("patient_id"));
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                
                String status = rs.getString("status_name");
                if (status == null) status = "Unknown";
                
                String displayUpdated = "No record";
                
                // THE FIX: Unified YYYY-MM-DD hh:mm AM/PM Time Logic
                java.sql.Date dbDate = rs.getDate("last_updated");
                if (dbDate != null) {
                    java.sql.Time dbTime = rs.getTime("last_updated");
                    LocalTime localTime = dbTime.toLocalTime();
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                    String amPm = localTime.getHour() >= 12 ? "PM" : "AM";
                    
                    displayUpdated = dbDate.toString() + " " + localTime.format(timeFormatter) + " " + amPm;
                }

                model.addRow(new Object[]{
                    displayId,
                    fullName,
                    status,
                    displayUpdated
                });
            }
        } catch (SQLException ex) {
            System.out.println("Warning: " + ex.getMessage());
            model.addRow(new Object[]{"ERR", "Database Error", "Connection failed", "N/A"});
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
            System.out.println("Warning: Could not fetch metrics for table " + tableName);
        }
        return rowsCount;
    }
}