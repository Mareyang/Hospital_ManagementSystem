/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constants;

/**
 *
 * @author Arabella
 * 
 * 
 */

import controls.HospitalDashboard;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    public static JButton createButton(String text, String path, int y, JPanel SidePanel)
    {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, y, 220, 50);
        panel1.setBackground(ColorsTheme.Side_Panel);

        JLabel iconLbl = new JLabel(new ImageIcon(HospitalDashboard.class.getResource(path)));
        iconLbl.setBounds(18, 9, 32, 32);
        panel1.add(iconLbl);

        JButton btnClick = new JButton(text);
        btnClick.setBounds(60, 0, 150, 50);

        ButtonStyles.sidebarButton(btnClick);

        panel1.add(btnClick);

        SidePanel.add(panel1);

        return btnClick;
}
}