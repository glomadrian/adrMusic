package com.adrian.music.managers;

import com.adrian.music.models.Track;
import com.adrian.music.services.TrackSearch.LastFm;
import com.adrian.music.services.TrackSearch.TrackSearch;
import com.adrian.music.utils.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 6/09/13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class CoverManager {

    TrackSearch trackSearch = new LastFm();
    String coverUri;

    public String downloadCover(String title,String artist){


        Track track = trackSearch.searhTrack(title, artist);
        String fileName = Utils.random();
        String imageURL = track.getSmallImageUrl();
        //Image size

        String imageSize = Utils.getConfigurationProperties().getProperty("imageSize");

        if(imageSize.equals("small"))
               imageURL = track.getSmallImageUrl();
        if(imageSize.equals("medium"))
               imageURL = track.getMediumImageUrl();
        if(imageSize.equals("big"))
               imageURL = track.getBigImageUrl();

        try {

          File cover =  Utils.saveImage(imageURL, fileName);

          return cover.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return coverUri;
    }
}
