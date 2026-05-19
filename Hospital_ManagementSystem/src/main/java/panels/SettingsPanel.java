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


/**
 *
 * 
 */
public class SettingsPanel extends JPanel implements ActionListener{
    
    private JPanel pnlGeneral, pnlAppearance, pnlSystem, TopPanel, cardPanel;
    private JLabel lblSettings, lblLine, lblHospital, lblHospitalAddress, lblHospitalPhone, lblHospitalEmail, lblSettingsDesc, lblTitle, lblValue, lblName, lblAddress, lblPhoneNumber, lblEmail, lblCurrency, lblDateFormat, lblTimeFormat, lblTheme, lblDarkMode, lblShowStats, lblAutoLogout, lblLogoutTime ;
    private JTextField txtSearch;
    private JButton btnEnableDarkMode, btnSearch, btnRefresh, btnAdd, btnReset, btnSave;
    private JTable table;
    private JComboBox cmbTheme, cmbDarkMode, cmbCurrency, cmbDateFormat, cmbTimeFormat, cmbStats, cmbAutoLogout, cmbAutoLogoutTime;
    private JScrollPane scrollPane;
    private String[] theme = {"Blue", "Red", "Green"},
            enable = {"Enable", "Disable"};
    private Integer[] time = {5, 10, 15, 20, 25, 30};
    
    
    public SettingsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
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
        pnlGeneral.setBounds(70, 130, 1500, 250);
        add(pnlGeneral);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlGeneral.setLayout(null);
        
        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlGeneral.add(lblLine);
        
        lblName = new JLabel("Hospital Name");
        lblName.setBounds(30, 80, 500, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblName);
        
        lblHospital = new JLabel(" ");
        lblHospital.setBounds(190, 80, 500, 30);
        lblHospital.setFont(FontsTheme.Plain_Texts);
        lblHospital.setForeground(ColorsTheme.Text_Black);
        lblHospital.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlGeneral.add(lblHospital);
        
        lblCurrency = new JLabel("Currency");
        lblCurrency.setBounds(800, 80, 500, 30);
        lblCurrency.setFont(FontsTheme.Plain_Texts);
        lblCurrency.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblCurrency); 
        

        cmbCurrency = new JComboBox<>(new String[]{
        " ", "USD - US Dollar", "PHP - Philippine Peso", "More Soon"
        });
        cmbCurrency.setBounds(940, 80, 200, 30);
        cmbCurrency.setFont(FontsTheme.Info_Texts);
        cmbCurrency.setForeground(ColorsTheme.Text_Black);
        cmbCurrency.setBackground(ColorsTheme.Text_White);
        pnlGeneral.add(cmbCurrency);
        
        
        
        lblDateFormat = new JLabel("Date Format");
        lblDateFormat.setBounds(800, 120, 500, 30);
        lblDateFormat.setFont(FontsTheme.Plain_Texts);
        lblDateFormat.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblDateFormat); 
        
        cmbDateFormat = new JComboBox<>(new String[]{
        " ", "YYYY / MM / DD", "MM / DD / YYYY", "DD / MM / YYYY"
        });
        cmbDateFormat.setBounds(940, 120, 200, 30);
        cmbDateFormat.setFont(FontsTheme.Info_Texts);
        cmbDateFormat.setForeground(ColorsTheme.Text_Black);
        cmbDateFormat.setBackground(ColorsTheme.Text_White);
        pnlGeneral.add(cmbDateFormat);
        
        
        lblTimeFormat = new JLabel("Time Format");
        lblTimeFormat.setBounds(800, 160, 500, 30);
        lblTimeFormat.setFont(FontsTheme.Plain_Texts);
        lblTimeFormat.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblTimeFormat); 
        
        cmbTimeFormat = new JComboBox<>(new String[]{
        " ", "12-Hour (AM/PM)", "24-Hour (Universal)"
        });
        cmbTimeFormat.setBounds(940, 160, 200, 30);
        cmbTimeFormat.setFont(FontsTheme.Info_Texts);
        cmbTimeFormat.setForeground(ColorsTheme.Text_Black);
        cmbTimeFormat.setBackground(ColorsTheme.Text_White);
        pnlGeneral.add(cmbTimeFormat);

                        
        lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 120, 100, 30);
        lblAddress.setFont(FontsTheme.Plain_Texts);
        lblAddress.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblAddress);
        
        lblHospitalAddress = new JLabel(" ");
        lblHospitalAddress.setBounds(190, 120, 500, 30);
        lblHospitalAddress.setFont(FontsTheme.Plain_Texts);
        lblHospitalAddress.setForeground(ColorsTheme.Text_Black);
        lblHospitalAddress.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlGeneral.add(lblHospitalAddress);
        
        lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(30, 160, 500, 30);
        lblPhoneNumber.setFont(FontsTheme.Plain_Texts);
        lblPhoneNumber.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblPhoneNumber);
        
        lblHospitalPhone = new JLabel(" ");
        lblHospitalPhone.setBounds(190, 160, 500, 30);
        lblHospitalPhone.setFont(FontsTheme.Plain_Texts);
        lblHospitalPhone.setForeground(ColorsTheme.Text_Black);
        lblHospitalPhone.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlGeneral.add(lblHospitalPhone);
        
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 200, 500, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblEmail);
        
        lblHospitalEmail = new JLabel(" ");
        lblHospitalEmail.setBounds(190, 200, 500, 30);
        lblHospitalEmail.setFont(FontsTheme.Plain_Texts);
        lblHospitalEmail.setForeground(ColorsTheme.Text_Black);
        lblHospitalEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlGeneral.add(lblHospitalEmail);
        
        // Appearance
        
        pnlAppearance = createCard("Appearance");
        pnlAppearance.setBounds(70, 400, 1500, 170);
        add(pnlAppearance);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlAppearance.setLayout(null);
        
        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlAppearance.add(lblLine);
        
        lblTheme = new JLabel("Theme Color");
        lblTheme.setBounds(30, 80, 200, 30);
        lblTheme.setFont(FontsTheme.Plain_Texts);
        lblTheme.setForeground(ColorsTheme.Text_Black);
        pnlAppearance.add(lblTheme);
        
        
        cmbTheme = new JComboBox<>(new String[]{
        " ", "Blue", "Red", "Green",
        });
        cmbTheme.setBounds(190, 80, 130, 30);
        cmbTheme.setFont(FontsTheme.Info_Texts);
        cmbTheme.setForeground(ColorsTheme.Text_Black);
        cmbTheme.setBackground(ColorsTheme.Text_White);
        pnlAppearance.add(cmbTheme);
        
        
        lblDarkMode = new JLabel("Dark Mode");
        lblDarkMode.setBounds(30, 120, 200, 30);
        lblDarkMode.setFont(FontsTheme.Plain_Texts);
        lblDarkMode.setForeground(ColorsTheme.Text_Black);
        pnlAppearance.add(lblDarkMode);
        
        
        cmbDarkMode = new JComboBox<>(new String[]{
        " ", "Enable", "Disable",
        });
        cmbDarkMode.setBounds(190, 120, 130, 30);
        cmbDarkMode.setFont(FontsTheme.Info_Texts);
        cmbDarkMode.setForeground(ColorsTheme.Text_Black);
        cmbDarkMode.setBackground(ColorsTheme.Text_White);
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
        
        // System
        
        pnlSystem = createCard("System");
        pnlSystem.setBounds(70, 590, 1500, 210);
        add(pnlSystem);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlSystem.setLayout(null);
        
        lblLine = new JLabel("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");
        lblLine.setBounds(0, 35, 1550, 40);
        lblLine.setForeground(Color.LIGHT_GRAY);
        pnlSystem.add(lblLine);
        
        lblShowStats = new JLabel("Show Dashboard Statistics");
        lblShowStats.setBounds(30, 80, 250, 30);
        lblShowStats.setFont(FontsTheme.Plain_Texts);
        lblShowStats.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblShowStats);
        
        // Can be changed to Checkbox
        cmbStats = new JComboBox<>(new String[]{
        " ", "Enable", "Disable",
        });
        cmbStats.setBounds(320, 80, 130, 30);
        cmbStats.setFont(FontsTheme.Info_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Text_White);
        pnlSystem.add(cmbStats);
        
        lblShowStats = new JLabel("Enable Auto Logout");
        lblShowStats.setBounds(30, 120, 250, 30);
        lblShowStats.setFont(FontsTheme.Plain_Texts);
        lblShowStats.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblShowStats);
        
        // Can be changed to Checkbox
        cmbStats = new JComboBox<>(new String[]{
        " ", "Enable", "Disable",
        });
        cmbStats.setBounds(320, 120, 130, 30);
        cmbStats.setFont(FontsTheme.Info_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Text_White);
        pnlSystem.add(cmbStats);
        
        
        lblAutoLogout = new JLabel("Auto Logout Time (Minutes)");
        lblAutoLogout.setBounds(30, 160, 250, 30);
        lblAutoLogout.setFont(FontsTheme.Plain_Texts);
        lblAutoLogout.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblAutoLogout);
        
        // Can be changed to Checkbox        
        cmbAutoLogoutTime = new JComboBox<>(new Integer[]{
         1, 5, 10, 15, 20, 25, 30,
        });
        cmbAutoLogoutTime.setBounds(320, 160, 130, 30);
        cmbAutoLogoutTime.setFont(FontsTheme.Info_Texts);
        cmbAutoLogoutTime.setForeground(ColorsTheme.Text_Black);
        cmbAutoLogoutTime.setBackground(ColorsTheme.Text_White);
        pnlSystem.add(cmbAutoLogoutTime);
        
        
        
        // BUTTONS
                
        btnReset = new JButton("Reset");
        btnReset.setBounds(1250  , 820, 130, 40); 
        btnReset.setFont(FontsTheme.Buttons);
        btnReset.setBackground(ColorsTheme.Text_Gray);
        btnReset.setForeground(ColorsTheme.Text_White);
        btnReset.setFocusPainted(false);
        add(btnReset);
        
        btnSave = new JButton("Save");
        btnSave.setBounds(1400, 820, 130, 40); 
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFocusPainted(false);
        add(btnSave);
        
        
        // ActionListener
        btnReset.addActionListener(this);
        btnSave.addActionListener(this);
                
                
    }

    
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
            
        } 
        
        else if (e.getSource() == btnSave) {
            
        }
        }
}
    

