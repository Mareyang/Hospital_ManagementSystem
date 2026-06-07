package panels;

import constants.PanelCard;
import constants.ColorsTheme;
import constants.FontsTheme;
import constants.TablePanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dialogs.NewPharmacyDialog;

public class PharmacyPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlTotal, pnlIn, pnlLow, pnlCrit; // Changed from JPanel to PanelCard
    private JLabel lblDetails, lblPharmacy;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private TablePanel tblPharmacy;
    private static final String[] columns = {"Item Code", "Medication", "Category", "Stock", "Reorder Level", "Status", "Actions"};
    
    public PharmacyPanel() {
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
        
        // Button for adding new medication
        btnAdd = new JButton("+  Add Medication");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        add(btnAdd);
        
        // Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search medication name, item code, or category...");
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
        
        // Title and subtitle label for pharmacy section
        lblPharmacy = new JLabel("Pharmacy");
        lblPharmacy.setBounds(30, 30, 500, 40);
        lblPharmacy.setFont(FontsTheme.Bold_Texts);
        lblPharmacy.setForeground(ColorsTheme.Text_Black);
        add(lblPharmacy);

        lblDetails = new JLabel("Manage medications and inventory.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        
        // Fetch Data & Build Table  
        Object[][] data = fetchPharmacy("");
        tblPharmacy = new TablePanel("Medication Inventory", columns, data, 560);
        tblPharmacy.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPharmacy);
        
        
        
        // ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }
    
    
    
    
    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPharmacy(searchKeyword);
        
        pnlMiddle.remove(tblPharmacy);
        tblPharmacy = new TablePanel(sectionTitle, columns, freshData, 560);
        tblPharmacy.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPharmacy);
        
        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchPharmacy(String queryTerm) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT * FROM pharmacy";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search medication name, item code, or category...");
        
        if (hasSearchFilter) {
            sql += " WHERE item_code LIKE ? OR brand_name LIKE ? OR generic_name LIKE ? OR category LIKE ?";
        }
        sql += " ORDER BY medication_id ASC"; 

        // Counters for Inventory Statistics
        int countTotal = 0;
        int countInStock = 0;
        int countLowStock = 0;
        int countCritical = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            if (hasSearchFilter) {
                statement.setString(1, "%" + queryTerm + "%");
                statement.setString(2, "%" + queryTerm + "%");
                statement.setString(3, "%" + queryTerm + "%");
                statement.setString(4, "%" + queryTerm + "%");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String itemCode = result.getString("item_code");
                String brandName = result.getString("brand_name");
                String genericName = result.getString("generic_name");
                String strength = result.getString("strength");
                String category = result.getString("category");
                int stock = result.getInt("current_stock");
                int reorder = result.getInt("reorder_level");
                String status = result.getString("status");
                String actions = ""; 

                String fullMedName = brandName + " (" + genericName + ") " + strength;

                // Tally stats dynamically based on database state
                countTotal++;
                if ("Critical".equalsIgnoreCase(status)) countCritical++;
                else if ("Low Stock".equalsIgnoreCase(status)) countLowStock++;
                else countInStock++;

                rowsList.add(new Object[]{itemCode, fullMedName, category, stock, reorder, status, actions});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load inventory from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        refreshSummaryCards(countTotal, countInStock, countLowStock, countCritical);

        return rowsList.toArray(new Object[0][]);
    }

    private void refreshSummaryCards(int total, int inStock, int lowStock, int critical) {
        if (pnlTotal != null) remove(pnlTotal);
        if (pnlIn != null) remove(pnlIn);
        if (pnlLow != null) remove(pnlLow);
        if (pnlCrit != null) remove(pnlCrit);

        pnlTotal = new PanelCard("Total Items", String.valueOf(total), ColorsTheme.Blue);
        pnlTotal.setBounds(70, 130, 350, 110);
        add(pnlTotal);
        
        pnlIn = new PanelCard("In Stock", String.valueOf(inStock), ColorsTheme.Green);
        pnlIn.setBounds(450, 130, 350, 110);
        add(pnlIn);
       
        pnlLow = new PanelCard("Low Stock", String.valueOf(lowStock), ColorsTheme.Yellow);
        pnlLow.setBounds(830, 130, 350, 110);
        add(pnlLow);
        
        pnlCrit = new PanelCard("Critical", String.valueOf(critical), ColorsTheme.Red);
        pnlCrit.setBounds(1210, 130, 350, 110);
        add(pnlCrit);

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            NewPharmacyDialog pharmacy = new NewPharmacyDialog();
            pharmacy.setVisible(true);
            updateTable("Medication Inventory", ""); 
        }
        else if (e.getSource() == btnSearch) {
            String searchKeyword = txtSearch.getText().trim();
            updateTable("Search Results", searchKeyword);
        }
        else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search medication name, item code, or category...");
            updateTable("Medication Inventory", "");
        }
    }
}