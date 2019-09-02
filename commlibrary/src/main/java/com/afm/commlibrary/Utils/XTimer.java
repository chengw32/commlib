package com.afm.commlibrary.Utils;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chenguowu on 2019/6/6.
 */
public class XTimer {

    private static Handler handler;
    private static Runnable mRunnable;
    private static int maxSeconds = 60;
    private static TextView currentView;

    public static boolean startTimer(TextView v) {

        currentView = v;
        if (null == handler)
            handler = new Handler(Looper.getMainLooper());

        if (null == mRunnable)
            mRunnable = new Runnable() {
                @Override
                public void run() {

                    maxSeconds--;
                    if (maxSeconds <= 0) {
                        //倒计时完了
                        maxSeconds = 60;
                        if (null != currentView) {
                            currentView.requestFocus();
                            currentView.setEnabled(true);
                            currentView.setText("获取验证码");
                        }
                    } else {

                        if (null != currentView) {
                            currentView.setEnabled(false);
                            currentView.setText(maxSeconds + "s 后重新获取");
                            handler.postDelayed(this, 1000);
                        }
                    }
                }
            };

        if (maxSeconds == 60)
        handler.post(mRunnable);

        return maxSeconds == 60; //返回的数据表示是否 计时器不在运行
    }

}
