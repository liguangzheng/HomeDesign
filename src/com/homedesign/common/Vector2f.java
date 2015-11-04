
package com.homedesign.common;

import android.renderscript.Matrix4f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 二维向量
 * 
 * @author liguangzheng
 */
public class Vector2f {

    private float[] vec = new float[2];

    public Vector2f() {
    }

    public Vector2f(float v1, float v2) {
        vec[0] = v1;
        vec[1] = v2;
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
    public void copy(Vector2f res) {
        if (null == vec) {
            throw new RuntimeException("vec is null");
        }
        vec[0] = res.getX();
        vec[1] = res.getY();
    }

    /**
     * 向量的模（即向量的大小）
     * 
     * @return
     */
    public float getLength() {
        return (float) Math.sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * 向量缩放（标量*向量）
     * 
     * @param vec
     * @param value
     */
    public static void scale(Vector2f vec, float value) {
        if (null == vec) {
            throw new RuntimeException("vec is null");
        }
        vec.setX(vec.getX() * value);
        vec.setY(vec.getY() * value);
    }

    /**
     * 标准化向量（向量除以其模，获取长度为1的向量）
     * 
     * @param vec
     */
    public static void normalize(Vector2f vec) {
        if (null == vec) {
            throw new RuntimeException("vec is null");
        }
        float scale = (float) Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY());
        if (scale == 0)
            return;
        scale = 1.0f / scale;
        vec.setX(vec.getX() * scale);
        vec.setY(vec.getY() * scale);
    }

    /**
     * 叉积
     * 
     * @param va
     * @param vb
     * @return
     */
    public static Vector3f cross(Vector3f va, Vector3f vb) {
        Vector3f out = new Vector3f();
        if (null == va || null == vb) {
            throw new RuntimeException("vec is null");
        }
        out.setX(va.getY() * vb.getZ() - vb.getY() * va.getZ());
        out.setX(va.getZ() * vb.getX() - vb.getZ() * va.getX());
        out.setX(va.getX() * vb.getY() - vb.getX() * va.getY());
        return out;
    }

    /**
     * 点乘（或内积），即向量对应分量乘积的和，其结果是标量。
     * 
     * @几何解释 点乘结果描述了两个向量的“相似”程度，点乘结果越大，两向量越相近。
     * @param va
     * @param vb
     * @return
     */
    public static float dot(Vector3f va, Vector3f vb) {
        if (null == va || null == vb) {
            throw new RuntimeException("vec is null");
        }
        return va.getX() * vb.getX() + va.getY() * vb.getY() + va.getZ() * vb.getZ();
    }

    /**
     * 相加
     * 
     * @几何解释 相加结果向量的模，即为两个向量对应点的距离。
     * @param va
     * @param vb
     * @return
     */
    public static Vector3f add(Vector3f va, Vector3f vb) {
        Vector3f out = new Vector3f();
        if (null == va || null == vb) {
            throw new RuntimeException("vec is null");
        }
        out.setX(va.getX() + vb.getX());
        out.setY(va.getY() + vb.getY());
        out.setZ(va.getZ() + vb.getZ());
        return out;
    }

    /**
     * 相减
     * 
     * @几何解释 相减结果向量的模，即为两个向量对应点的距离。
     * @param va
     * @param vb
     * @return
     */
    public static Vector3f subtract(Vector3f va, Vector3f vb) {
        Vector3f out = new Vector3f();
        if (null == va || null == vb) {
            throw new RuntimeException("vec is null");
        }
        out.setX(va.getX() - vb.getX());
        out.setY(va.getY() - vb.getY());
        out.setZ(va.getZ() - vb.getZ());
        return out;
    }

    /**
     * 向量变换
     * 
     * @param mat 变换矩阵
     */
    public void transform(Matrix4f mat) {
        if (null == mat)
            throw new RuntimeException("mat is null");
    }
}
