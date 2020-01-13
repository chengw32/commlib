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

        String url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Ftimgsa.baidu.com%2Ftimg%3Fimage%26quality%3D80%26size%3Db9999_10000%26sec%3D1578915154669%26di%3Dda229448d6bf288a6c27a0376781f35a%26imgtype%3D0%26src%3Dhttp%253A%252F%252Fimg4.18183.com%252Fuploads%252Fallimg%252F170316%252F6-1F316143217.jpg%2540q_80&thumburl=https%3A%2F%2Fss0.bdstatic.com%2F70cFuHSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D382112349%2C531774319%26fm%3D26%26gp%3D0.jpg";


        XImageUtil.lodeImage(url,mIv1);
        XImageUtil.lodeImage(url,mIv2);


    }


    @Override
    public void initData() {

    }
}
