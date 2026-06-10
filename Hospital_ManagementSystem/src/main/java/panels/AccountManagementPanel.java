package panels;

import constants.TablePanel;
import constants.ColorsTheme;
import constants.FontsTheme;
import dialogs.NewAccountDialog;
import dialogs.EditAccountDialog;
import dialogs.ViewAccountDialog;
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

public class AccountManagementPanel extends JPanel implements ActionListener {

    private JPanel pnlMiddle, pnlSearch;
    private JLabel lblTitle, lblDetails;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnView, btnEdit, btnDelete;
    private TablePanel tblAccounts;

    private static final String[] columns = {"Username", "First Name", "Last Name", "Role"};

    public AccountManagementPanel() {
        setLayout(null);
        setBackground(ColorsTheme.Middle_Panel);

        // Title and Subtitle
        lblTitle = new JLabel("Account Management");
        lblTitle.setBounds(30, 30, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);

        lblDetails = new JLabel("Register and manage credentials for hospital staff access.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);

        // Search Panel Container
        pnlSearch = new JPanel();
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(70, 130, 1500, 80);
        pnlSearch.setBackground(ColorsTheme.Main_Card);
        add(pnlSearch);

        // Middle Panel Container for Table
        pnlMiddle = new JPanel();
        pnlMiddle.setLayout(null);
        pnlMiddle.setBounds(70, 250, 1500, 620);
        pnlMiddle.setBackground(ColorsTheme.Main_Card);
        add(pnlMiddle);

        // CRUD Buttons next to the title
        btnAdd = new JButton("+ Add");
        btnAdd.setBounds(830, 40, 150, 45);
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
        btnAdd.setFocusPainted(false);
        add(btnAdd);

        btnView = new JButton("View");
        btnView.setBounds(995, 40, 150, 45);
        btnView.setFont(FontsTheme.Buttons);
        btnView.setBackground(ColorsTheme.Header);
        btnView.setForeground(ColorsTheme.Text_White);
        btnView.setFocusPainted(false);
        add(btnView);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(1160, 40, 150, 45);
        btnEdit.setFont(FontsTheme.Buttons);
        btnEdit.setBackground(ColorsTheme.Update_Pending);
        btnEdit.setForeground(ColorsTheme.Text_Black);
        btnEdit.setFocusPainted(false);
        add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(1325, 40, 150, 45);
        btnDelete.setFont(FontsTheme.Buttons);
        btnDelete.setBackground(ColorsTheme.Delete_Urgent);
        btnDelete.setForeground(ColorsTheme.Text_White);
        btnDelete.setFocusPainted(false);
        add(btnDelete);

        // Search Bar including search and refresh buttons
        txtSearch = new JTextField("Search by username or name...");
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

        // Build Table
        Object[][] data = fetchAccounts("");
        tblAccounts = new TablePanel("Registered Accounts", columns, data, 560);
        tblAccounts.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblAccounts);

        // Listeners
        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
    }

    private void updateTable(String title, String keyword) {
        Object[][] data = fetchAccounts(keyword);
        pnlMiddle.remove(tblAccounts);
        tblAccounts = new TablePanel(title, columns, data, 560);
        tblAccounts.setBounds(0, 0, 1500, 620);
        pnlMiddle.add(tblAccounts);

        pnlMiddle.repaint();
        pnlMiddle.revalidate();
    }

    private Object[][] fetchAccounts(String query) {
        List<Object[]> rowsList = new ArrayList<>();
        String sql = "SELECT username, firstname, lastname, role FROM users";
        boolean hasFilter = !query.isEmpty() && !query.equals("Search by username or name...");
        if (hasFilter) {
            sql += " WHERE username LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR role LIKE ?";
        }
        sql += " ORDER BY username ASC";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (hasFilter) {
                String term = "%" + query + "%";
                stmt.setString(1, term);
                stmt.setString(2, term);
                stmt.setString(3, term);
                stmt.setString(4, term);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rowsList.add(new Object[]{
                    rs.getString("username"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("role")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load accounts:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return rowsList.toArray(new Object[0][]);
    }

    private String getSelectedUsername() {
        int row = tblAccounts.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an account first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return tblAccounts.getTable().getValueAt(row, 0).toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource() == btnAdd) {
            NewAccountDialog dialog = new NewAccountDialog();
            dialog.setVisible(true);
            if (dialog.isSuccess()) {
                updateTable("Registered Accounts", "");
            }
        } else if (e.getSource() == btnView) {
            String user = getSelectedUsername();
            if (user != null) {
                ViewAccountDialog dialog = new ViewAccountDialog(user);
                dialog.setVisible(true);
            }
        } else if (e.getSource() == btnEdit) {
            String user = getSelectedUsername();
            if (user != null) {
                EditAccountDialog dialog = new EditAccountDialog(user);
                dialog.setVisible(true);
                if (dialog.isSuccess()) {
                    updateTable("Registered Accounts", "");
                }
            }
        } else if (e.getSource() == btnDelete) {
            String user = getSelectedUsername();
            if (user != null) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Permanently delete user account '" + user + "'?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteAccount(user);
                }
            }
        } else if (e.getSource() == btnSearch) {
            updateTable("Search Results", txtSearch.getText().trim());
        } else if (e.getSource() == btnRefresh) {
            txtSearch.setText("Search by username or name...");
            updateTable("Registered Accounts", "");
        }
    }

    private void deleteAccount(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateTable("Registered Accounts", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete account:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
