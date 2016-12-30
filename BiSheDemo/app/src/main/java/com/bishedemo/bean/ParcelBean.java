package com.bishedemo.bean;

import java.util.List;

/**
 * Created by fang on 2016/12/16.
 */

public class ParcelBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"nu":"3935862432336","ret_code":"0","ischeck":"0","com":"yunda","com_zh":"韵达快递","data":[{"time":"2016-11-30 20:37:37","location":"陕西西安长安区公司邮电大学便民服务站分部","context":"[陕西西安长安区公司邮电大学便民服务站分部]快件已被 已签收 签收","ftime":"2016-11-30 20:37:37"},{"time":"2016-11-30 20:25:00","location":"陕西西安长安区公司邮电大学便民服务站分部","context":"[陕西西安长安区公司邮电大学便民服务站分部]进行派件扫描；派送业务员：陈亮；联系电话：18149063085","ftime":"2016-11-30 20:25:00"},{"time":"2016-11-30 09:31:37","location":"陕西西安分拨中心","context":"[陕西西安分拨中心]从站点发出，本次转运目的地：陕西西安长安区公司","ftime":"2016-11-30 09:31:37"},{"time":"2016-11-30 08:35:03","location":"陕西西安分拨中心","context":"[陕西西安分拨中心]在分拨中心进行卸车扫描","ftime":"2016-11-30 08:35:03"},{"time":"2016-11-29 04:59:24","location":"广东广州分拨中心","context":"[广东广州分拨中心]进行装车扫描，即将发往：陕西西安分拨中心","ftime":"2016-11-29 04:59:24"},{"time":"2016-11-29 04:56:42","location":"广东广州分拨中心","context":"[广东广州分拨中心]在分拨中心进行称重扫描","ftime":"2016-11-29 04:56:42"},{"time":"2016-11-29 03:17:12","location":"广东广州天河区天平架一公司","context":"[广东广州天河区天平架一公司]进行下级地点扫描，将发往：陕西西安网点包","ftime":"2016-11-29 03:17:12"}],"state":"0","error_code":"000","error_description":"接口正常"}
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
         * nu : 3935862432336
         * ret_code : 0
         * ischeck : 0
         * com : yunda
         * com_zh : 韵达快递
         * data : [{"time":"2016-11-30 20:37:37","location":"陕西西安长安区公司邮电大学便民服务站分部","context":"[陕西西安长安区公司邮电大学便民服务站分部]快件已被 已签收 签收","ftime":"2016-11-30 20:37:37"},{"time":"2016-11-30 20:25:00","location":"陕西西安长安区公司邮电大学便民服务站分部","context":"[陕西西安长安区公司邮电大学便民服务站分部]进行派件扫描；派送业务员：陈亮；联系电话：18149063085","ftime":"2016-11-30 20:25:00"},{"time":"2016-11-30 09:31:37","location":"陕西西安分拨中心","context":"[陕西西安分拨中心]从站点发出，本次转运目的地：陕西西安长安区公司","ftime":"2016-11-30 09:31:37"},{"time":"2016-11-30 08:35:03","location":"陕西西安分拨中心","context":"[陕西西安分拨中心]在分拨中心进行卸车扫描","ftime":"2016-11-30 08:35:03"},{"time":"2016-11-29 04:59:24","location":"广东广州分拨中心","context":"[广东广州分拨中心]进行装车扫描，即将发往：陕西西安分拨中心","ftime":"2016-11-29 04:59:24"},{"time":"2016-11-29 04:56:42","location":"广东广州分拨中心","context":"[广东广州分拨中心]在分拨中心进行称重扫描","ftime":"2016-11-29 04:56:42"},{"time":"2016-11-29 03:17:12","location":"广东广州天河区天平架一公司","context":"[广东广州天河区天平架一公司]进行下级地点扫描，将发往：陕西西安网点包","ftime":"2016-11-29 03:17:12"}]
         * state : 0
         * error_code : 000
         * error_description : 接口正常
         */

        private String nu;
        private String ret_code;
        private String ischeck;
        private String com;
        private String com_zh;
        private String state;
        private String error_code;
        private String error_description;
        private List<DataBean> data;

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getCom_zh() {
            return com_zh;
        }

        public void setCom_zh(String com_zh) {
            this.com_zh = com_zh;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getError_description() {
            return error_description;
        }

        public void setError_description(String error_description) {
            this.error_description = error_description;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2016-11-30 20:37:37
             * location : 陕西西安长安区公司邮电大学便民服务站分部
             * context : [陕西西安长安区公司邮电大学便民服务站分部]快件已被 已签收 签收
             * ftime : 2016-11-30 20:37:37
             */

            private String time;
            private String location;
            private String context;
            private String ftime;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }
        }
    }
}
