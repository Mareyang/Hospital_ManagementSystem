/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import constants.ButtonStyles;
import constants.ColorsTheme;
import panels.MessagesPanel;
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
<<<<<<< HEAD
            btnBed, btnBill, btnStaff, btnEmergency, btnReports, btnMessage, btnSettings, btnSearch, btnNotification, btnAdmin;
    private JLabel lblSystemName, lblDateTime, lblCalendarIcon;
    private JTextField txtSearchField;
    private JPopupMenu adminMenu;
    private JMenuItem settings, logout, switchUser;
=======
            btnBed, btnBill, btnStaff, btnEmergency, btnReports, btnSettings, btnLogout;
    private JLabel lblSystemName;
    private JTextField txtSearchField;
    private JPanel dashboardPanel;
    private JLabel dashboardIconLbl, dashboardTxtLbl;
>>>>>>> main
    
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
        lblSystemName = new JLabel("Carelink Management System");

        lblSystemName.setBounds(125, 25, 800, 60);

        lblSystemName.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblSystemName.setForeground(Color.WHITE);

        TopPanel.add(lblSystemName);
        
        
        //With removable text
        String placeholder = "Search patients, records, appointments...";

        txtSearchField = new JTextField(placeholder);
        txtSearchField.setBounds(750, 30, 500, 40);
        txtSearchField.setFont(new Font("Arial", Font.PLAIN, 18));

        txtSearchField.setForeground(new Color(200, 200, 200)); // placeholder color

        txtSearchField.setBackground(Color.decode("#3A2A75"));
        txtSearchField.setCaretColor(Color.WHITE);
        txtSearchField.setOpaque(true);
        txtSearchField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        txtSearchField.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            if (txtSearchField.getText().equals(placeholder)) {
                txtSearchField.setText("");
                txtSearchField.setForeground(Color.WHITE);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {

            if (txtSearchField.getText().isEmpty()) {
                txtSearchField.setText(placeholder);
                txtSearchField.setForeground(new Color(200, 200, 200));
            }
        }
    });
        
        
        // add to panel
        TopPanel.add(txtSearchField);
        
        
<<<<<<< HEAD
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/search.png"));

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnSearch = new JButton(scaledIcon); // use your class variable
        btnSearch.setBounds(710, 30, 40, 40);
        btnSearch.setBackground(Color.decode("#3A2A75"));
        btnSearch.setBorder(BorderFactory.createEmptyBorder());
        

        TopPanel.add(btnSearch);
        
        lblDateTime = new JLabel();
        lblDateTime.setBounds(1350, 30, 400, 40);
        lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDateTime.setForeground(Color.WHITE);
        
        Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("MMM dd, yyyy || HH:mm:ss");

            lblDateTime.setText(sdf.format(new java.util.Date()));
            }
        });
        timer.start();

        TopPanel.add(lblDateTime);
        
        ImageIcon calendarIcon = new ImageIcon(getClass().getResource("/icons/calendar.png"));

        Image calImg = calendarIcon.getImage();
        Image scaledCalImg = calImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledCalendarIcon = new ImageIcon(scaledCalImg);

        lblCalendarIcon = new JLabel(scaledCalendarIcon);
        lblCalendarIcon.setBounds(1315, 30, 30, 40);

        TopPanel.add(lblCalendarIcon);
        
        
        // Notification Icon
        // Notification Button Icon
        ImageIcon notifIcon = new ImageIcon(getClass().getResource("/icons/notification.png"));

        Image notifImg = notifIcon.getImage();
        Image scaledNotifImg = notifImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledNotifIcon = new ImageIcon(scaledNotifImg);

        btnNotification = new JButton(scaledNotifIcon);
        btnNotification.setBounds(1580, 30, 40, 40);

        btnNotification.setBackground(ColorsTheme.Side_Panel);
        btnNotification.setBorder(BorderFactory.createEmptyBorder());
        

        TopPanel.add(btnNotification);
        
        // Admin Button
        // ICON (separate from button)
        ImageIcon adminIcon = new ImageIcon(getClass().getResource("/icons/admin.png"));

        Image adminImg = adminIcon.getImage();

        // increase size here
        Image scaledAdminImg = adminImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JLabel lblAdminIcon = new JLabel(new ImageIcon(scaledAdminImg));

        // IMPORTANT: match bounds to size
        lblAdminIcon.setBounds(1675, 20, 60, 60);

        TopPanel.add(lblAdminIcon);

        


        // BUTTON (text only)
        btnAdmin = new JButton("<html><b>Admin</b><br><span style='font-size:9px;'>Administrator</span></html>");
        btnAdmin.setBounds(1730, 30, 120, 40);

        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setBackground(new Color(58, 42, 117));
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBorder(BorderFactory.createEmptyBorder());
        btnAdmin.addActionListener(this);

        TopPanel.add(btnAdmin);


        // POPUP MENU
        adminMenu = new JPopupMenu();

        switchUser = new JMenuItem("Switch User");
        settings = new JMenuItem("Settings");
        logout = new JMenuItem("Logout");
        
        switchUser.addActionListener(this);
        settings.addActionListener(this);
        logout.addActionListener(this);

        adminMenu.add(switchUser);
        adminMenu.add(settings);
        adminMenu.addSeparator();
        adminMenu.add(logout);
        
        //logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/logo.png"));

        Image logoImg = logoIcon.getImage();

        // 🔥 BIGGER LOGO (more brand-like)
        Image scaledLogo = logoImg.getScaledInstance(140, 140, Image.SCALE_SMOOTH);

        JLabel lblLogo = new JLabel(new ImageIcon(scaledLogo));

        // better vertical centering in 100px top panel
        lblLogo.setBounds(10, 5, 120, 120);

        TopPanel.add(lblLogo);
        
        
        
        
=======
>>>>>>> main
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

        btnDashboard = createButton("Dashboard", "/icons/home.png", 100);        
        btnPatients = createButton("Patients", "/icons/patient.png", 150);
        btnAppointments = createButton("Appointments", "/icons/appointment.png", 200);
        btnMedRec = createButton("Medical Records", "/icons/record.png", 250);
        btnPrescription = createButton("Prescriptions", "/icons/prescription.png", 300);
        btnLab = createButton("Laboratory", "/icons/laboratory.png", 350);
        btnPharmacy = createButton("Pharmacy", "/icons/pharmacy2.png", 400);
        btnBed = createButton("Bed Management", "/icons/bed.png", 400);
        btnBill = createButton("Bill", "/icons/bill.png", 450);
        btnStaff = createButton("Staff", "/icons/staff.png", 500);
        btnEmergency = createButton("Emergency", "/icons/emergency.png", 550);
        btnReports = createButton("Reports", "/icons/report.png", 600);
        btnSettings = createButton("Settings", "/icons/setting.png", 650);
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
    
    
        @Override
        public void actionPerformed(ActionEvent c) {

            // SIDEBAR NAVIGATION
            if (c.getSource() == btnDashboard) {
                cardLayout.show(ContainerPanel, "dashboard");
            }
            else if (c.getSource() == btnPatients) {
                cardLayout.show(ContainerPanel, "patients");
            }
            else if (c.getSource() == btnAppointments) {
                cardLayout.show(ContainerPanel, "appointments");
            }
            else if (c.getSource() == btnMedRec) {
                cardLayout.show(ContainerPanel, "medicalRecords");
            }
            else if (c.getSource() == btnPrescription) {
                cardLayout.show(ContainerPanel, "prescriptions");
            }
            else if (c.getSource() == btnLab) {
                cardLayout.show(ContainerPanel, "laboratory");
            }
            else if (c.getSource() == btnPharmacy) {
                cardLayout.show(ContainerPanel, "pharmacy");
            }
            else if (c.getSource() == btnBed) {
                cardLayout.show(ContainerPanel, "bedManagement");
            }
            else if (c.getSource() == btnBill) {
                cardLayout.show(ContainerPanel, "billing");
            }
            else if (c.getSource() == btnStaff) {
                cardLayout.show(ContainerPanel, "staffManagement");
            }
            else if (c.getSource() == btnEmergency) {
                cardLayout.show(ContainerPanel, "emergency");
            }
            else if (c.getSource() == btnReports) {
                cardLayout.show(ContainerPanel, "reports");
            }
            else if (c.getSource() == btnMessage) {
                cardLayout.show(ContainerPanel, "messages");
            }
            else if (c.getSource() == btnSettings) {
                cardLayout.show(ContainerPanel, "settings");
            }

            // ADMIN BUTTON
            else if (c.getSource() == btnAdmin) {
                adminMenu.show(btnAdmin, 0, btnAdmin.getHeight());
            }

            // ADMIN MENU ITEMS
            else if (c.getSource() == logout) {
                System.out.println("Logging out...");
            }
            else if (c.getSource() == settings) {
                System.out.println("Opening settings...");
            }
            else if (c.getSource() == switchUser) {
                System.out.println("Switching user...");
            }
        }
        }
<<<<<<< HEAD
=======
       
        
        else if (e.getSource() == btnSettings) {
            cardLayout.show(ContainerPanel, "settings");
        }
        else if(e.getSource() == btnLogout)
        {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        }
    }
}
>>>>>>> main




