package cn.true123.lottery.activities.presenter;

import cn.true123.lottery.activities.view.ILotteryDetailView;
import cn.true123.lottery.model.LotteryDetail;
import cn.true123.lottery.data.LotteryServiceManager;
import mlog.true123.cn.lib.MLog;
import rx.Subscriber;

/**
 * Created by junbo on 3/11/2016.
 */

public class LotteryDetailPresenterImpl<T extends ILotteryDetailView> extends BasePresenterImpl<T> implements LotteryDetailPresenter<T> {
    String lotId;
    String issue;

    @Override
    public void start() {
        super.start();
        fetchData();
    }

    void fetchData() {
        LotteryServiceManager.getInstance().getLotteryDetail(new Subscriber<LotteryDetail>() {
            @Override
            public void onCompleted() {

                MLog.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            MLog.e(this,e.getMessage());
            }

            @Override
            public void onNext(LotteryDetail o) {
                MLog.i(o.toString());
                view.update(o);
            }
        }, lotId, issue);
    }

    @Override
    public void setIssueAndName(String issue, String name) {
        MLog.i("===="+issue+name);
        this.issue = issue;
        lotId = name;
    }
}
