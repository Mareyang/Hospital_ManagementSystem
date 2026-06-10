package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordDialog extends JDialog implements ActionListener {

    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle;
    private JLabel lblFirstname, lblLastname, lblUsername, lblNewPassword;
    private JTextField txtFirstname, txtLastname, txtUsername, txtNewPassword;
    private JButton btnReset, btnCancel;

    public ForgotPasswordDialog(JFrame parent) {
        super(parent, "Forgot Password", true);
        setSize(550, 480);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(parent);

        lblTitle = new JLabel("Reset Password");
        lblTitle.setBounds(30, 15, 400, 35);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblSubtitle = new JLabel("Verify your identity details to set a new password.");
        lblSubtitle.setBounds(30, 45, 450, 25);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);

        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(30, 85, 490, 270);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);

        // First Name
        lblFirstname = new JLabel("First Name");
        lblFirstname.setBounds(20, 20, 150, 30);
        lblFirstname.setFont(FontsTheme.Plain_Texts);
        lblFirstname.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFirstname);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(180, 20, 280, 30);
        txtFirstname.setFont(FontsTheme.Info_Texts);
        pnlContent.add(txtFirstname);

        // Last Name
        lblLastname = new JLabel("Last Name");
        lblLastname.setBounds(20, 80, 150, 30);
        lblLastname.setFont(FontsTheme.Plain_Texts);
        lblLastname.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblLastname);

        txtLastname = new JTextField();
        txtLastname.setBounds(180, 80, 280, 30);
        txtLastname.setFont(FontsTheme.Info_Texts);
        pnlContent.add(txtLastname);

        // Username / ID
        lblUsername = new JLabel("Username / ID");
        lblUsername.setBounds(20, 140, 150, 30);
        lblUsername.setFont(FontsTheme.Plain_Texts);
        lblUsername.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(180, 140, 280, 30);
        txtUsername.setFont(FontsTheme.Info_Texts);
        pnlContent.add(txtUsername);

        // New Password
        lblNewPassword = new JLabel("New Password");
        lblNewPassword.setBounds(20, 200, 150, 30);
        lblNewPassword.setFont(FontsTheme.Plain_Texts);
        lblNewPassword.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNewPassword);

        txtNewPassword = new JTextField();
        txtNewPassword.setBounds(180, 200, 280, 30);
        txtNewPassword.setFont(FontsTheme.Info_Texts);
        pnlContent.add(txtNewPassword);

        // Buttons
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(130, 380, 130, 35);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);

        btnReset = new JButton("Reset");
        btnReset.setBounds(290, 380, 130, 35);
        btnReset.setFont(FontsTheme.Buttons);
        btnReset.setBackground(ColorsTheme.Add_Confirm);
        btnReset.setForeground(ColorsTheme.Text_White);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(this);
        add(btnReset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnReset) {
            resetPassword();
        }
    }

    private void resetPassword() {
        String first = txtFirstname.getText().trim();
        String last = txtLastname.getText().trim();
        String user = txtUsername.getText().trim();
        String newPass = txtNewPassword.getText().trim();

        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String verifySql = "SELECT * FROM users WHERE username = ? AND firstname = ? AND lastname = ?";
        String updateSql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement verifyStmt = conn.prepareStatement(verifySql)) {
            
            verifyStmt.setString(1, user);
            verifyStmt.setString(2, first);
            verifyStmt.setString(3, last);

            try (ResultSet rs = verifyStmt.executeQuery()) {
                if (rs.next()) {
                    // Match found, update password
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setString(1, newPass);
                        updateStmt.setString(2, user);
                        int rows = updateStmt.executeUpdate();
                        if (rows > 0) {
                            JOptionPane.showMessageDialog(this, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid account details. No matching user found.", "Verification Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
