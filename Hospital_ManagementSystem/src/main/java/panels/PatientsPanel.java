package panels;

import dialogs.AddPatientDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.PanelCard;
import constants.TablePanel;
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

public class PatientsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch, pnlTotal, pnlAdmitted, pnlDischarged, pnlObservation;
    private TablePanel tblPatient;
    private JLabel lblPatient, lblDetails;
    private JTextField txtSearch;
    private JButton btnAdd, btnSearch, btnRefresh, btnView, btnEdit, btnDelete;
    private  static final String[] columns = {"Patient ID", "Patient Name" , "Age / Gender", "Contact", "Room", "Status"};
    private boolean canManagePatients;
    
    public PatientsPanel() {
        this(false);
    }

    public PatientsPanel(boolean canManagePatients) {
        this.canManagePatients = canManagePatients;

        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        // Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 380, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        // Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 270, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        // Button for adding new patient
        btnAdd = new JButton("+ Add");
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);

        btnView = new JButton("View");
        btnView.setFont(FontsTheme.Buttons);
        btnView.setBackground(ColorsTheme.Header);
        btnView.setForeground(ColorsTheme.Text_White);
        btnView.setFocusPainted(false);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(FontsTheme.Buttons);
        btnEdit.setBackground(ColorsTheme.Update_Pending);
        btnEdit.setForeground(ColorsTheme.Text_Black);
        btnEdit.setFocusPainted(false);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(FontsTheme.Buttons);
        btnDelete.setBackground(ColorsTheme.Delete_Urgent);
        btnDelete.setForeground(ColorsTheme.Text_White);
        btnDelete.setFocusPainted(false);
        
        // Dynamically align visible buttons to the right-hand side
        java.util.List<JButton> visibleButtons = new java.util.ArrayList<>();
        if (canManagePatients) {
            visibleButtons.add(btnAdd);
        }
        visibleButtons.add(btnView);
        if (canManagePatients) {
            visibleButtons.add(btnEdit);
            visibleButtons.add(btnDelete);
        }

        int[] slots = {830, 995, 1160, 1325};
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
        }
        
        // Search Bar including search and refresh buttons
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
        btnSearch.setFocusPainted(false);
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
        btnRefresh.setFocusPainted(false);
        pnlSearch.add(btnRefresh);
        
        // Title and subtitle label for patient section
        lblPatient = new JLabel("Patient Management");
        lblPatient.setBounds(30, 30, 500, 40);
        lblPatient.setFont(FontsTheme.Bold_Texts);
        lblPatient.setForeground(ColorsTheme.Text_Black);
        add(lblPatient);

        lblDetails = new JLabel(canManagePatients ? "Manage patient records and information." : "View patient records and information.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        // Fetch data and build table
        Object[][] data = fetchPatients("");
        tblPatient = new TablePanel("Recent Admissions", columns, data, 440);
        tblPatient.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPatient);
        configurePatientTable();

        
        
        // ActionListener
        if (canManagePatients) {
            btnAdd.addActionListener(this);
            btnEdit.addActionListener(this);
            btnDelete.addActionListener(this);
        }
        btnView.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }

    
    
    
    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPatients(searchKeyword);
        
        pnlMiddle.remove(tblPatient);
        tblPatient = new TablePanel(sectionTitle, columns, freshData, 440);
        tblPatient.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblPatient);
        configurePatientTable();
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchPatients(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search patients...");
        
        if (hasSearchFilter) {
            // We search by ID, first name, or last name
            sql += " WHERE patient_id LIKE ? OR first_name LIKE ? OR last_name LIKE ?";
        }
            sql += " ORDER BY patient_id ASC"; 

        // Variables to hold our dynamic counts
        int countTotal = 0;
        int countAdmitted = 0;
        int countDischarged = 0;
        int countObservation = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                
                String cleanSearch = queryTerm.replace("PAT-", "").replace("pat-", "");
                
                statement.setString(1, "%" + cleanSearch + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + queryTerm + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("patient_id");
                String displayId = String.format("PAT-%03d", rawId); 
                
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String fullName = firstName + " " + lastName;
                
                String ageAndgender = result.getString("age") + " / " + result.getString("gender");
                String contact = result.getString("contact_number");
                String room = result.getString("room_number");
                String status = result.getString("status");
                countTotal++;
                if ("Admitted".equalsIgnoreCase(status)) countAdmitted++;
                if ("Discharged".equalsIgnoreCase(status)) countDischarged++;
                if ("Observation".equalsIgnoreCase(status)) countObservation++; 

                rowsList.add(new Object[]{displayId, fullName, ageAndgender, contact, room, status});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patients from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        
        refreshSummaryCards(countTotal, countAdmitted, countDischarged, countObservation);

        return rowsList.toArray(new Object[0][]);
    }
    
    private void refreshSummaryCards(int total, int admitted, int discharged, int observation) {
        
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlAdmitted != null) remove(pnlAdmitted);
        if (pnlDischarged != null) remove(pnlDischarged);
        if (pnlObservation != null) remove(pnlObservation);

        pnlTotal = new PanelCard("Total Patients", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlAdmitted = new PanelCard("Admitted", String.valueOf(admitted), ColorsTheme.Green);
        pnlAdmitted.setBounds(450, 130, 350, 110);
        add(pnlAdmitted);
        
        pnlDischarged = new PanelCard("Discharged", String.valueOf(discharged), ColorsTheme.Yellow);
        pnlDischarged.setBounds(830, 130, 350, 110);
        add(pnlDischarged);
        
        pnlObservation = new PanelCard("Observation", String.valueOf(observation), ColorsTheme.Red);
        pnlObservation.setBounds(1210, 130, 350, 110);
        add(pnlObservation);

        repaint();
        revalidate();
    }

    private void configurePatientTable() {
        JTable table = tblPatient.getTable();
        table.setRowHeight(50);
    }

    private int getSelectedPatientId() {
        int row = tblPatient.getTable().getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a patient first.");
            return -1;
        }

        String displayId = tblPatient.getTable().getValueAt(row, 0).toString();
        return Integer.parseInt(displayId.replace("PAT-", ""));
    }

    private void viewPatient() {
        int id = getSelectedPatientId();
        if (id == -1) return;

        AddPatientDialog dialog = new AddPatientDialog(id, true);
        dialog.setVisible(true);
    }

    private void editPatient() {
        int id = getSelectedPatientId();
        if (id == -1) return;

        AddPatientDialog dialog = new AddPatientDialog(id, false);
        dialog.setVisible(true);
        updateTable("Recent Admissions", "");
    }

    private void deletePatient() {
        int id = getSelectedPatientId();
        if (id == -1) return;

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Permanently delete this patient record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Patient record deleted successfully.");
                updateTable("Recent Admissions", "");
            } else {
                JOptionPane.showMessageDialog(this, "Patient record not found.", "Delete Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete patient:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            AddPatientDialog patient = new AddPatientDialog();
            patient.setVisible(true); 
            updateTable("Recent Admissions", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or patient id...");
            updateTable("Recent Admissions", "");
        }
        else if (e.getSource() == btnView) {
            viewPatient();
        }
        else if (e.getSource() == btnEdit) {
            editPatient();
        }
        else if (e.getSource() == btnDelete) {
            deletePatient();
        }
    }
}