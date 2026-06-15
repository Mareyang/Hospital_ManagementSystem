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



public class ViewMedicalRecordDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblRecordID, lblPatient, lblDate, lblTime, lblType, lblDoctor, lblHeight, lblWeight, lblBP, lblHR, lblTemp, lblRecordedBy, lblNote;
    private JTextField txtRecordID, txtPatient, txtDate, txtTime, txtAmPm, txtType, txtDoctor, txtHeight, txtWeight, txtBP, txtHR, txtTemp, txtRecordedBy;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnCancel, btnMed;
    
    private int currentRecordId;

    
    public ViewMedicalRecordDialog(int recordId) {
        this.currentRecordId = recordId;
        
        setSize(1050, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Medical Record Details");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Viewing patient case details, vitals, and notes.");
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
        
        btnCancel = new JButton("Close");
        btnCancel.setBounds(790, 500, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Search);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
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
        txtPatient.setEditable(false); 
        pnlContent.add(txtPatient);
        
        lblType = new JLabel("Medical Type : ");
        lblType.setBounds(40, 110, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblType);
        
        txtType = new JTextField();
        txtType.setBounds(220, 110, 230, 30);
        txtType.setFont(FontsTheme.Plain_Texts);
        txtType.setEditable(false);
        pnlContent.add(txtType);
        
        lblHeight = new JLabel("Height (cm) : ");
        lblHeight.setBounds(40, 155, 200, 30);
        lblHeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHeight);
        
        txtHeight = new JTextField();
        txtHeight.setBounds(220, 155, 230, 30);
        txtHeight.setFont(FontsTheme.Plain_Texts);
        txtHeight.setEditable(false);
        pnlContent.add(txtHeight);
        
        lblWeight = new JLabel("Weight (kg) : ");
        lblWeight.setBounds(40, 200, 200, 30);
        lblWeight.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblWeight);
        
        txtWeight = new JTextField();
        txtWeight.setBounds(220, 200, 230, 30);
        txtWeight.setFont(FontsTheme.Plain_Texts);
        txtWeight.setEditable(false);
        pnlContent.add(txtWeight);
        
        lblRecordedBy = new JLabel("Recorded By: ");
        lblRecordedBy.setBounds(40, 245, 200, 30);
        lblRecordedBy.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblRecordedBy);
        
        txtRecordedBy = new JTextField(); 
        txtRecordedBy.setBounds(220, 245, 230, 30);
        txtRecordedBy.setFont(FontsTheme.Plain_Texts);
        txtRecordedBy.setEditable(false);
        pnlContent.add(txtRecordedBy);

        // --- RIGHT COLUMN ---
        lblDoctor = new JLabel("Attending Doctor:");
        lblDoctor.setBounds(510, 20, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setBounds(690, 20, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setEditable(false);
        pnlContent.add(txtDoctor);
        
        lblDate = new JLabel("Date :");
        lblDate.setBounds(510, 65, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(690, 65, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setEditable(false);
        pnlContent.add(txtDate);
        
        lblTime = new JLabel("Time : ");
        lblTime.setBounds(510, 110, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField();
        txtTime.setBounds(690, 110, 140, 30); 
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setEditable(false);
        pnlContent.add(txtTime);
        
        txtAmPm = new JTextField();
        txtAmPm.setBounds(840, 110, 80, 30);
        txtAmPm.setFont(FontsTheme.Plain_Texts);
        txtAmPm.setEditable(false);
        pnlContent.add(txtAmPm);
        
        lblBP = new JLabel("Blood Pressure :");
        lblBP.setBounds(510, 155, 200, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        
        txtBP = new JTextField();
        txtBP.setBounds(690, 155, 230, 30);
        txtBP.setFont(FontsTheme.Plain_Texts);
        txtBP.setEditable(false);
        pnlContent.add(txtBP);

        lblHR = new JLabel("Heart Rate (bpm) :");
        lblHR.setBounds(510, 200, 200, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        
        txtHR = new JTextField();
        txtHR.setBounds(690, 200, 230, 30);
        txtHR.setFont(FontsTheme.Plain_Texts);
        txtHR.setEditable(false);
        pnlContent.add(txtHR);
        
        lblTemp = new JLabel("Temperature (°C) :");
        lblTemp.setBounds(510, 245, 200, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        
        txtTemp = new JTextField();
        txtTemp.setBounds(690, 245, 230, 30);
        txtTemp.setFont(FontsTheme.Plain_Texts);
        txtTemp.setEditable(false);
        pnlContent.add(txtTemp);
        
        // --- BOTTOM SPAN ---
        lblNote = new JLabel("Diagnosis / Notes");
        lblNote.setBounds(40, 290, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(); 
        txaNote.setEditable(false);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(220, 290, 700, 50); 
        pnlContent.add(scrollNote);
        
        btnCancel.addActionListener(this);

        loadRecordData();
    }
    
    private void loadRecordData() {
        // Double JOIN to pull both Doctor and Nurse names!
        String sql = "SELECT m.*, p.first_name AS p_first, p.last_name AS p_last, " +
                     "d.firstname AS d_first, d.lastname AS d_last, " +
                     "n.firstname AS n_first, n.lastname AS n_last, rt.type_name " +
                     "FROM medical_records m " +
                     "LEFT JOIN patients p ON m.patient_id = p.patient_id " +
                     "LEFT JOIN users d ON m.doctor_id = d.user_id " +
                     "LEFT JOIN users n ON m.recorded_by_id = n.user_id " +
                     "LEFT JOIN record_types rt ON m.record_type_id = rt.type_id " +
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
                txtDoctor.setText(docName);
                
                String nurseName = rs.getInt("recorded_by_id") + " - " + rs.getString("n_first") + " " + rs.getString("n_last");
                txtRecordedBy.setText(nurseName);
                
                txtType.setText(rs.getString("type_name"));
                
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
                    if (localDT.getHour() >= 12) txtAmPm.setText("PM");
                    else txtAmPm.setText("AM");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load data.\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}