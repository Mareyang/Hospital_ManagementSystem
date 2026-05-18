/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
/**
 *
 * @author Admin
 */
public class NewStaffDialog extends JDialog {
    
    private JLabel lblDialogTitle, lblDialogDetails;
    // private JButton ;
    private JPanel pnlTabPersonal;
    
    
    
    public NewStaffDialog() {
        setLayout(null);
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        lblDialogTitle = new JLabel("Employee Information");
        lblDialogTitle.setBounds(40, 25, 350, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        lblDialogDetails = new JLabel("Complete all the required fields to add a record.");
        lblDialogDetails.setBounds(40, 60, 520, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDialogDetails);

        pnlTabPersonal = createTab("Personal Information");
        pnlTabPersonal.setBounds(50, 115, 250, 40);
        add(pnlTabPersonal);

        JPanel pnlTabPerf = createTab("Performance");
        pnlTabPerf.setBounds(300, 115, 250, 40);
        add(pnlTabPerf);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(40, 140, 950, 300);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        btnStaff.addActionListener(this);
        btnPerf.addActionListener(this);

        showStaffInfo();
        
    }
        
      public void showStaffInfo() {
        pnlForm.removeAll();
        pnlForm.repaint();
        pnlForm.revalidate();

        JLabel lblEmpID = new JLabel("Employee ID :");
        lblEmpID.setBounds(40, 40, 170, 30);
        lblEmpID.setFont(FontsTheme.Plain_Texts);
        lblEmpID.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmpID);

        JTextField txtEmpID = new JTextField("");
        txtEmpID.setBounds(220, 40, 230, 30);
        txtEmpID.setFont(FontsTheme.Plain_Texts);
        txtEmpID.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmpID);

        JLabel lblName = new JLabel("Full Name :");
        lblName.setBounds(40, 80, 170, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblName);

        JTextField txtName = new JTextField("");
        txtName.setBounds(220, 80, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtName);

        JLabel lblBday = new JLabel("Birthday :");
        lblBday.setBounds(40, 120, 170, 30);
        lblBday.setFont(FontsTheme.Plain_Texts);
        lblBday.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblBday);

        JTextField txtBday = new JTextField("");
        txtBday.setBounds(220, 120, 230, 30);
        txtBday.setFont(FontsTheme.Plain_Texts);
        txtBday.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtBday);

        JLabel lblGen = new JLabel("Gender :");
        lblGen.setBounds(40, 160, 170, 30);
        lblGen.setFont(FontsTheme.Plain_Texts);
        lblGen.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblGen);

        JComboBox<String> cmbGen = new JComboBox(new String[]{"", "Female", "Male"});
        cmbGen.setBounds(220, 160, 230, 30);
        cmbGen.setFont(FontsTheme.Plain_Texts);
        cmbGen.setForeground(ColorsTheme.Text_Black);
        cmbGen.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbGen);

        
        JLabel lblEmail = new JLabel("Email Address: ");
        lblEmail.setBounds(40, 200, 170, 30);
        lblEmail.setFont(FontsTheme.Plain_Texts);
        lblEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblEmail);
        
        JTextField txtEmail = new JTextField("");
        txtEmail.setBounds(220, 200, 230, 30);
        txtEmail.setFont(FontsTheme.Plain_Texts);
        txtEmail.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtEmail);

        JLabel lblContact = new JLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        lblContact.setFont(FontsTheme.Plain_Texts);
        lblContact.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblContact);

        JTextField txtContact = new JTextField("");
        txtContact.setBounds(220, 240, 230, 30);
        txtContact.setFont(FontsTheme.Plain_Texts);
        txtContact.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtContact);
        
        
        // LEFT SIDE

        JLabel lblMarital = new JLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        lblMarital.setFont(FontsTheme.Plain_Texts);
        lblMarital.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblMarital);

        JComboBox<String> cmbMarital = new JComboBox(new String[]{"", "Single", "Married", "Widowed", "Separated"});
        cmbMarital.setBounds(720, 40, 180, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts);
        cmbMarital.setForeground(ColorsTheme.Text_Black);
        cmbMarital.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbMarital);

        JLabel lblDep = new JLabel("Department :");
        lblDep.setBounds(540, 80, 170, 30);
        lblDep.setFont(FontsTheme.Plain_Texts);
        lblDep.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblDep);

        JTextField txtDep = new JTextField("");
        txtDep.setBounds(720, 80, 180, 30);
        txtDep.setFont(FontsTheme.Plain_Texts);
        txtDep.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtDep);

        JLabel lblRole = new JLabel("Role :");
        lblRole.setBounds(540, 120, 170, 30);
        lblRole.setFont(FontsTheme.Plain_Texts);
        lblRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblRole);

        JTextField txtRole =  new JTextField("");
        txtRole.setBounds(720, 120, 180, 30);
        txtRole.setFont(FontsTheme.Plain_Texts);
        txtRole.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtRole);

        JLabel lblHired = new JLabel("Hire Date :");
        lblHired.setBounds(540, 160, 170, 30);
        lblHired.setFont(FontsTheme.Plain_Texts);
        lblHired.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblHired);

        JTextField txtHired = new JTextField("MM/DD/YYYY");
        txtHired.setBounds(720, 160, 180, 30);
        txtHired.setFont(FontsTheme.Plain_Texts);
        txtHired.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(txtHired);

        JLabel lblOff = new JLabel("Day Off :");
        lblOff.setBounds(540, 200, 170, 30);
        lblOff.setFont(FontsTheme.Plain_Texts);
        lblOff.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblOff);

        JComboBox<String> cmbOff = new JComboBox(new String[]{"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        cmbOff.setBounds(720, 200, 180, 30);
        cmbOff.setFont(FontsTheme.Plain_Texts);
        cmbOff.setForeground(ColorsTheme.Text_Black);
        cmbOff.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbOff);

        JLabel lblStatus = new JLabel("Status :");
        lblStatus.setBounds(540, 240, 170, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlForm.add(lblStatus);

        JComboBox<String> cmbStats = new JComboBox(new String[]{"", "Active", "On Leave", "Contract"});
        cmbStats.setBounds(720, 240, 180, 30);
        cmbStats.setFont(FontsTheme.Plain_Texts);
        cmbStats.setForeground(ColorsTheme.Text_Black);
        cmbStats.setBackground(ColorsTheme.Main_Card);
        pnlForm.add(cmbStats);
       
        // OPTIONS BUTTON

        JButton btnSave = new JButton("Save Information");
        btnSave.setBounds(800, 495, 200, 40);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setBackground(ColorsTheme.Green);
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(635, 495, 150, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);
        
    }
      
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
         
        JComboBox<String> cmbSRole = new JComboBox(new String[]{"", "Doctor", "Nurse", "Admin"});
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
         
        JComboBox<String> cmbSRate = new JComboBox(new String[]{"", "5 - Excellent", "4 - Very Good", "3 - Average", "2 - Below Average", "1- Poor"});
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
        
    }
}

