/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import com.mycompany.hospitalmanage.ColorsTheme;
import com.mycompany.hospitalmanage.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author roder
 */
public class emergencyDialog extends JDialog {
    
        JLabel lblType, lblName, lblLocation, lblStatus, lblETA;
        JTextField txtType, txtName, txtLocation;
        JComboBox<String> cmbStatus, cmbETA;
        JButton btnSave, btnCancel;
    
        public emergencyDialog (){
            
        setTitle("Emergency Dispatch");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setModal(true);
        
        lblType = new JLabel("Type:");
        lblType.setBounds(40, 40, 120, 30);
        lblType.setFont(FontsTheme.Plain_Texts);
        add(lblType);

        lblName = new JLabel("Patient Name:");
        lblName.setBounds(40, 100, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        add(lblName);

        lblLocation = new JLabel("Location:");
        lblLocation.setBounds(40, 160, 120, 30);
        lblLocation.setFont(FontsTheme.Plain_Texts);
        add(lblLocation);

        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(40, 220, 120, 30);
        lblStatus.setFont(FontsTheme.Plain_Texts);
        add(lblStatus);

        lblETA = new JLabel("ETA:");
        lblETA.setBounds(40, 280, 120, 30);
        lblETA.setFont(FontsTheme.Plain_Texts);
        add(lblETA);

      
        txtType = new JTextField();
        txtType.setBounds(180, 40, 240, 35);
        add(txtType);

        txtName = new JTextField();
        txtName.setBounds(180, 100, 240, 35);
        add(txtName);

        txtLocation = new JTextField();
        txtLocation.setBounds(180, 160, 240, 35);
        add(txtLocation);

       
        String[] status = {"Available", "Dispatched", "Returning" , "Active Cases"};
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(180, 220, 240, 35);
        add(cmbStatus);

        String[] ETA = {"5 minutes", "10 minutes", "15 minutes", "20 minutes"};
        cmbETA = new JComboBox<>(ETA);
        cmbETA.setBounds(180, 280, 240, 35);
        add(cmbETA);

       
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
                        "Emergency Dispatch Added Successfully!"
                );

                dispose();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
}
