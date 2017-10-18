package radio.elenahudson.uk.ulti.service;

import android.content.Context;
import android.content.Intent;

import radio.elenahudson.uk.ulti.Constants;


public class startNotification {

    public static void startNotification(Context context) {
        Intent serviceIntent = new Intent(context, NotificationRadio.class);
        serviceIntent.setAction(Constants.STARTNOTIFICATION_ACTION);
        context.startService(serviceIntent);
    }
}
