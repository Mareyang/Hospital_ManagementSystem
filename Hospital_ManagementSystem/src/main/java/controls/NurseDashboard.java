/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controls;

import controls.LoginPage;
import constants.ButtonStyles;
import constants.ColorsTheme;
import constants.FontsTheme;
import panels.NurseDashboardPanel;
import panels.PatientsPanel;
import panels.AppointmentsPanel;
import panels.MedicalRecordsPanel;
import panels.PharmacyPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;

/**
 *
 * @author Arabella
 */
public class NurseDashboard extends JFrame implements ActionListener {
    
    private JPanel pnlSide, pnlTop, pnlContainer, pnlLogo;
    private CardLayout cardLayout;
    private JButton btnDashboard, btnPatients, btnAppointments, btnMedicalRecords, btnPharmacy, btnLogout;
    private JLabel lblSystemName, lblLogo;
    private JTextField txtSearchField;
    private ImageIcon logoIcon;
    private JButton activeBtn = null;
    
    
    
    public NurseDashboard() {
        setSize(1920, 1080);
        setResizable(false); 
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main layout panels
        pnlSide = new JPanel();
        pnlSide.setLayout(null);
        pnlSide.setBounds(0, 100, 270, 1080);
        pnlSide.setBackground(ColorsTheme.Side_Panel);
        add(pnlSide);
        
        pnlTop = new JPanel();
        pnlTop.setLayout(null);
        pnlTop.setBounds(0, 0, 1920, 100);
        pnlTop.setBackground(ColorsTheme.Side_Panel);
        add(pnlTop);
        
        cardLayout = new CardLayout();
        pnlContainer = new JPanel(cardLayout);
        pnlContainer.setBounds(270, 100, 1650, 980);
        pnlContainer.setBackground(ColorsTheme.Middle_Panel);
        add(pnlContainer);

        // Add items to the top panel
        // Logo setup
        pnlLogo = new JPanel();
        pnlLogo.setLayout(null);
        pnlLogo.setBounds(-20, 0, 120, 120);
        pnlLogo.setBackground(ColorsTheme.Side_Panel);

        logoIcon = new ImageIcon(getClass().getResource("/icons/logo.png"));
        Image logoImg = logoIcon.getImage();
        Image scaledLogo = logoImg.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        lblLogo = new JLabel(new ImageIcon(scaledLogo));
        lblLogo.setBounds(10, 0, 140, 130);
        pnlLogo.add(lblLogo);
        pnlTop.add(pnlLogo);
        
        // System title
        lblSystemName = new JLabel("Carelink Management System");
        lblSystemName.setBounds(110, 25, 600, 50);
        lblSystemName.setFont(FontsTheme.Dashboard_Title);
        lblSystemName.setForeground(ColorsTheme.Main_Card);
        pnlTop.add(lblSystemName);
        
        // Search bar setup
        String placeholder = "Search patients, records, staff...";

        txtSearchField = new JTextField(placeholder);
        txtSearchField.setBounds(730, 30, 500, 40);
        txtSearchField.setFont(FontsTheme.SearchBar);
        txtSearchField.setForeground(ColorsTheme.Search_fg); 
        txtSearchField.setBackground(ColorsTheme.Search_bg);
        txtSearchField.setCaretColor(ColorsTheme.Main_Card);
        txtSearchField.setOpaque(true);
        txtSearchField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        // Make placeholder text disappear when clicked
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
                    txtSearchField.setForeground(ColorsTheme.Search_fg);
                }
            }
        });
        pnlTop.add(txtSearchField);

        // Add the different screens (Pages)
        pnlContainer.add(new NurseDashboardPanel(), "dashboard");
        pnlContainer.add(new PatientsPanel(true), "patients");
        pnlContainer.add(new AppointmentsPanel(true, false), "appointments");
        pnlContainer.add(new MedicalRecordsPanel(), "medicalRecords");
        pnlContainer.add(new PharmacyPanel(true), "pharmacy");
        
        // Add navigation buttons to the side panel
        btnDashboard      = ButtonStyles.createButton("Dashboard", "/icons/home.png", 30, pnlSide);        
        btnPatients       = ButtonStyles.createButton("Patients", "/icons/patient.png", 80, pnlSide);
        btnAppointments   = ButtonStyles.createButton("Appointments", "/icons/appointment.png", 130, pnlSide);
        btnMedicalRecords = ButtonStyles.createButton("Records", "/icons/record.png", 180, pnlSide);
        btnPharmacy       = ButtonStyles.createButton("Pharmacy", "/icons/pharmacy2.png", 230, pnlSide);
        btnLogout         = ButtonStyles.createButton("Logout", "/icons/logout.png", 850, pnlSide);

        // Make the buttons clickable
        btnDashboard.addActionListener(this);
        btnPatients.addActionListener(this);
        btnAppointments.addActionListener(this);
        btnMedicalRecords.addActionListener(this);
        btnPharmacy.addActionListener(this);
        btnLogout.addActionListener(this);

        // Stop the search bar from blinking immediately when the app opens
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                pnlTop.requestFocusInWindow();
            }
        });

        SwingUtilities.invokeLater(() -> {
            pnlTop.requestFocusInWindow();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        // Change button color when clicked (unless it's the logout button)
        if (clicked != btnLogout) {
            
            // Reset the old button back to the normal color
            if (activeBtn != null) {
                activeBtn.setBackground(ColorsTheme.Side_Panel);
                activeBtn.getParent().setBackground(ColorsTheme.Side_Panel);
            }
            
            // Color the newly clicked button
            clicked.setBackground(ColorsTheme.Active_Button);
            clicked.getParent().setBackground(ColorsTheme.Active_Button);
            
            // Remember which button is currently active
            activeBtn = clicked;
        }
        
        // Switch screens based on which button was clicked
        if (e.getSource() == btnDashboard) {
            cardLayout.show(pnlContainer, "dashboard");
        }
        else if (e.getSource() == btnPatients) {
            cardLayout.show(pnlContainer, "patients");
        }
        else if (e.getSource() == btnAppointments) {
            cardLayout.show(pnlContainer, "appointments");
        }
        else if (e.getSource() == btnMedicalRecords) {
            cardLayout.show(pnlContainer, "medicalRecords");
        }
        else if (e.getSource() == btnPharmacy) {
            cardLayout.show(pnlContainer, "pharmacy");
        }
        else if (e.getSource() == btnLogout) {
            dispose(); // Close this window
            LoginPage lp = new LoginPage();
            lp.setVisible(true); // Open the login screen
        }
    }
    
    // Run the dashboard to test the screen
    public static void main(String[] args) {
            NurseDashboard nurse = new NurseDashboard();
            nurse.setVisible(true);
        
    }
}
