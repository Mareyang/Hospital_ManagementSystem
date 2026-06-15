package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // <--- Added ResultSet import
import java.sql.SQLException;
import javax.swing.*;

public class NewAppointmentDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblPatient, lblDate, lblTime, lblType, lblDoctor, lblStatus, lblNote;
    private JTextField txtDate, txtTime, txtStatus;
    
    private JComboBox<String> cmbPatient, cmbDoctor, cmbVisit, cmbAmPm;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnAppoint, btnCancel, btnConfirm;

    private static final String[] ampm = {"AM", "PM"};
    private static final String[] visits = {"Select Visit Type...", "Routine Checkup", "Follow-up", "New Consultation", "Emergency Visit"};

    public NewAppointmentDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Schedule Appointment");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Book a new consultation or visit.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnAppoint = new JButton("New Appointment");
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
        
        btnConfirm = new JButton("Confirm Appointment");
        btnConfirm.setBounds(690, 450, 300, 30);
        btnConfirm.setFont(FontsTheme.Buttons);
        btnConfirm.setForeground(ColorsTheme.Text_White);
        btnConfirm.setBackground(ColorsTheme.Green);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);
        
        // --- LEFT COLUMN ---
        lblPatient = new JLabel("Patient :");
        lblPatient.setBounds(40, 30, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatient);
        
        cmbPatient = new JComboBox<>(); // Initialized empty
        cmbPatient.setEditable(true); 
        cmbPatient.setBounds(220, 30, 230, 30);
        cmbPatient.setFont(FontsTheme.Plain_Texts);
        cmbPatient.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbPatient);
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(40, 80, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("(YYYY-MM-DD)");
        txtDate.setBounds(220, 80, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
        
        lblType = new JLabel("Visit Type : ");
        lblType.setBounds(40, 130, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblType);
        
        cmbVisit = new JComboBox<>(visits);
        cmbVisit.setBounds(220, 130, 230, 30);
        cmbVisit.setFont(FontsTheme.Plain_Texts);
        cmbVisit.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbVisit);
        
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
        
        txtTime = new JTextField("(HH:MM)");
        txtTime.setBounds(690, 80, 140, 30); 
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        cmbAmPm = new JComboBox<>(ampm);
        cmbAmPm.setBounds(840, 80, 80, 30); 
        cmbAmPm.setFont(FontsTheme.Plain_Texts);
        cmbAmPm.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbAmPm);
        
        lblStatus = new JLabel("Initial Status : ");
        lblStatus.setBounds(510, 130, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
        
        txtStatus = new JTextField("Scheduled");
        txtStatus.setBounds(690, 130, 230, 30);
        txtStatus.setFont(FontsTheme.Plain_Texts);
        txtStatus.setEditable(false); 
        pnlContent.add(txtStatus);
        
        // --- BOTTOM SPAN ---
        lblNote = new JLabel("Notes / Reason for Visit");
        lblNote.setBounds(40, 180, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 210, 880, 70);
        pnlContent.add(scrollNote);
        
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);

        // Fetch live database lists
        loadDropdownData();
    }
    
    private void loadDropdownData() {
        cmbPatient.addItem("Select Patient...");
        cmbDoctor.addItem("Select Doctor...");
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            
            // Fetch Patients
            String patSql = "SELECT patient_id, first_name, last_name FROM patients ORDER BY patient_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(patSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getInt("patient_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name");
                    cmbPatient.addItem(fullName);
                }
            }
            
            // Fetch Doctors
            String docSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Doctor' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(docSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String docName = rs.getInt("user_id") + " - Dr. " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbDoctor.addItem(docName);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not load dynamic dropdown lists: " + ex.getMessage());
        }
    }
    
    private void saveAppointmentToDatabase() {
        String patientInput = cmbPatient.getSelectedItem().toString();
        String doctorInput = cmbDoctor.getSelectedItem().toString();
        String date = txtDate.getText().trim();
        String rawTime = txtTime.getText().trim();
        String amPm = cmbAmPm.getSelectedItem().toString();
        String visitType = cmbVisit.getSelectedItem().toString();
        String notes = txaNote.getText().trim();
        
        if (patientInput.equals("Select Patient...") || doctorInput.equals("Select Doctor...") || date.isEmpty() || rawTime.isEmpty() || visitType.equals("Select Visit Type...")) {
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
            int patientId = Integer.parseInt(patientInput.split(" - ")[0].trim());
            int doctorId = Integer.parseInt(doctorInput.split(" - ")[0].trim());
            
            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, visit_type, notes, status_id) VALUES (?, ?, ?, ?, ?, ?, 1)";
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, patientId);
                stmt.setInt(2, doctorId);
                stmt.setString(3, date);
                stmt.setString(4, formattedTimeForDB);
                stmt.setString(5, visitType);
                stmt.setString(6, notes);
                
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Appointment scheduled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: Make sure the IDs exist!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
            JOptionPane.showMessageDialog(this, "Please select a valid Patient and Doctor.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnConfirm) {
            saveAppointmentToDatabase();
        }
    }
}