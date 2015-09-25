
package com.homedesign.base;

import android.opengl.Matrix;
import android.renderscript.Matrix4f;

import com.homedesign.common.Vector3f;

/**
 * @author liguangzheng
 * @description 摄像机类
 */
public class Camera {

    private Matrix4f mMatrix;
    private Vector3f mPosition;
    private Vector3f mLook;
    private Vector3f mUp;

    public Camera() {
        mMatrix = new Matrix4f();
    }

    /**
     * 获取视点矩阵
     * 
     * @return
     */
    public Matrix4f getMatrix() {
        return mMatrix;
    }

    /**
     * 设置摄像机位置
     * 
     * @param position
     */
    public void setPosition(Vector3f position) {
        mPosition = position;
    }

    /**
     * 设置摄像机朝向
     * 
     * @param look
     */
    public void setLook(Vector3f look) {
        mLook = look;
    }

    /**
     * 设置摄像机向上向量
     * 
     * @param up
     */
    public void setUp(Vector3f up) {
        mUp = up;
    }

    /**
     * 设置摄像机
     * 
     * @param position
     * @param look
     * @param up
     */
    public void setLookAt(Vector3f position, Vector3f look, Vector3f up) {
        mPosition = position;
        mLook = look;
        mUp = up;

        Matrix.setLookAtM(mMatrix.getArray(), 0, position.getX(), position.getY(), position.getZ(), look.getX(),
                look.getY(), look.getZ(), up.getX(), up.getY(), up.getZ());
    }

    /**
     * 设置摄像机
     * 
     * @param position
     * @param look
     */
    public void setLookAt(Vector3f position, Vector3f look) {
        Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
        setLookAt(position, look, up);
    }

    /**
     * 屏幕触控选择摄像机
     * 
     * @param screenx 屏上X轴的累加移动量 即3维空间按Y轴旋转的弧度
     * @param screeny 屏上Y轴的累加移动量 即3维空间按X轴旋转的弧度
     */
    public synchronized void updateLookAt(float screenx, float screeny) {
        Matrix.setLookAtM(mMatrix.getArray(), 0, mPosition.getX(), mPosition.getY(), mPosition.getZ(), mLook.getX(),
                mLook.getY(), mLook.getZ(), mUp.getX(), mUp.getY(), mUp.getZ());
    }
}
