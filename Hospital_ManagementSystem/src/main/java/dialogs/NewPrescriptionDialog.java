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
import java.sql.Statement;
import javax.swing.*;

public class NewPrescriptionDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblPatient, lblMed, lblDate, lblDura, lblDosage, lblQty, lblDiagnosis, lblTitle, lblSubtitle,
            lblFreq, lblRefill, lblNote, lblDoctor, lblTime;
    private JTextField txtDate, txtQty, txtDiagnosis, txtDura, txtRefill, txtTime;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    
    private JComboBox<String> cmbPatient, cmbDoctor, cmbDosage, cmbFreq, cmbAmPm, cmbMedication;
    private JButton btnAddInfo, btnCancel, btnPrescript;
    
    private static final String[] dosages = {"Select Dosage...", "500 mg", "250 mg", "100 mg", "5 mg", "10 mg", "10 ml", "5 ml"};
    private static final String[] frequencies = {"Select Frequency...", "Once daily (QD)", "Twice daily (BID)", "Three times daily (TID)", "Four times daily (QID)", "As needed (PRN)"};
    private static final String[] ampm = {"AM", "PM"};
    
    public NewPrescriptionDialog() {
        setSize(1050, 600); 
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Prescription");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Issue and manage patient medications and dosages.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 350); 
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnPrescript = new JButton("Prescription Details");
        btnPrescript.setBounds(40, 100, 250, 40);
        btnPrescript.setFont(FontsTheme.Buttons);
        btnPrescript.setForeground(ColorsTheme.Text_White);
        btnPrescript.setBackground(ColorsTheme.Header);
        btnPrescript.setFocusPainted(false);
        add(btnPrescript);
         
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 510, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnAddInfo = new JButton("Save Prescription");
        btnAddInfo.setBounds(690, 510, 300, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
         
        // --- LEFT SECTION ---
        lblPatient = new JLabel("Patient : ");
        lblPatient.setBounds(40, 30, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatient);
        
        cmbPatient = new JComboBox<>();
        cmbPatient.setEditable(true);
        cmbPatient.setBounds(220, 30, 230, 30);
        cmbPatient.setFont(FontsTheme.Plain_Texts);
        cmbPatient.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbPatient);

        lblMed = new JLabel("Medication Name : ");
        lblMed.setBounds(40, 70, 200, 30);
        lblMed.setFont(FontsTheme.Plain_Texts);
        lblMed.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMed);
        
        cmbMedication = new JComboBox<>();
        cmbMedication.setEditable(true);
        cmbMedication.setBounds(220, 70, 230, 30);
        cmbMedication.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbMedication);
        
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(40, 110, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("(YYYY-MM-DD)"); 
        txtDate.setBounds(220, 110, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);

        lblTime = new JLabel("Time : ");
        lblTime.setBounds(40, 150, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField("(HH:MM)"); 
        txtTime.setBounds(220, 150, 140, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        cmbAmPm = new JComboBox<>(ampm);
        cmbAmPm.setBounds(370, 150, 80, 30);
        cmbAmPm.setFont(FontsTheme.Plain_Texts);
        cmbAmPm.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbAmPm);
        
        lblDosage = new JLabel("Dosage : ");
        lblDosage.setBounds(40, 190, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
        
        cmbDosage = new JComboBox<>(dosages);
        cmbDosage.setEditable(true);
        cmbDosage.setBounds(220, 190, 230, 30);
        cmbDosage.setFont(FontsTheme.Plain_Texts);
        cmbDosage.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDosage);
        
        lblFreq = new JLabel("Frequency : ");
        lblFreq.setBounds(40, 230, 200, 30);
        lblFreq.setFont(FontsTheme.Plain_Texts);
        lblFreq.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFreq);
        
        cmbFreq = new JComboBox<>(frequencies);
        cmbFreq.setEditable(true);
        cmbFreq.setBounds(220, 230, 230, 30);
        cmbFreq.setFont(FontsTheme.Plain_Texts);
        cmbFreq.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbFreq);
       
        // --- RIGHT SECTION ---
        lblDoctor = new JLabel("Prescribing Doctor: ");
        lblDoctor.setBounds(510, 30, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);

        cmbDoctor = new JComboBox<>();
        cmbDoctor.setEditable(true);
        cmbDoctor.setBounds(690, 30, 230, 30);
        cmbDoctor.setFont(FontsTheme.Plain_Texts);
        cmbDoctor.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoctor);

        lblDura = new JLabel("Duration : ");
        lblDura.setBounds(510, 70, 200, 30);
        lblDura.setFont(FontsTheme.Plain_Texts);
        lblDura.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDura);
        
        txtDura = new JTextField("(e.g. 7 days)");
        txtDura.setBounds(690, 70, 230, 30);
        txtDura.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDura);

        lblQty = new JLabel("Quantity : ");
        lblQty.setBounds(510, 110, 200, 30);
        lblQty.setFont(FontsTheme.Plain_Texts);
        lblQty.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblQty);
        
        txtQty = new JTextField("(Pills/Tabs)");
        txtQty.setBounds(690, 110, 230, 30);
        txtQty.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtQty);
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(510, 150, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
        
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(690, 150, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDiagnosis);
        
        lblRefill = new JLabel("Refill Info : "); 
        lblRefill.setBounds(510, 190, 200, 30);
        lblRefill.setFont(FontsTheme.Plain_Texts);
        lblRefill.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRefill);
        
        txtRefill = new JTextField("0"); 
        txtRefill.setBounds(690, 190, 230, 30);
        txtRefill.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtRefill);
        
        lblNote = new JLabel("Special Notes : ");
        lblNote.setBounds(510, 230, 200, 30);
        lblNote.setFont(FontsTheme.Plain_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea("");
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Black);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(690, 230, 230, 70);
        pnlContent.add(scrollNote);

        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);

        loadDropdownData();
    }
  
    private void loadDropdownData() {
        cmbPatient.addItem("Select Patient...");
        cmbDoctor.addItem("Select Doctor...");
        cmbMedication.addItem("Select Medication...");
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            
            String patSql = "SELECT patient_id, first_name, last_name FROM patients ORDER BY patient_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(patSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getInt("patient_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name");
                    cmbPatient.addItem(fullName);
                }
            }
            
            String docSql = "SELECT user_id, firstname, lastname FROM users WHERE role = 'Doctor' ORDER BY user_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(docSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String docName = rs.getInt("user_id") + " - Dr. " + rs.getString("firstname") + " " + rs.getString("lastname");
                    cmbDoctor.addItem(docName);
                }
            }
            
            // THE FIX: Now using brand_name, generic_name, and strength. Only showing Active (status_id = 1) drugs!
            String pharmSql = "SELECT medication_id, brand_name, generic_name, strength FROM pharmacy WHERE status_id = 1 ORDER BY medication_id ASC";
            try (PreparedStatement stmt = conn.prepareStatement(pharmSql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int medId = rs.getInt("medication_id");
                    String brand = rs.getString("brand_name");
                    String generic = rs.getString("generic_name");
                    String strength = rs.getString("strength");
                    
                    String medName = (brand != null && !brand.isEmpty() ? brand + " (" + generic + ") " : generic + " ") + (strength != null ? strength : "");
                    
                    String displayString = medId + " - " + medName.trim();
                    cmbMedication.addItem(displayString);
                }
            } catch (SQLException e) {
                System.out.println("Pharmacy table error! " + e.getMessage());
            }
            
        } catch (SQLException ex) {
            System.out.println("Could not load dynamic dropdown lists: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            String patientInput = cmbPatient.getSelectedItem().toString();
            String doctorInput = cmbDoctor.getSelectedItem().toString();
            String medInput = cmbMedication.getSelectedItem().toString();
            
            String date = txtDate.getText().trim();
            String rawTime = txtTime.getText().trim();
            String amPm = cmbAmPm.getSelectedItem().toString();
            
            if (patientInput.equals("Select Patient...") || doctorInput.equals("Select Doctor...") || medInput.equals("Select Medication...") || date.isEmpty() || rawTime.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient, Doctor, Medication, Date, and Time are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
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

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
                
                // START TRANSACTION (Crucial for 2 tables)
                connection.setAutoCommit(false); 
                
                int patientId = Integer.parseInt(patientInput.split(" - ")[0].trim());
                int doctorId = Integer.parseInt(doctorInput.split(" - ")[0].trim());
                int medicationId = Integer.parseInt(medInput.split(" - ")[0].trim());
                
                String rawQty = txtQty.getText().trim();
                int qty = rawQty.isEmpty() || rawQty.equals("(Pills/Tabs)") ? 1 : Integer.parseInt(rawQty);
                
                String rawRefill = txtRefill.getText().trim();
                int refill = rawRefill.isEmpty() ? 0 : Integer.parseInt(rawRefill);

                // 1. INSERT INTO PRESCRIPTIONS
                String sql1 = "INSERT INTO prescriptions (patient_id, doctor_id, diagnosis, special_notes, prescription_date, status_id) VALUES (?, ?, ?, ?, ?, 1)";
                try (PreparedStatement insert1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                    insert1.setInt(1, patientId);
                    insert1.setInt(2, doctorId);
                    insert1.setString(3, txtDiagnosis.getText().trim());
                    insert1.setString(4, txaNote.getText().trim());
                    insert1.setString(5, formattedDateTimeForDB);
                    insert1.executeUpdate();
                    
                    // GRAB THE NEW PRESCRIPTION ID
                    ResultSet rs = insert1.getGeneratedKeys();
                    if (rs.next()) {
                        int newPrescriptionId = rs.getInt(1);
                        
                        // 2. INSERT INTO PRESCRIPTION DETAILS (LINE ITEM)
                        String sql2 = "INSERT INTO prescription_details (prescription_id, medication_id, dosage, frequency, duration, quantity, refill_info) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insert2 = connection.prepareStatement(sql2)) {
                            insert2.setInt(1, newPrescriptionId);
                            insert2.setInt(2, medicationId);
                            
                            // Prevent placeholder text from entering the DB
                            String dosageVal = cmbDosage.getSelectedItem().toString();
                            if (dosageVal.equals("Select Dosage...")) dosageVal = "";
                            
                            String freqVal = cmbFreq.getSelectedItem().toString();
                            if (freqVal.equals("Select Frequency...")) freqVal = "";
                            
                            String durationVal = txtDura.getText().trim();
                            if (durationVal.equals("(e.g. 7 days)")) durationVal = "";
                            
                            insert2.setString(3, dosageVal);
                            insert2.setString(4, freqVal);
                            insert2.setString(5, durationVal);
                            insert2.setInt(6, qty);
                            insert2.setInt(7, refill);
                            
                            insert2.executeUpdate();
                        }
                    }
                }
                
                // COMMIT TRANSACTION
                connection.commit();
                
                JOptionPane.showMessageDialog(this, "Prescription saved successfully to both tables!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) {
                JOptionPane.showMessageDialog(this, "Ensure Patient, Doctor, Medication, Quantity, and Refill Info are valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}