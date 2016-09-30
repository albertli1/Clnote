package com.monkey.t.clnote.Widget.COpenGL;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;


import javax.microedition.khronos.egl.EGLConfig;

import javax.microedition.khronos.opengles.GL10;


public class COpenGLRenderer implements GLSurfaceView.Renderer {

    private float cr, cg, cb;
    private FloatBuffer mTriangleBuffer;
    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 1f
    };

    private FloatBuffer mQuateBuffer;
    private float[] mQuateArray = {
            1f, 1f, 0f,
            -1f, 1f, 0f,
            1f, -1f, 0f,
            -1f, -1f, 0f
    };

    private float[] mQuadsArray = {
            1f, 1f, 0f,                           //右上
            -1f, 1f, 0f,                          //左上
            -1f, -1f, 0f,                         //左下
            1f, -1f, 0f                           //右下
    };
    //从这里可以看出，我们按照逆时针的方向画图
    private FloatBuffer mQuadsBuffer;

//    private FloatBuffer mTriangleBuffer;
//    private float[] mTriangleArray = {
//            0f, 1f, 0f,
//            -1f, -1f, 0f,
//            1f, -1f, 1f
//    };


    float rotateTri, rotateQuad;
    int one = 0x10000;

    //三角形的一个顶点
    private IntBuffer triggerBuffer = IntBuffer.wrap(new int[]{
            0, one, 0,     //上顶点
            -one, -one, 0,    //左顶点
            one, -one, 0    //右下点
    });

    //正方形的四个顶点
    private IntBuffer quateBuffer = IntBuffer.wrap(new int[]{
            one, one, 0,
            -one, -one, 0,
            one, -one, 0,
            -one, -one, 0
    });


    private IntBuffer colorBuffer = IntBuffer.wrap(new int[]{
            one, 0, 0, one,
            0, one, 0, one,
            0, 0, one, one
    });


    @Override
    public void onDrawFrame(GL10 gl) {


//            gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
//            gl.glMatrixMode(GL10.GL_PROJECTION);
//            gl.glLoadIdentity();
////            gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
////            gl.glTranslatef(-1.5f, 0.0f, -6.0f);
//
//            gl.glClearColor(cr, cg, cb, 0.0f);
////            gl.glFrustumf(-400, 400, -240, 240, 0.3f, 100);
//
//            gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
//            //三角形的颜色为红色，透明度为不透明
//            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
//
//            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//        otherDrawfram1(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置输出屏幕大小
        gl.glViewport(0, 0, width, height);
        setAxis(gl, width, height);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // TODO Auto-generated method stub


//        mTriangleBuffer = CBufferUtil.floatToBuffer(mTriangleArray);
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glShadeModel(GL10.GL_SMOOTH);
//
//
////            gl.glClearColor(0f, 0f, 0f, 0f);
////            gl.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
//
//        gl.glClearDepthf(1.0f);
//
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//
//        gl.glDepthFunc(GL10.GL_LEQUAL);
//
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
////            setAxis(gl);
//        onSurefaceinit(gl);

        otherinit(gl);
        otherDrawfram1(gl);
    }

    private void onSurefaceinit(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
//            gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
//            gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        gl.glClearColor(cr, cg, cb, 0.0f);
//            gl.glFrustumf(-400, 400, -240, 240, 0.3f, 100);

        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        //三角形的颜色为红色，透明度为不透明
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
    }

    private void buffinit() {
        mTriangleBuffer = CBufferUtil.floatToBuffer(mTriangleArray);
        mQuateBuffer = CBufferUtil.floatToBuffer(mQuateArray); //012 123
        mQuadsBuffer = CBufferUtil.floatToBuffer(mQuadsArray); //012 023
//        mTriangleBuffer = CBufferUtil.floatToBuffer(mTriangleArray);
    }

    private void otherinit(GL10 gl) {

        buffinit();
        // 启用阴影平滑
        gl.glShadeModel(GL10.GL_SMOOTH);

        // 黑色背景
        gl.glClearColor(0, 0, 0, 0);

        // 设置深度缓存
        gl.glClearDepthf(1.0f);
        // 启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 所作深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);

        // 告诉系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

    }

    private void otherDrawfram(GL10 gl) {
        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();


        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        //设置旋转
        gl.glRotatef(rotateTri, 0.0f, 1.0f, 0.0f);

        //设置定点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //设置颜色数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
        // 设置三角形顶点
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);
        //绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        //绘制三角形结束
        gl.glFinish();

        /***********************/
        /* 渲染正方形 */
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();

        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(1.5f, 0.0f, -6.0f);

        // 设置当前色为蓝色
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        //设置旋转
        gl.glRotatef(rotateQuad, 1.0f, 0.0f, 0.0f);

        //设置和绘制正方形
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, quateBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //绘制正方形结束
        gl.glFinish();

        //取消顶点数组
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //改变旋转的角度
        rotateTri += 0.5f;
        rotateQuad -= 0.5f;
    }

    private void otherDrawfram1(GL10 gl) {
        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();
        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        //设置旋转
        gl.glRotatef(rotateTri, 0.0f, 1.0f, 0.0f);

        //设置定点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //设置颜色数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

//        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mTriangleBuffer);
        // 设置三角形顶点
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
        //绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        //绘制三角形结束
        gl.glFinish();


        /***********************/
        /* 渲染正方形 */
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();

        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(1.5f, 1.5f, -6.0f);

        // 设置当前色为蓝色
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        //设置旋转
        gl.glRotatef(rotateQuad, 1.0f, 0.0f, 0.0f);

        //设置和绘制正方形
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mQuateBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        //绘制正方形结束
        gl.glFinish();

        //取消顶点数组
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //改变旋转的角度
        rotateTri += 0.5f;
        rotateQuad -= 0.5f;
    }

    private void otherDrawfram2(GL10 gl) {
        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();


        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        //设置旋转
        gl.glRotatef(rotateTri, 0.0f, 1.0f, 0.0f);

        //设置定点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //设置颜色数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
        // 设置三角形顶点
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);
        //绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        //绘制三角形结束
        gl.glFinish();

        /***********************/
        /* 渲染正方形 */
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();

        // 左移 1.5 单位，并移入屏幕 6.0
        gl.glTranslatef(1.5f, 0.0f, -6.0f);

        // 设置当前色为蓝色
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        //设置旋转
        gl.glRotatef(rotateQuad, 1.0f, 0.0f, 0.0f);

        //设置和绘制正方形
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, quateBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //绘制正方形结束
        gl.glFinish();

        //取消顶点数组
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //改变旋转的角度
        rotateTri += 0.5f;
        rotateQuad -= 0.5f;
    }


    private void setAxis(GL10 gl, int width, int height) {
//        float ratio = (float) width / height;
//        gl.glMatrixMode(GL10.GL_PROJECTION);
//        gl.glLoadIdentity();
//        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        float ratio = (float) width / height;
        //设置OpenGL场景的大小
//        gl.glViewport(0, 0, width, height);
        //设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置投影矩阵
        gl.glLoadIdentity();
        // 设置视口的大小
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        // 选择模型观察矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 重置模型观察矩阵
        gl.glLoadIdentity();
    }

    public void setColor(float r, float g, float b) {
        cr = r;
        cg = g;
        cb = b;
    }
}
