/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import com.mycompany.hospitalmanage.*;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import controls.AdminDashboard;
import constants.SystemSettings;


/**
 *
 * 
 */
public class SettingsPanel extends JPanel implements ActionListener{
    
    private JPanel pnlGeneral, pnlAppearance, pnlSystem, TopPanel, cardPanel;
    private JLabel lblSettings, lblLine, lblSettingsDesc, lblTitle, lblValue, lblCurrency, lblDarkMode, lblDensity, lblLimit, lblLanding;
    private JTextField txtSearch;
    private JButton btnEnableDarkMode, btnSearch, btnRefresh, btnAdd, btnReset, btnSave;
    private JTable table;
    private JComboBox cmbDarkMode, cmbCurrency, cmbDensity, cmbLimit, cmbLanding;
    private JScrollPane scrollPane;
    
    
    
    public SettingsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Title and subtitle label for settings section
        lblSettings = new JLabel("Settings");
        lblSettings.setBounds(30, 30, 500, 40);
        lblSettings.setFont(FontsTheme.Bold_Texts);
        lblSettings.setForeground(ColorsTheme.Text_Black);
        add(lblSettings);

        lblSettingsDesc = new JLabel("Manage system preferences.");
        lblSettingsDesc.setBounds(30, 70, 500, 40);
        lblSettingsDesc.setFont(FontsTheme.Plain_Texts);
        lblSettingsDesc.setForeground(ColorsTheme.Text_Gray);
        add(lblSettingsDesc);
        
        
        // General Settings
        pnlGeneral = createCard("General Settings");
        pnlGeneral.setBounds(70, 130, 1500, 130);
        add(pnlGeneral);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlGeneral.setLayout(null);
        
        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlGeneral.add(lblLine);
        
        lblCurrency = new JLabel("Currency");
        lblCurrency.setBounds(30, 80, 150, 30);
        lblCurrency.setFont(FontsTheme.Plain_Texts);
        lblCurrency.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblCurrency); 
        
        cmbCurrency = new JComboBox<>(new String[]{
        " ", "USD - US Dollar", "PHP - Philippine Peso", "More Soon"
        });
        cmbCurrency.setBounds(190, 80, 200, 30);
        cmbCurrency.setFont(FontsTheme.Info_Texts);
        cmbCurrency.setForeground(ColorsTheme.Text_Black);
        cmbCurrency.setBackground(ColorsTheme.Text_White);
        pnlGeneral.add(cmbCurrency);
        
        
        //Appearance
        pnlAppearance = createCard("Appearance");
        pnlAppearance.setBounds(70, 280, 1500, 130);
        add(pnlAppearance);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlAppearance.setLayout(null);
        
        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlAppearance.add(lblLine);
        
        lblDarkMode = new JLabel("Dark Mode");
        lblDarkMode.setBounds(30, 80, 200, 30);
        lblDarkMode.setFont(FontsTheme.Plain_Texts);
        lblDarkMode.setForeground(ColorsTheme.Text_Black);
        pnlAppearance.add(lblDarkMode);
        
        cmbDarkMode = new JComboBox<>(new String[]{
        " ", "Enable", "Disable",
        });
        cmbDarkMode.setBounds(190, 80, 130, 30);
        cmbDarkMode.setFont(FontsTheme.Info_Texts);
        cmbDarkMode.setForeground(ColorsTheme.Text_Black);
        cmbDarkMode.setBackground(ColorsTheme.Text_White);
        cmbDarkMode.setSelectedItem(ColorsTheme.isDarkMode ? "Enable" : "Disable");
        pnlAppearance.add(cmbDarkMode);
        
        
        
//        lblName = new JLabel("Hospital Name");
//        lblName.setBounds(30, 80, 500, 30);
//        lblName.setFont(FontsTheme.Plain_Texts);
//        lblName.setForeground(ColorsTheme.Text_Black);
//        pnlAppearance.add(lblName);
//        
//        lblHospital = new JLabel("Put hospital name here");
//        lblHospital.setBounds(190, 80, 500, 30);
//        lblHospital.setFont(FontsTheme.Plain_Texts);
//        lblHospital.setForeground(ColorsTheme.Text_Black);
//        lblHospital.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
//        pnlAppearance.add(lblHospital);
//        
//        lblAddress = new JLabel("Address");
//        lblAddress.setBounds(30, 120, 100, 30);
//        lblAddress.setFont(FontsTheme.Plain_Texts);
//        lblAddress.setForeground(ColorsTheme.Text_Black);
//        pnlAppearance.add(lblAddress);
//        
//        lblHospitalAddress = new JLabel("Put hospital address here");
//        lblHospitalAddress.setBounds(190, 120, 500, 30);
//        lblHospitalAddress.setFont(FontsTheme.Plain_Texts);
//        lblHospitalAddress.setForeground(ColorsTheme.Text_Black);
//        lblHospitalAddress.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
//        pnlAppearance.add(lblHospitalAddress);
                // System Preferences
        pnlSystem = createCard("System Preferences");
        pnlSystem.setBounds(70, 430, 1500, 210);
        add(pnlSystem);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0, 0, 1500, 10);
        pnlSystem.setLayout(null);

        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlSystem.add(lblLine);

        // 1. Table Density / Row Height
        lblDensity = new JLabel("Table Density / Row Height");
        lblDensity.setBounds(30, 80, 250, 30);
        lblDensity.setFont(FontsTheme.Plain_Texts);
        lblDensity.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblDensity);

        cmbDensity = new JComboBox<>(new String[]{
            "Compact (35px)", "Standard (45px)", "Spacious (50px)"
        });
        cmbDensity.setBounds(320, 80, 200, 30);
        cmbDensity.setFont(FontsTheme.Info_Texts);
        cmbDensity.setForeground(ColorsTheme.Text_Black);
        cmbDensity.setBackground(ColorsTheme.Text_White);
        if (SystemSettings.tableRowHeight == 35) {
            cmbDensity.setSelectedItem("Compact (35px)");
        } else if (SystemSettings.tableRowHeight == 45) {
            cmbDensity.setSelectedItem("Standard (45px)");
        } else {
            cmbDensity.setSelectedItem("Spacious (50px)");
        }
        pnlSystem.add(cmbDensity);

        // 2. Dashboard Log Limit
        lblLimit = new JLabel("Dashboard Table Record Limit");
        lblLimit.setBounds(30, 120, 250, 30);
        lblLimit.setFont(FontsTheme.Plain_Texts);
        lblLimit.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblLimit);

        cmbLimit = new JComboBox<>(new Integer[]{10, 25, 50, 100});
        cmbLimit.setBounds(320, 120, 200, 30);
        cmbLimit.setFont(FontsTheme.Info_Texts);
        cmbLimit.setForeground(ColorsTheme.Text_Black);
        cmbLimit.setBackground(ColorsTheme.Text_White);
        cmbLimit.setSelectedItem(SystemSettings.dashboardRecordLimit);
        pnlSystem.add(cmbLimit);

        // 3. Default Landing Screen
        lblLanding = new JLabel("Default Landing Page");
        lblLanding.setBounds(30, 160, 250, 30);
        lblLanding.setFont(FontsTheme.Plain_Texts);
        lblLanding.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblLanding);

        cmbLanding = new JComboBox<>(new String[]{
            "Dashboard", "Patients", "Pharmacy", "Staff", "Reports"
        });
        cmbLanding.setBounds(320, 160, 200, 30);
        cmbLanding.setFont(FontsTheme.Info_Texts);
        cmbLanding.setForeground(ColorsTheme.Text_Black);
        cmbLanding.setBackground(ColorsTheme.Text_White);
        if ("patients".equals(SystemSettings.defaultLandingTab)) {
            cmbLanding.setSelectedItem("Patients");
        } else if ("pharmacy".equals(SystemSettings.defaultLandingTab)) {
            cmbLanding.setSelectedItem("Pharmacy");
        } else if ("staffManagement".equals(SystemSettings.defaultLandingTab)) {
            cmbLanding.setSelectedItem("Staff");
        } else if ("reports".equals(SystemSettings.defaultLandingTab)) {
            cmbLanding.setSelectedItem("Reports");
        } else {
            cmbLanding.setSelectedItem("Dashboard");
        }
        pnlSystem.add(cmbLanding);


        //Buttons
        btnReset = new JButton("Reset");
        btnReset.setBounds(1250  , 660, 130, 40); 
        btnReset.setFont(FontsTheme.Buttons);
        btnReset.setBackground(ColorsTheme.Text_Gray);
        btnReset.setForeground(ColorsTheme.Text_White);
        btnReset.setFocusPainted(false);
        add(btnReset);
        
        btnSave = new JButton("Save");
        btnSave.setBounds(1400, 660, 130, 40); 
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFocusPainted(false);
        add(btnSave);
        
        
        //ActionListener
        btnReset.addActionListener(this);
        btnSave.addActionListener(this);
    }

    
    //Panel Card but title only
    public JPanel createCard(String title) {
        
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);

        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblTitle);

        return cardPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReset) {
            cmbDarkMode.setSelectedItem("Disable");
            cmbDensity.setSelectedItem("Spacious (50px)");
            cmbLimit.setSelectedItem(50);
            cmbLanding.setSelectedItem("Dashboard");
        } 
        else if (e.getSource() == btnSave) {
            String selectedDarkMode = cmbDarkMode.getSelectedItem().toString().trim();

            boolean darkModeEnabled = SystemSettings.isDarkMode;
            if (selectedDarkMode.equals("Enable")) {
                darkModeEnabled = true;
            } else if (selectedDarkMode.equals("Disable")) {
                darkModeEnabled = false;
            }

            // Get Density / Row Height
            int rowHeight = 50;
            String selectedDensity = cmbDensity.getSelectedItem().toString();
            if (selectedDensity.startsWith("Compact")) {
                rowHeight = 35;
            } else if (selectedDensity.startsWith("Standard")) {
                rowHeight = 45;
            }

            // Get Dashboard Record Limit
            int recordLimit = (Integer) cmbLimit.getSelectedItem();

            // Get Default Landing Screen
            String landingTab = "dashboard";
            String selectedLanding = cmbLanding.getSelectedItem().toString();
            if ("Patients".equals(selectedLanding)) {
                landingTab = "patients";
            } else if ("Pharmacy".equals(selectedLanding)) {
                landingTab = "pharmacy";
            } else if ("Staff".equals(selectedLanding)) {
                landingTab = "staffManagement";
            } else if ("Reports".equals(selectedLanding)) {
                landingTab = "reports";
            }

            // Save settings persistently
            SystemSettings.saveSettings(darkModeEnabled, rowHeight, recordLimit, landingTab);

            JOptionPane.showMessageDialog(this, "Settings saved successfully! Reloading dashboard...", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Reload AdminDashboard
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame instanceof AdminDashboard) {
                AdminDashboard newDashboard = new AdminDashboard();
                newDashboard.setVisible(true);
                parentFrame.dispose();
            }
        }
    }
}
    

