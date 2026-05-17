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
 * @author eiros
 */
public class NewLabDialog extends JDialog implements ActionListener {
    
    
    private JLabel lblDialogTitle, lblDialogDetails, lblID, lblName, lblDate, lblDoc, lblDepart, lblType, lblStatus, lblTest, lblNote;
    private JTextField txtID, txtName, txtDate, txtTest;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JComboBox<String> cmbDepart, cmbDoc, cmbType, cmbStatus;
    private JButton btnPatientTestInfo, btnCancel, btnAddInfo;
    private JPanel pnlContent;
    
    
    
    public NewLabDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        

        lblDialogTitle = new JLabel("Patient Laboratory");
        lblDialogTitle.setBounds(30, 10, 500, 40);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);
        
        lblDialogDetails = new JLabel("Create a laboratory request for a patient.");
        lblDialogDetails.setBounds(30, 40, 500, 40);
        lblDialogDetails.setFont(FontsTheme.Plain_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        btnPatientTestInfo = new JButton("Lab Form");
        btnPatientTestInfo.setBounds(40, 100, 250, 40);
        btnPatientTestInfo.setFont(FontsTheme.Buttons);
        btnPatientTestInfo.setForeground(ColorsTheme.Text_White);
        btnPatientTestInfo.setBackground(ColorsTheme.Header);
        btnPatientTestInfo.setFocusPainted(false);
        add(btnPatientTestInfo);

        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnAddInfo = new JButton("Submit Lab Request");
        btnAddInfo.setBounds(690, 450, 300, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Green);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        

        
        //ActionListener
        btnPatientTestInfo.addActionListener(this);
        
        showTestInfo();

}
        
        
    public void showTestInfo() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
       
        lblID = new JLabel("Lab ID : ");
        lblID.setBounds(40, 30, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
        
        txtID = new JTextField("");
        txtID.setBounds(220, 30, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        lblName = new JLabel("Name : ");
        lblName.setBounds(40, 70, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 70, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        lblDate = new JLabel("Requested Date : ");
        lblDate.setBounds(40, 110, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(220, 110, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblType = new JLabel("Test Type : ");
        lblType.setBounds(40, 150, 300, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        lblType.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblType);
        
        cmbType = new JComboBox<>(new String[]{
        " ", "Hematology", "Urinalysis", "Immunology", "Clinical Chemistry ",
        });
        cmbType.setBounds(220, 150, 230, 30);
        cmbType.setFont(FontsTheme.Plain_Texts);
        cmbType.setForeground(ColorsTheme.Text_Black);
        cmbType.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbType);
        
        
        
        //Left
        lblDepart = new JLabel("Department : ");
        lblDepart.setBounds(510, 30, 200, 30);
        lblDepart.setFont(FontsTheme.Plain_Texts);
        lblDepart.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDepart);
        
        
        cmbDepart = new JComboBox<>(new String[]{
        " ", "Emergency(ER)", "Laboratory", "Cardiology", "Pediatrics ", "Surgery", "OB-GYN", "Radiology",
        });
        cmbDepart.setBounds(690, 30, 230, 30);
        cmbDepart.setFont(FontsTheme.Plain_Texts);
        cmbDepart.setForeground(ColorsTheme.Text_Black);
        cmbDepart.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbDepart);
        
       
        lblDoc = new JLabel("Physician/Doctor : ");
        lblDoc.setBounds(510, 70, 200, 30);
        lblDoc.setFont(FontsTheme.Plain_Texts);
        lblDoc.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDoc);
        
        cmbDoc = new JComboBox<>(new String[]{
        " ", "Dr. Juan dela Cruz", "Dr. Maria Santos", "Dr. Ricardo Reyes", "Dr. Elena Garcia", "Dr. Roberto Castro"
        });
        cmbDoc.setBounds(690, 70, 230, 30);
        cmbDoc.setFont(FontsTheme.Plain_Texts);
        cmbDoc.setForeground(ColorsTheme.Text_Black);
        cmbDoc.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbDoc);
        
        lblTest = new JLabel("Specific Test : ");
        lblTest.setBounds(510, 110, 200, 30);
        lblTest.setFont(FontsTheme.Plain_Texts);
        lblTest.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTest);
        
        
        txtTest = new JTextField("");
        txtTest.setBounds(690, 110, 230, 30);
        txtTest.setFont(FontsTheme.Plain_Texts);
        txtTest.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtTest);
        
       
        lblStatus = new JLabel("Collection Status : ");
        lblStatus.setBounds(510, 150, 300, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        lblStatus.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblStatus);
        
        cmbStatus = new JComboBox<>(new String[]{
        " ", "Routine", "STAT/Emergency", "Fasting REquired", 
        });
        cmbStatus.setBounds(690, 150, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts);
        cmbStatus.setForeground(ColorsTheme.Text_Black);
        cmbStatus.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbStatus);
        
        
        lblNote = new JLabel("Clinical Remarks");
        lblNote.setBounds(50, 200, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
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
        scrollNote.setBounds(40, 230, 880, 50);
        pnlContent.setLayout(null);
        pnlContent.add(scrollNote);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnPatientTestInfo);
            showTestInfo();
        

    }
    
}
        

//    private JLabel createIconLabel(String path) {
//        java.net.URL resource = getClass().getResource(path);
//        ImageIcon icon;
//        
//        if (resource != null) {
//            icon = new ImageIcon(resource);
//        } else {
//            icon = new ImageIcon("src/main/resources" + path);
//        }
//        
//        Image image = icon.getImage();
//        Image scaledImage = image.getScaledInstance(56, 56, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//        JLabel label = new JLabel(scaledIcon);
//        
//        return label;
    

