package panels;

import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.NewStaffDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffManagementPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlTotal, pnlOn, pnlOff, pnlLeave;
    private TablePanel tblEmployee;
    private JLabel lblDetails, lblStaff;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private static final String[] columns = {"Staff ID", "Name", "Role", "Department", "Status", "Patient", "Actions"};
    
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
        btnAdd.setFocusPainted(false);
        add(btnAdd);
        
        //Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search by staff name or ID...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        btnSearch.setFocusPainted(false);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        btnRefresh.setFocusPainted(false);
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
        
        
        // Initial fetch to build table and metric cards
        Object[][] data = fetchStaff("");
        tblEmployee = new TablePanel("Employee Records", columns, data, 440);
        tblEmployee.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblEmployee);
        
        
        
        //ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    
    
    
    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchStaff(searchKeyword);
        pnlMiddle.remove(tblEmployee);
        
        tblEmployee = new TablePanel(sectionTitle, columns, freshData, 440);
        tblEmployee.setBounds(0, 0, 1500, 560);
        
        pnlMiddle.add(tblEmployee);
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchStaff(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM hospital_staff";

        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by staff name or ID...");

        if (hasSearchFilter) {
            sql += " WHERE full_name LIKE ? OR employee_id LIKE ?";
        }
        sql += " ORDER BY employee_id ASC"; 

        int countTotal = 0;
        int countOnDuty = 0;
        int countOffDuty = 0;
        int countOnLeave = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            if (hasSearchFilter) {
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String empID = result.getString("employee_id");
                String name = result.getString("full_name");
                String role = result.getString("role");
                String dept = result.getString("department");
                String status = result.getString("status");
                String patient = "None"; // Assuming this is linked elsewhere
                String actions = ""; 

                countTotal++;
                if ("Active".equalsIgnoreCase(status)) countOnDuty++;
                else if ("Off Duty".equalsIgnoreCase(status)) countOffDuty++;
                else if ("On Leave".equalsIgnoreCase(status)) countOnLeave++;

                rowsList.add(new Object[]{empID, name, role, dept, status, patient, actions});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load staff records:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countTotal, countOnDuty, countOffDuty, countOnLeave);
        return rowsList.toArray(new Object[0][]);
}

    private void refreshSummaryCards(int total, int on, int off, int leave) {
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlOn != null) remove(pnlOn);
        if (pnlOff != null) remove(pnlOff);
        if (pnlLeave != null) remove(pnlLeave);

        pnlTotal = new PanelCard("Total Staff", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlOn = new PanelCard("On Duty", String.valueOf(on), ColorsTheme.Green);
        pnlOn.setBounds(450, 130, 350, 110);
        add(pnlOn);
       
        pnlOff = new PanelCard("Off Duty", String.valueOf(off), ColorsTheme.Yellow);
        pnlOff.setBounds(830, 130, 350, 110);
        add(pnlOff);
        
        pnlLeave = new PanelCard("On Leave", String.valueOf(leave), ColorsTheme.Red);
        pnlLeave.setBounds(1210, 130, 350, 110);
        add(pnlLeave);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewStaffDialog dialog = new NewStaffDialog();
            dialog.setVisible(true);
            updateTable("Employee Records", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by staff name or ID...");
            updateTable("Employee Records", "");
        }
    }
}