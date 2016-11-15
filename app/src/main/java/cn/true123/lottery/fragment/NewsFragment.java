package cn.true123.lottery.fragment;

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
import cn.true123.lottery.R;
import cn.true123.lottery.activities.NewsDetailActivity;
import cn.true123.lottery.fragment.adapter.NewsFragmentAdapter;
import cn.true123.lottery.fragment.base.BaseFragment;
import cn.true123.lottery.fragment.listener.OnItemClickListener;
import cn.true123.lottery.fragment.presenter.NewsPresenter;
import cn.true123.lottery.fragment.presenter.NewsPresenterImpl;
import cn.true123.lottery.fragment.view.NewsView;
import cn.true123.lottery.model.News;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 14/11/2016.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsView<News>, OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<News> data = new ArrayList<>();
    NewsFragmentAdapter adapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initView() {
        adapter = new NewsFragmentAdapter(data, getActivity());
        adapter.setFooter(true);
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DivItemDecoration(DivItemDecoration.VERTICAL, 6, getResources().getColor(R.color.grey)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });


    }

    @Override
    protected NewsPresenter getPresenter() {
        return new NewsPresenterImpl();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public String getTitle() {
        return "彩票资讯";
    }

    @Override
    public void update(List<News> list, boolean isAdd) {
        if (!isAdd) {
            data.clear();

        }
        data.addAll(list);
        adapter.notifyDataSetChanged();
        adapter.setFlushing(false);
        if (list == null && list.size() == 0) {
            adapter.onEnd(true);
        }
    }

    @Override
    public void onItemClick(View view, long position) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", data.get((int) position).getUrl());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onFooterClick(View v, int layoutPosition) {
        MLog.i("loading  more...");
        presenter.loadMore();
    }
}
