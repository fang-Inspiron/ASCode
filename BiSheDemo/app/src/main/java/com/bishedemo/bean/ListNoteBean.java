package com.bishedemo.bean;

import java.text.SimpleDateFormat;

/**
 * Created by fang on 2016/11/20.
 */

public class ListNoteBean {
    private String name;
    private String date;

    public ListNoteBean( String name, String date) {
        this.name = name;
        this.date = date;
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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

}