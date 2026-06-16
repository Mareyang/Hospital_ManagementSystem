package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class NewStaffDialog extends JDialog implements ActionListener {
    
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails;
    private JLabel lblFirstname, lblLastname, lblUsername, lblPassword, lblEmail, lblRole, lblStatus, lblDepartment;
    private JTextField txtFirstname, txtLastname, txtUsername, txtPassword, txtEmail, txtDepartment;
    private JComboBox<String> cmbRole, cmbStats;
    private JButton btnSave, btnCancel;
    
    private static final String[] roles = {" ", "Doctor", "Nurse"};
    private static final String[] status = {" ", "Active", "On Leave", "Contract", "Inactive"};
    
    public NewStaffDialog() {
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        lblDialogTitle = new JLabel("Staff Profile Entry");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Register new hospital personnel and setup accounts.");
        lblDialogDetails.setBounds(30, 40, 650, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 100, 700, 360);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        btnSave = new JButton("Save Staff Profile");
        btnSave.setBounds(440, 490, 300, 30);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setBackground(ColorsTheme.Green);
        btnSave.setFocusPainted(false);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(230, 490, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);
        
        initializeForms();
    }
        
    private void initializeForms() {
        lblFirstname = new JLabel("First Name :");
        lblFirstname.setBounds(40, 40, 170, 30);
        lblFirstname.setFont(FontsTheme.Plain_Texts);
        lblFirstname.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblFirstname);

        txtFirstname = new JTextField("");
        txtFirstname.setBounds(220, 40, 400, 30);
        txtFirstname.setFont(FontsTheme.Plain_Texts);
        txtFirstname.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtFirstname);

        lblLastname = new JLabel("Last Name :");
        lblLastname.setBounds(40, 80, 170, 30);
        lblLastname.setFont(FontsTheme.Plain_Texts);
        lblLastname.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblLastname);

        txtLastname = new JTextField("");
        txtLastname.setBounds(220, 80, 400, 30);
        txtLastname.setFont(FontsTheme.Plain_Texts);
        txtLastname.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtLastname);

        lblUsername = new JLabel("Username :");
        lblUsername.setBounds(40, 120, 170, 30);
        lblUsername.setFont(FontsTheme.Plain_Texts);
        lblUsername.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblUsername);

        txtUsername = new JTextField("");
        txtUsername.setBounds(220, 120, 400, 30);
        txtUsername.setFont(FontsTheme.Plain_Texts);
        txtUsername.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtUsername);

        lblPassword = new JLabel("Password :");
        lblPassword.setBounds(40, 160, 170, 30);
        lblPassword.setFont(FontsTheme.Plain_Texts);
        lblPassword.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblPassword);

        txtPassword = new JTextField("");
        txtPassword.setBounds(220, 160, 400, 30);
        txtPassword.setFont(FontsTheme.Plain_Texts);
        txtPassword.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtPassword);

        lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(40, 200, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmail);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 200, 400, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmail);

        lblRole = new JLabel("Role :");
        lblRole.setBounds(40, 240, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblRole);

        cmbRole = new JComboBox<>(roles);
        cmbRole.setBounds(220, 240, 170, 30);
        cmbRole.setFont(FontsTheme.Plain_Texts);
        cmbRole.setForeground(ColorsTheme.Text_Black);
        cmbRole.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbRole);

        lblStatus = new JLabel("Status :");
        lblStatus.setBounds(410, 240, 80, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblStatus);

        cmbStats = new JComboBox<>(status);
        cmbStats.setBounds(480, 240, 140, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbStats);

        lblDepartment = new JLabel("Department :");
        lblDepartment.setBounds(40, 280, 170, 30);
        lblDepartment.setFont(FontsTheme.Plain_Texts);
        lblDepartment.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblDepartment);

        txtDepartment = new JTextField("");
        txtDepartment.setBounds(220, 280, 400, 30);
        txtDepartment.setFont(FontsTheme.Plain_Texts);
        txtDepartment.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtDepartment);
    }

    private boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private int getStatusId(String statusValue) {
        int statusId = 1;
        String sql = "SELECT status_id FROM user_status WHERE status_name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statusValue);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                statusId = rs.getInt("status_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statusId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnSave) {
            String firstname = txtFirstname.getText().trim();
            String lastname = txtLastname.getText().trim();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String email = txtEmail.getText().trim();
            String role = cmbRole.getSelectedItem().toString();
            String statusValue = cmbStats.getSelectedItem().toString();
            String department = txtDepartment.getText().trim();

            if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() || role.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name, Last Name, Username, Password, and Role are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (isUsernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO users (username, password, role, firstname, lastname, email, status_id, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = conn.prepareStatement(sql)) {
                
                insert.setString(1, username);
                insert.setString(2, password);
                insert.setString(3, role);
                insert.setString(4, firstname);
                insert.setString(5, lastname);
                insert.setString(6, email);
                insert.setInt(7, getStatusId(statusValue));
                insert.setString(8, department);

                int rows = insert.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Staff record added successfully!", "Staff Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}