
package com.homedesign.common;

import java.lang.Math;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * 图形生成器
 * 
 * @author liguangzheng
 */
public class ESShapes {

    private FloatBuffer mVertices; // 顶点缓冲
    private FloatBuffer mNormals; // 法向量缓冲
    private FloatBuffer mTexCoords; // 纹理缓冲
    private FloatBuffer mColors;// 颜色缓冲
    private ShortBuffer mIndices; // 索引缓冲
    private int mNumIndices; // 索引数量
    private int mNumVertices;// 顶点数量

    public FloatBuffer getVertices() {
        return mVertices;
    }

    public FloatBuffer getNormals() {
        return mNormals;
    }

    public FloatBuffer getTexCoords() {
        return mTexCoords;
    }

    public FloatBuffer getColors() {
        return mColors;
    }

    public ShortBuffer getIndices() {
        return mIndices;
    }

    public int getNumIndices() {
        return mNumIndices;
    }

    public int getNumVertices() {
        return mNumVertices;
    }

    /**
     * 球形生成器
     * 
     * @param numSlices
     * @param radius
     * @return
     */
    public int genSphere(int numSlices, float radius) {
        int i;
        int j;
        int numParallels = numSlices;
        int numVertices = (numParallels + 1) * (numSlices + 1);
        int numIndices = numParallels * numSlices * 6;
        float angleStep = ((2.0f * (float) Math.PI) / numSlices);

        // Allocate memory for buffers
        mVertices = ByteBuffer.allocateDirect(numVertices * 3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mNormals = ByteBuffer.allocateDirect(numVertices * 3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTexCoords = ByteBuffer.allocateDirect(numVertices * 2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mIndices = ByteBuffer.allocateDirect(numIndices * 2).order(ByteOrder.nativeOrder()).asShortBuffer();

        for (i = 0; i < numParallels + 1; i++) {
            for (j = 0; j < numSlices + 1; j++) {
                int vertex = (i * (numSlices + 1) + j) * 3;

                mVertices.put(vertex + 0,
                        (float) (radius * Math.sin(angleStep * (float) i) * Math.sin(angleStep * (float) j)));

                mVertices.put(vertex + 1, (float) (radius * Math.cos(angleStep * (float) i)));
                mVertices.put(vertex + 2,
                        (float) (radius * Math.sin(angleStep * (float) i) * Math.cos(angleStep * (float) j)));

                mNormals.put(vertex + 0, mVertices.get(vertex + 0) / radius);
                mNormals.put(vertex + 1, mVertices.get(vertex + 1) / radius);
                mNormals.put(vertex + 2, mVertices.get(vertex + 2) / radius);

                int texIndex = (i * (numSlices + 1) + j) * 2;
                mTexCoords.put(texIndex + 0, (float) j / (float) numSlices);
                mTexCoords.put(texIndex + 1, (1.0f - (float) i) / (float) (numParallels - 1));
            }
        }

        int index = 0;

        for (i = 0; i < numParallels; i++) {
            for (j = 0; j < numSlices; j++) {
                mIndices.put(index++, (short) (i * (numSlices + 1) + j));
                mIndices.put(index++, (short) ((i + 1) * (numSlices + 1) + j));
                mIndices.put(index++, (short) ((i + 1) * (numSlices + 1) + (j + 1)));

                mIndices.put(index++, (short) (i * (numSlices + 1) + j));
                mIndices.put(index++, (short) ((i + 1) * (numSlices + 1) + (j + 1)));
                mIndices.put(index++, (short) (i * (numSlices + 1) + (j + 1)));

            }
        }

        mNumIndices = numIndices;
        mNumVertices = numVertices;
        return numIndices;
    }

    /**
     * 正方体生成器
     * 
     * @param scale
     * @return
     */
    public int genCube(float scale) {
        int i;
        int numVertices = 24;
        int numIndices = 36;

        float[] cubeVerts = {
                -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f
        };

        float[] cubeNormals = {
                0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f
        };

        float[] cubeTex = {
                0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f
        };

        float[] cubeColor = {
                1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f
        };

        // Allocate memory for buffers
        mVertices = ByteBuffer.allocateDirect(numVertices * 3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mNormals = ByteBuffer.allocateDirect(numVertices * 3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTexCoords = ByteBuffer.allocateDirect(numVertices * 2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mColors = ByteBuffer.allocateDirect(numVertices * 4 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mIndices = ByteBuffer.allocateDirect(numIndices * 2).order(ByteOrder.nativeOrder()).asShortBuffer();

        mVertices.put(cubeVerts).position(0);

        for (i = 0; i < numVertices * 3; i++) {
            mVertices.put(i, mVertices.get(i) * scale);
        }

        mNormals.put(cubeNormals).position(0);
        mTexCoords.put(cubeTex).position(0);
        mColors.put(cubeColor).position(0);

        short[] cubeIndices = {
                0, 2, 1, 0, 3, 2, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 15, 14, 12, 14, 13, 16, 17, 18, 16, 18,
                19, 20, 23, 22, 20, 22, 21
        };

        mIndices.put(cubeIndices).position(0);
        mNumIndices = numIndices;
        mNumVertices = numVertices;
        return numIndices;
    }

}
