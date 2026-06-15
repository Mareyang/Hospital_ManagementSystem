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



public class NewMedicalRecordDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblPatientID, lblDate, lblTime, lblType, lblDoctor, lblHeight, lblWeight, lblBP, lblHR, lblTemp, lblRecordedBy, lblNote;
    private JTextField txtDate, txtTime, txtHeight, txtWeight, txtBP, txtHR, txtTemp;
    private JComboBox<String> cmbPatient, cmbDoctor, cmbRecordedBy, cmbType, cmbAmPm;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnAddInfo, btnCancel, btnMed;
    
    private static final String[] type = {"Select Type...", "Intake Vitals", "Progress Note", "Discharge Summary", "Lab Result"};
    private static final String[] ampm = {"AM", "PM"};

    
    
    public NewMedicalRecordDialog() {
        setSize(1050, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("New Medical Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Input patient case details, vitals, and medical history.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnMed = new JButton("Record Details");
        btnMed.setBounds(40, 100, 250, 40);
        btnMed.setFont(FontsTheme.Buttons);
        btnMed.setForeground(ColorsTheme.Text_White);
        btnMed.setBackground(ColorsTheme.Header);
        btnMed.setFocusPainted(false);
        add(btnMed);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 350); 
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 500, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnAddInfo = new JButton("Save Record");
        btnAddInfo.setBounds(690, 500, 300, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        
        
        // --- LEFT COLUMN ---
        lblPatientID = new JLabel("Patient :");
        lblPatientID.setBounds(40, 20, 200, 30);
        lblPatientID.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatientID);
        
        cmbPatient = new JComboBox<>();
        cmbPatient.setEditable(true);
        cmbPatient.setBounds(220, 20, 230, 30);
        cmbPatient.setFont(FontsTheme.Plain_Texts);
        cmbPatient.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbPatient);
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(40, 65, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("(YYYY-MM-DD)");
        txtDate.setBounds(220, 65, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
        
        lblType = new JLabel("Medical Type : ");
        lblType.setBounds(40, 110, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblType);
        
        cmbType = new JComboBox<>(type);
        cmbType.setBounds(220, 110, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        lblHeight = new JLabel("Height (cm) : ");
        lblHeight.setBounds(40, 155, 200, 30);
        lblHeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHeight);
        
        txtHeight = new JTextField();
        txtHeight.setBounds(220, 155, 230, 30);
        txtHeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHeight);
        
        lblWeight = new JLabel("Weight (kg) : ");
        lblWeight.setBounds(40, 200, 200, 30);
        lblWeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblWeight);
        
        txtWeight = new JTextField();
        txtWeight.setBounds(220, 200, 230, 30);
        txtWeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtWeight);
        
        lblRecordedBy = new JLabel("Recorded By: ");
        lblRecordedBy.setBounds(40, 245, 200, 30);
        lblRecordedBy.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblRecordedBy);
        
        // NOW A DROPDOWN
        cmbRecordedBy = new JComboBox<>(); 
        cmbRecordedBy.setEditable(true);
        cmbRecordedBy.setBounds(220, 245, 230, 30);
        cmbRecordedBy.setFont(FontsTheme.Plain_Texts);
        cmbRecordedBy.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbRecordedBy);

        // --- RIGHT COLUMN ---
        lblDoctor = new JLabel("Attending Doctor:");
        lblDoctor.setBounds(510, 20, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        cmbDoctor = new JComboBox<>();
        cmbDoctor.setEditable(true);
        cmbDoctor.setBounds(690, 20, 230, 30);
        cmbDoctor.setFont(FontsTheme.Plain_Texts);
        cmbDoctor.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoctor);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 65, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField("(HH:MM)");
        txtTime.setBounds(690, 65, 140, 30); 
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        cmbAmPm = new JComboBox<>(ampm);
        cmbAmPm.setBounds(840, 65, 80, 30);
        cmbAmPm.setFont(FontsTheme.Plain_Texts);
        cmbAmPm.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbAmPm);
        
        lblBP = new JLabel("Blood Pressure :");
        lblBP.setBounds(510, 110, 200, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        
        txtBP = new JTextField("(e.g., 120/80)");
        txtBP.setBounds(690, 110, 230, 30);
        txtBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtBP);

        lblHR = new JLabel("Heart Rate (bpm) :");
        lblHR.setBounds(510, 155, 200, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        
        txtHR = new JTextField();
        txtHR.setBounds(690, 155, 230, 30);
        txtHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHR);
        
        lblTemp = new JLabel("Temperature (°C) :");
        lblTemp.setBounds(510, 200, 200, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        
        txtTemp = new JTextField();
        txtTemp.setBounds(690, 200, 230, 30);
        txtTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTemp);
        
        // --- BOTTOM SPAN ---
        lblNote = new JLabel("Diagnosis / Notes");
        lblNote.setBounds(510, 245, 400, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(510, 275, 410, 50);
        pnlContent.add(scrollNote);
        
        // ActionListeners
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);

        // --- FETCH LIVE DATA FROM DATABASE ---
        loadDropdownData();
    }
    
    private void loadDropdownData() {
        cmbPatient.addItem("Select Patient...");
        cmbDoctor.addItem("Select Doctor...");
        cmbRecordedBy.addItem("Select Nurse...");
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            
            // 1. Fetch all Patients
            String patSql = "SELECT patient_id, first_name, last_name FROM patients ORDER BY patient_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(patSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getInt("patient_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name");
                    cmbPatient.addItem(fullName);
                }
            }
            
            // 2. Fetch all Doctors
            String docSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Doctor' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(docSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String docName = rs.getInt("user_id") + " - Dr. " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbDoctor.addItem(docName);
                }
            }
            
            // 3. Fetch all Nurses for "Recorded By"
            String nurseSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Nurse' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(nurseSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nurseName = rs.getInt("user_id") + " - " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbRecordedBy.addItem(nurseName);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Could not load dynamic dropdown lists: " + ex.getMessage());
        }
    }
    
    private void saveRecordToDatabase() {
        String patientInput = cmbPatient.getSelectedItem().toString();
        String doctorInput = cmbDoctor.getSelectedItem().toString();
        String recordedByInput = cmbRecordedBy.getSelectedItem().toString(); // Now gets text from dropdown
        String recordTypeString = cmbType.getSelectedItem().toString();
        
        String date = txtDate.getText().trim();
        String rawTime = txtTime.getText().trim();
        String amPm = cmbAmPm.getSelectedItem().toString();
        
        String bp = txtBP.getText().trim();
        String hr = txtHR.getText().trim();
        String notes = txaNote.getText().trim();
        
        if (patientInput.equals("Select Patient...") || doctorInput.equals("Select Doctor...") || recordedByInput.equals("Select Nurse...") || date.isEmpty() || rawTime.isEmpty() || recordTypeString.equals("Select Type...")) {
            JOptionPane.showMessageDialog(this, "Patient, Doctor, Nurse, Date, Time, and Type are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int typeId = 1;
        if (recordTypeString.equals("Progress Note")) typeId = 2;
        else if (recordTypeString.equals("Discharge Summary")) typeId = 3;
        else if (recordTypeString.equals("Lab Result")) typeId = 4;

        String formattedDateTimeForDB;
        try {
            if (!rawTime.contains(":")) throw new Exception();
            String[] parts = rawTime.split(":");
            int hour = Integer.parseInt(parts[0]);
            String minutes = parts[1];
            if (amPm.equals("PM") && hour != 12) hour += 12;
            else if (amPm.equals("AM") && hour == 12) hour = 0;
            formattedDateTimeForDB = String.format("%s %02d:%s:00", date, hour, minutes);
        } catch (Exception timeEx) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please use HH:MM.", "Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Apply the Split Trick to all 3!
            int patientId = Integer.parseInt(patientInput.split(" - ")[0].trim());
            int doctorId = Integer.parseInt(doctorInput.split(" - ")[0].trim());
            int recordedById = Integer.parseInt(recordedByInput.split(" - ")[0].trim());
            
            int heartRate = hr.isEmpty() ? 0 : Integer.parseInt(hr);
            double height = txtHeight.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtHeight.getText().trim());
            double weight = txtWeight.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtWeight.getText().trim());
            double temp = txtTemp.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtTemp.getText().trim());

            String sql = "INSERT INTO medical_records (patient_id, doctor_id, recorded_by_id, record_type_id, height, weight, blood_pressure, heart_rate, temperature, record_datetime, notes) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, patientId);
                stmt.setInt(2, doctorId);
                stmt.setInt(3, recordedById);
                stmt.setInt(4, typeId);
                stmt.setDouble(5, height);
                stmt.setDouble(6, weight);
                stmt.setString(7, bp);
                stmt.setInt(8, heartRate);
                stmt.setDouble(9, temp);
                stmt.setString(10, formattedDateTimeForDB);
                stmt.setString(11, notes); 
                
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Medical record saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
            JOptionPane.showMessageDialog(this, "Please ensure you have selected valid options from the lists.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnAddInfo) {
            saveRecordToDatabase();
        }
    }
}