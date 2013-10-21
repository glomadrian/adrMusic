package com.adrian.music.handler;

import com.adrian.music.provider.CoverProvider;
import es.gldos.coverFinder.exception.ImageNotFoundException;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author Adrian Garcia Lomas
 */
public class ImageHandlerTest extends TestCase {

    private final static Logger LOG = Logger.getLogger(ImageHandlerTest.class.getName());

    public void testImageHandler(){


        CoverProvider coverProvider = CoverProvider.getProvider();
        assertNotNull(coverProvider);

        try {
            InputStream inputStream = coverProvider.getCover("Anybody Home!!","Acid Drinkers");
            assertNotNull(inputStream);

            ImageHandler imageHandler = new ImageHandler();
            String imageURI = imageHandler.saveImage(inputStream);

            assertNotNull(imageURI);

        } catch (ImageNotFoundException e) {
            LOG.warning("Image not found in cover provider");
        } catch (IOException e) {
            LOG.warning("Image IO Exception");        }
    }
}
