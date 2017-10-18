package radio.elenahudson.uk.ulti.service;

import android.content.Context;
import android.content.Intent;

import radio.elenahudson.uk.ulti.Constants;


public class StopNotification {
    public static void StopNotification(Context context) {
        Intent serviceIntent = new Intent(context, NotificationRadio.class);
        serviceIntent.setAction(Constants.CLOSENOTIFICATION_ACTION);
        context.startService(serviceIntent);
    }
}
