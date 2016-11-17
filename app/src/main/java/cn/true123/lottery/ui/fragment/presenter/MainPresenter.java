package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BaseFailPresenter;
import cn.true123.lottery.ui.fragment.view.MainView;

/**
 * Created by junbo on 1/11/2016.
 */

public interface MainPresenter<T extends MainView> extends BaseFailPresenter<T> {
    public void refresh();
}
