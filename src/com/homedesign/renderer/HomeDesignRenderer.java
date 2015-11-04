
package com.homedesign.renderer;

import android.content.Context;
import android.opengl.GLES20;

import com.homedesign.base.BaseRenderer;
import com.homedesign.base.Camera;
import com.homedesign.base.Projection;
import com.homedesign.common.Vector3f;
import com.homedesign.plug.Cube;
import com.homedesign.plug.Line;
import com.homedesign.plug.Triangle;
import com.homedesign.util.LogUtil;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class HomeDesignRenderer extends BaseRenderer {
    private static final String TAG = "HomeDesignRenderer";

    private Line mLine;
    private Triangle mTriangle;
    private Cube mCube;

    public HomeDesignRenderer(Context context) {
        mLine = new Line(context);
        mTriangle = new Triangle(context);
        mCube = new Cube(context);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        // 设置背景色，为白色
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        // 启动深度测试
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        // 开背裁()
        GLES20.glDisable(GLES20.GL_CULL_FACE);
        // 初始化摄像机（eye world）
        mCamera = new Camera();
        mCamera.setLookAt(new Vector3f(2.0f, 2.0f, 2.0f), new Vector3f(0.0f, 0.0f, 0.0f));

        // 对世界坐标系进行交换
        // 平移
        // Matrix.translateM(getMatrix().getArray(), 0, 0.0f, 0.0f, -0.5f);
        // 旋转
        // Matrix.rotateM(getMatrix().getArray(), 0, 45.0f, 1.0f, 0.0f, 0.0f);

        mLine.create();
        mTriangle.create();
        mCube.create();
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        LogUtil.e(TAG, "renderer onDrawFrame");
        // 清空颜色缓冲和深度缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // 部件绘制
        mLine.draw(this, mCamera, mProjection);
        // mTriangle.draw(this, mCamera, mProjection);
        mCube.draw(this, mCamera, mProjection);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        // 设置视窗
        GLES20.glViewport(0, 0, width, height);
        // 设置投影
        mProjection = new Projection();
        mProjection.create(Projection.PROJECTION_PERSPECTIVE, width, height, 1.0f, 10.0f);
    }

}
