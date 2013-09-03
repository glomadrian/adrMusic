package com.adrian.music.services.RadioSearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 2/09/13
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class RockRadioWeb implements  RadioSearch {

    private static final String URL = "http://www.rockradio.com/";





    private HashMap<String, String> parseHtmlToRadios() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Elements radioNames = doc.select("ul.channels");

        if(radioNames.size()>0){

            Elements names = Jsoup.parse(radioNames.get(0).toString()).select("span");
            Elements streamUrls = Jsoup.parse(radioNames.get(0).toString()).select("a[href$=.pls]");


            if(names.size()==streamUrls.size()){

                HashMap<String, String>  list = new HashMap<String, String>();

                for(int i=0;i<names.size();i++){


                list.put(names.get(i).text(),streamUrls.get(i).attr("abs:href"));

                }

                return list;

            }

        }

        return null;

    }

    @Override
    public HashMap<String, String> searchRadio() {



        try {

           return  parseHtmlToRadios();


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }


        return null;
    }
}
