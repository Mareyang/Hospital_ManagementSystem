/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.TablePanel;
import constants.PanelCard;
import com.mycompany.hospitalmanage.*;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.AddEmergencyDialog;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


/**
 *
 * 
 */
public class EmergencyPanel extends JPanel implements ActionListener {
    
    private JPanel pnlActive, pnlDispatch, pnlAvail, pnlReturn, pnlCard, pnlTop,
            pnlAmbulance, pnlMiddle, pnlAmb1, pnlAmb2, pnlAmb3, pnlAmb4;
    private JLabel lblDetails, lblEmergency, lblTitle, lblValue, lblAmb1, lblAmb11, lblAmb12,
            lblAmb13, lblAmb2, lblAmb21, lblAmb22, lblAmb23, lblAmb3, lblAmb31, lblAmb32, lblAmb33, lblAmb4, 
            lblAmb41, lblAmb42, lblAmb43;
    private JButton btnAdd;
    private TablePanel tblEmergency;

    
    
    public EmergencyPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 600, 1500, 300);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        lblEmergency = new JLabel("Emergency Services");
        lblEmergency.setBounds(30, 30, 500, 40);
        lblEmergency.setFont(FontsTheme.Bold_Texts);
        lblEmergency.setForeground(ColorsTheme.Text_Black);
        add(lblEmergency);

        lblDetails = new JLabel("Manage ambulance dispatch and emergency cases.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        btnAdd = new JButton("+ Emergency Dispatch");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Delete_Urgent);
        btnAdd.setFocusPainted(false);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        pnlAvail = new PanelCard("Available", "3", ColorsTheme.Blue);
        pnlAvail.setBounds(70, 130, 350, 110);
        add(pnlAvail);
        
        
        pnlDispatch = new PanelCard("Dispatched", "1", ColorsTheme.Red); 
        pnlDispatch.setBounds(450, 130, 350, 110);
        add(pnlDispatch);
        
       
        pnlReturn = new PanelCard("Returning", "1", ColorsTheme.Yellow);
        pnlReturn.setBounds(830, 130, 350, 110);
        add(pnlReturn);
        
        
        pnlActive = new PanelCard("Active Cases", "2", ColorsTheme.Green); 
        pnlActive.setBounds(1210, 130, 350, 110);
        add(pnlActive);
        
     
        
      
        //Down Panel
        pnlAmbulance = roomCard("Ambulance Fleet", " ");
        pnlAmbulance.setBounds(70, 260, 1500, 300);
        add(pnlAmbulance);
        
       //amb2
        pnlAmb1 = new PanelCard("AMB-001"," ", ColorsTheme.Gray);
        pnlAmb1.setBounds(70, 90, 300, 175);
        pnlAmb1.setForeground(ColorsTheme.Text_Black);
        pnlAmb1.setFont(FontsTheme.Title_Texts);
        pnlAmbulance.add(pnlAmb1);
           
       
        //label in amb1
        lblAmb1 = new JLabel("Available", SwingConstants.RIGHT);       
        lblAmb1.setBounds(20, 25, 250, 25);
        lblAmb1.setForeground(ColorsTheme.Green);
        lblAmb1.setFont(FontsTheme.Plain_Texts);
        pnlAmb1.add(lblAmb1);

        
        lblAmb11 = new JLabel("ALS", SwingConstants.LEFT);
        lblAmb11.setBounds(20, 50, 250, 45);
        lblAmb11.setForeground(ColorsTheme.Text_Black);
        lblAmb11.setFont(FontsTheme.Info_Texts);
        pnlAmb1.add(lblAmb11);

        
        lblAmb12 = new JLabel("John Driver", SwingConstants.LEFT);
        lblAmb12.setBounds(20, 55, 250, 70);
        lblAmb12.setForeground(ColorsTheme.Text_Black);
        lblAmb12.setFont(FontsTheme.Info_Texts);
        pnlAmb1.add(lblAmb12);

        
        lblAmb13 = new JLabel("Base", SwingConstants.LEFT);
        lblAmb13.setBounds(20, 65, 150, 85);
        lblAmb13.setForeground(ColorsTheme.Text_Black);
        lblAmb13.setFont(FontsTheme.Info_Texts);
        pnlAmb1.add(lblAmb13);

    
        // amb2
        pnlAmb2 = new PanelCard("AMB-002"," ", ColorsTheme.Gray);
        pnlAmb2.setBounds(420, 90, 300, 175);
        pnlAmb2.setForeground(ColorsTheme.Text_Black);
        pnlAmb2.setFont(FontsTheme.Title_Texts);
        pnlAmbulance.add(pnlAmb2);

       //label in amb2
        lblAmb2 = new JLabel("Dispatched", SwingConstants.RIGHT);       
        lblAmb2.setBounds(20, 25, 250, 25);
        lblAmb2.setForeground(ColorsTheme.Red);
        lblAmb2.setFont(FontsTheme.Plain_Texts);
        pnlAmb2.add(lblAmb2);
        
        lblAmb21 = new JLabel("BLS", SwingConstants.LEFT);
        lblAmb21.setBounds(20, 50, 250, 45);
        lblAmb21.setForeground(ColorsTheme.Text_Black);
        lblAmb21.setFont(FontsTheme.Info_Texts);
        pnlAmb2.add(lblAmb21);
        
        lblAmb22 = new JLabel("En Route- 5th Ave", SwingConstants.LEFT);
        lblAmb22.setBounds(20, 55, 250, 70);
        lblAmb22.setForeground(ColorsTheme.Text_Black);
        lblAmb22.setFont(FontsTheme.Info_Texts);
        pnlAmb2.add(lblAmb22);
        
        
        lblAmb23 = new JLabel ("Mike Driver", SwingConstants.LEFT);
        lblAmb23.setBounds(20, 65, 150, 85);
        lblAmb23.setForeground(ColorsTheme.Text_Black);
        lblAmb23.setFont(FontsTheme.Info_Texts);
        pnlAmb2.add(lblAmb23);
    
        
         // amb3
        pnlAmb3 = new PanelCard("AMB-003", " ", ColorsTheme.Gray);
        pnlAmb3.setBounds(770, 90, 300, 175);
        pnlAmb3.setForeground(ColorsTheme.Text_Black);
        pnlAmb3.setFont(FontsTheme.Title_Texts);
        pnlAmbulance.add(pnlAmb3);
        
       //label in amb3
        lblAmb3 = new JLabel("Available", SwingConstants.RIGHT );       
        lblAmb3.setBounds(20, 25, 250, 25);
        lblAmb3.setForeground(ColorsTheme.Green);
        lblAmb3.setFont(FontsTheme.Plain_Texts);
        pnlAmb3.add(lblAmb3);
        
        lblAmb31 = new JLabel("Patient Transport", SwingConstants.LEFT);
        lblAmb31.setBounds(20, 50, 250, 45);
        lblAmb31.setForeground(ColorsTheme.Text_Black);
        lblAmb31.setFont(FontsTheme.Info_Texts);
        pnlAmb3.add(lblAmb31);
        
        lblAmb32= new JLabel("Base", SwingConstants.LEFT);
        lblAmb32.setBounds(20, 55, 250, 70);
        lblAmb32.setForeground(ColorsTheme.Text_Black);
        lblAmb32.setFont(FontsTheme.Info_Texts);
        pnlAmb3.add(lblAmb32);
        
        lblAmb33 = new JLabel("Tom Driver", SwingConstants.LEFT);
        lblAmb33.setBounds(20, 65, 150, 85);
        lblAmb33.setForeground(ColorsTheme.Text_Black);
        lblAmb33.setFont(FontsTheme.Info_Texts);
        pnlAmb3.add(lblAmb33);

        
         // amb4
        pnlAmb4 = new PanelCard("AMB-004", " ", ColorsTheme.Gray);
        pnlAmb4.setBounds(1110, 90, 300, 175);
        pnlAmb4.setForeground(ColorsTheme.Text_Black);
        pnlAmb4.setFont(FontsTheme.Title_Texts);
        pnlAmbulance.add(pnlAmb4);
        
       //label in amb4
        lblAmb4 = new JLabel("Returning", SwingConstants.RIGHT);       
        lblAmb4.setBounds(20, 25, 250, 25);
        lblAmb4.setForeground(ColorsTheme.Yellow);
        lblAmb4.setFont(FontsTheme.Plain_Texts);
        pnlAmb4.add(lblAmb4);
        
        lblAmb41 = new JLabel("ALS", SwingConstants.LEFT);
        lblAmb41.setBounds(20, 50, 250, 45);
        lblAmb41.setForeground(ColorsTheme.Text_Black);
        lblAmb41.setFont(FontsTheme.Info_Texts);
        pnlAmb4.add(lblAmb41);
        
        lblAmb42 = new JLabel("Highway 101", SwingConstants.LEFT);
        lblAmb42.setBounds(20, 55, 250, 70);
        lblAmb42.setForeground(ColorsTheme.Text_Black);
        lblAmb42.setFont(FontsTheme.Info_Texts);
        pnlAmb4.add(lblAmb42);
        
        lblAmb43 = new JLabel("Sarah Driver", SwingConstants.LEFT);
        lblAmb43.setBounds(20, 65, 150, 85);
        lblAmb43.setForeground(ColorsTheme.Text_Black);
        lblAmb43.setFont(FontsTheme.Info_Texts);
        pnlAmb4.add(lblAmb43);

         
        //Table
        String [] columns = {"Type", "Patient", "Location", "Status", "ETA", "Actions"};
        Object [][] data = {
        {"Cardiac Emergency", "Unknown", "Downtown", "Dispatched", "8 min", " "},
         {"Cardiac Emergency", "Unknown", "Downtown", "Active Cases", "8 min", " "}, 
         {"Cardiac Emergency", "Unknown", "Downtown", "Available", "8 min", " "}, 
         {"Cardiac Emergency", "Unknown", "Downtown", "Returning", "8 min", " "}, 
         {"Cardiac Emergency", "Unknown", "Downtown", "Dispatched", "8 min", " "},
         {"Cardiac Emergency", "Unknown", "Downtown", "Active Cases", "8 min", " "}, 
         {"Cardiac Emergency", "Unknown", "Downtown", "Available", "8 min", " "}, 
         {"Cardiac Emergency", "Unknown", "Downtown", "Returning", "8 min", " "}, 
        };
        
        
        
        tblEmergency = new TablePanel("Dispatch Logs", columns, data, 240);
        tblEmergency.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblEmergency);

        
        
        //ActionListener
        btnAdd.addActionListener(this);

    }

    
    
    
    
        public JPanel roomCard (String title, String value) {
            
        pnlCard = new JPanel();
        pnlCard.setLayout(null);
        pnlCard.setBackground(ColorsTheme.Gray_Button);
        
        pnlTop = new JPanel();
        pnlTop.setBounds(0, 0, 1490, 10);
        pnlTop.setBackground(ColorsTheme.Header);
        pnlCard.add(pnlTop);
        
        
        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        pnlCard.add(lblTitle);


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        pnlCard.add(lblValue);
        
        
        return pnlCard;
        }
         
        

        @Override
        public void actionPerformed(ActionEvent e) { 
            if (e.getSource()== btnAdd) {
                AddEmergencyDialog emergency = new AddEmergencyDialog ();
                emergency.setVisible(true);
            }
        }
            
  }
    
    

