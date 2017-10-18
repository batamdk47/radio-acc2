package radio.elenahudson.uk.ulti;

import android.content.Context;
import android.media.AudioManager;
import android.widget.SeekBar;

import radio.elenahudson.uk.ulti.general.General;
import radio.elenahudson.uk.ulti.general.GetCharractersLast;
import radio.elenahudson.uk.ulti.privacy.PrivacyPolicy;


public class VolumeControl {

    public static String totalapi = General.APARTOFAPI1 + General.KEY + GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,1)+ Constants.APARTOFAPI3+ GetCharractersLast.GetCharractersLast(General.APARTOFAPI1,1)+Constants.APARTOFAPI4 +GetCharractersLast.GetCharractersLast(PrivacyPolicy.str,4);

    public static void volumeControl(Context context, SeekBar volumeSeekbar, AudioManager audioManager) {
        try {
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            final AudioManager finalAudioManager = audioManager;
            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    finalAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
