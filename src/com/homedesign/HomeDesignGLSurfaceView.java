package com.homedesign;

import com.homedesign.base.BaseRenderer;
import com.homedesign.renderer.HomeDesignRenderer;
import com.homedesign.util.LogUtil;
import com.homedesign.util.OpenglUtil;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
* @author liguangzheng
* @version 创建时间：2015年9月24日 下午11:26:55
* @description 
*/
public class HomeDesignGLSurfaceView extends GLSurfaceView {
    private final static String TAG = "HomeDesignGLSurfaceView";
    private final int CONTEXT_CLIENT_VERSION = 2;
    
    private BaseRenderer mRenderer;
    
    public HomeDesignGLSurfaceView(Context context) {
        super(context);
        init(context);
    }
    
    public HomeDesignGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    
    private void init (Context context) {
        if (OpenglUtil.detectOpenGLES20(context)) {
            setEGLContextClientVersion(CONTEXT_CLIENT_VERSION);
            mRenderer = new HomeDesignRenderer(context);
            setRenderer(mRenderer);
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);// 主动渲染模式
        } else {
            LogUtil.e(TAG, "当前设备不支持OpenGL：" + CONTEXT_CLIENT_VERSION);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}