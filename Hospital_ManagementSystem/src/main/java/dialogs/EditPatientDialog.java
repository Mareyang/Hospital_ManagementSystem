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
import java.time.LocalDate;
import java.time.Period;
import javax.swing.*;



public class EditPatientDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblFirst, lblLast, lblNumber, lblGender, lblStatus, lblEmail, lblAddress, lblID, lblAge;
    private JTextField txtFirst, txtLast, txtBirth, txtNumber, txtEmail, txtAddress, txtID, txtAge;
    private JComboBox<String> cmbGender, cmbStatus;
    private JButton btnPersonal, btnCancel, btnUpdate;
    
    private static final String[] genderList = {"Select Gender...", "Male", "Female", "Other"};
    private static final String[] statusList = {"Select Status...", "Outpatient", "Admitted", "Discharged", "Transferred", "Deceased"}; 
    
    private String currentPatientId;

    public EditPatientDialog(String patientId) {
        // Strip out the "PAT-" prefix for the database search
        this.currentPatientId = patientId.replaceAll("(?i)[A-Z]+-", "");
        
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Edit Patient Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Update the patient's information below.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnPersonal = new JButton("Personal Information");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Header);
        btnPersonal.setFocusPainted(false);
        add(btnPersonal);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnUpdate = new JButton("Update Information");
        btnUpdate.setBounds(690, 450, 300, 30);
        btnUpdate.setFont(FontsTheme.Buttons);
        btnUpdate.setForeground(ColorsTheme.Text_White);
        btnUpdate.setBackground(ColorsTheme.Green);
        btnUpdate.setFocusPainted(false);
        add(btnUpdate);
        
        
        // --- LEFT COLUMN ---
        lblID = new JLabel("Patient ID : "); 
        lblID.setBounds(40, 40, 200, 30); 
        lblID.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblID);
        
        txtID = new JTextField(""); 
        txtID.setBounds(220, 40, 230, 30); 
        txtID.setFont(FontsTheme.Plain_Texts); 
        txtID.setEditable(false); // Locked so users can't edit the ID
        pnlContent.add(txtID);
        
        lblFirst = new JLabel("First Name : "); 
        lblFirst.setBounds(40, 90, 200, 30); 
        lblFirst.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblFirst);
        
        txtFirst = new JTextField(); 
        txtFirst.setBounds(220, 90, 230, 30); 
        txtFirst.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtFirst);
        
        lblLast = new JLabel("Last Name : "); 
        lblLast.setBounds(40, 140, 200, 30); 
        lblLast.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblLast);
        
        txtLast = new JTextField(); 
        txtLast.setBounds(220, 140, 230, 30);
        txtLast.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtLast);
        
        lblBirth = new JLabel("Birthday :"); 
        lblBirth.setBounds(40, 190, 200, 30); 
        lblBirth.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblBirth);
        
        txtBirth = new JTextField(); 
        txtBirth.setBounds(220, 190, 230, 30);
        txtBirth.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtBirth);

        lblAge = new JLabel("Age : "); 
        lblAge.setBounds(40, 240, 200, 30); 
        lblAge.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblAge);
        
        txtAge = new JTextField(""); 
        txtAge.setBounds(220, 240, 230, 30);
        txtAge.setFont(FontsTheme.Plain_Texts); 
        txtAge.setEditable(false); // Locked (Auto-calculates)
        pnlContent.add(txtAge);
        
        // --- RIGHT COLUMN ---
        lblNumber = new JLabel("Contact Number : "); 
        lblNumber.setBounds(510, 40, 200, 30); 
        lblNumber.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblNumber);
        
        txtNumber = new JTextField(); 
        txtNumber.setBounds(690, 40, 230, 30);
        txtNumber.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtNumber);
        
        lblAddress = new JLabel("Home Address : "); 
        lblAddress.setBounds(510, 90, 200, 30); 
        lblAddress.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblAddress);
        
        txtAddress = new JTextField(); 
        txtAddress.setBounds(690, 90, 230, 30); 
        txtAddress.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtAddress);
        
        lblEmail = new JLabel("Email Address : "); 
        lblEmail.setBounds(510, 140, 200, 30); 
        lblEmail.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblEmail);
        
        txtEmail = new JTextField(); 
        txtEmail.setBounds(690, 140, 230, 30); 
        txtEmail.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtEmail);
        
        lblGender = new JLabel("Gender : "); 
        lblGender.setBounds(510, 190, 200, 30); 
        lblGender.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblGender);
        
        cmbGender = new JComboBox<>(genderList);
        cmbGender.setBounds(690, 190, 230, 30);
        cmbGender.setFont(FontsTheme.Plain_Texts); 
        cmbGender.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbGender);

        lblStatus = new JLabel("Patient Status : "); 
        lblStatus.setBounds(510, 240, 200, 30); 
        lblStatus.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblStatus);
        
        cmbStatus = new JComboBox<>(statusList);
        cmbStatus.setBounds(690, 240, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts); 
        cmbStatus.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbStatus);
        
        // ActionListeners
        btnCancel.addActionListener(this);
        btnUpdate.addActionListener(this);
        
        // Load the existing data immediately when the dialog opens
        loadExistingData();
    }
    
    private void loadExistingData() {
        if (currentPatientId == null || currentPatientId.trim().isEmpty()) {
            return; 
        }

        String sql = "SELECT p.*, s.status_name FROM patients p " +
                     "LEFT JOIN patient_status s ON p.status_id = s.status_id " +
                     "WHERE p.patient_id = ?";
                     
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, currentPatientId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Set the ID field to show the formatted ID
                txtID.setText(String.format("PAT-%03d", rs.getInt("patient_id")));
                
                txtFirst.setText(rs.getString("first_name"));
                txtLast.setText(rs.getString("last_name"));
                
                // Birthday & Dynamic Age Calculation
                java.sql.Date bday = rs.getDate("birthday");
                if (bday != null) {
                    txtBirth.setText(bday.toString());
                    LocalDate birthLocalDate = bday.toLocalDate();
                    int calculatedAge = Period.between(birthLocalDate, LocalDate.now()).getYears();
                    txtAge.setText(String.valueOf(calculatedAge));
                }
                
                String gen = rs.getString("gender");
                if (gen != null) cmbGender.setSelectedItem(gen);
                
                txtNumber.setText(rs.getString("contact_number"));
                txtAddress.setText(rs.getString("address"));
                txtEmail.setText(rs.getString("email"));
                
                String stats = rs.getString("status_name");
                if (stats != null) cmbStatus.setSelectedItem(stats);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patient data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatePatientInDatabase() {
        String first = txtFirst.getText().trim();
        String last = txtLast.getText().trim();
        String birth = txtBirth.getText().trim();
        String gender = cmbGender.getSelectedItem().toString();
        String contact = txtNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String statusText = cmbStatus.getSelectedItem().toString();
        
        if (first.isEmpty() || last.isEmpty() || birth.isEmpty() || gender.equals("Select Gender...") || statusText.equals("Select Status...")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Map text status to ID based on your patient_status table
        int statusId = 1; // Default Outpatient
        if (statusText.equals("Admitted")) statusId = 2;
        else if (statusText.equals("Discharged")) statusId = 3;
        else if (statusText.equals("Transferred")) statusId = 4;
        else if (statusText.equals("Deceased")) statusId = 5;

        String sql = "UPDATE patients SET first_name=?, last_name=?, birthday=?, gender=?, contact_number=?, email=?, address=?, status_id=? WHERE patient_id=?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, birth); 
            stmt.setString(4, gender);
            stmt.setString(5, contact);
            stmt.setString(6, email);
            stmt.setString(7, address);
            stmt.setInt(8, statusId);
            stmt.setString(9, currentPatientId);
            
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Patient record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: Please check date format (YYYY-MM-DD).\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnUpdate) {
            updatePatientInDatabase();
        }
    }
}