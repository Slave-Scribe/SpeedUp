package org.dark.speedup;

import com.fs.starfarer.api.Global;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SU_LoadSettings {
    private static final String OLD_SETTINGS_FILE = "SPEED_UP.ini";
    private static final String SETTINGS_FILE = "data/config/SPEED_UP.json";

    // Use legacy settings file if it exists, if it fails to load try the new file
    // Throw error if both files fail
    @SuppressWarnings("ReassignedVariable")
    public static JSONObject loadSettings() throws IOException, JSONException {
        JSONObject settings = new JSONObject();
        try {
            settings = Global.getSettings().loadJSON(OLD_SETTINGS_FILE);
        } catch (Exception ignored) {
        }

        if (settings.length() == 0) {
            settings = Global.getSettings().loadJSON(SETTINGS_FILE);
        }

        return settings;
    }

    public static JSONArray getOptions(JSONObject settings, String optionName) throws JSONException {
        return settings.getJSONArray(optionName);
    }
}
