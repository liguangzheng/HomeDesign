
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
        // Clear the color buffer
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Load the vertex data
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, mVertices);
        GLES20.glEnableVertexAttribArray(0);

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
