/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

/**
 *
 * 
 */

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, cardPanel, TopPanel, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblTitle, lblValue, lblSubtitle, lblDistributionTitle, lblOverviewTitle;
    private JPanel pnlDistribution, pnlOverview, pnlImage, pnlMetrics;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
            
            
    public DashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);

        lblGreet = new JLabel("Welcome to CareLink!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        
        pnlPatients = createCard(
                "Total Patients",
                "2,847",
                "Active Records",
                ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 150, 350, 140);
        add(pnlPatients);
        
        
        pnlAppointments = createCard(
                "Today's Appointments",
                "156",
                "32 remaining",
                ColorsTheme.Orange);
        pnlAppointments.setBounds(450, 150, 350, 140);
        add(pnlAppointments);
        
       
        pnlBeds = createCard(
                "Bed Occupancy",
                "78%",
                "156 of 200 beds",
                ColorsTheme.Blue);
        pnlBeds.setBounds(830, 150, 350, 140);
        add(pnlBeds);
        
        
        pnlRevenue = createCard(
                "Revenue (MTD)",
                "₱125K",
                "Target: $150K",
                ColorsTheme.Green);
        pnlRevenue.setBounds(1210, 150, 350, 140);
        add(pnlRevenue);
        
        pnlDistribution = new JPanel();
        pnlDistribution.setLayout(null);
        pnlDistribution.setBackground(ColorsTheme.Middle_Panel);
        pnlDistribution.setBounds(950, 0, 550, 500);
        pnlMiddle.add(pnlDistribution);

        lblDistributionTitle = new JLabel("Department Distribution");
        lblDistributionTitle.setFont(FontsTheme.Title_Texts);
        lblDistributionTitle.setForeground(ColorsTheme.Text_Black);
        lblDistributionTitle.setBounds(25, 20, 350, 35);
        pnlDistribution.add(lblDistributionTitle);
        
        barCardiology = createCustomProgressBar(
                25, 80, 450, 28,
                25,
                new Color(52, 152, 219));

        barOrthopedics = createCustomProgressBar(
                25, 140, 450, 28,
                22,
                new Color(46, 204, 113));

        barEmergency = createCustomProgressBar(
                25, 200, 450, 28,
                20,
                new Color(231, 76, 60));

        barNeurology = createCustomProgressBar(
                25, 260, 450, 28,
                18,
                new Color(155, 89, 182));

        barPediatrics = createCustomProgressBar(
                25, 320, 450, 28,
                15,
                new Color(241, 196, 15));

        
        
        pnlDistribution.add(barCardiology);
        pnlDistribution.add(barOrthopedics);
        pnlDistribution.add(barEmergency);
        pnlDistribution.add(barNeurology);
        pnlDistribution.add(barPediatrics);
        
        
        JPanel leg1 = createLegendItem(
                30, 390,
                "Cardiology",
                new Color(52, 152, 219));

        JPanel leg2 = createLegendItem(
                250, 390,
                "Orthopedics",
                new Color(46, 204, 113));

        JPanel leg3 = createLegendItem(
                30, 425,
                "Emergency",
                new Color(231, 76, 60));

        JPanel leg4 = createLegendItem(
                250, 425,
                "Neurology",
                new Color(155, 89, 182));

        JPanel leg5 = createLegendItem(
                30, 460,
                "Pediatrics",
                new Color(241, 196, 15));

        pnlDistribution.add(leg1);
        pnlDistribution.add(leg2);
        pnlDistribution.add(leg3);
        pnlDistribution.add(leg4);
        pnlDistribution.add(leg5);
        
        
        
        pnlImage = new JPanel();
        pnlImage.setLayout(null);
        pnlImage.setBounds(0,0,950,500);
        pnlImage.setBackground(ColorsTheme.Blue);
        pnlMiddle.add(pnlImage);
        
        JLabel lblIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/hospi.jpg")));
        lblIcon.setBounds(0,0,950,500);
        pnlImage.add(lblIcon);

       
        

    }

    
    public JPanel createCard(String title, String value, String subtitle, Color topLineColor) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(topLineColor);
        cardPanel.add(TopPanel);

        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblTitle);

        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(Color.BLACK);
        lblValue.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblValue);

        lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setBounds(20, 100, 250, 25);
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblSubtitle);


        return cardPanel;
        
        
            }
    
    
    private JProgressBar createCustomProgressBar(
        int x,
        int y,
        int width,
        int height,
        int value,
        Color filledColor) {

    JProgressBar bar = new JProgressBar(0, 100);

    bar.setBounds(x, y, width, height);
    bar.setValue(value);

    bar.setStringPainted(true);

    bar.setFont(new Font("Segoe UI", Font.BOLD, 13));

    bar.setForeground(filledColor);

    bar.setBackground(new Color(230, 230, 230));

    bar.setBorderPainted(false);

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
    
    private JPanel createMetricsCard(int y, String text, Color color,String icon)
    {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(20,y,350,70);
        
        JLabel title = new JLabel(text);
        title.setBounds(80,0,350,70);
        title.setFont(FontsTheme.Plain_Texts);
        title.setForeground(ColorsTheme.Text_Black);
        card.add(title);
        
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(color);
        iconPanel.setBounds(15, 12, 40, 40);
        card.add(iconPanel);
        
        JLabel lblIcon = new JLabel(icon, JLabel.CENTER);
        lblIcon.setBounds(0, 0, 40, 40);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcon.setVerticalAlignment(SwingConstants.CENTER);
        iconPanel.add(lblIcon);
        
        
        return card;
    }
    
    
    }


    
    

    