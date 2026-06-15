package panels;

import constants.TablePanel;
import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.NewAppointmentDialog;
import dialogs.EditAppointmentDialog;
import dialogs.ViewAppointmentDialog;
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
    private PanelCard pnlTotal, pnlScheduled, pnlCompleted, pnlCancelled;
    private JLabel lblDetails, lblAppointment;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnView, btnEdit, btnCancelAppt, btnCompleteAppt;
    private JButton btnFilterUpcoming, btnFilterCompleted, btnFilterCancelled;
    private TablePanel tblAppointments;
    
    private static final String[] columns = {"Appt ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status"};
    
    // Role-based booleans
    private boolean canManageAppointments; 
    private boolean canCompleteAppointments; 
    private String currentTableFilter = "Scheduled"; 
    
    
    
    public AppointmentsPanel() {
        this(false, false); 
    }

    public AppointmentsPanel(boolean canManageAppointments, boolean canCompleteAppointments) {
        this.canManageAppointments = canManageAppointments;
        this.canCompleteAppointments = canCompleteAppointments;

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
        
        // --- Initialize Buttons ---
        btnAdd = new JButton("Add");
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        
        btnView = new JButton("View");
        btnView.setFont(FontsTheme.Buttons);
        btnView.setBackground(ColorsTheme.View);
        btnView.setForeground(ColorsTheme.Text_White);
        btnView.setFocusPainted(false);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(FontsTheme.Buttons);
        btnEdit.setBackground(ColorsTheme.Edit);
        btnEdit.setForeground(ColorsTheme.Text_White);
        btnEdit.setFocusPainted(false);

        btnCancelAppt = new JButton("Cancel");
        btnCancelAppt.setFont(FontsTheme.Buttons);
        btnCancelAppt.setBackground(ColorsTheme.Delete);
        btnCancelAppt.setForeground(ColorsTheme.Text_White);
        btnCancelAppt.setFocusPainted(false);

        btnCompleteAppt = new JButton("Complete");
        btnCompleteAppt.setFont(FontsTheme.Buttons);
        btnCompleteAppt.setBackground(ColorsTheme.Add);
        btnCompleteAppt.setForeground(ColorsTheme.Text_White);
        btnCompleteAppt.setFocusPainted(false);

        // --- Role-Based Button Logic ---
        List<JButton> visibleButtons = new ArrayList<>();
        
        if (canManageAppointments) {
            visibleButtons.add(btnAdd); 
        }
        
        visibleButtons.add(btnView); 
        
        if (canManageAppointments) {
            visibleButtons.add(btnEdit); 
        }
        
        if (canManageAppointments || canCompleteAppointments) {
            visibleButtons.add(btnCancelAppt); 
        }
        
        if (canCompleteAppointments) {
            visibleButtons.add(btnCompleteAppt); 
        }

        int[] slots = {830, 995, 1160, 1325};
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
            visibleButtons.get(i).addActionListener(this);
        }

        btnFilterUpcoming = createFilterButton("Upcoming", ColorsTheme.Search);
        btnFilterCompleted = createFilterButton("Completed", ColorsTheme.Green);
        btnFilterCancelled = createFilterButton("Cancelled", ColorsTheme.Text_Gray);

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
        
        Object[][] data = fetchAppointments("");
        tblAppointments = new TablePanel("Upcoming Visits", columns, data, 440);
        tblAppointments.setBounds(0, 0, 1500, 500);
        pnlMiddle.add(tblAppointments);
        
        tblAppointments.add(btnFilterUpcoming);
        btnFilterUpcoming.setBounds(970, 10, 150, 40);
        tblAppointments.add(btnFilterCompleted);
        btnFilterCompleted.setBounds(1130, 10, 150, 40);
        tblAppointments.add(btnFilterCancelled);
        btnFilterCancelled.setBounds(1290, 10, 150, 40);

        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnFilterUpcoming.addActionListener(this);
        btnFilterCompleted.addActionListener(this);
        btnFilterCancelled.addActionListener(this);
    }
    
    private JButton createFilterButton(String text, java.awt.Color color) {
        JButton button = new JButton(text);
        button.setFont(FontsTheme.Buttons);
        button.setBackground(color);
        button.setForeground(ColorsTheme.Text_White);
        button.setFocusPainted(false);
        return button;
    }
    
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchAppointments(searchKeyword);
        
        pnlMiddle.remove(tblAppointments);
        tblAppointments = new TablePanel(sectionTitle, columns, freshData, 440);
        tblAppointments.setBounds(0, 0, 1500, 500);
        
        tblAppointments.add(btnFilterUpcoming);
        btnFilterUpcoming.setBounds(970, 10, 150, 40);
        tblAppointments.add(btnFilterCompleted);
        btnFilterCompleted.setBounds(1130, 10, 150, 40);
        tblAppointments.add(btnFilterCancelled);
        btnFilterCancelled.setBounds(1290, 10, 150, 40);

        pnlMiddle.add(tblAppointments);
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchAppointments(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        
        String sql = "SELECT a.appt_id, a.appointment_date, a.appointment_time, " +
                     "p.first_name AS pat_first, p.last_name AS pat_last, " +
                     "u.firstname AS doc_first, u.lastname AS doc_last, u.department, s.status_name " +
                     "FROM appointments a " +
                     "LEFT JOIN patients p ON a.patient_id = p.patient_id " +
                     "LEFT JOIN users u ON a.doctor_id = u.user_id " +
                     "LEFT JOIN appointment_status s ON a.status_id = s.status_id ";
        
        List<String> conditions = new ArrayList<>();
        List<String> parameters = new ArrayList<>();
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search appointments...");

        // FIX: We removed the Status Filter from the SQL so we can count EVERYTHING first!
        
        if (hasSearchFilter) {
            conditions.add("(p.first_name LIKE ? OR p.last_name LIKE ? OR u.lastname LIKE ? OR a.appt_id LIKE ?)");
            String cleanSearch = queryTerm.replace("APT-", "").replace("apt-", "");
            parameters.add("%" + queryTerm + "%");
            parameters.add("%" + queryTerm + "%");
            parameters.add("%" + queryTerm + "%");
            parameters.add("%" + cleanSearch + "%");
        }

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        sql += " ORDER BY a.appointment_date ASC, a.appointment_time ASC"; 

        int countTotal = 0;
        int countScheduled = 0;
        int countCompleted = 0;
        int countCancelled = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < parameters.size(); i++) {
                statement.setString(i + 1, parameters.get(i));
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("appt_id");
                String displayId = String.format("APT-%03d", rawId); 
                
                String patName = result.getString("pat_first") + " " + result.getString("pat_last");
                String docName = "Dr. " + result.getString("doc_last");
                String dept = result.getString("department");
                String date = result.getString("appointment_date");
                String status = result.getString("status_name");
                
                // AM/PM CONVERSION
                String displayTime = "N/A";
                java.sql.Time dbTime = result.getTime("appointment_time");
                if (dbTime != null) {
                    java.time.LocalTime localTime = dbTime.toLocalTime();
                    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm a");
                    displayTime = localTime.format(formatter);
                }

                // 1. COUNT EVERYTHING FOR THE CARDS
                countTotal++;
                if ("Scheduled".equalsIgnoreCase(status)) countScheduled++;
                if ("Completed".equalsIgnoreCase(status)) countCompleted++;
                if ("Cancelled".equalsIgnoreCase(status)) countCancelled++; 
                if ("No Show".equalsIgnoreCase(status)) countCancelled++; // Usually grouped with cancelled in metrics

                // 2. ONLY ADD TO THE TABLE IF IT MATCHES THE CURRENT TAB
                boolean shouldAddToTable = false;
                if ("Scheduled".equals(currentTableFilter) && "Scheduled".equalsIgnoreCase(status)) {
                    shouldAddToTable = true;
                } else if ("Completed".equals(currentTableFilter) && "Completed".equalsIgnoreCase(status)) {
                    shouldAddToTable = true;
                } else if ("Cancelled".equals(currentTableFilter) && ("Cancelled".equalsIgnoreCase(status) || "No Show".equalsIgnoreCase(status))) {
                    shouldAddToTable = true;
                }

                if (shouldAddToTable) {
                    rowsList.add(new Object[]{displayId, patName, docName, dept, date, displayTime, status});
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load appointments from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countTotal, countScheduled, countCompleted, countCancelled);
        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int total, int scheduled, int completed, int cancelled) {
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlScheduled != null) remove(pnlScheduled);
        if (pnlCompleted != null) remove(pnlCompleted);
        if (pnlCancelled != null) remove(pnlCancelled);

        pnlTotal = new PanelCard("Total in View", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlScheduled = new PanelCard("Scheduled", String.valueOf(scheduled), ColorsTheme.Yellow);
        pnlScheduled.setBounds(450, 130, 350, 110);
        add(pnlScheduled);
        
        pnlCompleted = new PanelCard("Completed", String.valueOf(completed), ColorsTheme.Green);
        pnlCompleted.setBounds(830, 130, 350, 110);
        add(pnlCompleted);
        
        pnlCancelled = new PanelCard("Cancelled", String.valueOf(cancelled), ColorsTheme.Red);
        pnlCancelled.setBounds(1210, 130, 350, 110);
        add(pnlCancelled);

        repaint();
        revalidate();
    }

    private int getSelectedAppointmentId() {
        int row = tblAppointments.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return -1;
        }
        String displayId = tblAppointments.getTable().getValueAt(row, 0).toString();
        return Integer.parseInt(displayId.replace("APT-", ""));
    }

    private void changeAppointmentStatus(int apptId, int newStatusId, String successMessage) {
        String sql = "UPDATE appointments SET status_id = ? WHERE appt_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, newStatusId);
            statement.setInt(2, apptId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, successMessage);
                updateTable(getCurrentTableTitle(), txtSearch.getText().trim().equals("Search appointments...") ? "" : txtSearch.getText().trim());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update appointment:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getCurrentTableTitle() {
        if ("Completed".equals(currentTableFilter)) return "Completed Appointments";
        if ("Cancelled".equals(currentTableFilter)) return "Cancelled Appointments";
        return "Upcoming Visits";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewAppointmentDialog appointment = new NewAppointmentDialog();
            appointment.setVisible(true); 
            currentTableFilter = "Scheduled";
            updateTable(getCurrentTableTitle(), ""); 
        }
        else if (e.getSource() == btnView) {
            int id = getSelectedAppointmentId();
            if (id != -1) {
                ViewAppointmentDialog dialog = new ViewAppointmentDialog(id);
                dialog.setVisible(true);
            }
        }
        else if (e.getSource() == btnEdit) {
            int row = tblAppointments.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment first.");
                return;
            }
            
            // Check the status (Column 6) before allowing the edit!
            String status = tblAppointments.getTable().getValueAt(row, 6).toString();
            if (status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("Completed") || status.equalsIgnoreCase("No Show")) {
                JOptionPane.showMessageDialog(this, "You cannot edit an appointment that is already " + status + ".\nPlease book a new appointment.", "Edit Restricted", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = getSelectedAppointmentId();
            if (id != -1) {
                EditAppointmentDialog dialog = new EditAppointmentDialog(id);
                dialog.setVisible(true);
                updateTable(getCurrentTableTitle(), txtSearch.getText().trim().equals("Search appointments...") ? "" : txtSearch.getText().trim());
            }
        }
        else if (e.getSource() == btnCancelAppt) {
            int row = tblAppointments.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment first.");
                return;
            }
            
            String status = tblAppointments.getTable().getValueAt(row, 6).toString();
            if (status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("Completed")) {
                JOptionPane.showMessageDialog(this, "This appointment is already " + status + ".", "Action Denied", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = getSelectedAppointmentId();
            if (id != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Cancel this appointment?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    changeAppointmentStatus(id, 3, "Appointment cancelled successfully."); 
                }
            }
        }
        else if (e.getSource() == btnCompleteAppt) {
            int row = tblAppointments.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment first.");
                return;
            }
            
            String status = tblAppointments.getTable().getValueAt(row, 6).toString();
            if (status.equalsIgnoreCase("Completed")) {
                JOptionPane.showMessageDialog(this, "This appointment is already completed.", "Action Denied", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("No Show")) {
                JOptionPane.showMessageDialog(this, "You cannot complete a cancelled appointment.", "Action Denied", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = getSelectedAppointmentId();
            if (id != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Mark this appointment as completed?", "Confirm Completion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    changeAppointmentStatus(id, 2, "Appointment marked as completed."); 
                }
            }
        }
        else if (e.getSource() == btnSearch) {
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim());
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search appointments...");
            currentTableFilter = "Scheduled";
            updateTable(getCurrentTableTitle(), "");
        }
        else if (e.getSource() == btnFilterUpcoming) {
            currentTableFilter = "Scheduled";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim().equals("Search appointments...") ? "" : txtSearch.getText().trim());
        }
        else if (e.getSource() == btnFilterCompleted) {
            currentTableFilter = "Completed";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim().equals("Search appointments...") ? "" : txtSearch.getText().trim());
        }
        else if (e.getSource() == btnFilterCancelled) {
            currentTableFilter = "Cancelled";
            updateTable(getCurrentTableTitle(), txtSearch.getText().trim().equals("Search appointments...") ? "" : txtSearch.getText().trim());
        }
    }
    }
