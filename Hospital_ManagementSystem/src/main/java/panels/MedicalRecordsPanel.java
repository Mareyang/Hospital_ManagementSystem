package panels;

import dialogs.AddMedicalRecordDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Medical Records Panel with Dynamic Database Connection and Search Functionality.
 */
public class MedicalRecordsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblMedical;
    
    private final String[] columns = {"Record ID", "Patient Name", "Medical Type", "Doctor", "Date", "Time", "Actions"};
    
    
    public MedicalRecordsPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        // Main Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 250, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        // Search Panel Container 
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 130, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        // Button for adding new record
        btnAdd = new JButton("+ Add");
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
        
        // Title and subtitle labels
        lblMedical = new JLabel("Medical Records");
        lblMedical.setBounds(30, 30, 500, 40);
        lblMedical.setFont(FontsTheme.Bold_Texts);
        lblMedical.setForeground(ColorsTheme.Text_Black);
        add(lblMedical);

        lblDetails = new JLabel("Access and manage patient medical records.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);

        // Fetch data and build table initial state
        Object[][] data = fetchRecords("");
        tblMedical = new TablePanel("Recent Medical Records", columns, data, 560);
        tblMedical.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblMedical);

        
        
        
        // ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    
    
    

    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchRecords(searchKeyword);
        
        pnlMiddle.remove(tblMedical);
        tblMedical = new TablePanel(sectionTitle, columns, freshData, 560);
        tblMedical.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblMedical);
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchRecords(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM medical_records";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or patient id...");
        
        if (hasSearchFilter) {
            sql += " WHERE patient_name LIKE ? OR patient_id LIKE ? OR record_id LIKE ?";
        }
            sql += " ORDER BY record_id ASC"; 

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.replace("PAT-", "").replace("pat-", "").replace("MED-", "").replace("med-", "");
                
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + cleanSearch + "%");
                statement.setString(3, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawRecordId = result.getInt("record_id");
                String displayRecordId = String.format("MED-%03d", rawRecordId); 
                
                String name = result.getString("patient_name");
                String type = result.getString("record_type");
                String doctor = result.getString("doctor");
                String date = result.getString("record_date"); 
                String time = result.getString("record_time"); 
                String actions = ""; 

                rowsList.add(new Object[]{displayRecordId, name, type, doctor, date, time, actions});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load medical records from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return rowsList.toArray(new Object[0][]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            AddMedicalRecordDialog record = new AddMedicalRecordDialog();
            record.setVisible(true); 
            updateTable("Recent Medical Records", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or patient id...");
            updateTable("Recent Medical Records", "");
        }
    }
}