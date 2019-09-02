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
public class RefreshLoadMoreRecyclerView extends SwipeRefreshLayout {

    private RecyclerView mRecyclerView;
    private BaseQuickAdapter adapter;
//    private View emptyView;
//    private TextView mTvEmptyHint ;

    public RefreshLoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEnabled(false);
        initView();
    }

    private void initView() {
        mRecyclerView = new RecyclerView(this.getContext());
        this.addView(mRecyclerView);
        //设置下拉刷新 圈圈的颜色
        setColorSchemeResources(R.color.app_theme_color);
        addLineItemDecoration();
        initRecycleView();
    }

    public void showRefresh(){
        setRefreshing(true);
    }

    public void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    public void setAdapter(BaseQuickAdapter baseRecyclerAdapter) {
        adapter = baseRecyclerAdapter;
        //调用方法 adapter.getViewByPosition 的时候需要将 recycle 设置进去
        baseRecyclerAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(baseRecyclerAdapter);
//        emptyView = LayoutInflater.from(getContext()).inflate(R.layout.empty_layout, null);
//        mTvEmptyHint = emptyView.findViewById(R.id.tv_empty_hint);
//        adapter.setEmptyView(emptyView);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    public void setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener onLoadMoreListener) {
        adapter.setOnLoadMoreListener(onLoadMoreListener, mRecyclerView);
    }

    public void onLoadMoreComplete() {
        if (isRefreshing())
            setRefreshing(false);
        adapter.loadMoreComplete();
    }


    public void onLoadMoreEnd() {
        adapter.loadMoreEnd();
    }

    public void onLoadMoreError() {
        adapter.loadMoreFail();
    }

    public void onLoadMoreEnable(boolean enable) {
        adapter.setEnableLoadMore(enable);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 上午 10:17
     * Des 默认不能下拉刷新 设置监听的时候设置开启下拉刷新
     */
    public void setRefreshListener(OnRefreshListener listener) {
        setEnabled(true);
        setOnRefreshListener(listener);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 上午 10:26
     * Des 下拉刷新完成 并刷新适配器
     */
    public void refreshComplete() {
//		if (null != adapter) adapter.notifyDataSetChanged();
        if (isRefreshing())
            setRefreshing(false);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 上午 10:26
     * Des 下拉刷新完成 并刷新适配器
     */
    public RecyclerView.LayoutManager getLayoutManager() {
//		if (null != adapter) adapter.notifyDataSetChanged();
        return mRecyclerView.getLayoutManager();
    }


    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 下午 3:23
     * Des 行点击事件
     */
    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener listener) {
        if (null != adapter) adapter.setOnItemClickListener(listener);
    }
    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 下午 3:23
     * Des 行长按事件
     */
    public void setOnItemLongClickListener(BaseQuickAdapter.OnItemLongClickListener listener) {
        if (null != adapter) adapter.setOnItemLongClickListener(listener);
    }
    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 下午 3:23
     * Des 行长按事件
     */
    public View getViewByPersion(int position, int viewId) {
        if (null != adapter) return adapter.getViewByPosition(position,viewId);
        return null;
    }

    /**
     * Author chen guo wu
     * Time 2018/8/14 0014 下午 3:23
     * Des 行点击事件
     */
    public void setOnChildClickListener(BaseQuickAdapter.OnItemChildClickListener childClickListener) {
        if (null != adapter) adapter.setOnItemChildClickListener(childClickListener);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/15 0015 下午 6:43
     * Des 添加分割线 参数是分割线高度
     */
    public void addLineItemDecoration() {
        addLineItemDecoration(R.dimen.line_height);
    }

    public void addLineItemDecoration(int linHeight) {
        addLineItemDecoration(linHeight, R.color.line_color);
    }

    public void addLineItemDecoration(int linHeight, int lineColor) {
        mRecyclerView.addItemDecoration(new LineItemDecoration(getContext(), linHeight, lineColor));
    }


    /**
     * Author chen guo wu
     * Time 2018/8/15 0015 下午 6:44
     * Des 获取适配器
     */
    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    /**
     * Author chen guo wu
     * Time 2018/8/15 0015 下午 2:02
     * Des 返回数据容器
     * 必须在 setAdapter之后调用 不然 adapter为空
     * 必须在 setAdapter之后调用
     * 必须在 setAdapter之后调用
     * *
     */
    public List getData() {
        return adapter.getData();
    }

    /**
     * Author chen guo wu
     * Time 2018/8/15 0015 下午 2:02
     * Des 清空容器里的数据
     * 必须在 setAdapter之后调用
     * 不然 adapter为空
     * 必须在 setAdapter之后调用
     * 必须在 setAdapter之后调用
     * <p>
     * *
     */
    public void clearData() {
        if (null != adapter)
            adapter.getData().clear();
    }

    /**
     * Author chen guo wu
     * Time 2018/8/15 0015 下午 2:02
     * Des 返回数据容器 必须在 setAdapter之后调用 不让 adapter为空
     * 必须在 setAdapter之后调用
     * 必须在 setAdapter之后调用
     * *
     */
    public void addNewData(List newData) {
        if (null != adapter)
            adapter.addData(newData);
    }

    public void setNewData(List newData) {
        if (null != adapter)
            adapter.setNewData(newData);
    }

    public void notifyDataSetChanged() {
        if (null != adapter)
            adapter.notifyDataSetChanged();
    }

//    public void hideEmptyView() {
//        if (null != emptyView)
//            emptyView.setVisibility(GONE);
//        adapter.setEmptyView(emptyView);
//    }

    /**
     * Author chen guo wu
     * Time 2018/8/31 0031 上午 10:41
     * Des 设置layoutmanager
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        if (null != mRecyclerView)
            mRecyclerView.setLayoutManager(manager);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/31 0031 上午 10:41
     * Des 设置ItemAnimator
     */
    public void setItemAnimator(RecyclerView.ItemAnimator animate) {
        if (null != mRecyclerView)
            mRecyclerView.setItemAnimator(animate);
    }

    /**
     * Author chen guo wu
     * Time 2018/8/31 0031 上午 10:41
     * Des 设置layoutmanager
     */
    public void addOnScrolListener(RecyclerView.OnScrollListener scrollListener) {
        if (null != mRecyclerView)
            mRecyclerView.addOnScrollListener(scrollListener);
    }

    /**
     * Author chen guo wu
     * Time 2018/9/16 0016 下午 4:06
     * Des 添加头部
     * */
    public void addHeadView(View headView) {
        if (null != adapter)
        adapter.addHeaderView(headView) ;
    }

///**
// * Author chenguowu
// * Time 2019/1/21 14:17
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * */
//    public void setEmptyHintText(String hintText){
//        if (null != mTvEmptyHint)
//        mTvEmptyHint.setText(hintText);
//    }
//
///**
// * Author chenguowu
// * Time 2019/1/21 14:17
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * */
//    public void setEmptyTextColor(int hintTextColor){
//        if (null != mTvEmptyHint)
//        mTvEmptyHint.setTextColor(getResources().getColor(hintTextColor));
//    }
///**
// * Author chenguowu
// * Time 2019/1/21 14:17
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * Des 要在 setAdapter 之后设置
// * */
//    public void setEmptyTextSize(int hintTextSize){
//        if (null != mTvEmptyHint)
//        mTvEmptyHint.setTextSize(getResources().getDimension(hintTextSize));
//    }

}