
package com.homedesign;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.homedesign.renderer.TriangleRenderer;
import com.homedesign.util.LogUtil;
import com.homedesign.util.OpenglUtil;

public class ActivityMain extends Activity {
    private final static String TAG = "ActivityMain";

    private final int CONTEXT_CLIENT_VERSION = 2;
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new GLSurfaceView(this);

        if (OpenglUtil.detectOpenGLES20(this)) {
            mGLSurfaceView.setEGLContextClientVersion(CONTEXT_CLIENT_VERSION);
            mGLSurfaceView.setRenderer(new TriangleRenderer(this));
        } else {
            LogUtil.e(TAG, "当前设备不支持OpenGL：" + CONTEXT_CLIENT_VERSION);
            finish();
        }

        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
