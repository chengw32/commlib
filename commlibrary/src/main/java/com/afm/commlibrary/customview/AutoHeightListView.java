package com.afm.commlibrary.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @version 1.0
 * @author: Chenguozhen
 * email 1021632321@qq.com
 * @date: Create in 6:17 PM 2019/7/3
 * @description:
 * @Modified: By:
 */
public class AutoHeightListView extends ListView
{
    public AutoHeightListView(Context context) {
        super(context);
    }

    public AutoHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
