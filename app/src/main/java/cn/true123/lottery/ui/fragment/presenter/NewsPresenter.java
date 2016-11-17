package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BaseFailPresenter;
import cn.true123.lottery.ui.base.presenter.BasePresenter;
import cn.true123.lottery.ui.fragment.view.NewsView;

/**
 * Created by junbo on 14/11/2016.
 */

public interface NewsPresenter<T extends NewsView> extends BaseFailPresenter<T> {
    public void loadMore();
    public void refresh();
}
