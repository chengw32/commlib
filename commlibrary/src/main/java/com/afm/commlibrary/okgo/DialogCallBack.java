package com.afm.commlibrary.okgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;


/**
 * Created by chenguowu on 2019/1/19.
 * 如果 activity 为空 则不创建提升进度条提醒
 */

public abstract class DialogCallBack extends StringCallback {

    protected ProgressDialog dialog;
    protected Activity mActivity;
    protected boolean isShowMessage;

    public DialogCallBack() {
        initDialog(null);
    }
    public DialogCallBack(Activity activity) {
        initDialog(activity);
    }

    public DialogCallBack(Activity activity,  boolean showMessage) {
        this.isShowMessage = showMessage;
        mActivity = activity;
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
    public void onStart(Request<String, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing() && mActivity != null && !mActivity.isFinishing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing() && mActivity != null && !mActivity.isFinishing()) {
            dialog.dismiss();
        }
    }
}
