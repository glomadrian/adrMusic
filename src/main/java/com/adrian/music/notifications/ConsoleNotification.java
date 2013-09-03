package com.adrian.music.notifications;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleNotification implements MusicNotification {

    String title;
    String artist;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;

    }

    @Override
    public void sendNotification() {

        System.out.println(artist+": "+title);

    }
}
