package constants;

import java.util.prefs.Preferences;

public class SystemSettings {

    // Persistent User Preferences
    public static boolean isDarkMode = false;
    public static int tableRowHeight = 50;
    public static int dashboardRecordLimit = 50;
    public static String defaultLandingTab = "dashboard";
    public static String currency = "PHP";

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
            currency = prefs.get("currency", "PHP");
        } catch (Exception e) {
            // Default fallbacks in case preferences storage is not accessible
            isDarkMode = false;
            tableRowHeight = 50;
            dashboardRecordLimit = 50;
            defaultLandingTab = "dashboard";
            currency = "PHP";
        }
        // Sync the loaded theme setting to the ColorsTheme config
        ColorsTheme.applyTheme(isDarkMode);
    }

    // Save settings persistently
    public static void saveSettings(boolean darkMode, int rowHeight, int limit, String landingTab, String selectedCurrency) {
        try {
            Preferences prefs = Preferences.userNodeForPackage(SystemSettings.class);
            prefs.putBoolean("isDarkMode", darkMode);
            prefs.putInt("tableRowHeight", rowHeight);
            prefs.putInt("dashboardRecordLimit", limit);
            prefs.put("defaultLandingTab", landingTab);
            prefs.put("currency", selectedCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        isDarkMode = darkMode;
        tableRowHeight = rowHeight;
        dashboardRecordLimit = limit;
        defaultLandingTab = landingTab;
        currency = selectedCurrency;
        
        // Sync colors
        ColorsTheme.applyTheme(darkMode);
    }

    // Get currency symbol prefix
    public static String getCurrencySymbol() {
        if ("USD".equals(currency)) {
            return "$";
        }
        return "₱";
    }
}
