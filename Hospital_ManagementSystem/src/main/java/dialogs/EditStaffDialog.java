package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class EditStaffDialog extends JDialog implements ActionListener {
    
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails;
    private JLabel lblFirstname, lblLastname, lblEmail, lblRole, lblStatus, lblDepartment;
    private JTextField txtFirstname, txtLastname, txtEmail, txtDepartment;
    private JComboBox<String> cmbRole, cmbStats;
    private JButton btnSave, btnCancel;
    
    private String currentEmpId;
    
    private static final String[] roles = {" ", "Doctor", "Nurse", "Admin"};
    private static final String[] status = {" ", "Active", "On Leave", "Contract", "Inactive"};
    
    public EditStaffDialog(String empId) {
        this.currentEmpId = empId;
        
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        lblDialogTitle = new JLabel("Edit Staff Profile");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Modify existing hospital personnel and accounts.");
        lblDialogDetails.setBounds(30, 40, 650, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 100, 700, 360);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        btnSave = new JButton("Update Staff Profile");
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
        loadStaffData();
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

        lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(40, 120, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmail);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 120, 400, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmail);

        lblRole = new JLabel("Role :");
        lblRole.setBounds(40, 160, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblRole);

        cmbRole = new JComboBox<>(roles);
        cmbRole.setBounds(220, 160, 170, 30);
        cmbRole.setFont(FontsTheme.Plain_Texts);
        cmbRole.setForeground(ColorsTheme.Text_Black);
        cmbRole.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbRole);

        lblStatus = new JLabel("Status :");
        lblStatus.setBounds(410, 160, 80, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblStatus);

        cmbStats = new JComboBox<>(status);
        cmbStats.setBounds(480, 160, 140, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbStats);

        lblDepartment = new JLabel("Department :");
        lblDepartment.setBounds(40, 200, 170, 30);
        lblDepartment.setFont(FontsTheme.Plain_Texts);
        lblDepartment.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblDepartment);

        txtDepartment = new JTextField("");
        txtDepartment.setBounds(220, 200, 400, 30);
        txtDepartment.setFont(FontsTheme.Plain_Texts);
        txtDepartment.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtDepartment);
    }

    private void loadStaffData() {
        String sql = "SELECT u.*, us.status_name as status FROM users u " +
                     "LEFT JOIN user_status us ON u.status_id = us.status_id " +
                     "WHERE u.user_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String cleanId = currentEmpId.replaceAll("[A-Z]+-", "");
            stmt.setString(1, cleanId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                txtFirstname.setText(rs.getString("firstname"));
                txtLastname.setText(rs.getString("lastname"));
                txtEmail.setText(rs.getString("email"));
                txtDepartment.setText(rs.getString("department"));
                
                String role = rs.getString("role");
                if (role != null) cmbRole.setSelectedItem(role);
                
                String stats = rs.getString("status");
                if (stats != null) cmbStats.setSelectedItem(stats);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load staff data:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
            String email = txtEmail.getText().trim();
            String role = cmbRole.getSelectedItem().toString();
            String statusValue = cmbStats.getSelectedItem().toString();
            String department = txtDepartment.getText().trim();

            if (firstname.isEmpty() || lastname.isEmpty() || role.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name, Last Name, and Role are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE users SET firstname=?, lastname=?, email=?, role=?, status_id=?, department=? WHERE user_id=?";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement update = conn.prepareStatement(sql)) {
                
                update.setString(1, firstname);
                update.setString(2, lastname);
                update.setString(3, email);
                update.setString(4, role);
                update.setInt(5, getStatusId(statusValue));
                update.setString(6, department);
                
                String cleanId = currentEmpId.replaceAll("[A-Z]+-", "");
                update.setString(7, cleanId);

                int rows = update.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Staff record updated successfully!", "Staff Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database update operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
