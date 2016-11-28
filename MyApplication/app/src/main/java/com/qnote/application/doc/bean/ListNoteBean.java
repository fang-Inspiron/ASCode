package com.qnote.application.doc.bean;

import java.text.SimpleDateFormat;

/**
 * Created by silei on 2016/9/1.
 */
public class ListNoteBean {
    //// TODO: 2016/9/1 笔记列表单项信息
    private String id;
    private long updatetime;
    private String name;
    private String date;
    private boolean isdir;
    private String text;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
        setDate(updatetime);
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

    public boolean isdir() {
        return isdir;
    }

    public void setIsdir(boolean isdir) {
        this.isdir = isdir;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

