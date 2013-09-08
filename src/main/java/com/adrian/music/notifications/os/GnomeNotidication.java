package com.adrian.music.notifications.os;

import com.adrian.music.managers.CoverManager;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.utils.Utils;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class GnomeNotidication implements MusicNotification {

    private final static Logger LOG = Logger.getLogger(GnomeNotidication.class.getName());



    String title;
    String artist;
    String imageUri;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;

        CoverManager manager = new CoverManager();
        imageUri = manager.downloadCover(title,artist);
    }

    @Override
    public void sendNotification() {

        LOG.info("Launch gnome notification");


        try {

            String[] command = new String[] {"notify-send","-i",imageUri, artist, title};

            Process p = Runtime.getRuntime().exec(command);

            Utils.deleteFile(imageUri);

        } catch (IOException e) {

            LOG.warning("Error wile launch gnome notification, command not found");
        }


    }
}
