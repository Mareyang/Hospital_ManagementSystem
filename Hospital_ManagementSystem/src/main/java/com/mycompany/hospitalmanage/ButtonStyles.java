/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<< HEAD
<<<<<<<< HEAD:Hospital_ManagementSystem/src/main/java/constants/ButtonStyles.java
package constants;
========
package com.mycompany.hospitalmanage;
>>>>>>>> parent of 720ed23 (meow):Hospital_ManagementSystem/src/main/java/com/mycompany/hospitalmanage/ButtonStyles.java
=======
package com.mycompany.hospitalmanage;
>>>>>>> parent of 720ed23 (meow)

/**
 *
 * @author Arabella
 * 
 * 
 */

import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class ButtonStyles {

    public static void sidebarButton(JButton button) {

        button.setFont(FontsTheme.Buttons);
        button.setForeground(ColorsTheme.Text_White);
        button.setBackground(ColorsTheme.Side_Panel);

        button.setBorder(null);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 10, 0, 0));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}