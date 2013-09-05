package com.adrian.music.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 2/09/13
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

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

        is.close();
        os.close();
    }

}
