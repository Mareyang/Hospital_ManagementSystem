/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.hospitalmanage.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Arabella
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
            enable = {"Enable", "Disable"},
            currency = {"USD - US Dollar", "PHP - Philippine Peso", "More Soon"},
            dateFormat = {"YYYY / MM / DD", "MM / DD / YYYY", "DD / MM / YYYY"},
            timeFormat = {"12-Hour (AM/PM)", "24-Hour (Universal)"};
    private Integer[] time = {5, 10, 15, 20, 25, 30};
    
    
    SettingsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        lblSettings = new JLabel("Settings");
        lblSettings.setBounds(30, 30, 500, 40);
        lblSettings.setFont(FontsTheme.Bold_Texts);
        lblSettings.setForeground(ColorsTheme.Text_Black);
        add(lblSettings);

        lblSettingsDesc = new JLabel("Manage system preferences");
        lblSettingsDesc.setBounds(30, 70, 500, 40);
        lblSettingsDesc.setFont(FontsTheme.Plain_Texts);
        lblSettingsDesc.setForeground(ColorsTheme.Text_Black);
        add(lblSettingsDesc);
        
        // GENERAL SETTINGS
        
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
        
        lblHospital = new JLabel("placeholder");
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
        
        cmbDateFormat = new JComboBox<String>(currency);
        cmbDateFormat.setBounds(940, 80, 200, 30);
        cmbDateFormat.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbDateFormat.setOpaque(false);
        cmbDateFormat.setFocusable(false);
        cmbDateFormat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlGeneral.add(cmbDateFormat);
        
        lblDateFormat = new JLabel("Date Format");
        lblDateFormat.setBounds(800, 120, 500, 30);
        lblDateFormat.setFont(FontsTheme.Plain_Texts);
        lblDateFormat.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblDateFormat); 
        
        cmbCurrency = new JComboBox<String>(dateFormat);
        cmbCurrency.setBounds(940, 120, 200, 30);
        cmbCurrency.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbCurrency.setOpaque(false);
        cmbCurrency.setFocusable(false);
        cmbCurrency.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlGeneral.add(cmbCurrency);
        
        lblTimeFormat = new JLabel("Time Format");
        lblTimeFormat.setBounds(800, 160, 500, 30);
        lblTimeFormat.setFont(FontsTheme.Plain_Texts);
        lblTimeFormat.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblTimeFormat); 
        
        cmbTimeFormat = new JComboBox<String>(timeFormat);
        cmbTimeFormat.setBounds(940, 160, 200, 30);
        cmbTimeFormat.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbTimeFormat.setOpaque(false);
        cmbTimeFormat.setFocusable(false);
        cmbTimeFormat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlGeneral.add(cmbTimeFormat);
        
        
        
        lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 120, 100, 30);
        lblAddress.setFont(FontsTheme.Plain_Texts);
        lblAddress.setForeground(ColorsTheme.Text_Black);
        pnlGeneral.add(lblAddress);
        
        lblHospitalAddress = new JLabel("placeholder");
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
        
        lblHospitalPhone = new JLabel("placeholder");
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
        
        lblHospitalEmail = new JLabel("placeholder");
        lblHospitalEmail.setBounds(190, 200, 500, 30);
        lblHospitalEmail.setFont(FontsTheme.Plain_Texts);
        lblHospitalEmail.setForeground(ColorsTheme.Text_Black);
        lblHospitalEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlGeneral.add(lblHospitalEmail);
        
        // APPEARANCE
        
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
        
        cmbTheme = new JComboBox<String>(theme);
        cmbTheme.setBounds(190, 80, 130, 30);
        cmbTheme.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbTheme.setOpaque(false);
        cmbTheme.setFocusable(false);
        cmbTheme.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAppearance.add(cmbTheme);
        
        lblDarkMode = new JLabel("Dark Mode");
        lblDarkMode.setBounds(30, 120, 200, 30);
        lblDarkMode.setFont(FontsTheme.Plain_Texts);
        lblDarkMode.setForeground(ColorsTheme.Text_Black);
        pnlAppearance.add(lblDarkMode);
        
        cmbDarkMode = new JComboBox<String>(enable);
        cmbDarkMode.setBounds(190, 120, 130, 30);
        cmbDarkMode.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbDarkMode.setOpaque(false);
        cmbDarkMode.setFocusable(false);
        cmbDarkMode.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
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
        
        // SYSTEM
        
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
        cmbStats = new JComboBox<String>(enable);
        cmbStats.setBounds(320, 80, 130, 30);
        cmbStats.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbStats.setOpaque(false);
        cmbStats.setFocusable(false);
        cmbStats.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlSystem.add(cmbStats);
        
        lblShowStats = new JLabel("Enable Auto Logout");
        lblShowStats.setBounds(30, 120, 250, 30);
        lblShowStats.setFont(FontsTheme.Plain_Texts);
        lblShowStats.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblShowStats);
        
        // Can be changed to Checkbox
        cmbStats = new JComboBox<String>(enable);
        cmbStats.setBounds(320, 120, 130, 30);
        cmbStats.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbStats.setOpaque(false);
        cmbStats.setFocusable(false);
        cmbStats.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlSystem.add(cmbStats);
        
        lblAutoLogout = new JLabel("Auto Logout Time (Minutes)");
        lblAutoLogout.setBounds(30, 160, 250, 30);
        lblAutoLogout.setFont(FontsTheme.Plain_Texts);
        lblAutoLogout.setForeground(ColorsTheme.Text_Black);
        pnlSystem.add(lblAutoLogout);
        
        // Can be changed to Checkbox
        cmbAutoLogoutTime = new JComboBox<Integer>(time);
        cmbAutoLogoutTime.setBounds(320, 160, 130, 30);
        cmbAutoLogoutTime.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbAutoLogoutTime.setOpaque(false);
        cmbAutoLogoutTime.setFocusable(false);
        cmbAutoLogoutTime.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlSystem.add(cmbAutoLogoutTime);
        
        // BUTTONS
                
        btnSave = new JButton("Reset");
        btnSave.setBounds(1250  , 820, 130, 40); 
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Text_Gray);
        btnSave.setForeground(ColorsTheme.Text_White);
        add(btnSave);
        
        btnReset = new JButton("Save");
        btnReset.setBounds(1400, 820, 130, 40); 
        btnReset.setFont(FontsTheme.Buttons);
        btnReset.setBackground(ColorsTheme.Search_Button);
        btnReset.setForeground(ColorsTheme.Text_White);
        add(btnReset);
        
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
//    
//        public JPanel roomCard (String title, String value) {
//            
//        cardPanel = new JPanel();
//        cardPanel.setLayout(null);
//        cardPanel.setBackground(ColorsTheme.Header);
//        
//        TopPanel = new JPanel();
//        TopPanel.setBounds(0, 0, 1490, 10);
//        TopPanel.setBackground(ColorsTheme.Header);
//        cardPanel.add(TopPanel);
//
//        //Title
//        lblTitle = new JLabel(title);
//        lblTitle.setBounds(20, 25, 250, 25);
//        lblTitle.setForeground(Color.white);
//        lblTitle.setFont(FontsTheme.Bold_Texts);
//        cardPanel.add(lblTitle);
//
//        //Value
//        lblValue = new JLabel(value);
//        lblValue.setBounds(20, 50, 200, 50);
//        lblValue.setForeground(ColorsTheme.Text_Black);
//        lblValue.setFont(FontsTheme.Bold_Texts);
//        cardPanel.add(lblValue);
//        
//        return cardPanel;
//    
//        }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        }
    
    

