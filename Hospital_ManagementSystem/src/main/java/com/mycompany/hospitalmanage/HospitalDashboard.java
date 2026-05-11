/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.CardLayout;

/**
 *
 * @author Arabella
 */
public class HospitalDashboard extends JFrame implements ActionListener {
    
    private JPanel SidePanel, TopPanel, ContainerPanel;
    private CardLayout cardLayout;
    private JButton btnDashboard, btnPatients, btnAppointments, btnMedRec, btnPrescription, btnLab, btnPharmacy,
            btnBed, btnBill, btnStaff, btnEmergency, btnReports, btnMessage, btnSettings;
    
    
    HospitalDashboard() {
        setSize(1920, 1080);
        setResizable(false); 
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panels
        SidePanel = new JPanel();
        SidePanel.setLayout(null);
        SidePanel.setBounds(0, 0, 270, 1080);
        SidePanel.setBackground(ColorsTheme.Side_Panel);
        add(SidePanel);
        
        TopPanel = new JPanel();
        TopPanel.setLayout(null);
        TopPanel.setBounds(250, 0, 1670, 100);
        TopPanel.setBackground(ColorsTheme.Side_Panel);
        add(TopPanel);
        
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
        ContainerPanel.add(new MessagesPanel(), "messages");
        ContainerPanel.add(new SettingsPanel(), "settings");
        add(ContainerPanel);

        //Button
        btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(40, 100, 220, 50);
        ButtonStyles.sidebarButton(btnDashboard);
        SidePanel.add(btnDashboard);
        
        btnPatients = new JButton("Patients");
        btnPatients.setBounds(40, 150, 220, 50);
        ButtonStyles.sidebarButton(btnPatients);
        SidePanel.add(btnPatients);
        
        btnAppointments = new JButton("Appointments");
        btnAppointments.setBounds(40, 200, 220, 50);
        ButtonStyles.sidebarButton(btnAppointments);
        SidePanel.add(btnAppointments);
        
        btnMedRec = new JButton("Medical Records");
        btnMedRec.setBounds(40, 250, 220, 50);
        ButtonStyles.sidebarButton(btnMedRec);
        SidePanel.add(btnMedRec);
        
        btnPrescription = new JButton("Prescriptions");
        btnPrescription.setBounds(40, 300, 220, 50);
        ButtonStyles.sidebarButton(btnPrescription);
        SidePanel.add(btnPrescription);
        
        btnLab = new JButton("Laboratory");
        btnLab.setBounds(40, 350, 220, 50);
        ButtonStyles.sidebarButton(btnLab);
        SidePanel.add(btnLab);
        
        btnPharmacy = new JButton("Pharmacy");
        btnPharmacy.setBounds(40, 400, 220, 50);
        ButtonStyles.sidebarButton(btnPharmacy);
        SidePanel.add(btnPharmacy);
        
        btnBed = new JButton("Bed Management");
        btnBed.setBounds(40, 450, 220, 50);
        ButtonStyles.sidebarButton(btnBed);
        SidePanel.add(btnBed);
        
        btnBill = new JButton("Billing");
        btnBill.setBounds(40, 500, 220, 50);
        ButtonStyles.sidebarButton(btnBill);
        SidePanel.add(btnBill);
        
        btnStaff = new JButton("Staff Management");
        btnStaff.setBounds(40, 550, 220, 50);
        ButtonStyles.sidebarButton(btnStaff);
        SidePanel.add(btnStaff);
        
        btnEmergency = new JButton("Emergency");
        btnEmergency.setBounds(40, 600, 220, 50);
        ButtonStyles.sidebarButton(btnEmergency);
        SidePanel.add(btnEmergency);
        
        btnReports = new JButton("Reports");
        btnReports.setBounds(40, 650, 220, 50);
        ButtonStyles.sidebarButton(btnReports);
        SidePanel.add(btnReports);
        
        btnMessage = new JButton("Messages");
        btnMessage.setBounds(40, 700, 220, 50);
        ButtonStyles.sidebarButton(btnMessage);
        SidePanel.add(btnMessage);
        
        btnSettings = new JButton("Settings");
        btnSettings.setBounds(40, 750, 220, 50);
        ButtonStyles.sidebarButton(btnSettings);
        SidePanel.add(btnSettings);
        
        
        
        
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
        btnMessage.addActionListener(this);
        btnSettings.addActionListener(this);
        
        
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
        
        else if (e.getSource() == btnMessage) {
            cardLayout.show(ContainerPanel, "messages");
        }
        
        else if (e.getSource() == btnSettings) {
            cardLayout.show(ContainerPanel, "settings");
        }
    }
}




