package com.afm.commlib;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.afm.commlibrary.bases.BaseWithImmersionBarActivity;

public class MainActivity extends BaseWithImmersionBarActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected int getCustomLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {

        FragmentManager fm = this.getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
               // 将原有的Activity替换成Fragment。也可以添加到原有之上不替换 如：ft.add(R.id.rel, new
               // Fragment()) ;如果添加的多了会比较耗资源。
                ft.add(R.id.content,HomePageFragment.newInstance());
                 // 提交
                ft.commit();


    }

    @Override
    public void initData() {

    }
}
