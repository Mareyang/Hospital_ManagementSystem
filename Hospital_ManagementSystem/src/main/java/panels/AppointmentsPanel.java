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
    private JButton btnSearch, btnRefresh, btnAdd, btnEdit, btnCancel, btnDelete, btnUpcoming, btnRecent, btnCancelled, btnComplete;
    private TablePanel tblAppointments;
    private static final String[] columns = {"Appt ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status"};
    private boolean canManageAppointments;
    private boolean canCompleteAppointments;
    private String currentTableFilter = "Upcoming";
    
    public AppointmentsPanel() {
        this(true);
    }

    public AppointmentsPanel(boolean canManageAppointments) {
        this(canManageAppointments, false);
    }

    public AppointmentsPanel(boolean canManageAppointments, boolean canCompleteAppointments) {
        this.canManageAppointments = canManageAppointments;
        this.canCompleteAppointments = canCompleteAppointments;

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
        if (canManageAppointments) {
            add(btnAdd);
        }

        // Appointment action buttons beside the table title
        btnEdit = new JButton("Edit");
        btnEdit.setBounds(1080, 10, 90, 40);
        btnEdit.setFont(FontsTheme.Buttons);
        btnEdit.setBackground(ColorsTheme.Search);
        btnEdit.setForeground(ColorsTheme.Text_White);
        btnEdit.setFocusPainted(false);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(1180, 10, 120, 40);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setBackground(ColorsTheme.Red);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setFocusPainted(false);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(1310, 10, 170, 40);
        btnDelete.setFont(FontsTheme.Buttons);
        btnDelete.setBackground(ColorsTheme.Delete_Urgent);
        btnDelete.setForeground(ColorsTheme.Text_White);
        btnDelete.setFocusPainted(false);

        btnUpcoming = createTableHeaderButton("Upcoming", ColorsTheme.Search);
        btnUpcoming.setBounds(300, 10, 130, 40);

        btnRecent = createTableHeaderButton("Recents", ColorsTheme.Green);
        btnRecent.setBounds(450, 10, 130, 40);

        btnCancelled = createTableHeaderButton("Cancelled", ColorsTheme.Text_Gray);
        btnCancelled.setBounds(600, 10, 150, 40);

        btnComplete = createTableHeaderButton("Complete", ColorsTheme.Green);
        btnComplete.setBounds(1250, 10, 230, 40);

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

        lblDetails = new JLabel(canManageAppointments ? "Manage and schedule patient appointments." : "View daily appointment schedule and status.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
     
        // Fetch Data & Build Table  
        Object[][] data = fetchAppointments("");
        tblAppointments = new TablePanel("Upcoming Visits", columns, data, 440);
        tblAppointments.setBounds(0, 0, 1500, 500);
        pnlMiddle.add(tblAppointments);
        configureAppointmentTable();

        
        
      
        //ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnUpcoming.addActionListener(this);
        btnRecent.addActionListener(this);
        btnCancelled.addActionListener(this);
        if (canManageAppointments) {
            btnEdit.addActionListener(this);
            btnCancel.addActionListener(this);
            btnDelete.addActionListener(this);
        }
        if (canCompleteAppointments) {
            btnComplete.addActionListener(this);
        }
        
    }
    
    private int getSelectedAppointmentId() {
        int row = tblAppointments.getTable().getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return -1;
        }

        String displayId = tblAppointments.getTable()
                .getValueAt(row, 0).toString();

        return Integer.parseInt(displayId.replace("APT-", ""));
    }

    private void editAppointment() {
        int id = getSelectedAppointmentId();
        if (id == -1) return;

        editAppointment(id);
    }

    private void editAppointment(int id) {
        NewAppointmentDialog dialog1 = new NewAppointmentDialog(id);
        dialog1.setVisible(true);

        updateTable(getCurrentTableTitle(), "");
    }

    private void cancelAppointment() {
        int id = getSelectedAppointmentId();
        if (id == -1) return;

        cancelAppointment(id);
    }

    private void cancelAppointment(int id) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Cancel this appointment?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "UPDATE appointments SET status = 'Cancelled' WHERE appt_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment cancelled successfully.");
                updateTable(getCurrentTableTitle(), "");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to cancel appointment:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void completeAppointment() {
        int row = tblAppointments.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return;
        }

        String displayId = tblAppointments.getTable().getValueAt(row, 0).toString();
        int id = Integer.parseInt(displayId.replace("APT-", ""));
        String status = tblAppointments.getTable().getValueAt(row, 6).toString();
        if ("Cancelled".equalsIgnoreCase(status)) {
            JOptionPane.showMessageDialog(this, "Cancelled appointments cannot be marked complete.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("Completed".equalsIgnoreCase(status)) {
            JOptionPane.showMessageDialog(this, "This appointment is already completed.", "Already Completed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Mark this appointment as completed?",
                "Confirm Completion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "UPDATE appointments SET status = 'Completed' WHERE appt_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment marked as completed.");
                currentTableFilter = "Recent";
                updateTable(getCurrentTableTitle(), "");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to complete appointment:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAppointment() {
        int id = getSelectedAppointmentId();
        if (id == -1) return;

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Permanently delete this appointment record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM appointments WHERE appt_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment record deleted successfully.");
                updateTable(getCurrentTableTitle(), "");
            } else {
                JOptionPane.showMessageDialog(this, "Appointment record not found.", "Delete Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete appointment:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchAppointments(searchKeyword);
        
        pnlMiddle.remove(tblAppointments);
        tblAppointments = new TablePanel(sectionTitle, columns, freshData, 440);
        tblAppointments.setBounds(0, 0, 1500, 500);
        pnlMiddle.add(tblAppointments);
        configureAppointmentTable();
        
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchAppointments(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        List<String> conditions = new ArrayList<>();
        List<String> parameters = new ArrayList<>();
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search appointments...");

        if ("Upcoming".equals(currentTableFilter)) {
            conditions.add("status NOT IN (?, ?)");
            parameters.add("Completed");
            parameters.add("Cancelled");
        } else if ("Recent".equals(currentTableFilter)) {
            conditions.add("status = ?");
            parameters.add("Completed");
        } else if ("Cancelled".equals(currentTableFilter)) {
            conditions.add("status = ?");
            parameters.add("Cancelled");
        }
        
        if (hasSearchFilter) {
            conditions.add("(patient_name LIKE ? OR doctor LIKE ? OR appt_id LIKE ?)");
            String cleanSearch = queryTerm.replace("APT-", "").replace("apt-", "");
            parameters.add("%" + queryTerm + "%");
            parameters.add("%" + queryTerm + "%");
            parameters.add("%" + cleanSearch + "%");
        }

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        sql += " ORDER BY appt_id ASC"; 

        // Variables to hold our dynamic counts
        int countTotal = 0;
        int countConfirmed = 0;
        int countPending = 0;
        int countUrgent = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < parameters.size(); i++) {
                statement.setString(i + 1, parameters.get(i));
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

                countTotal++;
                if ("Confirmed".equalsIgnoreCase(status)) countConfirmed++;
                if ("Pending".equalsIgnoreCase(status)) countPending++;
                if ("Emergency Visit".equalsIgnoreCase(visitType)) countUrgent++; 

                rowsList.add(new Object[]{displayId, name, doctor, dept, date, time, status});
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

    private void configureAppointmentTable() {
        JTable table = tblAppointments.getTable();
        table.setRowHeight(50);

        tblAppointments.add(btnUpcoming);
        tblAppointments.add(btnRecent);
        tblAppointments.add(btnCancelled);

        if (canManageAppointments) {
            tblAppointments.add(btnEdit);
            tblAppointments.add(btnCancel);
            tblAppointments.add(btnDelete);
        }
        if (canCompleteAppointments) {
            tblAppointments.add(btnComplete);
        }
    }

    private JButton createTableHeaderButton(String text, java.awt.Color color) {
        JButton button = new JButton(text);
        button.setFont(FontsTheme.Buttons);
        button.setBackground(color);
        button.setForeground(ColorsTheme.Text_White);
        button.setFocusPainted(false);
        return button;
    }

    private String getCurrentTableTitle() {
        if ("Recent".equals(currentTableFilter)) {
            return "Recent Appointments";
        }
        if ("Cancelled".equals(currentTableFilter)) {
            return "Cancelled Appointments";
        }
        return "Upcoming Visits";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewAppointmentDialog appointment = new NewAppointmentDialog();
            appointment.setVisible(true); 
            currentTableFilter = "Upcoming";
            updateTable(getCurrentTableTitle(), ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable(getCurrentTableTitle(), searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search appointments...");
            currentTableFilter = "Upcoming";
            updateTable(getCurrentTableTitle(), "");
        }
        else if (e.getSource() == btnUpcoming) {
            currentTableFilter = "Upcoming";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim());
        }
        else if (e.getSource() == btnRecent) {
            currentTableFilter = "Recent";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim());
        }
        else if (e.getSource() == btnCancelled) {
            currentTableFilter = "Cancelled";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim());
        }
        else if (e.getSource() == btnEdit) {
            editAppointment();
        }
        else if (e.getSource() == btnCancel) {
            cancelAppointment();
        }
        else if (e.getSource() == btnDelete) {
            deleteAppointment();
        }
        else if (e.getSource() == btnComplete) {
            completeAppointment();
        }
    }
}
