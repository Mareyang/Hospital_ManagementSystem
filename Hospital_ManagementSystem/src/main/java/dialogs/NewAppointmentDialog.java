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

public class NewAppointmentDialog extends JDialog implements ActionListener {
    
    private JLabel lblTitle, lblSubtitle, lblDate, lblTime, lblID, lblName, lblDepart, lblDoc, lblVisit, lblRoom, lblNote;
    private JTextField txtDate, txtTime, txtID, txtName;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JComboBox<String> cmbDepart, cmbDoc, cmbVisit, cmbRoom;
    private JButton btnAppoint, btnCancel, btnConfirm;
    private JPanel pnlContent;
    private int appointmentId = -1;
    private boolean editMode = false;
    
    private static final String[] departs = {" ", "Emergency(ER)", "Laboratory", "Cardiology", "Pediatrics ", "Surgery", "OB-GYN", "Radiology"};
    private static final String[] doctors = {" ", "Dr. Juan dela Cruz", "Dr. Maria Santos", "Dr. Ricardo Reyes", "Dr. Elena Garcia", "Dr. Roberto Castro"};
    private static final String[] visits = {" ", "New Consultation", "Follow-up Visit", "Routine Check-up", "Emergency Visit", "Diagnostic/Lab Test"};
    private static final String[] rooms = {" ", "ER-01", "ER-02", "LAB-01", "LAB-02", "RM-201", "RM-202", "XRAY-01", "ICU-01", "ICU-02", "OR-01"};
    
    
    public NewAppointmentDialog() {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Appointment");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Schedule and manage healthcare consultations and visits.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        //Button for left upper side 
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
        
        // Buttons for right lower side 
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
        
        
        
        // Appointment Records Form
        lblDate = new JLabel("Appointment Date : ");
        lblDate.setBounds(40, 30, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(220, 30, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblTime = new JLabel("Appointment Time : ");
        lblTime.setBounds(40, 70, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField("");
        txtTime.setBounds(220, 70, 230, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtTime);
        
        lblID = new JLabel("Patient ID : ");
        lblID.setBounds(40, 110, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
        
        txtID = new JTextField("");
        txtID.setBounds(220, 110, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        lblName = new JLabel("Patient Name : ");
        lblName.setBounds(40, 150, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 150, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        // RIGHT SIDE
        lblDepart = new JLabel("Department : ");
        lblDepart.setBounds(510, 30, 200, 30);
        lblDepart.setFont(FontsTheme.Plain_Texts);
        lblDepart.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDepart);
        
        // Options for department needed to sched
        cmbDepart = new JComboBox<>(departs);
        cmbDepart.setBounds(690, 30, 230, 30);
        cmbDepart.setFont(FontsTheme.Plain_Texts);
        cmbDepart.setForeground(ColorsTheme.Text_Black);
        cmbDepart.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDepart);
        
        lblDoc = new JLabel("Doctor : ");
        lblDoc.setBounds(510, 70, 200, 30);
        lblDoc.setFont(FontsTheme.Plain_Texts);
        lblDoc.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoc);
        
        // Options for Doctor's name 
        cmbDoc = new JComboBox<>(doctors);
        cmbDoc.setBounds(690, 70, 230, 30);
        cmbDoc.setFont(FontsTheme.Plain_Texts);
        cmbDoc.setForeground(ColorsTheme.Text_Black);
        cmbDoc.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoc);
        
        lblVisit = new JLabel("Visit Type : ");
        lblVisit.setBounds(510, 110, 200, 30);
        lblVisit.setFont(FontsTheme.Plain_Texts);
        lblVisit.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblVisit);
        
        //Options for Visit type and concern
        cmbVisit = new JComboBox<>(visits);
        cmbVisit.setBounds(690, 110, 230, 30);
        cmbVisit.setFont(FontsTheme.Plain_Texts);
        cmbVisit.setForeground(ColorsTheme.Text_Black);
        cmbVisit.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbVisit);
        
        lblRoom = new JLabel("Room Number : ");
        lblRoom.setBounds(510, 150, 200, 30);
        lblRoom.setFont(FontsTheme.Plain_Texts);
        lblRoom.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRoom);
        
        //Options for room number available
        cmbRoom = new JComboBox<>(rooms);
        cmbRoom.setBounds(690, 150, 230, 30);
        cmbRoom.setFont(FontsTheme.Plain_Texts);
        cmbRoom.setForeground(ColorsTheme.Text_Black);
        cmbRoom.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbRoom);
        
        // additional notes for reasons visit
        lblNote = new JLabel("Note/Reason for Visit");
        lblNote.setBounds(50, 200, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(""); // Removed the "Write here..." so it's clean for DB insertion
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 230, 880, 50);
        pnlContent.setLayout(null);
        pnlContent.add(scrollNote);
        
        
        //ActionListener
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        
    }

    public NewAppointmentDialog(int appointmentId) {
        this();
        this.appointmentId = appointmentId;
        this.editMode = true;

        lblTitle.setText("Edit Appointment");
        lblSubtitle.setText("Update healthcare consultation and visit details.");
        btnAppoint.setText("Edit Appointment");
        btnConfirm.setText("Update Appointment");

        loadAppointment();
    }

    private void loadAppointment() {
        String sql = "SELECT * FROM appointments WHERE appt_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement select = connection.prepareStatement(sql)) {

            select.setInt(1, appointmentId);

            try (ResultSet result = select.executeQuery()) {
                if (result.next()) {
                    txtDate.setText(result.getString("appointment_date"));
                    txtTime.setText(result.getString("appointment_time"));
                    txtID.setText(String.format("PAT-%03d", result.getInt("patient_id")));
                    txtName.setText(result.getString("patient_name"));
                    cmbDepart.setSelectedItem(result.getString("department"));
                    cmbDoc.setSelectedItem(result.getString("doctor"));
                    cmbVisit.setSelectedItem(result.getString("visit_type"));
                    cmbRoom.setSelectedItem(result.getString("room_number"));
                    txaNote.setText(result.getString("notes"));
                } else {
                    JOptionPane.showMessageDialog(this, "Appointment record not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load appointment:\n" + sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnConfirm) {
            // Validation: Ensure mandatory fields are filled
            if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || 
                txtDate.getText().trim().isEmpty() || txtTime.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Fill all the required information.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql;
            if (editMode) {
                sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ?, patient_id = ?, "
                        + "patient_name = ?, department = ?, doctor = ?, visit_type = ?, room_number = ?, notes = ? "
                        + "WHERE appt_id = ?";
            } else {
                sql = "INSERT INTO appointments (appointment_date, appointment_time, patient_id, patient_name, "
                        + "department, doctor, visit_type, room_number, notes, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pending')";
            }

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                
                statement.setString(1, txtDate.getText().trim());
                statement.setString(2, txtTime.getText().trim());
                
                String rawPatientInput = txtID.getText().trim().toUpperCase().replace("PAT-", "");
                statement.setInt(3, Integer.parseInt(rawPatientInput));
                
                statement.setString(4, txtName.getText().trim());
                statement.setString(5, cmbDepart.getSelectedItem().toString());
                statement.setString(6, cmbDoc.getSelectedItem().toString());
                statement.setString(7, cmbVisit.getSelectedItem().toString());
                statement.setString(8, cmbRoom.getSelectedItem().toString());
                statement.setString(9, txaNote.getText().trim());
                if (editMode) {
                    statement.setInt(10, appointmentId);
                }

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    String message = editMode ? "Appointment updated successfully!" : "Appointment scheduled successfully!";
                    JOptionPane.showMessageDialog(this, message, 
                                                  "Appointment Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); 
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Patient ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + sqlException.getMessage(), 
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
