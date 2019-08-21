package com.afmoney.commlibrary.bannerImageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.afmoney.commlibrary.Utils.XImageUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by chenguowu on 2019/6/5.
 */
public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        XImageUtil.lodeImage(path.toString(),imageView);
    }



}
