package com.bishedemo.bean;

/**
 * Created by fang on 2016/11/21
 */
public class Channel {

    private String channelId;
    private String name;

    public Channel(String name, String id) {
        this.name = name;
        this.channelId = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
