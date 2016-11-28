package com.bishedemo.bean;

import java.util.List;

/**
 * Created by fang on 2016/11/15.
 */

public class TitleBean {
    private String title;
    private String channelId;
    private String link;
    private String pubDate;
    private String nid;
    private List<String> imageurls;
    private String desc;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setImageurls(List<String> imageurls) {
        this.imageurls = imageurls;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getNid() {
        return nid;
    }

    public List<String> getImageurls() {
        return imageurls;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "TitleBean{" +
                "title='" + title + '\'' +
                ", channelId='" + channelId + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", nid='" + nid + '\'' +
                ", imageurls=" + imageurls +
                ", desc='" + desc + '\'' +
                '}';
    }
}
