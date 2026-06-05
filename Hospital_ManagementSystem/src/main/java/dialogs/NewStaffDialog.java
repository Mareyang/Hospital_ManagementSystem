package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class NewStaffDialog extends JDialog implements ActionListener {
      
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails, lblEmpID, lblName, lblBday, lblGen, lblEmail, lblContact, 
            lblMarital, lblDep, lblRole, lblHired, lblOff, lblStatus, lblPR, lblCName, lblSName, lblLine, lblSRole, lblSRate, lblCS;
    private JTextField txtEmpID, txtName, txtContact, txtEmail, txtBday, txtDep, txtRole, txtHired, txtCName, txtSName;
    private JButton btnStaff, btnPerf, btnSave, btnCancel;
    private JComboBox<String> cmbGen, cmbMarital, cmbStats, cmbOff, cmbSRole, cmbSRate;
    private JTextArea txaComs;
    private JScrollPane scrollComs;
    
    private static final String[] gender = {" ", "Male", "Female", "Prefer not to say"};
    private static final String[] days = {" ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] status = {" ", "Active", "On Leave", "Contract"};
    private static final String[] marital = {" ", "Single", "Married", "Divorced", "Widowed"};
    private static final String[] roles = {" ", "Doctor", "Nurse", "Admin"};
    private static final String[] rates = {" ", "5 - Excellent", "4 - Very Good", "3 - Average", "2 - Below Average", "1- Poor"};
    
    public NewStaffDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        lblDialogTitle = new JLabel("Staff Profile Entry");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Register new hospital personnel and configure departmental assignments.");
        lblDialogDetails.setBounds(30, 40, 650, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        btnStaff = new JButton("Staff Information");
        btnStaff.setBounds(40, 100, 250, 40);
        btnStaff.setFont(FontsTheme.Buttons);
        btnStaff.setForeground(ColorsTheme.Text_White);
        btnStaff.setBackground(ColorsTheme.Header);
        btnStaff.setFocusPainted(false);
        add(btnStaff);
        
        btnPerf = new JButton("Performance and Suggestions");
        btnPerf.setBounds(290, 100, 320, 40);
        btnPerf.setFont(FontsTheme.Buttons);
        btnPerf.setForeground(ColorsTheme.Text_White);
        btnPerf.setBackground(ColorsTheme.Header);
        btnPerf.setFocusPainted(false);
        add(btnPerf);
        
        pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 140, 950, 300);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        btnSave = new JButton("Save Staff Profile");
        btnSave.setBounds(690, 450, 300, 30);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setBackground(ColorsTheme.Green);
        btnSave.setFocusPainted(false);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnStaff.addActionListener(this);
        btnPerf.addActionListener(this);
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);
        
        initializeForms();
        showStaffInfo();
    }
        
    private void initializeForms() {
        // --- STAFF INFORMATION SUB-FIELDS ---
        lblEmpID = new JLabel("Employee ID :");
        lblEmpID.setBounds(40, 40, 170, 30);
        lblEmpID.setFont(FontsTheme.Plain_Texts);
        lblEmpID.setForeground(ColorsTheme.Text_Black);

        txtEmpID = new JTextField("");
        txtEmpID.setBounds(220, 40, 230, 30);
        txtEmpID.setFont(FontsTheme.Plain_Texts);
        txtEmpID.setForeground(ColorsTheme.Text_Black);

        lblName = new JLabel("Full Name :");
        lblName.setBounds(40, 80, 170, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);

        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);

        lblBday = new JLabel("Birthday :");
        lblBday.setBounds(40, 120, 170, 30);
        lblBday.setFont(FontsTheme.Plain_Texts);
        lblBday.setForeground(ColorsTheme.Text_Black);

        txtBday = new JTextField("");
        txtBday.setBounds(220, 120, 230, 30);
        txtBday.setFont(FontsTheme.Plain_Texts);
        txtBday.setForeground(ColorsTheme.Text_Black);

        lblGen = new JLabel("Gender :");
        lblGen.setBounds(40, 160, 170, 30);
        lblGen.setFont(FontsTheme.Plain_Texts);
        lblGen.setForeground(ColorsTheme.Text_Black);

        cmbGen = new JComboBox<>(gender);
        cmbGen.setBounds(220, 160, 230, 30);
        cmbGen.setFont(FontsTheme.Plain_Texts);
        cmbGen.setForeground(ColorsTheme.Text_Black);
        cmbGen.setBackground(ColorsTheme.Main_Card);
        
        lblEmail = new JLabel("Email Address: ");
        lblEmail.setBounds(40, 200, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 200, 230, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);

        lblContact = new JLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        lblContact.setFont(FontsTheme.Plain_Texts);
        lblContact.setForeground(ColorsTheme.Text_Black);

        txtContact = new JTextField("");
        txtContact.setBounds(220, 240, 230, 30);
        txtContact.setFont(FontsTheme.Plain_Texts);
        txtContact.setForeground(ColorsTheme.Text_Black);
        
        lblMarital = new JLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        lblMarital.setFont(FontsTheme.Plain_Texts);
        lblMarital.setForeground(ColorsTheme.Text_Black);

        cmbMarital = new JComboBox<>(marital);
        cmbMarital.setBounds(720, 40, 180, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts);
        cmbMarital.setForeground(ColorsTheme.Text_Black);
        cmbMarital.setBackground(ColorsTheme.Main_Card);

        lblDep = new JLabel("Department :");
        lblDep.setBounds(540, 80, 170, 30);
        lblDep.setFont(FontsTheme.Plain_Texts);
        lblDep.setForeground(ColorsTheme.Text_Black);

        txtDep = new JTextField("");
        txtDep.setBounds(720, 80, 180, 30);
        txtDep.setFont(FontsTheme.Plain_Texts);
        txtDep.setForeground(ColorsTheme.Text_Black);

        lblRole = new JLabel("Role :");
        lblRole.setBounds(540, 120, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);

        txtRole = new JTextField("");
        txtRole.setBounds(720, 120, 180, 30);
        txtRole.setFont(FontsTheme.Plain_Texts);
        txtRole.setForeground(ColorsTheme.Text_Black);

        lblHired = new JLabel("Hire Date :");
        lblHired.setBounds(540, 160, 170, 30);
        lblHired.setFont(FontsTheme.Plain_Texts);
        lblHired.setForeground(ColorsTheme.Text_Black);

        txtHired = new JTextField(" ");
        txtHired.setBounds(720, 160, 180, 30);
        txtHired.setFont(FontsTheme.Plain_Texts);
        txtHired.setForeground(ColorsTheme.Text_Black);

        lblOff = new JLabel("Day Off :");
        lblOff.setBounds(540, 200, 170, 30);
        lblOff.setFont(FontsTheme.Plain_Texts);
        lblOff.setForeground(ColorsTheme.Text_Black);

        cmbOff = new JComboBox<>(days);
        cmbOff.setBounds(720, 200, 180, 30);
        cmbOff.setFont(FontsTheme.Plain_Texts);
        cmbOff.setForeground(ColorsTheme.Text_Black);
        cmbOff.setBackground(ColorsTheme.Main_Card);

        lblStatus = new JLabel("Status :");
        lblStatus.setBounds(540, 240, 170, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);

        cmbStats = new JComboBox<>(status);
        cmbStats.setBounds(720, 240, 180, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);

        // --- PERFORMANCE AND EVALUATION SUB-FIELDS ---
        lblPR = new JLabel("Performance Rate");
        lblPR.setBounds(70, 10, 200, 30);
        lblPR.setFont(FontsTheme.Title_Texts);
        lblPR.setForeground(ColorsTheme.Text_Black);
        
        lblCName = new JLabel("Client Name : ");
        lblCName.setBounds(40, 50, 170, 30);
        lblCName.setFont(FontsTheme.Plain_Texts);
        lblCName.setForeground(ColorsTheme.Text_Black);
        
        txtCName = new JTextField("");
        txtCName.setBounds(250, 50, 250, 30);
        txtCName.setFont(FontsTheme.Plain_Texts);
        txtCName.setForeground(ColorsTheme.Text_Black);
          
        lblLine = new JLabel("Staff Details");
        lblLine.setBounds(70, 100, 380, 30);
        lblLine.setFont(FontsTheme.Title_Texts);
        lblLine.setForeground(ColorsTheme.Text_Black);
        
        lblSName = new JLabel("Employee Name : ");
        lblSName.setBounds(40, 140, 170, 50);
        lblSName.setFont(FontsTheme.Plain_Texts);
        lblSName.setForeground(ColorsTheme.Text_Black);
        
        txtSName = new JTextField(" ");
        txtSName.setBounds(250, 150, 250, 30);
        txtSName.setFont(FontsTheme.Plain_Texts);
        txtSName.setForeground(ColorsTheme.Text_Black);
        
        lblSRole = new JLabel("Role : ");
        lblSRole.setBounds(40, 180, 170, 50);
        lblSRole.setFont(FontsTheme.Plain_Texts);
        lblSRole.setForeground(ColorsTheme.Text_Black);
         
        cmbSRole = new JComboBox<>(roles);
        cmbSRole.setBounds(250, 190, 250, 30);
        cmbSRole.setFont(FontsTheme.Plain_Texts);
        cmbSRole.setForeground(ColorsTheme.Text_Black);
        cmbSRole.setBackground(ColorsTheme.Main_Card);
        
        lblSRate = new JLabel("Rate (1-5) : ");
        lblSRate.setBounds(40, 220, 170, 50);
        lblSRate.setFont(FontsTheme.Plain_Texts);
        lblSRate.setForeground(ColorsTheme.Text_Black);
         
        cmbSRate = new JComboBox<>(rates);
        cmbSRate.setBounds(250, 230, 250, 30);
        cmbSRate.setFont(FontsTheme.Plain_Texts);
        cmbSRate.setForeground(ColorsTheme.Text_Black);
        cmbSRate.setBackground(ColorsTheme.Main_Card);
        
        lblCS = new JLabel("Comments & Suggestions");
        lblCS.setBounds(540, 10, 550, 30);
        lblCS.setFont(FontsTheme.Title_Texts);
        lblCS.setForeground(ColorsTheme.Text_Black);
        
        txaComs = new JTextArea(" ");
        txaComs.setText("Write here...");
        txaComs.setEditable(true);
        txaComs.setFont(FontsTheme.Dialog_Texts);
        txaComs.setForeground(ColorsTheme.Text_Gray);
        txaComs.setLineWrap(true);
        txaComs.setWrapStyleWord(true);
        
        scrollComs = new JScrollPane(txaComs);
        scrollComs.setBounds(550, 50, 350, 200);
    }
    
    public void showStaffInfo() {
        pnlForm.removeAll();
        pnlForm.add(lblEmpID);
        pnlForm.add(txtEmpID);
        pnlForm.add(lblName);
        pnlForm.add(txtName);
        pnlForm.add(lblBday);
        pnlForm.add(txtBday);
        pnlForm.add(lblGen);
        pnlForm.add(cmbGen);
        pnlForm.add(lblEmail);
        pnlForm.add(txtEmail);
        pnlForm.add(lblContact);
        pnlForm.add(txtContact);
        pnlForm.add(lblMarital);
        pnlForm.add(cmbMarital);
        pnlForm.add(lblDep);
        pnlForm.add(txtDep);
        pnlForm.add(lblRole);
        pnlForm.add(txtRole);
        pnlForm.add(lblHired);
        pnlForm.add(txtHired);
        pnlForm.add(lblOff);
        pnlForm.add(cmbOff);
        pnlForm.add(lblStatus);
        pnlForm.add(cmbStats);
        pnlForm.repaint();
        pnlForm.revalidate();
    }
    
    public void showPerfRate() {
        pnlForm.removeAll();
        pnlForm.add(lblPR);
        pnlForm.add(lblCName);
        pnlForm.add(txtCName);
        pnlForm.add(lblLine);
        pnlForm.add(lblSName);
        pnlForm.add(txtSName);
        pnlForm.add(lblSRole);
        pnlForm.add(cmbSRole);
        pnlForm.add(lblSRate);
        pnlForm.add(cmbSRate);
        pnlForm.add(lblCS);
        pnlForm.add(scrollComs);
        pnlForm.repaint();
        pnlForm.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStaff) {
            showStaffInfo();
        } else if (e.getSource() == btnPerf) {
            showPerfRate();
        } else if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnSave) {
            // Extract core fields
            String empID = txtEmpID.getText().trim();
            String fullName = txtName.getText().trim();
            String birthday = txtBday.getText().trim();
            String gen = cmbGen.getSelectedItem().toString();
            String email = txtEmail.getText().trim();
            String contact = txtContact.getText().trim();
            String maritalStatus = cmbMarital.getSelectedItem().toString();
            String department = txtDep.getText().trim();
            String role = txtRole.getText().trim();
            String hireDate = txtHired.getText().trim();
            String dayOff = cmbOff.getSelectedItem().toString();
            String statusValue = cmbStats.getSelectedItem().toString();
            
            // Extract evaluation fields
            String clientName = txtCName.getText().trim();
            String evalStaffName = txtSName.getText().trim();
            String evalRole = cmbSRole.getSelectedItem().toString();
            String perfRate = cmbSRate.getSelectedItem().toString();
            String comments = txaComs.getText().trim();
            
            if (comments.equals("Write here...")) {
                comments = "";
            }

            // Simple validation structure mimicking template
            if (empID.isEmpty() || fullName.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Employee ID, Full Name, and Role are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Database Save Implementation matching template mechanics
            String sql = "INSERT INTO hospital_staff (employee_id, full_name, birthday, gender, email, contact_number, marital_status, department, role, hire_date, day_off, status, client_name, eval_staff_name, eval_role, performance_rate, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = conn.prepareStatement(sql)) {
                
                insert.setString(1, empID);
                insert.setString(2, fullName);
                insert.setString(3, birthday);
                insert.setString(4, gen);
                insert.setString(5, email);
                insert.setString(6, contact);
                insert.setString(7, maritalStatus);
                insert.setString(8, department);
                insert.setString(9, role);
                insert.setString(10, hireDate);
                insert.setString(11, dayOff);
                insert.setString(12, statusValue);
                insert.setString(13, clientName);
                insert.setString(14, evalStaffName);
                insert.setString(15, evalRole);
                insert.setString(16, perfRate);
                insert.setString(17, comments);

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