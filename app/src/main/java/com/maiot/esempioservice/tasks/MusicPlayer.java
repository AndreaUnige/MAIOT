package com.maiot.esempioservice.tasks;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

public class MusicPlayer {

    Context context = null;
    Uri ringtone = null;
    MediaPlayer mediaPlayer = null;

    public MusicPlayer(Context context, int whichRingtone) {
        this.context = context;
        ringtone = RingtoneManager.getDefaultUri(whichRingtone);
        mediaPlayer = MediaPlayer.create(context, ringtone);

        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
    }

    public void start() {
        mediaPlayer.start();
    }

    public void stop () {
        mediaPlayer.stop();
    }
}
