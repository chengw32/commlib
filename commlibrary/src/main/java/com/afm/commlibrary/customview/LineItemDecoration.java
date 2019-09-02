package com.afm.commlibrary.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.afm.commlibrary.R;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Administrator on 2018/8/14 0014.
 */

public class LineItemDecoration extends RecyclerView.ItemDecoration {

    private Paint linePaint;

    private int dividerHeight;

    public LineItemDecoration(Context context, int linHeight, int lineColor) {
        this.linePaint = new Paint();
        linePaint.setColor(context.getResources().getColor(lineColor == 0 ? R.color.white : lineColor));
        dividerHeight = context.getResources().getDimensionPixelSize(linHeight);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, linePaint);
        }

    }
}
