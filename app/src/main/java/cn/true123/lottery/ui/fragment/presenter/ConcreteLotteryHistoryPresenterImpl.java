package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BaseFailPresenterImpl;
import cn.true123.lottery.ui.base.presenter.BasePresenterImpl;
import cn.true123.lottery.ui.fragment.view.ConcreteLotteryView;
import cn.true123.lottery.model.LotteryHistory;
import cn.true123.lottery.data.LotteryServiceManager;
import mlog.true123.cn.lib.MLog;
import rx.Subscriber;


/**
 * Created by junbo on 7/11/2016.
 */

public class ConcreteLotteryHistoryPresenterImpl extends BaseFailPresenterImpl<ConcreteLotteryView> implements ConcreteLotteryHistoryPresenter<ConcreteLotteryView> {
    int currentPage=1;
    int pageCount=10;
    String lotId;

    public ConcreteLotteryHistoryPresenterImpl(String lotId) {
        this.lotId = lotId;
        MLog.i("lotid="+lotId);
    }

    @Override
    public void start() {
        fetchData();
    }

    @Override
    public void refresh() {
        currentPage=1;
        fetchData();
    }

    @Override
    public void loadMore() {
        currentPage++;
        fetchData();
    }

    public void fetchData() {
        MLog.i("fetchdata");
        LotteryServiceManager.getInstance().getHistory360(new Subscriber<LotteryHistory>() {
            @Override
            public void onCompleted() {
                MLog.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                MLog.i("onError" + e.getMessage());
                view.fail();

            }

            @Override
            public void onNext(LotteryHistory o) {
                MLog.i("onNext" + o.getList());
                pageCount = Integer.parseInt(o.getPageCount());
                MLog.i("pc=" + pageCount);
                if (view != null) {
                    if (currentPage == 1) {
                        view.update(o.getList(), false);
                    } else {
                        view.update(o.getList(), true);
                    }
                }

            }
        }, lotId, currentPage + "");
    }


}
