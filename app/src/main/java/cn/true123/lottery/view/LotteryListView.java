package cn.true123.lottery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import cn.true123.lottery.R;

/**
 * Created by feimeng0530 on 2016/3/22.
 */
public class LotteryListView extends ListView {
    private TextView textView;
    ListViewFooterOnClickListener listener;
    public LotteryListView(Context context) {
        super(context);
    }

    public LotteryListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LotteryListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setListener(ListViewFooterOnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void addFooterView(View v) {
         v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_footer,null);
        textView= (TextView) v.findViewById(R.id.list_view_footer);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.onClick();
            }
        });
        super.addFooterView(v);
    }
    public void setScrollEnd(){
        textView.setText(getResources().getString(R.string.loadEnd));
        textView.setClickable(false);
    }

    public interface ListViewFooterOnClickListener{
        public void onClick();
    }
}
