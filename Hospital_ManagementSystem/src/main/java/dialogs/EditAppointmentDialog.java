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

public class EditAppointmentDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblApptID, lblPatient, lblDate, lblType, lblDoctor, lblStatus, lblTime, lblNote;
    private JTextField txtApptID, txtPatient, txtDate, txtTime;
    private JComboBox<String> cmbDoctor, cmbVisit, cmbStatus, cmbAmPm;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnAppoint, btnCancel, btnUpdate;
    
    private static final String[] ampm = {"AM", "PM"};
    private static final String[] visits = {"Select Visit Type...", "Routine Checkup", "Follow-up", "New Consultation", "Emergency Visit"};
    private static final String[] statuses = {"Scheduled", "Completed", "Cancelled", "No Show"};
    
    private int currentApptId;

    public EditAppointmentDialog(int apptId) {
        this.currentApptId = apptId;
        
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Edit Appointment");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Update the schedule, details, or status for this visit.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnAppoint = new JButton("Appointment Details");
        btnAppoint.setBounds(40, 100, 250, 40);
        btnAppoint.setFont(FontsTheme.Buttons);
        btnAppoint.setForeground(ColorsTheme.Text_White);
        btnAppoint.setBackground(ColorsTheme.Header);
        btnAppoint.setFocusPainted(false);
        add(btnAppoint);
        
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
        
        btnUpdate = new JButton("Update Appointment");
        btnUpdate.setBounds(690, 450, 300, 30);
        btnUpdate.setFont(FontsTheme.Buttons);
        btnUpdate.setForeground(ColorsTheme.Text_White);
        btnUpdate.setBackground(ColorsTheme.Green);
        btnUpdate.setFocusPainted(false);
        add(btnUpdate);
        
        // --- LEFT COLUMN ---
        lblApptID = new JLabel("Appointment ID :");
        lblApptID.setBounds(40, 30, 200, 30);
        lblApptID.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblApptID);
        
        txtApptID = new JTextField();
        txtApptID.setBounds(220, 30, 230, 30);
        txtApptID.setFont(FontsTheme.Plain_Texts);
        txtApptID.setEditable(false);
        pnlContent.add(txtApptID);

        lblPatient = new JLabel("Patient :");
        lblPatient.setBounds(40, 80, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatient);
        
        txtPatient = new JTextField();
        txtPatient.setBounds(220, 80, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setEditable(false); // Locked 
        pnlContent.add(txtPatient);
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(40, 130, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(220, 130, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
        
        // --- RIGHT COLUMN ---
        lblDoctor = new JLabel("Doctor :");
        lblDoctor.setBounds(510, 30, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        cmbDoctor = new JComboBox<>(); // Initialized empty
        cmbDoctor.setEditable(true);
        cmbDoctor.setBounds(690, 30, 230, 30);
        cmbDoctor.setFont(FontsTheme.Plain_Texts);
        cmbDoctor.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoctor);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 80, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField();
        txtTime.setBounds(690, 80, 140, 30); 
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        cmbAmPm = new JComboBox<>(ampm);
        cmbAmPm.setBounds(840, 80, 80, 30);
        cmbAmPm.setFont(FontsTheme.Plain_Texts);
        cmbAmPm.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbAmPm);
        
        lblStatus = new JLabel("Current Status : ");
        lblStatus.setBounds(510, 130, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
        
        cmbStatus = new JComboBox<>(statuses);
        cmbStatus.setBounds(690, 130, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        cmbStatus.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbStatus);
        
        // --- BOTTOM ROW ---
        lblType = new JLabel("Visit Type : ");
        lblType.setBounds(40, 180, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblType);
        
        cmbVisit = new JComboBox<>(visits);
        cmbVisit.setBounds(220, 180, 230, 30);
        cmbVisit.setFont(FontsTheme.Plain_Texts);
        cmbVisit.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbVisit);
        
        lblNote = new JLabel("Notes / Reason for Visit");
        lblNote.setBounds(510, 180, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(510, 210, 410, 70);
        pnlContent.add(scrollNote);
        
        btnCancel.addActionListener(this);
        btnUpdate.addActionListener(this);

        loadDropdownData();
        loadAppointmentData();
    }
    
    private void loadDropdownData() {
        cmbDoctor.addItem("Select Doctor...");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            String docSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Doctor' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(docSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String docName = rs.getInt("user_id") + " - Dr. " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbDoctor.addItem(docName);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not load doctor list: " + ex.getMessage());
        }
    }

    private void loadAppointmentData() {
        String sql = "SELECT a.*, s.status_name, p.first_name, p.last_name, u.lastname AS doc_last, u.firstname AS doc_first FROM appointments a " +
                     "LEFT JOIN appointment_status s ON a.status_id = s.status_id " +
                     "LEFT JOIN patients p ON a.patient_id = p.patient_id " +
                     "LEFT JOIN users u ON a.doctor_id = u.user_id " +
                     "WHERE a.appt_id = ?";
                     
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, currentApptId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                txtApptID.setText(String.format("APT-%03d", rs.getInt("appt_id")));
                
                String patientFullName = rs.getInt("patient_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name");
                txtPatient.setText(patientFullName);
                
                String docName = rs.getInt("doctor_id") + " - Dr. " + rs.getString("doc_first") + " " + rs.getString("doc_last");
                cmbDoctor.setSelectedItem(docName);
                
                txtDate.setText(rs.getString("appointment_date"));
                
                java.sql.Time dbTime = rs.getTime("appointment_time");
                if (dbTime != null) {
                    java.time.LocalTime localTime = dbTime.toLocalTime();
                    java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm");
                    txtTime.setText(localTime.format(timeFormatter));
                    if (localTime.getHour() >= 12) {
                        cmbAmPm.setSelectedItem("PM");
                    } else {
                        cmbAmPm.setSelectedItem("AM");
                    }
                }
                
                txaNote.setText(rs.getString("notes"));
                
                String visitType = rs.getString("visit_type");
                if (visitType != null) cmbVisit.setSelectedItem(visitType);
                
                String statusName = rs.getString("status_name");
                if (statusName != null) cmbStatus.setSelectedItem(statusName);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load data.\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // THE FIX: Added Silent Trigger Billing Logic for dropdown status changes!
    private void updateAppointmentInDatabase() {
        String doctorInput = cmbDoctor.getSelectedItem().toString();
        String date = txtDate.getText().trim();
        String rawTime = txtTime.getText().trim();
        String amPm = cmbAmPm.getSelectedItem().toString();
        String visitType = cmbVisit.getSelectedItem().toString();
        String statusText = cmbStatus.getSelectedItem().toString();
        String notes = txaNote.getText().trim();
        
        if (doctorInput.equals("Select Doctor...") || date.isEmpty() || rawTime.isEmpty() || visitType.equals("Select Visit Type...")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String formattedTimeForDB = rawTime;
        try {
            if (!rawTime.contains(":")) throw new Exception("No colon");
            String[] parts = rawTime.split(":");
            int hour = Integer.parseInt(parts[0]);
            String minutes = parts[1];
            if (amPm.equals("PM") && hour != 12) hour += 12;
            else if (amPm.equals("AM") && hour == 12) hour = 0;
            formattedTimeForDB = String.format("%02d:%s:00", hour, minutes);
        } catch (Exception timeEx) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please use HH:MM.", "Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int doctorId = Integer.parseInt(doctorInput.split(" - ")[0].trim());
            
            int statusId = 1; 
            if (statusText.equals("Completed")) statusId = 2;
            if (statusText.equals("Cancelled")) statusId = 3;
            if (statusText.equals("No Show")) statusId = 4;
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
                conn.setAutoCommit(false); // Secure transaction for billing sync
                
                // --- SILENT TRIGGER: AUTO-BILLING ---
                if (statusId == 2) {
                    int patientId = Integer.parseInt(txtPatient.getText().split(" - ")[0].trim());
                    int currentDbStatus = 0;
                    
                    // Prevent Double Charging
                    String getPatSql = "SELECT status_id FROM appointments WHERE appt_id = ?";
                    try (PreparedStatement patStmt = conn.prepareStatement(getPatSql)) {
                        patStmt.setInt(1, currentApptId);
                        ResultSet patRs = patStmt.executeQuery();
                        if (patRs.next()) {
                            currentDbStatus = patRs.getInt("status_id");
                        }
                    }

                    if (currentDbStatus != 2) {
                        int activeBillingId = -1;
                        String checkBillSql = "SELECT billing_id FROM billing WHERE patient_id = ? AND status_id = 1 LIMIT 1";
                        try (PreparedStatement checkBillStmt = conn.prepareStatement(checkBillSql)) {
                            checkBillStmt.setInt(1, patientId);
                            ResultSet billRs = checkBillStmt.executeQuery();
                            if (billRs.next()) {
                                activeBillingId = billRs.getInt("billing_id");
                            } else {
                                String createBillSql = "INSERT INTO billing (patient_id, total_amount, net_amount, status_id) VALUES (?, 0, 0, 1)";
                                try (PreparedStatement createBillStmt = conn.prepareStatement(createBillSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                                    createBillStmt.setInt(1, patientId);
                                    createBillStmt.executeUpdate();
                                    ResultSet keys = createBillStmt.getGeneratedKeys();
                                    if (keys.next()) activeBillingId = keys.getInt(1);
                                }
                            }
                        }
                        
                        double consultationFee = 500.00;
                        String insertItemSql = "INSERT INTO billing_items (billing_id, description, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement itemStmt = conn.prepareStatement(insertItemSql)) {
                            itemStmt.setInt(1, activeBillingId);
                            itemStmt.setString(2, "Consultation Fee (APT-" + String.format("%03d", currentApptId) + ")");
                            itemStmt.setInt(3, 1);
                            itemStmt.setDouble(4, consultationFee);
                            itemStmt.setDouble(5, consultationFee);
                            itemStmt.executeUpdate();
                        }
                        
                        String updateTotalSql = "UPDATE billing SET total_amount = total_amount + ?, net_amount = net_amount + ? WHERE billing_id = ?";
                        try (PreparedStatement totalStmt = conn.prepareStatement(updateTotalSql)) {
                            totalStmt.setDouble(1, consultationFee);
                            totalStmt.setDouble(2, consultationFee);
                            totalStmt.setInt(3, activeBillingId);
                            totalStmt.executeUpdate();
                        }
                    }
                }
                // --- END SILENT TRIGGER ---

                String sql = "UPDATE appointments SET doctor_id=?, appointment_date=?, appointment_time=?, visit_type=?, notes=?, status_id=? WHERE appt_id=?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, doctorId);
                    stmt.setString(2, date);
                    stmt.setString(3, formattedTimeForDB); 
                    stmt.setString(4, visitType);
                    stmt.setString(5, notes);
                    stmt.setInt(6, statusId);
                    stmt.setInt(7, currentApptId);
                    
                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        conn.commit(); 
                        JOptionPane.showMessageDialog(this, "Appointment successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (SQLException ex) {
                    conn.rollback();
                    throw ex;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: Ensure the Doctor ID exists! \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
            JOptionPane.showMessageDialog(this, "Please select a valid Doctor.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnUpdate) {
            updateAppointmentInDatabase();
        }
    }
}