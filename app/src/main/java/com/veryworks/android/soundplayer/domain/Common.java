package com.veryworks.android.soundplayer.domain;

import android.net.Uri;

/**
 * Created by pc on 2/28/2017.
 */

public abstract class Common {
    public abstract String getTitle();
    public abstract String getArtist();
    public abstract String getDuration();
    public abstract Uri getImageUri();
}
