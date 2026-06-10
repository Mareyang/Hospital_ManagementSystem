/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * 
 */
public class AddMedicalRecordDialog extends JDialog implements ActionListener {
  
    private JPanel pnlContent;
   
    private JLabel lblTitle, lblSubtitle, lblBP, lblHR, lblTemp, lblWeight, lblHeight, lblPatient, lblID, lblType, lblDoctor, lblDate, lblRecorded, lblTime;
    private JTextField txtPatient, txtID, txtDoctor, txtDate, txtHeight, txtWeight, txtBloodPressure, txtHeartRate, txtTemperature, txtRecorded, txtTime;
    private JComboBox<String> cmbType, cmbDoctor;
    private JButton btnAddInfo, btnCancel, btnMed;
    
    private static final String[] type ={" ", "Consultation", "Lab Result", "Imaging", "Procedure", "Surgery", "Follow-up", "Other"};
    private static final String[] doctors = {" ", "Dr. Juan dela Cruz", "Dr. Maria Santos", "Dr. Ricardo Reyes", "Dr. Elena Garcia", "Dr. Roberto Castro"};


    
    
    
    public AddMedicalRecordDialog(int recordId, boolean readOnly) {
        this();
        loadRecord(recordId);
        if (readOnly) {
            setReadOnly();
        }
    }

    public AddMedicalRecordDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
      
        lblTitle = new JLabel("Patient Medical Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
      
        lblSubtitle = new JLabel("Manage patient case details and medical history.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
      
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
      
        
        // Buttons for Medical Records, Cancel and Save Record
        btnMed = new JButton("Medical Records");
        btnMed.setBounds(40, 100, 250, 40);
        btnMed.setFont(FontsTheme.Buttons);
        btnMed.setForeground(ColorsTheme.Text_White);
        btnMed.setBackground(ColorsTheme.Header);
        btnMed.setFocusPainted(false);
        add(btnMed);
        
       
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
      
        btnAddInfo = new JButton("Save Record");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
       
        
       
        
        // Medical Record Form
        
        // Patient ID Label and TextField
        lblID = new JLabel("Patient ID : ");
        lblID.setBounds(40, 30, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
      
        txtID = new JTextField("");
        txtID.setBounds(220, 30, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        // Patient Name Label and TextField
        lblPatient = new JLabel("Patient Name : ");
        lblPatient.setBounds(40, 70, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatient);
      
        txtPatient = new JTextField("");
        txtPatient.setBounds(220, 70, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtPatient);
      
        // Type of medical concern
        lblType = new JLabel("Medical Type : ");
        lblType.setBounds(40, 110, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
      
        // Options for type of medical concern
        cmbType = new JComboBox<>(type);
        cmbType.setBounds(220, 110, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setForeground(ColorsTheme.Text_Black);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        
        // Height Label and TextField
        lblHeight = new JLabel("Height (cm) : ");
        lblHeight.setBounds(40, 150, 200, 30);
        lblHeight.setFont(FontsTheme.Plain_Texts);
        lblHeight.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblHeight);
      
        txtHeight = new JTextField("");
        txtHeight.setBounds(220, 150, 230, 30);
        txtHeight.setFont(FontsTheme.Plain_Texts);
        txtHeight.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtHeight);

        // Weight Label and TextField
        lblWeight = new JLabel("Weight (kg) : ");
        lblWeight.setBounds(40, 190, 200, 30);
        lblWeight.setFont(FontsTheme.Plain_Texts);
        lblWeight.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblWeight);
      
        txtWeight = new JTextField("");
        txtWeight.setBounds(220, 190, 230, 30);
        txtWeight.setFont(FontsTheme.Plain_Texts);
        txtWeight.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtWeight);
        
        // Record Label and TextField
        lblRecorded = new JLabel("Recorded by : ");
        lblRecorded.setBounds(40, 230, 200, 30);
        lblRecorded.setFont(FontsTheme.Plain_Texts);
        lblRecorded.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRecorded);
      
        txtRecorded = new JTextField("");
        txtRecorded.setBounds(220, 230, 230, 30);
        txtRecorded.setFont(FontsTheme.Plain_Texts);
        txtRecorded.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtRecorded);
        
      
        
        //LEFT : Additional info needed for form
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(510, 30, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
      
        txtDate = new JTextField(""); 
        txtDate.setBounds(690, 30, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 70, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
      
        txtTime = new JTextField(""); 
        txtTime.setBounds(690, 70, 230, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtTime);
        
        // Doctor name Label and TextField
        lblDoctor = new JLabel("Doctor :");
        lblDoctor.setBounds(510, 110, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);
        
        cmbDoctor = new JComboBox<>(doctors);
        cmbDoctor.setBounds(690, 110, 230, 30);
        cmbDoctor.setFont(FontsTheme.Plain_Texts);
        cmbDoctor.setForeground(ColorsTheme.Text_Black);
        cmbDoctor.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoctor);
        

        // Info needed for Vital Signs
        lblBP = new JLabel("Blood Pressure :");
        lblBP.setBounds(510, 150, 200, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        
        txtBloodPressure = new JTextField("");
        txtBloodPressure.setBounds(690, 150, 230, 30);
        txtBloodPressure.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtBloodPressure);

        lblHR = new JLabel("Heart Rate (bpm) :");
        lblHR.setBounds(510, 190, 200, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        
        txtHeartRate = new JTextField("");
        txtHeartRate.setBounds(690, 190, 230, 30);
        txtHeartRate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHeartRate);

        
        lblTemp = new JLabel("Temperature (°C) :");
        lblTemp.setBounds(510, 230, 200, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        
        txtTemperature = new JTextField("");
        txtTemperature.setBounds(690, 230, 230, 30);
        txtTemperature.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTemperature);
        
        
        
        
        // ActionListeners
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
        
        
    }

    private void loadRecord(int recordId) {
        String sql = "SELECT * FROM medical_records WHERE record_id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    txtID.setText("PAT-" + rs.getInt("patient_id"));
                    txtPatient.setText(rs.getString("patient_name"));
                    cmbType.setSelectedItem(rs.getString("record_type"));
                    txtHeight.setText(String.valueOf(rs.getDouble("height")));
                    txtWeight.setText(String.valueOf(rs.getDouble("weight")));
                    txtRecorded.setText(rs.getString("recorded_by"));
                    txtDate.setText(rs.getString("record_date"));
                    txtTime.setText(rs.getString("record_time"));
                    cmbDoctor.setSelectedItem(rs.getString("doctor"));
                    txtBloodPressure.setText(rs.getString("blood_pressure"));
                    txtHeartRate.setText(rs.getString("heart_rate"));
                    txtTemperature.setText(String.valueOf(rs.getDouble("temperature")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load medical record:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setReadOnly() {
        lblTitle.setText("View Medical Record");
        lblSubtitle.setText("Viewing details of patient case record.");
        
        txtID.setEditable(false);
        txtPatient.setEditable(false);
        cmbType.setEnabled(false);
        txtHeight.setEditable(false);
        txtWeight.setEditable(false);
        txtRecorded.setEditable(false);
        txtDate.setEditable(false);
        txtTime.setEditable(false);
        cmbDoctor.setEnabled(false);
        txtBloodPressure.setEditable(false);
        txtHeartRate.setEditable(false);
        txtTemperature.setEditable(false);

        btnAddInfo.setVisible(false); // Hide Save button
        btnCancel.setText("Close");
        btnCancel.setBounds(790, 450, 200, 30); // Move Close button to Save button's position
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            // Basic validation
            if (txtPatient.getText().trim().isEmpty() || txtID.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all the required information.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO medical_records (patient_id, patient_name, record_type, height, weight, recorded_by, record_date, record_time, doctor, blood_pressure, heart_rate, temperature) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = connection.prepareStatement(sql)) {
                
                String rawPatientInput = txtID.getText().trim().toUpperCase().replace("PAT-", "");
                insert.setInt(1, Integer.parseInt(rawPatientInput));
                
                insert.setString(2, txtPatient.getText().trim());
                insert.setString(3, cmbType.getSelectedItem().toString());
                
                String rawHeight = txtHeight.getText().trim();
                insert.setDouble(4, rawHeight.isEmpty() ? 0.0 : Double.parseDouble(rawHeight));
                
                String rawWeight = txtWeight.getText().trim();
                insert.setDouble(5, rawWeight.isEmpty() ? 0.0 : Double.parseDouble(rawWeight));
                
                insert.setString(6, txtRecorded.getText().trim());
                insert.setString(7, txtDate.getText().trim());
                insert.setString(8, txtTime.getText().trim());
                
                insert.setString(9, cmbDoctor.getSelectedItem().toString());
                insert.setString(10, txtBloodPressure.getText().trim());
                insert.setString(11, txtHeartRate.getText().trim());
                
                String rawTemp = txtTemperature.getText().trim();
                insert.setDouble(12, rawTemp.isEmpty() ? 0.0 : Double.parseDouble(rawTemp));

                
                int rowsAffected = insert.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Medical record added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please ensure Patient ID, Height, Weight, and Temperature are valid numeric formats.", "Input Error", JOptionPane.ERROR_MESSAGE);
                
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error:\n" + sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}