# SoundPlayer
사운드 플레이어

### Observer Player
플레이어에 옵저버 형태로 컨트롤러를 등록. 각각의 컨트롤러에 notify 해준다.
```java
private static Controller instance = null;
List<ControlInterface> targets;

private Controller(){
    targets = new ArrayList<>();
}

public static Controller getInstance(){
    if(instance == null){
        instance = new Controller();
    }
    return instance;
}

public void addObserver(ControlInterface target) {
    targets.add(target);
}

public void play(){
    for(ControlInterface target : targets){
        target.startPlayer();
    }
}

public void pause(){
    for(ControlInterface target : targets){
        target.pausePlayer();
    }
}

public void remove(ControlInterface target){
    targets.remove(target);
}
```

### Foreground Service
```java
// stop service
Intent intentStop = new Intent( getApplicationContext(), SoundService.class );
intentStop.setAction( ACTION_STOP );
PendingIntent stopIntent = getService(getApplicationContext(), 1, intentStop, 0);

NotificationCompat.Builder builder = new NotificationCompat.Builder( this );
builder.setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle( sound.getTitle() )
        .setContentText( sound.getArtist() );

// remove noti. when pause
if(ACTION_PAUSE.equals(action_flag)) {
    builder.setDeleteIntent(stopIntent);
    builder.setOngoing(false);
}

builder.addAction(generateAction(android.R.drawable.ic_media_previous, "Prev", ACTION_PREVIOUS));
builder.addAction(action);
builder.addAction(generateAction(android.R.drawable.ic_media_next, "Next", ACTION_NEXT));

// foreground
startForeground(NOTIFICATION_ID, builder.build());
```

### Screen Capture
![Music List](https://raw.githubusercontent.com/javafa/-2nd-Project-SoundPlayer/master/screenCaptures/list.png "list")
![Album List](https://raw.githubusercontent.com/javafa/-2nd-Project-SoundPlayer/master/screenCaptures/album.png "album")
![Player](https://raw.githubusercontent.com/javafa/-2nd-Project-SoundPlayer/master/screenCaptures/player.png "player")
![Navigation](https://raw.githubusercontent.com/javafa/-2nd-Project-SoundPlayer/master/screenCaptures/navigation.png "mene")
