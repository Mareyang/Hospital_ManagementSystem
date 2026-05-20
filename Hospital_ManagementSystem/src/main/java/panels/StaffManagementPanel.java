/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.temp4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class StaffManagementPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlOn, pnlOff, pnlLeave;
    private TablePanel tblEmployee;
    private JLabel lblDetails, lblStaff;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
   
    
    
    public StaffManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        //Search Panel Container
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //Button for adding new staff
        btnAdd = new JButton("+  Add Staff");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        //Search Bar including search and refresh buttons
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
        
        
        //Title and subtitle label for staff section
        lblStaff = new JLabel("Staff Management");
        lblStaff.setBounds(30, 30, 500, 40);
        lblStaff.setFont(FontsTheme.Bold_Texts);
        lblStaff.setForeground(ColorsTheme.Text_Black);
        add(lblStaff);

        lblDetails = new JLabel("Manage hospital staffs and schedules.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Summary Panel Cards
        pnlTotal = new PanelCard("Total Staff", "245", ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlOn = new PanelCard("On Duty", "180", ColorsTheme.Green);
        pnlOn.setBounds(450, 130, 350, 110);
        add(pnlOn);
       
        pnlOff = new PanelCard("Off Duty", "52", ColorsTheme.Yellow);
        pnlOff.setBounds(830, 130, 350, 110);
        add(pnlOff);
        
        pnlLeave = new PanelCard("On Leave", "13", ColorsTheme.Red);
        pnlLeave.setBounds(1210, 130, 350, 110);
        add(pnlLeave);
        
        
        
        //Table column names
        String[] columns = {"Name", "Role", "Department", "Status", "Patient", "Actions"};
        
        //Sample records
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
        

        
        //Table Panel for records
        tblEmployee = new TablePanel("Employee Records", columns, data, 440);
        tblEmployee.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblEmployee);
        
        
        //ActionListener
        btnAdd.addActionListener(this);
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens staff form dialog
        if (btnAdd == e.getSource()) {
            temp4 dialog = new temp4();
            dialog.setVisible(true);
        }
    }
}
    
    


