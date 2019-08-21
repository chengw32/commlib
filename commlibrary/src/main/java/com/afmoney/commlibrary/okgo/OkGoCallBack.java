package com.afmoney.commlibrary.okgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Window;

import com.afmoney.commlibrary.Utils.ActivityManagerUtil;
import com.afmoney.commlibrary.Utils.BaseSPUtil;
import com.afmoney.commlibrary.Utils.XToastUtil;
import com.afmoney.commlibrary.Utils.XLogUtil;
import com.afmoney.commlibrary.application.BaseApplication;
import com.afmoney.commlibrary.beans.BaseBean;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import okhttp3.Headers;


/**
 * Created by chenguowu on 2019/1/19.
 * 如果 activity 为空 则不创建提升进度条提醒
 */

public abstract class OkGoCallBack<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    private Class<T> clazz;
    private boolean isShowMessage;

    public OkGoCallBack( Class<T> tClass) {
        super((Class<T>) String.class);
        clazz = tClass;
        initDialog(null);
    }
    public OkGoCallBack(Activity activity, Class<T> tClass) {
        super((Class<T>) String.class);
        clazz = tClass;
        initDialog(activity);
    }

    public OkGoCallBack(Activity activity, Class<T> tClass, boolean showMessage) {
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

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        Headers headers = response.headers();
        XLogUtil.e("respons headers  " + headers.toString());


        T t = response.body();
        XLogUtil.e("请求结果  " + t.toString());

        BaseBean baseBean = null;
        try {
            baseBean = JSON.parseObject(t.toString(), BaseBean.class);
            if ("2004".equals(baseBean.getCode()) || "2001".equals(baseBean.getCode())) {

                //token异常{ 2001：token 为空  。2004：token失效}
                BaseSPUtil.setLogin(false);
                BaseSPUtil.setToken("");
                Class<?> aClass = BaseApplication.context.getClassLoader().loadClass("com.rongta.cloudprint.ui.login.LoginAccountActivity");
                if (null != aClass) {
                    ActivityManagerUtil.getInstance().closeAll();
                    Intent intent = new Intent(BaseApplication.context, aClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    BaseApplication.context.startActivity(intent);
                }else {
                    XLogUtil.e("OkGoCallBack loadClass LoginAccountActivity=null");
                }

            }


        } catch (Exception e) {
            XLogUtil.e("JSON.parseObject Exception: " + e.getMessage());
            error(response);
        }
        if (null == baseBean) {
            XLogUtil.e("OkGoCallBack error: null == baseBean");
            error(response);
            return;

        } else {
            if (baseBean.isSuccess()) {
                if (isShowMessage) {
                    XToastUtil.show(baseBean.getMessage());
                }
                success(JSON.parseObject(t.toString(), clazz));
            } else {
                XToastUtil.show(baseBean.getMessage());
                XLogUtil.e("error: " + baseBean.getMessage());
                error(response);
            }
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);

        error(response);
    }

    protected abstract void success(T body);

    public void error(com.lzy.okgo.model.Response<T> response) {
        XLogUtil.e("error:" + response.body());
    }

}
