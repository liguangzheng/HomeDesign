
package com.homedesign.common;

import android.renderscript.Matrix4f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 四维向量
 * 
 * @author liguangzheng
 */
public class Vector4f {

    private float[] vec = new float[4];

    public Vector4f(float v1, float v2, float v3, float v4) {
        vec[0] = v1;
        vec[1] = v2;
        vec[2] = v3;
        vec[3] = v4;
    }

    /**
     * 获取数组
     * 
     * @return
     */
    public float[] getArray() {
        return vec;
    }

    /**
     * 获取第一个元素
     * 
     * @return
     */
    public float getX() {
        return vec[0];
    }

    /**
     * 获取第二个元素
     * 
     * @return
     */
    public float getY() {
        return vec[1];
    }

    /**
     * 获取第三个元素
     * 
     * @return
     */
    public float getZ() {
        return vec[2];
    }

    /**
     * 获取第四个元素
     * 
     * @return
     */
    public float getW() {
        return vec[3];
    }

    /**
     * 设置第一个元素
     * 
     * @param x
     */
    public void setX(float x) {
        vec[0] = x;
    }

    /**
     * 设置第二个元素
     * 
     * @param y
     */
    public void setY(float y) {
        vec[1] = y;
    }

    /**
     * 设置第三个元素
     * 
     * @param z
     */
    public void setZ(float z) {
        vec[2] = z;
    }

    /**
     * 设置第四个元素
     * 
     * @param z
     */
    public void setW(float w) {
        vec[3] = w;
    }

    /**
     * 获取向量缓冲
     * 
     * @return
     */
    public FloatBuffer getFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(vec).position(0);
        return floatbuffer;
    }

    /**
     * 复制指定向量
     * 
     * @param res
     */
    public void copy(Vector4f res) {
        if (null == vec) {
            throw new RuntimeException("vec is null");
        }
        vec[0] = res.getX();
        vec[1] = res.getY();
        vec[2] = res.getZ();
        vec[3] = res.getW();
    }

    /**
     * 向量变换
     * 
     * @param mat 变换矩阵
     */
    public void transform(Matrix4f mat) {
        if (null == mat)
            throw new RuntimeException("mat is null");
        float f0 = getX() * mat.getArray()[0] + getY() * mat.getArray()[1] + getZ() * mat.getArray()[2] + getW()
                * mat.getArray()[3];
        float f1 = getX() * mat.getArray()[4] + getY() * mat.getArray()[5] + getZ() * mat.getArray()[6] + getW()
                * mat.getArray()[7];
        float f2 = getX() * mat.getArray()[8] + getY() * mat.getArray()[9] + getZ() * mat.getArray()[10] + getW()
                * mat.getArray()[11];
        float f3 = getX() * mat.getArray()[12] + getY() * mat.getArray()[13] + getZ() * mat.getArray()[14] + getW()
                * mat.getArray()[15];
        setX(f0);
        setY(f1);
        setZ(f2);
        setW(f3);
    }
}
