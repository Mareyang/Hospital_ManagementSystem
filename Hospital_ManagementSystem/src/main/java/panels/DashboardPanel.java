/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

/**
 *
 * @author Arabella
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class DashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, cardPanel, TopPanel, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblTitle, lblValue, lblSubtitle;
    
            
            
    DashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 300, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        //Greetings
        lblGreet = new JLabel("Welcome back to CareLink Hospital!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Black);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        
        pnlPatients = createCard(
                "Total Patients",
                "2,847",
                "Active Records");
        pnlPatients.setBounds(70, 130, 350, 140);
        add(pnlPatients);
        
        
        pnlAppointments = createCard(
                "Today's Appointments",
                "156",
                "32 remaining");
        pnlAppointments.setBounds(450, 130, 350, 140);
        add(pnlAppointments);
        
       
        pnlBeds = createCard(
                "Bed Occupancy",
                "78%",
                "156 of 200 beds");
        pnlBeds.setBounds(830, 130, 350, 140);
        add(pnlBeds);
        
        
        pnlRevenue = createCard(
                "Revenue (MTD)",
                "₱125K",
                "Target: $150K");
        pnlRevenue.setBounds(1210, 130, 350, 140);
        add(pnlRevenue);
        

    }

    
    public JPanel createCard(String title, String value, String subtitle) {

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


        //Subtitle
        lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setBounds(20, 100, 250, 25);
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblSubtitle);


        return cardPanel;
        
        
            }
    }