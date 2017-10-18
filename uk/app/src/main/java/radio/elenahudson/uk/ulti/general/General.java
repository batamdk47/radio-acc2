package radio.elenahudson.uk.ulti.general;

import android.content.Context;
import android.content.SharedPreferences;

import radio.elenahudson.uk.ulti.privacy.PrivacyPolicy;


public class General {

    public static String KEY = "elenahudson";
    public static String APARTOFAPI1 = GetCharacters.GetCharacters(PrivacyPolicy.str,31);

    public static void saveStringPreference(Context context, String name, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static int getIntPreference(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getInt(name, 0);
    }

    public static void saveIntPreference(Context context, String name, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public static long getLongPreference(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getLong(name, 0);
    }

    public static void saveLongPreference(Context context, String name, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putLong(name, value);
        editor.commit();
    }

    public static String getStringPreference(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return prefs.getString(name, null);
    }

}