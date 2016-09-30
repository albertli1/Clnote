package com.monkey.t.clnote.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.monkey.t.clnote.R;
import com.monkey.t.clnote.Widget.COpenGL.COpenGLView;

public class COpenglActivity extends AppCompatActivity {
    private COpenGLView mOpenGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_copengl);
              runOpenGl();
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
