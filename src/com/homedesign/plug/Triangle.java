
package com.homedesign.plug;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.homedesign.base.BasePlug;
import com.homedesign.base.BaseRenderer;
import com.homedesign.base.Camera;
import com.homedesign.base.Projection;
import com.homedesign.common.ESShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 部件：三角形
 * 
 * @author liguangzheng
 */
public class Triangle extends BasePlug {

    private FloatBuffer mVertices;
    private FloatBuffer mColors;

    private final float[] mVerticesData = {
            0.0f * UNIT, 0.5f * UNIT, 0.0f * UNIT, -0.5f * UNIT, 0.0f * UNIT, 0.0f * UNIT, 0.5f * UNIT, 0.0f * UNIT,
            0.0f * UNIT
    };

    private final float[] mColorData = {
            0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f
    };

    public Triangle(Context context) {
        super(context);
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
        mColors = ByteBuffer.allocateDirect(mColorData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mColors.put(mColorData).position(0);
    }

    @Override
    public void create() {
        // Store the program object
        int program = ESShader.loadProgramFromAsset(mContext, "shaders/triangle.vs", "shaders/triangle.fs");
        setProgramObject(program);
    }

    @Override
    public void draw(BaseRenderer renderer, Camera camera, Projection projection) {
        // Use the program object
        GLES20.glUseProgram(getProgramObject());
        // 顶点设置
        loadIdentity();
        // Matrix.translateM(getMVPMatrix().getArray(), 0, 0.5f, 0, 0);// 执行平移
        Matrix.rotateM(getModelMatrix().getArray(), 0, 45, 0, 0, 1);// 执行旋转

        // 颜色设置
        // 获取vertex attribute "a_color"的入口点
        int attributeColor = GLES20.glGetAttribLocation(getProgramObject(), "a_color");
        // 打开入a_color入口点
        GLES20.glEnableVertexAttribArray(attributeColor);
        // 传递顶点数据给a_color
        GLES20.glVertexAttribPointer(attributeColor, 4, GLES20.GL_FLOAT, false, 0, mColors);

        // 获取vertex attribute "a_position"的入口点
        int attributePosition = GLES20.glGetAttribLocation(getProgramObject(), "a_position");
        // 打开入a_position入口点
        GLES20.glEnableVertexAttribArray(attributePosition);
        // 传递顶点数据给a_position
        GLES20.glVertexAttribPointer(attributePosition, 3, GLES20.GL_FLOAT, false, 0, mVertices);

        // 获取uniform "matViewProjection"的入口点
        int attributeMatViewProjection = GLES20.glGetUniformLocation(getProgramObject(), "matViewProjection");
        float[] temp1 = new float[16];
        Matrix.multiplyMM(temp1, 0, renderer.getMatrix().getArray(), 0, getModelMatrix().getArray(), 0);
        float[] temp2 = new float[16];
        Matrix.multiplyMM(temp2, 0, camera.getMatrix().getArray(), 0, temp1, 0);
        float[] temp3 = new float[16];
        Matrix.multiplyMM(temp3, 0, projection.getMatrix().getArray(), 0, temp2, 0);
        GLES20.glUniformMatrix4fv(attributeMatViewProjection, 1, false, temp3, 0);

        // 执行绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);// GLES20.GL_TRIANGLES/GLES20.GL_LINE_LOOP
    }

    @Override
    public void translate(float tx, float ty, float tz) {
        super.translate(tx, ty, tz);
    }
}
