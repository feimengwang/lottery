package cn.true123.lottery.ui.fragment.presenter;

/**
 * Created by junbo on 1/11/2016.
 */

public interface BasePresenter<T> {
    public void attach(T view);
    public void start();
}
