package com.afmoney.commlibrary.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Environment;
import android.widget.ImageView;

import com.afmoney.commlibrary.R;
import com.afmoney.commlibrary.application.BaseApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class XImageUtil {
	
	//request code
	public static final int IMAGE_REQUEST_CODE = 1000;
	
	
	private static Dialog dialog;
	

	
	public static void lodeImage(String url, ImageView mCoverImage) {
		if (null == mCoverImage)return;
		RequestOptions options = new RequestOptions()
				.centerCrop()
				.diskCacheStrategy(DiskCacheStrategy.ALL);
		Glide.with(BaseApplication.mInstance)
				.setDefaultRequestOptions(
						new RequestOptions()
								.frame(1000000)
								.centerCrop()
								.error(R.color.bar_grey)
				)
				.load(url)
				.apply(options)
				.into(mCoverImage);
		
	}
	
	public static void lodeImageDefault(String url, ImageView mCoverImage) {
		RequestOptions options = new RequestOptions()
				.centerCrop()
				.placeholder(R.color.bar_grey)
				.error(R.color.bar_grey)
				.diskCacheStrategy(DiskCacheStrategy.ALL);
		Glide.with(BaseApplication.mInstance)
				.load(url)
				.apply(options)
				.into(mCoverImage);

	}

	/**
	 * Author chenguowu
	 * Time 2018/11/27 15:33
	 * Des 预览图片
	 * */
	public static void previewImg(Activity activity, List<LocalMedia> selectList, int position) {
		PictureSelector.create(activity).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
	}
	
	public static void showPictrueSelector(Activity activity, int maxSelectNum, boolean enableCrop, List<LocalMedia> selectList) {
		PictureSelector.create(activity)
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
				.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
				.withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
				.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
				.circleDimmedLayer(false)// 是否圆形裁剪
				.showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
				.showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
				.openClickSound(true)// 是否开启点击声音
				.selectionMedia(selectList)// 是否传入已选图片
				.minimumCompressSize(200)// 小于100kb的图片不压缩
				.cropCompressQuality(90)// 裁剪压缩质量 默认100
				.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
	}
	public static void showPictrueSelector(Activity activity, int maxSelectNum, boolean enableCrop) {

//		String[] perms = { Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
//		if (!EasyPermissions.hasPermissions(activity,perms)){
//			EasyPermissions.requestPermissions(activity,"",10,perms);
//			return;
//		}
		PictureSelector.create(activity)
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
				.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
				.withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
				.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
				.circleDimmedLayer(false)// 是否圆形裁剪
				.showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
				.showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
				.openClickSound(true)// 是否开启点击声音
				.selectionMedia(null)// 是否传入已选图片
				.minimumCompressSize(200)// 小于100kb的图片不压缩
				.cropCompressQuality(90)// 裁剪压缩质量 默认100
				.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
