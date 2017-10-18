package radio.elenahudson.uk.country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import radio.elenahudson.uk.CountryObject;


public class ArrayCountry {
    public static ArrayList<CountryObject> ArrayCountry(){
        ArrayList<CountryObject> arrayList = new ArrayList<>();
        try {


            String str = "[{\"country\":\"usa\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/united-states.png\"},{\"country\":\"uk\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/united-kingdom.png\"},{\"country\":\"germany\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/germany.png\"},{\"country\":\"spain\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/spain.png\"},{\"country\":\"hungary\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/hungary.png\"},{\"country\":\"japan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/japan.png\"},{\"country\":\"india\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/india.png\"},{\"country\":\"romania\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/romania.png\"},{\"country\":\"mexico\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/mexico.png\"},{\"country\":\"italy\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/italy.png\"},{\"country\":\"czech\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/czech.png\"},{\"country\":\"canada\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/canada.png\"},{\"country\":\"brazil\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/brazil.png\"},{\"country\":\"australia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/australia.png\"},{\"country\":\"bangladesh\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/bangladesh.png\"},{\"country\":\"argentia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/argentia.png\"},{\"country\":\"costa rica\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/costa-rica.png\"},{\"country\":\"bulgaria\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/bulgaria.png\"},{\"country\":\"algeria\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/algeria.png\"},{\"country\":\"ireland\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/ireland.png\"},{\"country\":\"cyprus\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/cyprus.png\"},{\"country\":\"honduras\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/honduras.png\"},{\"country\":\"ghana\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/ghana.png\"},{\"country\":\"ecuador\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/ecuador.png\"},{\"country\":\"chile\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/chile.png\"},{\"country\":\"uruguay\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/uruguay.png\"},{\"country\":\"ukraine\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/ukraine.png\"},{\"country\":\"haiti\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/haiti.png\"},{\"country\":\"greece\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/greece.png\"},{\"country\":\"puerto rico\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/puerto-rico.png\"},{\"country\":\"new zealand\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/new-zealand.png\"},{\"country\":\"russia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/russia.png\"},{\"country\":\"france\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/france.png\"},{\"country\":\"denmark\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/denmark.png\"},{\"country\":\"finland\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/finland.png\"},{\"country\":\"egypt\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/egypt.png\"},{\"country\":\"singapore\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/singapore.png\"},{\"country\":\"poland\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/poland.png\"},{\"country\":\"senegal\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/senegal.png\"},{\"country\":\"portugal\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/portugal.png\"},{\"country\":\"peru\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/peru.png\"},{\"country\":\"dominican\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/dominican-republic.png\"},{\"country\":\"malaysia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/malaysia.png\"},{\"country\":\"thailand\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/thailand.png\"},{\"country\":\"lebanon\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/lebanon.png\"},{\"country\":\"sudan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/sudan.png\"},{\"country\":\"nigeria\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/nigeria.png\"},{\"country\":\"myanmar\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/myanmar.png\"},{\"country\":\"cameroon\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/cameroon.png\"},{\"country\":\"moldova\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/moldova.png\"},{\"country\":\"gambia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/gambia.png\"},{\"country\":\"jamaica\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/jamaica.png\"},{\"country\":\"serbia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/serbia.png\"},{\"country\":\"albania\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/albania.png\"},{\"country\":\"slovakia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/slovakia.png\"},{\"country\":\"tunisia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/tunisia.png\"},{\"country\":\"norway\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/norway.png\"},{\"country\":\"morocco\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/morocco.png\"},{\"country\":\"indonesia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/indonesia.png\"},{\"country\":\"colombia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/colombia.png\"},{\"country\":\"kyrgyzstan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/kyrgyzstan.png\"},{\"country\":\"liberia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/liberia.png\"},{\"country\":\"malawi\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/malawi.png\"},{\"country\":\"suriname\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/suriname.png\"},{\"country\":\"iraq\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/iraq.png\"},{\"country\":\"jordan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/jordan.png\"},{\"country\":\"fiji\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/fiji.png\"},{\"country\":\"uzbekistan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/uzbekistan.png\"},{\"country\":\"armenia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/armenia.png\"},{\"country\":\"madagascar\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/madagascar.png\"},{\"country\":\"kurtce\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/kurtce.png\"},{\"country\":\"mauritius\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/mauritius.png\"},{\"country\":\"kuwait\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/kuwait.png\"},{\"country\":\"uganda\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/uganda.png\"},{\"country\":\"oman\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/oman.png\"},{\"country\":\"zambia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/zambia.png\"},{\"country\":\"qatar\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/qatar.png\"},{\"country\":\"lithuania\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/lithuania.png\"},{\"country\":\"tanzania\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/tanzania.png\"},{\"country\":\"khmer\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/cambodia.png\"},{\"country\":\"afghanistan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/afghanistan.png\"},{\"country\":\"mongolia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/mongolia.png\"},{\"country\":\"ethiopia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/ethiopia.png\"},{\"country\":\"togo\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/togo.png\"},{\"country\":\"mauritania\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/mauritania.png\"},{\"country\":\"yemen\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/yemen.png\"},{\"country\":\"macedonia\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/macedonia.png\"},{\"country\":\"kenya\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/kenya.png\"},{\"country\":\"tajikistan\",\"flag\":\"https://storage.googleapis.com/countryradio/flag/tajikistan.png\"}]";
              JSONArray mJson = new JSONArray(str);

            for(int i =0; i<mJson.length();i++){
                JSONObject son = mJson.getJSONObject(i);
                CountryObject countryObject =new CountryObject();
                String ss = son.getString("country");
                String s = String.valueOf(ss.charAt(0)).toUpperCase() +ss.substring(1);
                countryObject.country = s;
                countryObject.flag = son.getString("flag");
                arrayList.add(countryObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
