package panels;

import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import dialogs.NewMedicalRecordDialog;
import dialogs.ViewMedicalRecordDialog; 
import dialogs.EditMedicalRecordDialog; 

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MedicalRecordsPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblMedical, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnView, btnEdit;
    private TablePanel tblMedical;
    
    private final String[] columns = {"Record ID", "Patient Name", "Medical Type", "Attending Doctor", "Date", "Time"};
    
    private boolean canManageRecords;

    
    public MedicalRecordsPanel(boolean canManageRecords) {
        this.canManageRecords = canManageRecords;
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);
        
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 250, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);
        
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 130, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);
        
        btnAdd = new JButton("Add");
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

        java.util.List<JButton> visibleButtons = new java.util.ArrayList<>();
        if (canManageRecords) {
            visibleButtons.add(btnAdd); 
        }
        visibleButtons.add(btnView); 
        if (canManageRecords) {
            visibleButtons.add(btnEdit); 
        }

        int[] slots = {995, 1160, 1325}; 
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
            visibleButtons.get(i).addActionListener(this);
        }
        
        txtSearch = new JTextField("Search by patient name or record ID...");
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
        
        lblMedical = new JLabel("Medical Records");
        lblMedical.setBounds(30, 30, 500, 40);
        lblMedical.setFont(FontsTheme.Bold_Texts);
        lblMedical.setForeground(ColorsTheme.Text_Black);
        add(lblMedical);

        lblDetails = new JLabel(canManageRecords ? "Access and manage patient medical history and vitals." : "View patient medical history and vitals.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);

        Object[][] data = fetchRecords("");
        tblMedical = new TablePanel("Recent Medical Records", columns, data, 560);
        tblMedical.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblMedical);

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
        
        String sql = "SELECT m.record_id, m.record_datetime, " +
                     "p.first_name AS pat_first, p.last_name AS pat_last, " +
                     "u.lastname AS doc_last, rt.type_name " +
                     "FROM medical_records m " +
                     "LEFT JOIN patients p ON m.patient_id = p.patient_id " +
                     "LEFT JOIN users u ON m.doctor_id = u.user_id " +
                     "LEFT JOIN record_types rt ON m.record_type_id = rt.type_id";
                     
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search by patient name or record ID...");
        
        if (hasSearchFilter) {
            sql += " WHERE p.first_name LIKE ? OR p.last_name LIKE ? OR m.record_id LIKE ?";
        }
        sql += " ORDER BY m.record_id DESC"; 

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                String cleanSearch = queryTerm.replace("MED-", "").replace("med-", "");
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + cleanSearch + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int rawRecordId = result.getInt("record_id");
                String displayRecordId = String.format("MED-%03d", rawRecordId); 
                
                String patName = result.getString("pat_first") + " " + result.getString("pat_last");
                String docName = "Dr. " + result.getString("doc_last");
                String type = result.getString("type_name");
                
                String displayDate = "N/A";
                String displayTime = "N/A";
                Timestamp dbDateTime = result.getTimestamp("record_datetime");
                
                if (dbDateTime != null) {
                    java.time.LocalDateTime localDT = dbDateTime.toLocalDateTime();
                    displayDate = localDT.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    displayTime = localDT.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
                }

                rowsList.add(new Object[]{displayRecordId, patName, type, docName, displayDate, displayTime});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load medical records.\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return rowsList.toArray(new Object[0][]);
    }
    
    private int getSelectedRecordId() {
        int row = tblMedical.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a medical record first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        String displayId = tblMedical.getTable().getValueAt(row, 0).toString();
        return Integer.parseInt(displayId.replace("MED-", ""));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewMedicalRecordDialog record = new NewMedicalRecordDialog();
            record.setVisible(true); 
            updateTable("Recent Medical Records", ""); 
        }
        else if (e.getSource() == btnView) {
            // WIRED UP THE VIEW BUTTON
            int id = getSelectedRecordId();
            if (id != -1) {
                ViewMedicalRecordDialog dialog = new ViewMedicalRecordDialog(id);
                dialog.setVisible(true);
            }
        }
        else if (e.getSource() == btnEdit) {
            // WIRED UP THE EDIT BUTTON
            int id = getSelectedRecordId();
            if (id != -1) {
                EditMedicalRecordDialog dialog = new EditMedicalRecordDialog(id);
                dialog.setVisible(true);
                updateTable("Recent Medical Records", ""); // Refreshes table just in case they changed the Doctor!
            }
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by patient name or record ID...");
            updateTable("Recent Medical Records", "");
        }
    }
}