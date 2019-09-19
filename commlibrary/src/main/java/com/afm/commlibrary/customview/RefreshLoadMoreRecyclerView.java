package com.afm.commlibrary.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.afm.commlibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


/**
 * Author chen guo wu
 * Time 2018/8/10 0010 下午 3:59
 * Des RecycleView 配合 SwipeRefreshLayout使用
 */
public class RefreshLoadMoreRecyclerView extends RefreshLoadMoreRecyclerViewNoDiver {


    public RefreshLoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void initView() {
        super.initView();
        addLineItemDecoration();
    }
}
