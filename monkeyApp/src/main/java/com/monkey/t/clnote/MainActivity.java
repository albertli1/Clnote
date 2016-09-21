package com.monkey.t.clnote;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.monkey.t.clnote.Widget.COpenGL.COpenGLView;

import com.monkey.t.clnote.R;

public class MainActivity extends Activity {

    private COpenGLView mOpenGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        runOpenGl();
    }

    private void runOpenGl() {
        //去标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mOpenGLView = new COpenGLView(this);
        setContentView(mOpenGLView);
    }


}
