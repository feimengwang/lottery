package cn.true123.lottery.ui.base.presenter;

import cn.true123.lottery.ui.base.view.BaseView;

/**
 * Created by junbo on 17/11/2016.
 */

public interface BaseFailPresenter<T extends BaseView> extends BasePresenter<T> {

    public void retry();
}
