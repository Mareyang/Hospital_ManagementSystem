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
public class AddPrescriptionDialog extends JDialog implements ActionListener {
  
    private JPanel pnlContent;
   
    private JLabel lblTitle, lblSubtitle;
    private JLabel lblPatient, lblDoctor, lblDate, lblMedications, lblStatus;
    private JLabel lblQuantity, lblDuration;
   
    private JTextField txtPatient, txtDoctor, txtDate;
    private JTextField txtQuantity, txtDuration;
    private JTextArea txaMedications;
    private JScrollPane scrollMedications;
    private JComboBox<String> cmbStatus;
   
    private JButton btnAddInfo, btnCancel;

    public AddPrescriptionDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
      
        lblTitle = new JLabel("Prescription");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
      
        lblSubtitle = new JLabel("Complete all the required fields to add a prescription.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Black);
        add(lblSubtitle);
      
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
      
        createPrescriptionForm();
       
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        add(btnCancel);
      
        btnAddInfo = new JButton("Save Prescription");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        add(btnAddInfo);
       
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
    }
  
    private void createPrescriptionForm() {
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
      
        lblDoctor = new JLabel("Doctor : ");
        lblDoctor.setBounds(40, 90, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        lblDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoctor);
      
        txtDoctor = new JTextField("");
        txtDoctor.setBounds(220, 90, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        txtDoctor.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDoctor);
      
        lblDate = new JLabel("Date : ");
        lblDate.setBounds(40, 140, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
      
        txtDate = new JTextField(""); 
        txtDate.setBounds(220, 140, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);

        lblStatus = new JLabel("Status : ");
        lblStatus.setBounds(40, 190, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStatus);
      
        cmbStatus = new JComboBox<>(new String[]{
            " ", "Pending", "Dispensed", "Cancelled", "On Hold"
        });
        cmbStatus.setBounds(220, 190, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        cmbStatus.setForeground(ColorsTheme.Text_Black);
        cmbStatus.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbStatus);
      
        lblMedications = new JLabel("Medications : ");
        lblMedications.setBounds(510, 40, 200, 30);
        lblMedications.setFont(FontsTheme.Plain_Texts);
        lblMedications.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMedications);
      
        txaMedications = new JTextArea();
        txaMedications.setFont(FontsTheme.Plain_Texts);
        txaMedications.setLineWrap(true);
        txaMedications.setWrapStyleWord(true);
       
        scrollMedications = new JScrollPane(txaMedications);
        scrollMedications.setBounds(690, 40, 230, 100);
        pnlContent.add(scrollMedications);

        lblQuantity = new JLabel("Quantity : ");
        lblQuantity.setBounds(510, 155, 200, 30);
        lblQuantity.setFont(FontsTheme.Plain_Texts);
        lblQuantity.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblQuantity);
      
        txtQuantity = new JTextField("");
        txtQuantity.setBounds(690, 155, 230, 30);
        txtQuantity.setFont(FontsTheme.Plain_Texts);
        txtQuantity.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtQuantity);

        lblDuration = new JLabel("Duration : ");
        lblDuration.setBounds(510, 195, 200, 30);
        lblDuration.setFont(FontsTheme.Plain_Texts);
        lblDuration.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDuration);
      
        txtDuration = new JTextField("");
        txtDuration.setBounds(690, 195, 230, 30);
        txtDuration.setFont(FontsTheme.Plain_Texts);
        txtDuration.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDuration);
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