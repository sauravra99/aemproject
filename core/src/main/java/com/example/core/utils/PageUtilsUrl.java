package com.example.core.utils;

public class PageUtilsUrl {

    public static String getValidUrl(String pagePath){

        if (pagePath ==null){

            return null;
        }
        if (pagePath.startsWith("http://") || pagePath.startsWith("https://") ){

            return pagePath;
        }

        return pagePath + ".html";
    }

}
