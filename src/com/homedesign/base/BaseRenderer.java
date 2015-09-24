
package com.homedesign.base;

import android.opengl.GLSurfaceView;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    private float[] mModelMatrix = new float[16];
    protected Camera mCamera;
    protected Projection mProjection;

    /**
     * 获取模型变换矩阵
     * @return
     */
    public float[] getMatrix() {
        return mModelMatrix;
    }

    /**
     * 获取摄像机
     * @return
     */
    public Camera getCamera() {
        return mCamera;
    }
    
    /**
     * 获取投影对象
     * @return
     */
    public Projection getProjection() {
        return mProjection;
    }
}
