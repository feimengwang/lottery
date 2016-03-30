package cn.true123.lottery;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import cn.true123.lottery.utils.LotteryUtils;

public class RuleActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleActivity.this, MainActivity.class);
                startActivity(intent);
                RuleActivity.this.finish();
            }
        });
        toolbar.setTitle(LotteryUtils.getStringRes(this,R.string.rule));
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportFragmentManager().beginTransaction().replace(R.id.setting_content, new RuleFragment()).commit();
//
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.about_layout);
//		back = (Button) findViewById(R.id.about_back);
//		TextView title = (TextView) findViewById(R.id.title_text);
//		title.setText("陶陶彩票使用说明");
//		back.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				SettingActivity.this.finish();
//			}
//		});

    }
}
