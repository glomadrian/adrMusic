package com.adrian.music.notifications;

import com.adrian.music.managers.CoverManager;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class GnomeNotidication implements MusicNotification{

    String title;
    String artist;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;
    }

    @Override
    public void sendNotification() {


        CoverManager manager = new CoverManager();
        String imageUri = manager.downloadCover(title,artist);

        try {


            String[] command = new String[] {"notify-send","-i",imageUri, artist, title};

            Process p = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
