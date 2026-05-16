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
 * @author Arabella
 */
public class AddMedicalRecordDialog extends JDialog implements ActionListener {
  
    private JPanel pnlContent;
   
    private JLabel lblTitle, lblSubtitle;
    private JLabel lblPatient, lblMRN, lblType, lblDoctor, lblDate;
    private JLabel lblDiagnosis;
    private JLabel lblVitalSigns;
   
    private JTextField txtPatient, txtMRN, txtDoctor, txtDate;
    private JTextField txtDiagnosis;
    private JTextField txtBloodPressure, txtHeartRate, txtTemperature;
    private JComboBox<String> cmbType;
   
    private JButton btnAddInfo, btnCancel;

    public AddMedicalRecordDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
      
        lblTitle = new JLabel("Medical Record");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
      
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Black);
        add(lblSubtitle);
      
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
      
        createMedicalRecordForm();
       
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        add(btnCancel);
      
        btnAddInfo = new JButton("Save Record");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        add(btnAddInfo);
       
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
    }
  
    private void createMedicalRecordForm() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();

        lblPatient = new JLabel("Patient : ");
        lblPatient.setBounds(40, 40, 200, 30);
        lblPatient.setFont(FontsTheme.Plain_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPatient);
      
        txtPatient = new JTextField("");
        txtPatient.setBounds(220, 40, 230, 30);
        txtPatient.setFont(FontsTheme.Plain_Texts);
        txtPatient.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtPatient);
      
        lblMRN = new JLabel("MRN : ");
        lblMRN.setBounds(40, 90, 200, 30);
        lblMRN.setFont(FontsTheme.Plain_Texts);
        lblMRN.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMRN);
      
        txtMRN = new JTextField("");
        txtMRN.setBounds(220, 90, 230, 30);
        txtMRN.setFont(FontsTheme.Plain_Texts);
        txtMRN.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtMRN);
      
        lblType = new JLabel("Type : ");
        lblType.setBounds(40, 140, 200, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
      
        cmbType = new JComboBox<>(new String[]{
            " ", "Consultation", "Lab Result", "Imaging", "Procedure", "Surgery", "Follow-up", "Other"
        });
        cmbType.setBounds(220, 140, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setForeground(ColorsTheme.Text_Black);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(40, 190, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
      
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(220, 190, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        txtDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDiagnosis);

        lblDoctor = new JLabel("Doctor : ");
        lblDoctor.setBounds(510, 40, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);
      
        txtDoctor = new JTextField("");
        txtDoctor.setBounds(690, 40, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDoctor);
      
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(510, 90, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
      
        txtDate = new JTextField(""); 
        txtDate.setBounds(690, 90, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblVitalSigns = new JLabel("Vital Signs :");
        lblVitalSigns.setBounds(510, 140, 150, 30);
        lblVitalSigns.setFont(FontsTheme.Plain_Texts);
        lblVitalSigns.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblVitalSigns);

        JLabel lblBP = new JLabel("BP:");
        lblBP.setBounds(665, 140, 40, 30);
        lblBP.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblBP);
        txtBloodPressure = new JTextField("");
        txtBloodPressure.setBounds(705, 140, 85, 30);
        txtBloodPressure.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtBloodPressure);

        JLabel lblHR = new JLabel("HR:");
        lblHR.setBounds(800, 140, 40, 30);
        lblHR.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblHR);
        txtHeartRate = new JTextField("");
        txtHeartRate.setBounds(840, 140, 65, 30);
        txtHeartRate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtHeartRate);

        
        JLabel lblTemp = new JLabel("Temp:");
        lblTemp.setBounds(665, 175, 65, 30);
        lblTemp.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblTemp);
        txtTemperature = new JTextField("");
        txtTemperature.setBounds(730, 175, 90, 30);
        txtTemperature.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtTemperature);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnAddInfo) {
            dispose();
        }
    }
}