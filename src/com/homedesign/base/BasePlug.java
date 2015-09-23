
package com.homedesign.base;

import android.content.Context;

public abstract class BasePlug {

    protected Context mContext;
    protected int mProgramObject;

    public BasePlug(Context context) {
        mContext = context;
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
     * 初始创建
     */
    public abstract void create();

    /**
     * 绘制
     */
    public abstract void draw();

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
