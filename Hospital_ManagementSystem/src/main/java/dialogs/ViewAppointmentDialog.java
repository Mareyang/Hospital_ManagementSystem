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



public class ViewAppointmentDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblApptID, lblPatient, lblDate, lblTime, lblDoctor, lblVisit, lblStatus, lblNote;
    private JTextField txtApptID, txtPatient, txtDate, txtTime, txtAmPm, txtDoctor, txtStatus, txtVisit;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnAppoint, btnCancel;
    
    private int currentApptId;

    
    public ViewAppointmentDialog(int apptId) {
        this.currentApptId = apptId;
        
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Appointment Details");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Viewing scheduled visit and consultation notes.");
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
        
        btnCancel = new JButton("Close");
        btnCancel.setBounds(790, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Search);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
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
        txtPatient.setEditable(false); 
        pnlContent.add(txtPatient);
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(40, 130, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(220, 130, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setEditable(false);
        pnlContent.add(txtDate);
        
        // --- RIGHT COLUMN ---
        lblDoctor = new JLabel("Doctor :");
        lblDoctor.setBounds(510, 30, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setBounds(690, 30, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setEditable(false);
        pnlContent.add(txtDoctor);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 80, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField();
        txtTime.setBounds(690, 80, 140, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setEditable(false);
        pnlContent.add(txtTime);
        
        txtAmPm = new JTextField();
        txtAmPm.setBounds(840, 80, 80, 30);
        txtAmPm.setFont(FontsTheme.Plain_Texts);
        txtAmPm.setEditable(false);
        pnlContent.add(txtAmPm);
        
        lblStatus = new JLabel("Current Status : ");
        lblStatus.setBounds(510, 130, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
        
        txtStatus = new JTextField("");
        txtStatus.setBounds(690, 130, 230, 30);
        txtStatus.setFont(FontsTheme.Plain_Texts);
        txtStatus.setEditable(false);
        pnlContent.add(txtStatus);
        
        // --- BOTTOM ROW ---
        lblVisit = new JLabel("Visit Type : ");
        lblVisit.setBounds(40, 180, 200, 30);
        lblVisit.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblVisit);
        
        txtVisit = new JTextField();
        txtVisit.setBounds(220, 180, 230, 30);
        txtVisit.setFont(FontsTheme.Plain_Texts);
        txtVisit.setEditable(false);
        pnlContent.add(txtVisit);
        
        lblNote = new JLabel("Notes / Reason for Visit");
        lblNote.setBounds(510, 180, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(false);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(510, 210, 410, 70);
        pnlContent.add(scrollNote);
        
        btnCancel.addActionListener(this);

        loadAppointmentData();
    }
    
    private void loadAppointmentData() {
        String sql = "SELECT a.*, s.status_name, p.first_name, p.last_name, u.lastname AS doc_last FROM appointments a " +
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
                
                String docName = rs.getInt("doctor_id") + " - Dr. " + rs.getString("doc_last");
                txtDoctor.setText(docName);
                
                txtDate.setText(rs.getString("appointment_date"));
                
                java.sql.Time dbTime = rs.getTime("appointment_time");
                if (dbTime != null) {
                    java.time.LocalTime localTime = dbTime.toLocalTime();
                    java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm");
                    txtTime.setText(localTime.format(timeFormatter));
                    if (localTime.getHour() >= 12) {
                        txtAmPm.setText("PM");
                    } else {
                        txtAmPm.setText("AM");
                    }
                }
                
                txaNote.setText(rs.getString("notes"));
                txtVisit.setText(rs.getString("visit_type"));
                
                String statusName = rs.getString("status_name");
                txtStatus.setText(statusName != null ? statusName : "Unknown");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}