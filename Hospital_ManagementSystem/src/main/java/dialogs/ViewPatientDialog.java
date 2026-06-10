/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ViewPatientDialog extends JDialog implements ActionListener{
    private JPanel pnlContent;
    private JLabel lblTitle, lblSubtitle, lblBirth, lblID, lblAge, lblNumber, lblGender, lblStatus, lblEmail, lblAddress, 
            lblRoom, lblMarital, lblFirst, lblLast;
    private JTextField txtID, txtAge, txtNumber, txtEmail, txtAddress, txtBirth, txtFirst, txtLast;
    private JButton btnPersonal, btnAddInfo, btnCancel;
    private JComboBox<String> cmbStatus, cmbGender, cmbMarital, cmbRoom;
    
    private static final String[] gender = {" ", "Male", "Female", "Prefer not to say"};
    private static final String[] status = {" ", "Admitted", "Discharged", "Observation"};
    private static final String[] room = {" ", "ER-01", "ER-02", "LAB-01", "LAB-02", "RM-201", "RM-202", "XRAY-01", "ICU-01", "ICU-02", "OR-01"};
    private static final String[] marital = {" ", "Single", "Married", "Divorced", "Widowed"};

    
    private String currentPatientId;
   
    public ViewPatientDialog(String patientId) {
        this.currentPatientId = patientId.replaceAll("(?i)[A-Z]+-", "");
        
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Patient Information");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Complete all the required fields to add a record.");
        lblSubtitle.setBounds(30, 40, 500, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnPersonal = new JButton("Personal Information");
        btnPersonal.setBounds(40, 100, 250, 40);
        btnPersonal.setFont(FontsTheme.Buttons);
        btnPersonal.setForeground(ColorsTheme.Text_White);
        btnPersonal.setBackground(ColorsTheme.Header);
        btnPersonal.setFocusPainted(false);
        add(btnPersonal);
        
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
        
        btnAddInfo = new JButton("Save Information");
        btnAddInfo.setBounds(790, 450, 200, 30);
        btnAddInfo.setFont(FontsTheme.Buttons);
        btnAddInfo.setForeground(ColorsTheme.Text_White);
        btnAddInfo.setBackground(ColorsTheme.Green);
        btnAddInfo.setFocusPainted(false);
        add(btnAddInfo);
        
        
       
        // Personal Information Form
        lblID = new JLabel("Patient ID : "); 
        lblID.setBounds(40, 40, 200, 30); 
        lblID.setFont(FontsTheme.Plain_Texts); 
        lblID.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblID);
        
        txtID = new JTextField(""); 
        txtID.setBounds(220, 40, 230, 30); 
        txtID.setFont(FontsTheme.Plain_Texts); 
        txtID.setForeground(ColorsTheme.Text_Black);
        txtID.setEditable(false);
        pnlContent.add(txtID);
        
        lblFirst = new JLabel("First Name : "); 
        lblFirst.setBounds(40, 80, 200, 30); 
        lblFirst.setFont(FontsTheme.Plain_Texts); 
        lblFirst.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblFirst);
        
        txtFirst = new JTextField(""); 
        txtFirst.setBounds(220, 80, 230, 30); 
        txtFirst.setFont(FontsTheme.Plain_Texts); 
        txtFirst.setForeground(ColorsTheme.Text_Black);
        txtFirst.setEditable(false);
        pnlContent.add(txtFirst);
        
        lblLast = new JLabel("Last Name : "); 
        lblLast.setBounds(40, 120, 200, 30); 
        lblLast.setFont(FontsTheme.Plain_Texts); 
        lblLast.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblLast);
        
        txtLast = new JTextField(""); 
        txtLast.setBounds(220, 120, 230, 30);
        txtLast.setFont(FontsTheme.Plain_Texts); 
        txtLast.setForeground(ColorsTheme.Text_Black);
        txtLast.setEditable(false);
        pnlContent.add(txtLast);
        
        lblAge = new JLabel("Age : "); 
        lblAge.setBounds(40, 160, 200, 30); 
        lblAge.setFont(FontsTheme.Plain_Texts); 
        lblAge.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblAge);
        
        txtAge = new JTextField(""); 
        txtAge.setBounds(220, 160, 230, 30);
        txtAge.setFont(FontsTheme.Plain_Texts); 
        txtAge.setForeground(ColorsTheme.Text_Black);
        txtAge.setEditable(false);
        pnlContent.add(txtAge);
        
        lblBirth = new JLabel("Birthday : "); 
        lblBirth.setBounds(40, 200, 200, 30); 
        lblBirth.setFont(FontsTheme.Plain_Texts); 
        lblBirth.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblBirth);
        
        txtBirth = new JTextField(""); 
        txtBirth.setBounds(220, 200, 230, 30);
        txtBirth.setFont(FontsTheme.Plain_Texts); 
        txtBirth.setForeground(ColorsTheme.Text_Black);
        txtBirth.setEditable(false);
        pnlContent.add(txtBirth);
        
        lblGender = new JLabel("Gender : "); 
        lblGender.setBounds(40, 240, 200, 30); 
        lblGender.setFont(FontsTheme.Plain_Texts); 
        lblGender.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblGender);
        
        cmbGender = new JComboBox<>(gender);
        cmbGender.setBounds(220, 240, 230, 30);
        cmbGender.setFont(FontsTheme.Plain_Texts); 
        cmbGender.setForeground(ColorsTheme.Text_Black); 
        cmbGender.setBackground(ColorsTheme.Main_Card);
        cmbGender.setEnabled(false);
        pnlContent.add(cmbGender);
        
        lblNumber = new JLabel("Contact Number : "); 
        lblNumber.setBounds(510, 40, 200, 30); 
        lblNumber.setFont(FontsTheme.Plain_Texts); 
        lblNumber.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblNumber);
        
        txtNumber = new JTextField(""); 
        txtNumber.setBounds(690, 40, 230, 30);
        txtNumber.setFont(FontsTheme.Plain_Texts); 
        txtNumber.setForeground(ColorsTheme.Text_Black);
        txtNumber.setEditable(false);
        pnlContent.add(txtNumber);
        
        lblAddress = new JLabel("Home Address : "); 
        lblAddress.setBounds(510, 80, 200, 30); 
        lblAddress.setFont(FontsTheme.Plain_Texts);
        lblAddress.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblAddress);
        
        txtAddress = new JTextField(""); 
        txtAddress.setBounds(690, 80, 230, 30); 
        txtAddress.setFont(FontsTheme.Plain_Texts); 
        txtAddress.setForeground(ColorsTheme.Text_Black);
        txtAddress.setEditable(false);
        pnlContent.add(txtAddress);
        
        lblEmail = new JLabel("Email Address : "); 
        lblEmail.setBounds(510, 120, 200, 30); 
        lblEmail.setFont(FontsTheme.Plain_Texts); 
        lblEmail.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblEmail);
        
        txtEmail = new JTextField(""); 
        txtEmail.setBounds(690, 120, 230, 30); 
        txtEmail.setFont(FontsTheme.Plain_Texts); 
        txtEmail.setForeground(ColorsTheme.Text_Black);
        txtEmail.setEditable(false);
        pnlContent.add(txtEmail);
        
        lblMarital = new JLabel("Marital Status : "); 
        lblMarital.setBounds(510, 160, 200, 30); 
        lblMarital.setFont(FontsTheme.Plain_Texts); 
        lblMarital.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblMarital);
        
        cmbMarital = new JComboBox<>(marital);
        cmbMarital.setBounds(690, 160, 230, 30);
        cmbMarital.setFont(FontsTheme.Plain_Texts); 
        cmbMarital.setForeground(ColorsTheme.Text_Black); 
        cmbMarital.setBackground(ColorsTheme.Text_White);
        cmbMarital.setEnabled(false);
        pnlContent.add(cmbMarital);
        
        lblStatus = new JLabel("Patient Status : "); 
        lblStatus.setBounds(510, 200, 200, 30); 
        lblStatus.setFont(FontsTheme.Plain_Texts); 
        lblStatus.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblStatus);
        
        cmbStatus = new JComboBox<>(status);
        cmbStatus.setBounds(690, 200, 230, 30);
        cmbStatus.setFont(FontsTheme.Plain_Texts); 
        cmbStatus.setForeground(ColorsTheme.Text_Black); 
        cmbStatus.setBackground(ColorsTheme.Text_White);
        cmbStatus.setEnabled(false);
        pnlContent.add(cmbStatus);
        
        lblRoom = new JLabel("Room Number : "); 
        lblRoom.setBounds(510, 240, 200, 30); 
        lblRoom.setFont(FontsTheme.Plain_Texts); 
        lblRoom.setForeground(ColorsTheme.Text_Black); 
        pnlContent.add(lblRoom);
        
        cmbRoom = new JComboBox<>(room);
        cmbRoom.setBounds(690, 240, 230, 30);
        cmbRoom.setFont(FontsTheme.Plain_Texts); 
        cmbRoom.setForeground(ColorsTheme.Text_Black); 
        cmbRoom.setBackground(ColorsTheme.Text_White);
        cmbRoom.setEnabled(false);
        pnlContent.add(cmbRoom);

        
        // ActionListener
        btnPersonal.addActionListener(this);
        btnCancel.addActionListener(this);
        //btnAddInfo.addActionListener(this);
        
        loadPatientData();

    }
    
    private void loadPatientData() {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, currentPatientId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                txtID.setText(String.format("PAT-%03d", rs.getInt("patient_id")));
                txtFirst.setText(rs.getString("first_name"));
                txtLast.setText(rs.getString("last_name"));
                txtAge.setText(rs.getString("age"));
                txtBirth.setText(rs.getString("birthday"));
                
                String gen = rs.getString("gender");
                if (gen != null) cmbGender.setSelectedItem(gen);
                
                txtNumber.setText(rs.getString("contact_number"));
                txtAddress.setText(rs.getString("address"));
                 txtEmail.setText(rs.getString("email"));
                
                String maritalStats = rs.getString("marital_status");
                if (maritalStats != null) cmbMarital.setSelectedItem(maritalStats);
                
                
                String stats = rs.getString("status");
                if (stats != null) cmbStatus.setSelectedItem(stats);
                
                String rooms = rs.getString("room_number");
                if (rooms != null) cmbRoom.setSelectedItem(rooms);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patients data:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
}
        public void showPatientInfo() {
        pnlContent.removeAll();
        pnlContent.add(lblID);
        pnlContent.add(txtID);
        pnlContent.add(lblFirst);
        pnlContent.add(txtFirst);
        pnlContent.add(lblAge);
        pnlContent.add(txtAge);
        pnlContent.add(lblBirth);
        pnlContent.add(txtBirth);
        pnlContent.add(lblGender);
        pnlContent.add(cmbGender);
        pnlContent.add(lblNumber);
        pnlContent.add(txtNumber);
        pnlContent.add(lblAddress);
        pnlContent.add(txtAddress);
        pnlContent.add(lblEmail);
        pnlContent.add(txtEmail);
        pnlContent.add(lblMarital);
        pnlContent.add(cmbMarital);
        pnlContent.add(lblStatus);
        pnlContent.add(cmbStatus);
        pnlContent.add(lblRoom);
        pnlContent.add(cmbRoom);
        pnlContent.repaint();
        pnlContent.revalidate();

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == btnPersonal) {
            showPatientInfo();
        } else if (e.getSource() == btnCancel) {
            dispose();
        
        }
        }
}
