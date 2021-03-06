package com.afm.commlibrary.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @version 1.0
 * @author: Chenguowu
 * @date: Create in 3:07 PM 2019/6/28
 * @description:
 * @Modified: By:
 */
@SuppressLint("AppCompatCustomView")
public class XEditText extends EditText {
    public XEditText(Context context) {
        super(context);
        init();
    }

    public XEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(null);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        //使光标移动到最后的字符串
        if (!TextUtils.isEmpty(text)) {
            setSelection(text.length());
        }

    }


    /**
     * Author chenguowu
     * Time 2019/7/31 9:01
     * Des 获取字符串并去掉头尾空格
     * */
    public String getXText() {
        return super.getText().toString().trim();
    }

    /**
     * Author chenguowu
     * Time 2019/7/31 9:03
     * Des 是否为空
     * */
    public boolean isEmpty(){
        return TextUtils.isEmpty(super.getText().toString().trim());
    }


}
