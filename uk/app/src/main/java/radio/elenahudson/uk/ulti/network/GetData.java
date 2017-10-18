package radio.elenahudson.uk.ulti.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import radio.elenahudson.uk.RadioObject;
import radio.elenahudson.uk.ulti.VolumeControl;


public class GetData {
    public static void GetData(ArrayList<RadioObject> arrayList, ArrayList<String> arrayListCategory) {
        try {
            String s = new ReadStringFromServer().ReadStringFromServer(VolumeControl.totalapi);
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
                    if (arrayListCategory.contains(sonRadio.category)) {
                    } else {
                        arrayListCategory.add(sonRadio.category);
                    }
                }


                arrayList.add(sonRadio);
            }
            arrayListCategory.add("Other");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
