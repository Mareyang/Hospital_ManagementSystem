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

public class ViewAccountDialog extends JDialog implements ActionListener {

    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle;
    private JLabel lblUsername, lblPassword, lblRole, lblFirstname, lblLastname;
    private JTextField txtUsername, txtPassword, txtFirstname, txtLastname;
    private JComboBox<String> cmbRole;
    private JButton btnClose;
    
    private String username;

    public ViewAccountDialog(String username) {
        this.username = username;

        setTitle("View Account");
        setSize(600, 500);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);

        lblTitle = new JLabel("View Account");
        lblTitle.setBounds(30, 15, 500, 35);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblSubtitle = new JLabel("Detailed information of the selected user account.");
        lblSubtitle.setBounds(30, 45, 500, 25);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);

        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(30, 90, 540, 280);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);

        // Username
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 20, 150, 30);
        lblUsername.setFont(FontsTheme.Plain_Texts);
        lblUsername.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(180, 20, 320, 30);
        txtUsername.setFont(FontsTheme.Info_Texts);
        txtUsername.setEditable(false);
        pnlContent.add(txtUsername);

        // Password
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(20, 70, 150, 30);
        lblPassword.setFont(FontsTheme.Plain_Texts);
        lblPassword.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(180, 70, 320, 30);
        txtPassword.setFont(FontsTheme.Info_Texts);
        txtPassword.setEditable(false);
        pnlContent.add(txtPassword);

        // First Name
        lblFirstname = new JLabel("First Name");
        lblFirstname.setBounds(20, 120, 150, 30);
        lblFirstname.setFont(FontsTheme.Plain_Texts);
        lblFirstname.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFirstname);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(180, 120, 320, 30);
        txtFirstname.setFont(FontsTheme.Info_Texts);
        txtFirstname.setEditable(false);
        pnlContent.add(txtFirstname);

        // Last Name
        lblLastname = new JLabel("Last Name");
        lblLastname.setBounds(20, 170, 150, 30);
        lblLastname.setFont(FontsTheme.Plain_Texts);
        lblLastname.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblLastname);

        txtLastname = new JTextField();
        txtLastname.setBounds(180, 170, 320, 30);
        txtLastname.setFont(FontsTheme.Info_Texts);
        txtLastname.setEditable(false);
        pnlContent.add(txtLastname);

        // Role
        lblRole = new JLabel("Role");
        lblRole.setBounds(20, 220, 150, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRole);

        cmbRole = new JComboBox<>(new String[]{"Admin", "Doctor", "Nurse"});
        cmbRole.setBounds(180, 220, 320, 30);
        cmbRole.setFont(FontsTheme.Info_Texts);
        cmbRole.setEnabled(false);
        pnlContent.add(cmbRole);

        // Action Buttons
        btnClose = new JButton("Close");
        btnClose.setBounds(230, 400, 140, 35);
        btnClose.setFont(FontsTheme.Buttons);
        btnClose.setBackground(ColorsTheme.Text_Gray);
        btnClose.setForeground(ColorsTheme.Text_White);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(this);
        add(btnClose);

        loadUserData();
    }

    private void loadUserData() {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    txtUsername.setText(rs.getString("username"));
                    txtPassword.setText(rs.getString("password"));
                    txtFirstname.setText(rs.getString("firstname"));
                    txtLastname.setText(rs.getString("lastname"));
                    cmbRole.setSelectedItem(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load account details:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            dispose();
        }
    }
}
