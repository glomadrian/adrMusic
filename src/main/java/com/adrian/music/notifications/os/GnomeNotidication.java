package com.adrian.music.notifications.os;

import com.adrian.music.managers.CoverManager;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.utils.Utils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class GnomeNotidication implements MusicNotification {

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




        try {


            String[] command = new String[] {"notify-send","-i",imageUri, artist, title};

            Process p = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //TODO Hacer algo con esto
        Utils.deleteFile(imageUri);
    }
}
