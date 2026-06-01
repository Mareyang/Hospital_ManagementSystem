/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constants;

/**
 *
 * @author Arabella
 */
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import javax.swing.*;

public class PanelCard extends JPanel {

    private JPanel pnlTop;
    private JLabel lblTitle, lblValue;

    public PanelCard(String title, String value, Color topColor) {
        setLayout(null);
        setBackground(ColorsTheme.Main_Card);

        
        //Top Color Bar
        pnlTop = new JPanel();
        pnlTop.setBounds(0, 0, 350, 10);
        pnlTop.setBackground(topColor);
        add(pnlTop);

        
        //Card Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        add(lblTitle);

        
        //Card Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        add(lblValue);
    }
}