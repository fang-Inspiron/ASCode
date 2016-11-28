package com.bishedemo.bean;

import java.text.SimpleDateFormat;

/**
 * Created by fang on 2016/11/20.
 */

public class ListNoteBean {
    private String id;
    private long updateTime;
    private String name;
    private String date;
    private String text;

    public ListNoteBean(String id, String name, String date, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.text = text;
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUpdatetime() {
        return updateTime;
    }

    public void setUpdatetime(long updateTime) {
        this.updateTime = updateTime;
        setDate(updateTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(long time) {
        date=format.format(time);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}