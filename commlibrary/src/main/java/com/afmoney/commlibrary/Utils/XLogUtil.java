package com.afmoney.commlibrary.Utils;

import android.util.Log;

/**
 * Created by chenguowu on 2019/1/14.
 */

public class XLogUtil {

    private static final String TAG = "XLogUtil" ;
    public static boolean logIsOpen = true;

    public static void e(Object msg){
        if (!logIsOpen)return;


        int segmentSize = 3 * 1024;
        long length = msg.toString().length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            Log.e(TAG, msg.toString());
        }else {
            while (msg.toString().length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.toString().substring(0, segmentSize );
                msg = msg.toString().replace(logContent, "");
                Log.e(TAG, logContent);
            }
            Log.e(TAG, msg.toString());// 打印剩余日志
        }
    }
    public static void e(String tag, String msg){
        if (logIsOpen)
        Log.e(tag,msg);
    }

}
