package com.afm.commlib;


import android.widget.TextView;

import com.afm.commlibrary.bases.BaseTextView;
import com.afm.commlibrary.bases.BaseWithImmersionBarActivity;

public class MainActivity extends BaseWithImmersionBarActivity {


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected int getCustomLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {

    }


    @Override
    public void initData() {

    }
}
