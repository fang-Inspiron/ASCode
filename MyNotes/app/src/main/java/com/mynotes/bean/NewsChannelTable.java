package com.mynotes.bean;

import java.io.Serializable;

/**
 * Created by fang on 2016/11/4.
 */

public class NewsChannelTable implements Serializable {

    private String newsChannelName;
    private String newsChannelId;
    private String newsChannelType;
    private boolean newsChannelSelect;
    private int newsChannelIndex;
    private Boolean newsChannelFixed;

    public NewsChannelTable() {
    }

    public NewsChannelTable(String newChannelName) {
        this.newsChannelName = newChannelName;
    }

    public NewsChannelTable(String newsChannelName, String newsChannelId, String newsChannelType, boolean newsChannelSelect, int newsChannelIndex, Boolean newsChannelFixed) {
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }

    public String getNewsChannelName() {
        return newsChannelName;
    }

    public String getNewsChannelId() {
        return newsChannelId;
    }

    public String getNewsChannelType() {
        return newsChannelType;
    }

    public boolean isNewsChannelSelect() {
        return newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return newsChannelIndex;
    }

    public Boolean getNewsChannelFixed() {
        return newsChannelFixed;
    }

    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }

    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }

    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }
}
