package com.monkey.t.clnote.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.SparseIntArray;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;



public class CHttpUtils   {

    private Context mContext;
    private String mCharset;
    private int mTimeout;
    private HttpURLConnection mHttpURLConnection;

    private SparseIntArray requestMap;
    private HttpEventListener httpEventListener;

    private Handler httpHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    protected CHttpUtils(Context context) {
        mContext = context;
        requestMap =  new SparseIntArray();
    }

    private void get(String url,int requestcode, int timeout){

    }

    private void post(String url, int requestcode, Map<String,String> params) {

    }

    public interface HttpEventListener {
        public void OnTimeout(int requestcode);
        public void OnError(int requestcode,int errorcode,int errormsg);
        public void OnSuccess(int requestcode,String content);

    }



}
