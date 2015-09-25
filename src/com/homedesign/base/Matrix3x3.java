
package com.homedesign.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 3x3矩阵
 * 
 * @author liguangzheng
 */
public class Matrix3x3 {

    private float[] mat = new float[9];

    public Matrix3x3() {
    }

    public Matrix3x3(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        mat[0] = x1;
        mat[1] = y1;
        mat[2] = z1;
        mat[3] = x2;
        mat[4] = y2;
        mat[5] = z2;
        mat[6] = x3;
        mat[7] = y3;
        mat[8] = z3;
    }

    public Matrix3x3(Vector3 v1, Vector3 v2, Vector3 v3) {
        if (null != v1) {
            mat[0] = v1.getX();
            mat[1] = v1.getY();
            mat[2] = v1.getZ();
        }
        if (null != v2) {
            mat[3] = v2.getX();
            mat[4] = v2.getY();
            mat[5] = v2.getZ();
        }
        if (null != v3) {
            mat[6] = v3.getX();
            mat[7] = v3.getY();
            mat[8] = v3.getZ();
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
