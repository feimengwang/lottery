package cn.true123.lottery.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.model.Ball;
import cn.true123.lottery.utils.BitmapUtils;

@SuppressLint("DrawAllocation")
public class KJLineItemView extends ViewGroup {
    private List<Ball> list;
    private float padding = 6;
    private int screenWidth, screenHeight;
    private static final int maxSize = 9;
    int rowHeight = 0;
    //int childNum = 0;

    public KJLineItemView(Context context) {
        super(context);
    }

    public KJLineItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public KJLineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        int right = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int w1 = child.getMeasuredWidth();
            int h1 = child.getMeasuredHeight();

            left += padding;
            int top = (int) padding;

            right = (int) (left + w1 + padding);
            int bottom = (int) (padding + h1);

            if (child instanceof TextView && ((TextView) child).length() > 3) {
                Paint paint = new Paint();
                paint.setTextSize(((TextView) child).getTextSize());
                Paint.FontMetrics fm = paint.getFontMetrics();
                top += (rowHeight - (Math.abs(fm.top - fm.bottom))) / 2;
                bottom = (int) (top + Math.abs(fm.top - fm.bottom));
            }
            child.layout(left, top, right, bottom);
            left += w1 + padding;

        }
    }

    public void setWidthHeight(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void addViewList(List<Ball> list) {
        removeAllViews();
        Iterator<Ball> it = list.iterator();
        //childNum = list.size();
        while (it.hasNext()) {
            Ball ball = it.next();
            addView(createItemView(ball));
        }
        postInvalidate();
    }

    private View createItemView(Ball ball) {


        int toWidth = (int) ((screenWidth - maxSize * padding) / maxSize);
        Bitmap reSizeRed = null;
        Bitmap reSizeBlue = null;
        Bitmap bitmapblue = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_ball);
        reSizeBlue = BitmapUtils.createBitmapBySize(bitmapblue, toWidth, toWidth);
        Bitmap bitmapred = BitmapFactory.decodeResource(getResources(), R.mipmap.red_ball);
        reSizeRed = BitmapUtils.createBitmapBySize(bitmapred, toWidth, toWidth);

        View v = (View) LayoutInflater.from(getContext()).inflate(
                R.layout.ball, null);
        TextView tv = (TextView) v.findViewById(R.id.text_ball);
        tv.setText(ball.getNum());

        if (ball.isBlue()) {
            if (reSizeBlue != null) {
                tv.setBackground(BitmapUtils.bitmapToDrawableByBD(getResources(), reSizeBlue));
            } else
                tv.setBackgroundResource(R.mipmap.blue_ball);
        } else if (ball.isRed()) {
            if (reSizeRed != null) {
                tv.setBackground(BitmapUtils.bitmapToDrawableByBD(getResources(), reSizeRed));
            } else
                tv.setBackgroundResource(R.mipmap.red_ball);
        } else {
            tv.setBackgroundResource(android.R.color.transparent);
            tv.setTextColor(getResources().getColor(R.color.blue));
        }
        return v;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int cCount = getChildCount();
        int toWidth = 0;
        int width = 0, height = 0;
        int cWidth, cHeight;
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            if (toWidth > 0) {
                childView.measure(toWidth, toWidth);
            }
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            width += cWidth + padding * 2;
            if (cHeight > height) {
                height = cHeight;
            }
        }
        rowHeight = (int) (height + padding * 2);
        setMeasuredDimension((int) (width + padding), rowHeight);

    }

}
