package com.afm.commlibrary.application;

import android.app.Application;
import android.os.Environment;

import com.afm.commlibrary.Utils.XLogUtil;
import com.lzy.okgo.OkGo;
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
    public static Application mInstance;
    public static int statusBarHeight = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
             statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        OkGo.getInstance().init(this);
        ZXingLibrary.initDisplayOpinion(this);

    }


}
