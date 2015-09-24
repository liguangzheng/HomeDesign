
package com.homedesign.plug;

import android.content.Context;
import android.opengl.GLES20;

import com.homedesign.base.BasePlug;
import com.homedesign.base.Camera;
import com.homedesign.base.Projection;
import com.homedesign.common.ESShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Line extends BasePlug {

    private FloatBuffer mVertices;
    private FloatBuffer mColors;

    private final float[] mVerticesData = {
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f
    };

    private final float[] mColorData = {
            1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f
    };

    public Line(Context context) {
        super(context);
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
        mColors = ByteBuffer.allocateDirect(mColorData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mColors.put(mColorData).position(0);
    }

    @Override
    public void create() {
        int program = ESShader.loadProgramFromAsset(mContext, "shaders/line.vs", "shaders/line.fs");
        setProgramObject(program);
    }

    @Override
    public void draw(Camera camera, Projection projection) {
        GLES20.glUseProgram(getProgramObject());

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
        GLES20.glVertexAttribPointer(attributePosition, 2, GLES20.GL_FLOAT, false, 0, mVertices);

        // 执行绘制
        GLES20.glDrawArrays(GLES20.GL_LINES, 0, 6);
    }

}
