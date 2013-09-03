package com.adrian.music.services.TrackSearch;

import com.adrian.music.models.Track;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface TrackSearch {

    public Track searhTrack(String title, String artist);
}
