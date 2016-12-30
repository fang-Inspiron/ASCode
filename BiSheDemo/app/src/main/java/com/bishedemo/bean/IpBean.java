package com.bishedemo.bean;

/**
 * Created by fang on 2016/12/13.
 */

public class IpBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"region":"云南","county":"","isp":"移动","continents":"亚洲","en_name":"China","city_code":"530100","lnt":"102.712251","lat":"25.040609","en_name_short":"CN","ip":"183.225.1.165","city":"昆明","country":"中国"}
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
         * region : 云南
         * county :
         * isp : 移动
         * continents : 亚洲
         * en_name : China
         * city_code : 530100
         * lnt : 102.712251
         * lat : 25.040609
         * en_name_short : CN
         * ip : 183.225.1.165
         * city : 昆明
         * country : 中国
         */

        private String region;
        private String county;
        private String isp;
        private String continents;
        private String en_name;
        private String city_code;
        private String lnt;
        private String lat;
        private String en_name_short;
        private String ip;
        private String city;
        private String country;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public String getContinents() {
            return continents;
        }

        public void setContinents(String continents) {
            this.continents = continents;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getLnt() {
            return lnt;
        }

        public void setLnt(String lnt) {
            this.lnt = lnt;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getEn_name_short() {
            return en_name_short;
        }

        public void setEn_name_short(String en_name_short) {
            this.en_name_short = en_name_short;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
