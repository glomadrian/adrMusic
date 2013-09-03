package com.adrian.music.notifications.launcher;

import com.adrian.music.models.Track;
import com.adrian.music.notifications.ConsoleNotification;
import com.adrian.music.notifications.GnomeNotidication;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 3/09/13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class NotificationLauncher implements Runnable {

    Track track;

    public NotificationLauncher(Track track) {
        this.track = track;
    }



    @Override
    public void run() {



            //Notificacion de consola

            MusicNotification consoleNotification = new ConsoleNotification();
            consoleNotification.createNotification(track.getName(), track.getArtist());
            consoleNotification.sendNotification();

            //Notificacion del SO
            MusicNotification osNotification = null;


            //LINUX
            if(Utils.getOS().contains("Linux"))
                osNotification = new GnomeNotidication();
            //MAC


            //WINDOWS


            if(osNotification!=null){
                osNotification.createNotification(track.getName(), track.getArtist());
                osNotification.sendNotification();
            }


            //TODO Notificacion allJoyn

    }
}
