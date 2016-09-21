package com.monkey.t.clnote.Widget.COpenGL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class COpenGLView extends GLSurfaceView{

    private COpenGLRenderer mRenderer;

    public COpenGLView(Context context) {

        super(context);

        mRenderer = new COpenGLRenderer();

        setRenderer(mRenderer);


        // TODO Auto-generated constructor stub

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        queueEvent(new Runnable(){



            @Override

            public void run() {

                mRenderer.setColor(event.getX()/getWidth(), event.getY()/getHeight(), 1.0f);

            }



        });
        return super.onTouchEvent(event);
    }
}
