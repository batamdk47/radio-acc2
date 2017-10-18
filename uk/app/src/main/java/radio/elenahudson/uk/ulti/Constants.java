package radio.elenahudson.uk.ulti;


import radio.elenahudson.uk.ulti.general.GetCharacters;
import radio.elenahudson.uk.ulti.general.GetCharractersLast;
import radio.elenahudson.uk.ulti.privacy.PrivacyPolicy;

public class Constants {
    public static String MAIN_ACTION = "Action.main";
    public static String STARTNOTIFICATION_ACTION = "Action.startnotification";
    public static String CLOSENOTIFICATION_ACTION = "Action.closenotification";
    public static String STOPCOUNTDOWNTIMER_ACTION = "Action.stopcountdowntimer";
    public static String APARTOFAPI3= GetCharacters.GetCharacters(PrivacyPolicy.PACKAGENAME,5);
    public static String APARTOFAPI4 = GetCharractersLast.GetCharractersLast(PrivacyPolicy.PACKAGENAME,3);

    public static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    public static final int BUFFER_SEGMENT_COUNT = 256;
    public interface NOTIFICATION_ID {
        int NOTIFICATION_SERVICE = 1;
    }

}
