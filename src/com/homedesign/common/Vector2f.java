
package com.homedesign.common;

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
