package panels;

import constants.TablePanel;
import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.AddPrescriptionDialog;
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

public class PrescriptionsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlPending, pnlDispense, pnlCancel;
    private JLabel lblDetails, lblPrescription;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblPrescription;
    private static final String[] columns = {"Rx ID", "Patient Name", "Doctor", "Date", "Medications", "Status", "Actions"};
    
    public PrescriptionsPanel() {
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
        
        // Button for adding new prescription
        btnAdd = new JButton("+ New Rx");
        btnAdd.setBounds(1325, 40, 150, 45); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        add(btnAdd);
        
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
                
        // Title and subtitle label for prescription section
        lblPrescription = new JLabel("Prescription");
        lblPrescription.setBounds(30, 30, 500, 40);
        lblPrescription.setFont(FontsTheme.Bold_Texts);
        lblPrescription.setForeground(ColorsTheme.Text_Black);
        add(lblPrescription);

        lblDetails = new JLabel("Manage and dispense prescriptions.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        
        // Fetch Data & Build Table  
        Object[][] data = fetchPrescriptions("");
        tblPrescription = new TablePanel("Recent Prescription", columns, data, 440);
        tblPrescription.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPrescription);

        
        
        
        // ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    
    
    
    private void updateTable (String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPrescriptions(searchKeyword);
        
        pnlMiddle.remove(tblPrescription);
        tblPrescription = new TablePanel(sectionTitle, columns, freshData, 440);
        tblPrescription.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPrescription);
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchPrescriptions(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or patient id...");
        
        if (hasSearchFilter) {
            sql += " WHERE patient_name LIKE ? OR rx_id LIKE ? OR patient_id LIKE ?";
        }
        sql += " ORDER BY rx_id ASC"; 

        // Variables to hold our dynamic counts
        int countPending = 0;
        int countDispensed = 0;
        int countCancelled = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.toUpperCase().replace("RX-", "").replace("PAT-", "").trim();
                
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + cleanSearch + "%");
                statement.setString(3, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawId = result.getInt("rx_id");
                String displayId = String.format("RX-%03d", rawId); 
                
                String name = result.getString("patient_name");
                String doctor = result.getString("doctor");
                String date = result.getString("prescription_date");
                String medications = result.getString("medication_name") + " (" + result.getString("dosage") + ")";
                String status = result.getString("status");
                String actions = ""; 

                if ("Pending".equalsIgnoreCase(status)) countPending++;
                if ("Dispensed".equalsIgnoreCase(status)) countDispensed++;
                if ("Cancelled".equalsIgnoreCase(status)) countCancelled++;

                rowsList.add(new Object[]{displayId, name, doctor, date, medications, status, actions});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load prescriptions from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countPending, countDispensed, countCancelled);

        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int pending, int dispensed, int cancelled) {
        
        if (pnlPending != null) remove(pnlPending);
        if (pnlDispense != null) remove(pnlDispense);
        if (pnlCancel != null) remove(pnlCancel);

        pnlPending = new PanelCard("Pending", String.valueOf(pending), ColorsTheme.Orange);
        pnlPending.setBounds(170, 130, 350, 110);
        add(pnlPending);
        
        pnlDispense = new PanelCard("Dispensed Today", String.valueOf(dispensed), ColorsTheme.Green);
        pnlDispense.setBounds(620, 130, 350, 110);
        add(pnlDispense);
        
        pnlCancel = new PanelCard("Cancelled", String.valueOf(cancelled), ColorsTheme.Red);
        pnlCancel.setBounds(1070, 130, 350, 110);
        add(pnlCancel);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            AddPrescriptionDialog prescription = new AddPrescriptionDialog();
            prescription.setVisible(true); 
            updateTable("Recent Prescription", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or patient id...");
            updateTable("Recent Prescription", "");
        }
    }
}