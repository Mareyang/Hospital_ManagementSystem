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
    
    public static JButton createButton(String text, String path, int y, JPanel pnlSide)
    {
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(null);
        pnlButton.setBounds(20, y, 220, 50);
        pnlButton.setBackground(ColorsTheme.Side_Panel);

        JLabel lblIcon = new JLabel(new ImageIcon(ButtonStyles.class.getResource(path)));
        lblIcon.setBounds(18, 9, 32, 32);
        pnlButton.add(lblIcon);

        JButton btnClick = new JButton(text);
        btnClick.setBounds(60, 0, 150, 50);
        ButtonStyles.sidebarButton(btnClick);

        pnlButton.add(btnClick);
        pnlSide.add(pnlButton);

        return btnClick;
}
}