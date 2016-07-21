package com.xy.lifemanage.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by nemo on 2016/5/8 0008.
 */
public class UserBean extends BmobUser{
    private String organize;
    private BmobFile img;
    private Lable lables;
    private MyMessage mMessages;

    public BmobFile getImg() {
        return img;
    }

    public void setImg(BmobFile img) {
        this.img = img;
    }

    public Lable getLables() {
        return lables;
    }

    public void setLables(Lable lables) {
        this.lables = lables;
    }

    public MyMessage getmMessages() {
        return mMessages;
    }

    public void setmMessages(MyMessage mMessages) {
        this.mMessages = mMessages;
    }

    public String getOrganize() {
        return organize;
    }

    public void setOrganize(String organize) {
        this.organize = organize;
    }

    class MyMessage extends BmobObject{
        private HashMap<String,String> priMessage;
        private ArrayList<String> priTask;
        private HashMap<String,String> priInvite;
        private HashMap<String,String> priApply;

        public HashMap<String, String> getPriApply() {
            return priApply;
        }

        public void setPriApply(HashMap<String, String> priApply) {
            this.priApply = priApply;
        }

        public HashMap<String, String> getPriInvite() {
            return priInvite;
        }

        public void setPriInvite(HashMap<String, String> priInvite) {
            this.priInvite = priInvite;
        }

        public HashMap<String, String> getPriMessage() {
            return priMessage;
        }

        public void setPriMessage(HashMap<String, String> priMessage) {
            this.priMessage = priMessage;
        }

        public ArrayList<String> getPriTask() {
            return priTask;
        }

        public void setPriTask(ArrayList<String> priTask) {
            this.priTask = priTask;
        }
    }

    class Lable extends BmobObject{
        private String lable;
        private Integer count;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }
    }
}
