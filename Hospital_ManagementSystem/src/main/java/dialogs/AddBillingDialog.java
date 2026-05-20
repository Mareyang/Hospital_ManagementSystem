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
    private JButton btnSave, btnCancel, btnBill;
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
        
        btnBill = new JButton("Billing Details");
        btnBill.setBounds(40, 100, 250, 40);
        btnBill.setFont(FontsTheme.Buttons);
        btnBill.setForeground(ColorsTheme.Text_White);
        btnBill.setBackground(ColorsTheme.Search_Button);
        btnBill.setFocusPainted(false);
        add(btnBill);
        
        
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
        btnSave.setFocusPainted(false);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        
    
        //ActionListener
        btnBill.addActionListener(this);
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
        
        showBillingDetails();
    }
    
    
    
    public void showBillingDetails() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        
        // Invoice Label and TextField
        lblInvNo = new JLabel("Invoice No:");
        lblInvNo.setBounds(40, 40, 200, 30);
        lblInvNo.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblInvNo);
        
        txtInvNo = new JTextField();
        txtInvNo.setBounds(220, 40, 230, 30);
        txtInvNo.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtInvNo);
        
        
        // Patient ID Label and TextField
        lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setBounds(40, 80, 200, 30);
        lblPatientId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatientId);
        
        txtPatientId = new JTextField();
        txtPatientId.setBounds(220, 80, 230, 30);
        txtPatientId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtPatientId);
        
        // Patient Name Label and TextField
        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 120, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(220, 120, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtName);
        
        // Assigned Doctor Label and TextField
        lblDoctor = new JLabel("Assigned Doctor:");
        lblDoctor.setBounds(40, 160, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setBounds(220, 160, 230, 30);
        txtDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDoctor);
        
        // Date Label and TextField 
        lblDate = new JLabel("Date:");
        lblDate.setBounds(40, 200, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(220, 200, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDate);
       
        // Consultation Label and TextField
        lblConsultation = new JLabel("Consultation Fee:");
        lblConsultation.setBounds(40, 240, 200, 30);
        lblConsultation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblConsultation);
        
        txtConsultation = new JTextField();
        txtConsultation.setBounds(220, 240, 230, 30);
        txtConsultation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtConsultation);
        
        
        // Left Side : Fees 
        // Medicine Fee Label and TextField
        lblMedicine = new JLabel("Medicine Fee:");
        lblMedicine.setBounds(510, 40, 200, 30);
        lblMedicine.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblMedicine);
        
        txtMedicine = new JTextField();
        txtMedicine.setBounds(690, 40, 230, 30);
        txtMedicine.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtMedicine);
        
        // Laboratory Fee Label and TextField
        lblLab = new JLabel("Laboratory Fee:");
        lblLab.setBounds(510, 80, 200, 30);
        lblLab.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblLab);
        
        txtLab = new JTextField();
        txtLab.setBounds(690, 80, 230, 30);
        txtLab.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtLab);
        
        // Sum of all fees and bills
        lblAmount = new JLabel("Total Amount:");
        lblAmount.setBounds(510, 120, 200, 30);
        lblAmount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(690, 120, 230, 30);
        txtAmount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtAmount);
       
        // Includes discount to lessen the total amount needs to pay
        lblDiscount = new JLabel("Discount:");
        lblDiscount.setBounds(510, 160, 200, 30);
        lblDiscount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDiscount);
        
        txtDiscount = new JTextField();
        txtDiscount.setBounds(690, 160, 230, 30);
        txtDiscount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(txtDiscount);
        
        // Payment methods
        lblPayment = new JLabel("Payment Method:");
        lblPayment.setBounds(510, 200, 200, 30);
        lblPayment.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPayment);
        
        // Options for payment methods
        cmbPayment = new JComboBox<> (new String[] {
            " ", "Cash", "GCash", "Card", "Insurance"}
        );
        cmbPayment.setBounds(690, 200, 230, 30);
        cmbPayment.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbPayment);
         
        // Bills status
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(510, 240, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
   
        // Options for bills status 
        cmbStatus = new JComboBox<> (new String[] {
            " ", "Paid", "Pending", "Overdue"}
        );
        cmbStatus.setBounds(690, 240, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(cmbStatus);


        
        
       
    }
        @Override
        public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBill) {
            showBillingDetails();
        }
            
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnSave) {
            JOptionPane.showMessageDialog(this, "Billing payment completed successfully!", 
                    "Billing Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
