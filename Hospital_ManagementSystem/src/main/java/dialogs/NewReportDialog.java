package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import constants.SystemSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class NewReportDialog extends JDialog implements ActionListener {
    
    private JLabel lblTitle, lblSubtitle, lblCateg, lblName, lblNote, lblBy, lblDate, lblID, lblPeriod; 
    private JButton btnReportDetails, btnWrite, btnAuto, btnCancel, btnConfirm;
    private JPanel pnlContent;
    private JTextField txtName, txtBy, txtDate, txtID, txtPeriod;
    private JComboBox<String> cmbCateg;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    
    private static String[] categs = {" "};
    
    public NewReportDialog(String initialType) {
        setSize(1050, 550);
        setLayout(null);
        getContentPane().setBackground(ColorsTheme.Middle_Panel);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Report Generator");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Select parameters and date ranges to compile your data.");
        lblSubtitle.setBounds(30, 40, 700, 40);
        lblSubtitle.setFont(FontsTheme.Plain_Texts);
        lblSubtitle.setForeground(ColorsTheme.Text_Gray);
        add(lblSubtitle);
        
        btnReportDetails = new JButton("Report Details");
        btnReportDetails.setBounds(40, 100, 250, 40);
        btnReportDetails.setFont(FontsTheme.Buttons);
        btnReportDetails.setForeground(ColorsTheme.Text_White);
        btnReportDetails.setBackground(ColorsTheme.Header);
        btnReportDetails.setFocusPainted(false);
        add(btnReportDetails);
       
        btnWrite = new JButton("Write Report");
        btnWrite.setBounds(290, 100, 200, 40);
        btnWrite.setFont(FontsTheme.Buttons);
        btnWrite.setForeground(ColorsTheme.Text_White);
        btnWrite.setBackground(ColorsTheme.Header);
        btnWrite.setFocusPainted(false);
        add(btnWrite);

        btnAuto = new JButton("Auto-Generate Summary");
        btnAuto.setBounds(500, 100, 250, 40);
        btnAuto.setFont(FontsTheme.Buttons);
        btnAuto.setForeground(ColorsTheme.Text_Black);
        btnAuto.setBackground(ColorsTheme.Search);
        btnAuto.setFocusPainted(false);
        add(btnAuto);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(580, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnConfirm = new JButton("Save Report");
        btnConfirm.setBounds(790, 450, 200, 30);
        btnConfirm.setFont(FontsTheme.Buttons);
        btnConfirm.setForeground(ColorsTheme.Text_White);
        btnConfirm.setBackground(ColorsTheme.Green);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);
                
        
        btnReportDetails.addActionListener(this);
        btnWrite.addActionListener(this);
        btnAuto.addActionListener(this);
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
        
        initializeForms(initialType);
        showGenerateReport();
        
        // Auto-generate summary immediately upon opening
        String summary = generateReportSummary(initialType, "");
        txaNote.setText(summary);
    }
    
    private void loadCategories() {
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add(" ");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT type_name FROM report_types");
             java.sql.ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("type_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        categs = list.toArray(new String[0]);
    }
    
    
    private void initializeForms(String initialType) {
        loadCategories();
        lblName = new JLabel("Report Title : ");
        lblName.setBounds(40, 60, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 60, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        
        lblID = new JLabel("Report ID : ");
        lblID.setBounds(40, 130, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        
        txtID = new JTextField("Auto-generated");
        txtID.setBounds(220, 130, 230, 30);
        txtID.setFont(FontsTheme.Plain_Texts);
        txtID.setEditable(false);
        
        lblBy = new JLabel("Prepared By : ");
        lblBy.setBounds(40, 200, 200, 30);
        lblBy.setFont(FontsTheme.Plain_Texts);
        lblBy.setForeground(ColorsTheme.Text_Black);
        
        txtBy = new JTextField("");
        txtBy.setBounds(220, 200, 230, 30);
        txtBy.setFont(FontsTheme.Plain_Texts);
        
        lblDate = new JLabel("Date of Report : ");
        lblDate.setBounds(510, 60, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        
        txtDate = new JTextField("");
        txtDate.setBounds(690, 60, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        
        lblPeriod = new JLabel("Reporting Period : ");
        lblPeriod.setBounds(510, 130, 200, 30);
        lblPeriod.setFont(FontsTheme.Plain_Texts);
        lblPeriod.setForeground(ColorsTheme.Text_Black);
        
        txtPeriod = new JTextField("");
        txtPeriod.setBounds(690, 130, 230, 30);
        txtPeriod.setFont(FontsTheme.Plain_Texts);
        
        lblCateg = new JLabel("Report Scope : ");
        lblCateg.setBounds(510, 200, 200, 30);
        lblCateg.setFont(FontsTheme.Plain_Texts);
        lblCateg.setForeground(ColorsTheme.Text_Black);
        
        cmbCateg = new JComboBox<>(categs);
        if (initialType != null) {
            cmbCateg.setSelectedItem(initialType);
        }
        cmbCateg.setBounds(690, 200, 230, 30);
        cmbCateg.setFont(FontsTheme.Plain_Texts);
        cmbCateg.setBackground(ColorsTheme.Main_Card);
        
        lblNote = new JLabel("Executive Summary: ");
        lblNote.setBounds(50, 10, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        
        txaNote = new JTextArea("Write reports here...");
        txaNote.setFont(FontsTheme.Info_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 50, 880, 230);
    }
    
    public void showGenerateReport() {
        pnlContent.removeAll();
        pnlContent.add(lblName);
        pnlContent.add(txtName);
        pnlContent.add(lblID);
        pnlContent.add(txtID);
        pnlContent.add(lblBy);
        pnlContent.add(txtBy);
        pnlContent.add(lblDate);
        pnlContent.add(txtDate);
        pnlContent.add(lblPeriod);
        pnlContent.add(txtPeriod);
        pnlContent.add(lblCateg);
        pnlContent.add(cmbCateg);
        pnlContent.repaint();
        pnlContent.revalidate();
    }
    
    public void showWriteReport() {
        pnlContent.removeAll();
        pnlContent.add(lblNote);
        pnlContent.add(scrollNote);
        pnlContent.repaint();
        pnlContent.revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReportDetails) {
            showGenerateReport();
        } else if (e.getSource() == btnWrite) {
            showWriteReport();
        } else if (e.getSource() == btnAuto) {
            String reportScope = cmbCateg.getSelectedItem().toString();
            String period = txtPeriod.getText().trim();
            String summary = generateReportSummary(reportScope, period);
            txaNote.setText(summary);
            showWriteReport();
        } else if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnConfirm) {
            String reportID = txtID.getText().trim();
            String reportName = txtName.getText().trim();
            String reportScope = cmbCateg.getSelectedItem().toString();
            String preparedBy = txtBy.getText().trim();
            String dateGen = txtDate.getText().trim();
            String period = txtPeriod.getText().trim();
            String summary = txaNote.getText().trim();
            
            if (summary.equals("Write reports here...")) {
                summary = "";
            }

            if (reportName.isEmpty() || reportScope.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Report Title and Scope are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int typeId = getTypeId(reportScope);
            int generatedById = 1; // Default to Admin ID for now

            String sql = "INSERT INTO hospital_reports (report_title, report_type_id, generated_by_id, reporting_period, report_body) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement insert = conn.prepareStatement(sql)) {
                
                insert.setString(1, reportName);
                insert.setInt(2, typeId);
                insert.setInt(3, generatedById);
                insert.setString(4, period);
                insert.setString(5, summary);

                int rows = insert.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Report generated successfully!", "Report Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database write operation failed:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getTypeId(String typeName) {
        int id = 1;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT type_id FROM report_types WHERE type_name = ?")) {
            stmt.setString(1, typeName);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("type_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    private String generateReportSummary(String scope, String period) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- AUTO GENERATED SUMMARY ---\n");
        sb.append("Reporting Scope: ").append(scope).append("\n");
        if (period == null || period.trim().isEmpty()) {
            sb.append("Reporting Period: All\n");
        } else {
            sb.append("Reporting Period: ").append(period).append("\n");
        }
        sb.append("------------------------------\n\n");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "")) {
            if (scope.contains("Financial") || scope.contains("Billing") || scope.contains("Revenue")) {
                String sql = "SELECT COUNT(*) as cnt, SUM(net_amount) as total FROM billing";
                if (!period.isEmpty()) sql += " WHERE billing_date LIKE ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (!period.isEmpty()) stmt.setString(1, "%" + period + "%");
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        double rev = rs.getDouble("total");
                        if ("USD".equals(SystemSettings.currency)) rev /= 58.0;
                        sb.append("Total Billing Invoices: ").append(rs.getInt("cnt")).append("\n");
                        sb.append("Total Net Revenue: ").append(SystemSettings.getCurrencySymbol()).append(String.format("%.2f", rev)).append("\n");
                    }
                }
            } else if (scope.contains("Patient") || scope.contains("Demographics") || scope.contains("Admissions")) {
                String sql = "SELECT COUNT(*) as cnt, " +
                             "SUM(CASE WHEN gender = 'Male' THEN 1 ELSE 0 END) as male_cnt, " +
                             "SUM(CASE WHEN gender = 'Female' THEN 1 ELSE 0 END) as female_cnt, " +
                             "SUM(CASE WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) < 18 THEN 1 ELSE 0 END) as minors, " +
                             "SUM(CASE WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 18 AND 59 THEN 1 ELSE 0 END) as adults, " +
                             "SUM(CASE WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) >= 60 THEN 1 ELSE 0 END) as seniors, " +
                             "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as outpatient, " +
                             "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as admitted, " +
                             "SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as discharged " +
                             "FROM patients";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        sb.append("Total Registered Patients: ").append(rs.getInt("cnt")).append("\n\n");
                        sb.append("[ Gender Breakdown ]\n");
                        sb.append("Male: ").append(rs.getInt("male_cnt")).append("\n");
                        sb.append("Female: ").append(rs.getInt("female_cnt")).append("\n\n");
                        sb.append("[ Age Groups ]\n");
                        sb.append("Minors (< 18): ").append(rs.getInt("minors")).append("\n");
                        sb.append("Adults (18-59): ").append(rs.getInt("adults")).append("\n");
                        sb.append("Seniors (60+): ").append(rs.getInt("seniors")).append("\n\n");
                        sb.append("[ Current Status ]\n");
                        sb.append("Admitted: ").append(rs.getInt("admitted")).append("\n");
                        sb.append("Outpatient: ").append(rs.getInt("outpatient")).append("\n");
                        sb.append("Discharged: ").append(rs.getInt("discharged")).append("\n");
                    }
                }
            } else if (scope.contains("Clinical") || scope.contains("Prescription") || scope.contains("Pharmacy Dispensation")) {
                String sql = "SELECT p.status_id, COUNT(DISTINCT p.prescription_id) as num_prescriptions, SUM(pd.quantity * ph.unit_price) as total_cost " +
                             "FROM prescriptions p " +
                             "LEFT JOIN prescription_details pd ON p.prescription_id = pd.prescription_id " +
                             "LEFT JOIN pharmacy ph ON pd.medication_id = ph.medication_id ";
                if (!period.isEmpty()) sql += "WHERE p.prescription_date LIKE ? ";
                sql += "GROUP BY p.status_id";
                
                int totalRx = 0;
                int dispensedRx = 0;
                int cancelledRx = 0;
                double undispensedCost = 0.0;
                double dispensedCost = 0.0;

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (!period.isEmpty()) stmt.setString(1, "%" + period + "%");
                    java.sql.ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        int status = rs.getInt("status_id");
                        int count = rs.getInt("num_prescriptions");
                        double cost = rs.getDouble("total_cost");
                        totalRx += count;
                        if (status == 1) { // Pending
                            undispensedCost += cost;
                        } else if (status == 2) { // Dispensed
                            dispensedRx += count;
                            dispensedCost += cost;
                        } else if (status == 3) { // Cancelled
                            cancelledRx += count;
                        }
                    }
                }
                if ("USD".equals(SystemSettings.currency)) {
                    undispensedCost /= 58.0;
                    dispensedCost /= 58.0;
                }
                String symbol = SystemSettings.getCurrencySymbol();
                sb.append("Total Prescriptions: ").append(totalRx).append("\n");
                sb.append("Total Dispensed: ").append(dispensedRx).append("\n");
                sb.append("Total Cancelled: ").append(cancelledRx).append("\n\n");
                sb.append("Total Cost of Undispensed Medicines: ").append(symbol).append(String.format("%.2f", undispensedCost)).append("\n");
                sb.append("Total Cost of Dispensed Medicines: ").append(symbol).append(String.format("%.2f", dispensedCost)).append("\n");

            } else if (scope.contains("Inventory")) {
                int totalMeds = 0;
                int inStock = 0;
                int lowStock = 0;
                int discontinued = 0;
                StringBuilder lowStockNames = new StringBuilder();

                String sql = "SELECT brand_name, generic_name, current_stock, reorder_level, status_id FROM pharmacy";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    java.sql.ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        totalMeds++;
                        int stock = rs.getInt("current_stock");
                        int reorder = rs.getInt("reorder_level");
                        int status = rs.getInt("status_id");
                        String name = rs.getString("brand_name");
                        if (name == null || name.isEmpty()) name = rs.getString("generic_name");

                        if (status == 2) {
                            discontinued++;
                        } else if (stock <= reorder) {
                            lowStock++;
                            lowStockNames.append(" - ").append(name).append(" (Stock: ").append(stock).append(")\n");
                        } else {
                            inStock++;
                        }
                    }
                }
                sb.append("Total Unique Medicines: ").append(totalMeds).append("\n");
                sb.append("Medicines In Stock (Healthy Level): ").append(inStock).append("\n");
                sb.append("Medicines Discontinued: ").append(discontinued).append("\n");
                sb.append("Medicines Low Stock: ").append(lowStock).append("\n");
                if (lowStock > 0) {
                    sb.append("\nItems currently Low Stock:\n");
                    sb.append(lowStockNames.toString());
                }
            } else if (scope.contains("Appointments")) {
                String sql = "SELECT COUNT(*) as cnt, " +
                             "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as scheduled, " +
                             "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as completed, " +
                             "SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as cancelled, " +
                             "SUM(CASE WHEN status_id = 4 THEN 1 ELSE 0 END) as noshow " +
                             "FROM appointments";
                if (period != null && !period.trim().isEmpty()) {
                    sql += " WHERE DATE_FORMAT(appointment_date, '%M %Y') = ?";
                }
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (period != null && !period.trim().isEmpty()) {
                        stmt.setString(1, period.trim());
                    }
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        sb.append("Total Appointments: ").append(rs.getInt("cnt")).append("\n\n");
                        sb.append("[ By Status ]\n");
                        sb.append("Scheduled: ").append(rs.getInt("scheduled")).append("\n");
                        sb.append("Completed: ").append(rs.getInt("completed")).append("\n");
                        sb.append("Cancelled: ").append(rs.getInt("cancelled")).append("\n");
                        sb.append("No-Show: ").append(rs.getInt("noshow")).append("\n");
                    }
                }
            } else if (scope.contains("Staff") || scope.contains("HR")) {
                String sql = "SELECT COUNT(*) as cnt, " +
                             "SUM(CASE WHEN role = 'Doctor' THEN 1 ELSE 0 END) as docs, " +
                             "SUM(CASE WHEN role = 'Nurse' THEN 1 ELSE 0 END) as nurses, " +
                             "SUM(CASE WHEN role = 'Admin' THEN 1 ELSE 0 END) as admins, " +
                             "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as active, " +
                             "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as on_leave, " +
                             "SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as contract, " +
                             "SUM(CASE WHEN status_id = 4 THEN 1 ELSE 0 END) as inactive " +
                             "FROM users";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        sb.append("Total Staff Members: ").append(rs.getInt("cnt")).append("\n\n");
                        sb.append("[ By Role ]\n");
                        sb.append("Doctors: ").append(rs.getInt("docs")).append("\n");
                        sb.append("Nurses: ").append(rs.getInt("nurses")).append("\n");
                        sb.append("Admins: ").append(rs.getInt("admins")).append("\n\n");
                        sb.append("[ By Status ]\n");
                        sb.append("Active: ").append(rs.getInt("active")).append("\n");
                        sb.append("On Leave: ").append(rs.getInt("on_leave")).append("\n");
                        sb.append("Contract: ").append(rs.getInt("contract")).append("\n");
                        sb.append("Inactive: ").append(rs.getInt("inactive")).append("\n");
                    }
                }
            } else if (scope.contains("Medical Records")) {
                String sql = "SELECT COUNT(*) as cnt, " +
                             "SUM(CASE WHEN record_type_id = 1 THEN 1 ELSE 0 END) as vitals, " +
                             "SUM(CASE WHEN record_type_id = 2 THEN 1 ELSE 0 END) as progress, " +
                             "SUM(CASE WHEN record_type_id = 3 THEN 1 ELSE 0 END) as discharge, " +
                             "SUM(CASE WHEN record_type_id = 4 THEN 1 ELSE 0 END) as lab " +
                             "FROM medical_records";
                if (period != null && !period.trim().isEmpty()) {
                    sql += " WHERE DATE_FORMAT(record_datetime, '%M %Y') = ?";
                }
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (period != null && !period.trim().isEmpty()) {
                        stmt.setString(1, period.trim());
                    }
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        sb.append("Total Medical Records Logged: ").append(rs.getInt("cnt")).append("\n\n");
                        sb.append("[ By Record Type ]\n");
                        sb.append("Intake Vitals: ").append(rs.getInt("vitals")).append("\n");
                        sb.append("Progress Notes: ").append(rs.getInt("progress")).append("\n");
                        sb.append("Discharge Summaries: ").append(rs.getInt("discharge")).append("\n");
                        sb.append("Lab Results: ").append(rs.getInt("lab")).append("\n");
                    }
                }
            } else {
                sb.append("No automated statistics available for this category.\n");
            }
        } catch (SQLException ex) {
            sb.append("Error fetching data: ").append(ex.getMessage()).append("\n");
        }
        return sb.toString();
    }
}