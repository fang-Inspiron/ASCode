package com.bishedemo.bean;

import java.util.List;

/**
 * Created by fang on 2017/5/10.
 */

public class BusBean {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"retList":[{"stats":"外语大学南校区;小居安;西北大学南校区;樱花广场;岔道口村;陕师大长安校区;邮电大学南校区;长安区政府;航天五零四所;政法大学南区;金堆城小区;太阳新城;绿园度假村;西北饭店;东长安街西口;韦曲北站;北长安街凤栖路口;航天大道西口;金昆家具;三森国际家居;电视塔;国展中心;吴家坟[陕西师范大学];政法大学;八里村;纬二街[雁塔西路长安路口];小寨[小寨南];长安立交;省体育场;南稍门;南门[南门外南省人大];钟楼[钟楼南开元商场];北大街[西五路北大街南];北门;西闸口南口;火车站西[环城北路尚德路西]","name":"616路(外语大学南校区-火车站西[环城北路尚德路西])","info":"火车站西\u2014外语大学南校区6:00-23:00|外语大学南校区\u2014火车站西6:30-23:00 ;无人售票空调车,投币2元,刷卡5折"},{"stats":"火车站西[环城北路尚德路西];西闸口南口;北门;北大街;钟楼[钟楼南城市酒店];南门[南门外南长安国际];南稍门;省体育场;长安立交;小寨[小寨南];纬二街[雁塔西路长安路口];八里村;政法大学;吴家坟[陕西师范大学];国展中心;电视塔;三森国际家居;金昆家具;航天大道西口;北长安街凤栖路口;韦曲北站;东长安街西口;西北饭店;绿园度假村;太阳新城;金堆城小区;政法大学南区;航天五零四所;长安区政府;邮电大学南校区;陕师大长安校区;樱花广场;西北大学南校区;小居安;外语大学南校区","name":"616路(火车站西[环城北路尚德路西]-外语大学南校区)","info":"火车站西\u2014外语大学南校区6:00-23:00|外语大学南校区\u2014火车站西6:30-23:00 ;无人售票空调车,投币2元,刷卡5折"}]}
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
         * retList : [{"stats":"外语大学南校区;小居安;西北大学南校区;樱花广场;岔道口村;陕师大长安校区;邮电大学南校区;长安区政府;航天五零四所;政法大学南区;金堆城小区;太阳新城;绿园度假村;西北饭店;东长安街西口;韦曲北站;北长安街凤栖路口;航天大道西口;金昆家具;三森国际家居;电视塔;国展中心;吴家坟[陕西师范大学];政法大学;八里村;纬二街[雁塔西路长安路口];小寨[小寨南];长安立交;省体育场;南稍门;南门[南门外南省人大];钟楼[钟楼南开元商场];北大街[西五路北大街南];北门;西闸口南口;火车站西[环城北路尚德路西]","name":"616路(外语大学南校区-火车站西[环城北路尚德路西])","info":"火车站西\u2014外语大学南校区6:00-23:00|外语大学南校区\u2014火车站西6:30-23:00 ;无人售票空调车,投币2元,刷卡5折"},{"stats":"火车站西[环城北路尚德路西];西闸口南口;北门;北大街;钟楼[钟楼南城市酒店];南门[南门外南长安国际];南稍门;省体育场;长安立交;小寨[小寨南];纬二街[雁塔西路长安路口];八里村;政法大学;吴家坟[陕西师范大学];国展中心;电视塔;三森国际家居;金昆家具;航天大道西口;北长安街凤栖路口;韦曲北站;东长安街西口;西北饭店;绿园度假村;太阳新城;金堆城小区;政法大学南区;航天五零四所;长安区政府;邮电大学南校区;陕师大长安校区;樱花广场;西北大学南校区;小居安;外语大学南校区","name":"616路(火车站西[环城北路尚德路西]-外语大学南校区)","info":"火车站西\u2014外语大学南校区6:00-23:00|外语大学南校区\u2014火车站西6:30-23:00 ;无人售票空调车,投币2元,刷卡5折"}]
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
             * stats : 外语大学南校区;小居安;西北大学南校区;樱花广场;岔道口村;陕师大长安校区;邮电大学南校区;长安区政府;航天五零四所;政法大学南区;金堆城小区;太阳新城;绿园度假村;西北饭店;东长安街西口;韦曲北站;北长安街凤栖路口;航天大道西口;金昆家具;三森国际家居;电视塔;国展中心;吴家坟[陕西师范大学];政法大学;八里村;纬二街[雁塔西路长安路口];小寨[小寨南];长安立交;省体育场;南稍门;南门[南门外南省人大];钟楼[钟楼南开元商场];北大街[西五路北大街南];北门;西闸口南口;火车站西[环城北路尚德路西]
             * name : 616路(外语大学南校区-火车站西[环城北路尚德路西])
             * info : 火车站西—外语大学南校区6:00-23:00|外语大学南校区—火车站西6:30-23:00 ;无人售票空调车,投币2元,刷卡5折
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
