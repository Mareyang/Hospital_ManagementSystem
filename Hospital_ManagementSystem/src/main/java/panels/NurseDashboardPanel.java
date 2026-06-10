/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.AddMedicalRecordDialog;
import dialogs.AddPatientDialog;
import dialogs.NewAppointmentDialog;
import dialogs.NewPharmacyDialog;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class NurseDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblPatientMonitoringTitle, lblMedicationSuppliesTitle, lblQuickActionsTitle;
    
    private JPanel pnlPatientMonitoring, pnlMedicationSupplies, pnlQuickActions;
    private JTable tblPatientMonitoring, tblMedicationSupplies;
    private JScrollPane spPatientMonitoring, spMedicationSupplies;
    
    
    
    
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
        
        
        //Summary Panel Cards
        pnlPatients = new PanelCard2(
                "Patients Assigned",
                "24",
                "Under your care",
                ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 150, 350, 140);
        add(pnlPatients);
        
        pnlAppointments = new PanelCard2(
                "Today's Appointments",
                "18",
                "6 remaining",
                ColorsTheme.Orange);
        pnlAppointments.setBounds(450, 150, 350, 140);
        add(pnlAppointments);
        
        pnlBeds = new PanelCard2(
                "Medication Due",
                "9",
                "Next round pending",
                ColorsTheme.Blue);
        pnlBeds.setBounds(830, 150, 350, 140);
        add(pnlBeds);
        
        pnlRevenue = new PanelCard2(
                "Critical Alerts",
                "3",
                "Needs attention",
                ColorsTheme.Green);
        pnlRevenue.setBounds(1210, 150, 350, 140);
        add(pnlRevenue);
        
        
        
        // Patient Monitoring List table
        pnlPatientMonitoring = new JPanel();
        pnlPatientMonitoring.setLayout(null);
        pnlPatientMonitoring.setBounds(0, 0, 650, 500);
        pnlPatientMonitoring.setBackground(ColorsTheme.Main_Card);
        pnlMiddle.add(pnlPatientMonitoring);

        lblPatientMonitoringTitle = new JLabel("Patient Monitoring List");
        lblPatientMonitoringTitle.setFont(FontsTheme.Title_Texts);
        lblPatientMonitoringTitle.setForeground(ColorsTheme.Text_Black);
        lblPatientMonitoringTitle.setBounds(15, 10, 400, 30);
        pnlPatientMonitoring.add(lblPatientMonitoringTitle);

        String[] patientColumns = {"Patient Name", "Room / Bed", "Condition", "Last Updated"};
        Object[][] patientRows = {
            {"John Doe", "Ward 3 - Bed 1", "Stable", "08:30 AM"},
            {"Mery Smith", "Ward 3 - Bed 2", "Stable", "08:45 AM"},
            {"Robert Brown", "ICU - Bed 4", "Critical", "08:50 AM"},
            {"Linda White", "Ward 2 - Bed 1", "Serious", "08:40 AM"},
            {"David Wilson", "Ward 2 - Bed 2", "Stable", "08:35 AM"},
            {"Sarah Geronimo", "Ward 1 - Bed 4", "Stable", "09:10 AM"},
            {"Lebron James", "ICU - Bed 2", "Serious", "09:25 AM"},
            {"Wemby Talo", "Ward 4 - Bed 3", "Stable", "09:40 AM"},
            {"KAT Bading", "Ward 1 - Bed 1", "Critical", "09:55 AM"}
        };

        DefaultTableModel patientTableModel = new DefaultTableModel(patientRows, patientColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPatientMonitoring = createSimpleTable(patientTableModel);
        tblPatientMonitoring.getColumnModel().getColumn(0).setPreferredWidth(190);
        tblPatientMonitoring.getColumnModel().getColumn(1).setPreferredWidth(160);
        tblPatientMonitoring.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblPatientMonitoring.getColumnModel().getColumn(3).setPreferredWidth(130);

        spPatientMonitoring = new JScrollPane(tblPatientMonitoring);
        spPatientMonitoring.setBounds(15, 50, 620, 435);
        spPatientMonitoring.getViewport().setBackground(ColorsTheme.Main_Card);
        spPatientMonitoring.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY));
        pnlPatientMonitoring.add(spPatientMonitoring);

        // Medication Supplies table
        pnlMedicationSupplies = new JPanel();
        pnlMedicationSupplies.setLayout(null);
        pnlMedicationSupplies.setBounds(680, 0, 390, 500);
        pnlMedicationSupplies.setBackground(ColorsTheme.Main_Card);
        pnlMiddle.add(pnlMedicationSupplies);

        lblMedicationSuppliesTitle = new JLabel("Medication Supplies");
        lblMedicationSuppliesTitle.setFont(FontsTheme.Title_Texts);
        lblMedicationSuppliesTitle.setForeground(ColorsTheme.Text_Black);
        lblMedicationSuppliesTitle.setBounds(15, 10, 250, 30);
        pnlMedicationSupplies.add(lblMedicationSuppliesTitle);

        String[] supplyColumns = {"Medication", "Stock", "Status"};
        Object[][] supplyRows = {
            {"Paracetamol", "120", "Available"},
            {"Amoxicillin", "45", "Available"},
            {"Insulin", "18", "Low Stock"},
            {"Salbutamol", "0", "Out of Stock"},
            {"Cetirizine", "80", "Available"},
            {"Metformin", "12", "Low Stock"},
            {"Aspirin", "0", "Out of Stock"},
            {"Ibuprofen", "64", "Available"},
            {"Losartan", "25", "Available"}
        };

        DefaultTableModel supplyTableModel = new DefaultTableModel(supplyRows, supplyColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblMedicationSupplies = createSimpleTable(supplyTableModel);
        tblMedicationSupplies.getColumnModel().getColumn(0).setPreferredWidth(160);
        tblMedicationSupplies.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblMedicationSupplies.getColumnModel().getColumn(2).setPreferredWidth(120);

        spMedicationSupplies = new JScrollPane(tblMedicationSupplies);
        spMedicationSupplies.setBounds(15, 50, 360, 435);
        spMedicationSupplies.getViewport().setBackground(ColorsTheme.Main_Card);
        spMedicationSupplies.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY));
        pnlMedicationSupplies.add(spMedicationSupplies);

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

    private JTable createSimpleTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(FontsTheme.Info_Texts);
        table.setForeground(ColorsTheme.Text_Black);
        table.setBackground(ColorsTheme.Main_Card);
        table.setRowHeight(35);
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
