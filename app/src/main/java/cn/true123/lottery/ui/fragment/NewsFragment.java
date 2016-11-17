package cn.true123.lottery.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.true123.lottery.R;
import cn.true123.lottery.model.News;
import cn.true123.lottery.ui.activities.NewsDetailActivity;
import cn.true123.lottery.ui.fragment.adapter.NewsFragmentAdapter;
import cn.true123.lottery.ui.fragment.base.BaseFailFragment;
import cn.true123.lottery.ui.fragment.listener.OnItemClickListener;
import cn.true123.lottery.ui.fragment.presenter.NewsPresenter;
import cn.true123.lottery.ui.fragment.presenter.NewsPresenterImpl;
import cn.true123.lottery.ui.fragment.view.NewsView;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 14/11/2016.
 */

public class NewsFragment extends BaseFailFragment<NewsPresenter> implements NewsView<News>, OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<News> data = new ArrayList<>();
    NewsFragmentAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        adapter = new NewsFragmentAdapter(data, getActivity());
        adapter.setFooter(true);
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DivItemDecoration(DivItemDecoration.VERTICAL, 6, getResources().getColor(R.color.grey)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected void onDataRefresh() {
        presenter.refresh();
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
        super.update(null);
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
