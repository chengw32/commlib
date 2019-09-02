package com.afm.commlibrary.bases;

import android.os.Bundle;


import com.gyf.immersionbar.ImmersionBar;

import androidx.annotation.Nullable;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author chenguowu
 * Time 2019/8/28 8:44
 * Des 实现沉浸式的activity
 * */
public abstract class BaseWithImmersionBarActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)//原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }
}