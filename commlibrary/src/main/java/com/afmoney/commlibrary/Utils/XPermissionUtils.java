package com.afmoney.commlibrary.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.afmoney.commlibrary.R;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by chenguowu on 2019/3/7.
 */
public class XPermissionUtils {
    public static final String[] permArray = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    public static boolean hasPermissions(Context context, String[] permissions) {
        return EasyPermissions.hasPermissions(context, permissions);
    }

    public static void requestPermissions(Activity context, int requestCode, String[] permissions) {
        EasyPermissions.requestPermissions(
                context,
                "需要存/读SD卡的文件 请通过该权限不然功能异常",
                requestCode,
                permissions);
    }

}
