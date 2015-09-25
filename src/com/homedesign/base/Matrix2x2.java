
package com.homedesign.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 2x2矩阵
 * 
 * @author liguangzheng
 */
public class Matrix2x2 {

    private float[] mat = new float[4];

    public Matrix2x2() {
    }

    public Matrix2x2(float x1, float y1, float x2, float y2) {
        mat[0] = x1;
        mat[1] = y1;
        mat[2] = x2;
        mat[3] = y2;
    }

    public Matrix2x2(Vector2 v1, Vector2 v2) {
        if (null != v1) {
            mat[0] = v1.getX();
            mat[1] = v1.getY();
        }
        if (null != v2) {
            mat[2] = v2.getX();
            mat[3] = v2.getY();
        }
    }

    /**
     * 获取数组
     * 
     * @return
     */
    public float[] getArray() {
        return mat;
    }

    /**
     * 获取矩阵缓冲
     * 
     * @return
     */
    public FloatBuffer getFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(mat).position(0);
        return floatbuffer;
    }
}
