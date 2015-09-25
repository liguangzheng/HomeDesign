
package com.homedesign.plug;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.homedesign.base.BasePlug;
import com.homedesign.base.BaseRenderer;
import com.homedesign.base.Camera;
import com.homedesign.base.Projection;
import com.homedesign.common.ESShader;
import com.homedesign.common.ESShapes;

/**
 * 部件：正方体
 * 
 * @author liguangzheng
 */
public class Cube extends BasePlug {

    private ESShapes mCube;
    // VertexBufferObject Ids
    private int[] mVBOIds = new int[3];

    public Cube(Context context) {
        super(context);
        mCube = new ESShapes();
        mCube.genCube(1);
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
        // Matrix.translateM(getMVPMatrix().getArray(), 0, 0.5f, 0, 0);// 执行平移
        // Matrix.rotateM(getMVPMatrix().getArray(), 0, 45, 0, 0, 1);// 执行旋转

        // 获取uniform "matViewProjection"的入口点
        int attributeMatViewProjection = GLES20.glGetUniformLocation(getProgramObject(), "matViewProjection");
        float[] temp1 = new float[16];
        Matrix.multiplyMM(temp1, 0, camera.getMatrix().getArray(), 0, getMVPMatrix().getArray(), 0);
        float[] temp2 = new float[16];
        Matrix.multiplyMM(temp2, 0, camera.getMatrix().getArray(), 0, renderer.getMatrix().getArray(), 0);
        float[] temp3 = new float[16];
        Matrix.multiplyMM(temp3, 0, projection.getMatrix().getArray(), 0, temp2, 0);
        GLES20.glUniformMatrix4fv(attributeMatViewProjection, 1, false, temp3, 0);

        GLES20.glGenBuffers(3, mVBOIds, 0);
        // 顶点
        mCube.getVertices().position(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBOIds[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mCube.getNumVertices() * 3 * 4, mCube.getVertices(),
                GLES20.GL_STATIC_DRAW);
        // 颜色
        mCube.getColors().position(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBOIds[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mCube.getNumVertices() * 4 * 4, mCube.getColors(),
                GLES20.GL_STATIC_DRAW);
        // 索引
        mCube.getIndices().position(0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mVBOIds[2]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, mCube.getNumIndices() * 2, mCube.getIndices(),
                GLES20.GL_STATIC_DRAW);
        // 顶点
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBOIds[0]);
        int attributePosition = GLES20.glGetAttribLocation(getProgramObject(), "a_position");
        GLES20.glEnableVertexAttribArray(attributePosition);
        GLES20.glVertexAttribPointer(attributePosition, 3, GLES20.GL_FLOAT, false, 3 * 4, 0);
        // 颜色
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVBOIds[1]);
        int attributeColor = GLES20.glGetAttribLocation(getProgramObject(), "a_color");
        GLES20.glEnableVertexAttribArray(attributeColor);
        GLES20.glVertexAttribPointer(attributeColor, 4, GLES20.GL_FLOAT, false, 4 * 4, 0);
        // 索引
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mVBOIds[2]);

        // 索引法执行绘制
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mCube.getNumIndices(), GLES20.GL_UNSIGNED_SHORT, 0);

        // 尾处理
        GLES20.glDisableVertexAttribArray(attributePosition);
        GLES20.glDisableVertexAttribArray(attributeColor);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void translate(float tx, float ty, float tz) {
        super.translate(tx, ty, tz);
    }
}
