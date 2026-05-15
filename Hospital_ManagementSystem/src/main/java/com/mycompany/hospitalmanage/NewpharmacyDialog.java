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
public class NewpharmacyDialog extends JDialog {
    
    NewpharmacyDialog() {
        
        
        setLayout(null);
        setSize(1050, 585);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);

        JLabel lblDialogIcon = createIconLabel("/icons/pharmacy.png");
        lblDialogIcon.setBounds(40, 25, 56, 56);
        add(lblDialogIcon);

        JLabel lblDialogTitle = new JLabel("Add Medication");
        lblDialogTitle.setBounds(110, 25, 300, 35);
        lblDialogTitle.setFont(FontsTheme.Bold_Texts);
        lblDialogTitle.setForeground(ColorsTheme.Text_Black);
        add(lblDialogTitle);

        JLabel lblDialogDetails = new JLabel("Add a new medication to the inventory");
        lblDialogDetails.setBounds(110, 60, 450, 30);
        lblDialogDetails.setFont(FontsTheme.Info_Texts);
        lblDialogDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDialogDetails);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(null);
        pnlForm.setBounds(50, 115, 950, 365);
        pnlForm.setBackground(ColorsTheme.Main_Card);
        add(pnlForm);

        JLabel lblMedication = createFormLabel("Medication:");
        lblMedication.setBounds(70, 35, 170, 30);
        pnlForm.add(lblMedication);

        JTextField txtMedication = createFormTextField();
        txtMedication.setBounds(250, 35, 580, 30);
        pnlForm.add(txtMedication);

        JLabel lblItemCode = createFormLabel("Item Code:");
        lblItemCode.setBounds(70, 80, 170, 30);
        pnlForm.add(lblItemCode);

        JTextField txtItemCode = createFormTextField();
        txtItemCode.setBounds(250, 80, 220, 30);
        pnlForm.add(txtItemCode);

        JLabel lblCategory = createFormLabel("Category:");
        lblCategory.setBounds(510, 80, 120, 30);
        pnlForm.add(lblCategory);

        JComboBox<String> cmbCategory = new JComboBox<>(new String[]{
            "Pain Reliever",
            "Antibiotic",
            "Respiratory",
            "Antihistamine",
            "Diabetes",
            "Hypertension",
            "Gastrointestinal"
        });
        cmbCategory.setBounds(630, 80, 200, 30);
        cmbCategory.setFont(FontsTheme.Info_Texts);
        pnlForm.add(cmbCategory);

        JLabel lblStock = createFormLabel("Stock:");
        lblStock.setBounds(70, 125, 170, 30);
        pnlForm.add(lblStock);

        JTextField txtStock = createFormTextField();
        txtStock.setBounds(250, 125, 220, 30);
        pnlForm.add(txtStock);

        JLabel lblReorder = createFormLabel("Reorder Level:");
        lblReorder.setBounds(510, 125, 150, 30);
        pnlForm.add(lblReorder);

        JTextField txtReorder = createFormTextField();
        txtReorder.setBounds(670, 125, 160, 30);
        pnlForm.add(txtReorder);

        JLabel lblExpiry = createFormLabel("Expiry Date:");
        lblExpiry.setBounds(70, 170, 170, 30);
        pnlForm.add(lblExpiry);

        JTextField txtExpiry = createFormTextField();
        txtExpiry.setBounds(250, 170, 220, 30);
        pnlForm.add(txtExpiry);

        JLabel lblStatus = createFormLabel("Status:");
        lblStatus.setBounds(510, 170, 120, 30);
        pnlForm.add(lblStatus);

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"In Stock", "Low Stock", "Critical"});
        cmbStatus.setBounds(630, 170, 200, 30);
        cmbStatus.setFont(FontsTheme.Info_Texts);
        pnlForm.add(cmbStatus);

        JLabel lblSupplier = createFormLabel("Supplier:");
        lblSupplier.setBounds(70, 215, 170, 30);
        pnlForm.add(lblSupplier);

        JTextField txtSupplier = createFormTextField();
        txtSupplier.setBounds(250, 215, 580, 30);
        pnlForm.add(txtSupplier);

        JLabel lblNotes = createFormLabel("Notes:");
        lblNotes.setBounds(70, 265, 170, 30);
        pnlForm.add(lblNotes);

        JTextArea txaNotes = new JTextArea("Write notes here...");
        txaNotes.setFont(FontsTheme.Info_Texts);
        txaNotes.setForeground(ColorsTheme.Text_Gray);
        txaNotes.setLineWrap(true);
        txaNotes.setWrapStyleWord(true);

        JScrollPane scrollNotes = new JScrollPane(txaNotes);
        scrollNotes.setBounds(250, 265, 580, 70);
        pnlForm.add(scrollNotes);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(635, 495, 150, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Text_Gray);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);

        JButton btnSave = new JButton("Save Medication");
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
