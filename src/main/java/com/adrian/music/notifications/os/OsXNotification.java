package com.adrian.music.notifications.os;

import com.adrian.music.managers.CoverManager;
import com.adrian.music.models.Track;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.services.TrackSearch.LastFm;
import com.adrian.music.services.TrackSearch.TrackSearch;
import com.adrian.music.utils.Utils;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 4/09/13
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class OsXNotification implements MusicNotification {

    private final static Logger LOG = Logger.getLogger(OsXNotification.class.getName());


    String title;
    String artist;
    String imageUri;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;


        //Imagen cover
        TrackSearch albumSearch = new LastFm();
        Track track = albumSearch.searhTrack(title, artist);

        //Descargar y guardar imagen temporalmente
        CoverManager manager = new CoverManager();

        imageUri = manager.downloadCover(title,artist);
    }

    @Override
    public void sendNotification() {

        LOG.info("Launch OS X notification");

        try {

            String[] command = new String[] {"growlnotify","--image",imageUri,"-t", artist,"-m", title};
            Process p = Runtime.getRuntime().exec(command);

            Utils.deleteFile(imageUri);

        } catch (IOException e) {

            LOG.warning("Error wile launch OS X notification, command not found");
        }
    }
}
