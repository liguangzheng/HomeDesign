
package com.homedesign.base;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author liguangzheng
 * @description 摄像机类
 */
public class Camera {

    /**
     * 模型视点矩阵。用于描述摄像机的变换。
     */
    private float[] mMatrix = new float[16];

    public Camera() {
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
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(mMatrix).position(0);
        return floatbuffer;
    }

    /**
     * 初始化摄像机
     */
    public void setLookAt(float eyex, float eyey, float eyez, float lookx, float looky, float lookz) {
        Matrix.setLookAtM(mMatrix, 0, eyex, eyey, eyez, lookx, looky, lookz, 0.0f, 1.0f, 0.0f);
    }
}
