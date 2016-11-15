package cn.true123.lottery.activities.presenter;

import cn.true123.lottery.activities.view.BaseView;

/**
 * Created by junbo on 3/11/2016.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    T view;

    @Override
    public void attach(T view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
