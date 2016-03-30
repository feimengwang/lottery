package cn.true123.lottery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.model.LotteryDetail;
import cn.true123.lottery.retrofit.LotteryServiceManager;
import cn.true123.lottery.utils.LotteryUtils;
import cn.true123.lottery.view.KJLineItemView;
import cn.true123.lottery.view.LotteryDetailView;
import rx.Subscriber;

public class DetailFragment extends LotteryBaseFragment {
    private static final String TAG = "DetailFragment";
    private String lotId;
    private String issue;
    private TextView tName, tPhase, tTime, tPool, tSale;
    private LotteryDetailView detail;
    private KJLineItemView item;
    OnFragmentChangeListener listener;

    public void setLotteryId(String lotId) {
        this.lotId = lotId;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setListener(OnFragmentChangeListener listener) {
        this.listener = listener;
    }

    public static DetailFragment getInstance(OnFragmentChangeListener listener,
                                             String lotId, String issue) {
        DetailFragment fragment = new DetailFragment();
        Log.i(TAG, "new Detail=" + lotId + ";" + issue);
        //fragment.setContext(context);
        fragment.setIssue(issue);
        fragment.setListener(listener);
        fragment.setLotteryId(lotId);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (listener != null) {
            listener.updateIcon(R.drawable.go_back);
            listener.updateTitle(getResString(R.string.lottery_issue2,LotteryUtils.getName(lotId) , issue ));
            listener.updateNavigationOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.nextFragment(null);
                    }
                }
            });
        }
        Log.i(TAG, "start RX" + new Date().getTime());
        if(!LotteryUtils.checkNetwork(getActivity())){
            Snackbar.make(getView(),"无法连接网络，请检查网络！",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(!LotteryUtils.checkIfShouldUseNetwork(getActivity())){
            Snackbar.make(getView(),"请检查App系统设置，！",Snackbar.LENGTH_SHORT).show();
            return;
        }
        LotteryServiceManager.instance(getActivity()).getLotteryDetail(new Subscriber<LotteryDetail>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted=");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError=Throwable" + e.getLocalizedMessage());
            }

            @Override
            public void onNext(LotteryDetail o) {
                Log.i(TAG, "onNext=" + o.toString());
                update(o);
            }
        }, lotId, issue);
        Log.i(TAG, "end RX" + new Date().getTime());
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
                R.layout.detail_layout, null);
        initView(view);
        return view;
    }

    private void initView(View v) {
        tName = (TextView) v.findViewById(R.id.text_detail_name);
        tPhase = (TextView) v.findViewById(R.id.text_detail_phase);
        tPool = (TextView) v.findViewById(R.id.text_detail_pool);
        tSale = (TextView) v.findViewById(R.id.text_detail_sale);
        tTime = (TextView) v.findViewById(R.id.text_detail_timedraw);
        detail = (LotteryDetailView) v.findViewById(R.id.detail_detail);
        item = (KJLineItemView) v.findViewById(R.id.view_detail_ball);
    }
    public String getResString(int resId){
       return getResources().getString(resId);
    }
    public String getResString(int resId,Object... args){
        return getResources().getString(resId,args);
    }
    /**
     *
     * @param lotteryDetail
     */
    public void update(final LotteryDetail lotteryDetail) {

        Log.i(TAG, "update=");
        tName.setText(lotteryDetail.getLotName());
        tPhase.setText(getResString(R.string.lottery_issue,lotteryDetail.getIssue()));
        tPool.setText(getResString(R.string.lottery_pool,lotteryDetail.getMoney()));
        tSale.setText(getResString(R.string.lottery_sale,lotteryDetail.getSale()));
        tTime.setText(getResString(R.string.lottery_time,lotteryDetail.getDate()));
        List lotteryDetailLevel = lotteryDetail.getLevel();
        if (lotteryDetailLevel != null) lotteryDetailLevel.add(0, new LotteryDetail.LevelEntity(getResString(R.string.lottery_item),getResString(R.string.lottery_count),getResString(R.string.lottery_num)));
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        detail.setList(lotteryDetailLevel, outMetrics.widthPixels);
        item.setWidthHeight(outMetrics.widthPixels, outMetrics.heightPixels);
        item.addViewList(LotteryUtils.getBall(new Lottery.IEntity() {
            @Override
            public String getLotName() {
                return lotteryDetail.getLotName();
            }

            @Override
            public String getIssue() {
                return null;
            }

            @Override
            public String getBalls() {
                return lotteryDetail.getCode();
            }

            @Override
            public String getDate() {
                return null;
            }
        }));
    }


    @Override
    protected String getTAGName() {
        return TAG;
    }
}
