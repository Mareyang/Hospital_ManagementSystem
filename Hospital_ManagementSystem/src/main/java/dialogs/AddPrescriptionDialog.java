package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class AddPrescriptionDialog extends JDialog implements ActionListener {
    
    private JPanel pnlContent;
    private JLabel lblPatientID, lblPatientName, lblMed, lblDate, lblDura, lblDosage, lblQty, lblDiagnosis, lblTitle, lblSubtitle,
            lblFreq, lblRefill, lblNote, lblDoctor, lblTime;
    private JTextField txtPatientID, txtPatientName, txtMed, txtDate, txtQty, txtDiagnosis, txtDura, txtRefill, txtTime;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JComboBox<String> cmbDosage, cmbFreq, cmbDoctor;
    private JButton btnAddInfo, btnCancel, btnPrescript;
    
    private static final String[] dosages = {" ", "500 mg", "250 mg", "100 mg", "5 mg", "10 mg", "10 ml", "5 ml"};
    private static final String[] frequencies = {" ", "Once daily (QD)", "Twice daily (BID)", "Three times daily (TID)", "Four times daily (QID)", "As needed (PRN)"};
    private static final String[] doctors = {" ", "Dr. Juan dela Cruz", "Dr. Maria Santos", "Dr. Ricardo Reyes", "Dr. Elena Garcia", "Dr. Roberto Castro"};
    
    
    
    public AddPrescriptionDialog() {
        setSize(1050, 600); // Expanded slightly for the new fields
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
        pnlContent.setBounds(40, 140, 950, 350); // Tall enough for all elements
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
         
        // LEFT SECTION
        lblPatientID = new JLabel("Patient ID : ");
        lblPatientID.setBounds(40, 30, 200, 30);
        lblPatientID.setFont(FontsTheme.Plain_Texts);
        lblPatientID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatientID);
        
        txtPatientID = new JTextField("");
        txtPatientID.setBounds(220, 30, 230, 30);
        txtPatientID.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtPatientID);

        lblPatientName = new JLabel("Patient Name : ");
        lblPatientName.setBounds(40, 70, 200, 30);
        lblPatientName.setFont(FontsTheme.Plain_Texts);
        lblPatientName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatientName);
        
        txtPatientName = new JTextField("");
        txtPatientName.setBounds(220, 70, 230, 30);
        txtPatientName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtPatientName);
        
        lblMed = new JLabel("Medication Name : ");
        lblMed.setBounds(40, 110, 200, 30);
        lblMed.setFont(FontsTheme.Plain_Texts);
        lblMed.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMed);
        
        txtMed = new JTextField("");
        txtMed.setBounds(220, 110, 230, 30);
        txtMed.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtMed);
        
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(40, 150, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField(""); 
        txtDate.setBounds(220, 150, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);

        lblTime = new JLabel("Time : ");
        lblTime.setBounds(40, 190, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField(""); 
        txtTime.setBounds(220, 190, 230, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTime);
        
        lblDosage = new JLabel("Dosage : ");
        lblDosage.setBounds(40, 230, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
        
        cmbDosage = new JComboBox<>(dosages);
        cmbDosage.setBounds(220, 230, 230, 30);
        cmbDosage.setFont(FontsTheme.Plain_Texts);
        cmbDosage.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDosage);
        
        lblFreq = new JLabel("Frequency : ");
        lblFreq.setBounds(40, 270, 200, 30);
        lblFreq.setFont(FontsTheme.Plain_Texts);
        lblFreq.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFreq);
        
        cmbFreq = new JComboBox<>(frequencies);
        cmbFreq.setBounds(220, 270, 230, 30);
        cmbFreq.setFont(FontsTheme.Plain_Texts);
        cmbFreq.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbFreq);
      
        // RIGHT SECTION
        lblDura = new JLabel("Duration : ");
        lblDura.setBounds(510, 30, 200, 30);
        lblDura.setFont(FontsTheme.Plain_Texts);
        lblDura.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDura);
        
        txtDura = new JTextField("");
        txtDura.setBounds(690, 30, 230, 30);
        txtDura.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDura);

        lblQty = new JLabel("Quantity : ");
        lblQty.setBounds(510, 70, 200, 30);
        lblQty.setFont(FontsTheme.Plain_Texts);
        lblQty.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblQty);
        
        txtQty = new JTextField("");
        txtQty.setBounds(690, 70, 230, 30);
        txtQty.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtQty);
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(510, 110, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
        
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(690, 110, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDiagnosis);
        
        lblRefill = new JLabel("Refill Info : ");
        lblRefill.setBounds(510, 150, 200, 30);
        lblRefill.setFont(FontsTheme.Plain_Texts);
        lblRefill.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRefill);
        
        txtRefill = new JTextField("");
        txtRefill.setBounds(690, 150, 230, 30);
        txtRefill.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtRefill);
        
        lblNote = new JLabel("Special Notes : ");
        lblNote.setBounds(510, 190, 200, 30);
        lblNote.setFont(FontsTheme.Plain_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea("");
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Black);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(690, 190, 230, 70);
        pnlContent.add(scrollNote);

        lblDoctor = new JLabel("Prescribing Doctor : ");
        lblDoctor.setBounds(510, 270, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);

        cmbDoctor = new JComboBox<>(doctors);
        cmbDoctor.setBounds(690, 270, 230, 30);
        cmbDoctor.setFont(FontsTheme.Plain_Texts);
        cmbDoctor.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDoctor);
        
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } 
        else if (e.getSource() == btnAddInfo) {
            // Validation step
            if (txtPatientID.getText().trim().isEmpty() || txtPatientName.getText().trim().isEmpty() || txtMed.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient ID, Patient Name, and Medication Name are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO prescriptions (patient_id, patient_name, medication_name, prescription_date, dosage, frequency, duration, quantity, diagnosis, refill_info, special_notes, doctor, status) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pending')";

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = connection.prepareStatement(sql)) {
                
                String cleanPatientId = txtPatientID.getText().trim().toUpperCase().replace("PAT-", "");
                insert.setInt(1, Integer.parseInt(cleanPatientId));
                
                insert.setString(2, txtPatientName.getText().trim());
                insert.setString(3, txtMed.getText().trim());
                insert.setString(4, txtDate.getText().trim());
                insert.setString(5, cmbDosage.getSelectedItem().toString());
                insert.setString(6, cmbFreq.getSelectedItem().toString());
                insert.setString(7, txtDura.getText().trim());
                
                String rawQty = txtQty.getText().trim();
                insert.setInt(8, rawQty.isEmpty() ? 1 : Integer.parseInt(rawQty));
                
                insert.setString(9, txtDiagnosis.getText().trim());
                insert.setString(10, txtRefill.getText().trim());
                insert.setString(11, txaNote.getText().trim());
                insert.setString(12, cmbDoctor.getSelectedItem().toString());

                int rows = insert.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Prescription saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Patient ID and Quantity must be valid numeric representations.", "Input Error", JOptionPane.ERROR_MESSAGE);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}