package com.qnote.application.doc.bean;

import java.util.List;

/**
 * Created by silei on 2016/9/10.
 */
public class NoteListBean {

    private int status;


    private ValueBean value;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public static class ValueBean {
        private int content;
        private long updatetime;

        private List<ListNoteBean> date;

        public int getContent() {
            return content;
        }

        public void setContent(int content) {
            this.content = content;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(long updatetime) {
            this.updatetime = updatetime;
        }

        public List<ListNoteBean> getDate() {
            return date;
        }

        public void setDate(List<ListNoteBean> date) {
            this.date = date;
        }

    }
}
