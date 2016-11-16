package cn.true123.lottery.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.true123.lottery.R;


/**
 * Created by junbo on 3/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity  {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initIntent();

        initPresenter();
        iniView();

    }

    protected abstract void initIntent();

    protected abstract void initPresenter();

    protected abstract void iniView();

    protected abstract int getLayoutResId();

    public String getResString(int resId){
        return getResources().getString(resId);
    }
    public String getResString(int resId,Object... args){
        return getResources().getString(resId,args);
    }

}
