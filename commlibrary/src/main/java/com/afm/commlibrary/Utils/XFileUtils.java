package com.afm.commlibrary.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by chenguowu on 2019/3/14.
 */
public class XFileUtils {
    public static String getFileType(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }

        return value;
    }


    public static String getFileSize(File file) {
        String size = null;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                DecimalFormat df = new DecimalFormat("#.00");
                try {
                    size = df.format((double) fis.available() / 1048576) + " MB";
                } catch (IOException e) {
                    size = "0 MB";
                }
            } catch (FileNotFoundException e) {
                size = "0 MB";
            }
        }
        return size;
    }

    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        int times = 0;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[1024];
            /*
             * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            while (is.read(b) != -1) {
                if (times < 1) {
                    times++;
                    XLogUtil.e("文件数据====："+new String(b,"UTF-8"));
                }
//            value = bytesToHexString(b);
            }
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        System.out.println(builder.toString());
        return builder.toString();
    }


    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
        //images
//        mFileTypes.put("FFD8FF", "jpg");
//        mFileTypes.put("FFD8FFE0", "jpg");
//        mFileTypes.put("89504E47", "png");
//        mFileTypes.put("47494638", "gif");
//        mFileTypes.put("49492A00", "tif");
//        mFileTypes.put("424D", "bmp");
//        mFileTypes.put("3109616E", "txt");
//        //
//        mFileTypes.put("41433130", "dwg"); //CAD
//        mFileTypes.put("38425053", "psd");
//        mFileTypes.put("7B5C727466", "rtf"); //日记本
//        mFileTypes.put("3C3F786D6C", "xml");
//        mFileTypes.put("68746D6C3E", "html");
//        mFileTypes.put("44656C69766572792D646174653A", "eml"); //邮件
//        mFileTypes.put("D0CF11E0", "doc");
//        mFileTypes.put("5374616E64617264204A", "mdb");
//        mFileTypes.put("252150532D41646F6265", "ps");
//        mFileTypes.put("255044462D312E", "pdf");
//        mFileTypes.put("504B0304", "zip");
//        mFileTypes.put("52617221", "rar");
        //视频
        mFileTypes.put("00000020", "mp4");
        mFileTypes.put("0000001C", "mp4/3gp");
        mFileTypes.put("3026B275", "wmv");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
    }

    public static boolean isVideo(String filePath) {
        if (mFileTypes.containsKey(getFileType(filePath))) return true;
        return false;
    }

}
