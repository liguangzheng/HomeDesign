
package com.homedesign.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 4x4矩阵
 * 
 * @author liguangzheng
 */
public class Matrix4x4 {

    private float[] mat = new float[16];

    public Matrix4x4() {
    }

    public Matrix4x4(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2, float x3,
            float y3, float z3, float w3, float x4, float y4, float z4, float w4) {
        mat[0] = x1;
        mat[1] = y1;
        mat[2] = z1;
        mat[3] = w1;

        mat[4] = x2;
        mat[5] = y2;
        mat[6] = z2;
        mat[7] = w2;

        mat[8] = x3;
        mat[9] = y3;
        mat[10] = z3;
        mat[11] = w3;

        mat[12] = x4;
        mat[13] = y4;
        mat[14] = z4;
        mat[15] = w4;
    }

    public Matrix4x4(Vector4 v1, Vector4 v2, Vector4 v3, Vector4 v4) {
        if (null != v1) {
            mat[0] = v1.getX();
            mat[1] = v1.getY();
            mat[2] = v1.getZ();
            mat[3] = v1.getW();
        }
        if (null != v2) {
            mat[4] = v2.getX();
            mat[5] = v2.getY();
            mat[6] = v2.getZ();
            mat[7] = v2.getW();
        }
        if (null != v3) {
            mat[8] = v3.getX();
            mat[9] = v3.getY();
            mat[10] = v3.getZ();
            mat[11] = v3.getW();
        }
        if (null != v4) {
            mat[12] = v4.getX();
            mat[13] = v4.getY();
            mat[14] = v4.getZ();
            mat[15] = v4.getW();
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
