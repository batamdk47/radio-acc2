package radio.elenahudson.uk.ulti.general;

public class GetCharractersLast {

    public static String GetCharractersLast(String word, int n) {
        String str = null;
        if (word.length() == n) {
            str = word;
        } else if (word.length() > n) {
            str = word.substring(word.length() - n);
        }
        return str;
    }
}


