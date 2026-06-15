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
import dialogs.EditPharmacyDialog; 

public class PharmacyPanel extends JPanel implements ActionListener {
    
    private JPanel pnlMiddle, pnlSearch;
    private PanelCard pnlTotal, pnlIn, pnlLow, pnlCrit; 
    private JLabel lblDetails, lblPharmacy;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnEdit, btnUseStock, btnRestock, btnDiscontinue; 
    private TablePanel tblPharmacy;
    
    private static final String[] columns = {"Item Code", "Medication", "Category", "Stock", "Reorder Level", "Inventory Status"};
    private boolean stockButtonsInTable;
    
    public PharmacyPanel() {
        this(false);
    }

    public PharmacyPanel(boolean stockButtonsInTable) {
        this.stockButtonsInTable = stockButtonsInTable;

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
        
        btnAdd = new JButton("Add");
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        
        // --- ADDED EDIT BUTTON ---
        btnEdit = new JButton("Edit");
        btnEdit.setFont(FontsTheme.Buttons);
        btnEdit.setBackground(ColorsTheme.Edit); 
        btnEdit.setForeground(ColorsTheme.Text_White);
        btnEdit.setFocusPainted(false);
        
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
 
        btnUseStock = new JButton("Use Stock");
        btnUseStock.setFont(FontsTheme.Buttons);
        btnUseStock.setFocusPainted(false);
        btnUseStock.setBackground(ColorsTheme.Orange); 
        btnUseStock.setForeground(ColorsTheme.Text_White);
        
        btnRestock = new JButton("Restock");
        btnRestock.setFont(FontsTheme.Buttons);
        btnRestock.setFocusPainted(false);
        btnRestock.setBackground(ColorsTheme.View); 
        btnRestock.setForeground(ColorsTheme.Text_White);
        
        btnDiscontinue = new JButton("Discontinue");
        btnDiscontinue.setFont(FontsTheme.Buttons);  
        btnDiscontinue.setForeground(ColorsTheme.Text_White);
        btnDiscontinue.setFocusPainted(false);
        btnDiscontinue.setBackground(ColorsTheme.Delete); 

        java.util.List<JButton> visibleButtons = new java.util.ArrayList<>();
        visibleButtons.add(btnAdd);
        visibleButtons.add(btnEdit); 
        visibleButtons.add(btnUseStock);
        visibleButtons.add(btnRestock);
        visibleButtons.add(btnDiscontinue);

        // Created 5 perfectly spaced slots for the Admin Panel
        int[] slots = {670, 835, 1000, 1165, 1330};
        int startSlotIndex = slots.length - visibleButtons.size();
        for (int i = 0; i < visibleButtons.size(); i++) {
            visibleButtons.get(i).setBounds(slots[startSlotIndex + i], 40, 150, 45);
            add(visibleButtons.get(i));
        }

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
        
        Object[][] data = fetchPharmacy("");
        tblPharmacy = new TablePanel("Medication Inventory", columns, data, 560);
        tblPharmacy.setBounds(0, 0, 1500, 560);
        pnlMiddle.add(tblPharmacy);
        
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this); // Added listener!
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnUseStock.addActionListener(this);
        btnRestock.addActionListener(this);
        btnDiscontinue.addActionListener(this);
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
        
        String sql = "SELECT ph.*, c.category_name " +
                     "FROM pharmacy ph " +
                     "LEFT JOIN medication_categories c ON ph.category_id = c.category_id ";
        
        boolean hasSearchFilter = !queryTerm.isEmpty() && !queryTerm.equals("Search medication name, item code, or category...");
        
        if (hasSearchFilter) {
            sql += " WHERE ph.item_code LIKE ? OR ph.brand_name LIKE ? OR ph.generic_name LIKE ? OR c.category_name LIKE ?";
        }
        sql += " ORDER BY ph.medication_id ASC"; 

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
                
                String category = result.getString("category_name");
                if (category == null) category = "Uncategorized";
                
                int stock = result.getInt("current_stock");
                int reorder = result.getInt("reorder_level");
                int statusId = result.getInt("status_id"); 
                
                String fullMedName = (brandName != null && !brandName.isEmpty() ? brandName + " (" + genericName + ") " : genericName + " ") + strength;

                countTotal++;
                if (stock <= 0) {
                    countCritical++;
                } else if (stock <= reorder) {
                    countLowStock++;
                } else {
                    countInStock++;
                }

                String inventoryStatus = "In Stock";
                if (statusId == 2) {
                    inventoryStatus = "Discontinued";
                } else if (statusId == 3) {
                    inventoryStatus = "Recalled";
                } else {
                    if (stock <= 0) {
                        inventoryStatus = "Out of Stock"; 
                    } else if (stock <= reorder) {
                        inventoryStatus = "Low Stock";
                    }
                }

                rowsList.add(new Object[]{itemCode, fullMedName, category, stock, reorder, inventoryStatus});
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load inventory from database.\nMake sure medication_categories table exists!", "Database Error", JOptionPane.ERROR_MESSAGE);
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
        
        pnlCrit = new PanelCard("Out of Stock", String.valueOf(critical), ColorsTheme.Red);
        pnlCrit.setBounds(1210, 130, 350, 110);
        add(pnlCrit);

        repaint();
        revalidate();
    }
    
    private void updateMedicationStockInDatabase(String itemCode, int newStock) {
        String sql = "UPDATE pharmacy SET current_stock = ? WHERE item_code = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, newStock);
            statement.setString(2, itemCode);
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
        
    private void discontinueMedication(String itemCode) {
        String sql = "UPDATE pharmacy SET status_id = 2, current_stock = 0 WHERE item_code = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, itemCode);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Medication has been successfully marked as Discontinued.\nStock has been set to 0.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Medication could not be found in database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to discontinue medication.", "Database Error", JOptionPane.ERROR_MESSAGE);
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
        // --- ADDED EDIT LOGIC ---
        else if (e.getSource() == btnEdit) {
            JTable actualTable = tblPharmacy.getTable(); 
            int selectedRow = actualTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a medication from the table first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Get the item code to pass to the dialog
            String itemCode = actualTable.getValueAt(selectedRow, 0).toString();
            EditPharmacyDialog editDialog = new EditPharmacyDialog(itemCode);
            editDialog.setVisible(true);
            
            // Refresh table after editing
            String currentSearch = txtSearch.getText().trim();
            if (currentSearch.equals("Search medication name, item code, or category...")) {
                updateTable("Medication Inventory", "");
            } else {
                updateTable("Search Results", currentSearch);
            }
        }
        else if (e.getSource() == btnUseStock) {
            JTable actualTable = tblPharmacy.getTable(); 
            int selectedRow = actualTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a medication from the table first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String currentStatus = actualTable.getValueAt(selectedRow, 5).toString();
            if (currentStatus.equals("Discontinued") || currentStatus.equals("Recalled")) {
                JOptionPane.showMessageDialog(this, "You cannot use stock for a medication that is Discontinued or Recalled.", "Action Blocked", JOptionPane.WARNING_MESSAGE);
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
        else if (e.getSource() == btnRestock) {
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
                "Current Stock: " + currentStock + "\nHow many units of " + medName + " did you RECEIVE from the supplier?", 
                "Restock Inventory", 
                JOptionPane.QUESTION_MESSAGE
            );

            if (input == null || input.trim().isEmpty()) {
                return; 
            }

            try {
                int quantityReceived = Integer.parseInt(input.trim());

                if (quantityReceived <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number greater than 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int newStock = currentStock + quantityReceived;
                
                String sql = "UPDATE pharmacy SET current_stock = ?, status_id = 1 WHERE item_code = ?";
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                     PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, newStock);
                    statement.setString(2, itemCode);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Inventory Restocked! New total: " + newStock, "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to update database.", "Error", JOptionPane.ERROR_MESSAGE);
                }

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
        else if (e.getSource() == btnDiscontinue) {
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
                "Are you sure you want to Discontinue " + medName + "?\nThis will keep the record but set its stock to 0.", 
                "Confirm Discontinue", 
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                discontinueMedication(itemCode);
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