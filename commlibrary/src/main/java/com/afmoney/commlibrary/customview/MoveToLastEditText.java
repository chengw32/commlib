package com.afmoney.commlibrary.customview;

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
public class MoveToLastEditText extends EditText {
    public MoveToLastEditText(Context context) {
        super(context);
    }

    public MoveToLastEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveToLastEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        //使光标移动到最后的字符串
        if (!TextUtils.isEmpty(text)) {
            setSelection(text.length());
        }

    }

}
