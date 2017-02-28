package com.veryworks.android.soundplayer.domain;

import java.util.List;

/**
 * Created by pc on 2/28/2017.
 */

public class Artist {
    public int id;
    public String artist;
    public String artist_key;
    public int number_of_tracks;
    public int number_of_albums;
    public List<Sound> sounds;
}
