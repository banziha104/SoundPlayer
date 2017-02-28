package com.veryworks.android.soundplayer.domain;

import android.net.Uri;

/**
 * Created by pc on 2/28/2017.
 */

public class Sound {

    // sound info.
    public int id;
    public Uri music_uri;
    public String title;
    public int artist_id;
    public String artist;
    public String artist_key;
    public int album_id;
    public Uri album_image_uri;
    public int genre_id;
    public int duration;
    public String is_music;
    public String composer;
    public String content_type;
    public String year;

    // add info.
    public int order;
    public boolean favor;
}
