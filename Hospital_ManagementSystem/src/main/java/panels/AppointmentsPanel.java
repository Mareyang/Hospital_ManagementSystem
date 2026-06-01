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
        String[] columns = {"Appt ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status", "Actions"};
        
        //Sample records
        Object[][] data = {
                
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

        
        
       
        
  
        
        
       
    
    


    
    

