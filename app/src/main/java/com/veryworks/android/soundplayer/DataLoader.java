package com.veryworks.android.soundplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.veryworks.android.soundplayer.domain.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2/28/2017.
 */

public class DataLoader {

    // 1. 데이터 컨테츠 URI 정의
    private final static Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    // 2. 데이터에서 가져올 데이터 컬럼명을 String 배열에 담는다.
    //    데이터컬럼명은 Content Uri 의 패키지에 들어있다.
    private final static String PROJ[] = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.IS_MUSIC,
            MediaStore.Audio.Media.CONTENT_TYPE,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.YEAR,
    };

    // datas 를 두개의 activity에서 공유하기 위해 static 형태로 변경
    private static List<Sound> datas = new ArrayList<>();

    // static 변수인 datas 를 체크해서 널이면 load 를 실행
    public static List<Sound> get(Context context){
        if(datas == null || datas.size() == 0){
            load(context);
        }
        return datas;
    }

    // load 함수는 get 함수를 통해서만 접근한다.
    private static void load(Context context) {
        // 1. 데이터에 접근하기위해 ContentResolver 를 불러온다.
        ContentResolver resolver = context.getContentResolver();

        // 2. Content Resolver 로 쿼리한 데이터를 Cursor 에 담는다.
        Cursor cursor = resolver.query(URI, PROJ, null, null, null);

        // 3. Cursor 에 담긴 데이터를 반복문을 돌면서 꺼낸다
        if(cursor != null) {
            while(cursor.moveToNext()) {
                Sound sound = new Sound();
                // 데이터
                sound.id = getInt(cursor, PROJ[0]);
                sound.album_id = getInt(cursor, PROJ[1]);
                sound.title = getString(cursor, PROJ[2]);
                sound.artist = getString(cursor, PROJ[3]);
                sound.album_image_uri = getMusicUri(sound.id);

                datas.add(sound);
            }
            // 처리 후 커서를 닫아준다
            cursor.close();
        }
    }

    private static String getGenre(){
        // MediaStore.Audio.Genres.getContentUriForAudioId();
        return "";
    }


    private static String getString(Cursor cursor, String columnName){
        int idx = cursor.getColumnIndex(columnName);
        return cursor.getString(idx);
    }

    private static int getInt(Cursor cursor, String columnName){
        int idx = cursor.getColumnIndex(columnName);
        return cursor.getInt(idx);
    }

    // 음악 id로 uri 를 가져오는 함수
    private static Uri getMusicUri(int music_id){
        Uri content_uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(content_uri, music_id+"");
    }

    // 앨범 Uri 생성
//    private static Uri getAlbumImageSimple(String album_id){
//        return Uri.parse("content://media/external/audio/albumart/" + album_id);
//    }
}
