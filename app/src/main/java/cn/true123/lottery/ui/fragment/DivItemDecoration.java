package cn.true123.lottery.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 14/11/2016.
 */

public class DivItemDecoration extends RecyclerView.ItemDecoration {
    public static int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static int VERTICAL = LinearLayout.VERTICAL;
    private int orientation;
    private int size;
    private ColorDrawable colorDrawable;

    public DivItemDecoration() {
        super();
    }

    public DivItemDecoration(int orientation, int size) {
        this.orientation = orientation;
        this.size = size;
    }

    public DivItemDecoration(int orientation, int size, int color) {
        this.orientation = orientation;
        this.size = size;
        colorDrawable = new ColorDrawable(color);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        if (colorDrawable == null) return;
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View v = parent.getChildAt(i);
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) v.getLayoutParams();
            int top = v.getBottom() + param.bottomMargin;
            int bottom = top + size;
            colorDrawable.setBounds(left, top, right, bottom);
            colorDrawable.draw(c);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int lastChild = state.getItemCount() - 1;
        MLog.i("lastChild" + lastChild);
        int currentChild = parent.getChildLayoutPosition(view);
        MLog.i("currentChild" + currentChild);
        if (layoutManager instanceof LinearLayoutManager) {
            if (orientation == VERTICAL) {
                if (currentChild < lastChild) {
                    outRect.set(0, 0, 0, size);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            } else {
                if (currentChild < lastChild) {
                    outRect.set(0, 0, size, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }
}
