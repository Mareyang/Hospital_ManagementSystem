package dialogs;

import com.mycompany.hospitalmanage.*;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


   
 
public class AddBillingDialog extends JDialog implements ActionListener {

    private JLabel lblInvNo, lblPatientId, lblName, lblDoctor, lblDate, lblConsultation, lblMedicine, lblLab, lblAmount, 
            lblDiscount, lblPayment, lblStatus, lblTitle, lblSubtitle, lblInvoice;
    private JTextField txtInvNo, txtPatientId, txtName, txtDoctor, txtDate, txtConsultation, txtMedicine, txtLab, txtAmount, txtDiscount;
    private JComboBox<String> cmbStatus, cmbPayment;
    private JButton btnSave, btnCancel,btnPersonal;
    private JPanel pnlContent;
    
    
    
    public AddBillingDialog() { 
        setSize(1050, 550);
        setLocationRelativeTo(null);
        setLayout(null); 
        setModal(true);

       
        lblTitle = new JLabel("Patient Bill");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Manage and process patient billing transactions.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnPersonal = new JButton("Billing Details");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Search_Button);
        add(btnPersonal);
        
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnSave = new JButton("Confirm Payment");
        btnSave.setBounds(790, 450, 200, 30);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFont(FontsTheme.Buttons);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        add(btnCancel);
        
        
    
        //ActionListener
        btnPersonal.addActionListener(this);
        
        showBillingDetails();
    }
    
    
    
    public void showBillingDetails() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblInvNo = new JLabel("Invoice No:");
        lblInvNo.setBounds(40, 40, 200, 30);
        lblInvNo.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblInvNo);
        
        txtInvNo = new JTextField();
        txtInvNo.setBounds(220, 40, 230, 30);
        txtInvNo.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtInvNo);
        
        lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setBounds(40, 80, 200, 30);
        lblPatientId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatientId);
        
        txtPatientId = new JTextField();
        txtPatientId.setBounds(220, 80, 230, 30);
        txtPatientId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtPatientId);

        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 120, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(220, 120, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtName);
        
        lblDoctor = new JLabel("Assigned Doctor:");
        lblDoctor.setBounds(40, 160, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setBounds(220, 160, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDoctor);
        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(40, 200, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(220, 200, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
        
        lblConsultation = new JLabel("Consultation Fee:");
        lblConsultation.setBounds(40, 240, 200, 30);
        lblConsultation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblConsultation);
        
        txtConsultation = new JTextField();
        txtConsultation.setBounds(220, 240, 230, 30);
        txtConsultation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtConsultation);
        
        
        // Left Side
        lblMedicine = new JLabel("Medicine Fee:");
        lblMedicine.setBounds(510, 40, 200, 30);
        lblMedicine.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblMedicine);
        
        txtMedicine = new JTextField();
        txtMedicine.setBounds(690, 40, 230, 30);
        txtMedicine.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtMedicine);
        
        lblLab = new JLabel("Laboratory Fee:");
        lblLab.setBounds(510, 80, 200, 30);
        lblLab.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblLab);
        
        txtLab = new JTextField();
        txtLab.setBounds(690, 80, 230, 30);
        txtLab.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtLab);
        
        lblAmount = new JLabel("Total Amount:");
        lblAmount.setBounds(510, 120, 200, 30);
        lblAmount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(690, 120, 230, 30);
        txtAmount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtAmount);

        lblDiscount = new JLabel("Discount:");
        lblDiscount.setBounds(510, 160, 200, 30);
        lblDiscount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDiscount);
        
        txtDiscount = new JTextField();
        txtDiscount.setBounds(690, 160, 230, 30);
        txtDiscount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDiscount);
        
        lblPayment = new JLabel("Payment Method:");
        lblPayment.setBounds(510, 200, 200, 30);
        lblPayment.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPayment);
        
        cmbPayment = new JComboBox<> (new String[] {
            " ", "Cash", "GCash", "Card", "Insurance"}
        );
        cmbPayment.setBounds(690, 200, 230, 30);
        cmbPayment.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbPayment);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(510, 240, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
   
        cmbStatus = new JComboBox<> (new String[] {
            " ", "Paid", "Pending", "Overdue"}
        );
        cmbStatus.setBounds(690, 240, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbStatus);


        
        
       
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnCancel);
            dispose();
        }
    }
    
        
