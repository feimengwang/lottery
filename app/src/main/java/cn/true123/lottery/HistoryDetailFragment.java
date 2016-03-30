package cn.true123.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.model.LotteryHistory;
import cn.true123.lottery.adapter.HistoryDetailAdapter;
import cn.true123.lottery.retrofit.LotteryServiceManager;
import cn.true123.lottery.view.LotteryListView;
import rx.Subscriber;

public class HistoryDetailFragment extends LotteryBaseFragment {

    private static final String TAG = "HistoryDetailFragment";
    LotteryListView listView;
    SwipeRefreshLayout refreshLayout;
    HistoryDetailAdapter adapter;
    List list = new ArrayList();
    OnFragmentChangeListener listener;
    private String lotId;
    int currentPage = 1;
    int pageCount = 10;

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }


    public void setListener(OnFragmentChangeListener listener) {
        this.listener = listener;
    }


    public static HistoryDetailFragment getInstance(OnFragmentChangeListener listener, String lotId) {
        HistoryDetailFragment fragment = new HistoryDetailFragment();
        fragment.setListener(listener);
        fragment.setLotId(lotId);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //list = new ArrayList();
        if (listener != null) {
            listener.updateIcon(R.drawable.go_back);
            listener.updateTitle(cn.true123.lottery.utils.LotteryUtils.getName(lotId) + "历史开奖");
            listener.updateNavigationOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.nextFragment(null);
                    }
                }
            });
        }
        adapter = new HistoryDetailAdapter(lotId,list, getActivity());
        listView.setAdapter(adapter);
        refresh();
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void refresh() {
        if (currentPage > pageCount) {
            listView.setScrollEnd();
            return;
        }
        LotteryServiceManager.instance(getActivity()).getHistory360(new Subscriber<LotteryHistory>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LotteryHistory o) {
                pageCount = Integer.parseInt(o.getPageCount());
                update(o);
            }
        }, lotId, currentPage + "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.history_detail_layout, null);
        initView(view);
        return view;
    }

    private void initView(View v) {
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.history_refresher);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage++;
                refresh();

            }
        });

        listView = (LotteryListView) v.findViewById(R.id.list_detail_history);
        listView.addFooterView(null);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listView.getLastVisiblePosition() == list.size() - 2) {
                    currentPage++;
                    refresh();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LotteryHistory.ListEntity entity = (LotteryHistory.ListEntity) list.get(position);
                if(listener!=null){
                    listener.nextFragment(DetailFragment.getInstance(listener,lotId,entity.getIssue()));
                }
            }
        });
        listView.setListener(new LotteryListView.ListViewFooterOnClickListener() {
            @Override
            public void onClick() {
                currentPage++;
                refresh();
            }
        });
    }


    public void update(LotteryHistory lotteryHistory) {

        if (lotteryHistory != null) {
            //if(list!=null)list.clear();
            //if(list.size()>10)list.remove(list.size()-1);
            list.addAll(lotteryHistory.getList());
            adapter.updateList(list);
            adapter.notifyDataSetChanged();
        }


    }


    @Override
    protected String getTAGName() {
        return TAG;
    }
}
