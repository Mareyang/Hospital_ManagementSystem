/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

import com.mycompany.hospitalmanage.ColorsTheme;
import com.mycompany.hospitalmanage.FontsTheme;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class StaffManagementPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlOn, pnlOff, pnlLeave, cardPanel, TopPanel;
    private JTable tblEmployee;
    private JLabel lblDetails, lblStaff, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JScrollPane scrollEmployee;
   // private ImagePanel imgPatient;
    
    
    StaffManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        btnAdd = new JButton("+  Add Staff");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        //Search Bar
        txtSearch = new JTextField("Search by patient name or patient id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnRefresh);
        
        
        
        
        lblStaff = new JLabel("Staff Management");
        lblStaff.setBounds(30, 30, 500, 40);
        lblStaff.setFont(FontsTheme.Bold_Texts);
        lblStaff.setForeground(ColorsTheme.Text_Black);
        add(lblStaff);

        lblDetails = new JLabel("Manage hospital staffs and schedules");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlTotal = createCard(
                "Total Staff",
                "245");
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlOn = createCard(
                "On Duty",
                "180");
        pnlOn.setBounds(450, 130, 350, 110);
        add(pnlOn);
        
       
        pnlOff = createCard(
                "Off Duty",
                "52");
        pnlOff.setBounds(830, 130, 350, 110);
        add(pnlOff);
        
        
        pnlLeave = createCard(
                "On Leave",
                "13");
        pnlLeave.setBounds(1210, 130, 350, 110);
        add(pnlLeave);
        
        
        
         //Table
        String[] columns = {"Name", "Role", "Department", "Status", "Patient", "Actions"};
        Object[][] data = {
            {"Adrian Marquez", "Head Nurse", "ICU", "On Duty", "12", ""},
            {"Sophia Reyes", "Admin Officer", "Human Resources", "Available", "0", ""},
            {"Daniel Cruz", "Staff Nurse", "Emergency Room", "On Duty", "8", ""},
            {"Angela Santos", "Cardiologist", "Cardiology", "Off Duty", "0", ""},
            {"Kevin Mendoza", "Finance Admin", "Finance", "Available", "0", ""},
            {"Nicole Garcia", "Staff Nurse", "Pediatrics", "On Duty", "10", ""},
            {"Joshua Lim", "Orthopedic Doctor", "Orthopedics", "Busy", "15", ""},
            {"Patricia Flores", "Records Admin", "Records", "Available", "0", ""},
            {"Ryan Torres", "Head Nurse", "Surgery", "On Duty", "11", ""},
            {"Claire Bautista", "Radiologist", "Radiology", "On Duty", "9", ""},

            {"Ethan Ramos", "Dermatologist", "Dermatology", "On Duty", "6", ""},
            {"Maria Lopez", "Staff Nurse", "ICU", "On Duty", "14", ""},
            {"John Fernandez", "IT Admin", "IT", "Available", "0", ""},
            {"Ella Navarro", "Oncologist", "Oncology", "Busy", "13", ""},
            {"Vincent Tan", "Staff Nurse", "Maternity", "Off Duty", "0", ""},
            {"Alyssa Rivera", "Operations Admin", "Operations", "Available", "0", ""},
            {"Mark Velasco", "ENT Doctor", "ENT", "On Duty", "7", ""},
            {"Sophia Cruz", "Staff Nurse", "Emergency Room", "Busy", "9", ""},
            {"James Castillo", "Procurement Admin", "Procurement", "Available", "0", ""},
            {"Camille Garcia", "Pulmonologist", "Pulmonology", "On Duty", "8", ""},

            {"Michael Torres", "Cardiologist", "Cardiology", "Off Duty", "0", ""},
            {"Patricia Ong", "Head Nurse", "ICU", "On Duty", "16", ""},
            {"Daniel Santos", "Finance Admin", "Finance", "Busy", "0", ""},
            {"Nicole Reyes", "Psychiatrist", "Psychiatry", "Available", "4", ""},
            {"Joshua Mendoza", "Staff Nurse", "Radiology", "On Duty", "7", ""},
            {"Angela Garcia", "Legal Admin", "Legal", "Available", "0", ""},
            {"Claire Ramos", "Pediatrician", "Pediatrics", "On Duty", "12", ""},
            {"Ethan Tan", "Staff Nurse", "Orthopedics", "Busy", "10", ""},
            {"James Bautista", "Records Admin", "Records", "Available", "0", ""},
            {"Patricia Navarro", "Neurologist", "Neurology", "Off Duty", "0", ""},

            {"Nathan Flores", "Staff Nurse", "Emergency Room", "On Duty", "9", ""},
            {"Harper Garcia", "Customer Service Admin", "Customer Service", "Busy", "0", ""},
            {"Benjamin Torres", "Dermatologist", "Dermatology", "On Duty", "5", ""},
            {"Evelyn Mendoza", "Staff Nurse", "ICU", "Available", "6", ""},
            {"Jacob Ramos", "IT Admin", "IT", "Available", "0", ""},
            {"Abigail Navarro", "Cardiologist", "Cardiology", "Busy", "14", ""},
            {"Michael Diaz", "Head Nurse", "Surgery", "On Duty", "13", ""},
            {"Emily Perez", "Finance Admin", "Finance", "Available", "0", ""},
            {"Daniel Rivera", "Radiologist", "Radiology", "On Duty", "8", ""},
            {"Sofia Castillo", "Staff Nurse", "Pediatrics", "Off Duty", "0", ""},

            {"Matthew Gomez", "Billing Admin", "Billing", "Busy", "0", ""},
            {"Avery Tan", "Oncologist", "Oncology", "On Duty", "11", ""},
            {"Joseph Bautista", "Staff Nurse", "Maternity", "Available", "5", ""},
            {"Ella Ong", "Operations Admin", "Operations", "Available", "0", ""},
            {"David Lim", "ENT Doctor", "ENT", "On Duty", "7", ""},
            {"Scarlett Reyes", "Staff Nurse", "Emergency Room", "Busy", "10", ""},
            {"Samuel Cruz", "HR Admin", "Human Resources", "Available", "0", ""},
            {"Grace Flores", "Orthopedic Doctor", "Orthopedics", "Off Duty", "0", ""},
            {"Carter Garcia", "Head Nurse", "ICU", "On Duty", "15", ""},
            {"Chloe Torres", "Accounting Admin", "Accounting", "Busy", "0", ""}

          };
        
        tblEmployee = new JTable (data, columns);
        tblEmployee.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblEmployee.setFont(FontsTheme.Info_Texts);
        tblEmployee.setRowHeight(50);
        tblEmployee.setDefaultEditor(Object.class, null);
        tblEmployee.getTableHeader().setReorderingAllowed(false);
       // tblEmployee.getTableHeader().setBackground(ColorsTheme.Header); 
        tblEmployee.getTableHeader().setForeground(ColorsTheme.Text_White);
        
        scrollEmployee = new JScrollPane(tblEmployee);
        scrollEmployee.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(scrollEmployee);
        
    }

    
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);


        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblTitle);


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(Color.BLACK);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
    }
    

