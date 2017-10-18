package radio.elenahudson.uk.ulti.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import radio.elenahudson.uk.RadioObject;


public class GetArraylist {
    public static ArrayList<RadioObject> getAraylist(Context context, String name) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(name, null);
        Type type = new TypeToken<ArrayList<RadioObject>>() {
        }.getType();
        ArrayList<RadioObject> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}
