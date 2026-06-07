package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlDoctors, pnlNurses, pnlAppointments, pnlSupplies, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    // Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    
    public AdminDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
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
        pnlPatients = new PanelCard2("Total Patients", "2,847", "Active Records", ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 160, 460, 140);
        add(pnlPatients);
        
        pnlDoctors = new PanelCard2("Total Doctors", "45", "Active Specialists", ColorsTheme.Blue);
        pnlDoctors.setBounds(580, 160, 460, 140);
        add(pnlDoctors);
        
        pnlNurses = new PanelCard2("Total Nurses", "120", "Active Staff", ColorsTheme.Orange);
        pnlNurses.setBounds(1090, 160, 460, 140);
        add(pnlNurses);
        
        // Summary Panel Cards Row 2
        pnlAppointments = new PanelCard2("Today's Appointments", "156", "Scheduled Today", ColorsTheme.Green);
        pnlAppointments.setBounds(70, 310, 460, 140);
        add(pnlAppointments);
        
        pnlSupplies = new PanelCard2("Medical Supplies", "1,245", "65 Items Low Stock", ColorsTheme.Red);
        pnlSupplies.setBounds(580, 310, 460, 140);
        add(pnlSupplies);
        
        pnlRevenue = new PanelCard2("Today's Revenue", "₱45K", "Target: ₱50K", ColorsTheme.Top_Line);
        pnlRevenue.setBounds(1090, 310, 460, 140);
        add(pnlRevenue);
        
        
        
        /* --- Department Distribution ---
        pnlDistribution = new JPanel();
        pnlDistribution.setLayout(null);
        pnlDistribution.setBackground(ColorsTheme.Main_Card); 
        pnlDistribution.setBounds(960, 0, 520, 400); 
        pnlMiddle.add(pnlDistribution);

        lblDistributionTitle = new JLabel("Department Distribution");
        lblDistributionTitle.setFont(FontsTheme.Title_Texts);
        lblDistributionTitle.setForeground(ColorsTheme.Text_Black);
        lblDistributionTitle.setBounds(32, 20, 350, 35);
        pnlDistribution.add(lblDistributionTitle);
        
        // Progress Bars
        barCardiology = createCustomProgressBar(32, 70, 450, 28, 25, ColorsTheme.Cardiology_Color);
        barOrthopedics = createCustomProgressBar(32, 110, 450, 28, 22, ColorsTheme.Orthophedics_Color);
        barEmergency = createCustomProgressBar(32, 150, 450, 28, 20, ColorsTheme.Emergency_Color);
        barNeurology = createCustomProgressBar(32, 190, 450, 28, 18, ColorsTheme.Neurology_Color);
        barPediatrics = createCustomProgressBar(32, 230, 450, 28, 15, ColorsTheme.Pediatrics_Color);

        pnlDistribution.add(barCardiology);
        pnlDistribution.add(barOrthopedics);
        pnlDistribution.add(barEmergency);
        pnlDistribution.add(barNeurology);
        pnlDistribution.add(barPediatrics);
        
        // Legend Items (now relative to pnlDistribution)
        pnlDistribution.add(createLegendItem(100, 280, "Cardiology", ColorsTheme.Cardiology_Color));
        pnlDistribution.add(createLegendItem(290, 280, "Orthopedics", ColorsTheme.Orthophedics_Color));
        pnlDistribution.add(createLegendItem(100, 320, "Emergency", ColorsTheme.Emergency_Color));
        pnlDistribution.add(createLegendItem(290, 320, "Neurology", ColorsTheme.Neurology_Color));
        pnlDistribution.add(createLegendItem(100, 360, "Pediatrics", ColorsTheme.Pediatrics_Color));
        */
        
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
        JButton btnAddPatient = new JButton("Add New Patient");
        btnAddPatient.setBounds(40, 80, 200, 70);
        btnAddPatient.setBackground(ColorsTheme.Blue); 
        btnAddPatient.setForeground(Color.WHITE);
        btnAddPatient.setFont(FontsTheme.Info_Texts);
        btnAddPatient.setFocusPainted(false);
        pnlQuickActions.add(btnAddPatient);
        
        JButton btnRegisterStaff = new JButton("Register Staff");
        btnRegisterStaff.setBounds(280, 80, 200, 70);
        btnRegisterStaff.setBackground(ColorsTheme.Green);
        btnRegisterStaff.setForeground(Color.WHITE);
        btnRegisterStaff.setFont(FontsTheme.Info_Texts);
        btnRegisterStaff.setFocusPainted(false);
        pnlQuickActions.add(btnRegisterStaff);
        
        // Line 2
        JButton btnManageDepts = new JButton("Manage Departments");
        btnManageDepts.setBounds(40, 180, 200, 70);
        btnManageDepts.setBackground(ColorsTheme.Orange);
        btnManageDepts.setForeground(Color.WHITE);
        btnManageDepts.setFont(FontsTheme.Info_Texts);
        btnManageDepts.setFocusPainted(false);
        pnlQuickActions.add(btnManageDepts);
        
        JButton btnManageRooms = new JButton("Manage Rooms");
        btnManageRooms.setBounds(280, 180, 200, 70);
        btnManageRooms.setBackground(ColorsTheme.Yellow);
        btnManageRooms.setForeground(Color.BLACK);
        btnManageRooms.setFont(FontsTheme.Info_Texts);
        btnManageRooms.setFocusPainted(false);
        pnlQuickActions.add(btnManageRooms);
        
        // Line 3
        JButton btnGenReports = new JButton("Generate Reports");
        btnGenReports.setBounds(160, 280, 200, 70);
        btnGenReports.setBackground(ColorsTheme.Top_Line);
        btnGenReports.setForeground(Color.WHITE);
        btnGenReports.setFont(FontsTheme.Info_Texts);
        btnGenReports.setFocusPainted(false);
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
        
        // Recents table
        String[] logColumns = {"ID", "Patient", "Department", "Date", "Status"};
        Object[][] logData = {
            {"#1024", "Lebron James", "Cardiology", "12-10-2026", "Admitted"},
            {"#1025", "Wemby Talo", "Orthopedics", "12-10-2026", "Scheduled"},
            {"#1026", "KAT Bading", "Emergency", "12-10-2026", "Discharged"},
            {"#1027", "John Carlo Nayan", "Neurology", "12-10-2026", "Admitted"},
            {"#1028", "Hello Johnson", "Pediatrics", "12-10-2026", "Admitted"},
            {"#1029", "Sarah Geronimo", "Cardiology", "12-10-2026", "Scheduled"}
        };
        
        JTable tblLogs = new JTable(logData, logColumns);
        tblLogs.setRowHeight(50);
        tblLogs.setFont(FontsTheme.Info_Texts);
        tblLogs.setDefaultEditor(Object.class, null);
        tblLogs.getTableHeader().setReorderingAllowed(false);
        tblLogs.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblLogs.getTableHeader().setBackground(ColorsTheme.Header);
        tblLogs.getTableHeader().setForeground(ColorsTheme.Text_White);

        // Center the text so it doesn't hug the left borders
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblLogs.getColumnCount(); i++) {
            tblLogs.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        JScrollPane logScrollPane = new JScrollPane(tblLogs);
        logScrollPane.setBounds(15, 60, 920, 320);
        logScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); 
        pnlOverview.add(logScrollPane);
        
    }

    private JProgressBar createCustomProgressBar(int x, int y, int width, int height, int value, Color filledColor) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setBounds(x, y, width, height);
        bar.setValue(value);
        bar.setStringPainted(true);
        bar.setFont(FontsTheme.Progress_Bar_Font);
        bar.setForeground(filledColor);
        bar.setBackground(ColorsTheme.Middle_Panel); 
        bar.setBorderPainted(false);
        return bar;
    }

    private JPanel createLegendItem(int x, int y, String text, Color color) {
        JPanel item = new JPanel();
        item.setLayout(null);
        item.setOpaque(false);
        item.setBounds(x, y, 300, 28);

        JPanel cube = new JPanel();
        cube.setBackground(color);
        cube.setBounds(0, 6, 16, 16);
        item.add(cube);

        JLabel label = new JLabel(text);
        label.setFont(FontsTheme.Info_Texts);
        label.setBounds(20, 2, 260, 24);
        item.add(label);
        return item;
    }
}