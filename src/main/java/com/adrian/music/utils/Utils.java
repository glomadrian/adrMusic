package com.adrian.music.utils;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 2/09/13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static Properties prop=null;
    public static String getOS(){

        String os = System.getProperty("os.name").toLowerCase();
        return  os;
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException, IOException {

        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }





    }

    public static Properties getConfigurationProperties(){

        if (prop==null){

            prop = new Properties();
            try {
                prop.load(new FileInputStream("config.properties"));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }


        return prop;

    }


    public static void deleteFile(String uri){
        File file = new File(uri);
        file.delete();

    }

    public static String random()
    {
        return new BigInteger(130,  new SecureRandom()).toString(32);
    }

}
