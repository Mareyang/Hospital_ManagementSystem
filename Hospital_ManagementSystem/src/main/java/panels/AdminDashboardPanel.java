package panels;

import constants.PanelCard2;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardPanel extends JPanel {
    
    private JPanel pnlPatients, pnlAppointments, pnlInventory, pnlRevenue, pnlMiddle;
    private JLabel lblGreet, lblDescrip, lblDistributionTitle, lblOverviewTitle;
    
    // Distribution & Progress bars
    private JPanel pnlDistribution, pnlOverview;
    private JProgressBar barCardiology, barOrthopedics, barEmergency, barNeurology, barPediatrics;
    
    
    public AdminDashboardPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        // Middle Panel Container 
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 350, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Middle_Panel); 
        add(pnlMiddle);

        // Greeting section
        lblGreet = new JLabel("Welcome to CareLink, Admin!");
        lblGreet.setBounds(30, 30, 1000, 40);
        lblGreet.setForeground(ColorsTheme.Text_Black);
        lblGreet.setFont(FontsTheme.Bold_Texts);
        add(lblGreet);
        
        lblDescrip = new JLabel("Here's what's happening at the hospital today.");
        lblDescrip.setBounds(30, 70, 500, 40);
        lblDescrip.setForeground(ColorsTheme.Text_Gray);
        lblDescrip.setFont(FontsTheme.Plain_Texts);
        add(lblDescrip);
        
        // Summary Panel Cards
        pnlPatients = new PanelCard2("Total Patients", "2,847", "Active Records", ColorsTheme.Yellow);
        pnlPatients.setBounds(70, 150, 350, 140);
        add(pnlPatients);
        
        pnlAppointments = new PanelCard2("Today's Appointments", "156", "32 remaining", ColorsTheme.Orange);
        pnlAppointments.setBounds(450, 150, 350, 140);
        add(pnlAppointments);
        
        pnlInventory = new PanelCard2("Pharmacy Inventory", "12 Low Items", "3 Out of Stock", ColorsTheme.Blue);
        pnlInventory.setBounds(830, 150, 350, 140);
        add(pnlInventory);
        
        pnlRevenue = new PanelCard2("Revenue (MTD)", "₱125K", "Target: ₱150K", ColorsTheme.Green);
        pnlRevenue.setBounds(1210, 150, 350, 140);
        add(pnlRevenue);
        
        
        
        pnlDistribution = new JPanel();
        pnlDistribution.setLayout(null);
        pnlDistribution.setBackground(ColorsTheme.Main_Card); 
        pnlDistribution.setBounds(960, 0, 520, 380); 
        pnlMiddle.add(pnlDistribution);

        lblDistributionTitle = new JLabel("Department Distribution");
        lblDistributionTitle.setFont(FontsTheme.Title_Texts);
        lblDistributionTitle.setForeground(ColorsTheme.Text_Black);
        lblDistributionTitle.setBounds(32, 20, 350, 35);
        pnlDistribution.add(lblDistributionTitle);
        
        // Progress Bars
        barCardiology = createCustomProgressBar(32, 80, 450, 28, 25, ColorsTheme.Cardiology_Color);
        barOrthopedics = createCustomProgressBar(32, 140, 450, 28, 22, ColorsTheme.Orthophedics_Color);
        barEmergency = createCustomProgressBar(32, 200, 450, 28, 20, ColorsTheme.Emergency_Color);
        barNeurology = createCustomProgressBar(32, 260, 450, 28, 18, ColorsTheme.Neurology_Color);
        barPediatrics = createCustomProgressBar(32, 320, 450, 28, 15, ColorsTheme.Pediatrics_Color);

        pnlDistribution.add(barCardiology);
        pnlDistribution.add(barOrthopedics);
        pnlDistribution.add(barEmergency);
        pnlDistribution.add(barNeurology);
        pnlDistribution.add(barPediatrics);
        
        // Legend Items
        pnlMiddle.add(createLegendItem(1060, 400, "Cardiology", ColorsTheme.Cardiology_Color));
        pnlMiddle.add(createLegendItem(1250, 400, "Orthopedics", ColorsTheme.Orthophedics_Color));
        pnlMiddle.add(createLegendItem(1060, 430, "Emergency", ColorsTheme.Emergency_Color));
        pnlMiddle.add(createLegendItem(1250, 430, "Neurology", ColorsTheme.Neurology_Color));
        pnlMiddle.add(createLegendItem(1060, 460, "Pediatrics", ColorsTheme.Pediatrics_Color));
        
       
        
        pnlOverview = new JPanel();
        pnlOverview.setLayout(null);
        pnlOverview.setBounds(0, 0, 920, 510); 
        pnlOverview.setBackground(ColorsTheme.Main_Card); 
        pnlMiddle.add(pnlOverview);
        
        lblOverviewTitle = new JLabel("Recent System & Security Logs");
        lblOverviewTitle.setFont(FontsTheme.Title_Texts);
        lblOverviewTitle.setForeground(ColorsTheme.Text_Black);
        lblOverviewTitle.setBounds(30, 20, 500, 35);
        pnlOverview.add(lblOverviewTitle);
        
        
    }

    private JProgressBar createCustomProgressBar(int x, int y, int width, int height, int value, Color filledColor) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setBounds(x, y, width, height);
        bar.setValue(value);
        bar.setStringPainted(true);
        bar.setFont(FontsTheme.Progress_Bar_Font);
        bar.setForeground(filledColor);
        bar.setBackground(ColorsTheme.Middle_Panel); 
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
}