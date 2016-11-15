package cn.true123.lottery.fragment.presenter;

import java.util.List;

import cn.true123.lottery.fragment.view.ConcreteLotteryView;

/**
 * Created by junbo on 7/11/2016.
 */

public interface ConcreteLotteryHistoryPresenter<T extends ConcreteLotteryView> extends BasePresenter<T> {
    public void refresh();
    public void loadMore();
}
