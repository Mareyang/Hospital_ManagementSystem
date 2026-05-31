/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;


import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.NewStaffDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * 
 */
public class StaffManagementPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlOn, pnlOff, pnlLeave;
    private TablePanel tblEmployee;
    private JLabel lblDetails, lblStaff;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
   
    
    
    public StaffManagementPanel() {
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
        
        //Button for adding new staff
        btnAdd = new JButton("+  Add Staff");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        add(btnAdd);
        
        
        //Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search by patient name or patient id...");
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
        
        
        //Title and subtitle label for staff section
        lblStaff = new JLabel("Staff Management");
        lblStaff.setBounds(30, 30, 500, 40);
        lblStaff.setFont(FontsTheme.Bold_Texts);
        lblStaff.setForeground(ColorsTheme.Text_Black);
        add(lblStaff);

        lblDetails = new JLabel("Manage hospital staffs and schedules.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Summary Panel Cards
        pnlTotal = new PanelCard("Total Staff", "245", ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlOn = new PanelCard("On Duty", "180", ColorsTheme.Green);
        pnlOn.setBounds(450, 130, 350, 110);
        add(pnlOn);
       
        pnlOff = new PanelCard("Off Duty", "52", ColorsTheme.Yellow);
        pnlOff.setBounds(830, 130, 350, 110);
        add(pnlOff);
        
        pnlLeave = new PanelCard("On Leave", "13", ColorsTheme.Red);
        pnlLeave.setBounds(1210, 130, 350, 110);
        add(pnlLeave);
        
        
        
        //Table column names
        String[] columns = {"Staff ID", "Name", "Role", "Department", "Status", "Patient", "Actions"};
        
        //Sample records
        Object[][] data = {
            
          };
        

        
        //Table Panel for records
        tblEmployee = new TablePanel("Employee Records", columns, data, 440);
        tblEmployee.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblEmployee);
        
        
        //ActionListener
        btnAdd.addActionListener(this);
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Opens staff form dialog
        if (btnAdd == e.getSource()) {
            NewStaffDialog dialog = new NewStaffDialog();
            dialog.setVisible(true);
        }
    }
}
    
    


