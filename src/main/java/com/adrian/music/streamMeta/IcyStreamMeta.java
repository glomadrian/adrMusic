package com.adrian.music.streamMeta;

import com.adrian.music.exceptions.streamParser.GetSongArtistException;
import com.adrian.music.exceptions.streamParser.GetSongTitleException;
import com.adrian.music.exceptions.streamParser.RefreshMetaException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IcyStreamMeta extends AbstractStreamMeta{

    private final static Logger LOG = Logger.getLogger(IcyStreamMeta.class.getName());


    private boolean isError;

    public IcyStreamMeta(URL streamUrl) {
        super(streamUrl);
         isError = false;
    }

    /**
     * Get artist using stream's title
     *
     * @return String
     * @throws java.io.IOException
     */
    public String getArtist() throws GetSongArtistException{

        Map<String, String> data = null;
        String title = null;

        try {

           data = getMetadata();

            if (!data.containsKey("StreamTitle"))
                return "";

            String streamTitle = data.get("StreamTitle");
            title = streamTitle.substring(0, streamTitle.indexOf("-"));


       } catch (IOException | RefreshMetaException e) {

           GetSongArtistException ex=  new GetSongArtistException();
           ex.initCause(e.getCause());
           throw ex;
       }
        return title.trim();
    }

    /**
     * Get title using stream's title
     *
     * @return String
     * @throws java.io.IOException
     */
    public String getTitle() throws GetSongTitleException {

        Map<String, String> data = null;
        try {
            data = getMetadata();

        } catch (IOException | RefreshMetaException e) {
            GetSongTitleException ex=  new GetSongTitleException();
            ex.initCause(e.getCause());
            throw ex;
       }

        if (!data.containsKey("StreamTitle"))
            return "";

        String streamTitle = data.get("StreamTitle");
        String artist = streamTitle.substring(streamTitle.indexOf("-")+1);
        return artist.trim();
    }

    public Map<String, String> getMetadata() throws IOException, RefreshMetaException {
        if (metadata == null) {
            refreshMeta();
        }

        return metadata;
    }

    public void refreshMeta() throws RefreshMetaException {

        try {
            retreiveMetadata();
        } catch (IOException e) {

            RefreshMetaException ex=  new RefreshMetaException();
            ex.initCause(e.getCause());
            throw ex;

        }

    }

    private void retreiveMetadata() throws IOException {
        URLConnection con = streamUrl.openConnection();
        con.setRequestProperty("Icy-MetaData", "1");
        con.setRequestProperty("Connection", "close");
        con.setRequestProperty("Accept", null);
        con.connect();

        int metaDataOffset = 0;
        Map<String, List<String>> headers = con.getHeaderFields();
        InputStream stream = con.getInputStream();

        if (headers.containsKey("icy-metaint")) {
            // Headers are sent via HTTP
            metaDataOffset = Integer.parseInt(headers.get("icy-metaint").get(0));
        } else {
            // Headers are sent within a stream
            StringBuilder strHeaders = new StringBuilder();
            char c;
            while ((c = (char)stream.read()) != -1) {
                strHeaders.append(c);
                if (strHeaders.length() > 5 && (strHeaders.substring((strHeaders.length() - 4), strHeaders.length()).equals("\r\n\r\n"))) {
                    // end of headers
                    break;
                }
            }

            // Match headers to get metadata offset within a stream
            Pattern p = Pattern.compile("\\r\\n(icy-metaint):\\s*(.*)\\r\\n");
            Matcher m = p.matcher(strHeaders.toString());
            if (m.find()) {
                metaDataOffset = Integer.parseInt(m.group(2));
            }
        }

        // In case no data was sent
        if (metaDataOffset == 0) {
            isError = true;
            return;
        }

        // Read metadata
        int b;
        int count = 0;
        int metaDataLength = 4080; // 4080 is the max length
        boolean inData = false;
        StringBuilder metaData = new StringBuilder();
        // Stream position should be either at the beginning or right after headers
        while ((b = stream.read()) != -1) {
            count++;

            // Length of the metadata
            if (count == metaDataOffset + 1) {
                metaDataLength = b * 16;
            }

            if (count > metaDataOffset + 1 && count < (metaDataOffset + metaDataLength)) {
                inData = true;
            } else {
                inData = false;
            }
            if (inData) {
                if (b != 0) {
                    metaData.append((char)b);
                }
            }
            if (count > (metaDataOffset + metaDataLength)) {
                break;
            }

        }

        // Set the data
        metadata = IcyStreamMeta.parseMetadata(metaData.toString());

        LOG.info("Stream metadata: "+metadata);
        // Close
        stream.close();
    }

    public boolean isError() {
        return isError;
    }

    public URL getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(URL streamUrl) {
        this.metadata = null;
        this.streamUrl = streamUrl;
        this.isError = false;
    }

    public static Map<String, String> parseMetadata(String metaString) {
        Map<String, String> metadata = new HashMap();
        String[] metaParts = metaString.split(";");
        Pattern p = Pattern.compile("^([a-zA-Z]+)=\\'([^\\']*)\\'$");
        Matcher m;
        for (int i = 0; i < metaParts.length; i++) {
            m = p.matcher(metaParts[i]);
            if (m.find()) {
                metadata.put((String)m.group(1), (String)m.group(2));
            }
        }

        return metadata;
    }
}