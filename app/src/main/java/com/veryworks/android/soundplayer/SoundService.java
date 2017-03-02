package com.veryworks.android.soundplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class SoundService extends Service {
    private static final String TAG = "SoundService";

    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_PAUSE = "action_pause";
    public static final String ACTION_REWIND = "action_rewind";
    public static final String ACTION_FAST_FORWARD = "action_fast_foward";
    public static final String ACTION_NEXT = "action_next";
    public static final String ACTION_PREVIOUS = "action_previous";
    public static final String ACTION_STOP = "action_stop";

    private MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(mMediaPlayer == null) {
            initMedia();
        }

        handleIntent(intent);

        return super.onStartCommand(intent, flags, startId);
    }

    // 1. 미디어 플레이어 기본값 설정
    private void initMedia() {

    }

    // 2. 명령어 실행
    private void handleIntent( Intent intent ) {
        if( intent == null || intent.getAction() == null )
            return;

        String action = intent.getAction();
        if( action.equalsIgnoreCase( ACTION_PLAY ) ) {

        } else if( action.equalsIgnoreCase( ACTION_PAUSE ) ) {

        } else if( action.equalsIgnoreCase( ACTION_FAST_FORWARD ) ) {

        } else if( action.equalsIgnoreCase( ACTION_REWIND ) ) {

        } else if( action.equalsIgnoreCase( ACTION_PREVIOUS ) ) {

        } else if( action.equalsIgnoreCase( ACTION_NEXT ) ) {

        } else if( action.equalsIgnoreCase( ACTION_STOP ) ) {

        }
    }



}
