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

public class ViewPrescriptionDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblPatient, lblMed, lblDate, lblTime, lblDosage, lblFreq, lblStatus, lblDoctor, lblDura, lblQty, lblDiagnosis, lblRefill, lblNote;
    private JTextField txtPatient, txtMed, txtDate, txtTime, txtAmPm, txtDosage, txtFreq, txtStatus, txtDoctor, txtDura, txtQty, txtDiagnosis, txtRefill;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JButton btnMedicationInfo, btnCancel;
    private int currentPrescriptionId;
    
    public ViewPrescriptionDialog(int prescriptionId) {
        this.currentPrescriptionId = prescriptionId;
        
        setSize(1050, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        String displayId = String.format("RX-%03d", prescriptionId);
        
        lblTitle = new JLabel("View Prescription: " + displayId);
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Read-only view of the patient's medication order.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnMedicationInfo = new JButton("Medication Form");
        btnMedicationInfo.setBounds(40, 100, 300, 40);
        btnMedicationInfo.setFont(FontsTheme.Buttons);
        btnMedicationInfo.setForeground(ColorsTheme.Text_White);
        btnMedicationInfo.setBackground(ColorsTheme.Header);
        btnMedicationInfo.setFocusPainted(false);
        add(btnMedicationInfo);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 350); 
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Close");
        btnCancel.setBounds(790, 510, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Search);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
         
        // --- LEFT COLUMN ---
        lblPatient = new JLabel("Patient : ");
        lblPatient.setBounds(40, 30, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatient);
        
        txtPatient = new JTextField("");
        txtPatient.setBounds(220, 30, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setEditable(false);
        pnlContent.add(txtPatient);

        lblMed = new JLabel("Medication Name : ");
        lblMed.setBounds(40, 70, 200, 30);
        lblMed.setFont(FontsTheme.Plain_Texts);
        lblMed.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMed);
        
        txtMed = new JTextField("");
        txtMed.setBounds(220, 70, 230, 30);
        txtMed.setFont(FontsTheme.Plain_Texts);
        txtMed.setEditable(false);
        pnlContent.add(txtMed);
        
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(40, 110, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(220, 110, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setEditable(false);
        pnlContent.add(txtDate);

        lblTime = new JLabel("Time : ");
        lblTime.setBounds(40, 150, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField("");
        txtTime.setBounds(220, 150, 140, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setEditable(false);
        pnlContent.add(txtTime);
        
        txtAmPm = new JTextField("");
        txtAmPm.setBounds(370, 150, 80, 30);
        txtAmPm.setFont(FontsTheme.Plain_Texts);
        txtAmPm.setEditable(false);
        pnlContent.add(txtAmPm);
        
        lblDosage = new JLabel("Dosage : ");
        lblDosage.setBounds(40, 190, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
        
        txtDosage = new JTextField("");
        txtDosage.setBounds(220, 190, 230, 30);
        txtDosage.setFont(FontsTheme.Plain_Texts);
        txtDosage.setEditable(false);
        pnlContent.add(txtDosage);
        
        lblFreq = new JLabel("Frequency : ");
        lblFreq.setBounds(40, 230, 200, 30);
        lblFreq.setFont(FontsTheme.Plain_Texts);
        lblFreq.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFreq);
        
        txtFreq = new JTextField("");
        txtFreq.setBounds(220, 230, 230, 30);
        txtFreq.setFont(FontsTheme.Plain_Texts);
        txtFreq.setEditable(false);
        pnlContent.add(txtFreq);
        
        lblStatus = new JLabel("Status : ");
        lblStatus.setBounds(40, 270, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStatus);
        
        txtStatus = new JTextField("");
        txtStatus.setBounds(220, 270, 230, 30);
        txtStatus.setFont(FontsTheme.Plain_Texts);
        txtStatus.setEditable(false);
        pnlContent.add(txtStatus);
       
        // --- RIGHT COLUMN ---
        lblDoctor = new JLabel("Prescribing Doctor: ");
        lblDoctor.setBounds(510, 30, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);

        txtDoctor = new JTextField("");
        txtDoctor.setBounds(690, 30, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setEditable(false);
        pnlContent.add(txtDoctor);

        lblDura = new JLabel("Duration : ");
        lblDura.setBounds(510, 70, 200, 30);
        lblDura.setFont(FontsTheme.Plain_Texts);
        lblDura.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDura);
        
        txtDura = new JTextField("");
        txtDura.setBounds(690, 70, 230, 30);
        txtDura.setFont(FontsTheme.Plain_Texts);
        txtDura.setEditable(false);
        pnlContent.add(txtDura);

        lblQty = new JLabel("Quantity : ");
        lblQty.setBounds(510, 110, 200, 30);
        lblQty.setFont(FontsTheme.Plain_Texts);
        lblQty.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblQty);
        
        txtQty = new JTextField("");
        txtQty.setBounds(690, 110, 230, 30);
        txtQty.setFont(FontsTheme.Plain_Texts);
        txtQty.setEditable(false);
        pnlContent.add(txtQty);
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(510, 150, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
        
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(690, 150, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        txtDiagnosis.setEditable(false);
        pnlContent.add(txtDiagnosis);
        
        lblRefill = new JLabel("Refill Info : ");
        lblRefill.setBounds(510, 190, 200, 30);
        lblRefill.setFont(FontsTheme.Plain_Texts);
        lblRefill.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRefill);
        
        txtRefill = new JTextField("");
        txtRefill.setBounds(690, 190, 230, 30);
        txtRefill.setFont(FontsTheme.Plain_Texts);
        txtRefill.setEditable(false);
        pnlContent.add(txtRefill);
        
        lblNote = new JLabel("Special Notes : ");
        lblNote.setBounds(510, 230, 200, 30);
        lblNote.setFont(FontsTheme.Plain_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea("");
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Black);
        txaNote.setEditable(false); 
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(690, 230, 230, 70);
        pnlContent.add(scrollNote);

        btnCancel.addActionListener(this);
        loadPrescriptionData();
    }
  
    private void loadPrescriptionData() {
        String sql = "SELECT pr.*, p.first_name, p.last_name, p.patient_id, " +
                     "u.firstname AS doc_first, u.lastname AS doc_last, " +
                     "ph.brand_name, ph.generic_name, ph.strength, " +
                     "pd.dosage, pd.frequency, pd.duration, pd.quantity, pd.refill_info, " +
                     "s.status_name " +
                     "FROM prescriptions pr " +
                     "LEFT JOIN patients p ON pr.patient_id = p.patient_id " +
                     "LEFT JOIN users u ON pr.doctor_id = u.user_id " +
                     "LEFT JOIN prescription_status s ON pr.status_id = s.status_id " +
                     "LEFT JOIN prescription_details pd ON pr.prescription_id = pd.prescription_id " +
                     "LEFT JOIN pharmacy ph ON pd.medication_id = ph.medication_id " +
                     "WHERE pr.prescription_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, currentPrescriptionId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                txtPatient.setText(rs.getInt("patient_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name"));
                txtDoctor.setText("Dr. " + rs.getString("doc_first") + " " + rs.getString("doc_last"));
                
                String brand = rs.getString("brand_name");
                String generic = rs.getString("generic_name");
                String strength = rs.getString("strength");
                String medName = (brand != null && !brand.isEmpty() ? brand + " (" + generic + ") " : generic + " ") + (strength != null ? strength : "");
                txtMed.setText(medName.trim());
                
                // THE FIX: Clean YYYY-MM-DD Date and AM/PM Time Logic
                java.sql.Date dbDate = rs.getDate("prescription_date");
                if (dbDate != null) {
                    txtDate.setText(dbDate.toString());
                } else {
                    txtDate.setText("N/A");
                }
                
                java.sql.Time dbTime = rs.getTime("prescription_date");
                if (dbTime != null) {
                    java.time.LocalTime localTime = dbTime.toLocalTime();
                    java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm");
                    txtTime.setText(localTime.format(timeFormatter));
                    if (localTime.getHour() >= 12) {
                        txtAmPm.setText("PM");
                    } else {
                        txtAmPm.setText("AM");
                    }
                } else {
                    txtTime.setText("N/A");
                    txtAmPm.setText("");
                }
                
                txtDosage.setText(rs.getString("dosage") != null ? rs.getString("dosage") : "N/A");
                txtFreq.setText(rs.getString("frequency") != null ? rs.getString("frequency") : "N/A");
                txtDura.setText(rs.getString("duration") != null ? rs.getString("duration") : "N/A");
                txtQty.setText(String.valueOf(rs.getInt("quantity")));
                txtRefill.setText(String.valueOf(rs.getInt("refill_info")));
                
                txtDiagnosis.setText(rs.getString("diagnosis") != null ? rs.getString("diagnosis") : "None");
                txaNote.setText(rs.getString("special_notes") != null ? rs.getString("special_notes") : "None");
                txtStatus.setText(rs.getString("status_name") != null ? rs.getString("status_name") : "Unknown");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not load prescription details:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
    }
}