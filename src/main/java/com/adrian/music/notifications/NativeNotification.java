package com.adrian.music.notifications;

import ch.twinkle.NotificationBuilder;
import ch.twinkle.style.INotificationStyle;
import ch.twinkle.style.theme.DarkDefaultNotification;
import ch.twinkle.style.theme.LightDefaultNotification;
import ch.twinkle.window.Positions;
import com.adrian.music.managers.CoverManager;
import com.adrian.music.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 6/09/13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class NativeNotification implements MusicNotification {

    NotificationBuilder notification;

    @Override
    public void createNotification(String title, String artist) {


        int delayTime = Integer.valueOf(Utils.getConfigurationProperties().getProperty("daleyTime"));
        String style = Utils.getConfigurationProperties().getProperty("style");
        String fadeAnimation = Utils.getConfigurationProperties().getProperty("fadeAnimation");
        String position = Utils.getConfigurationProperties().getProperty("position");


        //Fade
        boolean fade = false;

        if(fadeAnimation.equals("true"))
            fade = true;

        //Image

        CoverManager manager = new CoverManager();
        String imageUri = manager.downloadCover(title,artist);

        notification = new NotificationBuilder();
        notification
                .withMessage(title)
                .withTitle(artist)
                .withFadeInAnimation(fade)
                .withFadeOutAnimation(fade)
                .withDisplayTime(delayTime)
                .withPosition(getPositions(position))
                .withIcon(imageUri)
                .withStyle(getSyle(style));
    }

    @Override
    public void sendNotification() {
        notification.showNotification();
    }



    public Positions getPositions(String position){

        Positions positionPoint = Positions.NORTH_WEST;

        if(position.equals("C"))
            positionPoint = Positions.CENTER;
        if(position.equals("N"))
            positionPoint = Positions.NORTH;
        if(position.equals("S"))
            positionPoint = Positions.SOUTH;
        if(position.equals("E"))
            positionPoint = Positions.EAST;
        if(position.equals("W"))
            positionPoint = Positions.WEST;
        if(position.equals("NW"))
            positionPoint = Positions.NORTH_WEST;
        if(position.equals("NE"))
            positionPoint = Positions.NORTH_EAST;
        if(position.equals("SW"))
            positionPoint = Positions.SOUTH_WEST;
        if(position.equals("SE"))
            positionPoint = Positions.SOUTH_EAST;

        return positionPoint;

    }

    public INotificationStyle getSyle(String style){

        INotificationStyle notificationStyle=null;

        if(style.equals("dark"))
            notificationStyle = new DarkDefaultNotification();
        if(style.equals("light"))
            notificationStyle = new LightDefaultNotification();

        return  notificationStyle;
    }
}
