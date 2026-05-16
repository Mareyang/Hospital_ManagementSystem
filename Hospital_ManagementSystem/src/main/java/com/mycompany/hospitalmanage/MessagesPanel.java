/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

<<<<<<< HEAD
<<<<<<< HEAD:Hospital_ManagementSystem/src/main/java/panels/MessagesPanel.java
import constants.ColorsTheme;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.FontsTheme;
=======
import com.mycompany.hospitalmanage.ColorsTheme;
import com.mycompany.hospitalmanage.FontsTheme;
>>>>>>> parent of 720ed23 (meow):Hospital_ManagementSystem/src/main/java/com/mycompany/hospitalmanage/MessagesPanel.java
=======
import com.mycompany.hospitalmanage.ColorsTheme;
import com.mycompany.hospitalmanage.FontsTheme;
>>>>>>> parent of 720ed23 (meow)
import javax.swing.*;

/**
 *
 * @author Arabella
 */
public class MessagesPanel extends JPanel {
    
    private JPanel pnlMiddle;
    private JLabel lblDetails, lblMessage;
    private JButton btnAdd;
    
    
    public MessagesPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 130, 1500, 750);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        
        lblMessage = new JLabel("Messages");
        lblMessage.setBounds(30, 30, 500, 40);
        lblMessage.setFont(FontsTheme.Bold_Texts);
        lblMessage.setForeground(ColorsTheme.Text_Black);
        add(lblMessage);

        lblDetails = new JLabel("Internal messaging system");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        btnAdd = new JButton("+  New Messages");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        
        
        
        
    }   
}
