package com.adrian.music.notifications.os;

import com.adrian.music.managers.CoverManager;
import com.adrian.music.models.Track;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.services.TrackSearch.LastFm;
import com.adrian.music.services.TrackSearch.TrackSearch;
import com.adrian.music.utils.Utils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 4/09/13
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class OsXNotification implements MusicNotification {

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

        //TODO Mejorar ejecucion de comandos



        try {

            String[] command = new String[] {"growlnotify","--image",imageUri,"-t", artist,"-m", title};
            Process p = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //TODO Hacer algo con esto
        Utils.deleteFile(imageUri);

    }
}
