
package com.homedesign.renderer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.homedesign.plug.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class HomeDesignRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    public HomeDesignRenderer(Context context) {
        mTriangle = new Triangle(context);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        mTriangle.create();
        // 设置背景色，为白色
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        // 清空颜色缓冲和深度缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // 部件绘制
        mTriangle.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        // 设置视窗
        GLES20.glViewport(0, 0, width, height);
    }

}
