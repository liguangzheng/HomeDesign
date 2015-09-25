
package com.homedesign.base;

import android.opengl.GLSurfaceView;
import android.renderscript.Matrix4f;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    /**
     * 对世界坐标系（OpenGL中的三维空间）变化进行描述（即记录其矩阵变化）
     */
    private Matrix4f mWorldMatrix = new Matrix4f();
    protected Camera mCamera;
    protected Projection mProjection;

    /**
     * 获取模型变换矩阵
     * 
     * @return
     */
    public Matrix4f getMatrix() {
        return mWorldMatrix;
    }

    /**
     * 获取摄像机
     * 
     * @return
     */
    public Camera getCamera() {
        return mCamera;
    }

    /**
     * 获取投影对象
     * 
     * @return
     */
    public Projection getProjection() {
        return mProjection;
    }
}
