package cn.true123.lottery.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.true123.lottery.R;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 8/11/2016.
 */

public class NavButton extends FrameLayout {
    //@BindView(R.id.imageView)
    ImageView imageView;
    // @BindView(R.id.dot)
    View view;
    //@BindView(R.id.textView)
    TextView textView;
    private String cls;
    private Fragment fragment;

    public NavButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public NavButton(Context context) {
        super(context);
        init();
    }

    public void setSelected(boolean isSelected) {
        MLog.i("width="+imageView.getWidth()+";="+imageView.getHeight()+";"+imageView.getMeasuredHeight());
        imageView.setSelected(isSelected);
        textView.setSelected(isSelected);
        if(isSelected){
            textView.setTextColor(getResources().getColor(R.color.button_blue));
        }else{
            textView.setTextColor(getResources().getColor(R.color.black));
        }
    }

    public void initView(@DrawableRes int resId, @StringRes int title, String cls) {
        imageView.setImageResource(resId);
        textView.setText(title);
        this.cls = cls;
    }

    public String getClassName() {

        return cls;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setDotVisibility(int visibility) {
        view.setVisibility(visibility);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NavButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public NavButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.nav_buttion, this, true);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        view = findViewById(R.id.dot);

    }
}
