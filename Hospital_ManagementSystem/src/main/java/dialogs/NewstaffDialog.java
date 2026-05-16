/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import panels.ColorsTheme;
import panels.FontsTheme;

/**
 *
 * @author Admin
 */
public class NewstaffDialog extends JDialog {
    
    public NewstaffDialog() {
        setLayout(null);
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        JLabel lblDialogTitle = new JLabel("Employee Information");
        lblDialogTitle.setBounds(40, 25, 350, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        JLabel lblDialogDetails = new JLabel("Complete all the required fields to add a record.");
        lblDialogDetails.setBounds(40, 60, 520, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDialogDetails);

        JPanel pnlTabPersonal = createTab("Personal Information");
        pnlTabPersonal.setBounds(50, 115, 250, 40);
        add(pnlTabPersonal);

        JPanel pnlTabPerf = createTab("Performance");
        pnlTabPerf.setBounds(300, 115, 250, 40);
        add(pnlTabPerf);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(50, 155, 950, 325);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);

        JLabel lblEmpID = createFormLabel("Employee ID :");
        lblEmpID.setBounds(40, 40, 170, 30);
        pnlForm.add(lblEmpID);

        JTextField txtEmpID = createFormTextField();
        txtEmpID.setBounds(220, 40, 230, 30);
        pnlForm.add(txtEmpID);

        JLabel lblName = createFormLabel("Full Name :");
        lblName.setBounds(40, 80, 170, 30);
        pnlForm.add(lblName);

        JTextField txtName = createFormTextField();
        txtName.setBounds(220, 80, 230, 30);
        pnlForm.add(txtName);

        JLabel lblBday = createFormLabel("Birthday :");
        lblBday.setBounds(40, 120, 170, 30);
        pnlForm.add(lblBday);

        JTextField txtBday = createFormTextField();
        txtBday.setBounds(220, 120, 230, 30);
        pnlForm.add(txtBday);

        JLabel lblGen = createFormLabel("Gender :");
        lblGen.setBounds(40, 160, 170, 30);
        pnlForm.add(lblGen);

        JComboBox<String> cmbGen = createFormComboBox(new String[]{"", "Female", "Male"});
        cmbGen.setBounds(220, 160, 230, 30);
        pnlForm.add(cmbGen);

        
        JLabel lblEmail = createFormLabel("Email Address: ");
        lblEmail.setBounds(40, 200, 170, 30);
        pnlForm.add(lblEmail);
        
        JTextField txtEmail = createFormTextField();
        txtEmail.setBounds(220, 200, 230, 30);
        pnlForm.add(txtEmail);

        JLabel lblContact = createFormLabel("Contact Number :");
        lblContact.setBounds(40, 240, 170, 30);
        pnlForm.add(lblContact);

        JTextField txtContact = createFormTextField();
        txtContact.setBounds(220, 240, 230, 30);
        pnlForm.add(txtContact);

        JLabel lblMarital = createFormLabel("Marital Status :");
        lblMarital.setBounds(540, 40, 170, 30);
        pnlForm.add(lblMarital);

        JComboBox<String> cmbMarital = createFormComboBox(new String[]{"", "Single", "Married", "Widowed", "Separated"});
        cmbMarital.setBounds(720, 40, 180, 30);
        pnlForm.add(cmbMarital);

        JLabel lblDep = createFormLabel("Department :");
        lblDep.setBounds(540, 80, 170, 30);
        pnlForm.add(lblDep);

        JTextField txtDep = createFormTextField();
        txtDep.setBounds(720, 80, 180, 30);
        pnlForm.add(txtDep);

        JLabel lblRole = createFormLabel("Role :");
        lblRole.setBounds(540, 120, 170, 30);
        pnlForm.add(lblRole);

        JTextField txtRole = createFormTextField();
        txtRole.setBounds(720, 120, 180, 30);
        pnlForm.add(txtRole);

        JLabel lblHired = createFormLabel("Hire Date :");
        lblHired.setBounds(540, 160, 170, 30);
        pnlForm.add(lblHired);

        JTextField txtHired = createFormTextField();
        txtHired.setBounds(720, 160, 180, 30);
        pnlForm.add(txtHired);

        JLabel lblOff = createFormLabel("Day Off :");
        lblOff.setBounds(540, 200, 170, 30);
        pnlForm.add(lblOff);

        JComboBox<String> cmbOff = createFormComboBox(new String[]{"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        cmbOff.setBounds(720, 200, 180, 30);
        pnlForm.add(cmbOff);

        JLabel lblStatus = createFormLabel("Status :");
        lblStatus.setBounds(540, 240, 170, 30);
        pnlForm.add(lblStatus);

        JComboBox<String> cmbStats = createFormComboBox(new String[]{"", "Active", "On Leave", "Contract"});
        cmbStats.setBounds(720, 240, 180, 30);
        pnlForm.add(cmbStats);
       

        JButton btnSave = new JButton("Save Information");
        btnSave.setBounds(800, 495, 200, 40);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(635, 495, 150, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        setVisible(true);
        
    }

    private JPanel createTab(String text) {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(ColorsTheme.Search_Button);

        JLabel label = new JLabel(text);
        label.setBounds(0, 0, 250, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(FontsTheme.Buttons);
        label.setForeground(ColorsTheme.Text_White);
        tab.add(label);

        return tab;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FontsTheme.Plain_Texts);
        label.setForeground(ColorsTheme.Text_Black);
        return label;
    }

    private JTextField createFormTextField() {
        JTextField textField = new JTextField();
        textField.setFont(FontsTheme.Info_Texts);
        return textField;
    }

    private JComboBox<String> createFormComboBox(String[] values) {
        JComboBox<String> comboBox = new JComboBox<>(values);
        comboBox.setFont(FontsTheme.Info_Texts);
        return comboBox;
    }
}

