
package com.homedesign.util;

import android.util.Log;

import com.homedesign.BuildConfig;

public class LogUtil {

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

}
