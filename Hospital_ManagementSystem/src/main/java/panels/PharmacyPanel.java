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
    private JButton btnSearch, btnRefresh, btnAdd,btnUseStock,btnRemove;
    private TablePanel tblPharmacy;
    private static final String[] columns = {"Item Code", "Medication", "Category", "Stock", "Reorder Level", "Status"};
    private boolean stockButtonsInTable;
    
    public PharmacyPanel() {
        this(false);
    }

    public PharmacyPanel(boolean stockButtonsInTable) {
        this.stockButtonsInTable = stockButtonsInTable;

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
        btnAdd = new JButton("+ Add Med");
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        
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
 
        btnUseStock = new JButton("Update Stock");
        btnUseStock.setFont(FontsTheme.Buttons);
        btnUseStock.setFocusPainted(false);
        if (stockButtonsInTable) {
            btnUseStock.setBounds(1100, 10, 180, 40);
            btnUseStock.setBackground(Color.blue); 
            btnUseStock.setForeground(ColorsTheme.Text_White);
        } else {
            btnUseStock.setBackground(ColorsTheme.Update_Pending); 
            btnUseStock.setForeground(ColorsTheme.Text_Black);
        }
        
        btnRemove = new JButton("Remove Stock");
        btnRemove.setFont(FontsTheme.Buttons);  
        btnRemove.setForeground(ColorsTheme.Text_White);
        btnRemove.setFocusPainted(false);
        if (stockButtonsInTable) {
            btnRemove.setBounds(1290, 10, 190, 40);
            btnRemove.setBackground(ColorsTheme.Red); 
        } else {
            btnRemove.setBackground(ColorsTheme.Delete_Urgent); 
        }

        // Dynamically align visible outer-panel buttons to the right-hand side
        java.util.List<JButton> visibleButtons = new java.util.ArrayList<>();
        visibleButtons.add(btnAdd);
        if (!stockButtonsInTable) {
            visibleButtons.add(btnUseStock);
            visibleButtons.add(btnRemove);
        }

        int[] slots = {830, 995, 1160, 1325};
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
        }

        
        
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
        configurePharmacyTable();
        
        
        
        // ActionListeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnUseStock.addActionListener(this);
        btnRemove.addActionListener(this);
    }
    
    
    
    
    private void updateTable(String sectionTitle, String searchKeyword) {
        Object[][] freshData = fetchPharmacy(searchKeyword);
        
        pnlMiddle.remove(tblPharmacy);
        tblPharmacy = new TablePanel(sectionTitle, columns, freshData, 560);
        tblPharmacy.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPharmacy);
        configurePharmacyTable();
        
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
                String fullMedName = brandName + " (" + genericName + ") " + strength;

                // Tally stats dynamically based on database state
                countTotal++;
                if ("Critical".equalsIgnoreCase(status)) countCritical++;
                else if ("Low Stock".equalsIgnoreCase(status)) countLowStock++;
                else countInStock++;

                rowsList.add(new Object[]{itemCode, fullMedName, category, stock, reorder, status});
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

    private void configurePharmacyTable() {
        JTable table = tblPharmacy.getTable();
        table.setRowHeight(50);
        if (stockButtonsInTable) {
            tblPharmacy.add(btnUseStock);
            tblPharmacy.add(btnRemove);
        }
    }
        private void updateMedicationStockInDatabase(String itemCode, int newStock) {
  
    String sql = "UPDATE pharmacy SET current_stock = ?, status = CASE WHEN ? = 0 THEN 'Critical' ELSE status END WHERE item_code = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
         PreparedStatement statement = conn.prepareStatement(sql)) {

       
        statement.setInt(1, newStock);
        statement.setInt(2, newStock);
        statement.setString(3, itemCode);
        
     
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Stock updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Medication record not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to update stock in database.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
        
            private void removeMedicationFromDatabase(String itemCode) {
        String sql = "DELETE FROM pharmacy WHERE item_code = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, itemCode);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Medication removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Medication could not be found in database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to remove medication from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
        else if (e.getSource() == btnUseStock) {
        JTable actualTable = tblPharmacy.getTable(); 
        int selectedRow = actualTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a medication from the table first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }


        String itemCode = actualTable.getValueAt(selectedRow, 0).toString();
        String medName = actualTable.getValueAt(selectedRow, 1).toString();
        int currentStock = Integer.parseInt(actualTable.getValueAt(selectedRow, 3).toString());


        String input = JOptionPane.showInputDialog(
            this, 
            "Current Stock: " + currentStock + "\nHow many units of " + medName + " did you use?", 
            "Use Stock Quantity", 
            JOptionPane.QUESTION_MESSAGE
        );


        if (input == null || input.trim().isEmpty()) {
            return; 
        }

        try {

            int quantityUsed = Integer.parseInt(input.trim());

            if (quantityUsed <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number greater than 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (quantityUsed > currentStock) {
                JOptionPane.showMessageDialog(this, "Not enough stock available! You only have " + currentStock + " units left.", "Insufficient Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }


            int newStock = currentStock - quantityUsed;


            updateMedicationStockInDatabase(itemCode, newStock);


            String currentSearch = txtSearch.getText().trim();
            if (currentSearch.equals("Search medication name, item code, or category...")) {
                updateTable("Medication Inventory", "");
            } else {
                updateTable("Search Results", currentSearch);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
             }
        }
        else if (e.getSource() == btnRemove) {
        JTable actualTable = tblPharmacy.getTable(); 
        int selectedRow = actualTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a medication from the table first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

   
        String itemCode = actualTable.getValueAt(selectedRow, 0).toString();
        String medName = actualTable.getValueAt(selectedRow, 1).toString();

    
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to remove " + medName + " from inventory?", 
            "Confirm Use Stock", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            removeMedicationFromDatabase(itemCode);
            
       
            String currentSearch = txtSearch.getText().trim();
            if (currentSearch.equals("Search medication name, item code, or category...")) {
                updateTable("Medication Inventory", "");
            } else {
                updateTable("Search Results", currentSearch);
            }
        }

    }
}
}
