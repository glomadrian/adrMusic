package com.adrian.music.events;

import javax.sound.sampled.SourceDataLine;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class PlayerStartPlaying extends Event {

    SourceDataLine line;
    String streamUrl;

    public PlayerStartPlaying(SourceDataLine line, String streamUrl) {
        this.line = line;
        this.streamUrl = streamUrl;
    }

    public SourceDataLine getLine() {
        return line;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public void setLine(SourceDataLine line) {
        this.line = line;
    }

}
