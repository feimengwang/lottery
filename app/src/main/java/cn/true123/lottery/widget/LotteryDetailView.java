package cn.true123.lottery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Iterator;
import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.model.KJRect;
import cn.true123.lottery.model.LotteryDetail;

public class LotteryDetailView extends View {
	private static final String TAG="NewKJDetail";
	private int col = 3;
	private int row = 5;
	private int textcolor = Color.parseColor("#ffffff");
	private int bgcolor;
	private int hbgcolor;
	private List<LotteryDetail.LevelEntity> items;
	private float startx = 0, starty = 80;
	private float rowHeight = 80;
	private float height;
	private float width;
	private float paddingLeft = 0;
	private float paddingRight = 0;
	private int padingBottom= 10;
	private int paddingTop= 80;

	public LotteryDetailView(Context context) {
		super(context);
		init(null);
	}

	public LotteryDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public LotteryDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(attrs);
	}

	private void init(AttributeSet attrs) {
		if (attrs == null)
			return;
		TypedArray ta = getResources().obtainAttributes(attrs,
				R.styleable.Kjdetail);
		if (ta != null) {
			col = ta.getInteger(R.styleable.Kjdetail_tablecol, 3);
			textcolor = ta
					.getColor(R.styleable.Kjdetail_textcolor, Color.BLACK);
			bgcolor = ta.getColor(R.styleable.Kjdetail_bgcolor, Color.GRAY);
			hbgcolor = ta.getColor(R.styleable.Kjdetail_headbgcolor, Color.YELLOW);
		}
		ta.recycle();


		

	}

	private KJRect getRect(String text){
		Paint	mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Rect mBounds = new Rect();
		mPaint.setTextSize(35);
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textWidth = mBounds.width();
		float textHeight = mBounds.height();
		KJRect rc = new KJRect();
		rc.setHeight(textHeight);
		rc.setWidht(textWidth);
		return rc;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int lwidth = 0;
		int lheight = 0;
		Log.i(TAG,"heightSize="+heightSize);
		if (widthMode == MeasureSpec.EXACTLY) {
			lwidth = widthSize;
		} 

		if (heightMode == MeasureSpec.EXACTLY) {
			lheight = heightSize;
		} else if(heightMode==MeasureSpec.AT_MOST){
			lheight = (int) height+paddingTop+padingBottom;
		}else{
			lheight = (int) height+paddingTop+padingBottom;
		}
		Log.i(TAG, "onmeasure="+(heightMode == MeasureSpec.EXACTLY)+"lheight="+lheight+";lwidth="+lwidth);
		setMeasuredDimension(lwidth, lheight);
		requestLayout();
	}


	public void setList(List items, int width) {
		this.items = items;
		Log.i(TAG,"setList="+items.size()+";"+width);
		if (items != null){
			row = items.size();
			Log.i(TAG,"row1="+row);
			height = rowHeight * row;
			measure(width, (int)height+paddingTop+padingBottom);
		}
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		Log.i(TAG,"onDraw=");
		width = getMeasuredWidth();
		Paint bgpaint = new Paint();
		bgpaint.setColor(bgcolor);
		bgpaint.setStyle(Style.FILL);
		Log.i(TAG,"ondraw==="+width+";"+(height+paddingTop+padingBottom+50));
		canvas.drawRect(0, 0, width, height+paddingTop+padingBottom+50, bgpaint);

		drawhead(canvas);
		drawRect(canvas);
		drawLine(canvas);
		drawText(canvas);
	}
	
	private void drawhead(Canvas canvas){
		Paint paint = new Paint();
		// getResources().getColor(hbgcolor);
		paint.setColor(textcolor);
		paint.setStyle(Style.FILL);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(35);
		String text=getResources().getString(R.string.kj_detail);
		KJRect rc= getRect(text);
		RectF rf = new RectF();
		rf.set(0, 0, width, starty);
		Bitmap bp = BitmapFactory.decodeResource(getResources(), R.mipmap.notice_detail_background);
		canvas.drawBitmap(bp, null, rf, null);
		canvas.drawText(text, paddingLeft*2, paddingTop-(paddingTop-rc.getHeight())/2, paint);
	}
	private void drawText(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(textcolor);
		paint.setStyle(Style.FILL);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(35);
		if(items!=null && items.size()>0){
			Iterator it = items.iterator();
			int i=0;
			while(it.hasNext()){
				LotteryDetail.LevelEntity item = (LotteryDetail.LevelEntity) it.next();
				KJRect kjrc= getRect(item.getName());
				
				float sx = startx+((width-paddingLeft-paddingRight)/4-kjrc.getWidht())/2;
				float sy = starty+ rowHeight *i+(rowHeight -kjrc.getHeight())/2+kjrc.getHeight();
				//Toast.makeText(getContext(), ""+(width-paddingLeft-paddingRight)/5+";"+kjrc.getWidht(), Toast.LENGTH_SHORT).show();
				canvas.drawText(item.getName(), sx, sy, paint);
				kjrc = getRect(item.getCount());
				sx = startx+(width-paddingLeft-paddingRight)/4+(((width-paddingLeft-paddingRight)*3)/8-kjrc.getWidht())/2;
				sy=starty+ rowHeight *i+(rowHeight -kjrc.getHeight())/2+kjrc.getHeight();
				canvas.drawText(item.getCount(), sx, sy, paint);
				kjrc = getRect(item.getFund());
				sx = startx+(width-paddingLeft-paddingRight)/8*5+(((width-paddingLeft-paddingRight)*3)/8-kjrc.getWidht())/2;
				sy=starty+ rowHeight *i+(rowHeight -kjrc.getHeight())/2+kjrc.getHeight();
				canvas.drawText(item.getFund(), sx, sy, paint);
				i++;
			}
		}
		
		
	}
	
	private void drawLine(Canvas canvas){
		Paint paintLine = new Paint();

		paintLine.setColor(Color.rgb(79, 129, 189));

		paintLine.setStrokeWidth(1);
		

		paintLine.setStyle(Style.STROKE);
		
		//draw two vertical lines
		canvas.drawLine(startx+(width-paddingLeft-paddingRight)*2/8, starty, startx+(width-paddingLeft-paddingRight)*2/8, starty+height, paintLine);
		canvas.drawLine(startx+((width-paddingLeft-paddingRight)*5)/8, starty, startx+((width-paddingLeft-paddingRight)*5)/8, starty+height, paintLine);
		Log.i(TAG,"rowsss="+row);
		for(int i=0;i<row-1;i++){
			canvas.drawLine(startx, starty+(rowHeight *(i+1)), width - paddingRight, starty+(rowHeight *(i+1)), paintLine);
		}
		
	}
	
	private void drawRect(Canvas canvas) {
		Paint headRect = new Paint();
		headRect.setColor(hbgcolor);
		//headRect.setStrokeWidth(2);

		headRect.setStyle(Style.FILL);
		canvas.drawRect(0, starty, width, starty+ rowHeight, headRect);
		
		Paint paintRect = new Paint();
		paintRect.setColor(Color.BLACK);
		paintRect.setStrokeWidth(2);

		paintRect.setStyle(Style.STROKE);
		//canvas.drawRect(startx, starty, width - paddingRight, starty+height, paintRect);
		canvas.drawLine(startx, starty, width, starty, paintRect);
		canvas.drawLine(startx, starty+height, width, starty+height, paintRect);
		//canvas.save();
	}
}
