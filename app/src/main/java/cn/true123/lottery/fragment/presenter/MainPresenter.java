package cn.true123.lottery.fragment.presenter;

import cn.true123.lottery.fragment.view.MainView;

/**
 * Created by junbo on 1/11/2016.
 */

public interface MainPresenter<MainView> extends BasePresenter<MainView> {
    public void refresh();
}
