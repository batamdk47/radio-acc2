package radio.elenahudson.uk.ulti.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import radio.elenahudson.uk.RadioObject;
import radio.elenahudson.uk.ulti.Constants;
import radio.elenahudson.uk.ulti.general.General;
import radio.elenahudson.uk.ulti.general.GetCharractersLast;
import radio.elenahudson.uk.ulti.privacy.PrivacyPolicy;


public class GetDataCountry {
    public static void GetData(ArrayList<RadioObject> arrayList, ArrayList<String> arrayListCategoryCountry, String string) {
        try {
             String str = General.APARTOFAPI1 + General.KEY + GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,1)+ Constants.APARTOFAPI3+ GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,1)+"."+string + GetCharractersLast.GetCharractersLast(PrivacyPolicy.str,4);

            String s = new ReadStringFromServer().ReadStringFromServer(str);
            JSONArray mJson = new JSONArray(s);
            int j = mJson.length();
            for (int i = 0; i < j; i++) {
                JSONObject son = mJson.getJSONObject(i);
                RadioObject sonRadio = new RadioObject();
                sonRadio.name = son.getString("name").trim();
                sonRadio.link = son.getString("link");

                if (son.has("bitrate")) {
                    if (son.getString("bitrate").equals("")) {
                        sonRadio.bitrate = "128k";
                    } else {
                        sonRadio.bitrate = son.getString("bitrate");
                    }

                } else {
                    sonRadio.bitrate = "128k";
                }


                if (son.getString("pic").equals("")) {
                    sonRadio.pic = "http://cdn-radiotime-logos.tunein.com/s0q.png";
                } else {
                    sonRadio.pic = son.getString("pic");
                }
                if (son.has("location")) {
                    if (son.getString("location").equals("")) {
                        sonRadio.location = "in United States";
                    } else {
                        sonRadio.location = son.getString("location");
                    }
                } else {
                    sonRadio.location = "US";
                }
                if (son.has("category")) {
                    if (son.getString("category").equals("")) {
                        sonRadio.category = "All";
                    } else {
                        sonRadio.category = son.getString("category");
                    }
                } else {
                    sonRadio.category = "All";
                }

                if (!sonRadio.category.equals("Other")) {
                    if (arrayListCategoryCountry.contains(sonRadio.category)) {
                    } else {
                        arrayListCategoryCountry.add(sonRadio.category);
                    }
                }


                arrayList.add(sonRadio);
            }
            arrayListCategoryCountry.add("Other");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}