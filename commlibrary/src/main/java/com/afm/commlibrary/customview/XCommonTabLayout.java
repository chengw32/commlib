package com.afm.commlibrary.customview;

import android.content.Context;
import android.util.AttributeSet;

import com.afm.commlibrary.Utils.XLogUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by chenguowu on 2019/11/7.
 */
public class XCommonTabLayout extends CommonTabLayout implements OnTabSelectListener {

    private List<Fragment> mFragments = new ArrayList<>();
    private ViewPager mViewPager;

    public XCommonTabLayout(Context context) {
        super(context);
    }

    public XCommonTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setViewPager(ViewPager vp, List fragments, ArrayList<CustomTabEntity> tabs, FragmentManager fm) {

        if (vp == null || fragments == null || fragments.size() == 0 || tabs == null || fm == null) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }

        setTabData(tabs);
        setOnTabSelectListener(this);

        this.mViewPager = vp;
        mFragments.clear();
        mFragments.addAll(fragments);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setAdapter(new MyPagerAdapter(fm));
    }

    public void setViewPager(ViewPager vp, List fragments, String[] titiels, FragmentManager fm) {

        if (titiels == null || titiels.length == 0) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }

        ArrayList<CustomTabEntity> tabEntityList = new ArrayList<>();

        for (String tabTitle : titiels) {
            tabEntityList.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return tabTitle;
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
        }

        setViewPager(vp, fragments, tabEntityList, fm);
    }

    @Override
    public void onTabSelect(int position) {
        XLogUtil.e("onTabSelect");
        if (null != mViewPager) mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }


    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
