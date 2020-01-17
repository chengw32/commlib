package com.afm.commlibrary.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.IntentFilter;
import android.os.Environment;
import android.widget.ImageView;

import com.afm.commlibrary.R;
import com.afm.commlibrary.application.BaseApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import androidx.annotation.DrawableRes;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class XImageUtil {


    /**
     * Author chenguowu
     * Time 2020/1/13 16:18
     * Des 默认的加载图片
     */
    public static void lodeImage(String url, ImageView mCoverImage) {
        lodeImage(url, mCoverImage, R.drawable.x_default, false);
    }

    public static void lodeImage(String url, ImageView mCoverImage, int errorDrawable) {
        lodeImage(url, mCoverImage, errorDrawable, false);
    }


    public static void lodeImage(String url, ImageView mCoverImage, boolean isCenterCorp) {
        lodeImage(url, mCoverImage, R.drawable.x_default, isCenterCorp);
    }
    public static void lodeImage(String url, ImageView mCoverImage, boolean isCenterCorp, int errorDrawable) {
        lodeImage(url, mCoverImage, errorDrawable, isCenterCorp);
    }

    public static void lodeImage(String url, ImageView mCoverImage, int colorOrDrawable, boolean isCenterCorp) {
        if (null == mCoverImage) return;
        RequestOptions options = new RequestOptions()
                .error(colorOrDrawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        if (isCenterCorp)
            options.centerCrop();


        Glide.with(BaseApplication.mInstance)
                .load(url)
                .apply(options)
                .into(mCoverImage);

    }


    /**
     * Author chenguowu
     * Time 2018/11/27 15:33
     * Des 预览图片
     */
    public static void previewImg(Activity activity, List<LocalMedia> selectList, int position) {
        PictureSelector.create(activity).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
    }


    /**
     * Author chenguowu
     * Time 2020/1/13 17:42
     * Des 自定义裁切框的宽高比
     * ratio 比例
     */
    public static void showPictrueSelector(Activity activity, int maxSelectNum, List<LocalMedia> selectList, int ratioX, int ratioY) {
        showPictrueSelector(activity, maxSelectNum, true, selectList, ratioX, ratioY, 0, 0);
    }

    /**
     * Author chenguowu
     * Time 2020/1/13 19:05
     * Des 单张图片的时候，比如更改图片
     */
    public static void showPictrueSelector(Activity activity, int maxSelectNum, boolean enableCorop) {
        showPictrueSelector(activity, maxSelectNum, enableCorop, null, 0, 0, 0, 0);
    }

    public static void showPictrueSelector(Activity activity, int maxSelectNum, boolean enableCorop, List<LocalMedia> selectList) {
        showPictrueSelector(activity, maxSelectNum, enableCorop, selectList, 0, 0, 0, 0);
    }

    /**
     * Author chenguowu
     * Time 2020/1/13 17:48
     * Des 设置裁切出来的图片框高
     */
    public static void showPictrueSelector(Activity activity, int maxSelectNum, int cropW, int cropH, List<LocalMedia> selectList) {
        showPictrueSelector(activity, maxSelectNum, true, selectList, 0, 0, cropW, cropH);
    }


    public static void showPictrueSelector(Activity activity, int maxSelectNum, boolean enableCrop, List<LocalMedia> selectList, int ratioX, int ratioY, int cropW, int cropH) {
        PictureSelectionModel pictureSelectionModel = PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(enableCrop)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .compressSavePath(getPath())//压缩图片保存地址
                .glideOverride(320, 320)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(true)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(200)// 小于100kb的图片不压缩
                .cropCompressQuality(90);// 裁剪压缩质量 默认100


        //裁切后的图片大小
        if (cropW > 0 && cropH > 0) {
            pictureSelectionModel.cropWH(cropW, cropH);
        }

        //裁切框的宽高比
        if (ratioY > 0 && ratioX > 0) {
            pictureSelectionModel.enableCrop(true);
            pictureSelectionModel.withAspectRatio(ratioX, ratioY);// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
        }

        pictureSelectionModel.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    /**
     * 自定义压缩存储地址
     *
     * @return
     */
    private static String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }
}
