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

/**
 * Author chenguowu
 * Time 2019/9/12 10:29
 * Des 编辑框 带有清空功能
 * */
public class XEditTextWithClean extends FrameLayout {


    private float mCleanIconHeight,mCleanIconWidth,mTextSize,mEditTextPaddingTop,mEditTextPaddingBottom;
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
        mXEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                XLogUtil.e("beforeTextChanged="+s);
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
        mCleanIcon = inflate.findViewById(R.id.mClean);
        mCleanIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mXEdittext.setText("");
            }
        });

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XEditTextWithClean);
        mCleanIconHeight = ta.getDimension(R.styleable.XEditTextWithClean_x_clean_icon_height, dp2px(15));
        mCleanIconWidth = ta.getDimension(R.styleable.XEditTextWithClean_x_clean_icon_width, dp2px(15));
        mTextSize = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_text_size, sp2px(10f));
        mTextColor = ta.getColor(R.styleable.XEditTextWithClean_x_edittext_text_color, Color.parseColor("#000000"));
        mHintColor = ta.getColor(R.styleable.XEditTextWithClean_x_edittext_hint_color,Color.parseColor("#CCCCCC"));
        mEditTextPaddingTop = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_top,dp2px(10));
        mEditTextPaddingBottom = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_bottom,dp2px(10));
        mEditTextPaddingBottom = ta.getDimension(R.styleable.XEditTextWithClean_x_edittext_padding_bottom,dp2px(10));
        mText = ta.getString(R.styleable.XEditTextWithClean_x_edittext_text);
        mHintText = ta.getString(R.styleable.XEditTextWithClean_x_edittext_hint_text);

        ta.recycle();


        XLogUtil.e("mTextSIze: "+mTextSize);

        mXEdittext.setTextColor(mTextColor);
        mXEdittext.setHintTextColor(mHintColor);
        mXEdittext.setTextSize(mTextSize);
        mXEdittext.setHint(mHintText);
        mXEdittext.setText(mText);


        mXEdittext.setPadding( mXEdittext.getPaddingLeft(),(int)mEditTextPaddingTop, mXEdittext.getPaddingRight(),(int)mEditTextPaddingBottom);

    }


    public void setText(String str){
        mXEdittext.setText(str);
    }

    protected int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale );
    }

    protected int sp2px(float sp) {
        final float scale = this.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale );
    }
}
