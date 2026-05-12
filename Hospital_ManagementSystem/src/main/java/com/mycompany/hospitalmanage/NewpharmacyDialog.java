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
public class NewpharmacyDialog extends JDialog {
    
    NewpharmacyDialog() {
        
        
        setLayout(null);
        setSize(850, 570);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        JLabel lblDialogTitle = new JLabel("Add Medication");
        lblDialogTitle.setBounds(40, 25, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        JLabel lblDialogDetails = new JLabel("Add a new medication to the inventory");
        lblDialogDetails.setBounds(40, 60, 450, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        JLabel lblMedication = createFormLabel("Medication:");
        lblMedication.setBounds(70, 120, 170, 30);
        add(lblMedication);

        JTextField txtMedication = createFormTextField();
        txtMedication.setBounds(250, 120, 420, 30);
        add(txtMedication);

        JLabel lblItemCode = createFormLabel("Item Code:");
        lblItemCode.setBounds(70, 165, 170, 30);
        add(lblItemCode);

        JTextField txtItemCode = createFormTextField();
        txtItemCode.setBounds(250, 165, 200, 30);
        add(txtItemCode);

        JLabel lblCategory = createFormLabel("Category:");
        lblCategory.setBounds(470, 165, 120, 30);
        add(lblCategory);

        JComboBox<String> cmbCategory = new JComboBox<>(new String[]{
            "Pain Reliever",
            "Antibiotic",
            "Respiratory",
            "Antihistamine",
            "Diabetes",
            "Hypertension",
            "Gastrointestinal"
        });
        cmbCategory.setBounds(590, 165, 180, 30);
        cmbCategory.setFont(FontsTheme.Info_Texts);
        add(cmbCategory);

        JLabel lblStock = createFormLabel("Stock:");
        lblStock.setBounds(70, 210, 170, 30);
        add(lblStock);

        JTextField txtStock = createFormTextField();
        txtStock.setBounds(250, 210, 200, 30);
        add(txtStock);

        JLabel lblReorder = createFormLabel("Reorder Level:");
        lblReorder.setBounds(470, 210, 150, 30);
        add(lblReorder);

        JTextField txtReorder = createFormTextField();
        txtReorder.setBounds(620, 210, 150, 30);
        add(txtReorder);

        JLabel lblExpiry = createFormLabel("Expiry Date:");
        lblExpiry.setBounds(70, 255, 170, 30);
        add(lblExpiry);

        JTextField txtExpiry = createFormTextField();
        txtExpiry.setBounds(250, 255, 200, 30);
        add(txtExpiry);

        JLabel lblStatus = createFormLabel("Status:");
        lblStatus.setBounds(470, 255, 120, 30);
        add(lblStatus);

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"In Stock", "Low Stock", "Critical"});
        cmbStatus.setBounds(590, 255, 180, 30);
        cmbStatus.setFont(FontsTheme.Info_Texts);
        add(cmbStatus);

        JLabel lblSupplier = createFormLabel("Supplier:");
        lblSupplier.setBounds(70, 300, 170, 30);
        add(lblSupplier);

        JTextField txtSupplier = createFormTextField();
        txtSupplier.setBounds(250, 300, 420, 30);
        add(txtSupplier);

        JLabel lblNotes = createFormLabel("Notes:");
        lblNotes.setBounds(70, 355, 170, 30);
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

        JButton btnSave = new JButton("Save Medication");
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
