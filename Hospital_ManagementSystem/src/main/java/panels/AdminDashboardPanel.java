package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dialogs.NewStaffDialog;
import dialogs.NewPharmacyDialog;
import dialogs.NewReportDialog;

public class AdminDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlDoctors, pnlNurses, pnlAppointments, pnlSupplies, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    // Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    // Unified database configuration pointing to your active MySQL server port
    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = ""; 
    
    public AdminDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        // Fetch real-time live metrics directly using matching database logic
        String totalPatients = String.valueOf(getTableRowCount("patients", ""));
        String totalDoctors = String.valueOf(getTableRowCount("hospital_staff", "WHERE LOWER(role) = 'doctor'"));
        String totalNurses = String.valueOf(getTableRowCount("hospital_staff", "WHERE LOWER(role) = 'nurse'"));
        String totalAppointments = String.valueOf(getTableRowCount("appointments", "")); 
        String totalSupplies = String.valueOf(getTableRowCount("pharmacy", ""));
        
        // Match the Low Stock criteria present in your pharmacy schema
        int lowStock = getTableRowCount("pharmacy", "WHERE LOWER(status) = 'low stock'");
        String lowStockText = lowStock + " Items Low Stock";

        // Calculate cumulative asset revenue cleanly
        String totalRevenue = getInventoryAssetRevenue();

        // Middle Panel Container 
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 470, 1500, 400);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel); 
        add(pnlMiddle);

        // Greeting section
        lblGreet = new JLabel("Welcome to CareLink, Admin!");
        lblGreet.setBounds(30, 20, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 60, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        // Summary Panel Cards Row 1
        pnlPatients = new PanelCard2("Total Patients", totalPatients, "Active Records", ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 160, 460, 140);
        add(pnlPatients);
        
        pnlDoctors = new PanelCard2("Total Doctors", totalDoctors, "Active Specialists", ColorsTheme.Blue);
        pnlDoctors.setBounds(580, 160, 460, 140);
        add(pnlDoctors);
        
        pnlNurses = new PanelCard2("Total Nurses", totalNurses, "Active Staff", ColorsTheme.Orange);
        pnlNurses.setBounds(1090, 160, 460, 140);
        add(pnlNurses);
        
        // Summary Panel Cards Row 2
        pnlAppointments = new PanelCard2("Today's Appointments", totalAppointments, "Scheduled Total", ColorsTheme.Green);
        pnlAppointments.setBounds(70, 310, 460, 140);
        add(pnlAppointments);
        
        pnlSupplies = new PanelCard2("Medical Supplies", totalSupplies, lowStockText, ColorsTheme.Red);
        pnlSupplies.setBounds(580, 310, 460, 140);
        add(pnlSupplies);
        
        pnlRevenue = new PanelCard2("Total Stock Value", "₱" + totalRevenue, "Inventory Valuation", ColorsTheme.Top_Line);
        pnlRevenue.setBounds(1090, 310, 460, 140);
        add(pnlRevenue);
        
        // --- Quick Actions Panel ---
        JPanel pnlQuickActions = new JPanel();
        pnlQuickActions.setLayout(null);
        pnlQuickActions.setBackground(ColorsTheme.Main_Card); 
        pnlQuickActions.setBounds(960, 0, 520, 400); 
        pnlMiddle.add(pnlQuickActions);

        JLabel lblQuickTitle = new JLabel("Quick Actions");
        lblQuickTitle.setFont(FontsTheme.Title_Texts);
        lblQuickTitle.setForeground(ColorsTheme.Text_Black);
        lblQuickTitle.setBounds(32, 20, 350, 35);
        pnlQuickActions.add(lblQuickTitle);
        
        // Line 1
        JButton btnRegisterStaff = new JButton("Register Staff");
        btnRegisterStaff.setBounds(40, 80, 200, 70);
        btnRegisterStaff.setBackground(ColorsTheme.Green);
        btnRegisterStaff.setForeground(Color.WHITE);
        btnRegisterStaff.setFont(FontsTheme.Info_Texts);
        btnRegisterStaff.setFocusPainted(false);
        btnRegisterStaff.addActionListener(e -> new NewStaffDialog().setVisible(true));
        pnlQuickActions.add(btnRegisterStaff);
        
        JButton btnAddMedication = new JButton("Add Medication");
        btnAddMedication.setBounds(280, 80, 200, 70);
        btnAddMedication.setBackground(ColorsTheme.Yellow);
        btnAddMedication.setForeground(Color.BLACK);
        btnAddMedication.setFont(FontsTheme.Info_Texts);
        btnAddMedication.setFocusPainted(false);
        btnAddMedication.addActionListener(e -> new NewPharmacyDialog().setVisible(true));
        pnlQuickActions.add(btnAddMedication);
        
        // Line 2
        JButton btnGenReports = new JButton("Generate Reports");
        btnGenReports.setBounds(160, 180, 200, 70);
        btnGenReports.setBackground(ColorsTheme.Top_Line);
        btnGenReports.setForeground(Color.WHITE);
        btnGenReports.setFont(FontsTheme.Info_Texts);
        btnGenReports.setFocusPainted(false);
        btnGenReports.addActionListener(e -> new NewReportDialog().setVisible(true));
        pnlQuickActions.add(btnGenReports);
        
        pnlOverview = new JPanel();
        pnlOverview.setLayout(null);
        pnlOverview.setBounds(0, 0, 920, 400); 
        pnlOverview.setBackground(ColorsTheme.Main_Card); 
        pnlMiddle.add(pnlOverview);
        
        lblOverviewTitle = new JLabel("Recent Appointments & Admissions");
        lblOverviewTitle.setFont(FontsTheme.Title_Texts);
        lblOverviewTitle.setForeground(ColorsTheme.Text_Black);
        lblOverviewTitle.setBounds(30, 20, 500, 35);
        pnlOverview.add(lblOverviewTitle);
        
        // Dynamic Recents table setup
        String[] logColumns = {"ID", "Patient", "Department", "Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(logColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent cells from being edited directly
            }
        };
        
        JTable tblLogs = new JTable(tableModel);
        tblLogs.setRowHeight(50);
        tblLogs.setFont(FontsTheme.Info_Texts);
        tblLogs.setBackground(ColorsTheme.Main_Card);
        tblLogs.setForeground(ColorsTheme.Text_Black);
        tblLogs.setGridColor(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY);
        tblLogs.setSelectionBackground(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
        tblLogs.setSelectionForeground(ColorsTheme.Text_Black);
        tblLogs.getTableHeader().setReorderingAllowed(false);
        tblLogs.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblLogs.getTableHeader().setBackground(ColorsTheme.isDarkMode ? Color.decode("#2E2E38") : ColorsTheme.Header);
        tblLogs.getTableHeader().setForeground(ColorsTheme.Text_White);

        // Center the text and guarantee cell colors apply correctly
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
        logScrollPane.setBounds(15, 60, 890, 320);
        logScrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        logScrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY, 1)); 
        pnlOverview.add(logScrollPane);

        // Populate table from the database
        FetchReports(tableModel);
    }

    // --- DATABASE EXTRACTION OPERATIONS ---
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void FetchReports(DefaultTableModel model) {
        // Clear existing data before loading new records
        model.setRowCount(0); 
        
        // Query fetching recent appointments. 
        // We order by appt_id DESC to get the most recent entries and limit it to 50 for performance on the dashboard.
        String sql = "SELECT appt_id, patient_name, department, appointment_date, status FROM appointments ORDER BY appt_id DESC LIMIT 50";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String id = "#" + rs.getInt("appt_id");
                String patient = rs.getString("patient_name");
                String department = rs.getString("department");
                String date = rs.getString("appointment_date");
                String status = rs.getString("status");
                
                // Add the row to our model
                model.addRow(new Object[]{id, patient, department, date, status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Fallback empty row if connection fails to let user know something is wrong
            model.addRow(new Object[]{"ERR", "Database connection failed", "N/A", "N/A", "Error"});
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

    private String getInventoryAssetRevenue() {
        double calculatedAssetWorth = 0.0;
        String sql = "SELECT SUM(current_stock * unit_price) FROM pharmacy";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                calculatedAssetWorth = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (calculatedAssetWorth >= 1000) {
            return String.format("%.1fK", calculatedAssetWorth / 1000);
        }
        return String.valueOf((int) calculatedAssetWorth);
    }
}