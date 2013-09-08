package com.adrian.music.main;

import com.adrian.music.events.eventsBus.MusicEventBus;
import com.adrian.music.events.listeners.MusicEventListener;
import com.adrian.music.player.Player;
import com.adrian.music.services.RadioSearch.PlsExtract;
import com.adrian.music.services.RadioSearch.RadioSearch;
import com.adrian.music.services.RadioSearch.RockRadioWeb;
import com.adrian.music.utils.Utils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 31/08/13
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public class Main {

   private final static Logger LOG = Logger.getLogger(Main.class.getName());


    public static void showRadioList(ArrayList<String> list){


        for(int i=0;i<list.size();i++)
        System.out.println(""+i+". "+list.get(i));
    }


    static void turnOffLog(){
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

    }

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {


        //TODO Mejorar main y capturar excepciones

        String debugProperty = Utils.getConfigurationProperties().getProperty("debug");

        if(debugProperty.contains("false"))
        turnOffLog();

        LOG.info("Inicia app adrMusic");


        //Inicializar bus de datos
        MusicEventBus.getMusicEventBus().register(new MusicEventListener());

        //Variables
        PlsExtract plsExtract = new PlsExtract();



        RadioSearch radioSearch = new RockRadioWeb();
        HashMap<String,String> list = radioSearch.searchRadio();

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> urls = new ArrayList<String>();


        for (Map.Entry<String, String> entry : list.entrySet()) {
            names.add(entry.getKey());
            urls.add(entry.getValue());
        }

        showRadioList(names);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        int selection = Integer.parseInt(br.readLine());

        String radioUrl  = plsExtract.extractUrlFromPls(urls.get(selection));
        Player player = new Player(radioUrl);
        player.build();

        //Lanza el hilo del player
        Thread thread = new Thread(player);
        thread.start();

        LOG.info("Throw player thread with url: "+radioUrl);


    }



}

