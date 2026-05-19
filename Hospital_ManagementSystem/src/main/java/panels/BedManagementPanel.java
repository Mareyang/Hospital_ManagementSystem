/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * 
 */
public class BedManagementPanel extends JPanel {
    
    private JPanel pnlTotal, pnlOccupied, pnlAvail, pnlMaintenance, cardPanel, TopPanel;
    private JPanel pnlICU, pnlWardA, pnlMaternity, pnlSearch, pnlRefresh;
    private JPanel icu1, icu2, icu3;
    private JPanel ward1, ward2, ward3;
    private JPanel mat1, mat2, mat3;
    
    private JLabel Licu1, lblWardsOccuNum;
    private JLabel lblMatAvail, lblMatAvailNum, lblMatOccu, lblMatOccuNum, lblMatMain, lblMatMainNum, lblMatLabel;
    private JLabel lblicuAvail, lblicuAvailNum, lblicuOccu, lblicuOccuNum, lblicuMain, lblicuMainNum, lblICULabel;
    private JLabel lblWardAvail, lblWardAvailNum, lblWardsOccu, lblWardOccuNum, lblWardMain, lblWardMainNum, lblWardLabel;

    private JLabel lblDetails, lblBed, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
    private JProgressBar ProgMat, ProgICU, ProgWard;
   // private ImagePanel imgPatient;
    
    
    public BedManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        lblBed = new JLabel("Bed Management");
        lblBed.setBounds(30, 30, 500, 40);
        lblBed.setFont(FontsTheme.Bold_Texts);
        lblBed.setForeground(ColorsTheme.Text_Black);
        add(lblBed);

        lblDetails = new JLabel("Monitor and manage bed availability.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        pnlTotal = new PanelCard("Total Beds", "200", ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlOccupied = new PanelCard("Occupied", "150", ColorsTheme.Yellow);
        pnlOccupied.setBounds(450, 130, 350, 110);
        add(pnlOccupied);

        pnlAvail = new PanelCard("Available", "43", ColorsTheme.Green);
        pnlAvail.setBounds(830, 130, 350, 110);
        add(pnlAvail);
        
        pnlMaintenance = new PanelCard("Maintenance", "7", ColorsTheme.Red);
        pnlMaintenance.setBounds(1210, 130, 350, 110);
        add(pnlMaintenance);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //Search Bar
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
        
   
        //ICU
   
        pnlICU = roomCard ("Intensive Care Unit", "Floor 2");
        pnlICU.setBounds(70, 390, 420, 240);
        pnlICU.setLayout(null);
        
        icu1 = new JPanel();
        icu1.setBounds(25, 95, 110, 60);
        icu1.setLayout(null);
        
        lblicuAvail = new JLabel("Available");
        lblicuAvail.setBounds(25, 8, 100, 20);
        lblicuAvail.setFont(FontsTheme.Dialog_Texts);
        lblicuAvail.setForeground(ColorsTheme.Text_Black);
        icu1.add(lblicuAvail);
        
        lblicuAvailNum = new JLabel("2");
        lblicuAvailNum.setBounds(48, 25, 50, 30);
        lblicuAvailNum.setFont(FontsTheme.Title_Texts);
        lblicuAvailNum.setForeground(ColorsTheme.Text_Black);
        icu1.add(lblicuAvailNum);

        icu2 = new JPanel();
        icu2.setBounds(150, 95, 110, 60);
        icu2.setLayout(null);
        
        
        lblicuOccu = new JLabel("Occupied");
        lblicuOccu.setBounds(25, 8, 100, 20);
        lblicuOccu.setFont(FontsTheme.Dialog_Texts);
        lblicuOccu.setForeground(ColorsTheme.Text_Black);
        icu2.add(lblicuOccu);
        
        lblicuOccuNum = new JLabel("18");
        lblicuOccuNum.setBounds(45, 25, 50, 30);
        lblicuOccuNum.setFont(FontsTheme.Title_Texts);
        lblicuOccuNum.setForeground(ColorsTheme.Text_Black);
        icu2.add(lblicuOccuNum);
       

        icu3 = new JPanel();
        icu3.setBounds(280, 95, 110, 60);
        icu3.setLayout(null);
        
        lblicuMain = new JLabel("Maintenance");
        lblicuMain.setBounds(18, 8, 100, 20);
        lblicuMain.setFont(FontsTheme.Dialog_Texts);
        lblicuMain.setForeground(ColorsTheme.Text_Black);
        icu3.add(lblicuMain);
        
        lblicuMainNum = new JLabel("18");
        lblicuMainNum.setBounds(45, 25, 50, 30);
        lblicuMainNum.setFont(FontsTheme.Title_Texts);
        lblicuMainNum.setForeground(ColorsTheme.Text_Black);
        icu3.add(lblicuMainNum);
      
        ProgICU = new JProgressBar();
        ProgICU.setBounds(20,170,370,10);
        ProgICU.setValue(90);
        
        lblICULabel = new JLabel("15 out of 20 beds occupied");
        lblICULabel.setBounds(120, 140, 180, 120);
        lblICULabel.setFont(FontsTheme.Dialog_Texts);
        lblICULabel.setForeground(ColorsTheme.Text_Black);
        pnlICU.add(lblICULabel);

        pnlICU.add(icu1);
        pnlICU.add(icu2);
        pnlICU.add(icu3);
        pnlICU.add(ProgICU);
        add(pnlICU);
       
        // WARD AREA
        
        pnlWardA = roomCard ("Ward Area","Floor 1");
        pnlWardA.setBounds(600, 390, 420, 240);
        
        ward1 = new JPanel();
        ward1.setBounds(25, 95, 110, 60);
        ward1.setLayout(null);
        
        lblWardAvail = new JLabel("Available");
        lblWardAvail.setBounds(25, 8, 100, 20);
        lblWardAvail.setFont(FontsTheme.Dialog_Texts);
        lblWardAvail.setForeground(ColorsTheme.Text_Black);
        ward1.add(lblWardAvail);
        
        lblWardAvailNum = new JLabel("2");
        lblWardAvailNum.setBounds(48, 25, 50, 30);
        lblWardAvailNum.setFont(FontsTheme.Title_Texts);
        lblWardAvailNum.setForeground(ColorsTheme.Text_Black);
        ward1.add(lblWardAvailNum);

        ward2 = new JPanel();
        ward2.setBounds(150, 95, 110, 60);
        ward2.setLayout(null);
        
        
        lblWardsOccu = new JLabel("Occupied");
        lblWardsOccu.setBounds(25, 8, 100, 20);
        lblWardsOccu.setFont(FontsTheme.Dialog_Texts);
        lblWardsOccu.setForeground(ColorsTheme.Text_Black);
        ward2.add(lblWardsOccu);
        
        lblWardsOccuNum = new JLabel("18");
        lblWardsOccuNum.setBounds(45, 25, 50, 30);
        lblWardsOccuNum.setFont(FontsTheme.Title_Texts);
        lblWardsOccuNum.setForeground(ColorsTheme.Text_Black);
        ward2.add(lblWardsOccuNum);
       

        ward3 = new JPanel();
        ward3.setBounds(280, 95, 110, 60);
        ward3.setLayout(null);
        
        lblWardMain = new JLabel("Maintenance");
        lblWardMain.setBounds(18, 8, 100, 20);
        lblWardMain.setFont(FontsTheme.Dialog_Texts);
        lblWardMain.setForeground(ColorsTheme.Text_Black);
        ward3.add(lblWardMain);
        
        lblWardMainNum = new JLabel("18");
        lblWardMainNum.setBounds(45, 25, 50, 30);
        lblWardMainNum.setFont(FontsTheme.Title_Texts);
        lblWardMainNum.setForeground(ColorsTheme.Text_Black);
        ward3.add(lblWardMainNum);
      
        ProgWard = new JProgressBar();
        ProgWard.setBounds(20,170,370,10);
        ProgWard.setValue(90);
        
        lblWardLabel = new JLabel("15 out of 20 beds occupied");
        lblWardLabel.setBounds(120, 140, 180, 120);
        lblWardLabel.setFont(FontsTheme.Dialog_Texts);
        lblWardLabel.setForeground(ColorsTheme.Text_Black);
        pnlWardA.add(lblWardLabel);

        pnlWardA.add(ward1);
        pnlWardA.add(ward2);
        pnlWardA.add(ward3);
        pnlWardA.add(ProgWard);
        add(pnlWardA);
       
              
     // MATERNITY 

        pnlMaternity = roomCard("Maternity Rooms", "Floor 2");
        pnlMaternity.setBounds(1135, 390, 420, 240);

        mat1 = new JPanel();
        mat1.setBounds(25, 95, 110, 60);
        mat1.setLayout(null);
        
        lblMatAvail = new JLabel("Available");
        lblMatAvail.setBounds(25, 8, 100, 20);
        lblMatAvail.setFont(FontsTheme.Dialog_Texts);
        lblMatAvail.setForeground(ColorsTheme.Text_Black);
        mat1.add(lblMatAvail);
        
        lblMatAvailNum = new JLabel("2");
        lblMatAvailNum.setBounds(48, 24, 50, 30);
        lblMatAvailNum.setFont(FontsTheme.Title_Texts);
        lblMatAvailNum.setForeground(ColorsTheme.Text_Black);
        mat1.add(lblMatAvailNum);

        mat2 = new JPanel();
        mat2.setBounds(150, 95, 110, 60);
        mat2.setLayout(null);
        
        
        lblMatOccu = new JLabel("Occupied");
        lblMatOccu.setBounds(25, 8, 100, 20);
        lblMatOccu.setFont(FontsTheme.Dialog_Texts);
        lblMatOccu.setForeground(ColorsTheme.Text_Black);
        mat2.add(lblMatOccu);
        
        lblMatOccuNum = new JLabel("18");
        lblMatOccuNum.setBounds(48, 24, 50, 30);
        lblMatOccuNum.setFont(FontsTheme.Title_Texts);
        lblMatOccuNum.setForeground(ColorsTheme.Text_Black);
        mat2.add(lblMatOccuNum);
       

        mat3 = new JPanel();
        mat3.setBounds(280, 95, 110, 60);
        mat3.setLayout(null);
        
        lblMatMain = new JLabel("Maintenance");
        lblMatMain.setBounds(18, 8, 100, 20);
        lblMatMain.setFont(FontsTheme.Dialog_Texts);
        lblMatMain.setForeground(ColorsTheme.Text_Black);
        mat3.add(lblMatMain);
        
        lblMatMainNum = new JLabel("18");
        lblMatMainNum.setBounds(48, 24, 50, 30);
        lblMatMainNum.setFont(FontsTheme.Title_Texts);
        lblMatMainNum.setForeground(ColorsTheme.Text_Black);
        mat3.add(lblMatMainNum);
      
        ProgMat = new JProgressBar();
        ProgMat.setBounds(20,170,370,10);
        ProgMat.setValue(90);
        
        lblMatLabel = new JLabel("15 out of 20 beds occupied");
        lblMatLabel.setBounds(120, 140, 180, 120);
        lblMatLabel.setFont(FontsTheme.Dialog_Texts);
        lblMatLabel.setForeground(ColorsTheme.Text_Black);
        pnlMaternity.add(lblMatLabel);

        pnlMaternity.add(mat1);
        pnlMaternity.add(mat2);
        pnlMaternity.add(mat3);
        pnlMaternity.add(ProgMat);
        add(pnlMaternity);
 
       
        
    }

        
        
    public JPanel roomCard (String title, String value) {
            
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 500, 10);
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