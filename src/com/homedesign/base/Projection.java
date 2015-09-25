
package com.homedesign.base;

import android.opengl.Matrix;
import android.renderscript.Matrix4f;

public class Projection {

    public static final int PROJECTION_ORTHO = 0;// 正交投影
    public static final int PROJECTION_PERSPECTIVE = 1;// 透视投影

    /**
     * 投影矩阵
     */
    private Matrix4f mMatrix;

    public Projection() {
        mMatrix = new Matrix4f();
    }

    /**
     * 获取投影矩阵
     * 
     * @return
     */
    public Matrix4f getMatrix() {
        return mMatrix;
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
                Matrix.orthoM(mMatrix.getArray(), 0, -ratio, ratio, -1.0f, 1.0f, near, far);
            } else if (projection == PROJECTION_PERSPECTIVE) {
                Matrix.frustumM(mMatrix.getArray(), 0, -ratio, ratio, -1.0f, 1.0f, near, far);
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
                Matrix.orthoM(mMatrix.getArray(), 0, -1.0f, 1.0f, -ratio, ratio, near, far);
            } else if (projection == PROJECTION_PERSPECTIVE) {
                Matrix.frustumM(mMatrix.getArray(), 0, -1.0f, 1.0f, -ratio, ratio, near, far);
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
