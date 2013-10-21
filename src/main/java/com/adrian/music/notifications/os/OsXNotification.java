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
 * Date: 4/09/13
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class OsXNotification implements MusicNotification {

    private final static Logger LOG = Logger.getLogger(OsXNotification.class.getName());


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

        LOG.info("Launch OS X notification");

        try {

            String[] command = new String[] {"growlnotify","--image",imageUri,"-t", artist,"-m", title};
            Process p = Runtime.getRuntime().exec(command);

            Utils.deleteFile(imageUri);

        } catch (IOException e) {

            LOG.warning("Error wile launch OS X notification, command not found");
        }
    }
}
