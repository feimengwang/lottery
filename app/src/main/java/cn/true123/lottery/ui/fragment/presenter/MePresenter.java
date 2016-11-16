package cn.true123.lottery.ui.fragment.presenter;

import cn.true123.lottery.ui.base.presenter.BasePresenter;

/**
 * Created by junbo on 11/11/2016.
 */

public interface MePresenter<MeView> extends BasePresenter<MeView> {
    public void checkVersion();
}
