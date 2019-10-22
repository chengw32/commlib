package com.afm.commlibrary.bases;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afm.commlibrary.R;
import com.afm.commlibrary.Utils.ActivityManagerUtil;
import com.afm.commlibrary.Utils.XLogUtil;
import com.afm.commlibrary.Utils.XUtils;
import com.afm.commlibrary.application.BaseApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by chenguowu on 2019/2/20.
 */
public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    protected Activity xContext;
    protected TopBarView mTopBarView;
    protected View mStatusBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xContext = this;
        ActivityManagerUtil.getInstance().addActivity(this);
        setContentView();
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initUI();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(com.afm.commlibrary.bases.BaseEvent event) {/* Do something */}

    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    public abstract int getLayoutId();

    public abstract void initUI();

    public abstract void initData();


    protected void setContentView() {
        setContentView(R.layout.base_content_layout);
        mStatusBar = findViewById(R.id.mStatusBar);
        mTopBarView = findViewById(R.id.mTopBarView);

        ViewGroup viewById = findViewById(R.id.mContent);
        View inflate;
        int customLayoutId = getCustomLayoutId();
        if (customLayoutId != 0) {
            //customLayoutId 不为零说明 当前页面不需要通用的 topbarview。
            mTopBarView.setVisibility(View.GONE);
            inflate = LayoutInflater.from(this).inflate(getCustomLayoutId(), null);
        } else {
            //customLayoutId为0 说明需要用共同封装的topbarview
            inflate = LayoutInflater.from(this).inflate(getLayoutId(), null);
        }

        //如果布局设置了颜色 则topbar跟statusBar也设置颜色
        Drawable background = inflate.getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            XLogUtil.e(color);
            if (color != 0) {
                mTopBarView.setBackgroundColor(color);
                mStatusBar.setBackgroundColor(color);
            }
        }

        viewById.addView(inflate);
    }


    /**
     * Author chenguowu
     * Time 2019/9/9 14:20
     * 如果继承的activity重写了这个方法并返回页面布局，则以这个布局文件为页面
     */
    protected int getCustomLayoutId() {
        return 0;
    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 打开页面
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onDestroy() {
        ActivityManagerUtil.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }




    /**
     * 当用户离开edittext区域后自动取消焦点
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            if (v != null && v instanceof EditText) {
                if (isShouldHideKeyboard(v, ev)) {
                    XUtils.hideImm(v);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

}
