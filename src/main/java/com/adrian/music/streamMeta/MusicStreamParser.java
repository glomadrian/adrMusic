package com.adrian.music.streamMeta;

import com.adrian.music.exceptions.streamParser.GetSongArtistException;
import com.adrian.music.exceptions.streamParser.GetSongTitleException;
import com.adrian.music.exceptions.streamParser.RefreshMetaException;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 8/09/13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public interface MusicStreamParser  {

    public void refreshMeta() throws RefreshMetaException;
    String getTitle() throws GetSongTitleException;
    String getArtist() throws GetSongArtistException;


}
