package panels;

import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.NewPatientDialog;
import dialogs.EditPatientDialog;
import dialogs.ViewPatientDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;




public class PatientsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlTotal, pnlOutpatient, pnlAdmitted, pnlDischarged;
    private TablePanel tblPatient;
    private JLabel lblDetails, lblPatient;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnView, btnEdit;
    private static final String[] columns = {"Patient ID", "Patient Name" , "Age / Gender", "Contact", "Email", "Status"};
    
    private boolean canManagePatients;
    
    // Default constructor defaults to View-Only mode
    public PatientsPanel() {
        this(false);
    }

    // Main constructor with Role-Based Access Control
    public PatientsPanel(boolean canManagePatients) {
        this.canManagePatients = canManagePatients;
        
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
        
        // Button declarations (Not added to screen yet)
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

        
        
        // SMART LOGIC: Only add specific buttons if they have permission
        List<JButton> visibleButtons = new ArrayList<>();
        
        if (canManagePatients) {
            visibleButtons.add(btnAdd);
        }
        
        visibleButtons.add(btnView); // Everyone gets the View button
        
        if (canManagePatients) {
            visibleButtons.add(btnEdit);
            
        }

        // Dynamically space out whatever buttons are in the list so they align right
        int[] slots = {830, 995, 1160, 1325};
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
            
            // Attach action listeners to the active buttons
            visibleButtons.get(i).addActionListener(this); 
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

        lblDetails = new JLabel(canManagePatients ? "Manage patient admissions, records, and registration." : "View patient admissions and records.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        // Initial fetch to build table and metric cards
        Object[][] data = fetchPatients("");
        tblPatient = new TablePanel("Recent Admissions", columns, data, 440);
        tblPatient.setBounds(0, 0, 1500, 500);
        pnlMiddle.add(tblPatient);
        
        // ActionListeners for search tools
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPatients(searchKeyword);
        pnlMiddle.remove(tblPatient);
        
        tblPatient = new TablePanel(sectionTitle, columns, freshData, 440);
        tblPatient.setBounds(0, 0, 1500, 500);
        
        pnlMiddle.add(tblPatient);
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchPatients(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT p.*, s.status_name FROM patients p " +
                     "LEFT JOIN patient_status s ON p.status_id = s.status_id";

        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or patient id...");

        if (hasSearchFilter) {
            sql += " WHERE p.patient_id LIKE ? OR p.first_name LIKE ? OR p.last_name LIKE ?";
        }
        sql += " ORDER BY p.patient_id ASC"; 

        int countTotal = 0;
        int countOutpatient = 0;
        int countAdmitted = 0;
        int countDischarged = 0;

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
                
                int age = 0;
                java.sql.Date bday = result.getDate("birthday");
                if (bday != null) {
                    LocalDate birthLocalDate = bday.toLocalDate();
                    age = Period.between(birthLocalDate, LocalDate.now()).getYears();
                }
                
                String ageAndGender = age + " / " + result.getString("gender");
                String contact = result.getString("contact_number");
                String email = result.getString("email");
                String status = result.getString("status_name");
                
                if (status == null) status = "Unknown";

                countTotal++;
                if ("Outpatient".equalsIgnoreCase(status)) countOutpatient++;
                else if ("Admitted".equalsIgnoreCase(status)) countAdmitted++;
                else if ("Discharged".equalsIgnoreCase(status)) countDischarged++;

                rowsList.add(new Object[]{displayId, fullName, ageAndGender, contact, email, status});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patient records:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countTotal, countOutpatient, countAdmitted, countDischarged);
        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int total, int outpatient, int admitted, int discharged) {
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlOutpatient != null) remove(pnlOutpatient);
        if (pnlAdmitted != null) remove(pnlAdmitted);
        if (pnlDischarged != null) remove(pnlDischarged);

        pnlTotal = new PanelCard("Total Patients", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlOutpatient = new PanelCard("Outpatients", String.valueOf(outpatient), ColorsTheme.Yellow);
        pnlOutpatient.setBounds(450, 130, 350, 110);
        add(pnlOutpatient);
       
        pnlAdmitted = new PanelCard("Admitted", String.valueOf(admitted), ColorsTheme.Green);
        pnlAdmitted.setBounds(830, 130, 350, 110);
        add(pnlAdmitted);
        
        pnlDischarged = new PanelCard("Discharged", String.valueOf(discharged), ColorsTheme.Red);
        pnlDischarged.setBounds(1210, 130, 350, 110);
        add(pnlDischarged);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewPatientDialog dialog = new NewPatientDialog();
            dialog.setVisible(true);
            updateTable("Recent Admissions", ""); 
        }
        else if (e.getSource() == btnView) {
            int selectedRow = tblPatient.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient to view.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String patientId = tblPatient.getTable().getValueAt(selectedRow, 0).toString();
            ViewPatientDialog viewDialog = new ViewPatientDialog(patientId);
            viewDialog.setVisible(true);
        }
        else if (e.getSource() == btnEdit) {
            int selectedRow = tblPatient.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String patientId = tblPatient.getTable().getValueAt(selectedRow, 0).toString();
            EditPatientDialog editDialog = new EditPatientDialog(patientId);
            editDialog.setVisible(true);
            updateTable("Recent Admissions", txtSearch.getText().trim().equals("Search by patient name or patient id...") ? "" : txtSearch.getText().trim());
        }
        
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or patient id...");
            updateTable("Recent Admissions", "");
        }
    }
}