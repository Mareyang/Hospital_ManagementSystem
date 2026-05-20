/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class AddMedicalRecordDialog extends JDialog implements ActionListener {
  
    private JPanel pnlContent;
   
    private JLabel lblTitle, lblSubtitle, lblBP, lblHR, lblTemp;
    private JLabel lblPatient, lblMRN, lblType, lblDoctor, lblDate;
    private JLabel lblDiagnosis;
    private JLabel lblVitalSigns;
    private JTextField txtPatient, txtMRN, txtDoctor, txtDate, txtVitals;
    private JTextField txtDiagnosis;
    private JTextField txtBloodPressure, txtHeartRate, txtTemperature;
    private JComboBox<String> cmbType;
    private JButton btnAddInfo, btnCancel, btnMed;

    
    
    
    public AddMedicalRecordDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
      
        lblTitle = new JLabel("Patient Medical Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
      
        lblSubtitle = new JLabel("Manage patient case details and medical history.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
      
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
      
        btnMed = new JButton("Medical Records");
        btnMed.setBounds(40, 100, 250, 40);
        btnMed.setFont(FontsTheme.Buttons);
        btnMed.setForeground(ColorsTheme.Text_White);
        btnMed.setBackground(ColorsTheme.Header);
        btnMed.setFocusPainted(false);
        add(btnMed);
        
       
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
      
        btnAddInfo = new JButton("Save Record");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
       
        
        btnMed.addActionListener(this);
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
        
        createMedicalRecordForm();
        
    }
  
    private void createMedicalRecordForm() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();

        lblPatient = new JLabel("Patient Name : ");
        lblPatient.setBounds(40, 40, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatient);
      
        txtPatient = new JTextField("");
        txtPatient.setBounds(220, 40, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtPatient);
      
        lblMRN = new JLabel("Patient ID : ");
        lblMRN.setBounds(40, 80, 200, 30);
        lblMRN.setFont(FontsTheme.Plain_Texts);
        lblMRN.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMRN);
      
        txtMRN = new JTextField("");
        txtMRN.setBounds(220, 80, 230, 30);
        txtMRN.setFont(FontsTheme.Plain_Texts);
        txtMRN.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtMRN);
      
        lblType = new JLabel("Type : ");
        lblType.setBounds(40, 120, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
      
        cmbType = new JComboBox<>(new String[]{
            " ", "Consultation", "Lab Result", "Imaging", "Procedure", "Surgery", "Follow-up", "Other"
        });
        cmbType.setBounds(220, 120, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setForeground(ColorsTheme.Text_Black);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(40, 160, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
      
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(220, 160, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        txtDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDiagnosis);

        lblDoctor = new JLabel("Doctor : ");
        lblDoctor.setBounds(40, 200, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);
      
        txtDoctor = new JTextField("");
        txtDoctor.setBounds(220, 200, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDoctor);
      
        
        //LEFT
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(510, 40, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
      
        txtDate = new JTextField(""); 
        txtDate.setBounds(690, 40, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblVitalSigns = new JLabel("Vital Signs :");
        lblVitalSigns.setBounds(510, 80, 200, 30);
        lblVitalSigns.setFont(FontsTheme.Plain_Texts);
        lblVitalSigns.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblVitalSigns);
        
        txtVitals = new JTextField(""); 
        txtVitals.setBounds(690, 80, 230, 30);
        txtVitals.setFont(FontsTheme.Plain_Texts);
        txtVitals.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtVitals);

        lblBP = new JLabel("Blood Pressure :");
        lblBP.setBounds(510, 120, 200, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        
        txtBloodPressure = new JTextField("");
        txtBloodPressure.setBounds(690, 120, 230, 30);
        txtBloodPressure.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtBloodPressure);

        lblHR = new JLabel("Heart Rate :");
        lblHR.setBounds(510, 160, 200, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        
        txtHeartRate = new JTextField("");
        txtHeartRate.setBounds(690, 160, 230, 30);
        txtHeartRate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHeartRate);

        
        lblTemp = new JLabel("Temperature :");
        lblTemp.setBounds(510, 200, 200, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        
        txtTemperature = new JTextField("");
        txtTemperature.setBounds(690, 200, 230, 30);
        txtTemperature.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTemperature);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnMed) {
            createMedicalRecordForm();
        }
        
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnAddInfo) {
            JOptionPane.showMessageDialog(this, "Medical record added successfully!", 
                    "Medical Record Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}