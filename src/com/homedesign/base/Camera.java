
package com.homedesign.base;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author liguangzheng
 * @description 摄像机类
 */
public class Camera {

    private Vector3 mPosition;
    private Vector3 mLook;
    private Vector3 mUp;

    /**
     * 模型视点矩阵。用于描述摄像机的变换。
     */
    private float[] mMatrix = new float[16];

    public Camera() {
    }

    /**
     * 获取视点矩阵
     * 
     * @return
     */
    public float[] getMatrix() {
        return mMatrix;
    }

    /**
     * 获取视点矩阵缓冲
     * 
     * @return
     */
    public FloatBuffer getFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(mMatrix).position(0);
        return floatbuffer;
    }

    /**
     * 设置摄像机位置
     * 
     * @param position
     */
    public void setPosition(Vector3 position) {
        mPosition = position;
    }

    /**
     * 设置摄像机朝向
     * 
     * @param look
     */
    public void setLook(Vector3 look) {
        mLook = look;
    }

    /**
     * 设置摄像机向上向量
     * 
     * @param up
     */
    public void setUp(Vector3 up) {
        mUp = up;
    }

    /**
     * 设置摄像机
     * 
     * @param position
     * @param look
     * @param up
     */
    public void setLookAt(Vector3 position, Vector3 look, Vector3 up) {
        mPosition = position;
        mLook = look;
        mUp = up;

        Matrix.setLookAtM(mMatrix, 0, position.getX(), position.getY(), position.getZ(), look.getX(), look.getY(),
                look.getZ(), up.getX(), up.getY(), up.getZ());
    }

    /**
     * 设置摄像机
     * 
     * @param position
     * @param look
     */
    public void setLookAt(Vector3 position, Vector3 look) {
        Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);
        setLookAt(position, look, up);
    }

    /**
     * 屏幕触控选择摄像机
     * 
     * @param screenx 屏上X轴的累加移动量 即3维空间按Y轴旋转的弧度
     * @param screeny 屏上Y轴的累加移动量 即3维空间按X轴旋转的弧度
     */
    public synchronized void updateLookAt(float screenx, float screeny) {
        Matrix.setLookAtM(mMatrix, 0, mPosition.getX(), mPosition.getY(), mPosition.getZ(), mLook.getX(), mLook.getY(),
                mLook.getZ(), mUp.getX(), mUp.getY(), mUp.getZ());
    }
}
