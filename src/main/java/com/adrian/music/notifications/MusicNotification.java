package com.adrian.music.notifications;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public interface MusicNotification {


    public void createNotification(String title, String artist);
    public void sendNotification();

}
