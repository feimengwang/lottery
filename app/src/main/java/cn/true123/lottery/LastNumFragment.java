package cn.true123.lottery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.adapter.LastNumAdapter;
import cn.true123.lottery.retrofit.LotteryServiceManager;
import cn.true123.lottery.utils.LotteryUtils;
import rx.Subscriber;

public class LastNumFragment extends LotteryBaseFragment {

    private static final String TAG="LastNumFragment";
    ListView listView;
    LastNumAdapter adapter;
    List<Lottery.IEntity> list = null;
    OnFragmentChangeListener listener;
    SwipeRefreshLayout refresher;
    //PopupWindow popupWindow;
    //LinearLayout detail, history;
   // boolean popuWindowShowing = false;
    int currentposition = -1;


    public void setListener(OnFragmentChangeListener listener) {
        this.listener = listener;
    }


    public static LastNumFragment getInstance(OnFragmentChangeListener listener) {
        LastNumFragment fragment = new LastNumFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (listener!=null){
            listener.updateIcon(R.drawable.logo);
            listener.updateTitle("彩票最新开奖");
            listener.updateNavigationOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v){
//                    if(listener!=null){
//                        listener.nextFragment("last");
//                    }
                }
            });
        }
        refresh();
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
                R.layout.last_num_layout, null);
        initView(view);
        list= new ArrayList();
        adapter = new LastNumAdapter(list, getActivity());
        listView.setAdapter(adapter);
        return view;
    }

    private void initView(View v) {
        refresher = (SwipeRefreshLayout) v.findViewById(R.id.refresher);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        listView = (ListView) v.findViewById(R.id.list_kj);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getActivity(), "list.size()=" + list.size() + ";" + position, Toast.LENGTH_SHORT).show();
                Lottery.IEntity iEntity = list.get(position);
               listener.nextFragment(DetailFragment.getInstance(listener, LotteryUtils.getId(iEntity),iEntity.getIssue()));

            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Lottery.IEntity iEntity = list.get(position);
                listener.nextFragment(HistoryDetailFragment.getInstance(listener, LotteryUtils.getId(iEntity)));
                return true;
            }
        });

    }

    private void refresh() {
        refresher.setRefreshing(true);
        if(!LotteryUtils.checkNetwork(getActivity())){
            Snackbar.make(getView(),"无法连接网络，请检查网络！",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(!LotteryUtils.checkIfShouldUseNetwork(getActivity())){
            Snackbar.make(getView(),"请检查App系统设置，！",Snackbar.LENGTH_SHORT).show();
            return;
        }
        LotteryServiceManager.instance(getActivity()).getLastData360(new Subscriber<List<Lottery.IEntity>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted=");
                refreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"Throwable="+e.getLocalizedMessage());
                refreshing(false);
            }

            @Override
            public void onNext(List<Lottery.IEntity> o) {
               Log.i(TAG,"onNext="+o.size());
                update(o);
            }
        });

    }


    public void update(List<Lottery.IEntity> l) {
        Log.i("SS","size="+l.size());
        if(l!=null && l.size()>0){
            if(list!=null)list.clear();
            list.addAll(l);

            adapter.updateList(list);
            adapter.notifyDataSetChanged();
        }
        refreshing(false);
    }

    private void refreshing(boolean needRefresh){
        if(refresher.isRefreshing() &&! needRefresh){
            refresher.setRefreshing(false);
        }else if(!refresher.isRefreshing() && needRefresh){
            refresher.setRefreshing(true);
        }
    }
 private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String getTAGName() {
        return TAG;
    }
}
