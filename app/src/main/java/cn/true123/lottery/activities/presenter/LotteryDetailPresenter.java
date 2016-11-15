package cn.true123.lottery.activities.presenter;

import cn.true123.lottery.activities.view.ILotteryDetailView;

/**
 * Created by junbo on 3/11/2016.
 */

public interface LotteryDetailPresenter<T extends ILotteryDetailView> extends BasePresenter<T> {
    void setIssueAndName(String issue,String name);
}
