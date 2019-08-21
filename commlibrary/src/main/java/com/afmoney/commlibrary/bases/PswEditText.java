package com.afmoney.commlibrary.bases;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.afmoney.commlibrary.R;


/**
 * Created by chenguowu on 2018/11/25.
 */

public class PswEditText extends RelativeLayout {

    private String mEtText,mHintText ;
    private  EditText mEtPsw ;

    public PswEditText(Context context) {
        this(context, null);
    }

    public PswEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PswEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PswEditText);
        mHintText = typedArray.getString(R.styleable.PswEditText_hinttext);
        mEtText = typedArray.getString(R.styleable.PswEditText_text);

        typedArray.recycle();

        initView();
    }


    private void initView() {

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.psw_edit_text_layout, null);

        mEtPsw = layout.findViewById(R.id.mEtPsw);
        CheckBox mCbEye = layout.findViewById(R.id.mCbEye);
        mCbEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mEtPsw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mEtPsw.setSelection((mEtPsw.getText().toString().trim()).length());
                }else {
                    mEtPsw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mEtPsw.setSelection((mEtPsw.getText().toString().trim()).length());
                }
            }
        });


        if (TextUtils.isEmpty(mEtText) && !TextUtils.isEmpty(mHintText))
        mEtPsw.setHint(mHintText);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL ;
        layout.setLayoutParams(lp);
        addView(layout);

    }

    public String getText(){
        if (null != mEtPsw)
            return mEtPsw.getText().toString().trim();
        return "";
    }

    public void addTextChangedListener(TextWatcher watcher){
        if (null != mEtPsw){
             mEtPsw.addTextChangedListener(watcher);
        }
    }



//    //    设置高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, getMeasuredHeight());
    }
}
