package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewAccountDialog extends JDialog implements ActionListener {

    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle;
    private JLabel lblUsername, lblPassword, lblRole, lblFirstname, lblLastname;
    private JTextField txtUsername, txtPassword, txtFirstname, txtLastname;
    private JComboBox<String> cmbRole;
    private JButton btnSave, btnCancel;
    
    private boolean success = false;

    public NewAccountDialog() {
        setTitle("Register Account");
        setSize(600, 500);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);

        lblTitle = new JLabel("Add New Account");
        lblTitle.setBounds(30, 15, 500, 35);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblSubtitle = new JLabel("Create a new user account for system access.");
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
        pnlContent.add(cmbRole);

        // Action Buttons
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(150, 400, 130, 35);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);

        btnSave = new JButton("Save");
        btnSave.setBounds(320, 400, 130, 35);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this);
        add(btnSave);
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnSave) {
            saveNewAccount();
        }
    }

    private void saveNewAccount() {
        String user = txtUsername.getText().trim();
        String pass = txtPassword.getText().trim();
        String first = txtFirstname.getText().trim();
        String last = txtLastname.getText().trim();
        String role = cmbRole.getSelectedItem().toString();

        if (user.isEmpty() || pass.isEmpty() || first.isEmpty() || last.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO users (username, password, role, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, pass);
            stmt.setString(3, role);
            stmt.setString(4, first);
            stmt.setString(5, last);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Account registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                success = true;
                dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to register account:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
