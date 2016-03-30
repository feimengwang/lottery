package cn.true123.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.true123.lottery.adapter.HistoryAdapter;
import cn.true123.lottery.adapter.HistoryDetailAdapter;
import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.model.LotteryHistory;
import cn.true123.lottery.retrofit.LotteryServiceManager;
import cn.true123.lottery.utils.LotteryUtils;
import cn.true123.lottery.view.LotteryListView;
import rx.Subscriber;

public class HistoryFragment extends LotteryBaseFragment {

    private static final String TAG = "HistoryFragment";
    ListView listView;

    HistoryAdapter adapter;
    List<String> list = new ArrayList();
    OnFragmentChangeListener listener;



    public void setListener(OnFragmentChangeListener listener) {
        this.listener = listener;
    }


    public static HistoryFragment getInstance(OnFragmentChangeListener listener) {
        HistoryFragment fragment = new HistoryFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getName();
        adapter = new HistoryAdapter(list, getActivity());
        listView.setAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.history_layout, null);
        initView(view);
        return view;
    }
    private List getName(){
        Map nameId = LotteryUtils.getAllAvailable();
        if(nameId!=null && nameId.size()>0){
            Set keySet = nameId.keySet();
            list.addAll(keySet);
        }
        return list;
    }
    private void initView(View v) {


        listView = (ListView) v.findViewById(R.id.list_history);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lotName = list.get(position);
                if(listener!=null) {
                    listener.nextFragment(HistoryDetailFragment.getInstance(listener, LotteryUtils.getId(lotName)));
                }
            }
        });

    }


    @Override
    protected String getTAGName() {
        return TAG;
    }
}
