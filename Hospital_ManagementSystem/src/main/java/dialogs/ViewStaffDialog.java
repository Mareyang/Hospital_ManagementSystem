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

public class ViewStaffDialog extends JDialog implements ActionListener {
      
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails, lblEmpID, lblName, lblBday, lblGen, lblEmail, lblContact, 
            lblMarital, lblDep, lblRole, lblHired, lblOff, lblStatus, lblPR, lblCName, lblSName, lblLine, lblSRole, lblSRate, lblCS;
    private JTextField txtEmpID, txtName, txtContact, txtEmail, txtBday, txtDep, txtHired, txtCName, txtSName, txtSRole;
    private JButton btnStaff, btnPerf, btnClose;
    private JComboBox<String> cmbGen, cmbMarital, cmbStats, cmbOff, cmbRole, cmbSRate;
    private JTextArea txaComs;
    private JScrollPane scrollComs;
    
    private static final String[] gender = {" ", "Male", "Female", "Prefer not to say"};
    private static final String[] days = {" ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] status = {" ", "Active", "On Leave", "Contract", "Off Duty"};
    private static final String[] marital = {" ", "Single", "Married", "Divorced", "Widowed"};
    private static final String[] roles = {" ", "Doctor", "Nurse", "Admin"};
    private static final String[] rates = {" ", "5 - Excellent", "4 - Very Good", "3 - Average", "2 - Below Average", "1- Poor"};
    
    private String currentEmpId;

    public ViewStaffDialog(String empId) {
        this.currentEmpId = empId;
        
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        lblDialogTitle = new JLabel("View Staff Profile");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("View existing hospital personnel information and performance records.");
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
        
        btnClose = new JButton("Close");
        btnClose.setBounds(690, 450, 300, 30);
        btnClose.setFont(FontsTheme.Buttons);
        btnClose.setForeground(ColorsTheme.Text_White);
        btnClose.setBackground(ColorsTheme.Search);
        btnClose.setFocusPainted(false);
        add(btnClose);
        
        btnStaff.addActionListener(this);
        btnPerf.addActionListener(this);
        btnClose.addActionListener(this);
        
        initializeForms();
        loadStaffData();
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
        txtEmpID.setEditable(false); 

        lblName = new JLabel("Full Name :");
        lblName.setBounds(40, 80, 170, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);

        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        txtName.setEditable(false);

        lblBday = new JLabel("Birthday :");
        lblBday.setBounds(40, 120, 170, 30);
        lblBday.setFont(FontsTheme.Plain_Texts);
        lblBday.setForeground(ColorsTheme.Text_Black);

        txtBday = new JTextField("");
        txtBday.setBounds(220, 120, 230, 30);
        txtBday.setFont(FontsTheme.Plain_Texts);
        txtBday.setForeground(ColorsTheme.Text_Black);
        txtBday.setEditable(false);

        lblGen = new JLabel("Gender :");
        lblGen.setBounds(40, 160, 170, 30);
        lblGen.setFont(FontsTheme.Plain_Texts);
        lblGen.setForeground(ColorsTheme.Text_Black);

        cmbGen = new JComboBox<>(gender);
        cmbGen.setBounds(220, 160, 230, 30);
        cmbGen.setFont(FontsTheme.Plain_Texts);
        cmbGen.setForeground(ColorsTheme.Text_Black);
        cmbGen.setBackground(ColorsTheme.Main_Card);
        cmbGen.setEnabled(false);
        
        lblEmail = new JLabel("Email Address: ");
        lblEmail.setBounds(40, 200, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 200, 230, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        txtEmail.setEditable(false);

        lblContact = new JLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        lblContact.setFont(FontsTheme.Plain_Texts);
        lblContact.setForeground(ColorsTheme.Text_Black);

        txtContact = new JTextField("");
        txtContact.setBounds(220, 240, 230, 30);
        txtContact.setFont(FontsTheme.Plain_Texts);
        txtContact.setForeground(ColorsTheme.Text_Black);
        txtContact.setEditable(false);
        
        lblMarital = new JLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        lblMarital.setFont(FontsTheme.Plain_Texts);
        lblMarital.setForeground(ColorsTheme.Text_Black);

        cmbMarital = new JComboBox<>(marital);
        cmbMarital.setBounds(720, 40, 180, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts);
        cmbMarital.setForeground(ColorsTheme.Text_Black);
        cmbMarital.setBackground(ColorsTheme.Main_Card);
        cmbMarital.setEnabled(false);

        lblDep = new JLabel("Department :");
        lblDep.setBounds(540, 80, 170, 30);
        lblDep.setFont(FontsTheme.Plain_Texts);
        lblDep.setForeground(ColorsTheme.Text_Black);

        txtDep = new JTextField("");
        txtDep.setBounds(720, 80, 180, 30);
        txtDep.setFont(FontsTheme.Plain_Texts);
        txtDep.setForeground(ColorsTheme.Text_Black);
        txtDep.setEditable(false);

        lblRole = new JLabel("Role :");
        lblRole.setBounds(540, 120, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);

        cmbRole = new JComboBox<>(roles);
        cmbRole.setBounds(720, 120, 180, 30);
        cmbRole.setFont(FontsTheme.Plain_Texts);
        cmbRole.setForeground(ColorsTheme.Text_Black);
        cmbRole.setBackground(ColorsTheme.Main_Card);
        cmbRole.setEnabled(false);

        lblHired = new JLabel("Hire Date :");
        lblHired.setBounds(540, 160, 170, 30);
        lblHired.setFont(FontsTheme.Plain_Texts);
        lblHired.setForeground(ColorsTheme.Text_Black);

        txtHired = new JTextField(" ");
        txtHired.setBounds(720, 160, 180, 30);
        txtHired.setFont(FontsTheme.Plain_Texts);
        txtHired.setForeground(ColorsTheme.Text_Black);
        txtHired.setEditable(false);

        lblOff = new JLabel("Day Off :");
        lblOff.setBounds(540, 200, 170, 30);
        lblOff.setFont(FontsTheme.Plain_Texts);
        lblOff.setForeground(ColorsTheme.Text_Black);

        cmbOff = new JComboBox<>(days);
        cmbOff.setBounds(720, 200, 180, 30);
        cmbOff.setFont(FontsTheme.Plain_Texts);
        cmbOff.setForeground(ColorsTheme.Text_Black);
        cmbOff.setBackground(ColorsTheme.Main_Card);
        cmbOff.setEnabled(false);

        lblStatus = new JLabel("Status :");
        lblStatus.setBounds(540, 240, 170, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);

        cmbStats = new JComboBox<>(status);
        cmbStats.setBounds(720, 240, 180, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);
        cmbStats.setEnabled(false);

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
        txtCName.setEditable(false);
          
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
        txtSName.setEditable(false);
        
        lblSRole = new JLabel("Role : ");
        lblSRole.setBounds(40, 180, 170, 50);
        lblSRole.setFont(FontsTheme.Plain_Texts);
        lblSRole.setForeground(ColorsTheme.Text_Black);
         
        txtSRole = new JTextField("");
        txtSRole.setBounds(250, 190, 250, 30);
        txtSRole.setFont(FontsTheme.Plain_Texts);
        txtSRole.setForeground(ColorsTheme.Text_Black);
        txtSRole.setEditable(false);
        
        lblSRate = new JLabel("Rate (1-5) : ");
        lblSRate.setBounds(40, 220, 170, 50);
        lblSRate.setFont(FontsTheme.Plain_Texts);
        lblSRate.setForeground(ColorsTheme.Text_Black);
         
        cmbSRate = new JComboBox<>(rates);
        cmbSRate.setBounds(250, 230, 250, 30);
        cmbSRate.setFont(FontsTheme.Plain_Texts);
        cmbSRate.setForeground(ColorsTheme.Text_Black);
        cmbSRate.setBackground(ColorsTheme.Main_Card);
        cmbSRate.setEnabled(false);
        
        lblCS = new JLabel("Comments & Suggestions");
        lblCS.setBounds(540, 10, 550, 30);
        lblCS.setFont(FontsTheme.Title_Texts);
        lblCS.setForeground(ColorsTheme.Text_Black);
        
        txaComs = new JTextArea(" ");
        txaComs.setText("Write here...");
        txaComs.setEditable(false);
        txaComs.setFont(FontsTheme.Dialog_Texts);
        txaComs.setForeground(ColorsTheme.Text_Gray);
        txaComs.setLineWrap(true);
        txaComs.setWrapStyleWord(true);
        
        scrollComs = new JScrollPane(txaComs);
        scrollComs.setBounds(550, 50, 350, 200);
    }

    private void loadStaffData() {
        String sql = "SELECT * FROM hospital_staff WHERE employee_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String cleanId = currentEmpId.replaceAll("[A-Z]+-", "");
            stmt.setString(1, cleanId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int rawId = rs.getInt("employee_id");
                String role = rs.getString("role");
                String prefix = "EMP-";
                if ("Admin".equalsIgnoreCase(role)) prefix = "ADM-";
                else if ("Doctor".equalsIgnoreCase(role)) prefix = "DOC-";
                else if ("Nurse".equalsIgnoreCase(role)) prefix = "NUR-";
                txtEmpID.setText(String.format("%s%03d", prefix, rawId));
                txtName.setText(rs.getString("full_name"));
                txtBday.setText(rs.getString("birthday"));
                
                String gen = rs.getString("gender");
                if (gen != null) cmbGen.setSelectedItem(gen);
                
                txtEmail.setText(rs.getString("email"));
                txtContact.setText(rs.getString("contact_number"));
                
                String maritalStats = rs.getString("marital_status");
                if (maritalStats != null) cmbMarital.setSelectedItem(maritalStats);
                
                txtDep.setText(rs.getString("department"));
                if (role != null) cmbRole.setSelectedItem(role);
                txtHired.setText(rs.getString("hire_date"));
                
                String off = rs.getString("day_off");
                if (off != null) cmbOff.setSelectedItem(off);
                
                String stats = rs.getString("status");
                if (stats != null) cmbStats.setSelectedItem(stats);
                
                txtCName.setText(rs.getString("client_name"));
                txtSName.setText(rs.getString("eval_staff_name"));
                
                String evalRole = rs.getString("eval_role");
                if (evalRole != null) txtSRole.setText(evalRole);
                
                String perfRate = rs.getString("performance_rate");
                if (perfRate != null) cmbSRate.setSelectedItem(perfRate);
                
                String comments = rs.getString("comments");
                if (comments != null && !comments.isEmpty()) {
                    txaComs.setText(comments);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load staff data:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
        pnlForm.add(cmbRole);
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
        pnlForm.add(txtSRole);
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
        } else if (e.getSource() == btnClose) {
            dispose();
        }
    }
}
