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

public class AddPatientDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblID, lblAge, lblNumber, lblGender, lblStatus, lblEmail, lblAddress, 
            lblRoom, lblMarital, lblFirst, lblLast;
    private JTextField txtID, txtAge, txtNumber, txtEmail, txtAddress, txtBirth, txtFirst, txtLast;
    private JButton btnPersonal, btnAddInfo, btnCancel;
    private JComboBox<String> cmbStatus, cmbGender, cmbMarital, cmbRoom;
    private int patientId = -1;
    private boolean editMode = false;
    private boolean viewMode = false;
    
    private static final String[] gender = {" ", "Male", "Female", "Prefer not to say"};
    private static final String[] status = {" ", "Admitted", "Discharged", "Observation"};
    private static final String[] room = {" ", "ER-01", "ER-02", "LAB-01", "LAB-02", "RM-201", "RM-202", "XRAY-01", "ICU-01", "ICU-02", "OR-01"};
    private static final String[] marital = {" ", "Single", "Married", "Divorced", "Widowed"};

    
    
    public AddPatientDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Information");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
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
        
        btnAddInfo = new JButton("Save Information");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Green);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        
        
       
        // Personal Information Form
        lblID = new JLabel("Patient ID : "); 
        lblID.setBounds(40, 40, 200, 30); 
        lblID.setFont(FontsTheme.Plain_Texts); 
        lblID.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblID);
        
        txtID = new JTextField("Auto-generated"); 
        txtID.setBounds(220, 40, 230, 30); 
        txtID.setFont(FontsTheme.Plain_Texts); 
        txtID.setForeground(ColorsTheme.Text_Black);
        txtID.setEditable(false);
        pnlContent.add(txtID);
        
        lblFirst = new JLabel("First Name : "); 
        lblFirst.setBounds(40, 80, 200, 30); 
        lblFirst.setFont(FontsTheme.Plain_Texts); 
        lblFirst.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblFirst);
        
        txtFirst = new JTextField(""); 
        txtFirst.setBounds(220, 80, 230, 30); 
        txtFirst.setFont(FontsTheme.Plain_Texts); 
        txtFirst.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtFirst);
        
        lblLast = new JLabel("Last Name : "); 
        lblLast.setBounds(40, 120, 200, 30); 
        lblLast.setFont(FontsTheme.Plain_Texts); 
        lblLast.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblLast);
        
        txtLast = new JTextField(""); 
        txtLast.setBounds(220, 120, 230, 30);
        txtLast.setFont(FontsTheme.Plain_Texts); 
        txtLast.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtLast);
        
        lblAge = new JLabel("Age : "); 
        lblAge.setBounds(40, 160, 200, 30); 
        lblAge.setFont(FontsTheme.Plain_Texts); 
        lblAge.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblAge);
        
        txtAge = new JTextField(""); 
        txtAge.setBounds(220, 160, 230, 30);
        txtAge.setFont(FontsTheme.Plain_Texts); 
        txtAge.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAge);
        
        lblBirth = new JLabel("Birthday : "); 
        lblBirth.setBounds(40, 200, 200, 30); 
        lblBirth.setFont(FontsTheme.Plain_Texts); 
        lblBirth.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblBirth);
        
        txtBirth = new JTextField(""); 
        txtBirth.setBounds(220, 200, 230, 30);
        txtBirth.setFont(FontsTheme.Plain_Texts); 
        txtBirth.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtBirth);
        
        lblGender = new JLabel("Gender : "); 
        lblGender.setBounds(40, 240, 200, 30); 
        lblGender.setFont(FontsTheme.Plain_Texts); 
        lblGender.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblGender);
        
        cmbGender = new JComboBox<>(gender);
        cmbGender.setBounds(220, 240, 230, 30);
        cmbGender.setFont(FontsTheme.Plain_Texts); 
        cmbGender.setForeground(ColorsTheme.Text_Black); 
        cmbGender.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbGender);
        
        lblNumber = new JLabel("Contact Number : "); 
        lblNumber.setBounds(510, 40, 200, 30); 
        lblNumber.setFont(FontsTheme.Plain_Texts); 
        lblNumber.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblNumber);
        
        txtNumber = new JTextField(""); 
        txtNumber.setBounds(690, 40, 230, 30);
        txtNumber.setFont(FontsTheme.Plain_Texts); 
        txtNumber.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtNumber);
        
        lblAddress = new JLabel("Home Address : "); 
        lblAddress.setBounds(510, 80, 200, 30); 
        lblAddress.setFont(FontsTheme.Plain_Texts);
        lblAddress.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblAddress);
        
        txtAddress = new JTextField(""); 
        txtAddress.setBounds(690, 80, 230, 30); 
        txtAddress.setFont(FontsTheme.Plain_Texts); 
        txtAddress.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtAddress);
        
        lblEmail = new JLabel("Email Address : "); 
        lblEmail.setBounds(510, 120, 200, 30); 
        lblEmail.setFont(FontsTheme.Plain_Texts); 
        lblEmail.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblEmail);
        
        txtEmail = new JTextField(""); 
        txtEmail.setBounds(690, 120, 230, 30); 
        txtEmail.setFont(FontsTheme.Plain_Texts); 
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtEmail);
        
        lblMarital = new JLabel("Marital Status : "); 
        lblMarital.setBounds(510, 160, 200, 30); 
        lblMarital.setFont(FontsTheme.Plain_Texts); 
        lblMarital.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblMarital);
        
        cmbMarital = new JComboBox<>(marital);
        cmbMarital.setBounds(690, 160, 230, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts); 
        cmbMarital.setForeground(ColorsTheme.Text_Black); 
        cmbMarital.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbMarital);
        
        lblStatus = new JLabel("Patient Status : "); 
        lblStatus.setBounds(510, 200, 200, 30); 
        lblStatus.setFont(FontsTheme.Plain_Texts); 
        lblStatus.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblStatus);
        
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(690, 200, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts); 
        cmbStatus.setForeground(ColorsTheme.Text_Black); 
        cmbStatus.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbStatus);
        
        lblRoom = new JLabel("Room Number : "); 
        lblRoom.setBounds(510, 240, 200, 30); 
        lblRoom.setFont(FontsTheme.Plain_Texts); 
        lblRoom.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblRoom);
        
        cmbRoom = new JComboBox<>(room);
        cmbRoom.setBounds(690, 240, 230, 30);
        cmbRoom.setFont(FontsTheme.Plain_Texts); 
        cmbRoom.setForeground(ColorsTheme.Text_Black); 
        cmbRoom.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbRoom);
        
       
        
        // ActionListener
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);

        
    }

    public AddPatientDialog(int patientId, boolean viewOnly) {
        this();
        this.patientId = patientId;
        this.editMode = !viewOnly;
        this.viewMode = viewOnly;

        lblTitle.setText(viewOnly ? "View Patient Record" : "Edit Patient Record");
        lblSubtitle.setText(viewOnly ? "Review patient information." : "Update patient record information.");
        btnAddInfo.setText(viewOnly ? "Close" : "Update Information");
        txtID.setEditable(false);

        loadPatient();

        if (viewOnly) {
            setFieldsEditable(false);
            btnCancel.setVisible(false);
            btnAddInfo.setBounds(790, 450, 200, 30);
        }
    }

    private void loadPatient() {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement select = connection.prepareStatement(sql)) {

            select.setInt(1, patientId);

            try (ResultSet result = select.executeQuery()) {
                if (result.next()) {
                    txtID.setText(String.format("PAT-%03d", result.getInt("patient_id")));
                    txtFirst.setText(result.getString("first_name"));
                    txtLast.setText(result.getString("last_name"));
                    txtAge.setText(String.valueOf(result.getInt("age")));
                    txtBirth.setText(result.getString("birthday"));
                    cmbGender.setSelectedItem(result.getString("gender"));
                    txtNumber.setText(result.getString("contact_number"));
                    txtAddress.setText(result.getString("address"));
                    txtEmail.setText(result.getString("email"));
                    cmbMarital.setSelectedItem(result.getString("marital_status"));
                    cmbStatus.setSelectedItem(result.getString("status"));
                    cmbRoom.setSelectedItem(result.getString("room_number"));
                } else {
                    JOptionPane.showMessageDialog(this, "Patient record not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patient:\n" + sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void setFieldsEditable(boolean editable) {
        txtFirst.setEditable(editable);
        txtLast.setEditable(editable);
        txtAge.setEditable(editable);
        txtBirth.setEditable(editable);
        txtNumber.setEditable(editable);
        txtAddress.setEditable(editable);
        txtEmail.setEditable(editable);
        cmbGender.setEnabled(editable);
        cmbMarital.setEnabled(editable);
        cmbStatus.setEnabled(editable);
        cmbRoom.setEnabled(editable);
    }
        
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            if (viewMode) {
                dispose();
                return;
            }

            if (txtFirst.getText().trim().isEmpty() || txtLast.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all the required information.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql;
            if (editMode) {
                sql = "UPDATE patients SET first_name = ?, last_name = ?, age = ?, birthday = ?, gender = ?, "
                        + "contact_number = ?, address = ?, email = ?, marital_status = ?, status = ?, room_number = ? "
                        + "WHERE patient_id = ?";
            } else {
                sql = "INSERT INTO patients (first_name, last_name, age, birthday, gender, "
                        + "contact_number, address, email, marital_status, status, room_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                
                statement.setString(1, txtFirst.getText().trim());
                statement.setString(2, txtLast.getText().trim());
                
                statement.setInt(3, Integer.parseInt(txtAge.getText().trim()));
                statement.setString(4, txtBirth.getText().trim());
                
                statement.setString(5, cmbGender.getSelectedItem().toString());
                statement.setString(6, txtNumber.getText().trim());
                statement.setString(7, txtAddress.getText().trim());
                statement.setString(8, txtEmail.getText().trim());
                statement.setString(9, cmbMarital.getSelectedItem().toString());
                statement.setString(10, cmbStatus.getSelectedItem().toString());
                statement.setString(11, cmbRoom.getSelectedItem().toString());
                if (editMode) {
                    statement.setInt(12, patientId);
                }

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    String message = editMode ? "Patient record updated successfully!" : "Patient record saved successfully!";
                    JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Age value must be a valid number.", "Input Numerical Error", JOptionPane.ERROR_MESSAGE);
                
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
     
        }
    
    }
