/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.TablePanel;
import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.NewAppointmentDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class AppointmentsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlConfirm, pnlPending, pnlUrgent;
    private JLabel lblDetails, lblAppointment;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblAppointments;
    

    
    
    public AppointmentsPanel() {
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
        
        //Button for adding new appointment
        btnAdd = new JButton("+  New Appointment");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        //Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search appointments...");
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
                
        
        //Title and subtitle label for appointments section
        lblAppointment = new JLabel("Appointments");
        lblAppointment.setBounds(30, 30, 500, 40);
        lblAppointment.setFont(FontsTheme.Bold_Texts);
        lblAppointment.setForeground(ColorsTheme.Text_Black);
        add(lblAppointment);

        lblDetails = new JLabel("Manage and schedule patient appointments.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Summary Panel Cards
        pnlTotal = new PanelCard("Today's Total", "25", ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlConfirm = new PanelCard("Confirmed", "12", ColorsTheme.Green);
        pnlConfirm.setBounds(450, 130, 350, 110);
        add(pnlConfirm);
        
        pnlPending = new PanelCard("Pending", "8", ColorsTheme.Yellow);
        pnlPending.setBounds(830, 130, 350, 110);
        add(pnlPending);
        
        pnlUrgent = new PanelCard("Urgent", "5", ColorsTheme.Red);
        pnlUrgent.setBounds(1210, 130, 350, 110);
        add(pnlUrgent);
        
        
        //Table column names
        String[] columns = {"Patient Name", "Doctor", "Time", "Type", "Department", "Status", "Actions"};
        
        //Sample records
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
        

        //Table Panel for records
        tblAppointments = new TablePanel("Upcoming Visits", columns, data, 440);
        tblAppointments.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblAppointments);


        
        //ActionListener
        btnAdd.addActionListener(this);
        
        
        
        }
    
    
   


    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens appointment form dialog
        if (e.getSource() == btnAdd) {
        NewAppointmentDialog appointment = new NewAppointmentDialog();
        appointment.setVisible(true);
        }

    }
    
    
    
    
    }

        
        
       
        
  
        
        
       
    
    


    
    

