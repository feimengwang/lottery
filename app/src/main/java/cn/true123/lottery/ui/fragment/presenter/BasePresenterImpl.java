package cn.true123.lottery.ui.fragment.presenter;


import cn.true123.lottery.ui.fragment.view.BaseView;

/**
 * Created by junbo on 1/11/2016.
 */

public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    T view;

    @Override
    public void attach(T view) {
        this.view = view;
    }

}
