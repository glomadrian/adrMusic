package com.adrian.music.provider;

import es.gldos.coverFinder.exception.ImageNotFoundException;
import junit.framework.TestCase;

import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author Adrian Garcia Lomas
 */
public class CoverProviderTest extends TestCase {

    private final static Logger LOG = Logger.getLogger(CoverProviderTest.class.getName());

    public void testCoverProviderWorks(){

        CoverProvider coverProvider = CoverProvider.getProvider();
        assertNotNull(coverProvider);

        try {

            InputStream inputStream = coverProvider.getCover("master of puppets","metallica");
            assertNotNull(inputStream);

        } catch (ImageNotFoundException e) {
            LOG.warning("Image not found in cover provider");
        }
    }

    public void testCoverProviderCoverNotFoundImage(){

        CoverProvider coverProvider = CoverProvider.getProvider();
        assertNotNull(coverProvider);

        try {

            InputStream inputStream = coverProvider.getCover("fake no exists","fake name");
            assertNotNull(inputStream);

        } catch (ImageNotFoundException e) {
            LOG.warning("Image not found in cover provider");
        }
    }
}
