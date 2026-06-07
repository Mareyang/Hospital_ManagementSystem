package panels;

import constants.TablePanel;
import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.NewAppointmentDialog;
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

public class AppointmentsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlTotal, pnlConfirm, pnlPending, pnlUrgent;
    private JLabel lblDetails, lblAppointment, lblNoResults;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblAppointments;
    private static final String[] columns = {"Appt ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status", "Actions"};
    
    public AppointmentsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        // Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 500);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        // Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        // Button for adding new appointment
        btnAdd = new JButton("+  New Appointment");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        add(btnAdd);
        
        // Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search appointments...");
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
                
        // Title and subtitle label for appointments section
        lblAppointment = new JLabel("Appointments");
        lblAppointment.setBounds(30, 30, 500, 40);
        lblAppointment.setFont(FontsTheme.Bold_Texts);
        lblAppointment.setForeground(ColorsTheme.Text_Black);
        add(lblAppointment);

        lblDetails = new JLabel("Manage and schedule patient appointments.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
     
        // Fetch Data & Build Table  
        Object[][] data = fetchAppointments("");
        tblAppointments = new TablePanel("Upcoming Visits", columns, data, 440);
        tblAppointments.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblAppointments);

        
        
      
        //ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    
    
    
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchAppointments(searchKeyword);
        
        pnlMiddle.remove(tblAppointments);
        tblAppointments = new TablePanel(sectionTitle, columns, freshData, 440);
        tblAppointments.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblAppointments);
        
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchAppointments(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search appointments...");
        
        if (hasSearchFilter) {
            sql += " WHERE patient_name LIKE ? OR doctor LIKE ? OR appt_id LIKE ?";
        }
            sql += " ORDER BY appt_id ASC"; 

        // Variables to hold our dynamic counts
        int countTotal = 0;
        int countConfirmed = 0;
        int countPending = 0;
        int countUrgent = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.replace("APT-", "").replace("apt-", "");
                
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("appt_id");
                String displayId = String.format("APT-%03d", rawId); 
                
                String name = result.getString("patient_name");
                String doctor = result.getString("doctor");
                String dept = result.getString("department");
                String date = result.getString("appointment_date");
                String time = result.getString("appointment_time");
                String status = result.getString("status");
                String visitType = result.getString("visit_type");
                String actions = ""; 

                countTotal++;
                if ("Confirmed".equalsIgnoreCase(status)) countConfirmed++;
                if ("Pending".equalsIgnoreCase(status)) countPending++;
                if ("Emergency Visit".equalsIgnoreCase(visitType)) countUrgent++; 

                rowsList.add(new Object[]{displayId, name, doctor, dept, date, time, status, actions});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load appointments from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countTotal, countConfirmed, countPending, countUrgent);

        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int total, int confirmed, int pending, int urgent) {
        
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlConfirm != null) remove(pnlConfirm);
        if (pnlPending != null) remove(pnlPending);
        if (pnlUrgent != null) remove(pnlUrgent);

        pnlTotal = new PanelCard("Total Found", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlConfirm = new PanelCard("Confirmed", String.valueOf(confirmed), ColorsTheme.Green);
        pnlConfirm.setBounds(450, 130, 350, 110);
        add(pnlConfirm);
        
        pnlPending = new PanelCard("Pending", String.valueOf(pending), ColorsTheme.Yellow);
        pnlPending.setBounds(830, 130, 350, 110);
        add(pnlPending);
        
        pnlUrgent = new PanelCard("Urgent (ER)", String.valueOf(urgent), ColorsTheme.Red);
        pnlUrgent.setBounds(1210, 130, 350, 110);
        add(pnlUrgent);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewAppointmentDialog appointment = new NewAppointmentDialog();
            appointment.setVisible(true); 
            updateTable("Upcoming Visits", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search appointments...");
            updateTable("Upcoming Visits", "");
        }
    }
}