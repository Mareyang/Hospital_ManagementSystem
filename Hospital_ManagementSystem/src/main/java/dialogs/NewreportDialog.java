/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author eiros
 */
public class NewReportDialog extends JDialog implements ActionListener {
    
    private JLabel lblTitle, lblSubtitle, lblCateg, lblName, lblNote, lblBy, lblDate, lblID, lblPeriod; 
    private JButton btnReportDetails, btnWrite, btnCancel, btnConfirm;
    private JPanel pnlContent;
    private JTextField txtName, txtBy, txtDate, txtID, txtPeriod;
    private JComboBox<String> cmbCateg;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    
    
    
    public NewReportDialog() {
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Report Generator");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Select parameters and date ranges to compile your data.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        
        btnReportDetails = new JButton("Report Details");
        btnReportDetails.setBounds(40, 100, 250, 40);
        btnReportDetails.setFont(FontsTheme.Buttons);
        btnReportDetails.setForeground(ColorsTheme.Text_White);
        btnReportDetails.setBackground(ColorsTheme.Header);
        btnReportDetails.setFocusPainted(false);
        add(btnReportDetails);
       
        btnWrite = new JButton("Write Report");
        btnWrite.setBounds(290, 100, 250, 40);
        btnWrite.setFont(FontsTheme.Buttons);
        btnWrite.setForeground(ColorsTheme.Text_White);
        btnWrite.setBackground(ColorsTheme.Header);
        btnWrite.setFocusPainted(false);
        add(btnWrite);
        
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnConfirm = new JButton("Save Report");
        btnConfirm.setBounds(790, 450, 200, 30);
        btnConfirm.setFont(FontsTheme.Buttons);
        btnConfirm.setForeground(ColorsTheme.Text_White);
        btnConfirm.setBackground(ColorsTheme.Green);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);
        
        
        
        //ActionListener
        btnReportDetails.addActionListener(this);
        btnWrite.addActionListener(this);
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        
        
        showGenerateReport();
        
        
    }
    
    
    public void showGenerateReport() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
        
        
        lblName = new JLabel("Report Title : ");
        lblName.setBounds(40, 60, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblName);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 60, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtName);
        
        
        lblID = new JLabel("Report ID : ");
        lblID.setBounds(40, 130, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblID);
        
        txtID = new JTextField("");
        txtID.setBounds(220, 130, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtID);
        
        
        
        lblBy = new JLabel("Prepared By : ");
        lblBy.setBounds(40, 200, 200, 30);
        lblBy.setFont(FontsTheme.Plain_Texts);
        lblBy.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblBy);
        
        txtBy = new JTextField("");
        txtBy.setBounds(220, 200, 230, 30);
        txtBy.setFont(FontsTheme.Plain_Texts);
        txtBy.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtBy);
        
        
        
        
        //Left
        lblDate = new JLabel("Date of Report  : ");
        lblDate.setBounds(510, 60, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblDate);
        
        txtDate = new JTextField("");
        txtDate.setBounds(690, 60, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtDate);
        
        lblPeriod = new JLabel("Reporting Period : ");
        lblPeriod.setBounds(510, 130, 200, 30);
        lblPeriod.setFont(FontsTheme.Plain_Texts);
        lblPeriod.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblPeriod);
        
        txtPeriod = new JTextField("");
        txtPeriod.setBounds(690, 130, 230, 30);
        txtPeriod.setFont(FontsTheme.Plain_Texts);
        txtPeriod.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(txtPeriod);
        
        
        lblCateg = new JLabel("Report Scope : ");
        lblCateg.setBounds(510, 200, 200, 30);
        lblCateg.setFont(FontsTheme.Plain_Texts);
        lblCateg.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblCateg);
        
        
        cmbCateg = new JComboBox<>(new String[]{
        " ", "Admissions Summary", "Billing and Revenue", "Pharmacy Dispensation", "Emergency Logs ",
        });
        cmbCateg.setBounds(690, 200, 230, 30);
        cmbCateg.setFont(FontsTheme.Plain_Texts);
        cmbCateg.setForeground(ColorsTheme.Text_Black);
        cmbCateg.setBackground(ColorsTheme.Main_Card);
        pnlContent.add(cmbCateg);
        
        
        
        
    }
    
    public void showWriteReport() {
        pnlContent.removeAll();
        pnlContent.repaint();
        pnlContent.revalidate();
            
        lblNote = new JLabel("Executive Summary: ");
        lblNote.setBounds(50, 10, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        pnlContent.add(lblNote);
        
        txaNote = new JTextArea(" ");
        txaNote.setText("Write reports here...");
        txaNote.setEditable(true);
        txaNote.setFont(FontsTheme.Info_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 50, 880, 230);
        pnlContent.setLayout(null);
        pnlContent.add(scrollNote);
        
        
        
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReportDetails) {
            showGenerateReport();
    }
        else if(e.getSource() == btnWrite) {
            showWriteReport();
    }
        else if (e.getSource() == btnCancel) {
            dispose();
        } 
        
        else if (e.getSource() == btnConfirm) {
            JOptionPane.showMessageDialog(this, "Report generated successfully!", 
                    "Report Success", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}

