package com.explem.smalllemonade.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.explem.smalllemonade.interces.MyBInderInterface;
import com.explem.smalllemonade.interces.OnMediaPlayerListener;

import java.io.IOException;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;
    private OnMediaPlayerListener onMediaPlayerListener;
    private boolean flag;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    public void startPlay(String path) {
        if (mediaPlayer != null && path != null) {
            try {
                if (mediaPlayer.isPlaying() || flag) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            this.onMediaPlayerListener.onPlay();
            upadateProgess();

        }
    }

    public void setCurentPositon(int i) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(i);
        }
    }


    public void continueplay() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                flag = true;
                this.onMediaPlayerListener.onPauseing();
            } else {
                mediaPlayer.start();
                this.onMediaPlayerListener.onPlay();
            }
        }
        upadateProgess();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void upadateProgess() {
        final int duration = mediaPlayer.getDuration();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    onMediaPlayerListener.sendDurPosition(duration);
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    onMediaPlayerListener.sendCrentPosition(currentPosition);
                    if (!mediaPlayer.isPlaying()) {
                        break;
                    }
                }
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder implements MyBInderInterface {

        public void setOnMediaPlayerListener(OnMediaPlayerListener onMediaPlayerListener) {
            MyService.this.onMediaPlayerListener = onMediaPlayerListener;
        }

        @Override
        public void helpStartPlay(String path) {
            startPlay(path);
        }

        @Override
        public void helpContinue() {
            continueplay();
        }

        @Override
        public void sendCurentPosition(int i) {
            setCurentPositon(i);
        }
    }
}
