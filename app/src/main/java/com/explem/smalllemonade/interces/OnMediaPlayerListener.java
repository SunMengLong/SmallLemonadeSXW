package com.explem.smalllemonade.interces;

/**
 * Created by ${薛亚南}
 * on 2017/1/4 16：02.
 */

public interface OnMediaPlayerListener {
    void sendDurPosition(int position);

    void sendCrentPosition(int position);

    void onPlay();

    void onPauseing();
}
