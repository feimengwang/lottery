package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BasePresenter;

/**
 * Created by junbo on 14/11/2016.
 */

public interface NewsPresenter<NewsView> extends BasePresenter<NewsView> {
    public void loadMore();
    public void refresh();
}
