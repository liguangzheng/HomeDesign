
package com.homedesign.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 四维向量
 * 
 * @author liguangzheng
 */
public class Vector4 {

    private float[] vec = new float[4];

    public Vector4(float v1, float v2, float v3, float v4) {
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
     * 获取向量缓冲
     * 
     * @return
     */
    public FloatBuffer getFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(vec).position(0);
        return floatbuffer;
    }
}
