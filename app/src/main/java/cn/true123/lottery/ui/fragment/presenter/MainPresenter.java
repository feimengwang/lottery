package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BasePresenter;

/**
 * Created by junbo on 1/11/2016.
 */

public interface MainPresenter<MainView> extends BasePresenter<MainView> {
    public void refresh();
}
