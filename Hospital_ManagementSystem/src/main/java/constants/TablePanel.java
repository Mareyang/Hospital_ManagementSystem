/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constants;


import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.*;
import java.awt.Color;


public class TablePanel extends JPanel {

    private JTable tblTable;
    private JScrollPane scrollPane;
    private JLabel lblTitle;

    public TablePanel(String title, String[] columns, Object[][] data, int scrollHeight) {
        setLayout(null);
        setBackground(ColorsTheme.Main_Card);

        
        //Table Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(30, 20, 600, 30);
        lblTitle.setFont(FontsTheme.Title_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        //Creates the table using columns and data
        tblTable = new JTable(data, columns);

        
        //Table design and settings
        tblTable.setRowHeight(SystemSettings.tableRowHeight);
        tblTable.setFont(FontsTheme.Info_Texts);
        tblTable.setDefaultEditor(Object.class, null);
        tblTable.getTableHeader().setReorderingAllowed(false);

        // Dynamic dark mode styling
        tblTable.setBackground(ColorsTheme.Main_Card);
        tblTable.setForeground(ColorsTheme.Text_Black);
        tblTable.setGridColor(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY);
        tblTable.setSelectionBackground(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
        tblTable.setSelectionForeground(ColorsTheme.Text_Black);

        // Cell renderer to guarantee dynamic styling
        javax.swing.table.DefaultTableCellRenderer cellRenderer = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                return c;
            }
        };
        for (int i = 0; i < tblTable.getColumnCount(); i++) {
            tblTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //Table header design
        tblTable.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblTable.getTableHeader().setBackground(ColorsTheme.isDarkMode ? Color.decode("#2E2E38") : ColorsTheme.Header);
        tblTable.getTableHeader().setForeground(ColorsTheme.Text_White);

        //ScrollPane for viewing long table records
        scrollPane = new JScrollPane(tblTable);
        scrollPane.setBounds(0, 60, 1500, scrollHeight);
        scrollPane.getViewport().setBackground(ColorsTheme.Main_Card);
        scrollPane.setBorder(BorderFactory.createLineBorder(ColorsTheme.isDarkMode ? Color.decode("#334155") : Color.LIGHT_GRAY));
        add(scrollPane);
    }

    public JTable getTable() {
        return tblTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}