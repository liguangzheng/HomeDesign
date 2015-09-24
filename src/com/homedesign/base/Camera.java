
package com.homedesign.base;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Camera {

    /**
     * Store the view matrix. This can be thought of as our camera. This matrix
     * transforms world space to eye space; it positions things relative to our
     * eye.
     */
    private float[] mMatrix = new float[16];// 摄像机矩阵
    private FloatBuffer mFloatBuffer;

    public Camera() {
        mFloatBuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    /**
     * 获取矩阵
     * 
     * @return
     */
    public float[] getMatrix() {
        return mMatrix;
    }

    /**
     * 获取浮点型缓冲
     * 
     * @return
     */
    public FloatBuffer getFloatBuffer() {
        mFloatBuffer.put(mMatrix).position(0);
        return mFloatBuffer;
    }

    /**
     * 初始化摄像机
     */
    public void create() {
        // 设置相机
        // eye--摄像头在世界坐标系中的坐标
        final float eyeX = 1.5f;
        final float eyeY = 1.5f;
        final float eyeZ = 1.5f;
        // look--摄像头所望的方向的一点
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = 0.0f;
        // up--摄像头上方一坐标
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;
        Matrix.setLookAtM(mMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
    }
}
