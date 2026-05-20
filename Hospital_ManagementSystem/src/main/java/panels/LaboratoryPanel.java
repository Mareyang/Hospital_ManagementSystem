/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import dialogs.NewLabDialog;
/**
 *
 * 
 */
public class LaboratoryPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlProcessing, pnlCompleted, pnlStats;
    private JLabel lblDetails, lblAppointment;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblLab;
    
    
    public LaboratoryPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        //Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        //Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        //Button for adding new lab
        btnAdd = new JButton("+  New Lab Order");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        //Search Bar including search and refresh buttons
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
        
        
        //Title and subtitle label for lab section
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
        
        
        //Summary Panel Cards
        pnlPending = new PanelCard("Pending", "10", ColorsTheme.Yellow);
        pnlPending.setBounds(70, 130, 350, 110);
        add(pnlPending);
        
        pnlProcessing = new PanelCard("Processing", "12", ColorsTheme.Blue);
        pnlProcessing.setBounds(450, 130, 350, 110);
        add(pnlProcessing);
       
        pnlCompleted = new PanelCard("Completed", "45", ColorsTheme.Green);
        pnlCompleted.setBounds(830, 130, 350, 110);
        add(pnlCompleted);
        
        pnlStats = new PanelCard("STAT Orders", "5", ColorsTheme.Red);
        pnlStats.setBounds(1210, 130, 350, 110);
        add(pnlStats);
        
        
        
        //Table column names
        String[] columns = {"Patient Name", "Patient ID", "Test Type", "Priority", "Status", "Requested Date", "Actions"};
        
        //Sample records
        Object[][] data = {
                {"Maria Leonora", "000021", "Complete Blood Count", "Routine", "Pending", "May 12, 2026", " "},
                {"Jose Felipe", "000054", "Chest X-Ray", "STAT", "Processing", "May 12, 2026", " "},
                {"Angela Cruz", "000078", "Urinalysis", "Routine", "Completed", "May 11, 2026", " "},
                {"Nathaniel Ong", "000142", "Blood Chemistry", "Routine", "Pending", "May 11, 2026", " "},
                {"Miguel Santos", "000205", "ECG", "STAT", "Processing", "May 10, 2026", " "},
                {"Ella Villanueva", "000219", "Pregnancy Test", "Routine", "Completed", "May 10, 2026", " "},
                {"Sophia Reyes", "000115", "Lipid Profile", "Routine", "Completed", "May 09, 2026", " "},
                {"Daniel Garcia", "000126", "Fasting Blood Sugar", "Routine", "Pending", "May 09, 2026", " "}
        };
        
        
        //Table Panel for records
        tblLab = new TablePanel("Recent Lab Reports", columns, data, 560);
        tblLab.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblLab);
        
        
        
        //ActionListener
        btnAdd.addActionListener(this);
        
    }

    
   
    


    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens lab form dialog
        if(e.getSource() == btnAdd) {
            NewLabDialog lab = new NewLabDialog();
            lab.setVisible(true);
        }

        }
    }

