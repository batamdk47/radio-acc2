package radio.elenahudson.uk.ulti.timer;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import radio.elenahudson.uk.ulti.general.General;


public class CountdownTimer {
    public static CountDownTimer countDownTimer;
    public static final String FORMAT = "%02d:%02d:%02d";
    public static void  CountdownTimer(final Context context, long time, final TextView textView, final TextView textViewsecon) {

        countDownTimer = new CountDownTimer(time *60* 1000, 1000) { // adjust the milli seconds here
            public  void onTick(long millisUntilFinished) {
                textView.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                textViewsecon.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            General.saveLongPreference(context,"millisUntilFinished",millisUntilFinished);
            }

            public void onFinish() {
              StopCountdownTimer.StopNotification(context);
                textView.setText("");
                textViewsecon.setText("");

            }
        }.start();
    }
}
