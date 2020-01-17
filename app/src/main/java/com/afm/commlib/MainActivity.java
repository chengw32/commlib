package com.afm.commlib;


import android.widget.ImageView;

import com.afm.commlibrary.Utils.XImageUtil;
import com.afm.commlibrary.application.BaseApplication;
import com.afm.commlibrary.bases.BaseWebViewActivity;
import com.afm.commlibrary.bases.BaseWithImmersionBarActivity;
import com.afm.commlibrary.customview.XCommonTabLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.CommonTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MainActivity extends BaseWithImmersionBarActivity {


    @BindView(R.id.mIv1)
    ImageView mIv1;
    @BindView(R.id.mIv2)
    ImageView mIv2;
    @BindView(R.id.mIv3)
    ImageView mIv3;
    @BindView(R.id.mIv4)
    ImageView mIv4;


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

        String url = "http://image.xxomh.com/7fc39bbaa1274d168fb3f471d5ebbbb8.jpg-s160";


        XImageUtil.lodeImage(url,mIv1);
        XImageUtil.lodeImage(url,mIv2,true);


    }


    @Override
    public void initData() {

    }
}
