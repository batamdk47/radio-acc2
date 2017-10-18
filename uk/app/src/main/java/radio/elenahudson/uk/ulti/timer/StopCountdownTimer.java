package radio.elenahudson.uk.ulti.timer;

import android.content.Context;
import android.content.Intent;

import radio.elenahudson.uk.ulti.Constants;
import radio.elenahudson.uk.ulti.service.NotificationRadio;


public class StopCountdownTimer {
    public static void StopNotification(Context context) {
        Intent serviceIntent = new Intent(context, NotificationRadio.class);
        serviceIntent.setAction(Constants.STOPCOUNTDOWNTIMER_ACTION);
        context.startService(serviceIntent);
    }
}
