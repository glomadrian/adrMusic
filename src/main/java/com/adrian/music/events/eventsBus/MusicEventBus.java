package com.adrian.music.events.eventsBus;

import com.google.common.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 1/09/13
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class MusicEventBus {


    static EventBus musicEventBus;

    private MusicEventBus() {
    }


    public static EventBus getMusicEventBus(){

        if(musicEventBus ==null)
          musicEventBus = new EventBus();

       return musicEventBus;
    }


}
