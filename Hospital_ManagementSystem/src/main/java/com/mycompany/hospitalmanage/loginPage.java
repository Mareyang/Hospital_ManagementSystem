package com.mycompany.hospitalmanage;

import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage extends JFrame implements ActionListener {

    private JPanel pnlLeft, pnlRight;
    private JLabel hospitalName, hospitalDescription, lblBullets, lblBelow, 
            lblTitle, lblUsername,lblPassword, imgIcon;
    private JTextField txtUsername;
    private JPasswordField pxtPassword;
    private JButton btnLogin;
    
    
   
    LoginPage() {
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Left
        pnlLeft = new JPanel();
        pnlLeft.setBounds(0, 0, 400, 600);
        pnlLeft.setBackground(ColorsTheme.Side_Panel);
        pnlLeft.setLayout(null);
        add(pnlLeft);

        
        //Right
        pnlRight = new JPanel();
        pnlRight.setBounds(350, 0, 650, 600);
        pnlRight.setBackground(ColorsTheme.Main_Card);
        pnlRight.setLayout(null);
        add(pnlRight);

        imgIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/hospitalIcon.png")));
        imgIcon.setBounds(110, 30, 150, 150);
        pnlLeft.add(imgIcon);
        
        //Hospital Name/LeftPanel
        hospitalName = new JLabel("<html>&nbsp;&nbsp;CARELINK HOSPITAL<br>"
                + "MANAGEMENT SYSTEM</html>");
        hospitalName.setBounds(40, 180, 300, 100);
        hospitalName.setForeground(ColorsTheme.Text_White);
        hospitalName.setFont(FontsTheme.Login_Title);
        pnlLeft.add(hospitalName);
        
        
        //Hospital Description
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
        
        
        

        //Title/RightPanel
        lblTitle = new JLabel("Sign in");
        lblTitle.setBounds(300, 80, 200, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        pnlRight.add(lblTitle);

        //Username
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(170, 170, 100, 30);
        lblUsername.setFont(FontsTheme.Plain);
        pnlRight.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(170, 210, 350, 40);
        txtUsername.setFont(FontsTheme.Plain_Small);
        pnlRight.add(txtUsername);

        
        //Password
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(170, 280, 100, 30);
        lblPassword.setFont(FontsTheme.Plain);
        pnlRight.add(lblPassword);

        pxtPassword = new JPasswordField();
        pxtPassword.setBounds(170, 320, 350, 40);
        pxtPassword.setFont(FontsTheme.Plain_Small);
        pnlRight.add(pxtPassword);

        
        //Login 
        btnLogin = new JButton("Login");
        btnLogin.setBounds(170, 400, 350, 45);
        btnLogin.setBackground(ColorsTheme.Search);
        btnLogin.setForeground(ColorsTheme.Text_White);
        btnLogin.setFont(FontsTheme.Bold);
        btnLogin.addActionListener(this);
        pnlRight.add(btnLogin);

        
        
        
        setVisible(true);
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String username = txtUsername.getText();
        String password = new String(pxtPassword.getPassword());

        if (username.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            HospitalDashboard hd = new HospitalDashboard();
            hd.setVisible(true);
            dispose();

        } 
        
        else { JOptionPane.showMessageDialog(this,"Invalid Username or Password");
        
        
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}