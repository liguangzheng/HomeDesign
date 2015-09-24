
package com.homedesign.base;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Projection {

    public static final int PROJECTION_ORTHO = 0;// 正交投影
    public static final int PROJECTION_PERSPECTIVE = 1;// 透视投影

    /**
     * Store the projection matrix. This is used to project the scene onto a 2D
     * viewport.
     */
    private float[] mMatrix = new float[16];// 投影矩阵
    private FloatBuffer mFloatBuffer;

    public Projection() {
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
     * 创建投影
     * 
     * @param projection PROJECTION_ORTHO(正交)或PROJECTION_PERSPECTIVE(透视)
     * @param width
     * @param height
     * @param near 近平面位置
     * @param far 远平面位置
     */
    public void create(int projection, int width, int height, float near, float far) {
        final boolean isWidthLarger = width > height;
        final float ratio = isWidthLarger ? (float) width / height : (float) height / width;
        if (isWidthLarger) {
            // 横屏
            if (projection == PROJECTION_ORTHO) {
                Matrix.orthoM(mMatrix, 0, -ratio, ratio, -1.0f, 1.0f, near, far);
            } else if (projection == PROJECTION_PERSPECTIVE) {
                Matrix.frustumM(mMatrix, 0, -ratio, ratio, -1.0f, 1.0f, near, far);
            } else {
                try {
                    throw new Exception("projection is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // 竖屏
            if (projection == PROJECTION_ORTHO) {
                Matrix.orthoM(mMatrix, 0, -1.0f, 1.0f, -ratio, ratio, near, far);
            } else if (projection == PROJECTION_PERSPECTIVE) {
                Matrix.frustumM(mMatrix, 0, -1.0f, 1.0f, -ratio, ratio, near, far);
            } else {
                try {
                    throw new Exception("projection is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
