
package com.homedesign.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

public class OpenglUtil {

    /**
     * 检测当前设备是否支持OpenGL 2.0
     * 
     * @param context
     * @return
     */
    public static boolean detectOpenGLES20(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }

    /**
     * 检测当前设备是否支持OpenGL 3.0
     * 
     * @param context
     * @return
     */
    public static boolean detectOpenGLES30(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x30000);
    }
}
