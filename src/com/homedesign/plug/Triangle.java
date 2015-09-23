
package com.homedesign.plug;

import android.content.Context;
import android.opengl.GLES20;

import com.homedesign.base.BasePlug;
import com.homedesign.common.ESShader;
import com.homedesign.common.ESTransform;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * 部件：三角形
 * 
 * @author liguangzheng
 */
public class Triangle extends BasePlug {

    private ESTransform mTransform;
    private FloatBuffer mVertices;
    private FloatBuffer mColors;

    private final float[] mVerticesData = {
            0.0f, 0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f
    };

    private final float[] mColorData = {
            0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f
    };

    public Triangle(Context context) {
        super(context);
        mTransform = new ESTransform();
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
        mColors = ByteBuffer.allocateDirect(mColorData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mColors.put(mColorData).position(0);
    }

    @Override
    public void create() {
        // Store the program object
        mProgramObject = ESShader.loadProgramFromAsset(mContext, "shaders/triangle.vs", "shaders/triangle.fs");
    }

    @Override
    public void draw() {
        // Use the program object
        GLES20.glUseProgram(mProgramObject);
        // 顶点设置
        mTransform.matrixLoadIdentity();
        mTransform.rotate(45, 0, 0, 1);

        // 颜色设置
        // 获取vertex attribute "a_color"的入口点
        int attributeColor = GLES20.glGetAttribLocation(mProgramObject, "a_color");
        // 打开入a_color入口点
        GLES20.glEnableVertexAttribArray(attributeColor);
        // 传递顶点数据给a_color
        GLES20.glVertexAttribPointer(attributeColor, 4, GLES20.GL_FLOAT, false, 0, mColors);

        // 获取vertex attribute "a_position"的入口点
        int attributePosition = GLES20.glGetAttribLocation(mProgramObject, "a_position");
        // 打开入a_position入口点
        GLES20.glEnableVertexAttribArray(attributePosition);
        // 传递顶点数据给a_position
        GLES20.glVertexAttribPointer(attributePosition, 3, GLES20.GL_FLOAT, false, 0, mVertices);

        // 获取uniform "matViewProjection"的入口点
        int attributeMatViewProjection = GLES20.glGetUniformLocation(mProgramObject, "matViewProjection");
        GLES20.glUniformMatrix4fv(attributeMatViewProjection, 1, false, mTransform.getAsFloatBuffer());

        // 执行绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);// GLES20.GL_TRIANGLES/GLES20.GL_LINE_LOOP
    }

    @Override
    public void translate(float tx, float ty, float tz) {
        super.translate(tx, ty, tz);
    }
}
