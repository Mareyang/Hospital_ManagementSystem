/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import constants.ButtonStyles;
import constants.ColorsTheme;
//import panels.MessagesPanel;
import panels.ReportsPanel;
import panels.BedManagementPanel;
import panels.MedicalRecordsPanel;
import panels.PharmacyPanel;
import panels.PrescriptionsPanel;
import panels.EmergencyPanel;
import panels.AppointmentsPanel;
import panels.DashboardPanel;
import panels.PatientsPanel;
import panels.StaffManagementPanel;
import panels.SettingsPanel;
import panels.LaboratoryPanel;
import panels.BillingPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;
import javax.swing.border.Border;

/**
 *
 * @author Arabella
 */
public class HospitalDashboard extends JFrame implements ActionListener {
    
    private JPanel SidePanel, TopPanel, ContainerPanel;
    private CardLayout cardLayout;
    private JButton btnDashboard, btnPatients, btnAppointments, btnMedRec, btnPrescription, btnLab, btnPharmacy,
            btnBed, btnBill, btnStaff, btnEmergency, btnReports, btnSettings, btnLogout;
    private JLabel lblSystemName;
    private JTextField txtSearchField;
    private JPanel dashboardPanel;
    private JLabel dashboardIconLbl, dashboardTxtLbl;
    
    HospitalDashboard() {
        setSize(1920, 1080);
        setResizable(false); 
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panels
        SidePanel = new JPanel();
        SidePanel.setLayout(null);
        SidePanel.setBounds(0, 100, 270, 1080);
        SidePanel.setBackground(ColorsTheme.Side_Panel);
        add(SidePanel);
        
        TopPanel = new JPanel();
        TopPanel.setLayout(null);
        TopPanel.setBounds(0, 0, 1920, 100);
        TopPanel.setBackground(ColorsTheme.Side_Panel);
        add(TopPanel);
        
        //TopPanel Inside
        JLabel lblSystemName = new JLabel("Carelink Management System");
        lblSystemName.setBounds(120, 30, 600, 40);
        lblSystemName.setFont(new Font("Tahoma", Font.BOLD, 34));
        lblSystemName.setForeground(Color.WHITE);
        TopPanel.add(lblSystemName);
        
        
        //With removable text
        String placeholder = "Search patients, records, appointments...";

        txtSearchField = new JTextField(placeholder);
        txtSearchField.setBounds(700, 30, 500, 40);
        txtSearchField.setFont(new Font("Arial", Font.PLAIN, 18));

        txtSearchField.setForeground(new Color(200, 200, 200)); // placeholder color

        txtSearchField.setBackground(Color.decode("#3A2A75"));
        txtSearchField.setCaretColor(Color.WHITE);
        txtSearchField.setOpaque(true);
        txtSearchField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        txtSearchField.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtSearchField.getText().equals(placeholder)) {
                    txtSearchField.setText("");
                    txtSearchField.setForeground(Color.WHITE);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtSearchField.getText().isEmpty()) {
                    txtSearchField.setText(placeholder);
                    txtSearchField.setForeground(new Color(200, 200, 200));
                }
            }
        });
        
        //to avoid jtextfield focus
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowOpened(java.awt.event.WindowEvent e) {
            TopPanel.requestFocusInWindow();
            }
        });

        SwingUtilities.invokeLater(() -> {
            TopPanel.requestFocusInWindow();
        });
        
        
        // add to panel
        TopPanel.add(txtSearchField);
        
        
        //CardLayout
        cardLayout = new CardLayout();
        ContainerPanel = new JPanel(cardLayout);
        ContainerPanel.setBounds(270, 100, 1650, 980);
        ContainerPanel.setBackground(ColorsTheme.Middle_Panel);

        //AddPage
        ContainerPanel.add(new DashboardPanel(), "dashboard");
        ContainerPanel.add(new PatientsPanel(), "patients");
        ContainerPanel.add(new AppointmentsPanel(), "appointments");
        ContainerPanel.add(new MedicalRecordsPanel(), "medicalRecords");
        ContainerPanel.add(new PrescriptionsPanel(), "prescriptions");
        ContainerPanel.add(new LaboratoryPanel(), "laboratory");
        ContainerPanel.add(new PharmacyPanel(), "pharmacy");
        ContainerPanel.add(new BedManagementPanel(), "bedManagement");
        ContainerPanel.add(new BillingPanel(), "billing");
        ContainerPanel.add(new StaffManagementPanel(), "staffManagement");
        ContainerPanel.add(new EmergencyPanel(), "emergency");
        ContainerPanel.add(new ReportsPanel(), "reports");
        //ContainerPanel.add(new MessagesPanel(), "messages");
        ContainerPanel.add(new SettingsPanel(), "settings");
        add(ContainerPanel);

        btnDashboard = createButton("Dashboard", "/icons/home.png", 100);        
        btnPatients = createButton("Patients", "/icons/patient.png", 150);
        btnAppointments = createButton("Appointments", "/icons/appointment.png", 200);
        btnMedRec = createButton("Medical", "/icons/record.png", 250);
        btnPrescription = createButton("Prescriptions", "/icons/prescription.png", 300);
        btnLab = createButton("Laboratory", "/icons/laboratory.png", 350);
        btnPharmacy = createButton("Pharmacy", "/icons/pharmacy2.png", 400);
        btnBed = createButton("Bed Management", "/icons/bed.png", 450);
        btnBill = createButton("Bill", "/icons/bill.png", 500);
        btnStaff = createButton("Staff", "/icons/staff.png", 550);
        btnEmergency = createButton("Emergency", "/icons/emergency.png", 600);
        btnReports = createButton("Reports", "/icons/report.png", 650);
        btnSettings = createButton("Settings", "/icons/setting.png", 700);
        btnLogout = createButton("Logout", "/icons/logout.png", 850);

        //ActionListener
        btnDashboard.addActionListener(this);
        btnPatients.addActionListener(this);
        btnAppointments.addActionListener(this);
        btnMedRec.addActionListener(this);
        btnPrescription.addActionListener(this);
        btnLab.addActionListener(this);
        btnPharmacy.addActionListener(this);
        btnBed.addActionListener(this);
        btnBill.addActionListener(this);
        btnStaff.addActionListener(this);
        btnEmergency.addActionListener(this);
        btnReports.addActionListener(this);
//        btnMessage.addActionListener(this);
        btnSettings.addActionListener(this);
        btnLogout.addActionListener(this);
        
        
    }
    
    public JButton createButton(String text, String path, int y)
{
    JPanel panel1 = new JPanel();
    panel1.setLayout(null);
    panel1.setBounds(0, y, 220, 50);
    panel1.setBackground(ColorsTheme.Side_Panel);

    JLabel iconLbl = new JLabel(
            new ImageIcon(getClass().getResource(path))
    );
    iconLbl.setBounds(18, 9, 32, 32);
    panel1.add(iconLbl);

    JButton btnClick = new JButton(text);
    btnClick.setBounds(60, 0, 150, 50);

    ButtonStyles.sidebarButton(btnClick);

    panel1.add(btnClick);

    SidePanel.add(panel1);

    return btnClick;
}
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDashboard) {
        cardLayout.show(ContainerPanel, "dashboard");
        }
       
        else if (e.getSource() == btnPatients) {
            cardLayout.show(ContainerPanel, "patients");
        }

        else if (e.getSource() == btnAppointments) {
            cardLayout.show(ContainerPanel, "appointments");
        }
        
        else if (e.getSource() == btnMedRec) {
            cardLayout.show(ContainerPanel, "medicalRecords");
        }
        
        else if (e.getSource() == btnPrescription) {
            cardLayout.show(ContainerPanel, "prescriptions");
        }
        
        else if (e.getSource() == btnLab) {
            cardLayout.show(ContainerPanel, "laboratory");
        }

        else if (e.getSource() == btnPharmacy) {
            cardLayout.show(ContainerPanel, "pharmacy");
        }
        
        else if (e.getSource() == btnBed) {
            cardLayout.show(ContainerPanel, "bedManagement");
        }
        
        else if (e.getSource() == btnBill) {
            cardLayout.show(ContainerPanel, "billing");
        }
        
        else if (e.getSource() == btnStaff) {
            cardLayout.show(ContainerPanel, "staffManagement");
        }

        else if (e.getSource() == btnEmergency) {
            cardLayout.show(ContainerPanel, "emergency");
        }
        
        else if (e.getSource() == btnReports) {
            cardLayout.show(ContainerPanel, "reports");
        }
       
        
        else if (e.getSource() == btnSettings) {
            cardLayout.show(ContainerPanel, "settings");
        }
        else if(e.getSource() == btnLogout)
        {
            dispose();
            LoginPage lp = new LoginPage();
            lp.setVisible(true);
        }
    }
}




