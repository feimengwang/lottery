package cn.true123.lottery;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("关于本软件");
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AboutActivity.this, SettingActivity.class);
                AboutActivity.this.startActivity(intent);
            }
        });
        addToList(this);

    }

    @Override
    public int getContentViewResId() {
        return R.layout.about_layout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }
}
