package com.afm.commlibrary.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afm.commlibrary.R;
import com.afm.commlibrary.Utils.XLogUtil;
import com.afm.commlibrary.Utils.XUtils;

/**
 * Author chenguowu
 * Time 2019/9/12 10:29
 * Des 编辑框 带有清空功能
 * */
public class XEditTextWithClean extends FrameLayout {


    private float mCleanIconWH,mTextSize,mEditTextPaddingTop,mEditTextPaddingBottom,mEditTextPaddingLeft,mEditTextPaddingRight;
    private  Drawable mIconDrawable;
    private XEditText mXEdittext;
    private ImageView mCleanIcon;
    private int mTextColor,mHintColor;
    private String mText,mHintText;

    public XEditTextWithClean(Context context) {
        this(context,null);
    }

    public XEditTextWithClean(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XEditTextWithClean(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.edit_text_with_clean_layout, null);
        addView(inflate);

        mXEdittext = inflate.findViewById(R.id.mEt);
        mCleanIcon = inflate.findViewById(R.id.mClean);

        //default 的值都是像素值 , 从xml里面获取的都会转换成像素 比如xlm配置的是 25dp ，通过ta.getDimension()之后就是 75了

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XEditTextWithClean);
        mCleanIconWH = ta.getDimension(R.styleable.XEditTextWithClean_x_clean_icon_wh, XUtils.dip2px(25f));
        mTextSize = XUtils.px2sp(ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_text_size, XUtils.sp2px(13f)));
        mTextColor = ta.getColor(R.styleable.XEditTextWithClean_x_edittext_text_color, Color.parseColor("#000000"));
        mHintColor = ta.getColor(R.styleable.XEditTextWithClean_x_edittext_hint_color,Color.parseColor("#CCCCCC"));
        mEditTextPaddingTop = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_top,10);
        mEditTextPaddingBottom = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_bottom,XUtils.dip2px(10));
        mEditTextPaddingLeft = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_left,XUtils.dip2px(10));
        mEditTextPaddingRight = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_right,XUtils.dip2px(10));
        mText = ta.getString(R.styleable.XEditTextWithClean_x_edittext_text);
        mHintText = ta.getString(R.styleable.XEditTextWithClean_x_edittext_hint_text);
        mIconDrawable = ta.getDrawable(R.styleable.XEditTextWithClean_x_clean_icon_drawable);


        ta.recycle();


        mXEdittext.setTextColor(mTextColor);
        mXEdittext.setHintTextColor(mHintColor);
        mXEdittext.setTextSize(mTextSize);
        mXEdittext.setHint(mHintText);
        mXEdittext.setText(mText);

        if (null != mIconDrawable)mCleanIcon.setImageDrawable(mIconDrawable);


        mXEdittext.setPadding((int) mEditTextPaddingLeft,(int)mEditTextPaddingTop, (int) mEditTextPaddingRight,(int)mEditTextPaddingBottom);


        ViewGroup.LayoutParams iconLayoutParams = mCleanIcon.getLayoutParams();
        iconLayoutParams.width = (int) mCleanIconWH;
        iconLayoutParams.height = (int) mCleanIconWH;
        mCleanIcon.setLayoutParams(iconLayoutParams);

        mXEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    mCleanIcon.setVisibility(GONE);
                }else {
                    mCleanIcon.setVisibility(VISIBLE);
                }

            }
        });

        mCleanIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mXEdittext.setText("");
            }
        });
    }


    public void setText(String str){
        mXEdittext.setText(str);
    }




    public String getText(){
        return mXEdittext.getXText();
    }

    public boolean isEmpty(){
        return mXEdittext.isEmpty();
    }


}
