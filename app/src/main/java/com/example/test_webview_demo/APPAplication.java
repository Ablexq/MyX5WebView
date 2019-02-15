package com.example.test_webview_demo;

import android.app.Application;
import android.util.Log;

import com.example.test_webview_demo.service.InitializeService;
import com.tencent.smtt.sdk.QbSdk;

public class APPAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //方式一：直接初始化
//        initQQX5();

        //方式二：在intentService的子线程中初始化
        InitializeService.start(this);

        //方式三：在thread的子线程中初始化
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                initQQX5();
//            }
//        }).start();
    }

    private void initQQX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

}
