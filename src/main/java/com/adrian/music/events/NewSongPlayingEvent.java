package com.adrian.music.events;

import com.adrian.music.models.Track;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class NewSongPlayingEvent extends Event {

    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public NewSongPlayingEvent(Track track) {
        this.track = track;
    }
}
