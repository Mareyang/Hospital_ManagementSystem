package controls;

import controls.LoginPage;
import constants.ButtonStyles;
import constants.ColorsTheme;
import constants.FontsTheme;
import panels.NurseDashboardPanel;
import panels.PatientsPanel;
import panels.AppointmentsPanel;
import panels.MedicalRecordsPanel;
import panels.PrescriptionsPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NurseDashboard extends JFrame implements ActionListener {
    
    private JPanel pnlSide, pnlTop, pnlContainer, pnlLogo;
    private CardLayout cardLayout;
    public JButton btnDashboard, btnPatients, btnAppointments, btnMedicalRecords, btnPrescriptions, btnLogout;
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
        
        pnlSide = new JPanel();
        pnlSide.setLayout(null);
        pnlSide.setBounds(0, 100, 270, 980); 
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
        
        lblSystemName = new JLabel("Carelink Management System");
        lblSystemName.setBounds(110, 25, 600, 50);
        lblSystemName.setFont(FontsTheme.Dashboard_Title);
        lblSystemName.setForeground(ColorsTheme.Main_Card);
        pnlTop.add(lblSystemName);
        
        String placeholder = "Search patients, records, staff...";
        txtSearchField = new JTextField(placeholder);
        txtSearchField.setBounds(730, 30, 500, 40);
        txtSearchField.setFont(FontsTheme.SearchBar);
        txtSearchField.setForeground(ColorsTheme.Search_fg); 
        txtSearchField.setBackground(ColorsTheme.Search_bg);
        txtSearchField.setCaretColor(ColorsTheme.Main_Card);
        txtSearchField.setOpaque(true);
        txtSearchField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        pnlTop.add(txtSearchField);

        pnlContainer.add(new NurseDashboardPanel(), "dashboard");
        pnlContainer.add(new PatientsPanel(true), "patients"); 
        pnlContainer.add(new AppointmentsPanel(true, false), "appointments");
        pnlContainer.add(new MedicalRecordsPanel(true), "medicalRecords"); 
        pnlContainer.add(new PrescriptionsPanel(false, true), "prescriptions"); 
        
        btnDashboard      = ButtonStyles.createButton("Dashboard", "/icons/home.png", 30, pnlSide);        
        btnPatients       = ButtonStyles.createButton("Patients", "/icons/patient.png", 80, pnlSide);
        btnAppointments   = ButtonStyles.createButton("Appointments", "/icons/appointment.png", 130, pnlSide);
        btnMedicalRecords = ButtonStyles.createButton("Records", "/icons/record.png", 180, pnlSide);
        btnPrescriptions  = ButtonStyles.createButton("Prescriptions", "/icons/prescription.png", 230, pnlSide);
        btnLogout         = ButtonStyles.createButton("Logout", "/icons/logout.png", 850, pnlSide);

        activeBtn = btnDashboard;
        btnDashboard.setBackground(ColorsTheme.Active_Button);
        btnDashboard.getParent().setBackground(ColorsTheme.Active_Button);
        
        btnDashboard.addActionListener(this);
        btnPatients.addActionListener(this);
        btnAppointments.addActionListener(this);
        btnMedicalRecords.addActionListener(this);
        btnPrescriptions.addActionListener(this);
        btnLogout.addActionListener(this);

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

    public void switchView(String cardName, JButton sidebarButtonTarget) {
        cardLayout.show(pnlContainer, cardName);

        if (sidebarButtonTarget != null && sidebarButtonTarget != btnLogout) {
            if (activeBtn != null) {
                activeBtn.setBackground(ColorsTheme.Side_Panel);
                activeBtn.getParent().setBackground(ColorsTheme.Side_Panel);
            }
            sidebarButtonTarget.setBackground(ColorsTheme.Active_Button);
            sidebarButtonTarget.getParent().setBackground(ColorsTheme.Active_Button);
            activeBtn = sidebarButtonTarget;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked != btnLogout) {
            if (activeBtn != null) {
                activeBtn.setBackground(ColorsTheme.Side_Panel);
                activeBtn.getParent().setBackground(ColorsTheme.Side_Panel);
            }
            clicked.setBackground(ColorsTheme.Active_Button);
            clicked.getParent().setBackground(ColorsTheme.Active_Button);
            activeBtn = clicked;
        }
        
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
        else if (e.getSource() == btnPrescriptions) {
            cardLayout.show(pnlContainer, "prescriptions"); 
        }
        else if (e.getSource() == btnLogout) {
            dispose(); 
            LoginPage lp = new LoginPage();
            lp.setVisible(true); 
        }
    }
    
    public static void main(String[] args) {
        constants.SystemSettings.loadSettings();
        NurseDashboard nurse = new NurseDashboard();
        nurse.setVisible(true);
    }
}