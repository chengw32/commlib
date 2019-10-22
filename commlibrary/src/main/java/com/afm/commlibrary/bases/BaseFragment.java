package com.afm.commlibrary.bases;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afm.commlibrary.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by chenguowu on 2019/2/23.
 */
public abstract class BaseFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    Unbinder unbinder;
    protected TopBarView mTopBarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = setContentView(inflater, container);
        unbinder = ButterKnife.bind(this,view);
        initUI(view);
        initData(view);
        return view;
    }

    public abstract int getLayoutId();

    protected abstract void initUI(View inflate);

    protected abstract void initData(View inflate);


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {/* Do something */}


    protected View setContentView(LayoutInflater inflater, ViewGroup container) {

        View inflate;
        if (getCustomLayoutId() != 0) {
            //页面都是自己的
            inflate = inflater.inflate(getCustomLayoutId(), container, false);
        } else {
            //页面封装了topbarview

            int layoutId = getLayoutId();

            inflate = inflater.inflate(R.layout.base_content_layout, container, false);
            mTopBarView = inflate.findViewById(R.id.mTopBarView);
            //用于放除了topbarview的容器 这样不用每个页面都添加topbarview
            if (layoutId <= 0) return inflate;
            ViewGroup content = inflate.findViewById(R.id.mContent);
            //除了topbarview 内容部分的layout
            View contentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
            //将内容部分添加到总容器
            content.addView(contentView);
        }

        return inflate;
    }


    /**
     * Author chenguowu
     * Time 2019/9/9 14:20
     * 如果继承的fragment重写了这个方法并返回页面布局，则以这个布局文件为整个页面
     * 没有Topbarview
     */
    protected int getCustomLayoutId() {
        return 0;
    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != unbinder)
            unbinder.unbind();
    }
}
