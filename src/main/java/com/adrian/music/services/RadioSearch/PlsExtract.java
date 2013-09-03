package com.adrian.music.services.RadioSearch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 2/09/13
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public class PlsExtract {

    private static final String regularExpresion = "File[\\d]*=(http:\\/\\/[\\w.:\\d\\/]*)";

    public String doPost(String url) throws URISyntaxException, IOException, HttpException {

        DefaultHttpClient httpClient = new DefaultHttpClient();


        List<NameValuePair> params = new LinkedList<NameValuePair>();


        String paramString = URLEncodedUtils.format(params, "utf-8");

        HttpGet httpGet = new HttpGet(url);


        //Se crea un objeto httpResponse para almacenar la respuesta
        HttpResponse httpResponse = httpClient.execute(httpGet);

        //Respuesta
        HttpEntity httpEntity = httpResponse.getEntity();

        //is es un inputStream
        InputStream is = httpEntity.getContent();

        //Se crea un reader que leera el objeto is (inputStream)
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8);
        //sb es un ojbeto de tipo stringbuildier, sirve para ir creando un string mientras se lee el reader
        StringBuilder sb = new StringBuilder();
        //linea que se leera del buffer
        String line = null;

        //Bucle que leera lineas del buffer mientras no sea nulo,
        while ((line = reader.readLine()) != null) {

            //Cada linea extraida del buffer se añade al String buildier
            sb.append(line + "\n");
        }
        //Finalmente se cierra el inputStream
        is.close();

        //Guardamos el String creado en una variable de tipo String, contiene el objeto en notacion JSON

        return sb.toString();


    }

    public String extractUrlFromPls(String plsURL){

        //TODO Mejorar esto..

        try {

            Pattern patron = Pattern.compile(regularExpresion);
            Matcher matcher = patron.matcher(doPost(plsURL));

            matcher.find();



            if (matcher.groupCount()>0){

               return matcher.group(1);

            }





        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (HttpException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;

    }
}
