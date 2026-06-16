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

public class ViewStaffDialog extends JDialog implements ActionListener {
    
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails;
    private JLabel lblFirstname, lblLastname, lblUsername, lblPassword, lblEmail, lblRole, lblStatus, lblDepartment;
    private JTextField txtFirstname, txtLastname, txtUsername, txtPassword, txtEmail, txtDepartment;
    private JComboBox<String> cmbRole, cmbStats;
    private JButton btnClose;
    
    private String currentEmpId;
    
    private static final String[] roles = {" ", "Doctor", "Nurse", "Admin"};
    private static final String[] status = {" ", "Active", "On Leave", "Contract", "Inactive"};
    
    public ViewStaffDialog(String empId) {
        this.currentEmpId = empId;
        
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        lblDialogTitle = new JLabel("View Staff Profile");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("View existing hospital personnel and accounts.");
        lblDialogDetails.setBounds(30, 40, 650, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 100, 700, 360);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        btnClose = new JButton("Close");
        btnClose.setBounds(250, 490, 300, 30);
        btnClose.setFont(FontsTheme.Buttons);
        btnClose.setForeground(ColorsTheme.Text_White);
        btnClose.setBackground(ColorsTheme.Search);
        btnClose.setFocusPainted(false);
        add(btnClose);
        
        btnClose.addActionListener(this);
        
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
        txtFirstname.setEditable(false);
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
        txtLastname.setEditable(false);
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
        txtUsername.setEditable(false);
        txtUsername.setBackground(new Color(230, 230, 230));
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
        txtPassword.setEditable(false);
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
        txtEmail.setEditable(false);
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
        cmbRole.setEnabled(false);
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
        cmbStats.setEnabled(false);
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
        txtDepartment.setEditable(false);
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
                txtUsername.setText(rs.getString("username"));
                txtPassword.setText(rs.getString("password"));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            dispose();
        }
    }
}
