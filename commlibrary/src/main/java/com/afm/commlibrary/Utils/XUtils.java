package com.afm.commlibrary.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.afm.commlibrary.R;
import com.afm.commlibrary.application.BaseApplication;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenguowu on 2019/2/20.
 */
public class XUtils {

    /**
     * 判断字符串是否为URL
     *
     * @param urls 需要判断的String类型url
     * @return true:是URL；false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
//        boolean isurl = false;
//        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
//                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式
//
//        Pattern pat = Pattern.compile(regex.trim());//对比
//        Matcher mat = pat.matcher(urls.trim());
//        isurl = mat.matches();//判断是否匹配
//        if (isurl) {
//            isurl = true;
//        }
//        return isurl;
        return urls.startsWith("http");
    }


    /**
     * 判断网络情况
     *
     * @param context 上下文
     * @return false 表示没有网络 true 表示有网络
     */
    public static boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 如果没有网络，则弹出网络设置对话框
    public static boolean checkNetwork(final Activity activity) {
        boolean networkAvalible = isNetworkAvalible(activity);
        if (!networkAvalible) {
            TextView msg = new TextView(activity);
            msg.setText("　\n　当前没有可以使用的网络，请设置网络！");
            new AlertDialog.Builder(activity)
                    .setTitle("网络状态提示")
                    .setView(msg)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    // 跳转到设置界面
                                    activity.startActivityForResult(new Intent(
                                                    Settings.ACTION_WIRELESS_SETTINGS),
                                            0);
                                }
                            }).create().show();
        }
        return networkAvalible;
    }

    public static Integer[] getWidthAndHeight(Window window) {
        if (window == null) {
            return null;
        }
        Integer[] integer = new Integer[2];
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        integer[0] = dm.widthPixels;
        integer[1] = dm.heightPixels;
        return integer;
    }


    public static int getVirtualBarHeight(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - display.getHeight();
        } catch (Exception e) {
        }
        return vh;
    }

    public static int getTopStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = BaseApplication.mInstance.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = BaseApplication.mInstance.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    public static void hideImm(View v) {
        InputMethodManager imm = (InputMethodManager) BaseApplication.mInstance.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }

    public static void showImm(View v) {
        InputMethodManager imm = (InputMethodManager) BaseApplication.mInstance
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.RESULT_SHOWN);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = BaseApplication.mInstance.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale);
    }

    public static int sp2px(float sp) {
        final float fontScale = BaseApplication.mInstance.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = BaseApplication.mInstance.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(float dipValue) {
        final float scale = BaseApplication.mInstance.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static int getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            return 1;
        }
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            return "1.0";
        }
    }


    public static void upApp(Context context, String packageName) {
        Intent intentx = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intentx != null) {
            intentx.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intentx);
        }
    }

    static Handler timerHandler;
    static Runnable timerRunnable;
    //是否在两秒内
    static boolean isInTime;

    public static void exitApp(Activity activity) {
        if (isInTime) {
            isInTime = false;
            timerHandler = null;
            timerRunnable = null;
            System.exit(0);
            activity.finish();
        } else {
            XToastUtil.show("再点一次退出程序");
            isInTime = true;
        }

        if (null == timerHandler) timerHandler = new Handler();
        if (null == timerRunnable) timerRunnable = new Runnable() {
            @Override
            public void run() {
                isInTime = false;
            }
        };
        timerHandler.postDelayed(timerRunnable, 2000);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        if (null != charSequence) {
            return isEmpty(charSequence.toString());
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String str) {

        if (null == str) return true;

        String strTrim = str.trim();

        return strTrim.length() == 0 || "null".equals(strTrim) || "NULL".equals(strTrim);

    }
}
