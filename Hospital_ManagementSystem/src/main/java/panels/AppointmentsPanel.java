/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class AppointmentsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlConfirm, pnlPending, pnlUrgent, cardPanel, TopPanel;
    private JLabel lblDetails, lblAppointment, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnSearch1;
    private JScrollPane scrollPatient;
    private JTable tblPatient;


   // private ImagePanel imgPatient;
    
    
    AppointmentsPanel() {
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
        
        btnAdd = new JButton("+  New Appointment");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        //Search Bar
        txtSearch = new JTextField("Search appointments...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/search.png"));

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnSearch1 = new JButton(scaledIcon); // use your class variable
        btnSearch1.setBounds(40, 20, 40, 39);
        btnSearch1.setBackground(Color.decode("#3A2A75"));
        btnSearch1.setBorder(BorderFactory.createEmptyBorder());
        
        pnlSearch.add(btnSearch1);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search_Button);
        btnSearch.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        pnlSearch.add(btnRefresh);
        
        
        
        
        lblAppointment = new JLabel("Appointments");
        lblAppointment.setBounds(30, 30, 500, 40);
        lblAppointment.setFont(FontsTheme.Bold_Texts);
        lblAppointment.setForeground(ColorsTheme.Text_Black);
        add(lblAppointment);

        lblDetails = new JLabel("Manage and schedule patient appointments");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlTotal = createCard(
                "Today's Total",
                "25");
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlConfirm = createCard(
                "Confirmed",
                "12");
        pnlConfirm.setBounds(450, 130, 350, 110);
        add(pnlConfirm);
        
       
        pnlPending = createCard(
                "Pending",
                "8");
        pnlPending.setBounds(830, 130, 350, 110);
        add(pnlPending);
        
        
        pnlUrgent = createCard(
                "Urgent",
                "5");
        pnlUrgent.setBounds(1210, 130, 350, 110);
        add(pnlUrgent);
        
        
        //Table
        String[] columns = {"Patient Name", "Doctor", "Time", "Type", "Department", "Status", "Actions"};
        Object[][] data = {
                {"Maria Leonora", "Dr. Santos", "8:00 AM", "Check-up", "Emergency", "Confirmed", " "},
                {"Jose Felipe", "Dr. Ramirez", "8:30 AM", "Blood Test", "Laboratory", "Pending", " "},
                {"Angela Cruz", "Dr. Garcia", "9:00 AM", "Consultation", "Cardiology", "Urgent", " "},
                {"Mark Anthony", "Dr. Reyes", "9:30 AM", "Follow-up", "Orthopedics", "Confirmed", " "},
                {"Sophia Reyes", "Dr. Mendoza", "10:00 AM", "Vaccination", "Pediatrics", "Pending", " "},
                {"Daniel Garcia", "Dr. Flores", "10:30 AM", "MRI Scan", "Radiology", "Urgent", " "},
                {"Christine Mae", "Dr. Navarro", "11:00 AM", "Physical Exam", "General Medicine", "Confirmed", " "},
                {"Nathaniel Ong", "Dr. Aquino", "11:30 AM", "Urine Test", "Laboratory", "Pending", " "},
                {"Patricia Gomez", "Dr. Bautista", "1:00 PM", "Dental Check", "Dental", "Confirmed", " "},
                {"Kevin Dela Cruz", "Dr. Villanueva", "1:30 PM", "X-Ray", "Radiology", "Urgent", " "},
                {"Isabella Flores", "Dr. Torres", "2:00 PM", "Consultation", "Neurology", "Confirmed", " "},
                {"Francis Mendoza", "Dr. Herrera", "2:30 PM", "Heart Check", "Cardiology", "Urgent", " "},
                {"Jasmine Aquino", "Dr. Santos", "3:00 PM", "Therapy", "Rehabilitation", "Pending", " "},
                {"Miguel Santos", "Dr. Cruz", "3:30 PM", "Surgery Prep", "Surgery", "Urgent", " "},
                {"Ella Villanueva", "Dr. Garcia", "4:00 PM", "Eye Check", "Ophthalmology", "Confirmed", " "},
                {"Adrian Torres", "Dr. Ramirez", "4:30 PM", "CT Scan", "Radiology", "Pending", " "},
          };
        
        tblPatient = new JTable (data, columns);
        tblPatient.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblPatient.setFont(FontsTheme.Info_Texts);
        tblPatient.setRowHeight(50);
        tblPatient.setDefaultEditor(Object.class, null);
        tblPatient.getTableHeader().setReorderingAllowed(false);
        tblPatient.getTableHeader().setBackground(ColorsTheme.Header); 
        tblPatient.getTableHeader().setForeground(ColorsTheme.Text_White);
        
        scrollPatient = new JScrollPane(tblPatient);
        scrollPatient.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(scrollPatient);
        
        
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
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
    }
        
        
        
        
        
//        imgPatient = new ImagePanel("/patient.png"); 
//        imgPatient.setBounds(80, 150, 380, 450);
//        add(imgPatient);
        
        
        
  
        
        
       
    
    


    
    

