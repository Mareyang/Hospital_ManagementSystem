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



public class NewPatientDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblID, lblAge, lblFirst, lblLast, lblNumber, lblGender, lblStatus, lblEmail, lblAddress;
    private JTextField txtID, txtAge, txtFirst, txtLast, txtBirth, txtNumber, txtEmail, txtAddress;
    private JComboBox<String> cmbGender, cmbStatus;
    private JButton btnPersonal, btnCancel, btnSave;
    
    private static final String[] genderList = {"Select Gender...", "Male", "Female", "Other"};
    private static final String[] statusList = {"Select Status...", "Outpatient", "Admitted"}; 
    // Note: We only allow Outpatient or Admitted for brand new patients. 
    // Discharged/Deceased are updated later!

    
    public NewPatientDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("New Patient Registration");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all required fields to register a new patient.");
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
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnSave = new JButton("Save Information");
        btnSave.setBounds(790, 450, 200, 30);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setBackground(ColorsTheme.Green);
        btnSave.setFocusPainted(false);
        add(btnSave);
        
        // --- LEFT COLUMN ---
        lblID = new JLabel("Patient ID : "); 
        lblID.setBounds(40, 40, 200, 30); 
        lblID.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblID);
        
        txtID = new JTextField("Auto-generated"); 
        txtID.setBounds(220, 40, 230, 30); 
        txtID.setFont(FontsTheme.Plain_Texts); 
        txtID.setEditable(false); // Locked
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
        
        txtBirth = new JTextField("(YYYY-MM-DD)"); 
        txtBirth.setBounds(220, 190, 230, 30);
        txtBirth.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(txtBirth);

        lblAge = new JLabel("Age : "); 
        lblAge.setBounds(40, 240, 200, 30); 
        lblAge.setFont(FontsTheme.Plain_Texts); 
        pnlContent.add(lblAge);
        
        txtAge = new JTextField("Auto-calculated"); 
        txtAge.setBounds(220, 240, 230, 30);
        txtAge.setFont(FontsTheme.Plain_Texts); 
        txtAge.setEditable(false); // Locked
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

        lblStatus = new JLabel("Initial Status : "); 
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
        btnSave.addActionListener(this);
    }
    
    private void savePatientToDatabase() {
        // 1. Grab all the text from the fields
        String first = txtFirst.getText().trim();
        String last = txtLast.getText().trim();
        String birth = txtBirth.getText().trim();
        String gender = cmbGender.getSelectedItem().toString();
        String contact = txtNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String statusText = cmbStatus.getSelectedItem().toString();
        
        // 2. Simple Validation (Make sure they didn't leave blanks)
        if (first.isEmpty() || last.isEmpty() || birth.isEmpty() || gender.equals("Select Gender...") || statusText.equals("Select Status...")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields (Name, Birthday, Gender, Status).", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Map the text status back to the database ID (1 = Outpatient, 2 = Admitted)
        int statusId = statusText.equals("Outpatient") ? 1 : 2 ;

        // 3. Connect to DB and Insert
        String sql = "INSERT INTO patients (first_name, last_name, birthday, gender, contact_number, email, address, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
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
            
            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Patient successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the dialog box
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: Please check date format (YYYY-MM-DD) or connection.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnSave) {
            savePatientToDatabase();
        }
    }
}