
package com.homedesign.base;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    private float[] mModelMatrix = new float[16];
    protected Camera mCamera;
    protected Projection mProjection;

    /**
     * 获取模型变换矩阵
     * 
     * @return
     */
    public float[] getMatrix() {
        return mModelMatrix;
    }

    /**
     * 获取模型变换矩阵缓冲
     * 
     * @return
     */
    public FloatBuffer getModelMatrixFloatBuffer() {
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatbuffer.put(mModelMatrix).position(0);
        return floatbuffer;
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
