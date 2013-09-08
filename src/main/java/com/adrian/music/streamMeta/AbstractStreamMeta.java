package com.adrian.music.streamMeta;

import java.net.URL;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 8/09/13
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStreamMeta  implements MusicStreamParser{

    protected URL streamUrl;
    public Map<String, String> metadata;

    protected AbstractStreamMeta(URL streamUrl) {

        this.streamUrl = streamUrl;
    }
}
