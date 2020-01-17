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

        String url = "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fa1.att.hudong.com%2F60%2F38%2F01200000194369136323385641912.jpg&thumburl=http%3A%2F%2Fimg5.imgtn.bdimg.com%2Fit%2Fu%3D2109617360%2C2782960381%26fm%3D26%26gp%3D0.jpg";


        XImageUtil.lodeImage(url,mIv1);
        XImageUtil.lodeImage(url,mIv2);


    }


    @Override
    public void initData() {

    }
}
