package radio.elenahudson.uk.ulti.network;


import radio.elenahudson.uk.ulti.Constants;
import radio.elenahudson.uk.ulti.general.General;
import radio.elenahudson.uk.ulti.general.GetCharractersLast;
import radio.elenahudson.uk.ulti.privacy.PrivacyPolicy;

public class TotalAPI {
    public static String totalapitest = General.APARTOFAPI1 + General.KEY + GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,8)+ Constants.APARTOFAPI3+GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,7)+Constants.APARTOFAPI4 + GetCharractersLast.GetCharractersLast(PrivacyPolicy.str,12);
}
