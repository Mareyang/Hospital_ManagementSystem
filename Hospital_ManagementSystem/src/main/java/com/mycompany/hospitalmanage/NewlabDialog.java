/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import java.awt.Dialog;
import java.awt.Image;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        JLabel lblDialogIcon = createIconLabel("/icons/lab.png");
        lblDialogIcon.setBounds(40, 25, 56, 56);
        add(lblDialogIcon);

        JLabel lblDialogTitle = new JLabel("New Lab Order");
        lblDialogTitle.setBounds(110, 25, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);
        
        JLabel lblDialogDetails = new JLabel("Create a laboratory request for a patient");
        lblDialogDetails.setBounds(110, 60, 450, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(50, 115, 950, 365);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);
        
        JLabel lblPatientID = createFormLabel("Patient ID:");
        lblPatientID.setBounds(70, 35, 160, 30);
        pnlForm.add(lblPatientID);
        
        JTextField txtPatientID = createFormTextField();
        txtPatientID.setBounds(230, 35, 300, 30);
        pnlForm.add(txtPatientID);
        
        JButton btnFindPatient = new JButton("Find Patient");
        btnFindPatient.setBounds(550, 35, 170, 30);
        btnFindPatient.setFont(FontsTheme.Info_Texts);
        btnFindPatient.setBackground(ColorsTheme.Search_Button);
        btnFindPatient.setForeground(ColorsTheme.Text_White);
        pnlForm.add(btnFindPatient);
        
        JLabel lblPatientName = createFormLabel("Patient Name:");
        lblPatientName.setBounds(70, 80, 160, 30);
        pnlForm.add(lblPatientName);
        
        JTextField txtPatientName = createFormTextField();
        txtPatientName.setBounds(230, 80, 600, 30);
        pnlForm.add(txtPatientName);
        
        JLabel lblTestType = createFormLabel("Test Type:");
        lblTestType.setBounds(70, 125, 160, 30);
        pnlForm.add(lblTestType);
        
        JComboBox<String> cmbTestType = new JComboBox<>(new String[]{
                "Complete Blood Count",
                "Urinalysis",
                "Blood Chemistry",
                "Lipid Profile",
                "Fasting Blood Sugar",
                "Chest X-Ray",
                "ECG"
        });
        cmbTestType.setBounds(230, 125, 600, 30);
        cmbTestType.setFont(FontsTheme.Info_Texts);
        pnlForm.add(cmbTestType);
        
        JLabel lblPriority = createFormLabel("Priority:");
        lblPriority.setBounds(70, 170, 160, 30);
        pnlForm.add(lblPriority);
        
        JComboBox<String> cmbPriority = new JComboBox<>(new String[]{"Routine", "STAT"});
        cmbPriority.setBounds(230, 170, 220, 30);
        cmbPriority.setFont(FontsTheme.Info_Texts);
        pnlForm.add(cmbPriority);
        
        JLabel lblRequestedDate = createFormLabel("Requested Date:");
        lblRequestedDate.setBounds(510, 170, 170, 30);
        pnlForm.add(lblRequestedDate);
        
        JTextField txtRequestedDate = createFormTextField();
        txtRequestedDate.setText("MM/DD/YYYY");
        txtRequestedDate.setBounds(680, 170, 150, 30);
        pnlForm.add(txtRequestedDate);
        
        JLabel lblRequestedBy = createFormLabel("Requested By:");
        lblRequestedBy.setBounds(70, 215, 160, 30);
        pnlForm.add(lblRequestedBy);
        
        JTextField txtRequestedBy = createFormTextField();
        txtRequestedBy.setBounds(230, 215, 600, 30);
        pnlForm.add(txtRequestedBy);
        
        JLabel lblNotes = createFormLabel("Clinical Notes:");
        lblNotes.setBounds(70, 265, 200, 30);
        pnlForm.add(lblNotes);
        
        JTextArea txaNotes = new JTextArea("Write notes here...");
        txaNotes.setFont(FontsTheme.Info_Texts);
        txaNotes.setForeground(ColorsTheme.Text_Gray);
        txaNotes.setLineWrap(true);
        txaNotes.setWrapStyleWord(true);
        
        JScrollPane scrollNotes = new JScrollPane(txaNotes);
        scrollNotes.setBounds(230, 265, 600, 70);
        pnlForm.add(scrollNotes);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(635, 495, 150, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        JButton btnSave = new JButton("Save Lab Order");
        btnSave.setBounds(800, 495, 200, 40);
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

    private JLabel createIconLabel(String path) {
        java.net.URL resource = getClass().getResource(path);
        ImageIcon icon = resource == null
                ? new ImageIcon("src/main/resources" + path)
                : new ImageIcon(resource);
        Image scaledImage = icon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }
}
