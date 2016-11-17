package cn.true123.lottery.ui.fragment.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.true123.lottery.R;
import cn.true123.lottery.ui.base.presenter.BaseFailPresenter;
import cn.true123.lottery.ui.base.presenter.BasePresenter;

/**
 * Created by junbo on 17/11/2016.
 */

public abstract class BaseFailFragment<K extends BaseFailPresenter> extends BaseFragment {
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.viewStub)
    ViewStub viewStub;
    public K presenter;

    public void setViewStubGone() {
        viewStub.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black)
                , getResources().getColor(R.color.blue), getResources().getColor(R.color.red));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                onDataRefresh();
            }
        });
    }
    @Override
    protected void initPresenter() {
        presenter = getPresenter();
        if (presenter != null) {
            presenter.attach(this);
            presenter.start();
        }
    }

    protected abstract K getPresenter();

    protected abstract void onDataRefresh();


    @Override
    public void fail() {

        swipeRefreshLayout.setRefreshing(false);
       View v= viewStub.inflate();
        if(v!=null){
            TextView textView= (TextView) v.findViewById(R.id.retryTextView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.retry();
                }
            });
        }

    }

    @Override
    public void update(List list) {
        swipeRefreshLayout.setRefreshing(false);
        setViewStubGone();
    }
}
