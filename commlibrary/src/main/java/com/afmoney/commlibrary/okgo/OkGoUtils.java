package com.afmoney.commlibrary.okgo;

import android.Manifest;
import android.app.Activity;
import android.text.TextUtils;

import com.afmoney.commlibrary.Utils.BaseSPUtil;
import com.afmoney.commlibrary.Utils.Constants;
import com.afmoney.commlibrary.Utils.XPermissionUtils;
import com.afmoney.commlibrary.Utils.XToastUtil;
import com.afmoney.commlibrary.Utils.XUtils;
import com.afmoney.commlibrary.application.BaseApplication;
import com.afmoney.commlibrary.model.BaseParams;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.OkUpload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.upload.UploadListener;
import com.lzy.okserver.upload.UploadTask;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenguowu on 2019/1/15.
 * <p>
 * <p>
 * 如果要添加 头部在 CallBack的 onStart 方法里添加
 * 如果要添加 头部在 CallBack的 onStart 方法里添加
 * 如果要添加 头部在 CallBack的 onStart 方法里添加
 * 如果要添加 头部在 CallBack的 onStart 方法里添加
 */

public class OkGoUtils {
    private static final int REQUEST_PERMISSION_STORAGE = 0x01;


    public static <T> void post(Activity act, BaseParams params, OkGoCallBack<T> jsonCallback) {


        if (!XUtils.checkNetwork(act)) return;

        OkGo.<T>post(getApiName(params))//
                .upJson(JSON.toJSONString(params))//已json的形式提交回服务器
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .tag(act)// 页面销毁时取消请求是根据这个来判断的
//                .params(parseDataParams(params))
//                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(jsonCallback);
    }

    public static <T> void put(Activity act, BaseParams params, OkGoCallBack<T> jsonCallback) {

        if (!XUtils.checkNetwork(act)) return;

        OkGo.<T>put(getApiName(params))//
                .upJson(JSON.parseObject(JSON.toJSONString(params)).toString())//已json的形式提交回服务器
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .tag(act)// 页面销毁时取消请求是根据这个来判断的
//                .params(parseDataParams(params))
//                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(jsonCallback);
    }
    public static <T> void del(Activity act, BaseParams params, OkGoCallBack<T> jsonCallback) {

        if (!XUtils.checkNetwork(act)) return;

        OkGo.<T>delete(getApiName(params))//
                .upJson(JSON.parseObject(JSON.toJSONString(params)).toString())//已json的形式提交回服务器
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .tag(act)// 页面销毁时取消请求是根据这个来判断的
//                .params(parseDataParams(params))
//                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(jsonCallback);
    }

    public static <T> void get(Activity act, BaseParams params, OkGoCallBack<T> jsonCallback) {
        if (!XUtils.checkNetwork(act)) return;
        OkGo.<T>get(getApiName(params))//
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .tag(act)// 页面销毁时取消请求是根据这个来判断的
                .params(parseDataParams(params))
//                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(jsonCallback);
    }

    //url 已经拼接好参数了
    public static <T> void get(Activity act, String url, OkGoCallBack<T> jsonCallback) {
        if (!XUtils.checkNetwork(act)) return;
        OkGo.<T>get(url)//
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .tag(act)// 页面销毁时取消请求是根据这个来判断的
//                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(jsonCallback);
    }

    public static void downFile(Activity activity, String url, DownloadListener downloadListener) {
        //设置下载路径
        if (!XUtils.checkNetwork(activity)) return;
        checkSDCardPermission(activity);
        OkDownload.getInstance().setFolder(BaseApplication.FILE_PATH);
        OkDownload.getInstance().getThreadPool().setCorePoolSize(3);
        startDownLoad(url, downloadListener);
    }

    public static void downApkFile(Activity activity, String apkUrl, DownloadListener downloadListener) {
        //设置下载路径
        if (TextUtils.isEmpty(apkUrl)) {
            XToastUtil.show("文件路径为空");
            return;
        }

        if (!XUtils.checkNetwork(activity)) return;
        if (!checkSDCardPermission(activity)) return;
        OkDownload.getInstance().setFolder(BaseApplication.APK_PATH);
        OkDownload.getInstance().getThreadPool().setCorePoolSize(3);
        startDownLoad(apkUrl, downloadListener);
    }

    public static void uploadFile(Activity activity, String url, String filePath, UploadListener uploadListener) {

        if (TextUtils.isEmpty(filePath)) {
            XToastUtil.show("上传文件为空,请选择文件");
            return;
        }


        File uploadFile = new File(filePath);
        if (null == uploadFile || !uploadFile.exists()) {
            XToastUtil.show("上传文件为空,请选择文件");
            return;
        }

        if (!XUtils.checkNetwork(activity)) return;
        checkSDCardPermission(activity);
        PostRequest<String> postRequest = OkGo.<String>post(url)//
                .headers("X-Access-Token", BaseSPUtil.getToken())
                .params("file", new File(filePath))//
                .converter(new StringConvert());

        UploadTask<String> task = OkUpload.request(filePath, postRequest)//
                .priority(1)//
                .register(uploadListener)
                .save().start();
    }


    private static void startDownLoad(String url, DownloadListener downloadListener) {
        //这里只是演示，表示请求可以传参，怎么传都行，和okgo使用方法一样
        GetRequest<File> request = OkGo.<File>get(url);//
//                .headers("aaa", "111")//
//                .params("bbb", "222");

        //这里第一个参数是tag，代表下载任务的唯一标识，传任意字符串都行，需要保证唯一,我这里用url作为了tag
        OkDownload.request(url, request)//
//                .priority(apk.priority)//优先级
//                .extra1(apk)//
                .save()//
                .register(downloadListener)//
                .start();
    }

    /**
     * 检查SD卡权限
     */
    public static boolean checkSDCardPermission(Activity activity) {

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        boolean b = XPermissionUtils.hasPermissions(activity, permissions);

        if (!b) {
            XPermissionUtils.requestPermissions(activity, 0, permissions);
        }
        return b;
    }

    private static Map<String, String> parseDataParams(BaseParams params) {
        if (params == null) {
            return new HashMap<String, String>();
        }
        Class<?> clz = params.getClass();
        return parseFields(params, clz);
    }

    private static Map<String, String> parseFields(Object input, Class<?> clz) {
        HashMap<String, String> dataParams = new HashMap<String, String>();
        // 获取当前类的所有属性，包括public、protected、private的，但是不包括父类的属性
        Field[] subFields = clz.getDeclaredFields();
        parseFieldsToMap(input, subFields, dataParams, false);
        // 获取所有public的属性，包括父类的；
        Field[] publicFields = clz.getFields();
        parseFieldsToMap(input, publicFields, dataParams, true);
        return dataParams;
    }


    private static void parseFieldsToMap(Object input, Field[] fields, HashMap<String, String> dataParams,
                                         boolean checkFieldInMap) {
        if (fields == null || fields.length == 0) {
            return;
        }
        String fieldName = null;
        Object value = null;
        for (int i = 0; i < fields.length; i++) {
            value = null;
            try {
                fieldName = fields[i].getName();
                if (excludeField(fieldName, dataParams, checkFieldInMap)) {
                    /*
                     * 1、如果是内部类，去掉当前相关类引用；
                     * 2、排除api,v,ecode,sid,serialVersionUID等参数；
                     * 3、排除ORIGINALJSON参数；
                     */
                    continue;
                } else {
                    fields[i].setAccessible(true);
                    value = fields[i].get(input);
                }
            } catch (Throwable e) {
            }
            if (value != null) {
                try {
                    if (value instanceof String) {
                        dataParams.put(fieldName, value.toString());
                    } else {
                        dataParams.put(fieldName, JSON.toJSONString(value));
                    }
                } catch (Throwable e) {
                }
            }

        }
    }


    private static boolean excludeField(String fieldName, HashMap<String, String> dataParams, boolean checkFieldInMap) {
        /*
         * 1、如果是内部类，去掉当前相关类引用； 2、排除api,v,ecode,sid,serialVersionUID等参数；
         * 3、排除ORIGINALJSON参数；
         */
        if ((fieldName.indexOf("$") != -1)) {
            /*
             * 1、如果是内部类，去掉当前相关类引用；
             */
            return true;
        } else if (Constants.API_NAME.equals(fieldName)) {
            /*
             * 2、排除api,v,ecode,sid,serialVersionUID等参数；
             */
            return true;
        } else if (checkFieldInMap) {
            /*
             * 判断数据Map重复field； 子类存在重复public属性时，不需要再获取该属性值，
             * 因为class.getFields()会获取所有public的属性，包括父类的；
             * 当子类存在与父类同名的属性时，会返回子类的属性值，
             * 因此，在class.getDeclaredFields()时，就不需要再获取同名属性值了。
             */
            if (dataParams.containsKey(fieldName)) {
                return true;
            }
        }
        return false;
    }

    private static String getApiName(BaseParams o) {
        if (o == null) {
            return null;
        }
        Class<?> c = (Class<?>) o.getClass();
        Field[] fs = c.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            if (f.getName().equals(Constants.API_NAME)) {
                try {
                    return f.get(o).toString();
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                }
            }
        }
        return null;
    }

}
