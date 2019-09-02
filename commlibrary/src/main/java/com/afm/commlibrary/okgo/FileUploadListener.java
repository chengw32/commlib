package com.afm.commlibrary.okgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.model.Progress;
import com.lzy.okserver.upload.UploadListener;

/**
 * Created by chenguowu on 2019/7/15.
 */
public class FileUploadListener extends UploadListener {


    private ProgressDialog dialog;

    public FileUploadListener(Object tag) {
        super(tag);
    }
    public FileUploadListener(Object tag,Activity act) {
        super(tag);
        initDialog(act);
    }


    private void initDialog(Activity activity) {
        if (null == activity || activity.isFinishing()) return;
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("文件上传中...");
    }

    @Override
    public void onStart(Progress progress) {

        if (null != dialog)dialog.show();
    }

    @Override
    public void onProgress(Progress progress) {

    }

    @Override
    public void onError(Progress progress) {

    }

    @Override
    public void onFinish(Object o, Progress progress) {

        if (dialog != null && dialog.isShowing())dialog.dismiss();
    }

    @Override
    public void onRemove(Progress progress) {

    }

}
