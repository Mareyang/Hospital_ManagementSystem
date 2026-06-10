package controls;

import constants.ColorsTheme;
import constants.FontsTheme;
import controls.AdminDashboard;
import controls.DoctorDashboard;
import controls.NurseDashboard;
import dialogs.ForgotPasswordDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame implements ActionListener {

    private JPanel pnlLeft, pnlRight;
    private JLabel hospitalName, hospitalDescription, lblBullets, lblBelow, 
            lblTitle, lblUsername,lblPassword, imgIcon;
    private JTextField txtUsername;
    private JPasswordField pxtPassword;
    private JButton btnLogin, btnForgot;
    
    public LoginPage() {
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Left Panel
        pnlLeft = new JPanel();
        pnlLeft.setBounds(0, 0, 400, 600);
        pnlLeft.setBackground(ColorsTheme.Side_Panel);
        pnlLeft.setLayout(null);
        add(pnlLeft);

        // Right Panel
        pnlRight = new JPanel();
        pnlRight.setBounds(350, 0, 650, 600);
        pnlRight.setBackground(ColorsTheme.Main_Card);
        pnlRight.setLayout(null);
        add(pnlRight);

        imgIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/hospitalIcon.png")));
        imgIcon.setBounds(110, 30, 150, 150);
        pnlLeft.add(imgIcon);
        
        // Hospital Name/LeftPanel
        hospitalName = new JLabel("<html>&nbsp;&nbsp;CARELINK HOSPITAL<br>"
                + "MANAGEMENT SYSTEM</html>");
        hospitalName.setBounds(40, 180, 300, 100);
        hospitalName.setForeground(ColorsTheme.Text_White);
        hospitalName.setFont(FontsTheme.Login_Title);
        pnlLeft.add(hospitalName);
        
        // Hospital Description
        hospitalDescription = new JLabel("<html> An advanced hospital information system built to <br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp; secure data and synchronize patient care. </html>");
        hospitalDescription.setBounds(50, 270, 300, 40);
        hospitalDescription.setForeground(ColorsTheme.Text_White);
        hospitalDescription.setFont(FontsTheme.Login_Subtitle);
        pnlLeft.add(hospitalDescription);
        
        lblBullets = new JLabel("<html> ☑&nbsp; Patient & Medical Records<br>" +
                "☑&nbsp; Appointment Scheduling<br>" +
                "☑&nbsp; Prescriptions & Pharmacy<br>" +
                "☑&nbsp; Laboratory Tracking<br>" +
                "☑&nbsp; Bed Management & Bill<br>" +
                "☑&nbsp; Staff & Emergency Services<br>");
        lblBullets.setBounds(100, 330, 300, 100);
        lblBullets.setForeground(ColorsTheme.Text_White);
        lblBullets.setFont(FontsTheme.Login_Subtitle);
        pnlLeft.add(lblBullets);
        
        lblBelow = new JLabel("<html> ©&nbsp; Copyright 2026 CareLink </html>");
        lblBelow.setBounds(110, 500, 300, 100);
        lblBelow.setForeground(ColorsTheme.Text_White);
        lblBelow.setFont(FontsTheme.Login_Subtitle);
        pnlLeft.add(lblBelow);

        // Title/RightPanel
        lblTitle = new JLabel("Sign in");
        lblTitle.setBounds(300, 60, 200, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        pnlRight.add(lblTitle);

        // Username
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(170, 150, 100, 30);
        lblUsername.setFont(FontsTheme.Plain);
        pnlRight.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(170, 190, 350, 40);
        txtUsername.setFont(FontsTheme.Plain_Small);
        pnlRight.add(txtUsername);

        // Password
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(170, 260, 100, 30);
        lblPassword.setFont(FontsTheme.Plain);
        pnlRight.add(lblPassword);

        pxtPassword = new JPasswordField();
        pxtPassword.setBounds(170, 300, 350, 40);
        pxtPassword.setFont(FontsTheme.Plain_Small);
        pnlRight.add(pxtPassword);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(170, 380, 350, 45);
        btnLogin.setBackground(ColorsTheme.Search); 
        btnLogin.setForeground(ColorsTheme.Text_White);
        btnLogin.setFont(FontsTheme.Bold);
        btnLogin.addActionListener(this);
        pnlRight.add(btnLogin);
        
        // Forgot Password
        btnForgot = new JButton("Forgot Password?");
        btnForgot.setBounds(170, 440, 350, 30); 
        btnForgot.setForeground(ColorsTheme.Search); 
        btnForgot.setFont(FontsTheme.Plain);
        btnForgot.setContentAreaFilled(false); 
        btnForgot.setBorderPainted(false);     
        btnForgot.setFocusPainted(false);     
        btnForgot.addActionListener(this);
        pnlRight.add(btnForgot);
                
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Handle Forgot Password routing
        if (e.getSource() == btnForgot) {
            ForgotPasswordDialog dialog = new ForgotPasswordDialog(this);
            dialog.setVisible(true);
            return; 
        }

        String username = txtUsername.getText();
        String password = new String(pxtPassword.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
            
            PreparedStatement select = connection.prepareStatement("SELECT role, firstname, lastname FROM users WHERE username=? AND password=?");
            select.setString(1, username);
            select.setString(2, password);

            ResultSet result = select.executeQuery();

            if (result.next()) {
                String dbRole = result.getString("role");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                if (dbRole.equals("Admin")) {
                    JOptionPane.showMessageDialog(this, "Welcome back, Admin " +lastName + "!");
                    AdminDashboard admin = new AdminDashboard();
                    admin.setVisible(true);
                } 
                else if (dbRole.equals("Doctor")) {
                    JOptionPane.showMessageDialog(this, "Welcome back, Dr. " + lastName + "!");
                    DoctorDashboard doctor = new DoctorDashboard();
                    doctor.setVisible(true);
                } 
                else if (dbRole.equals("Nurse")) {
                    JOptionPane.showMessageDialog(this, "Welcome back, Nurse " + lastName + "!");
                    NurseDashboard nurse = new NurseDashboard();
                    nurse.setVisible(true);
                }
                
                dispose(); 

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }

            result.close();
            select.close();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error! Is XAMPP running?", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}