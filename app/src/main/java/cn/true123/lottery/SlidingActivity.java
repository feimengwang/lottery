package cn.true123.lottery;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import cn.true123.lottery.listener.SlidingListener;
import cn.true123.lottery.utils.LotteryUtils;

public class SlidingActivity extends AppCompatActivity implements SlidingListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sliding_layout);
        boolean isFirst = LotteryUtils.getBooleanFromSharePreferences(this, "isFirst", true);
        if (isFirst) {
            startSlide();
        } else {
            gotoMainActivity();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void startSlide() {
        SlidingFragment fragment = new SlidingFragment();
        fragment.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.sliding_content, fragment).commit();

    }

    @Override
    public void slidingFinish() {
        LotteryUtils.saveBooleanToSharePreferences(this, "isFirst", false);
        gotoMainActivity();
    }
}
