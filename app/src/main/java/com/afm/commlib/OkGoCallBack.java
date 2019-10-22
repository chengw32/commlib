package com.afm.commlib;

import android.app.Activity;

import com.afm.commlibrary.Utils.XLogUtil;
import com.afm.commlibrary.okgo.DialogCallBack;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Headers;


/**
 * Created by chenguowu on 2019/1/19.
 * 如果 activity 为空 则不创建提升进度条提醒
 */

public abstract class OkGoCallBack<T> extends DialogCallBack {
    @Override
    public void onSuccess(Response<String> response) {
        Headers headers = response.headers();
        XLogUtil.e("respons headers  " + headers.toString());

        XLogUtil.e("请求结果  " + response.body());

        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];

        success(JSON.parseObject(response.body(),actualTypeArgument));
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);

    }

    //    @Override
//    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
//        Headers headers = response.headers();
//        XLogUtil.e("respons headers  " + headers.toString());
//
//
//        T t = response.body();
//        XLogUtil.e("请求结果  " + t.toString());
//
//        success(t);
//        BaseBean baseBean = null;
//        try {
//            baseBean = JSON.parseObject(t.toString(), BaseBean.class);
//            if ("2004".equals(baseBean.getCode()) || "2001".equals(baseBean.getCode())) {
//
//                //token异常{ 2001：token 为空  。2004：token失效}
//                Class<?> aClass = BaseApplication.mInstance.getClassLoader().loadClass("com.rongta.cloudprint.ui.login.LoginAccountActivity");
//                if (null != aClass) {
//                    ActivityManagerUtil.getInstance().closeAll();
//                    Intent intent = new Intent(BaseApplication.mInstance, aClass);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    BaseApplication.mInstance.startActivity(intent);
//                }else {
//                    XLogUtil.e("DialogCallBack loadClass LoginAccountActivity=null");
//                }
//
//            }
//
//
//        } catch (Exception e) {
//            XLogUtil.e("JSON.parseObject Exception: " + e.getMessage());
//            error(response);
//        }
//        if (null == baseBean) {
//            XLogUtil.e("DialogCallBack error: null == baseBean");
//            error(response);
//            return;
//
//        } else {
//            if (baseBean.isSuccess()) {
//                if (isShowMessage) {
//                    XToastUtil.show(baseBean.getMessage());
//                }
//                success((T) JSON.parse(t.toString()));
//            } else {
//                XToastUtil.show(baseBean.getMessage());
//                XLogUtil.e("error: " + baseBean.getMessage());
//                error(response);
//            }
//        }
//    }

//    @Override
//    public void onError(Response<T> response) {
//        super.onError(response);
//
//        error(response);
//    }

    protected abstract void success(T body);

    public void error(com.lzy.okgo.model.Response<T> response) {
        XLogUtil.e("error:" + response.body());
    }

}
