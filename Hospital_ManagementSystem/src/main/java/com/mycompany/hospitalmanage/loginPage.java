package com.mycompany.hospitalmanage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage extends JFrame implements ActionListener {

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    JLabel hospitalName = new JLabel("CARELINK HOSPITAL");
    JLabel titleLabel = new JLabel("Login");

    JLabel usernameLabel = new JLabel("Username");
    JTextField usernameField = new JTextField();

    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();

    JButton loginButton = new JButton("Login");

    loginPage() {

        setTitle("Hospital Management System");
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // LEFT PANEL
        leftPanel.setBounds(0, 0, 350, 600);
        leftPanel.setBackground(new Color(16, 23, 95));
        leftPanel.setLayout(null);
        add(leftPanel);

        // RIGHT PANEL
        rightPanel.setBounds(350, 0, 650, 600);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);
        add(rightPanel);

        // HOSPITAL NAME
        hospitalName.setBounds(40, 200, 300, 40);
        hospitalName.setForeground(Color.WHITE);
        hospitalName.setFont(new Font("Arial", Font.BOLD, 28));
        leftPanel.add(hospitalName);

        // TITLE
        titleLabel.setBounds(250, 80, 200, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        rightPanel.add(titleLabel);

        // USERNAME
        usernameLabel.setBounds(120, 170, 100, 30);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rightPanel.add(usernameLabel);

        usernameField.setBounds(120, 210, 350, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        rightPanel.add(usernameField);

        // PASSWORD
        passwordLabel.setBounds(120, 280, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rightPanel.add(passwordLabel);

        passwordField.setBounds(120, 320, 350, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        rightPanel.add(passwordField);

        // LOGIN BUTTON
        loginButton.setBounds(120, 400, 350, 45);
        loginButton.setBackground(new Color(37, 99, 235));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(this);
        rightPanel.add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("1234")) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            HospitalDashboard hd = new HospitalDashboard();
            hd.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );
        }
    }

    public static void main(String[] args) {
        new loginPage();
    }
}