package com.afm.commlibrary.bases;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afm.commlibrary.R;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;


/**
 * Created by chenguowu on 2018/11/25.
 */

public class TopBarView extends RelativeLayout {

    private View mBackButton,mTvBarLine;
    private TextView mTvTitle, topbar_right_text;

    public TopBarView(Context context) {
        this(context, null);
    }

    public TopBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.top_bar_layout, null);

        mTvTitle = layout.findViewById(R.id.tv_title);
        mTvBarLine = layout.findViewById(R.id.mTvBarLine);
        topbar_right_text = layout.findViewById(R.id.topbar_right_text);
        mBackButton = layout.findViewById(R.id.iv_back);
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity)
                    ((Activity) getContext()).finish();
            }
        });
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(lp);
        addView(layout);

    }


    public void setBackButtonGone() {
        if (null != mBackButton) mBackButton.setVisibility(GONE);
    }

    public void onBackButtonClickListener(OnClickListener listener) {
        if (null != mBackButton) mBackButton.setOnClickListener(listener);
    }

    //设置标题
    public void setTitle(String title) {
        if (null != mTvTitle) {
            mTvTitle.setText(title);
        }
    }

    //设置标题
    public void setTitleTextColor(@ColorInt int color) {
        if (null != mTvTitle) {
            mTvTitle.setTextColor(color);
        }
    }

    public void setRightText(String rightText) {
        if (null != topbar_right_text) {
            if (View.VISIBLE != topbar_right_text.getVisibility()) {
                topbar_right_text.setVisibility(VISIBLE);
            }
            topbar_right_text.setText(rightText);
        }
    }

    public void setOnRightTextOnClickListener(OnClickListener listener) {
        if (null != topbar_right_text) {
            topbar_right_text.setOnClickListener(listener);
        }
    }

    public void setBarLineGone(){
      if (null != mTvBarLine)mTvBarLine.setVisibility(GONE);
    }


    //    设置高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, getMeasuredHeight());
    }
}
