
package com.homedesign.base;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class BasePlug {

    protected Context mContext;
    private int mProgramObject;
    /**
     * Allocate storage for the final combined matrix. This will be passed into
     * the shader program.
     */
    private float[] mMVPMatrix = new float[16];
    private FloatBuffer mMVPMatrixFloatBuffer;

    public BasePlug(Context context) {
        mContext = context;
        mMVPMatrixFloatBuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public FloatBuffer getMVPMatrixFloatBuffer() {
        mMVPMatrixFloatBuffer.put(mMVPMatrix).position(0);
        return mMVPMatrixFloatBuffer;
    }

    public float[] getMVPMatrix() {
        return mMVPMatrix;
    }

    /**
     * 获取当前装载程序
     * 
     * @param programobject
     */
    public void setProgramObject(int programobject) {
        mProgramObject = programobject;
    }

    /**
     * 设置当前装载程序
     * 
     * @return
     */
    public int getProgramObject() {
        return mProgramObject;
    }

    /**
     * 初始创建，一般用于创建程序并绑定着色器
     */
    public abstract void create();

    /**
     * 绘制
     * 
     * @param camera
     * @param projection
     */
    public abstract void draw(Camera camera, Projection projection);

    /**
     * 平移
     * 
     * @param tx
     * @param ty
     * @param tz
     */
    public void translate(float tx, float ty, float tz) {

    }

    /**
     * 旋转
     * 
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public void rotate(float angle, float x, float y, float z) {

    }

    /**
     * 缩放
     * 
     * @param sx
     * @param sy
     * @param sz
     */
    public void scale(float sx, float sy, float sz) {

    }
}
