
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

    public BasePlug(Context context) {
        mContext = context;
    }

    /**
     * 获取联合矩阵缓冲
     * 
     * @return
     */
    public FloatBuffer getMVPMatrixFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(mMVPMatrix).position(0);
        return floatbuffer;
    }

    /**
     * 获取联合矩阵
     * 
     * @return
     */
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
     * @param renderer 渲染器，其中存在世界坐标系矩阵变化
     * @param camera 摄像机，其中存在摄像机矩阵变化
     * @param projection 投影，其中包括投影矩阵变化
     */
    public abstract void draw(BaseRenderer renderer, Camera camera, Projection projection);

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
