package com.adrian.music.streamMeta;

import com.adrian.music.exceptions.streamParser.GetSongArtistException;
import com.adrian.music.exceptions.streamParser.GetSongTitleException;
import com.adrian.music.exceptions.streamParser.RefreshMetaException;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 8/09/13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class AdrStreamMeta extends AbstractStreamMeta {


    protected AdrStreamMeta(URL streamUrl) {
        super(streamUrl);
    }

    @Override
    public void refreshMeta() throws RefreshMetaException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTitle() throws GetSongTitleException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getArtist() throws GetSongArtistException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
