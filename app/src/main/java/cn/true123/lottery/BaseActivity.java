package cn.true123.lottery;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import cn.true123.lottery.application.LotteryApplication;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    Toolbar toolbar;
    private long preBackClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewResId());
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            parentView.setFitsSystemWindows(true);
        }
    }

    public abstract int getContentViewResId();

    public void addToList(Activity activity) {
        ((LotteryApplication) getApplication()).addRunningActivity(activity);
    }

    public void exitApp() {
        ((LotteryApplication) getApplication()).exit();
    }

    public void finishActivity(Activity activity) {
        ((LotteryApplication) getApplication()).removeRunningActivity(activity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long curBackClickTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (curBackClickTime - preBackClickTime > 1 * 1000) {
                Snackbar.make(this.getWindow().getDecorView(), "再按一次后退键退出程序", Snackbar.LENGTH_SHORT).show();
            } else if (curBackClickTime - preBackClickTime < 1 * 1000) {
                exitApp();
            }
            preBackClickTime = System.currentTimeMillis();

        }
        return true;
    }
}
