package com.afm.commlib;


import com.afm.commlibrary.bases.BaseWebViewActivity;
import com.afm.commlibrary.bases.BaseWithImmersionBarActivity;
import com.afm.commlibrary.customview.XCommonTabLayout;
import com.flyco.tablayout.CommonTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MainActivity extends BaseWithImmersionBarActivity {

    @BindView(R.id.mTabLayout)
    XCommonTabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;


    private String[] mTabEntities = {"全部", "待付款", "待收货", "带归还", "已完成"};
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getCustomLayoutId() {
        return 0;
    }

    @Override
    public void initUI() {

        startActivity(BaseWebViewActivity.class);

    }


    @Override
    public void initData() {

    }
}
