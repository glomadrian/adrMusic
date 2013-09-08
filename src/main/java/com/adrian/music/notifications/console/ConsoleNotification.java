package com.adrian.music.notifications.console;

import com.adrian.music.notifications.MusicNotification;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleNotification implements MusicNotification {

    private final static Logger LOG = Logger.getLogger(ConsoleNotification.class.getName());

    String title;
    String artist;


    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;

    }

    @Override
    public void sendNotification() {

        LOG.info("Launch console notification");
        System.out.println(artist+": "+title);

    }
}
