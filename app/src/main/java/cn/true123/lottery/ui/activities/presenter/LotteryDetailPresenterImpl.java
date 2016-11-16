package cn.true123.lottery.ui.activities.presenter;

import java.util.ArrayList;
import java.util.List;

import cn.true123.lottery.data.LotteryServiceManager;
import cn.true123.lottery.model.LotteryDetail;
import cn.true123.lottery.ui.activities.view.ILotteryDetailView;
import cn.true123.lottery.ui.base.presenter.BasePresenterImpl;
import mlog.true123.cn.lib.MLog;
import rx.Subscriber;
import rx.functions.Func1;


/**
 * Created by junbo on 3/11/2016.
 */

public class LotteryDetailPresenterImpl<T extends ILotteryDetailView> extends BasePresenterImpl<ILotteryDetailView> implements LotteryDetailPresenter<ILotteryDetailView> {
    String lotId;
    String issue;

    @Override
    public void start() {
        super.start();
        fetchData();
    }

    void fetchData() {
        LotteryServiceManager.getInstance().getLotteryDetail(lotId, issue)
                .map(new Func1<LotteryDetail, List<LotteryDetail>>() {
                    @Override
                    public List<LotteryDetail> call(LotteryDetail lotteryDetail) {
                        List list = new ArrayList();
                        list.add(lotteryDetail);
                        return list;
                    }
                }).subscribe(new Subscriber<List<LotteryDetail>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<LotteryDetail> lotteryDetails) {
                view.update(lotteryDetails);
            }
        });
    }

    @Override
    public void setIssueAndName(String issue, String name) {
        MLog.i("====" + issue + name);
        this.issue = issue;
        lotId = name;
    }
}
