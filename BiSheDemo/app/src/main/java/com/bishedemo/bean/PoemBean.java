package com.bishedemo.bean;

import java.util.List;

/**
 * Created by fang on 2016/12/16.
 */

public class PoemBean {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"list":["美景向空尽，好尚古人心。","美人结长恨，好事不出门。","美人闭红烛，好和薰御服。","美人摘新英，好寺松为径。","美人何处所，好赠玉条脱。","美人不共此，好鸟始云至。"]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * list : ["美景向空尽，好尚古人心。","美人结长恨，好事不出门。","美人闭红烛，好和薰御服。","美人摘新英，好寺松为径。","美人何处所，好赠玉条脱。","美人不共此，好鸟始云至。"]
         */

        private int ret_code;
        private List<String> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
