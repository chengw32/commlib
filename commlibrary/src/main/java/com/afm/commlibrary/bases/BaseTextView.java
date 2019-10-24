package com.afm.commlibrary.bases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.afm.commlibrary.Utils.XUtils;

import androidx.annotation.Nullable;

/**
 * Created by chenguowu on 2019/10/24.
 */
@SuppressLint("AppCompatCustomView")
public class BaseTextView extends TextView {
    public BaseTextView(Context context) {
        super(context);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * Author chenguowu
     * Des 防止 后台返回 "null" 字符串 展示出来就是个bug
     * */
    public void setTextX(CharSequence text) {
        if (null == text || XUtils.isEmpty(text.toString())) text = "";
        super.setText(text, BufferType.NORMAL);
    }

    /**
     * Author chenguowu
     * Des getResources().getColor() 在这边实现
     * */
    public void setTextColorX(int color) {
        int colorResource = getResources().getColor(color);
        super.setTextColor(colorResource);
    }
}
