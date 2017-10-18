package radio.elenahudson.uk.ulti.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;

import radio.elenahudson.uk.RadioObject;


public class SaveArraylist {
    public static void saveArraylist(Context context, String name, ArrayList<RadioObject> arrayList) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(arrayList);

        editor.putString(name, json);
        editor.commit();
    }
}
