package com.adrian.music.managers;

import com.adrian.music.models.Track;
import com.adrian.music.services.TrackSearch.LastFm;
import com.adrian.music.services.TrackSearch.TrackSearch;
import com.adrian.music.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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


        try {

            Utils.saveImage(track.getMediumImageUrl(), "cover");

            //Comando
            String pwd = "pwd";


            String s;

            Process procesPWD = Runtime.getRuntime().exec(pwd);

            StringBuffer pwdResult = new StringBuffer();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(procesPWD.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                pwdResult.append(s);
            }

            coverUri = pwdResult.toString()+"/cover";

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return coverUri;
    }
}
