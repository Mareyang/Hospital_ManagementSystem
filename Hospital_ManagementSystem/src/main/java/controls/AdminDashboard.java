package controls;

import controls.LoginPage;
import constants.ButtonStyles;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import panels.ReportsPanel;
import panels.PharmacyPanel;
import panels.AdminDashboardPanel;
import panels.PatientsPanel;
import panels.StaffManagementPanel;
import panels.SettingsPanel;
import panels.AccountManagementPanel;
import panels.BillingPanel; // --- ADDED IMPORT ---
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;

public class AdminDashboard extends JFrame implements ActionListener {
        
    private JPanel pnlSide, pnlTop, pnlContainer, pnlLogo;
    private CardLayout cardLayout;
    // --- ADDED btnBilling ---
    private JButton btnDashboard, btnPatients, btnPharmacy, btnBilling, btnStaff, btnReports, btnSettings, btnAccounts, btnLogout;
    private JLabel lblSystemName, lblLogo;
    private JTextField txtSearchField;
    private ImageIcon logoIcon;
    private JButton activeBtn = null;
    private AdminDashboardPanel dashboardPanel;
    
    public AdminDashboard() {
        setSize(1920, 1080);
        setResizable(false); 
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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

        dashboardPanel = new AdminDashboardPanel();
        pnlContainer.add(dashboardPanel, "dashboard");
        pnlContainer.add(new PatientsPanel(false), "patients"); 
        pnlContainer.add(new PharmacyPanel(true), "pharmacy"); 
        pnlContainer.add(new BillingPanel(), "billing"); 
        pnlContainer.add(new StaffManagementPanel(), "staffManagement");
        pnlContainer.add(new ReportsPanel(), "reports");
        pnlContainer.add(new SettingsPanel(), "settings");
        pnlContainer.add(new AccountManagementPanel(), "accounts");
        
        // --- ADDED btnBilling & SHIFTED Y-COORDINATES ---
        btnDashboard = ButtonStyles.createButton("Dashboard", "/icons/home.png", 30, pnlSide);        
        btnPatients  = ButtonStyles.createButton("Patients", "/icons/patient.png", 80, pnlSide);
        btnPharmacy  = ButtonStyles.createButton("Pharmacy", "/icons/pharmacy2.png", 130, pnlSide);
        btnBilling   = ButtonStyles.createButton("Billing", "/icons/report.png", 180, pnlSide); // Adjust icon path if you have a specific billing icon
        btnStaff     = ButtonStyles.createButton("Staff", "/icons/staff.png", 230, pnlSide);
        btnReports   = ButtonStyles.createButton("Reports", "/icons/report.png", 280, pnlSide);
        btnAccounts  = ButtonStyles.createButton("Accounts", "/icons/staff.png", 330, pnlSide);
        btnSettings  = ButtonStyles.createButton("Settings", "/icons/setting.png", 380, pnlSide);
        btnLogout    = ButtonStyles.createButton("Logout", "/icons/logout.png", 850, pnlSide);

        btnDashboard.addActionListener(this);
        btnPatients.addActionListener(this);
        btnPharmacy.addActionListener(this);
        btnBilling.addActionListener(this); // --- ADDED LISTENER ---
        btnStaff.addActionListener(this);
        btnReports.addActionListener(this);
        btnSettings.addActionListener(this);
        btnAccounts.addActionListener(this);
        btnLogout.addActionListener(this);

        cardLayout.show(pnlContainer, SystemSettings.defaultLandingTab);

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
            dashboardPanel.refreshData();
            cardLayout.show(pnlContainer, "dashboard");
        }
        else if (e.getSource() == btnPatients) {
            cardLayout.show(pnlContainer, "patients");
        }
        else if (e.getSource() == btnPharmacy) {
            cardLayout.show(pnlContainer, "pharmacy");
        }
        // --- ADDED ROUTING FOR BILLING ---
        else if (e.getSource() == btnBilling) {
            cardLayout.show(pnlContainer, "billing");
        }
        else if (e.getSource() == btnStaff) {
            cardLayout.show(pnlContainer, "staffManagement");
        }
        else if (e.getSource() == btnReports) {
            cardLayout.show(pnlContainer, "reports");
        }
        else if (e.getSource() == btnSettings) {
            cardLayout.show(pnlContainer, "settings");
        }
        else if (e.getSource() == btnAccounts) {
            cardLayout.show(pnlContainer, "accounts");
        }
        else if (e.getSource() == btnLogout) {
            dispose(); 
            LoginPage lp = new LoginPage();
            lp.setVisible(true); 
        }
    }
    
    public static void main(String[] args) {
        SystemSettings.loadSettings();
        AdminDashboard admin = new AdminDashboard();
        admin.setVisible(true);
        
    }
}