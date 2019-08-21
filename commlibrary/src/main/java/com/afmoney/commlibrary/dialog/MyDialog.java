package com.afmoney.commlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.afmoney.commlibrary.R;


public class MyDialog extends Dialog {

    private String titleMessage;
    private String contontMessage,mRightStr,mLeftStr;
    private TextView mLeft_button , mRight_button;
    public OnConfirmClickListener oncConfirmListener;
    private TextView diolog_contont ;

    public MyDialog(Context context, String message) {
        super(context, R.style.dialog_custom);
        this.contontMessage = message;
    }
    public MyDialog(Context context, String title, String message) {
        super(context, R.style.dialog_custom);
        this.titleMessage = title;
        this.contontMessage = message;
    }
    public MyDialog(Context context, String titleMessage, String contontMessage,OnConfirmClickListener listen) {
       this(context,titleMessage,contontMessage);
        this.oncConfirmListener = listen ;
    }
    public MyDialog(Context context, String contontMessage, OnConfirmClickListener listen) {
       this(context,"",contontMessage,listen);
        this.oncConfirmListener = listen ;
    }
    public MyDialog(Context context, String titleMessage, String contontMessage, OnConfirmClickListener listen, String rStr, String lStr) {
       this(context,titleMessage,contontMessage,listen);
        this.mRightStr = rStr ;
        this.mLeftStr = lStr ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.make_sure_layout);
//        setCanceledOnTouchOutside(false);// 点击Dialog外部不会dismiss
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(layoutParams);
//        window.setGravity(Gravity.BOTTOM);
        initView();
    }

    private void initView() {
        TextView diolog_title = (TextView) findViewById(R.id.tv_title);
        diolog_title.setText(titleMessage);
        diolog_contont = (TextView) findViewById(R.id.tv_content);
        diolog_contont.setText(contontMessage);
        mRight_button = (TextView) findViewById(R.id.dialog_right_button);
        mRight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != oncConfirmListener) {
                    oncConfirmListener.confirm();
                }
                dismiss();
            }
        });
        mLeft_button = (TextView) findViewById(R.id.dialog_left_button);
        mLeft_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != oncConfirmListener) oncConfirmListener.onCancle();
                dismiss();


            }
        });
    }


    public void setText(String content) {
       if (null != diolog_contont)diolog_contont.setText(content);
    }
}
