/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import java.awt.Dialog;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import panels.ColorsTheme;
import panels.FontsTheme;
/**
 *
 * @author eiros
 */
public class NewlabDialog extends JDialog implements ActionListener {
    
    private JButton btnPatientTestInfo, btnClinicalNotes;
    private JPanel pnlPatientTestInfo, pnlClinicalNotes;
    
        public NewlabDialog() {
        
        
        setLayout(null);
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        
        JLabel lbllabIcon = createIconLabel("/icons/lab.png");
        lbllabIcon.setBounds(30, 25, 72, 72);
        add(lbllabIcon);

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

        btnPatientTestInfo = createTabButton("Patient/Test Info");
        btnPatientTestInfo.setBounds(50, 115, 250, 40);
        btnPatientTestInfo.addActionListener(this);
        add(btnPatientTestInfo);

        btnClinicalNotes = createTabButton("Clinical Notes");
        btnClinicalNotes.setBounds(300, 115, 250, 40);
        btnClinicalNotes.addActionListener(this);
        add(btnClinicalNotes);

        pnlPatientTestInfo = createFormPanel();
        add(pnlPatientTestInfo);

        pnlClinicalNotes = createFormPanel();
        pnlClinicalNotes.setVisible(false);
        add(pnlClinicalNotes);
        
        JLabel lblPatientID = createFormLabel("Patient ID:");
        lblPatientID.setBounds(70, 35, 160, 30);
        pnlPatientTestInfo.add(lblPatientID);
        
        JTextField txtPatientID = createFormTextField();
        txtPatientID.setBounds(230, 35, 300, 30);
        pnlPatientTestInfo.add(txtPatientID);
        
        JButton btnFindPatient = new JButton("Find Patient");
        btnFindPatient.setBounds(550, 35, 170, 30);
        btnFindPatient.setFont(FontsTheme.Info_Texts);
        btnFindPatient.setBackground(ColorsTheme.Search_Button);
        btnFindPatient.setForeground(ColorsTheme.Text_White);
        pnlPatientTestInfo.add(btnFindPatient);
        
        JLabel lblPatientName = createFormLabel("Patient Name:");
        lblPatientName.setBounds(70, 80, 160, 30);
        pnlPatientTestInfo.add(lblPatientName);
        
        JTextField txtPatientName = createFormTextField();
        txtPatientName.setBounds(230, 80, 600, 30);
        pnlPatientTestInfo.add(txtPatientName);
        
        JLabel lblTestType = createFormLabel("Test Type:");
        lblTestType.setBounds(70, 125, 160, 30);
        pnlPatientTestInfo.add(lblTestType);
        
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
        pnlPatientTestInfo.add(cmbTestType);
        
        JLabel lblPriority = createFormLabel("Priority:");
        lblPriority.setBounds(70, 170, 160, 30);
        pnlPatientTestInfo.add(lblPriority);
        
        JComboBox<String> cmbPriority = new JComboBox<>(new String[]{"Routine", "STAT"});
        cmbPriority.setBounds(230, 170, 220, 30);
        cmbPriority.setFont(FontsTheme.Info_Texts);
        pnlPatientTestInfo.add(cmbPriority);
        
        JLabel lblRequestedDate = createFormLabel("Requested Date:");
        lblRequestedDate.setBounds(510, 170, 170, 30);
        pnlPatientTestInfo.add(lblRequestedDate);
        
        JTextField txtRequestedDate = createFormTextField();
        txtRequestedDate.setText("MM/DD/YYYY");
        txtRequestedDate.setBounds(680, 170, 150, 30);
        pnlPatientTestInfo.add(txtRequestedDate);
        
        JLabel lblRequestedBy = createFormLabel("Requested By:");
        lblRequestedBy.setBounds(70, 215, 160, 30);
        pnlPatientTestInfo.add(lblRequestedBy);
        
        JTextField txtRequestedBy = createFormTextField();
        txtRequestedBy.setBounds(230, 215, 600, 30);
        pnlPatientTestInfo.add(txtRequestedBy);
        
        JLabel lblNotes = createFormLabel("Clinical Notes:");
        lblNotes.setBounds(70, 45, 200, 30);
        pnlClinicalNotes.add(lblNotes);
        
        JTextArea txaNotes = new JTextArea("Write notes here...");
        txaNotes.setFont(FontsTheme.Info_Texts);
        txaNotes.setForeground(ColorsTheme.Text_Gray);
        txaNotes.setLineWrap(true);
        txaNotes.setWrapStyleWord(true);
        
        JScrollPane scrollNotes = new JScrollPane(txaNotes);
        scrollNotes.setBounds(230, 45, 600, 210);
        pnlClinicalNotes.add(scrollNotes);

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

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 155, 950, 325);
        panel.setBackground(ColorsTheme.Main_Card);
        return panel;
    }

    private JButton createTabButton(String text) {
        JButton button = new JButton(text);
        button.setFont(FontsTheme.Buttons);
        button.setBackground(ColorsTheme.Search_Button);
        button.setForeground(ColorsTheme.Text_White);
        button.setFocusPainted(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPatientTestInfo) {
            pnlPatientTestInfo.setVisible(true);
            pnlClinicalNotes.setVisible(false);
        } else if (e.getSource() == btnClinicalNotes) {
            pnlPatientTestInfo.setVisible(false);
            pnlClinicalNotes.setVisible(true);
        }
        
        revalidate();
        repaint();
    }

    private JLabel createIconLabel(String path) {
        java.net.URL resource = getClass().getResource(path);
        ImageIcon icon;
        
        if (resource != null) {
            icon = new ImageIcon(resource);
        } else {
            icon = new ImageIcon("src/main/resources" + path);
        }
        
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(56, 56, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        
        return label;
    }
}
