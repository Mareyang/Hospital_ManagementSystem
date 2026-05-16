package dialogs;

import com.mycompany.hospitalmanage.*;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

    public class BillDialog extends JDialog implements ActionListener {

    JLabel lblInvNo,lblPatientId, lblName,lblDoctor,lblDate,lblConsultation,lblMedicine,lblLab, lblAmount,lblDiscount, lblPayment,lblStatus, lblTitle,lblSubtitle,lblInvoice;
    JTextField txtInvNo,txtPatientId, txtName, txtDoctor,txtDate,txtConsultation,txtMedicine,txtLab,txtAmount,txtDiscount;
    JComboBox<String> cmbStatus, cmbPayment;
    JButton btnSave, btnCancel,btnPersonal,btnHistory;
    JPanel pnlContent;
    public BillDialog() {

        
        setSize(1050, 550);
        setLocationRelativeTo(null);
        setLayout(null); 
        setModal(true);

       
        lblTitle = new JLabel("Patient Invoice");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Black);
        add(lblSubtitle);
        
        btnPersonal = new JButton("Personal Information");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Search_Button);
        add(btnPersonal);
        
        btnHistory = new JButton("Medical History");
        btnHistory.setBounds(290, 100, 250, 40);
        btnHistory.setFont(FontsTheme.Buttons);
        btnHistory.setForeground(ColorsTheme.Text_White);
        btnHistory.setBackground(ColorsTheme.Search_Button);
        add(btnHistory);
        
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
    
        //ActionListener
        btnPersonal.addActionListener(this);
        btnHistory.addActionListener(this);
        
        showPersonalInfo();
    }
        public void showPersonalInfo() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblInvNo = new JLabel("Invoice No:");
        lblInvNo.setBounds(40, 40, 200, 30);
        lblInvNo.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblInvNo);
        
        txtInvNo = new JTextField();
        txtInvNo.setBounds(220, 40, 230, 30);
        pnlContent.add(txtInvNo);
        
        lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setBounds(40, 80, 200, 30);
        lblPatientId.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPatientId);
        
        txtPatientId = new JTextField();
        txtPatientId.setBounds(220, 80, 230, 30);
        pnlContent.add(txtPatientId);

        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 120, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(220, 120, 230, 30);
        pnlContent.add(txtName);
        
        lblDoctor = new JLabel("Assigned Doctor:");
        lblDoctor.setBounds(40, 160, 200, 30);
        lblDoctor.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setBounds(220, 160, 230, 30);
        pnlContent.add(txtDoctor);
        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(40, 200, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField();
        txtDate.setBounds(220, 200, 230, 30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM dd, yyyy");
        txtDate.setText(LocalDate.now().format(format));
        pnlContent.add(txtDate);
        
        lblConsultation = new JLabel("Consultation Fee:");
        lblConsultation.setBounds(40, 240, 200, 30);
        lblConsultation.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblConsultation);
        
        txtConsultation = new JTextField();
        txtConsultation.setBounds(220, 240, 230, 30);
        pnlContent.add(txtConsultation);
        
        // Left Side
        
        lblMedicine = new JLabel("Medicine Fee:");
        lblMedicine.setBounds(510, 40, 200, 30);
        lblMedicine.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblMedicine);
        
        txtMedicine = new JTextField();
        txtMedicine.setBounds(690, 40, 230, 30);
        pnlContent.add(txtMedicine);
        
        lblLab = new JLabel("Laboratory Fee:");
        lblLab.setBounds(510, 80, 200, 30);
        lblLab.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblLab);
        
        txtLab = new JTextField();
        txtLab.setBounds(690, 80, 230, 30);
        pnlContent.add(txtLab);
        
        lblAmount = new JLabel("Total Amount:");
        lblAmount.setBounds(510, 120, 200, 30);
        lblAmount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(690, 120, 230, 30);
        pnlContent.add(txtAmount);

        lblDiscount = new JLabel("Discount:");
        lblDiscount.setBounds(510, 160, 200, 30);
        lblDiscount.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblDiscount);
        
        txtDiscount = new JTextField();
        txtDiscount.setBounds(690, 160, 230, 30);
        pnlContent.add(txtDiscount);
        
        lblPayment = new JLabel("Payment Method:");
        lblPayment.setBounds(510, 200, 200, 30);
        lblPayment.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblPayment);
        
        String[] payment = {"Cash", "GCash", "Card", "Insurance"};
        cmbPayment = new JComboBox<>(payment);
        cmbPayment.setBounds(690, 200, 230, 30);
        pnlContent.add(cmbPayment);
        
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(510, 240, 200, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        pnlContent.add(lblStatus);
   
        String[] status = {"Paid", "Pending", "Overdue"};
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(690, 240, 230, 30);
        pnlContent.add(cmbStatus);


        btnSave = new JButton("Save Information");
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
        
       
    }
        public void showInvoiceHistory() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        lblInvoice = new JLabel("Invoice History");
        lblInvoice.setBounds(10, 10, 200, 30);
        lblInvoice.setFont(FontsTheme.Plain_Texts);
        lblInvoice.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblInvoice);
     
        
        
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

      
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invoice Added Successfully!"
                );

                dispose();
            }
        });
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnPersonal) {
            showPersonalInfo();
        }
        else if(e.getSource() == btnHistory) {
            showInvoiceHistory();
        }
        
    }
        
}