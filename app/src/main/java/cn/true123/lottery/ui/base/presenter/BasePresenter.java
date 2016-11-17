package cn.true123.lottery.ui.base.presenter;

/**
 * Created by junbo on 16/11/2016.
 */

public interface BasePresenter<T> {
    public void attach(T view);

    public void start();

    public void destory();

}