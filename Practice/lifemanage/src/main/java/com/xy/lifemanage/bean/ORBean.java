package com.xy.lifemanage.bean;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by nemo on 2016/5/10 0010.
 */
public class ORBean extends BmobObject{
    private BmobFile or_image;
    private String or_name;
    private String or_post;

    public BmobFile getOr_image() {
        return or_image;
    }

    public void setOr_image(BmobFile or_image) {
        this.or_image = or_image;
    }

    public String getOr_name() {
        return or_name;
    }

    public void setOr_name(String or_name) {
        this.or_name = or_name;
    }

    public String getOr_post() {
        return or_post;
    }

    public void setOr_post(String or_post) {
        this.or_post = or_post;
    }
}
