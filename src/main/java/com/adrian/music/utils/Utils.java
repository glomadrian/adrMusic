package com.adrian.music.utils;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 2/09/13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static Properties prop=null;


    private final static Logger LOG = Logger.getLogger(Utils.class.getName());


    public static String getOS(){

        String os = System.getProperty("os.name").toLowerCase();
        return  os;
    }

    public static File saveImage(String imageUrl, String destinationFile) throws IOException, IOException {

        LOG.info("Saving image with name "+destinationFile);


        URL url = new URL(imageUrl);
        InputStream is = url.openStream();

        //Create File object
        File file =new File(destinationFile);

        OutputStream os = new FileOutputStream(file);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

       is.close();
       os.close();


        return file;
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
