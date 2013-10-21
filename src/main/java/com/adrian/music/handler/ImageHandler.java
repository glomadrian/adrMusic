package com.adrian.music.handler;

import com.adrian.music.utils.Utils;

import java.io.*;

/**
 * Save inputStream in image files with id and return complete path to it, also delete a image
 *
 * @author Adrian Garcia Lomas
 */
public class ImageHandler {


    public String saveImage(InputStream inputStream) throws IOException {

        String randomName = Utils.random();

        File file = new File(randomName);

        OutputStream outputStream = new FileOutputStream(file);

        byte[] buf =new byte[1024];

        int len;
        while((len = inputStream.read(buf))>0){
            outputStream.write(buf,0,len);
        }
        outputStream.close();
        inputStream.close();

        return file.getAbsolutePath();
    }


    public void deleteImage(String imageURI){

        File file = new File(imageURI);

        file.delete();
    }
}
