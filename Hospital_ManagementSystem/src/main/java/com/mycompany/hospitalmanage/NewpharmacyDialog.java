/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author eiros
 */
public class NewpharmacyDialog extends JDialog implements ActionListener {
    
    private JButton btnMedicationInfo, btnStockDetails;
    private JPanel pnlMedicationInfo, pnlStockDetails;
    
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

        btnMedicationInfo = createTabButton("Medication Info");
        btnMedicationInfo.setBounds(50, 115, 250, 40);
        btnMedicationInfo.addActionListener(this);
        add(btnMedicationInfo);

        btnStockDetails = createTabButton("Stock Details");
        btnStockDetails.setBounds(300, 115, 250, 40);
        btnStockDetails.addActionListener(this);
        add(btnStockDetails);

        pnlMedicationInfo = createFormPanel();
        add(pnlMedicationInfo);

        pnlStockDetails = createFormPanel();
        pnlStockDetails.setVisible(false);
        add(pnlStockDetails);

        JLabel lblMedication = createFormLabel("Medication:");
        lblMedication.setBounds(70, 35, 170, 30);
        pnlMedicationInfo.add(lblMedication);

        JTextField txtMedication = createFormTextField();
        txtMedication.setBounds(250, 35, 580, 30);
        pnlMedicationInfo.add(txtMedication);

        JLabel lblItemCode = createFormLabel("Item Code:");
        lblItemCode.setBounds(70, 80, 170, 30);
        pnlMedicationInfo.add(lblItemCode);

        JTextField txtItemCode = createFormTextField();
        txtItemCode.setBounds(250, 80, 220, 30);
        pnlMedicationInfo.add(txtItemCode);

        JLabel lblCategory = createFormLabel("Category:");
        lblCategory.setBounds(510, 80, 120, 30);
        pnlMedicationInfo.add(lblCategory);

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
        pnlMedicationInfo.add(cmbCategory);

        JLabel lblStock = createFormLabel("Stock:");
        lblStock.setBounds(70, 35, 170, 30);
        pnlStockDetails.add(lblStock);

        JTextField txtStock = createFormTextField();
        txtStock.setBounds(250, 35, 220, 30);
        pnlStockDetails.add(txtStock);

        JLabel lblReorder = createFormLabel("Reorder Level:");
        lblReorder.setBounds(510, 35, 150, 30);
        pnlStockDetails.add(lblReorder);

        JTextField txtReorder = createFormTextField();
        txtReorder.setBounds(670, 35, 160, 30);
        pnlStockDetails.add(txtReorder);

        JLabel lblExpiry = createFormLabel("Expiry Date:");
        lblExpiry.setBounds(70, 80, 170, 30);
        pnlStockDetails.add(lblExpiry);

        JTextField txtExpiry = createFormTextField();
        txtExpiry.setBounds(250, 80, 220, 30);
        pnlStockDetails.add(txtExpiry);

        JLabel lblStatus = createFormLabel("Status:");
        lblStatus.setBounds(510, 80, 120, 30);
        pnlStockDetails.add(lblStatus);

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"In Stock", "Low Stock", "Critical"});
        cmbStatus.setBounds(630, 80, 200, 30);
        cmbStatus.setFont(FontsTheme.Info_Texts);
        pnlStockDetails.add(cmbStatus);

        JLabel lblSupplier = createFormLabel("Supplier:");
        lblSupplier.setBounds(70, 125, 170, 30);
        pnlStockDetails.add(lblSupplier);

        JTextField txtSupplier = createFormTextField();
        txtSupplier.setBounds(250, 125, 580, 30);
        pnlStockDetails.add(txtSupplier);

        JLabel lblNotes = createFormLabel("Notes:");
        lblNotes.setBounds(70, 180, 170, 30);
        pnlStockDetails.add(lblNotes);

        JTextArea txaNotes = new JTextArea("Write notes here...");
        txaNotes.setFont(FontsTheme.Info_Texts);
        txaNotes.setForeground(ColorsTheme.Text_Gray);
        txaNotes.setLineWrap(true);
        txaNotes.setWrapStyleWord(true);

        JScrollPane scrollNotes = new JScrollPane(txaNotes);
        scrollNotes.setBounds(250, 180, 580, 90);
        pnlStockDetails.add(scrollNotes);

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
        if (e.getSource() == btnMedicationInfo) {
            pnlMedicationInfo.setVisible(true);
            pnlStockDetails.setVisible(false);
        } else if (e.getSource() == btnStockDetails) {
            pnlMedicationInfo.setVisible(false);
            pnlStockDetails.setVisible(true);
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
