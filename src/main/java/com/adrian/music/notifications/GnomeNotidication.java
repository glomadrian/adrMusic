package com.adrian.music.notifications;

import com.adrian.music.models.Track;
import com.adrian.music.services.TrackSearch.TrackSearch;
import com.adrian.music.services.TrackSearch.LastFm;
import com.adrian.music.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class GnomeNotidication implements MusicNotification{

    String title;
    String artist;

    @Override
    public void createNotification(String title, String artist) {

        this.title = title;
        this.artist = artist;
    }

    @Override
    public void sendNotification() {


        //TODO Mejorar ejecucion de comandos

        //Imagen cover
        TrackSearch albumSearch = new LastFm();
        Track track = albumSearch.searhTrack(title, artist);

        //Descargar y guardar imagen temporalmente

        try {

            Utils.saveImage(track.getMediumImageUrl(),"cover");

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        //Comando
        String pwd = "pwd";


        String s;
        try {
            Process procesPWD = Runtime.getRuntime().exec(pwd);

            StringBuffer pwdResult = new StringBuffer();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(procesPWD.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                pwdResult.append(s);
            }


            String coverUri = pwdResult.toString()+"/cover";

            String[] command = new String[] {"notify-send","-i",coverUri, artist, title};


            Process p = Runtime.getRuntime().exec(command);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
