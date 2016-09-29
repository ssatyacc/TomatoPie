package hackerearth.satya.tomatopie;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String LOCATION_PREFS = "LOCATION_PREFS";
    private static final String CITY_NAME = "CITY_NAME";
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPrefs(Context context) {
        this.context = context;
        this.sharedPrefs = context.getSharedPreferences(LOCATION_PREFS, Activity.MODE_PRIVATE);
        this.editor = sharedPrefs.edit();
    }

    public String getCityName() {
        return sharedPrefs.getString(CITY_NAME, "");
    }

    public void setCityName(String cityName) {
        editor.putString(CITY_NAME, cityName).apply();
    }
}
