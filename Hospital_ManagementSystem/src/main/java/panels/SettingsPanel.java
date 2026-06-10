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
        styleComboBox(cmbCurrency);
        if ("USD".equals(SystemSettings.currency)) {
            cmbCurrency.setSelectedItem("USD - US Dollar");
        } else if ("PHP".equals(SystemSettings.currency)) {
            cmbCurrency.setSelectedItem("PHP - Philippine Peso");
        } else {
            cmbCurrency.setSelectedItem(" ");
        }
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
        styleComboBox(cmbDarkMode);
        cmbDarkMode.setSelectedItem(SystemSettings.isDarkMode ? "Enable" : "Disable");
        pnlAppearance.add(cmbDarkMode);
        
        
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
        styleComboBox(cmbDensity);
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
        styleComboBox(cmbLimit);
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
        styleComboBox(cmbLanding);
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

    // Helper method to style combo boxes to render correctly in Dark Mode
    private void styleComboBox(JComboBox<?> cmb) {
        cmb.setForeground(ColorsTheme.Text_Black);
        cmb.setBackground(ColorsTheme.Main_Card);
        cmb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                java.awt.Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
                    c.setForeground(ColorsTheme.Text_Black);
                } else {
                    c.setBackground(ColorsTheme.Main_Card);
                    c.setForeground(ColorsTheme.Text_Black);
                }
                return c;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReset) {
            cmbDarkMode.setSelectedItem("Disable");
            cmbCurrency.setSelectedItem("PHP - Philippine Peso");
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

            // Get Currency
            String selectedCurrency = "PHP";
            String currencyString = cmbCurrency.getSelectedItem().toString();
            if (currencyString.startsWith("USD")) {
                selectedCurrency = "USD";
            }

            // Save settings persistently
            SystemSettings.saveSettings(darkModeEnabled, rowHeight, recordLimit, landingTab, selectedCurrency);

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
    

