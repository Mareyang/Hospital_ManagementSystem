/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constants;


import constants.ColorsTheme;
import constants.FontsTheme;
import javax.swing.*;


public class TablePanel extends JPanel {

    private JTable tblTable;
    private JScrollPane scrollPane;
    private JLabel lblTitle;

    public TablePanel(String title, String[] columns, Object[][] data, int scrollHeight) {
        setLayout(null);
        setBackground(ColorsTheme.Main_Card);

        lblTitle = new JLabel(title);
        lblTitle.setBounds(30, 20, 600, 30);
        lblTitle.setFont(FontsTheme.Title_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        tblTable = new JTable(data, columns);

        tblTable.setRowHeight(50);
        tblTable.setFont(FontsTheme.Info_Texts);
        tblTable.setDefaultEditor(Object.class, null);
        tblTable.getTableHeader().setReorderingAllowed(false);

        tblTable.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblTable.getTableHeader().setBackground(ColorsTheme.Header);
        tblTable.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollPane = new JScrollPane(tblTable);
        scrollPane.setBounds(0, 60, 1500, scrollHeight);
        add(scrollPane);
    }

    public JTable getTable() {
        return tblTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}