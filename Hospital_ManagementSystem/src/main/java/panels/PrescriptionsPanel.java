/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

<<<<<<< HEAD
import dialogs.AddPrescriptionDialog;
import constants.ColorsTheme;
import constants.FontsTheme;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import java.awt.Color;
import java.awt.Image;
>>>>>>> Agulto
import javax.swing.*;

/**
 *
 * @author Arabella
 */
<<<<<<< HEAD
public class PrescriptionsPanel extends JPanel implements ActionListener{
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlDispense, pnlCancel, pnlCard, pnlTop;
    private JLabel lblDetails, lblPrescription, lblTitle, lblValue, lblHead;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd;
    private JTable table;
    private JScrollPane scrollTable;
    
    
    public PrescriptionsPanel() {
=======
public class PrescriptionsPanel extends JPanel {
    
    private JPanel pnlMiddle, pnlSearch, pnlPending, pnlDispense, pnlCancel, cardPanel, TopPanel;
    private JLabel lblDetails, lblPrescription, lblTitle, lblValue;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, btnAdd, btnSearch1;
   // private ImagePanel imgPatient;
    
    
    PrescriptionsPanel() {
>>>>>>> Agulto
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
        
        btnAdd = new JButton("+  New Prescription");
        btnAdd.setBounds(1280, 40, 250, 50); 
        btnAdd.setFont(FontsTheme.Buttons);
        btnAdd.setBackground(ColorsTheme.Add_Confirm);
        btnAdd.setForeground(ColorsTheme.Text_White);
<<<<<<< HEAD
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(this);
        add(btnAdd);
        
=======
        add(btnAdd);
        
        //Search Bar
>>>>>>> Agulto
        txtSearch = new JTextField("Search by patient name or patient id...");
        txtSearch.setBounds(80, 20, 1100, 40);
        txtSearch.setFont(FontsTheme.Info_Texts);
        txtSearch.setForeground(ColorsTheme.Text_Gray);
        pnlSearch.add(txtSearch);
<<<<<<< HEAD
      
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search);
        btnSearch.setForeground(ColorsTheme.Text_White);
        btnSearch.setFocusPainted(false);
=======
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/search.png"));

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnSearch1 = new JButton(scaledIcon); // use your class variable
        btnSearch1.setBounds(40, 20, 40, 39);
        btnSearch1.setBackground(Color.decode("#3A2A75"));
        btnSearch1.setBorder(BorderFactory.createEmptyBorder());
        btnSearch1.setEnabled(false);
        pnlSearch.add(btnSearch1);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1200, 20, 130, 40); 
        btnSearch.setFont(FontsTheme.Buttons);
        btnSearch.setBackground(ColorsTheme.Search_Button);
        btnSearch.setForeground(ColorsTheme.Text_White);
>>>>>>> Agulto
        pnlSearch.add(btnSearch);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1350, 20, 130, 40); 
        btnRefresh.setFont(FontsTheme.Buttons);
        btnRefresh.setBackground(ColorsTheme.Text_Gray);
        btnRefresh.setForeground(ColorsTheme.Text_White);
<<<<<<< HEAD
        btnRefresh.setFocusPainted(false);
        pnlSearch.add(btnRefresh);
        
        
        lblPrescription = new JLabel("Prescription");
=======
        pnlSearch.add(btnRefresh);
        
        
        
        
        lblPrescription = new JLabel("Prescriptions");
>>>>>>> Agulto
        lblPrescription.setBounds(30, 30, 500, 40);
        lblPrescription.setFont(FontsTheme.Bold_Texts);
        lblPrescription.setForeground(ColorsTheme.Text_Black);
        add(lblPrescription);

<<<<<<< HEAD
        lblDetails = new JLabel("Manage and dispense prescriptions.");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Gray);
        add(lblDetails);
        
        
        //Card
        pnlPending = createCard("Pending", "30", ColorsTheme.Orange);
=======
        lblDetails = new JLabel("Manage and dispense prescriptions");
        lblDetails.setBounds(30, 70, 500, 40);
        lblDetails.setFont(FontsTheme.Plain_Texts);
        lblDetails.setForeground(ColorsTheme.Text_Black);
        add(lblDetails);
        
        
        pnlPending = createCard(
                "Pending",
                "30");
>>>>>>> Agulto
        pnlPending.setBounds(170, 130, 400, 110);
        add(pnlPending);
        
        
<<<<<<< HEAD
        pnlDispense = createCard("Dispensed Today", "17", ColorsTheme.Green);
=======
        pnlDispense = createCard(
                "Dispensed Today",
                "17");
>>>>>>> Agulto
        pnlDispense.setBounds(620, 130, 400, 110);
        add(pnlDispense);
        
       
<<<<<<< HEAD
        pnlCancel = createCard("Cancelled", "4", ColorsTheme.Red);
=======
        pnlCancel = createCard(
                "Cancelled",
                "4");
>>>>>>> Agulto
        pnlCancel.setBounds(1070, 130, 400, 110);
        add(pnlCancel);
        
        
<<<<<<< HEAD
        //Table
        String[] columns = {"Patient Name", "Doctor", "Date", "Medications", "Status"};
        
        Object[][] data = {
            {"John Smith", "Dr. Chen", "May 13, 2026", "3x daily / 20 days", "Pending"},
            {"Sarah Johnson", "Dr. Williams", "May 14, 2026", "2x daily / 3 days", "Dispensed"},
            {"Maria Leonora", "Dr. Robert Chen", "May 15, 2026", "Amoxicillin 500mg (3x daily / 7 days)", "Active"},
            {"Jose Felipe", "Dr. Sarah Jenkins", "May 15, 2026", "Metformin 850mg (2x daily / 30 days)", "Active"},
            {"Angela Cruz", "Dr. Alan Reyes", "May 14, 2026", "Paracetamol 500mg (As needed for pain)", "Completed"},
            {"Mark Anthony", "Dr. Grace Torres", "May 14, 2026", "Losartan 50mg (Once daily / 60 days)", "Active"},
            {"Sophia Reyes", "Dr. Robert Chen", "May 13, 2026", "Cetirizine 10mg (Once nightly / 10 days)", "Completed"},
            {"Daniel Garcia", "Dr. David Kim", "May 12, 2026", "Ibuprofen 400mg (Every 8 hours / 5 days)", "Cancelled"},
            {"Christine Mae", "Dr. Sarah Jenkins", "May 12, 2026", "Atorvastatin 20mg (Once nightly / 30 days)", "Active"},
            {"Nathaniel Ong", "Dr. Alan Reyes", "May 10, 2026", "Omeprazole 20mg (Before breakfast / 14 days)", "Completed"},
            {"Francis Mendoza", "Dr. Grace Torres", "May 09, 2026", "Salbutamol Inhaler (1-2 puffs as needed)", "Active"},
            {"Jasmine Aquino", "Dr. David Kim", "May 08, 2026", "Prednisone 5mg (Once daily / 5 days)", "Completed"}
        };
        
        table = new JTable(data, columns);
        table.setRowHeight(50);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(FontsTheme.Title_Texts);
        table.setFont(FontsTheme.Info_Texts);
        table.getTableHeader().setBackground(ColorsTheme.Header); 
        table.getTableHeader().setForeground(ColorsTheme.Text_White);

        scrollTable = new JScrollPane(table);
        scrollTable.setBounds(0, 60, 1500, 560);
        pnlMiddle.add(scrollTable);
        
        lblHead = new JLabel("Recent Prescription");
        lblHead.setBounds(30, 20, 300, 30);
        lblHead.setFont(FontsTheme.Title_Texts);
        lblHead.setForeground(ColorsTheme.Text_Black);
        pnlMiddle.add(lblHead);
        
=======
>>>>>>> Agulto
        
    }

    
<<<<<<< HEAD
    public JPanel createCard(String title, String value, Color topLineColor) {

        pnlCard = new JPanel();
        pnlCard.setLayout(null);
        pnlCard.setBackground(ColorsTheme.Main_Card);
        
        pnlTop = new JPanel();
        pnlTop.setBounds(0, 0, 400, 10);
        pnlTop.setBackground(topLineColor);
        pnlCard.add(pnlTop);

=======
    public JPanel createCard(String title, String value) {

        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(ColorsTheme.Main_Card);
        
        TopPanel = new JPanel();
        TopPanel.setBounds(0, 0, 400, 10);
        TopPanel.setBackground(ColorsTheme.Top_Line);
        cardPanel.add(TopPanel);


        //Title
>>>>>>> Agulto
        lblTitle = new JLabel(title);
        lblTitle.setBounds(20, 25, 250, 25);
        lblTitle.setForeground(ColorsTheme.Text_Black);
        lblTitle.setFont(FontsTheme.Plain_Texts);
<<<<<<< HEAD
        pnlCard.add(lblTitle);

=======
        cardPanel.add(lblTitle);


        //Value
>>>>>>> Agulto
        lblValue = new JLabel(value);
        lblValue.setBounds(20, 50, 200, 50);
        lblValue.setForeground(Color.BLACK);
        lblValue.setFont(FontsTheme.Bold_Texts);
<<<<<<< HEAD
        pnlCard.add(lblValue);


        return pnlCard;
        
        
            }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
        AddPrescriptionDialog prescription = new AddPrescriptionDialog();
        prescription.setVisible(true);
        }
    }
=======
        cardPanel.add(lblValue);


        return cardPanel;
        
        
            }
>>>>>>> Agulto
    }
        

   

