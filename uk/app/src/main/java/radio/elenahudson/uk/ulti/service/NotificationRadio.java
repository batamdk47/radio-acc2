package radio.elenahudson.uk.ulti.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import radio.elenahudson.uk.HomeActivity;
import radio.elenahudson.uk.R;
import radio.elenahudson.uk.ulti.Constants;
import radio.elenahudson.uk.ulti.general.General;

public class NotificationRadio extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction().equals(Constants.STARTNOTIFICATION_ACTION)) {
            showNotification();
        } else if (intent != null && intent.getAction().equals(Constants.CLOSENOTIFICATION_ACTION)) {
            if (HomeActivity.playercontrol.isPlaying())
                // delete notification
                HomeActivity.exoPlayer.setPlayWhenReady(false);
                stopForeground(true);
                stopSelf();

        } else if (intent != null && intent.getAction().equals(Constants.STOPCOUNTDOWNTIMER_ACTION)) {
            if (HomeActivity.playercontrol.isPlaying()) {
                HomeActivity.exoPlayer.setPlayWhenReady(false);
                stopForeground(true);
                stopSelf();
                System.exit(0);
            }
        }
        return START_STICKY;
    }

    private void showNotification() {
        Notification status;
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification_view);

        //back application
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        notificationIntent.setAction(Constants.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        // close notification

        Intent closeIntent = new Intent(this, NotificationRadio.class);
        closeIntent.setAction(Constants.CLOSENOTIFICATION_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,closeIntent, 0);
        views.setOnClickPendingIntent(R.id.btn_close, pcloseIntent);

        views.setImageViewResource(R.id.img_channel, R.mipmap.icon);
        views.setTextViewText(R.id.txt_local,"Location: "+ General.getStringPreference(this,"location"));
        views.setTextViewText(R.id.txt_name_station,"Playing: "+ General.getStringPreference(this, "Key_RadioName"));
        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.mipmap.radio;
        status.contentIntent = pendingIntent;
        startForeground(Constants.NOTIFICATION_ID.NOTIFICATION_SERVICE, status);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}