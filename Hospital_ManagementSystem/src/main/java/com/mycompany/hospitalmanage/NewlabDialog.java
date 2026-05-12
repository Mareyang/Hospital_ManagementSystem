/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author eiros
 */
public class NewlabDialog extends JDialog {
    
        NewlabDialog() {
        
        
        setLayout(null);
        setSize(850, 570);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        JLabel lblDialogTitle = new JLabel("New Lab Order");
        lblDialogTitle.setBounds(40, 25, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);
        
        JLabel lblDialogDetails = new JLabel("Create a laboratory request for a patient");
        lblDialogDetails.setBounds(40, 60, 450, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);
        
        JLabel lblPatientID = createFormLabel("Patient ID:");
        lblPatientID.setBounds(70, 120, 160, 30);
        add(lblPatientID);
        
        JTextField txtPatientID = createFormTextField();
        txtPatientID.setBounds(230, 120, 250, 30);
        add(txtPatientID);
        
        JButton btnFindPatient = new JButton("Find Patient");
        btnFindPatient.setBounds(500, 120, 160, 30);
        btnFindPatient.setFont(FontsTheme.Info_Texts);
        btnFindPatient.setBackground(ColorsTheme.Search_Button);
        btnFindPatient.setForeground(ColorsTheme.Text_White);
        add(btnFindPatient);
        
        JLabel lblPatientName = createFormLabel("Patient Name:");
        lblPatientName.setBounds(70, 165, 160, 30);
        add(lblPatientName);
        
        JTextField txtPatientName = createFormTextField();
        txtPatientName.setBounds(230, 165, 430, 30);
        add(txtPatientName);
        
        JLabel lblTestType = createFormLabel("Test Type:");
        lblTestType.setBounds(70, 210, 160, 30);
        add(lblTestType);
        
        JComboBox<String> cmbTestType = new JComboBox<>(new String[]{
                "Complete Blood Count",
                "Urinalysis",
                "Blood Chemistry",
                "Lipid Profile",
                "Fasting Blood Sugar",
                "Chest X-Ray",
                "ECG"
        });
        cmbTestType.setBounds(230, 210, 430, 30);
        cmbTestType.setFont(FontsTheme.Info_Texts);
        add(cmbTestType);
        
        JLabel lblPriority = createFormLabel("Priority:");
        lblPriority.setBounds(70, 255, 160, 30);
        add(lblPriority);
        
        JComboBox<String> cmbPriority = new JComboBox<>(new String[]{"Routine", "STAT"});
        cmbPriority.setBounds(230, 255, 180, 30);
        cmbPriority.setFont(FontsTheme.Info_Texts);
        add(cmbPriority);
        
        JLabel lblRequestedDate = createFormLabel("Requested Date:");
        lblRequestedDate.setBounds(440, 255, 160, 30);
        add(lblRequestedDate);
        
        JTextField txtRequestedDate = createFormTextField();
        txtRequestedDate.setText("MM/DD/YYYY");
        txtRequestedDate.setBounds(590, 255, 180, 30);
        add(txtRequestedDate);
        
        JLabel lblRequestedBy = createFormLabel("Requested By:");
        lblRequestedBy.setBounds(70, 300, 160, 30);
        add(lblRequestedBy);
        
        JTextField txtRequestedBy = createFormTextField();
        txtRequestedBy.setBounds(230, 300, 430, 30);
        add(txtRequestedBy);
        
        JLabel lblNotes = createFormLabel("Clinical Notes:");
        lblNotes.setBounds(70, 355, 200, 30);
        add(lblNotes);
        
        JTextArea txaNotes = new JTextArea("Write notes here...");
        txaNotes.setFont(FontsTheme.Info_Texts);
        txaNotes.setForeground(ColorsTheme.Text_Gray);
        txaNotes.setLineWrap(true);
        txaNotes.setWrapStyleWord(true);
        
        JScrollPane scrollNotes = new JScrollPane(txaNotes);
        scrollNotes.setBounds(70, 390, 700, 80);
        add(scrollNotes);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(450, 480, 120, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);
        
        JButton btnSave = new JButton("Save Lab Order");
        btnSave.setBounds(590, 480, 200, 40);
        btnSave.setFont(FontsTheme.Buttons);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        add(btnSave);
        
        setVisible(true);
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
}
