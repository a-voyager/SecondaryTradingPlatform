package com.swpuiot.stp.utils;

import com.google.gson.Gson;

/**
 * Created by DELL on 2016/12/11.
 */
public class GsonUtils {

    private static Gson gson = new Gson();

    public static String toJson(Object o){
        return gson.toJson(o);
    }

    public static  <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

}
