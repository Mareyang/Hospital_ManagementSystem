/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.ColorsTheme;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class BedManagementPanel extends JPanel {
    
    private JPanel pnlTotal, pnlOccupied, pnlAvail, pnlMaintenance, cardPanel, TopPanel;
    private JPanel pnlICU, pnlWardA, pnlOR, pnlMaternity, pnlER, pnlPriv;
    private JPanel icu1, icu2, icu3;
    private JPanel ward1, ward2, ward3;
    private JPanel priv1, priv2, priv3;
    private JPanel or1, or2, or3;
    private JPanel mat1, mat2, mat3;
    private JPanel er1, er2, er3;
    
    private JLabel Licu1;
    private JLabel lblDetails, lblBed, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
   // private ImagePanel imgPatient;
    
    
    public BedManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        lblBed = new JLabel("Bed Management");
        lblBed.setBounds(30, 30, 500, 40);
        lblBed.setFont(FontsTheme.Bold_Texts);
        lblBed.setForeground(ColorsTheme.Text_Black);
        add(lblBed);

        lblDetails = new JLabel("Monitor and manage bed availability");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlTotal = createCard(
                "Total Beds",
                "200");
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        
        pnlOccupied = createCard(
                "Occupied",
                "150");
        pnlOccupied.setBounds(450, 130, 350, 110);
        add(pnlOccupied);
        
       
        pnlAvail = createCard(
                "Available",
                "43");
        pnlAvail.setBounds(830, 130, 350, 110);
        add(pnlAvail);
        
        
        pnlMaintenance = createCard(
                "Maintenance",
                "7");
        pnlMaintenance.setBounds(1210, 130, 350, 110);
        add(pnlMaintenance);
        
        //ROOMS
        
        pnlICU = roomCard (
                "Intensive Care Unit",
                "Floor 2");
        pnlICU.setBounds(70, 290, 480, 300);
        pnlICU.setLayout(null);
        
        
        JPanel icu1 = new JPanel();
        icu1.setBounds(20, 120, 135, 80);
//        JLabel Licu1 = new JLabel("Available", SwingConstants.CENTER);
//        Licu1.setBounds(15, 130, 130, 100);
//        icu1.add(Licu1);
         //icu1.setBackground(Color.red);
        
        JPanel icu2 = new JPanel();
        icu2.setBounds(170, 120, 135, 80);
        JPanel icu3 = new JPanel();
        icu3.setBounds(320, 120, 135, 80);        
        add(pnlICU);
        pnlICU.add(icu1);
        pnlICU.add(icu2);
        pnlICU.add(icu3);
       
       
        
        pnlWardA = roomCard (
                "Ward Area",
                "Floor 1");
        pnlWardA.setBounds(575, 290, 480, 300);
        
        JPanel ward1 = new JPanel();
        ward1.setBounds(20, 120, 135, 80);
        JPanel ward2 = new JPanel();
        ward2.setBounds(170, 120, 135, 80);
        JPanel ward3 = new JPanel();
        ward3.setBounds(320, 120, 135, 80);  
        add(pnlWardA);
        pnlWardA.add(ward1);
        pnlWardA.add(ward2);
        pnlWardA.add(ward3);
        
        
        pnlPriv = roomCard (
                "Private Rooms",
                "Floor 2");
        pnlPriv.setBounds(1080, 290, 480, 300);
        JPanel priv1 = new JPanel();
        priv1.setBounds(20, 120, 135, 80);
        JPanel priv2 = new JPanel();
        priv2.setBounds(170, 120, 135, 80);
        JPanel priv3 = new JPanel();
        priv3.setBounds(320, 120, 135, 80);  
        add(pnlPriv);
        pnlPriv.add(priv1);
        pnlPriv.add(priv2);
        pnlPriv.add(priv3);
        
        
        pnlOR = roomCard (
                "Operating Room",
                "Floor 2");
        pnlOR.setBounds(70, 620, 480, 300);
        JPanel or1 = new JPanel();
        or1.setBounds(20, 120, 135, 80);
        JPanel or2 = new JPanel();
        or2.setBounds(170, 120, 135, 80);
        JPanel or3 = new JPanel();
        or3.setBounds(320, 120, 135, 80);  
        add(pnlOR);
        pnlOR.add(or1);
        pnlOR.add(or2);
        pnlOR.add(or3);
        
      
        pnlMaternity = roomCard (
                "Delivery Room",
                "Floor 1");
        pnlMaternity.setBounds(575, 620, 480, 300);
        JPanel mat1 = new JPanel();
        mat1.setBounds(20, 120, 135, 80);
        JPanel mat2 = new JPanel();
        mat2.setBounds(170, 120, 135, 80);
        JPanel mat3 = new JPanel();
        mat3.setBounds(320, 120, 135, 80);  
        add(pnlMaternity);
        pnlMaternity.add(mat1);
        pnlMaternity.add(mat2);
        pnlMaternity.add(mat3);
        
        

        pnlER = roomCard (
                "Emergency Room",
                "Floor 1");
        pnlER.setBounds(1080, 620, 480, 300);
        JPanel er1 = new JPanel();
        er1.setBounds(20, 120, 135, 80);
        JPanel er2 = new JPanel();
        er2.setBounds(170, 120, 135, 80);
        JPanel er3 = new JPanel();
        er3.setBounds(320, 120, 135, 80);  
        add(pnlER);
        pnlER.add(er1);
        pnlER.add(er2);
        pnlER.add(er3);
        
       
        
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
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 350, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);
            
          //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 350, 30);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        cardPanel.add(lblTitle);
           
          //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Plain_Texts);
        cardPanel.add(lblValue);
        
        
        
        return cardPanel;
    
    }
}
    
//    public JPanel roomStats (String title, String value) {
//        
//         //Title
//        lblTitle = new JLabel(title);
//        lblTitle.setBounds(15, 25, 130, 100);
//        lblTitle.setForeground(ColorsTheme.Text_Black);
//        lblTitle.setFont(FontsTheme.Bold_Texts);
//        cardPanel.add(lblTitle);
//           
//          //Value
//        lblValue = new JLabel(value);
//        lblValue.setBounds(15, 45, 130, 100);
//        lblValue.setForeground(ColorsTheme.Text_Black);
//        lblValue.setFont(FontsTheme.Plain_Texts);
//        cardPanel.add(lblValue);
//        
//         return cardPanel;
    

