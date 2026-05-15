/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanage;

/**
 *
 * @author Arabella
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusColor extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String status = String.valueOf(value);

        cell.setForeground(ColorsTheme.Text_Black);

        
        if (status.equals("Blue")) {
            cell.setForeground(ColorsTheme.Blue);
        }

        else if (status.equals("Yellow")) {
            cell.setForeground(ColorsTheme.Yellow);
        }

        else if (status.equals("Red")) {
            cell.setForeground(ColorsTheme.Red);
        }

        else if (status.equals("Green")) {
            cell.setForeground(ColorsTheme.Green);
        }

        return cell;
    }
}