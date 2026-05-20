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
 * 
 */
public class NewAppointmentDialog extends JDialog implements ActionListener {
    
    private JLabel lblTitle, lblSubtitle, lblDate, lblTime, lblID, lblName, lblDepart, lblDoc, lblVisit, lblRoom, lblNote;
    private JTextField txtDate, txtTime, txtID, txtName;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    private JComboBox<String> cmbDepart, cmbDoc, cmbVisit, cmbRoom;
    private JButton btnAppoint, btnCancel, btnConfirm;
    private JPanel pnlContent;
    
    
    
    public NewAppointmentDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Appointment");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Schedule and manage healthcare consultations and visits.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        
        btnAppoint = new JButton("New Appointment");
        btnAppoint.setBounds(40, 100, 250, 40);
        btnAppoint.setFont(FontsTheme.Buttons);
        btnAppoint.setForeground(ColorsTheme.Text_White);
        btnAppoint.setBackground(ColorsTheme.Header);
        btnAppoint.setFocusPainted(false);
        add(btnAppoint);
       
        
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
        
        btnConfirm = new JButton("Confirm Appointment");
        btnConfirm.setBounds(690, 450, 300, 30);
        btnConfirm.setFont(FontsTheme.Buttons);
        btnConfirm.setForeground(ColorsTheme.Text_White);
        btnConfirm.setBackground(ColorsTheme.Green);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);
        
        
        
        //ActionListener
        btnAppoint.addActionListener(this);
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        showNewAppointment();
        
        
    }
    
    
    public void showNewAppointment() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        
        
        lblDate = new JLabel("Appointment Date : ");
        lblDate.setBounds(40, 30, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(220, 30, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblTime = new JLabel("Appointment Time : ");
        lblTime.setBounds(40, 70, 200, 30);
        lblTime.setFont(FontsTheme.Plain_Texts);
        lblTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblTime);
        
        txtTime = new JTextField("");
        txtTime.setBounds(220, 70, 230, 30);
        txtTime.setFont(FontsTheme.Plain_Texts);
        txtTime.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtTime);
        
        lblID = new JLabel("Patient ID : ");
        lblID.setBounds(40, 110, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
        
        txtID = new JTextField("");
        txtID.setBounds(220, 110, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        lblName = new JLabel("Patient Name : ");
        lblName.setBounds(40, 150, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 150, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        
        // LEFT SIDE
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
        
        lblVisit = new JLabel("Visit Type : ");
        lblVisit.setBounds(510, 110, 200, 30);
        lblVisit.setFont(FontsTheme.Plain_Texts);
        lblVisit.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblVisit);
        
        cmbVisit = new JComboBox<>(new String[]{
        " ", "New Consultation", "Follow-up Visit", "Routine Check-up", "Emergency Visit", "Diagnostic/Lab Test"
        });
        cmbVisit.setBounds(690, 110, 230, 30);
        cmbVisit.setFont(FontsTheme.Plain_Texts);
        cmbVisit.setForeground(ColorsTheme.Text_Black);
        cmbVisit.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbVisit);
        
        lblRoom = new JLabel("Room Number : ");
        lblRoom.setBounds(510, 150, 200, 30);
        lblRoom.setFont(FontsTheme.Plain_Texts);
        lblRoom.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblRoom);
        
        cmbRoom = new JComboBox<>(new String[]{
        " ", "ER-01", "ER-02", "LAB-01", "LAB-02", "RM-201", "RM-202", "XRAY-01", "ICU-01", "ICU-02", "OR-01"
        });
        cmbRoom.setBounds(690, 150, 230, 30);
        cmbRoom.setFont(FontsTheme.Plain_Texts);
        cmbRoom.setForeground(ColorsTheme.Text_Black);
        cmbRoom.setBackground(ColorsTheme.Text_White);
        pnlContent.add(cmbRoom);
        
        
        lblNote = new JLabel("Note/Reason for Visit");
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
        if(e.getSource() == btnAppoint) {
            showNewAppointment();
        }
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnConfirm) {
            JOptionPane.showMessageDialog(this, "Appointment scheduled successfully!", 
                    "Appointment Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
