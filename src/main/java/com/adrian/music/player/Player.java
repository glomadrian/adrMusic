package com.adrian.music.player;

import com.adrian.music.events.PlayerStartPlaying;
import com.adrian.music.events.eventsBus.MusicEventBus;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Runnable {

    String streamingUrl;
    AudioInputStream audioStream;
    SourceDataLine line;
    AudioFormat decodedFormat;

    public String getStreamingUrl() {
        return streamingUrl;
    }

    public void setStreamingUrl(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    public AudioInputStream getAudioStream() {
        return audioStream;
    }

    public void setAudioStream(AudioInputStream audioStream) {
        this.audioStream = audioStream;
    }

    public SourceDataLine getLine() {
        return line;
    }

    public void setLine(SourceDataLine line) {
        this.line = line;
    }

    public AudioFormat getDecodedFormat() {
        return decodedFormat;
    }

    public void setDecodedFormat(AudioFormat decodedFormat) {
        this.decodedFormat = decodedFormat;
    }

    public Player(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    public void build() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        AudioInputStream in = AudioSystem.getAudioInputStream(new URL((streamingUrl)));
        AudioFormat baseFormat = in.getFormat();
        decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);
        audioStream = AudioSystem.getAudioInputStream(decodedFormat, in);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
        line = (SourceDataLine) AudioSystem.getLine(info);

    }


    @Override
    public void run() {



            if(line != null) {
                try {


                    line.open(decodedFormat);

                    byte[] data = new byte[4096];
                    // Start
                    line.start();

                    //Comienza la reproducion del stream, entonces se lanza el evento

                    MusicEventBus.getMusicEventBus().post(new PlayerStartPlaying(this.line,streamingUrl));

                    int nBytesRead;
                    while ((nBytesRead = audioStream.read(data, 0, data.length)) != -1) {

                        line.write(data, 0, nBytesRead);
                    }
                    // Stop
                    line.drain();
                    line.stop();
                    line.close();
                    audioStream.close();


                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }

}
