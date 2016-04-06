package cn.true123.lottery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import cn.true123.lottery.application.LotteryApplication;
import cn.true123.lottery.listener.OnFragmentChangeListener;

public class MainActivity extends BaseActivity implements OnFragmentChangeListener {
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    FragmentManager fragmentmanager;
    private long preBackClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fragmentmanager = getSupportFragmentManager();

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
        addToList(this);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
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

        LotteryApplication.add(this, fragment.getTAGName());

        fragmentmanager.beginTransaction().replace(R.id.lay_content, fragment, fragment.getTAGName()).addToBackStack(fragment.getTAGName()).commit();

    }

    public void nextFragment(String tag) {
        LotteryBaseFragment fragment = null;
        if (tag != null) {

            fragment = (LotteryBaseFragment) fragmentmanager.findFragmentByTag(tag);

        }
        if (fragment != null) {
            fragmentmanager.beginTransaction().replace(R.id.lay_content, fragment, fragment.getTAGName()).commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }
}
