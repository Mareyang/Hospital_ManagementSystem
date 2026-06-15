package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import controls.DoctorDashboard;
import dialogs.NewPrescriptionDialog; // ADDED: So the doctor can prescribe!
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Window;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DoctorDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlSurgery, pnlPending, pnlMiddle, pnlOverview, pnlQuickActions;
    private JLabel lblGreet, lblDescrip, lblOverviewTitle, lblQuickTitle;
    private JTable tblLogs;
    private DefaultTableModel tableModel;
    private JScrollPane logScrollPane;
    
    // Database Credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";
    
    public DoctorDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
                
        // Middle Panel Container
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel);
        add(pnlMiddle);

        // Greeting Section
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
        
        // Quick Actions Panel
        pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card); 
        pnlQuickActions.setBounds(1140, 0, 350, 600); 
        pnlMiddle.add(pnlQuickActions);

        lblQuickTitle = new JLabel("Quick Actions");
        lblQuickTitle.setFont(FontsTheme.Title_Texts);
        lblQuickTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickTitle.setBounds(32, 8, 350, 50);
        pnlQuickActions.add(lblQuickTitle);
        
        // --- THE FIXED QUICK ACTION BUTTONS ---
        pnlQuickActions.add(createQuickActionButton("Patient Records", () -> {
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

        pnlQuickActions.add(createQuickActionButton("Medical Records", () -> {
            Window topWindow = SwingUtilities.getWindowAncestor(this);
            if (topWindow instanceof DoctorDashboard) {
                DoctorDashboard dashboard = (DoctorDashboard) topWindow;
                dashboard.switchView("medicalRecords", dashboard.btnMedicalRecords); 
            }
        }, 45, 230, ColorsTheme.Green, ColorsTheme.Text_White));

        pnlQuickActions.add(createQuickActionButton("Prescribe Medication", () -> {
            // THE FIX: Hooked up to the new Dialog we built!
            new NewPrescriptionDialog().setVisible(true);
        }, 45, 305, ColorsTheme.Teal, ColorsTheme.Text_White));
        // ----------------------------------------

        // Overview Schedule Panel
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

        // Schedule Table Setup
        String[] logColumns = {"Time", "Patient", "Type", "Status"};
        tableModel = new DefaultTableModel(logColumns, 0);
        
        tblLogs = new JTable(tableModel);
        tblLogs.setRowHeight(SystemSettings.tableRowHeight);
        tblLogs.setFont(FontsTheme.Info_Texts);
        tblLogs.setDefaultEditor(Object.class, null);
        
        tblLogs.setBackground(ColorsTheme.Main_Card);
        tblLogs.setForeground(ColorsTheme.Text_Black);
        tblLogs.setGridColor(ColorsTheme.Gray);
        tblLogs.setSelectionBackground(ColorsTheme.Active_Button);
        tblLogs.setSelectionForeground(ColorsTheme.Text_White);
        
        tblLogs.getTableHeader().setReorderingAllowed(false);
        tblLogs.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblLogs.getTableHeader().setBackground(ColorsTheme.Header);
        tblLogs.getTableHeader().setForeground(ColorsTheme.Text_White);

        // Center Table Text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
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
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblLogs.getColumnCount(); i++) {
            tblLogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        logScrollPane = new JScrollPane(tblLogs);
        logScrollPane.setBounds(20, 70, 1080, 400);
        logScrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        logScrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.Gray, 1)); 
        pnlOverview.add(logScrollPane);
        
        // --- ADDED: Auto-Refresh Listener ---
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshDashboardData();
            }
        });

        refreshDashboardData();
    }
    

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    private void refreshDashboardData() {
        refreshSummaryCards();
        loadTodaySchedule();
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
    
    private void refreshSummaryCards() {
        if (pnlAppointments != null) remove(pnlAppointments);
        if (pnlPending != null) remove(pnlPending);
        if (pnlSurgery != null) remove(pnlSurgery);
        if (pnlPatients != null) remove(pnlPatients);
        
        // THE FIX: Strict constraints to only pull relevant, real-time data
        String totalAppointments = String.valueOf(getTableRowCount("appointments", "WHERE DATE(appointment_date) = CURDATE()"));
        int remainingCount = getTableRowCount("appointments", "WHERE status_id = 1 AND DATE(appointment_date) = CURDATE()");
        String remainingText = remainingCount + " remaining";
        
        // Safely zeroing out missing tables until they are built
        String pendingDiagnoses = "0"; 
        String upcomingSurgeries = "0"; 
        
        String totalPatients = String.valueOf(getTableRowCount("patients", "WHERE status_id = 2")); // Only admitted ones need intense oversight
        
        pnlAppointments = new PanelCard2("Today's Appointments", totalAppointments, remainingText, ColorsTheme.Yellow);
        pnlAppointments.setBounds(70, 150, 350, 140);
        add(pnlAppointments);
        
        pnlPending = new PanelCard2("Pending Diagnoses", pendingDiagnoses, "To be reviewed", ColorsTheme.Orange);
        pnlPending.setBounds(450, 150, 350, 140);
        add(pnlPending);
        
        pnlSurgery = new PanelCard2("Upcoming Surgeries", upcomingSurgeries, "Scheduled today", ColorsTheme.Blue);
        pnlSurgery.setBounds(830, 150, 350, 140);
        add(pnlSurgery);
        
        pnlPatients = new PanelCard2("Admitted Patients", totalPatients, "Under observation", ColorsTheme.Green);
        pnlPatients.setBounds(1210, 150, 350, 140);
        add(pnlPatients);
        
        repaint();
        revalidate();
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
        button.addActionListener(e -> action.run());
        return button;
    }

    private void loadTodaySchedule() {
        tableModel.setRowCount(0);
    
        // THE FIX: Restricting the query to ONLY pull today's schedule!
        String sql = "SELECT a.appointment_time, p.first_name, p.last_name, a.visit_type, s.status_name " +
                     "FROM appointments a " +
                     "LEFT JOIN patients p ON a.patient_id = p.patient_id " +
                     "LEFT JOIN appointment_status s ON a.status_id = s.status_id " +
                     "WHERE DATE(a.appointment_date) = CURDATE() " +
                     "ORDER BY a.appointment_time ASC LIMIT " + SystemSettings.dashboardRecordLimit;
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
        
            while (rs.next()) {
                String displayTime = "N/A";
                java.sql.Time dbTime = rs.getTime("appointment_time");
                if (dbTime != null) {
                    java.time.LocalTime localTime = dbTime.toLocalTime();
                    displayTime = localTime.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
                }
                
                String patient = rs.getString("first_name") + " " + rs.getString("last_name");
                String type = rs.getString("visit_type"); 
                String status = rs.getString("status_name");
            
                tableModel.addRow(new Object[]{displayTime, patient, type, status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            tableModel.addRow(new Object[]{"N/A", "Error loading schedule", "N/A", "N/A"});
        }
    }
}