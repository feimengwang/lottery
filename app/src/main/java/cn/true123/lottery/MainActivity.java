package cn.true123.lottery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.true123.lottery.application.LotteryApplication;
import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.utils.LotteryUtils;

public class MainActivity extends BaseActivity implements OnFragmentChangeListener {
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    FragmentManager fragmentmanager;
    private long preBackClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.logo);
        fragmentmanager = getSupportFragmentManager();
        // setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.ic_menu_more_overflow));
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.setting:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.rule:
                        Intent intentRule = new Intent(MainActivity.this, RuleActivity.class);
                        MainActivity.this.startActivity(intentRule);
                        break;

                    case R.id.history:
                        Intent intent1History = new Intent(MainActivity.this, HistoryActivity.class);
                        MainActivity.this.startActivity(intent1History);
                        break;
                }
                return true;
            }
        });

        nextFragment(LastNumFragment.getInstance(this));

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long curBackClickTime = System.currentTimeMillis();
        Log.i(TAG,"curBackClickTime="+curBackClickTime);
        Log.i(TAG,"preBackClickTime="+preBackClickTime);
        Log.i(TAG,"keyCode="+keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           String tag= LotteryApplication.pop(this);
            if(tag!=null)nextFragment(tag);
            if(tag==null && preBackClickTime - curBackClickTime > 1 * 1000){
                Snackbar.make(this.getWindow().getDecorView(),"",Snackbar.LENGTH_SHORT).show();
            }else if(tag==null && preBackClickTime - curBackClickTime < 1 * 1000) {
                this.finish();
            }
            preBackClickTime=System.currentTimeMillis();

        }
        return true;
    }

    @Override
    public void updateTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateNavigationOnClickLister(View.OnClickListener listener) {
        if (listener != null) {
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    @Override
    public void updateIcon(int resId) {
        toolbar.setNavigationIcon(resId);
    }

    @Override
    public void nextFragment(LotteryBaseFragment fragment) {
        if (fragment == null) {

            fragment = LastNumFragment.getInstance(this);
        }

        LotteryApplication.add(this,fragment.getTAGName());

        fragmentmanager.beginTransaction().replace(R.id.lay_content, fragment,fragment.getTAGName()).addToBackStack(fragment.getTAGName()).commit();

    }

    public void nextFragment(String tag ) {
        LotteryBaseFragment fragment =null;
        if (tag != null) {

            fragment = (LotteryBaseFragment) fragmentmanager.findFragmentByTag(tag);

        }
        if(fragment!=null) {
            fragmentmanager.beginTransaction().replace(R.id.lay_content, fragment, fragment.getTAGName()).commit();
        }

    }
}
