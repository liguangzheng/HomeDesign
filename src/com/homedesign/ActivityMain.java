
package com.homedesign;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.homedesign.renderer.HelloTriangleRenderer;
import com.homedesign.util.LogUtil;
import com.homedesign.util.OpenglUtil;

public class ActivityMain extends Activity {

    private final int CONTEXT_CLIENT_VERSION = 2;
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new GLSurfaceView(this);

        if (OpenglUtil.detectOpenGLES20(this)) {
            // Tell the surface view we want to create an OpenGL ES
            // 2.0-compatible
            // context, and set an OpenGL ES 2.0-compatible renderer.
            mGLSurfaceView.setEGLContextClientVersion(CONTEXT_CLIENT_VERSION);
            mGLSurfaceView.setRenderer(new HelloTriangleRenderer(this));
        } else {
            LogUtil.e("HelloTriangle", "OpenGL ES 2.0 not supported on device.  Exiting...");
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
