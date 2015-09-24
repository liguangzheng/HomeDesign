
package com.homedesign.base;

import android.opengl.GLSurfaceView;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    private float[] mModelMatrix = new float[16];

    public float[] getMatrix() {
        return mModelMatrix;
    }

}
