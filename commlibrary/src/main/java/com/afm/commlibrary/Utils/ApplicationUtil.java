package com.afm.commlibrary.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenguowu on 2019/2/20.
 */
public class ApplicationUtil {
    public static int getVersionCode(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            return 1;
        }
    }
    public static String getVersionName(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            return "1.0";
        }
    }

    public static void upApp(Context context,String packageName){
        Intent intentx = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intentx != null) {
            intentx.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intentx);
        }
    }

    public static String getCurrentAppProcessesName(Context context)
    {
        int pid = android.os.Process.myPid(); // Returns the identifier of this process
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext())
        {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try
            {
                if (info.pid == pid)
                {
                    // 根据进程的信息获取当前进程的名字
                    return info.processName;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }

}
