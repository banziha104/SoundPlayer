package com.veryworks.android.soundplayer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import static android.app.PendingIntent.getService;

public class SoundService extends Service {
    private static final String TAG = "SoundService";

    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_PAUSE = "action_pause";
    public static final String ACTION_NEXT = "action_next";
    public static final String ACTION_PREVIOUS = "action_previous";
    public static final String ACTION_STOP = "action_stop";

    public static MediaPlayer mMediaPlayer = null;

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
        // 음원 uri
        Uri musicUri = null; //TODO datas.get(position).music_uri;

        // 플레이어에 음원 세팅
        mMediaPlayer = MediaPlayer.create(this, musicUri);
        mMediaPlayer.setLooping(false); // 반복여부
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //TODO next();
            }
        });
    }

    // 2. 명령어 실행
    private void handleIntent( Intent intent ) {
        if( intent == null || intent.getAction() == null )
            return;

        String action = intent.getAction();
        if( action.equalsIgnoreCase( ACTION_PLAY ) ) {
            // 음원처리
            playerStart();
        } else if( action.equalsIgnoreCase( ACTION_PAUSE ) ) {

        } else if( action.equalsIgnoreCase( ACTION_PREVIOUS ) ) {

        } else if( action.equalsIgnoreCase( ACTION_NEXT ) ) {

        } else if( action.equalsIgnoreCase( ACTION_STOP ) ) {

        }
    }

    // Activity 에서의 클릭 버튼 생성
    private NotificationCompat.Action generateAction(int icon, String title, String intentAction ) {
        Intent intent = new Intent( getApplicationContext(), SoundService.class );
        intent.setAction( intentAction );
        // PendingIntent : 실행 대상이 되는 인텐트를 지연시키는 역할
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);

        return new NotificationCompat.Action.Builder(icon, title, pendingIntent).build();
    }

    // 노티바를 생성하는 함수
    private void buildNotification( NotificationCompat.Action action , String action_flag) {
        // 노티바 전체를 클릭했을 때 실행되는 메인 intent
        Intent intent = new Intent( getApplicationContext(), SoundService.class );
        intent.setAction( ACTION_STOP );
        PendingIntent pendingIntent = getService(getApplicationContext(), 1, intent, 0);

        // 노티바 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this );

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "Beautiful" )
                .setContentText( "Crush" );

        // 퍼즈일 경우만 노티 삭제 가능
        if(ACTION_PAUSE.equals(action_flag)) {
            builder.setDeleteIntent(pendingIntent);
            builder.setOngoing(true);
        }

        builder.addAction(generateAction(android.R.drawable.ic_media_previous, "Previous", ACTION_PREVIOUS));
        builder.addAction(action);
        builder.addAction(generateAction(android.R.drawable.ic_media_next, "Next", ACTION_NEXT));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 노티바를 화면에 보여준다
        notificationManager.notify(1, builder.build());
    }

    private void playerStart(){
        // 노티피케이션 바 생성
        buildNotification( generateAction( android.R.drawable.ic_media_pause, "Pause", ACTION_PAUSE ), ACTION_PAUSE );
        mMediaPlayer.start();
    }
}
