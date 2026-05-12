/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

/**
 *
 * @author Arabella
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, cardPanel, TopPanel, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblTitle, lblValue, lblSubtitle, lblDistributionTitle;
    private JPanel pnlDistribution;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
            
            
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
        
        pnlDistribution = new JPanel();
        pnlDistribution.setLayout(null);
        pnlDistribution.setBackground(ColorsTheme.Middle_Panel);
        pnlDistribution.setBounds(950,50,500,400);
        pnlMiddle.add(pnlDistribution);
        
        lblDistributionTitle = new JLabel("Department Distribution");
        lblDistributionTitle.setFont(FontsTheme.Title_Texts);
        lblDistributionTitle.setBounds(10,20,300,30);
        pnlDistribution.add(lblDistributionTitle);
        
        barCardiology = createCustomProgressBar(10,70,480,30,25,new Color(0,0,255));
        barOrthopedics = createCustomProgressBar(10,120,480,30,22,new Color(0,255,0));
        barEmergency = createCustomProgressBar(10,170,480,30,20, new Color(255,0,0));
        barNeurology = createCustomProgressBar(10,220,480,30,18, new Color(255,0,255));
        barPediatrics = createCustomProgressBar(10,270,480,30,15, new Color(255,200,200));
        
        pnlDistribution.add(barCardiology);
        pnlDistribution.add(barOrthopedics);
        pnlDistribution.add(barEmergency);
        pnlDistribution.add(barNeurology);
        pnlDistribution.add(barPediatrics);
        
        
        JPanel leg1 = createLegendItem(20, 300, "Cardiology 25%", new Color(0,0,255));
        JPanel leg2 = createLegendItem(20, 330, "Neurology 22%", new Color(0,255,0));
        JPanel leg3 = createLegendItem(20, 360, "Orthopedics 20%", new Color(255,0,0));
        JPanel leg4 = createLegendItem(200, 300, "Pediatrics 18%", new Color(255,0,255));
        JPanel leg5 = createLegendItem(200, 330, "Emergency 15%", new Color(255,200,200));

        pnlDistribution.add(leg1);
        pnlDistribution.add(leg2);
        pnlDistribution.add(leg3);
        pnlDistribution.add(leg4);
        pnlDistribution.add(leg5);

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
    
    
    private JProgressBar createCustomProgressBar(int x, int y, int width, int height, int value, Color filledColor) {
        JProgressBar bar = new JProgressBar(0, 100);   
        bar.setBounds(x, y, width, height);            
        bar.setValue(value);                           
        bar.setStringPainted(true);                   
        bar.setForeground(filledColor);                
        bar.setBackground(new Color(220, 220, 220));   
        return bar;
    }
    
    
    
    private JPanel createLegendItem(int x, int y, String text, Color color) {
        JPanel item = new JPanel();
        item.setLayout(null);
        item.setOpaque(false);
        item.setBounds(x, y, 300, 28);

        JPanel cube = new JPanel();
        cube.setBackground(color);
        cube.setBounds(0, 6, 16, 16);
        item.add(cube);

        JLabel label = new JLabel(text);
        label.setFont(FontsTheme.Info_Texts);
        label.setBounds(20, 2, 260, 24);
        item.add(label);

        return item;
    }
    }

    

    