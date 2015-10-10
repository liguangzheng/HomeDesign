
package com.homedesign.common;

import android.renderscript.Matrix4f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 三维向量
 * 
 * @author liguangzheng
 */
public class Vector3f {

    private float[] vec = new float[3];

    public Vector3f() {
    }

    public Vector3f(float x, float y, float z) {
        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
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
     * @return
     */
    public static Vector3f copy(Vector3f res) {
        Vector3f out = new Vector3f();
        if (null == res) {
            throw new RuntimeException("vec is null");
        }
        out.setX(res.getX());
        out.setY(res.getY());
        out.setZ(res.getZ());
        return out;
    }

    /**
     * 标准化向量（向量除以其模，获取长度为1的向量）
     * 
     * @param vec
     */
    public static void normalize(Vector3f vec) {
        if (null == vec) {
            throw new RuntimeException("vec is null");
        }
        float scale = (float) Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY() + vec.getZ() * vec.getZ());
        if (scale == 0)
            return;
        scale = 1.0f / scale;
        vec.setX(vec.getX() * scale);
        vec.setY(vec.getY() * scale);
        vec.setZ(vec.getZ() * scale);
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
     * 点积
     * 
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
        float f0 = getX() * mat.getArray()[0] + getY() * mat.getArray()[4] + getZ() * mat.getArray()[8] + 1
                * mat.getArray()[12];

        float f1 = getX() * mat.getArray()[1] + getY() * mat.getArray()[5] + getZ() * mat.getArray()[9] + 1
                * mat.getArray()[13];

        float f2 = getX() * mat.getArray()[2] + getY() * mat.getArray()[6] + getZ() * mat.getArray()[10] + 1
                * mat.getArray()[14];
        setX(f0);
        setX(f1);
        setX(f2);
    }
}
