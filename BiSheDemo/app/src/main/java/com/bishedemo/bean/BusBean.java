package com.bishedemo.bean;

import java.util.List;

/**
 * Created by fang on 2016/12/16.
 */

public class BusBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"retList":[{"stats":"西直门北;车公庄北;索家坟;文慧桥北;明光桥北;蓟门桥;蓟门桥北;学知桥北;北京航空航天大学;成府路口南;北京语言大学;地铁五道口站;五道口;清华园;蓝旗营;中关园北站;清华大学西门;颐和园路东口;西苑北站;坡上村;地铁北宫门站;颐和园北宫门;安河桥;红山口;国防大学;黑山扈;西北旺;韩家川北站;韩家川村;韩家川","name":"375路(西直门北-韩家川)","info":"首末车:5:30-22:10;10公里以内票价2元,每增加5公里以内加价1元。持卡乘车普通卡5折、学生卡2.5折优惠。"},{"stats":"韩家川;韩家川村;韩家川北站;西北旺;黑山扈;国防大学;红山口;安河桥;颐和园北宫门;地铁北宫门站;坡上村;西苑北站;颐和园路东口;清华大学西门;中关园北站;蓝旗营;清华园;五道口;地铁五道口站;北京语言大学;成府路口南;北京航空航天大学;学知桥南;蓟门桥北;蓟门桥南;明光桥南;文慧桥南;索家坟;西直门北","name":"375路(韩家川-西直门北)","info":"首末车:5:30-22:10;10公里以内票价2元,每增加5公里以内加价1元。持卡乘车普通卡5折、学生卡2.5折优惠。"}]}
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
         * retList : [{"stats":"西直门北;车公庄北;索家坟;文慧桥北;明光桥北;蓟门桥;蓟门桥北;学知桥北;北京航空航天大学;成府路口南;北京语言大学;地铁五道口站;五道口;清华园;蓝旗营;中关园北站;清华大学西门;颐和园路东口;西苑北站;坡上村;地铁北宫门站;颐和园北宫门;安河桥;红山口;国防大学;黑山扈;西北旺;韩家川北站;韩家川村;韩家川","name":"375路(西直门北-韩家川)","info":"首末车:5:30-22:10;10公里以内票价2元,每增加5公里以内加价1元。持卡乘车普通卡5折、学生卡2.5折优惠。"},{"stats":"韩家川;韩家川村;韩家川北站;西北旺;黑山扈;国防大学;红山口;安河桥;颐和园北宫门;地铁北宫门站;坡上村;西苑北站;颐和园路东口;清华大学西门;中关园北站;蓝旗营;清华园;五道口;地铁五道口站;北京语言大学;成府路口南;北京航空航天大学;学知桥南;蓟门桥北;蓟门桥南;明光桥南;文慧桥南;索家坟;西直门北","name":"375路(韩家川-西直门北)","info":"首末车:5:30-22:10;10公里以内票价2元,每增加5公里以内加价1元。持卡乘车普通卡5折、学生卡2.5折优惠。"}]
         */

        private int ret_code;
        private List<RetListBean> retList;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<RetListBean> getRetList() {
            return retList;
        }

        public void setRetList(List<RetListBean> retList) {
            this.retList = retList;
        }

        public static class RetListBean {
            /**
             * stats : 西直门北;车公庄北;索家坟;文慧桥北;明光桥北;蓟门桥;蓟门桥北;学知桥北;北京航空航天大学;成府路口南;北京语言大学;地铁五道口站;五道口;清华园;蓝旗营;中关园北站;清华大学西门;颐和园路东口;西苑北站;坡上村;地铁北宫门站;颐和园北宫门;安河桥;红山口;国防大学;黑山扈;西北旺;韩家川北站;韩家川村;韩家川
             * name : 375路(西直门北-韩家川)
             * info : 首末车:5:30-22:10;10公里以内票价2元,每增加5公里以内加价1元。持卡乘车普通卡5折、学生卡2.5折优惠。
             */

            private String stats;
            private String name;
            private String info;

            public String getStats() {
                return stats;
            }

            public void setStats(String stats) {
                this.stats = stats;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
    }
}
