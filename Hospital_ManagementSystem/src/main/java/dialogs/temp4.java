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
 * @author Admin
 */
public class temp4 extends JDialog implements ActionListener {
      
    private JPanel pnlForm;
    private JLabel lblDialogTitle, lblDialogDetails, lblEmpID, lblName, lblBday,lblGen,lblEmail, lblContact, 
            lblMarital, lblDep, lblRole, lblHired, lblOff, lblStatus, lblPR, lblCName, lblSName,lblLine, lblSRole, lblSRate, lblCS;
    private JTextField txtEmpID, txtName, txtContact, txtEmail, txtBday, txtDep, txtRole, txtHired, txtCName, txtSName;
    private JButton btnStaff, btnPerf, btnSave,btnCancel;
    private JComboBox<String> cmbGen, cmbMarital, cmbStats, cmbOff, cmbSRole, cmbSRate;
    private JTextArea txaComs;
    private JScrollPane scrollComs;
    
    
    public temp4() {
        setLayout(null);
        setSize(1050, 550);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        
        lblDialogTitle = new JLabel("Staff Profile Entry");
        lblDialogTitle.setBounds(30, 10, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Register new hospital personnel and configure departmental assignments.");
        lblDialogDetails.setBounds(30, 40, 650, 30);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        btnStaff = new JButton("Staff Information");
        btnStaff.setBounds(40, 100, 250, 40);
        btnStaff.setFont(FontsTheme.Buttons);
        btnStaff.setForeground(ColorsTheme.Text_White);
        btnStaff.setBackground(ColorsTheme.Header);
        btnStaff.setFocusPainted(false);
        add(btnStaff);
        
        btnPerf = new JButton("Performance and Suggestions");
        btnPerf.setBounds(290, 100, 320, 40);
        btnPerf.setFont(FontsTheme.Buttons);
        btnPerf.setForeground(ColorsTheme.Text_White);
        btnPerf.setBackground(ColorsTheme.Header);
        btnPerf.setFocusPainted(false);
        add(btnPerf);
        
        
        pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 140, 950, 300);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        
        // OPTIONS BUTTON

        btnSave = new JButton("Save Staff Profile");
        btnSave.setBounds(690, 450, 300, 30);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setBackground(ColorsTheme.Green);
        btnSave.setFocusPainted(false);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        
        
        
        btnStaff.addActionListener(this);
        btnPerf.addActionListener(this);
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);
        
        

        showStaffInfo();
        
    }
        
    // To add new employee 
    public void showStaffInfo() {
        pnlForm.removeAll();
        pnlForm.repaint();
        pnlForm.revalidate();

        lblEmpID = new JLabel("Employee ID :");
        lblEmpID.setBounds(40, 40, 170, 30);
        lblEmpID.setFont(FontsTheme.Plain_Texts);
        lblEmpID.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmpID);

        txtEmpID = new JTextField("");
        txtEmpID.setBounds(220, 40, 230, 30);
        txtEmpID.setFont(FontsTheme.Plain_Texts);
        txtEmpID.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmpID);

        lblName = new JLabel("Full Name :");
        lblName.setBounds(40, 80, 170, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblName);

        txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtName);

        lblBday = new JLabel("Birthday :");
        lblBday.setBounds(40, 120, 170, 30);
        lblBday.setFont(FontsTheme.Plain_Texts);
        lblBday.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblBday);

        txtBday = new JTextField("");
        txtBday.setBounds(220, 120, 230, 30);
        txtBday.setFont(FontsTheme.Plain_Texts);
        txtBday.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtBday);

        lblGen = new JLabel("Gender :");
        lblGen.setBounds(40, 160, 170, 30);
        lblGen.setFont(FontsTheme.Plain_Texts);
        lblGen.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblGen);

        cmbGen = new JComboBox(new String[]{"", "Female", "Male"});
        cmbGen.setBounds(220, 160, 230, 30);
        cmbGen.setFont(FontsTheme.Plain_Texts);
        cmbGen.setForeground(ColorsTheme.Text_Black);
        cmbGen.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbGen);

        
        lblEmail = new JLabel("Email Address: ");
        lblEmail.setBounds(40, 200, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmail);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(220, 200, 230, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmail);

        lblContact = new JLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        lblContact.setFont(FontsTheme.Plain_Texts);
        lblContact.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblContact);

        txtContact = new JTextField("");
        txtContact.setBounds(220, 240, 230, 30);
        txtContact.setFont(FontsTheme.Plain_Texts);
        txtContact.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtContact);
        
        
        // LEFT SIDE

        lblMarital = new JLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        lblMarital.setFont(FontsTheme.Plain_Texts);
        lblMarital.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblMarital);

        // Options for Marital status
        cmbMarital = new JComboBox(new String[]{
        "", "Single", "Married", "Widowed", "Separated"});
        cmbMarital.setBounds(720, 40, 180, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts);
        cmbMarital.setForeground(ColorsTheme.Text_Black);
        cmbMarital.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbMarital);

        lblDep = new JLabel("Department :");
        lblDep.setBounds(540, 80, 170, 30);
        lblDep.setFont(FontsTheme.Plain_Texts);
        lblDep.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblDep);

        txtDep = new JTextField("");
        txtDep.setBounds(720, 80, 180, 30);
        txtDep.setFont(FontsTheme.Plain_Texts);
        txtDep.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtDep);

        lblRole = new JLabel("Role :");
        lblRole.setBounds(540, 120, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblRole);

        txtRole =  new JTextField("");
        txtRole.setBounds(720, 120, 180, 30);
        txtRole.setFont(FontsTheme.Plain_Texts);
        txtRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtRole);

        lblHired = new JLabel("Hire Date :");
        lblHired.setBounds(540, 160, 170, 30);
        lblHired.setFont(FontsTheme.Plain_Texts);
        lblHired.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblHired);

        txtHired = new JTextField("MM/DD/YYYY");
        txtHired.setBounds(720, 160, 180, 30);
        txtHired.setFont(FontsTheme.Plain_Texts);
        txtHired.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtHired);

        lblOff = new JLabel("Day Off :");
        lblOff.setBounds(540, 200, 170, 30);
        lblOff.setFont(FontsTheme.Plain_Texts);
        lblOff.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblOff);

        // Option for day off
        cmbOff = new JComboBox(new String[]{
        "", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        cmbOff.setBounds(720, 200, 180, 30);
        cmbOff.setFont(FontsTheme.Plain_Texts);
        cmbOff.setForeground(ColorsTheme.Text_Black);
        cmbOff.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbOff);

        lblStatus = new JLabel("Status :");
        lblStatus.setBounds(540, 240, 170, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblStatus);

        cmbStats = new JComboBox(new String[]{
        "", "Active", "On Leave", "Contract"});
        cmbStats.setBounds(720, 240, 180, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbStats);
       
        
        
    }
      // To fill up performance forms to rate staffs
    public void showPerfRate() {
        pnlForm.removeAll();
        pnlForm.repaint();
        pnlForm.revalidate();
        
          
        lblPR = new JLabel("Performance Rate");
        lblPR.setBounds(70, 10, 200, 30);
        lblPR.setFont(FontsTheme.Title_Texts);
        lblPR.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblPR);
        
        
        lblCName = new JLabel("Client Name : ");
        lblCName.setBounds(40, 50, 170, 30);
        lblCName.setFont(FontsTheme.Plain_Texts);
        lblCName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblCName);
        
        txtCName = new JTextField("");
        txtCName.setBounds(250, 50, 250, 30);
        txtCName.setFont(FontsTheme.Plain_Texts);
        txtCName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtCName);
          
        lblLine = new JLabel("Staff Details");
        lblLine.setBounds(70, 100, 380, 30);
        lblLine.setFont(FontsTheme.Title_Texts);
        lblLine.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblLine);
        
        lblSName = new JLabel("Employee Name : ");
        lblSName.setBounds(40, 140, 170, 50);
        lblSName.setFont(FontsTheme.Plain_Texts);
        lblSName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblSName);
        
        txtSName = new JTextField(" ");
        txtSName.setBounds(250, 150, 250, 30);
        txtSName.setFont(FontsTheme.Plain_Texts);
        txtSName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtSName);
        
        lblSRole = new JLabel("Role : ");
        lblSRole.setBounds(40, 180, 170, 50);
        lblSRole.setFont(FontsTheme.Plain_Texts);
        lblSRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblSRole);
         
        cmbSRole = new JComboBox(new String[]{
        "", "Doctor", "Nurse", "Admin"});
        cmbSRole.setBounds(250, 190, 250, 30);
        cmbSRole.setFont(FontsTheme.Plain_Texts);
        cmbSRole.setForeground(ColorsTheme.Text_Black);
        cmbSRole.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbSRole);
        
        
        lblSRate = new JLabel("Rate (1-5) : ");
        lblSRate.setBounds(40, 220, 170, 50);
        lblSRate.setFont(FontsTheme.Plain_Texts);
        lblSRate.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblSRate);
         
        cmbSRate = new JComboBox(new String[]{
        "", "5 - Excellent", "4 - Very Good", "3 - Average", "2 - Below Average", "1- Poor"});
        cmbSRate.setBounds(250, 230, 250, 30);
        cmbSRate.setFont(FontsTheme.Plain_Texts);
        cmbSRate.setForeground(ColorsTheme.Text_Black);
        cmbSRate.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbSRate);
        
        
        lblCS = new JLabel("Comments & Suggestions");
        lblCS.setBounds(540, 10, 550, 30);
        lblCS.setFont(FontsTheme.Title_Texts);
        lblCS.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblCS);
        
        txaComs = new JTextArea(" ");
        txaComs.setText("Write here...");
        txaComs.setEditable(true);
        txaComs.setFont(FontsTheme.Dialog_Texts);
        txaComs.setForeground(ColorsTheme.Text_Gray);
        txaComs.setLineWrap(true);
        txaComs.setWrapStyleWord(true);
        
        scrollComs = new JScrollPane(txaComs);
        scrollComs.setBounds(550, 50, 350, 200);
        pnlForm.setLayout(null);
        pnlForm.add(scrollComs);
        
      }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStaff) {
            showStaffInfo();
        }
        else if(e.getSource() == btnPerf) {
            showPerfRate();
        }
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnSave) {
            JOptionPane.showMessageDialog(this, "Staff record added successfully!", 
                    "Staff Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}