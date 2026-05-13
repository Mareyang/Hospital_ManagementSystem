package com.mycompany.main;

import com.mycompany.hospitalmanage.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BillDialog extends JDialog {

    JLabel lblPatientId, lblName, lblAmount, lblStatus, lblPayment;
    JTextField txtPatientId, txtName, txtAmount;
    JComboBox<String> cmbStatus, cmbPayment;
    JButton btnSave, btnCancel;

    public BillDialog() {

        setTitle("New Invoice");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setModal(true);

       
        lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setBounds(40, 40, 120, 30);
        lblPatientId.setFont(FontsTheme.Plain_Texts);
        add(lblPatientId);

        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 100, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        add(lblName);

        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(40, 160, 120, 30);
        lblAmount.setFont(FontsTheme.Plain_Texts);
        add(lblAmount);

        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(40, 220, 120, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        add(lblStatus);

        lblPayment = new JLabel("Payment:");
        lblPayment.setBounds(40, 280, 120, 30);
        lblPayment.setFont(FontsTheme.Plain_Texts);
        add(lblPayment);

      
        txtPatientId = new JTextField();
        txtPatientId.setBounds(180, 40, 240, 35);
        add(txtPatientId);

        txtName = new JTextField();
        txtName.setBounds(180, 100, 240, 35);
        add(txtName);

        txtAmount = new JTextField();
        txtAmount.setBounds(180, 160, 240, 35);
        add(txtAmount);

       
        String[] status = {"Paid", "Pending", "Overdue"};
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(180, 220, 240, 35);
        add(cmbStatus);

        String[] payment = {"Cash", "GCash", "Card", "Insurance"};
        cmbPayment = new JComboBox<>(payment);
        cmbPayment.setBounds(180, 280, 240, 35);
        add(cmbPayment);

       
        btnSave = new JButton("Save");
        btnSave.setBounds(90, 370, 130, 40);
        btnSave.setBackground(ColorsTheme.Add_Confirm);
        btnSave.setForeground(ColorsTheme.Text_White);
        btnSave.setFont(FontsTheme.Buttons);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 370, 130, 40);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setFont(FontsTheme.Buttons);
        add(btnCancel);

     
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
}