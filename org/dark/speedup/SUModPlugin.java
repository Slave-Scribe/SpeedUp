package org.dark.speedup;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import org.apache.log4j.Level;
import org.dark.speedup.SU_LoadSettings;
import org.dark.speedup.SU_SpeedUpEveryFrame;
import org.dark.speedup.SU_SpeedUpCampaign;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SUModPlugin extends BaseModPlugin {

    @Override
    public void onApplicationLoad() {
        try {
            JSONObject settings = SU_LoadSettings.loadSettings();
            SU_SpeedUpEveryFrame.reloadSettings(SU_LoadSettings.getOptions(settings, "speedOptions"));
            SU_SpeedUpCampaign.reloadSettings(SU_LoadSettings.getOptions(settings, "speedOptionsCampaign"));
        } catch (IOException | JSONException e) {
            Global.getLogger(SUModPlugin.class).log(Level.ERROR, "SpeedUp load failed: " + e.getMessage());
        }
    }

    @Override
    public void onGameLoad(boolean newGame) {
        Global.getSector().getListenerManager().addListener(new SU_SpeedUpCampaign(), true);
    }

    @Override
    public void onDevModeF8Reload() {
        this.onApplicationLoad();
    }
}
