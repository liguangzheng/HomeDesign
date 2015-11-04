
package com.homedesign.base;

import android.opengl.Matrix;
import android.renderscript.Matrix4f;

import com.homedesign.common.Vector3f;

/**
 * @author liguangzheng
 * @description 摄像机类，支持平移和旋转
 */
public class Camera {

    /**
     * 用于记录三维观察坐标系（或视觉坐标系）变化进行描述（即记录其矩阵变化）
     */
    private Matrix4f mMatrix;
    private Vector3f mEye;// 摄像机(眼睛)在世界坐标系中的坐标
    private Vector3f mLook;// 摄像机(眼睛)所望的方向的一点
    private Vector3f mUp;// 摄像机(眼睛)上方一坐标
    private float mSpeed;// 摄像机(眼睛)移动速度

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
     * @param eye
     */
    public void setPosition(Vector3f eye) {
        mEye = eye;
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
     * @param eye
     * @param look
     * @param up
     */
    public void setLookAt(Vector3f eye, Vector3f look, Vector3f up) {
        mEye = eye;
        mLook = look;
        mUp = up;

        Matrix.setLookAtM(mMatrix.getArray(), 0, eye.getX(), eye.getY(), eye.getZ(), look.getX(), look.getY(),
                look.getZ(), up.getX(), up.getY(), up.getZ());
    }

    /**
     * 设置摄像机，默认up(0.0f, 1.0f, 0.0f)，即平视，眼睛在视觉坐标系中水平放置
     * 
     * @param position
     * @param look
     */
    public void setLookAt(Vector3f eye, Vector3f look) {
        Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
        setLookAt(eye, look, up);
    }

    /**
     * 屏幕触控选择摄像机
     * 
     * @param screenx 屏上X轴的累加移动量 即3维空间按Y轴旋转的弧度
     * @param screeny 屏上Y轴的累加移动量 即3维空间按X轴旋转的弧度
     */
    public synchronized void updateLookAt(float screenx, float screeny) {
        Vector3f tempUp = Vector3f.copy(mUp);
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
     * 绘制摄像机可视边框
     */
    public void drawBox() {

    }
}
