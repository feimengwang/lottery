package cn.true123.lottery;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import cn.true123.lottery.listener.OnFragmentChangeListener;

public class HistoryActivity extends AppCompatActivity implements OnFragmentChangeListener{


	private FragmentManager fragmentmanager;
	Toolbar toolbar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_history_layout);

		toolbar= (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitleTextColor(Color.WHITE);
		toolbar.setTitle("历史开奖");
		toolbar.setNavigationIcon(R.drawable.go_back);
		toolbar.setNavigationOnClickListener(clickListener);

		fragmentmanager = getSupportFragmentManager();
		nextFragment(HistoryFragment.getInstance(this));
	}
	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(HistoryActivity.this,MainActivity.class);
			HistoryActivity.this.startActivity(intent);
			HistoryActivity.this.finish();
		}
	};

	@Override
	public void updateTitle(String title) {
		toolbar.setTitle(title);
	}

	@Override
	public void updateNavigationOnClickLister(OnClickListener listener) {
		toolbar.setNavigationOnClickListener(listener);
	}

	@Override
	public void updateIcon(int resId) {
		toolbar.setNavigationIcon(resId);
	}

	@Override
	public void nextFragment(LotteryBaseFragment fragment) {
			if(fragment==null){
				fragment = HistoryFragment.getInstance(this);
				toolbar.setTitle("历史开奖");
				toolbar.setNavigationOnClickListener(clickListener);
		}
		fragmentmanager.beginTransaction().replace(R.id.lay_content, fragment).commit();

	}

}
