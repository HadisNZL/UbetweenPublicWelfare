package com.ubetween.ubetweenpublicwelfare.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ubetween.ubetweenpublicwelfare.base.BaseApplication;

/**
 * 缓存的管理工具
 *
 * @author hadis on 16.3.14.
 */
public class SharePrefenceUtil {

    public static final String TOKEN = "token";
    public static final String FIRST = "first";

    /**
     * 全局配置文件 获取时用
     *
     * @return sp
     */
    public static SharedPreferences getSp() {
        return BaseApplication.getInstance().getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    /**
     * 获全局配置文件 保存时用
     *
     * @return editor
     */
    public static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }

    /**
     * 存取token string
     *
     * @param token
     */
    public static void setToken(String token) {
        getEditor().putString(TOKEN, token).apply();
    }

    public static String getToken() {
        return getSp().getString(TOKEN, "");
    }

    /**
     * 判断是不是第一次进入程序 返回 boolean
     *
     * @param first
     */
    public static void setFirst(boolean first) {
        getEditor().putBoolean(FIRST, first).apply();
    }

    public static boolean getFirst(boolean defValue) {
        return getSp().getBoolean(FIRST, defValue);
    }

    /**
     * 清除某一条
     *
     * @param key
     */
    public static void clearOne(String key) {
        getEditor().remove(key).apply();
    }

    /**
     * 清除Shareprefence所有的
     */
    public static void clearAll() {
        getEditor().clear().apply();
    }
}
