package constants;

import java.awt.Color;

public class ColorsTheme {
    
    // Main Colors
    public static Color Side_Panel = Color.decode("#1E3163");
    public static Color Middle_Panel = Color.decode("#F4F4F4");
    public static Color Main_Card = Color.WHITE;
    public static Color Top_Line = Color.decode("#3BC1A8");
    public static Color Header = Color.decode("#406AAF");

    // Dashboard Search Bar
    public static Color Search_fg = Color.decode("#C4E2F5");
    public static Color Search_bg = Color.decode("#4B5694");
    
    // Text Colors
    public static Color Text_White = Color.WHITE;
    public static Color Text_Black = Color.BLACK;
    public static Color Text_Gray = Color.GRAY;
    
    // Status Colors
    public static Color Active_Button = Color.decode("#4B5694");
    public static Color Search = Color.decode("#406AAF");
    public static Color Gray = Color.decode("#EDE9E6");
    public static Color Green = Color.decode("#48A111");
    public static Color NeonGreen = Color.GREEN;
    public static Color Yellow = Color.decode("#FFE400");
    public static Color Red = Color.decode("#FF1700");
    public static Color Blue = Color.decode("#4300FF");
    public static Color Orange = Color.decode("#FF8E00");
    public static Color Cancel = Color.decode("#BFC6C4");
    public static Color Delete_Urgent = Color.decode("#A82323");
    public static Color Add_Confirm = Color.decode("#48A111");

    // Status Colors
    public static Color Search_Button = Color.decode("#406AAF");
    public static Color Gray_Button = Color.decode("#EDE9E6");
    public static Color Update_Pending = Color.decode("#FFC107");
    
    // Dashboard Panel Colors
    public static Color Cardiology_Color = new Color(52, 152, 219);
    public static Color Orthophedics_Color = new Color(46, 204, 113);
    public static Color Emergency_Color = new Color(231, 76, 60);
    public static Color Neurology_Color = new Color(155, 89, 182);
    public static Color Pediatrics_Color = new Color(241, 196, 15);

    // Dynamic Theme State
    public static boolean isDarkMode = false;

    public static void applyTheme(boolean darkMode) {
        isDarkMode = darkMode;

        if (darkMode) {
            Middle_Panel = Color.decode("#121214"); // modernized slate black background
            Main_Card = Color.decode("#1E1E24"); // modernized slate gray card
            Text_Black = Color.WHITE;
        } else {
            Middle_Panel = Color.decode("#F4F4F4"); // light background
            Main_Card = Color.WHITE; // white card
            Text_Black = Color.BLACK;
        }
        
        // Ensure UI components use the correct theme colors globally
        javax.swing.UIManager.put("TextField.background", Main_Card);
        javax.swing.UIManager.put("TextField.foreground", Text_Black);
        javax.swing.UIManager.put("TextArea.background", Main_Card);
        javax.swing.UIManager.put("TextArea.foreground", Text_Black);
        javax.swing.UIManager.put("ComboBox.background", Main_Card);
        javax.swing.UIManager.put("ComboBox.foreground", Text_Black);
        javax.swing.UIManager.put("ComboBox.selectionBackground", isDarkMode ? Color.decode("#334155") : Color.decode("#E2E8F0"));
        javax.swing.UIManager.put("ComboBox.selectionForeground", Text_Black);
    }
}