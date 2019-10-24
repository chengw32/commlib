package com.afm.commlibrary.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * Created by chenguowu on 2019/9/19.
 * 便于使用 不用每次都传BaseViewHolder
 */
public abstract class XAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder>{
    public XAdapter(int layoutResId) {
        super(layoutResId);
    }
}
