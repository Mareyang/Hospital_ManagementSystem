/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import dialogs.NewLabDialog;
/**
 *
 * @author Arabella
 */
public class LaboratoryPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlProcessing, pnlCompleted, pnlStats, pnlCard, pnlTop;
    private JLabel lblDetails, lblAppointment, lblTitle, lblValue, lblHead;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable tblLab;
    private JScrollPane scrollTable;
   // private ImagePanel imgPatient;
    
    
    public LaboratoryPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        btnAdd = new JButton("+  New Lab Order");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
        //Search Bar
        txtSearch = new JTextField("Search patient name, patient ID, or test type...");
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
        
        
        

        lblAppointment = new JLabel("Laboratory");
        lblAppointment.setBounds(30, 30, 500, 40);
        lblAppointment.setFont(FontsTheme.Bold_Texts);
        lblAppointment.setForeground(ColorsTheme.Text_Black);
        add(lblAppointment);

        lblDetails = new JLabel("Manage laboratory tests and results.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        pnlPending = createCard("Pending", "10", ColorsTheme.Yellow);
        pnlPending.setBounds(70, 130, 350, 110);
        add(pnlPending);
        
        
        pnlProcessing = createCard("Processing", "12", ColorsTheme.Blue);
        pnlProcessing.setBounds(450, 130, 350, 110);
        add(pnlProcessing);
        
       
        pnlCompleted = createCard("Completed", "45", ColorsTheme.Green);
        pnlCompleted.setBounds(830, 130, 350, 110);
        add(pnlCompleted);
        
        
        pnlStats = createCard("STAT Orders", "5", ColorsTheme.Red);
        pnlStats.setBounds(1210, 130, 350, 110);
        add(pnlStats);
        
        
        
        //Table
        String[] columns = {"Patient Name", "Patient ID", "Test Type", "Priority", "Status", "Requested Date", "Actions"};
        Object[][] data = {
                {"Maria Leonora", "000021", "Complete Blood Count", "Routine", "Pending", "May 12, 2026", " "},
                {"Jose Felipe", "000054", "Chest X-Ray", "STAT", "Processing", "May 12, 2026", " "},
                {"Angela Cruz", "000078", "Urinalysis", "Routine", "Completed", "May 11, 2026", " "},
                {"Nathaniel Ong", "000142", "Blood Chemistry", "Routine", "Pending", "May 11, 2026", " "},
                {"Miguel Santos", "000205", "ECG", "STAT", "Processing", "May 10, 2026", "View"},
                {"Ella Villanueva", "000219", "Pregnancy Test", "Routine", "Completed", "May 10, 2026", " "},
                {"Sophia Reyes", "000115", "Lipid Profile", "Routine", "Completed", "May 09, 2026", " "},
                {"Daniel Garcia", "000126", "Fasting Blood Sugar", "Routine", "Pending", "May 09, 2026", " "}
        };
        
        tblLab = new JTable(data, columns);
        tblLab.setRowHeight(50);
        tblLab.setDefaultEditor(Object.class, null);
        tblLab.getTableHeader().setReorderingAllowed(false);
        tblLab.getTableHeader().setFont(FontsTheme.Title_Texts);
        tblLab.setFont(FontsTheme.Info_Texts);
        tblLab.getTableHeader().setBackground(ColorsTheme.Header); 
        tblLab.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollTable = new JScrollPane(tblLab);
        scrollTable.setBounds(0, 60, 1500, 620);
        pnlMiddle.add(scrollTable);
        
        lblHead = new JLabel("Recent Lab Reports");
        lblHead.setBounds(30, 20, 300, 30);
        lblHead.setFont(FontsTheme.Title_Texts);
        lblHead.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblHead);
        
        
    }

    
    public JPanel createCard(String title, String value, Color topColor) {

        pnlCard = new JPanel();
        pnlCard.setLayout(null);
        pnlCard.setBackground(ColorsTheme.Main_Card);
        
        pnlTop = new JPanel();
        pnlTop.setBounds(0, 0, 350, 10);
        pnlTop.setLayout(null);
        pnlTop.setBackground(topColor);
        pnlCard.add(pnlTop);
        

        //Title
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
        pnlCard.add(lblTitle);


        //Value
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(ColorsTheme.Text_Black);
        lblValue.setFont(FontsTheme.Bold_Texts);
        pnlCard.add(lblValue);
        
        
        return pnlCard;
        
        
            }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd) {
            NewLabDialog lab = new NewLabDialog();
            lab.setVisible(true);
        }

        }
    }

