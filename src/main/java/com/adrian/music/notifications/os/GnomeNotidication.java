package com.adrian.music.notifications.os;

import com.adrian.music.handler.ImageHandler;
import com.adrian.music.notifications.MusicNotification;
import com.adrian.music.provider.CoverProvider;
import com.adrian.music.utils.Utils;
import es.gldos.coverFinder.exception.ImageNotFoundException;

import java.io.IOException;
import java.io.InputStream;
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



    private String title;
    private String artist;
    private CoverProvider coverProvider = CoverProvider.getProvider();
    private String imageUri;
    private ImageHandler imageHandler;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;

        //Image handler
        imageHandler = new ImageHandler();

        InputStream image = null;
        try {

            image = coverProvider.getCover(title,artist);
            imageUri = imageHandler.saveImage(image);

        } catch (ImageNotFoundException e) {
            LOG.warning("Image not found excepion");
        } catch (IOException e) {
            LOG.warning("IO Exception");
        }


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
