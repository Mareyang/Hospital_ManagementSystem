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
    private JLabel lblPatient, lblMed, lblDate, lblDura, lblDosage, lblQty, lblDiagnosis, lblTitle, lblSubtitle,
            lblFreq, lblRefill, lblNote;
    private JTextField txtPatient, txtMed, txtDate, txtQty, txtDiagnosis, txtDura, txtRefill;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JComboBox<String> cmbDosage, cmbFreq;
    private JButton btnAddInfo, btnCancel, btnPrescript;

    
    
    public AddPrescriptionDialog() {
        setSize(1050, 550);
        setLayout(null);
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
        pnlContent.setBounds(40, 140, 950, 300);
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
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
      
        btnAddInfo = new JButton("Save Prescription");
        btnAddInfo.setBounds(690, 450, 300, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Add_Confirm);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
       
        
        btnCancel.addActionListener(this);
        btnAddInfo.addActionListener(this);
        
        createPrescriptionForm();
        
    }
  
    private void createPrescriptionForm() {
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
      
        lblMed = new JLabel("Medication Name : ");
        lblMed.setBounds(40, 80, 200, 30);
        lblMed.setFont(FontsTheme.Plain_Texts);
        lblMed.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblMed);
      
        txtMed = new JTextField("");
        txtMed.setBounds(220, 80, 230, 30);
        txtMed.setFont(FontsTheme.Plain_Texts);
        txtMed.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtMed);
      
        lblDate = new JLabel("Date/Time : ");
        lblDate.setBounds(40, 120, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
      
        txtDate = new JTextField(""); 
        txtDate.setBounds(220, 120, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);

        lblDosage = new JLabel("Dosage : ");
        lblDosage.setBounds(40, 160, 200, 30);
        lblDosage.setFont(FontsTheme.Plain_Texts);
        lblDosage.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDosage);
      
        cmbDosage = new JComboBox<>(new String[]{
            " ", "Pending", "Dispensed", "Cancelled", "On Hold"
        });
        cmbDosage.setBounds(220, 160, 230, 30);
        cmbDosage.setFont(FontsTheme.Plain_Texts);
        cmbDosage.setForeground(ColorsTheme.Text_Black);
        cmbDosage.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDosage);
      
        lblFreq = new JLabel("Frequency : ");
        lblFreq.setBounds(40, 200, 200, 30);
        lblFreq.setFont(FontsTheme.Plain_Texts);
        lblFreq.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblFreq);
        
        cmbFreq = new JComboBox<>(new String[]{
            " ", "Pending", "Dispensed", "Cancelled", "On Hold"
        });
        cmbFreq.setBounds(220, 200, 230, 30);
        cmbFreq.setFont(FontsTheme.Plain_Texts);
        cmbFreq.setForeground(ColorsTheme.Text_Black);
        cmbFreq.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbFreq);
      
        
        // Left
        lblDura = new JLabel("Duration : ");
        lblDura.setBounds(510, 40, 200, 30);
        lblDura.setFont(FontsTheme.Plain_Texts);
        lblDura.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDura);
      
        txtDura = new JTextField("");
        txtDura.setBounds(690, 40, 230, 30);
        txtDura.setFont(FontsTheme.Plain_Texts);
        txtDura.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDura);

        lblQty = new JLabel("Quantity : ");
        lblQty.setBounds(510, 80, 200, 30);
        lblQty.setFont(FontsTheme.Plain_Texts);
        lblQty.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblQty);
      
        txtQty = new JTextField("");
        txtQty.setBounds(690, 80, 230, 30);
        txtQty.setFont(FontsTheme.Plain_Texts);
        txtQty.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtQty);
        
        
        lblDiagnosis = new JLabel("Diagnosis : ");
        lblDiagnosis.setBounds(510, 120, 200, 30);
        lblDiagnosis.setFont(FontsTheme.Plain_Texts);
        lblDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDiagnosis);
      
        txtDiagnosis = new JTextField("");
        txtDiagnosis.setBounds(690, 120, 230, 30);
        txtDiagnosis.setFont(FontsTheme.Plain_Texts);
        txtDiagnosis.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDiagnosis);
        
        
        lblRefill = new JLabel("Refill Info : ");
        lblRefill.setBounds(510, 160, 200, 30);
        lblRefill.setFont(FontsTheme.Plain_Texts);
        lblRefill.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRefill);
      
        txtRefill = new JTextField("");
        txtRefill.setBounds(690, 160, 230, 30);
        txtRefill.setFont(FontsTheme.Plain_Texts);
        txtRefill.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtRefill);
        
        lblNote = new JLabel("Special Notes : ");
        lblNote.setBounds(510, 200, 200, 30);
        lblNote.setFont(FontsTheme.Plain_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(" ");
        txaNote.setText("Write here...");
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Dialog_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(690, 200, 230, 50);
        pnlContent.setLayout(null);
        pnlContent.add(scrollNote);
        
        
        
        
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