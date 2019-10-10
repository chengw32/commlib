package com.afm.commlibrary.okgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Window;

import com.afm.commlibrary.Utils.ActivityManagerUtil;
import com.afm.commlibrary.Utils.BaseSPUtil;
import com.afm.commlibrary.Utils.XToastUtil;
import com.afm.commlibrary.Utils.XLogUtil;
import com.afm.commlibrary.application.BaseApplication;
import com.afm.commlibrary.beans.BaseBean;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import okhttp3.Headers;


/**
 * Created by chenguowu on 2019/1/19.
 * 如果 activity 为空 则不创建提升进度条提醒
 */

public abstract class DialogCallBack<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    private Class<T> clazz;
    private boolean isShowMessage;

    public DialogCallBack(Class<T> tClass) {
        super((Class<T>) String.class);
        clazz = tClass;
        initDialog(null);
    }
    public DialogCallBack(Activity activity, Class<T> tClass) {
        super((Class<T>) String.class);
        clazz = tClass;
        initDialog(activity);
    }

    public DialogCallBack(Activity activity, Class<T> tClass, boolean showMessage) {
        super((Class<T>) String.class);
        clazz = tClass;
        this.isShowMessage = showMessage;
        initDialog(activity);
    }

    private void initDialog(Activity activity) {
        if (null == activity || activity.isFinishing()) return;
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
