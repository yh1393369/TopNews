package com.rookie.www.topnews.utils;

/**
 * Created by Hi on 2017/2/13.
 */

public class Constant {

    public static final String URL_BASE = "http://v.juhe.cn/toutiao/index";
    public static final String KEY = "0e2e3d9e573eed9381e672a2ff752cca";

    public static final String NEWS_TYPE_TOP = "top";
    public static final String NEWS_TYPE_FASHION = "shishang";
    public static final String NEWS_TYPE_SOCIETY = "shehui";
    public static final String NEWS_TYPE_HOME = "guonei";
    public static final String NEWS_TYPE_ABROAD = "guoji";
    public static final String NEWS_TYPE_AMUSE = "yule";
    public static final String NEWS_TYPE_SPORT = "tiyu";
    public static final String NEWS_TYPE_WAR = "junshi";
    public static final String NEWS_TYPE_SCIENCE = "keji";

    public static String getUrl(String newsType){
        return URL_BASE + "?type=" + newsType + "&key=" + KEY;
    }

}
