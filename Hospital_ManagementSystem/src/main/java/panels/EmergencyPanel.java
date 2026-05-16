/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import com.mycompany.hospitalmanage.*;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.emergencyDialog;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Arabella
 */
public class EmergencyPanel extends JPanel implements ActionListener{
    
    private JPanel pnlActive, pnlDispatch, pnlAvail, pnlReturn, cardPanel, TopPanel,pnlAmbulance,pnlMiddle,pnlAmb1,pnlAmb2,pnlAmb3,pnlAmb4;
    private JLabel lblDetails, lblEmergency, lblTitle, lblValue,
            lblAmb1,lblAmb11,lblAmb12,lblAmb13,lblAmb2,lblAmb21,lblAmb22,lblAmb23,lblAmb3,lblAmb31,lblAmb32,lblAmb33,lblAmb4,lblAmb41,lblAmb42,lblAmb43;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table;
    private JScrollPane scrollPane;
   // private ImagePanel imgPatient;
    
    
    public EmergencyPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 600, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        lblEmergency = new JLabel("Emergency Services");
        lblEmergency.setBounds(30, 30, 500, 40);
        lblEmergency.setFont(FontsTheme.Bold_Texts);
        lblEmergency.setForeground(ColorsTheme.Text_Black);
        add(lblEmergency);

        lblDetails = new JLabel("Manage ambulance dispatch and emergency cases");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+ Emergency Dispatch");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Delete_Urgent);
        btnAdd.setFocusPainted(false);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
        
        pnlAvail = createCard(
                "Available",
                "3");
        TopPanel.setBackground(Color.green);
       
        pnlAvail.setBounds(70, 130, 350, 110);
        add(pnlAvail);
        
        
        pnlDispatch = createCard(
                "Dispatched",
                "1");
        TopPanel.setBackground(Color.red);
     
        pnlDispatch.setBounds(450, 130, 350, 110);
        add(pnlDispatch);
        
       
        pnlReturn = createCard(
                "Returning",
                "1");
        TopPanel.setBackground(Color.orange);
       
        pnlReturn.setBounds(830, 130, 350, 110);
        add(pnlReturn);
        
        
        pnlActive = createCard(
                "Active Cases",
                "2");  
        TopPanel.setBackground(Color.blue);
       
        pnlActive.setBounds(1210, 130, 350, 110);
        add(pnlActive);
        
     
        
      
        pnlAmbulance = createCard("Ambulance Fleet", " ");
        pnlAmbulance.setBounds(70, 260, 1500, 300);
        add(pnlAmbulance);
        TopPanel.setBackground(ColorsTheme.Header);
        TopPanel.setBounds(0,0,1500,10);
        pnlAmbulance.setLayout(null);
       //amb2
        pnlAmb1 = roomCard("AMB-001"," ");
        pnlAmbulance.add(pnlAmb1);
        pnlAmb1.setBounds(70, 90, 300, 175);
        //label in amb1
        lblAmb1 = new JLabel ("Available",SwingConstants.RIGHT );       
        pnlAmb1.add(lblAmb1);
        lblAmb1.setBounds(20, 25, 250, 25);
        lblAmb1.setForeground(Color.green);
        lblAmb1.setFont(FontsTheme.Plain_Texts);
        
        lblAmb11 = new JLabel ("ALS",SwingConstants.LEFT  );
        pnlAmb1.add(lblAmb11);
        lblAmb11.setBounds(20, 50, 250, 45);
        lblAmb11.setForeground(Color.white);
        lblAmb11.setFont(FontsTheme.Info_Texts);
        
        lblAmb12 = new JLabel ( "JOHN DRIVER",SwingConstants.LEFT  );
        pnlAmb1.add(lblAmb12);
        lblAmb12.setBounds(20, 55, 250, 70);
        lblAmb12.setForeground(Color.white);
        lblAmb12.setFont(FontsTheme.Info_Texts);
        
        lblAmb13 = new JLabel ( "BASE",SwingConstants.LEFT  );
        pnlAmb1.add(lblAmb13);
        lblAmb13.setBounds(20, 65, 150, 85);
        lblAmb13.setForeground(Color.white);
        lblAmb13.setFont(FontsTheme.Info_Texts);
        
    
        // amb2
        
        pnlAmb2 = roomCard("AMB-002"," ");
        pnlAmbulance.add(pnlAmb2);
        pnlAmb2.setBounds(420, 90, 300, 175);
       //label in amb2
        lblAmb2 = new JLabel ("Dispatched",SwingConstants.RIGHT );       
        pnlAmb2.add(lblAmb2);
        lblAmb2.setBounds(20, 25, 250, 25);
        lblAmb2.setForeground(Color.red);
        lblAmb2.setFont(FontsTheme.Plain_Texts);
        
        lblAmb21 = new JLabel ("BLS",SwingConstants.LEFT  );
        pnlAmb2.add(lblAmb21);
        lblAmb21.setBounds(20, 50, 250, 45);
        lblAmb21.setForeground(Color.white);
        lblAmb21.setFont(FontsTheme.Info_Texts);
        
        lblAmb22 = new JLabel ( "En Route- 5th Ave",SwingConstants.LEFT  );
        pnlAmb2.add(lblAmb22);
        lblAmb22.setBounds(20, 55, 250, 70);
        lblAmb22.setForeground(Color.white);
        lblAmb22.setFont(FontsTheme.Info_Texts);
        
        lblAmb23 = new JLabel ( "Mike Driver",SwingConstants.LEFT  );
        pnlAmb2.add(lblAmb23);
        lblAmb23.setBounds(20, 65, 150, 85);
        lblAmb23.setForeground(Color.white);
        lblAmb23.setFont(FontsTheme.Info_Texts);
    
        
         // amb3
        
        pnlAmb3 = roomCard("AMB-003"," ");
        pnlAmbulance.add(pnlAmb3);
        pnlAmb3.setBounds(770, 90, 300, 175);
       //label in amb3
        lblAmb3 = new JLabel ("Available",SwingConstants.RIGHT );       
        pnlAmb3.add(lblAmb3);
        lblAmb3.setBounds(20, 25, 250, 25);
        lblAmb3.setForeground(Color.green);
        lblAmb3.setFont(FontsTheme.Plain_Texts);
        
        lblAmb31 = new JLabel ("Patient Transport",SwingConstants.LEFT  );
        pnlAmb3.add(lblAmb31);
        lblAmb31.setBounds(20, 50, 250, 45);
        lblAmb31.setForeground(Color.white);
        lblAmb31.setFont(FontsTheme.Info_Texts);
        
        lblAmb32= new JLabel ( "Base",SwingConstants.LEFT  );
        pnlAmb3.add(lblAmb32);
        lblAmb32.setBounds(20, 55, 250, 70);
        lblAmb32.setForeground(Color.white);
        lblAmb32.setFont(FontsTheme.Info_Texts);
        
        lblAmb33 = new JLabel ( "Tom Driver",SwingConstants.LEFT  );
        pnlAmb3.add(lblAmb33);
        lblAmb33.setBounds(20, 65, 150, 85);
        lblAmb33.setForeground(Color.white);
        lblAmb33.setFont(FontsTheme.Info_Texts);
        
         // amb4
        
        pnlAmb4 = roomCard("AMB-004"," ");
        pnlAmbulance.add(pnlAmb4);
        pnlAmb4.setBounds(1110, 90, 300, 175);
       //label in amb4
        lblAmb4 = new JLabel ("Returning",SwingConstants.RIGHT );       
        pnlAmb4.add(lblAmb4);
        lblAmb4.setBounds(20, 25, 250, 25);
        lblAmb4.setForeground(Color.orange);
        lblAmb4.setFont(FontsTheme.Plain_Texts);
        
        lblAmb41 = new JLabel ("ALS",SwingConstants.LEFT  );
        pnlAmb4.add(lblAmb41);
        lblAmb41.setBounds(20, 50, 250, 45);
        lblAmb41.setForeground(Color.white);
        lblAmb41.setFont(FontsTheme.Info_Texts);
        
        lblAmb42 = new JLabel ( "Highway 101",SwingConstants.LEFT  );
        pnlAmb4.add(lblAmb42);
        lblAmb42.setBounds(20, 55, 250, 70);
        lblAmb42.setForeground(Color.white);
        lblAmb42.setFont(FontsTheme.Info_Texts);
        
        lblAmb43 = new JLabel ( "Sarah Driver",SwingConstants.LEFT  );
        pnlAmb4.add(lblAmb43);
        lblAmb43.setBounds(20, 65, 150, 85);
        lblAmb43.setForeground(Color.white);
        lblAmb43.setFont(FontsTheme.Info_Texts);

        
        
         emergencyTable ();
         
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
        public JPanel roomCard (String title, String value) {
            
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Header);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 1490, 10);
        TopPanel.setBackground(ColorsTheme.Header);
        cardPanel.add(TopPanel);
        


        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(Color.white);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblTitle);


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);
        
        
        return cardPanel;
        
        }   
        private void emergencyTable() {
        String [] columns = {"Type", "Patient","Location","Status","ETA","Action"};
        Object [][] data = {
        {"Cardiac Emergency","Unknown","Downtown","Dispatched","8 min"," "},
         {"Cardiac Emergency","Unknown","Downtown","Active Cases","8 min"," "}, 
         {"Cardiac Emergency","Unknown","Downtown","Available","8 min"," "}, 
         {"Cardiac Emergency","Unknown","Downtown","Returning","8 min"," "}, 
        };
        table = new JTable (data, columns);
        table.getTableHeader().setFont(FontsTheme.Bold_Texts);
        table.setFont (FontsTheme.Plain_Texts);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(ColorsTheme.Header); 
        table.getTableHeader().setForeground(ColorsTheme.Text_White);
         
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds (0, 50, 1500, 620);
            pnlMiddle.add(scrollPane);
        
        
        JLabel ltable = new JLabel ( "Active Emergency Cases",SwingConstants.LEFT  );
        pnlMiddle.add(ltable);
        ltable.setBounds(0, 0, 1500, 50);
        ltable.setForeground(Color.black);
        ltable.setFont(FontsTheme.Title_Texts);
        table.getColumnModel().getColumn(3)
            .setCellRenderer(new EmergencyPanel.StatusColor());
        }
        
        
  
            @Override
            public void actionPerformed(ActionEvent e) { 
                if (e.getSource()== btnAdd) {
                    emergencyDialog emergency = new emergencyDialog ();
                    emergency.setVisible(true);
                }
            }
            
        private static class StatusColor extends DefaultTableCellRenderer {
            
              @Override
            
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
            
            java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = value.toString();
            
            cell.setForeground(ColorsTheme.Text_Black);
            
            if (status.equals("STAT")) {
                cell.setForeground(ColorsTheme.Delete_Urgent);
            } else if (status.equals("Dispatched")) {
                cell.setForeground(Color.red);
            } else if (status.equals("Returning")) {
                cell.setForeground(Color.orange);
            } else if (status.equals("Available")) {
                cell.setForeground(ColorsTheme.Add_Confirm);
            }else if (status.equals("Active Cases")) {
                cell.setForeground(Color.blue);
            }
            return cell;
        }
        }
        }
    
    

