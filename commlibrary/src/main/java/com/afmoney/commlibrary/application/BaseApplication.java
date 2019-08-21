package com.afmoney.commlibrary.application;

import android.app.Application;
import android.os.Environment;

import com.afmoney.commlibrary.Utils.ApplicationUtil;
import com.lzy.okgo.OkGo;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;

/**
 * Created by chenguowu on 2019/2/14.
 */
public class BaseApplication extends Application {

    public static String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"apkFiles";
    public static String APK_PATH = FILE_PATH+"/apk/";
    /**
     * The context.
     */
    public static Application context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkGo.getInstance().init(this);
        ZXingLibrary.initDisplayOpinion(this);

    }


}
