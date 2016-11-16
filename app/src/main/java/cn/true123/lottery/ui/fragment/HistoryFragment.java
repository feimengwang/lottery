package cn.true123.lottery.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import cn.true123.lottery.App;
import cn.true123.lottery.R;
import cn.true123.lottery.adapter.HistoryAdapter;
import cn.true123.lottery.ui.fragment.base.BaseFragment;
import cn.true123.lottery.ui.fragment.presenter.BasePresenter;
import cn.true123.lottery.ui.fragment.presenter.HistoryPresenterImpl;
import cn.true123.lottery.ui.fragment.view.HistoryView;
import cn.true123.lottery.model.LotteryItem;

/**
 * Created by junbo on 4/11/2016.
 */

public class HistoryFragment extends BaseFragment implements HistoryView<LotteryItem> {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    HistoryAdapter adapter;
    List<BaseFragment> fragments = new ArrayList<>();

    public static HistoryFragment getInstance() {
        return new HistoryFragment();
    }

    @Override
    protected void initView() {
        adapter = new HistoryAdapter(getActivity().getSupportFragmentManager(), fragments, getActivity());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected BasePresenter getPresenter() {
        presenter = new HistoryPresenterImpl();
        return presenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    public String getTitle() {
        return App.getAppContext().getString(R.string.lottery_history);
    }


    @Override
    public void fail(String message) {

    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(List<LotteryItem> data) {
        if (data != null && data.size() > 0) {
            Iterator<LotteryItem> it = data.iterator();
            while (it.hasNext()) {
                LotteryItem item = it.next();
                fragments.add(ConcreteLotteryHistoryFragment.getInstance(item.getId(), item.getName()));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
