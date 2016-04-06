package cn.true123.lottery;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                SettingActivity.this.finish();
            }
        });
        toolbar.setTitle("设置");
        toolbar.setTitleTextColor(Color.WHITE);
        getFragmentManager().beginTransaction().replace(R.id.setting_content, new SettingFragment()).commit();

        addToList(this);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.setting_layout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }
}
