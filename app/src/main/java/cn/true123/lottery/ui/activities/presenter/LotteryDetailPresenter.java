package cn.true123.lottery.ui.activities.presenter;

import cn.true123.lottery.ui.activities.view.ILotteryDetailView;
import cn.true123.lottery.ui.base.presenter.BasePresenter;

/**
 * Created by junbo on 3/11/2016.
 */

public interface LotteryDetailPresenter<T extends ILotteryDetailView> extends BasePresenter<T> {
    void setIssueAndName(String issue,String name);
}
