package radio.elenahudson.uk.ulti.general;


public class GetCharacters {
    public static String GetCharacters(String str, Integer n){
        String Get =str.substring(0, Math.min(str.length(), n));
        return Get;
    }
}
