package com.afm.commlibrary.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import androidx.annotation.LayoutRes;

/**
 * Created by chenguowu on 2019/9/19.
 * 便于使用 不用每次都传BaseViewHolder
 */
public abstract class XAdapter<T> {

    public BaseQuickAdapter mAdapter ;

    public XAdapter(@LayoutRes int itemLayout) {

        mAdapter = new BaseQuickAdapter<T, BaseViewHolder>(itemLayout){
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                XAdapter.this.convert(helper,item);
            }
        };
    }

    protected abstract void convert(BaseViewHolder helper, T item);
}
