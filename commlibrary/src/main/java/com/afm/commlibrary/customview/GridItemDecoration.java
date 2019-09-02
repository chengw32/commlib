package com.afm.commlibrary.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.afm.commlibrary.Utils.XLogUtil;

import androidx.annotation.ColorRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Author chenguowu
 * Time 2019/8/28 8:50
 * Des 网格风格线
 * */
public class GridItemDecoration extends RecyclerView.ItemDecoration {


    private int dividerHeight = 3;

    private Paint linePaint;


    public GridItemDecoration(Context context, @ColorRes int color, int height) {
        this.linePaint = new Paint();
        dividerHeight = context.getResources().getDimensionPixelSize(height);
        linePaint.setColor(context.getResources().getColor(color));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawGridLine(c, parent);
    }

    private void drawGridLine(Canvas c, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        XLogUtil.e("childCount: " + childCount);


        int horizontal_bottom = 0;
        int horizontal_top = 0;
        int horizontal_left = 0;
        int horizontal_right = 0;


        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;

            //竖直放方向的分割线
            if ((i + spanCount) < childCount) {
                c.drawRect(left, top, right, bottom, linePaint);
            }


            //水平方向风格线
            int i1 = i % spanCount;


            if (i1 == 0) {
                //画中间的分割线 left top right 都是第一次画了之后后面都不会变的
                //变的只是bottom

                if (horizontal_top == 0) horizontal_top = view.getTop();
                if (horizontal_left == 0) horizontal_left = view.getRight();
                if (horizontal_right == 0) horizontal_right = horizontal_left + dividerHeight;

                //计算底部的坐标
//                if (horizontal_bottom == 0) {
//                    //第一次计算出高度 后面只要叠加就行 每行是一样的高度
//                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
//                    int topMargin = layoutParams.topMargin;
//                    int bottomMargin = layoutParams.bottomMargin;
//                    XLogUtil.e(topMargin);
//                    horizontal_bottom += view.getBottom() + dividerHeight+ topMargin+bottomMargin;
//                } else
//                    horizontal_bottom += horizontal_bottom;

                horizontal_bottom = view.getBottom();
                XLogUtil.e(horizontal_bottom);
                c.drawRect(horizontal_left, horizontal_top, horizontal_right, horizontal_bottom, linePaint);
            }

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int itemPosition = parent.getLayoutManager().getPosition(view);
        int spanCount = getSpanCount(parent);
        outRect.left = dividerHeight;
        outRect.top = dividerHeight;
        outRect.right = dividerHeight;
        outRect.bottom = dividerHeight;
        if (itemPosition != 0) {
            if (itemPosition / spanCount == 0) {
                outRect.left = 0;
            } else {
                if (itemPosition % spanCount == 0) {
                    outRect.top = 0;
                } else {
                    outRect.left = 0;
                    outRect.top = 0;
                }
            }
        }
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}
