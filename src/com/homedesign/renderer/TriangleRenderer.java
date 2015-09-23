
package com.homedesign.renderer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.homedesign.common.ESShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TriangleRenderer implements GLSurfaceView.Renderer {

    private Context mContext;
    private int mProgramObject;
    private int mWidth;
    private int mHeight;
    private FloatBuffer mVertices;

    private final float[] mVerticesData = {
            0.0f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f
    };

    public TriangleRenderer(Context context) {
        this.mContext = context;
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        // Store the program object
        mProgramObject = ESShader.loadProgramFromAsset(mContext, "shaders/triangle.vs", "shaders/triangle.fs");

        // 设置背景色
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        // Use the program object
        GLES20.glUseProgram(mProgramObject);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        // 清空颜色缓冲和深度缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // 顶点设置
        // 获取vertex attribute "a_position"的入口点
        int attributePosition = GLES20.glGetAttribLocation(mProgramObject, "a_position");
        // 打开入a_position入口点
        GLES20.glEnableVertexAttribArray(attributePosition);
        // 传递顶点数据给a_position
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, mVertices);
        // 执行绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);// GLES20.GL_TRIANGLES/GLES20.GL_LINE_LOOP
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        mWidth = width;
        mHeight = height;
        // Set the viewport
        GLES20.glViewport(0, 0, mWidth, mHeight);
    }

}
