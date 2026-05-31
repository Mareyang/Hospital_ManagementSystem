/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class NurseDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlBeds, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    //Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview, pnlImage, pnlMetrics;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    
    
    
    public NurseDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        //Middle Panel Container
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);

        //Greeting section
        lblGreet = new JLabel("Welcome to CareLink, Nurse!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        
        //Summary Panel Cards
        pnlPatients = new PanelCard2(
                "Total Patients",
                "2,847",
                "Active Records",
                ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 150, 350, 140);
        add(pnlPatients);
        
        pnlAppointments = new PanelCard2(
                "Today's Appointments",
                "156",
                "32 remaining",
                ColorsTheme.Orange);
        pnlAppointments.setBounds(450, 150, 350, 140);
        add(pnlAppointments);
        
        pnlBeds = new PanelCard2(
                "Bed Occupancy",
                "78%",
                "156 of 200 beds",
                ColorsTheme.Blue);
        pnlBeds.setBounds(830, 150, 350, 140);
        add(pnlBeds);
        
        pnlRevenue = new PanelCard2(
                "Revenue (MTD)",
                "₱125K",
                "Target: $150K",
                ColorsTheme.Green);
        pnlRevenue.setBounds(1210, 150, 350, 140);
        add(pnlRevenue);
        
        
        
        //Department Distribution Panel
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
        
        
        //Progress Bars
        barCardiology = createCustomProgressBar(25, 80, 450, 28, 25, ColorsTheme.Cardiology_Color);
        barOrthopedics = createCustomProgressBar(25, 140, 450, 28, 22, ColorsTheme.Orthophedics_Color);
        barEmergency = createCustomProgressBar(25, 200, 450, 28, 20, ColorsTheme.Emergency_Color);
        barNeurology = createCustomProgressBar(25, 260, 450, 28, 18, ColorsTheme.Neurology_Color);
        barPediatrics = createCustomProgressBar(25, 320, 450, 28, 15, ColorsTheme.Pediatrics_Color);

        pnlDistribution.add(barCardiology);
        pnlDistribution.add(barOrthopedics);
        pnlDistribution.add(barEmergency);
        pnlDistribution.add(barNeurology);
        pnlDistribution.add(barPediatrics);
        
        
        //Legend Items
        JPanel leg1 = createLegendItem(30, 390, "Cardiology", ColorsTheme.Cardiology_Color);
        JPanel leg2 = createLegendItem(250, 390, "Orthopedics", ColorsTheme.Orthophedics_Color);
        JPanel leg3 = createLegendItem(30, 425, "Emergency", ColorsTheme.Emergency_Color);
        JPanel leg4 = createLegendItem(250, 425, "Neurology", ColorsTheme.Neurology_Color);
        JPanel leg5 = createLegendItem(30, 460, "Pediatrics", ColorsTheme.Pediatrics_Color);

        pnlDistribution.add(leg1);
        pnlDistribution.add(leg2);
        pnlDistribution.add(leg3);
        pnlDistribution.add(leg4);
        pnlDistribution.add(leg5);
        
        
        //Background Image Panel
        pnlImage = new JPanel();
        pnlImage.setLayout(null);
        pnlImage.setBounds(0, 0, 950, 500);
        pnlImage.setBackground(ColorsTheme.Blue);
        pnlMiddle.add(pnlImage);
        
        JLabel lblIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/hospi.png")));
        lblIcon.setBounds(0, 0, 950, 500);
        pnlImage.add(lblIcon);
    }

     //Creates a customized progress bar for department distribution.
    private JProgressBar createCustomProgressBar(int x, int y, int width, int height, int value, Color filledColor) {
        
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setBounds(x, y, width, height);
        bar.setValue(value);
        bar.setStringPainted(true);
        bar.setFont(FontsTheme.Progress_Bar_Font);
        bar.setForeground(filledColor);
        bar.setBackground(ColorsTheme.Main_Card);
        bar.setBorderPainted(false);

        return bar;
    }

    //Creates a legend item (color box + label) for the distribution chart.
    private JPanel createLegendItem(int x, int y, String text, Color color) {
        JPanel item = new JPanel();
        item.setLayout(null);
        item.setOpaque(false);
        item.setBounds(x, y, 300, 28);

        //Color indicator square
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