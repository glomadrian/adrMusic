package com.adrian.music.notifications.launcher;

import com.adrian.music.models.Track;
import com.adrian.music.notifications.*;
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



            //Check if console notification is true
            String consoleNotificationConf = Utils.getConfigurationProperties().getProperty("consoleNotification");

            if(consoleNotificationConf.equals("true")){
                //Notificacion de consola

                MusicNotification consoleNotification = new ConsoleNotification();
                consoleNotification.createNotification(track.getName(), track.getArtist());
                consoleNotification.sendNotification();
            }


            //Check if console notification is true
            String dekstopNotificationConf = Utils.getConfigurationProperties().getProperty("desktopNotification");

            if(dekstopNotificationConf.equals("native")){

                MusicNotification nativeNotification= new NativeNotification();
                nativeNotification.createNotification(track.getName(), track.getArtist());
                nativeNotification.sendNotification();

            }

            if(dekstopNotificationConf.equals("os"))
            {

                //Notificacion del SO
                MusicNotification osNotification = null;

                //LINUX
                if(Utils.getOS().contains("linux"))
                    osNotification = new GnomeNotidication();
                //MAC
                if(Utils.getOS().contains("mac os x"))
                    osNotification = new OsXNotification();

                //Fuck windows

                if(osNotification!=null){
                    osNotification.createNotification(track.getName(), track.getArtist());
                    osNotification.sendNotification();
                }
            }


    }
}
