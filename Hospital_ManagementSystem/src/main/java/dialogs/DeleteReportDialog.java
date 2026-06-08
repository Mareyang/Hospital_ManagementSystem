package dialogs;

import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class DeleteReportDialog extends JDialog implements ActionListener {
    
    private JLabel lblTitle, lblSubtitle, lblCateg, lblName, lblNote, lblBy, lblDate, lblID, lblPeriod; 
    private JButton btnReportDetails, btnWrite, btnConfirm, btnCancel;
    private JPanel pnlContent;
    private JTextField txtName, txtBy, txtDate, txtID, txtPeriod;
    private JComboBox<String> cmbCateg;
    private JTextArea txaNote;
    private JScrollPane scrollNote;
    
    private static final String[] categs = {" ", "Admissions Summary", "Billing and Revenue", "Pharmacy Dispensation", "Emergency Logs"};
    
    private String currentReportId;

    public DeleteReportDialog(String reportId) {
        this.currentReportId = reportId;
        
        setSize(1050, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);
        
        lblTitle = new JLabel("Delete Report");
        lblTitle.setBounds(30, 10, 500, 40);
        lblTitle.setFont(FontsTheme.Bold_Texts);
        lblTitle.setForeground(ColorsTheme.Delete_Urgent);
        add(lblTitle);
        
        lblSubtitle = new JLabel("Review report information below. Are you sure you want to delete this report record?");
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
       
        btnWrite = new JButton("Executive Summary");
        btnWrite.setBounds(290, 100, 250, 40);
        btnWrite.setFont(FontsTheme.Buttons);
        btnWrite.setForeground(ColorsTheme.Text_White);
        btnWrite.setBackground(ColorsTheme.Header);
        btnWrite.setFocusPainted(false);
        add(btnWrite);
        
        pnlContent = new JPanel();
        pnlContent.setLayout(null);
        pnlContent.setBounds(40, 140, 950, 300);
        pnlContent.setBackground(ColorsTheme.Main_Card);
        add(pnlContent);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 450, 200, 30);
        btnCancel.setFont(FontsTheme.Buttons);
        btnCancel.setForeground(ColorsTheme.Text_White);
        btnCancel.setBackground(ColorsTheme.Cancel);
        btnCancel.setFocusPainted(false);
        add(btnCancel);
        
        btnConfirm = new JButton("Confirm Delete");
        btnConfirm.setBounds(690, 450, 300, 30);
        btnConfirm.setFont(FontsTheme.Buttons);
        btnConfirm.setForeground(ColorsTheme.Text_White);
        btnConfirm.setBackground(ColorsTheme.Delete_Urgent);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);
                
        btnReportDetails.addActionListener(this);
        btnWrite.addActionListener(this);
        btnConfirm.addActionListener(this);
        btnCancel.addActionListener(this);
        
        initializeForms();
        loadReportData();
        showGenerateReport();
    }
    
    private void initializeForms() {
        lblName = new JLabel("Report Title : ");
        lblName.setBounds(40, 60, 200, 30);
        lblName.setFont(FontsTheme.Plain_Texts);
        lblName.setForeground(ColorsTheme.Text_Black);
        
        txtName = new JTextField("");
        txtName.setBounds(220, 60, 230, 30);
        txtName.setFont(FontsTheme.Plain_Texts);
        txtName.setEditable(false);
        
        lblID = new JLabel("Report ID : ");
        lblID.setBounds(40, 130, 200, 30);
        lblID.setFont(FontsTheme.Plain_Texts);
        lblID.setForeground(ColorsTheme.Text_Black);
        
        txtID = new JTextField("");
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
        txtBy.setEditable(false);
        
        lblDate = new JLabel("Date of Report : ");
        lblDate.setBounds(510, 60, 200, 30);
        lblDate.setFont(FontsTheme.Plain_Texts);
        lblDate.setForeground(ColorsTheme.Text_Black);
        
        txtDate = new JTextField("");
        txtDate.setBounds(690, 60, 230, 30);
        txtDate.setFont(FontsTheme.Plain_Texts);
        txtDate.setEditable(false);
        
        lblPeriod = new JLabel("Reporting Period : ");
        lblPeriod.setBounds(510, 130, 200, 30);
        lblPeriod.setFont(FontsTheme.Plain_Texts);
        lblPeriod.setForeground(ColorsTheme.Text_Black);
        
        txtPeriod = new JTextField("");
        txtPeriod.setBounds(690, 130, 230, 30);
        txtPeriod.setFont(FontsTheme.Plain_Texts);
        txtPeriod.setEditable(false);
        
        lblCateg = new JLabel("Report Scope : ");
        lblCateg.setBounds(510, 200, 200, 30);
        lblCateg.setFont(FontsTheme.Plain_Texts);
        lblCateg.setForeground(ColorsTheme.Text_Black);
        
        cmbCateg = new JComboBox<>(categs);
        cmbCateg.setBounds(690, 200, 230, 30);
        cmbCateg.setFont(FontsTheme.Plain_Texts);
        cmbCateg.setBackground(ColorsTheme.Main_Card);
        cmbCateg.setEnabled(false);
        
        lblNote = new JLabel("Executive Summary: ");
        lblNote.setBounds(50, 10, 300, 30);
        lblNote.setFont(FontsTheme.Title_Texts);
        lblNote.setForeground(ColorsTheme.Text_Black);
        
        txaNote = new JTextArea(" ");
        txaNote.setFont(FontsTheme.Info_Texts);
        txaNote.setForeground(ColorsTheme.Text_Gray);
        txaNote.setLineWrap(true);
        txaNote.setWrapStyleWord(true);
        txaNote.setEditable(false);
        
        scrollNote = new JScrollPane(txaNote);
        scrollNote.setBounds(40, 50, 880, 230);
    }
    
    private void loadReportData() {
        String sql = "SELECT * FROM hospital_reports WHERE report_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String cleanId = currentReportId.toUpperCase().replace("RPT-", "");
            stmt.setString(1, cleanId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int rawId = rs.getInt("report_id");
                txtID.setText(String.format("RPT-%03d", rawId));
                txtName.setText(rs.getString("report_name"));
                
                String scope = rs.getString("report_type");
                if (scope != null) cmbCateg.setSelectedItem(scope);
                
                txtBy.setText(rs.getString("generated_by"));
                txtDate.setText(rs.getString("date_generated"));
                txtPeriod.setText(rs.getString("reporting_period"));
                
                String summary = rs.getString("executive_summary");
                if (summary != null && !summary.isEmpty()) {
                    txaNote.setText(summary);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load report data:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
        } else if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnConfirm) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM hospital_reports WHERE report_id = ?")) {
                String cleanId = currentReportId.toUpperCase().replace("RPT-", "");
                stmt.setString(1, cleanId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Report record deleted successfully!", "Report Deleted", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to delete report:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
