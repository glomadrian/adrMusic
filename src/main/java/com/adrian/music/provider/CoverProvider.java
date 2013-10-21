package com.adrian.music.provider;

import com.adrian.music.utils.Utils;
import es.gldos.coverFinder.exception.ImageNotFoundException;
import es.gldos.coverFinder.handler.CustomFolderImageHandler;
import es.gldos.coverFinder.handler.URLImageHandler;
import es.gldos.coverFinder.provider.InputStreamCoverProvider;
import es.gldos.coverFinder.service.ICoverService;
import es.gldos.coverFinder.service.ITunesCoverService;
import es.gldos.coverFinder.service.LastFmCoverService;
import es.gldos.coverFinder.service.invoquer.InvokerService;

import java.io.InputStream;

/**
 * @author Adrian Garcia Lomas
 */
public class CoverProvider {

    private static CoverProvider coverProvider = null;
    private  InputStreamCoverProvider inputStreamCoverProvider;

    public  static CoverProvider getProvider(){

        if (coverProvider == null){
             coverProvider = new CoverProvider();
             coverProvider.createInputStreamCoverProvider();

        }

        return  coverProvider;
    }


    public void createInputStreamCoverProvider(){

        //Define objects

        //Define all services

        ICoverService lastFM = new LastFmCoverService(Utils.getConfigurationProperties().getProperty("lastFmAPIKey"),Utils.getConfigurationProperties().getProperty("lastFmSecret"));
        ICoverService iTunes = new ITunesCoverService();

        //Create invoker

        InvokerService invoquer = new InvokerService();

        invoquer.addCoverService(lastFM);
        invoquer.addCoverService(iTunes);

        //Create handler
        URLImageHandler urlHandler = new URLImageHandler();
        CustomFolderImageHandler customFolderHandler = new CustomFolderImageHandler();

        String size = Utils.getConfigurationProperties().getProperty("imageSize");

        int value=0;

        if(size.equals("small"))
           value  =  InputStreamCoverProvider.SMALL_SIZE;
        else if (size.equals("medium"))
           value  =  InputStreamCoverProvider.SMALL_SIZE;
        else if (size.equals("large"))
           value  =  InputStreamCoverProvider.LARGE_SIZE;

        this.inputStreamCoverProvider = new InputStreamCoverProvider(invoquer,urlHandler,customFolderHandler,Utils.getConfigurationProperties().getProperty("imageNotFoundSrc"),value);

    }

    public InputStream getCover(String title,String artist) throws ImageNotFoundException {

        return inputStreamCoverProvider.getCover(title,artist);
    }
}
