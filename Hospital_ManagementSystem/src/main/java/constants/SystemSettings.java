package constants;

import java.util.prefs.Preferences;

public class SystemSettings {

    // Persistent User Preferences
    public static int tableRowHeight = 50;
    public static String defaultLandingTab = "dashboard";

    static {
        loadSettings();
    }

    // Load settings from persistent OS preferences storage
    public static void loadSettings() {
        try {
            Preferences prefs = Preferences.userNodeForPackage(SystemSettings.class);
            isDarkMode = prefs.getBoolean("isDarkMode", false);
            tableRowHeight = prefs.getInt("tableRowHeight", 50);
            dashboardRecordLimit = prefs.getInt("dashboardRecordLimit", 50);
            defaultLandingTab = prefs.get("defaultLandingTab", "dashboard");
        } catch (Exception e) {
            // Default fallbacks in case preferences storage is not accessible
            isDarkMode = false;
            tableRowHeight = 50;
            dashboardRecordLimit = 50;
            defaultLandingTab = "dashboard";
        }
        // Sync the loaded theme setting to the ColorsTheme config
        ColorsTheme.applyTheme(isDarkMode);
    }

    // Save settings persistently
    public static void saveSettings(boolean darkMode, int rowHeight, int limit, String landingTab) {
        try {
            Preferences prefs = Preferences.userNodeForPackage(SystemSettings.class);
            prefs.putBoolean("isDarkMode", darkMode);
            prefs.putInt("tableRowHeight", rowHeight);
            prefs.putInt("dashboardRecordLimit", limit);
            prefs.put("defaultLandingTab", landingTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        isDarkMode = darkMode;
        tableRowHeight = rowHeight;
        dashboardRecordLimit = limit;
        defaultLandingTab = landingTab;
        
        // Sync colors
        ColorsTheme.applyTheme(darkMode);
    }
}
