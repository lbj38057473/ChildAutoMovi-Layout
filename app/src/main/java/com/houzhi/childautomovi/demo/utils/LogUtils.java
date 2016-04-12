package com.houzhi.childautomovi.demo.utils;

import android.util.Log;

/**
 * Created by houzhi on 6/19/15.
 */
public class LogUtils {
static boolean debug = false;
    public static void i(String tag, String info) {
        if(debug)
        Log.i(tag,info);
    }

    public static void e(String tag, String info){
       if(debug) Log.e(tag,info);
    }

    public static void d(String tag, String info){
        if(debug) Log.d(tag,info);
    }
}
