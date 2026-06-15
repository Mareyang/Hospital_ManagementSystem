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
import java.sql.Timestamp;
import javax.swing.*;



public class EditMedicalRecordDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblRecordID, lblPatient, lblDate, lblTime, lblType, lblDoctor, lblHeight, lblWeight, lblBP, lblHR, lblTemp, lblRecordedBy, lblNote;
    private JTextField txtRecordID, txtPatient, txtDate, txtTime, txtHeight, txtWeight, txtBP, txtHR, txtTemp;
    private JComboBox<String> cmbDoctor, cmbRecordedBy, cmbType, cmbAmPm;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnCancel, btnUpdate, btnMed;
    
    private static final String[] type = {"Select Type...", "Intake Vitals", "Progress Note", "Discharge Summary", "Lab Result"};
    private static final String[] ampm = {"AM", "PM"};
    
    private int currentRecordId;

    public EditMedicalRecordDialog(int recordId) {
        this.currentRecordId = recordId;
        
        setSize(1050, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Edit Medical Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Update patient case details, vitals, or correct medical history.");
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
        
        btnUpdate = new JButton("Update Record");
        btnUpdate.setBounds(690, 500, 300, 30);
        btnUpdate.setFont(FontsTheme.Buttons);
        btnUpdate.setForeground(ColorsTheme.Text_White);
        btnUpdate.setBackground(ColorsTheme.Green);
        btnUpdate.setFocusPainted(false);
        add(btnUpdate);
        
        // --- Form Fields Setup (Grid Layout) ---
        
        lblRecordID = new JLabel("Record ID :");
        lblRecordID.setBounds(40, 20, 200, 30);
        lblRecordID.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblRecordID);
        
        txtRecordID = new JTextField();
        txtRecordID.setBounds(220, 20, 230, 30);
        txtRecordID.setFont(FontsTheme.Plain_Texts);
        txtRecordID.setEditable(false);
        pnlContent.add(txtRecordID);
        
        lblPatient = new JLabel("Patient :");
        lblPatient.setBounds(40, 65, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatient);
        
        txtPatient = new JTextField();
        txtPatient.setBounds(220, 65, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setEditable(false); // Locked
        pnlContent.add(txtPatient);
        
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
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(510, 65, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(690, 65, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 110, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField();
        txtTime.setBounds(690, 110, 140, 30); 
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        cmbAmPm = new JComboBox<>(ampm);
        cmbAmPm.setBounds(840, 110, 80, 30);
        cmbAmPm.setFont(FontsTheme.Plain_Texts);
        cmbAmPm.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbAmPm);
        
        lblBP = new JLabel("Blood Pressure :");
        lblBP.setBounds(510, 155, 200, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        
        txtBP = new JTextField();
        txtBP.setBounds(690, 155, 230, 30);
        txtBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtBP);

        lblHR = new JLabel("Heart Rate (bpm) :");
        lblHR.setBounds(510, 200, 200, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        
        txtHR = new JTextField();
        txtHR.setBounds(690, 200, 230, 30);
        txtHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHR);
        
        lblTemp = new JLabel("Temperature (°C) :");
        lblTemp.setBounds(510, 245, 200, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        
        txtTemp = new JTextField();
        txtTemp.setBounds(690, 245, 230, 30);
        txtTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTemp);
        
        // --- BOTTOM SPAN ---
        lblNote = new JLabel("Diagnosis / Notes");
        lblNote.setBounds(40, 290, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(220, 290, 700, 50); 
        pnlContent.add(scrollNote);
        
        btnCancel.addActionListener(this);
        btnUpdate.addActionListener(this);

        loadDropdownData();
        loadRecordData();
    }
    
    private void loadDropdownData() {
        cmbDoctor.addItem("Select Doctor...");
        cmbRecordedBy.addItem("Select Nurse...");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String docSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Doctor' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(docSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String docName = rs.getInt("user_id") + " - Dr. " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbDoctor.addItem(docName);
                }
            }
            String nurseSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Nurse' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(nurseSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nurseName = rs.getInt("user_id") + " - " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbRecordedBy.addItem(nurseName);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not load dropdown lists: " + ex.getMessage());
        }
    }

    private void loadRecordData() {
        // Double JOIN on users! One for the Doctor, one for the Nurse.
        String sql = "SELECT m.*, p.first_name AS p_first, p.last_name AS p_last, " +
                     "d.firstname AS d_first, d.lastname AS d_last, " +
                     "n.firstname AS n_first, n.lastname AS n_last " +
                     "FROM medical_records m " +
                     "LEFT JOIN patients p ON m.patient_id = p.patient_id " +
                     "LEFT JOIN users d ON m.doctor_id = d.user_id " +
                     "LEFT JOIN users n ON m.recorded_by_id = n.user_id " +
                     "WHERE m.record_id = ?";
                     
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, currentRecordId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                txtRecordID.setText(String.format("MED-%03d", rs.getInt("record_id")));
                
                String patientFullName = rs.getInt("patient_id") + " - " + rs.getString("p_first") + " " + rs.getString("p_last");
                txtPatient.setText(patientFullName);
                
                String docName = rs.getInt("doctor_id") + " - Dr. " + rs.getString("d_first") + " " + rs.getString("d_last");
                cmbDoctor.setSelectedItem(docName);
                
                String nurseName = rs.getInt("recorded_by_id") + " - " + rs.getString("n_first") + " " + rs.getString("n_last");
                cmbRecordedBy.setSelectedItem(nurseName);
                
                int typeId = rs.getInt("record_type_id");
                if (typeId == 1) cmbType.setSelectedItem("Intake Vitals");
                else if (typeId == 2) cmbType.setSelectedItem("Progress Note");
                else if (typeId == 3) cmbType.setSelectedItem("Discharge Summary");
                else if (typeId == 4) cmbType.setSelectedItem("Lab Result");
                
                txtHeight.setText(String.valueOf(rs.getDouble("height")));
                txtWeight.setText(String.valueOf(rs.getDouble("weight")));
                txtBP.setText(rs.getString("blood_pressure"));
                txtHR.setText(String.valueOf(rs.getInt("heart_rate")));
                txtTemp.setText(String.valueOf(rs.getDouble("temperature")));
                txaNote.setText(rs.getString("notes"));
                
                Timestamp dbDateTime = rs.getTimestamp("record_datetime");
                if (dbDateTime != null) {
                    java.time.LocalDateTime localDT = dbDateTime.toLocalDateTime();
                    txtDate.setText(localDT.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    txtTime.setText(localDT.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm")));
                    if (localDT.getHour() >= 12) cmbAmPm.setSelectedItem("PM");
                    else cmbAmPm.setSelectedItem("AM");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load data.\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateRecordInDatabase() {
        String doctorInput = cmbDoctor.getSelectedItem().toString();
        String recordedByInput = cmbRecordedBy.getSelectedItem().toString();
        String recordTypeString = cmbType.getSelectedItem().toString();
        
        String date = txtDate.getText().trim();
        String rawTime = txtTime.getText().trim();
        String amPm = cmbAmPm.getSelectedItem().toString();
        
        String bp = txtBP.getText().trim();
        String hr = txtHR.getText().trim();
        String notes = txaNote.getText().trim();
        
        if (doctorInput.equals("Select Doctor...") || recordedByInput.equals("Select Nurse...") || date.isEmpty() || rawTime.isEmpty() || recordTypeString.equals("Select Type...")) {
            JOptionPane.showMessageDialog(this, "Doctor, Nurse, Date, Time, and Type are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
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
            int doctorId = Integer.parseInt(doctorInput.split(" - ")[0].trim());
            int recordedById = Integer.parseInt(recordedByInput.split(" - ")[0].trim());
            
            int heartRate = hr.isEmpty() ? 0 : Integer.parseInt(hr);
            double height = txtHeight.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtHeight.getText().trim());
            double weight = txtWeight.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtWeight.getText().trim());
            double temp = txtTemp.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtTemp.getText().trim());

            String sql = "UPDATE medical_records SET doctor_id=?, recorded_by_id=?, record_type_id=?, height=?, weight=?, blood_pressure=?, heart_rate=?, temperature=?, record_datetime=?, notes=? WHERE record_id=?";
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, doctorId);
                stmt.setInt(2, recordedById);
                stmt.setInt(3, typeId);
                stmt.setDouble(4, height);
                stmt.setDouble(5, weight);
                stmt.setString(6, bp);
                stmt.setInt(7, heartRate);
                stmt.setDouble(8, temp);
                stmt.setString(9, formattedDateTimeForDB);
                stmt.setString(10, notes); 
                stmt.setInt(11, currentRecordId);
                
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Medical record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
            JOptionPane.showMessageDialog(this, "Ensure valid Doctor and Nurse selection.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnUpdate) {
            updateRecordInDatabase();
        }
    }
}