package cn.true123.lottery.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.true123.lottery.App;
import cn.true123.lottery.R;
import cn.true123.lottery.ui.activities.LotteryDetailActivity;
import cn.true123.lottery.ui.fragment.adapter.MainFragmentAdapter;
import cn.true123.lottery.ui.fragment.base.BaseFragment;
import cn.true123.lottery.ui.fragment.listener.OnItemClickListener;
import cn.true123.lottery.ui.fragment.presenter.MainPresenter;
import cn.true123.lottery.ui.fragment.presenter.MainPresenterImpl;
import cn.true123.lottery.ui.fragment.view.DividerDecoration;
import cn.true123.lottery.ui.fragment.view.MainView;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.utils.LotteryUtils;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 1/11/2016.
 */

public class MainFragment extends BaseFragment<MainPresenter> implements MainView, OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;

    MainFragmentAdapter adapter;
    List<Lottery.IEntity> data;

    public static MainFragment getInstance() {

        return new MainFragment();
    }

    @Override
    protected void initView() {
        if (data == null) data = new ArrayList<>();
        adapter = new MainFragmentAdapter(data, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerDecoration(getActivity(), DividerDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                presenter.refresh();
            }
        });
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public String getTitle() {
        return App.getAppContext().getString(R.string.last_lottery);
    }

    @Override
    public void update(List list) {
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String message) {

    }

    @Override
    public void onItemClick(View view, long position) {
        Lottery.IEntity entity = data.get((int) position);
        startDetailActivity(entity);
    }

    @Override
    public void onFooterClick(View v, int layoutPosition) {

    }

    private void startDetailActivity(Lottery.IEntity entity) {
        Intent intent = new Intent(getActivity(), LotteryDetailActivity.class);
        Bundle b = new Bundle();
        MLog.i("===+"+entity.getIssue()+";"+LotteryUtils.getId(entity));
        b.putString("issue", entity.getIssue());
        b.putString("lotId", LotteryUtils.getId(entity));
        intent.putExtras(b);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
