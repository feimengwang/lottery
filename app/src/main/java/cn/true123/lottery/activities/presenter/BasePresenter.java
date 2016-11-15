package cn.true123.lottery.activities.presenter;

/**
 * Created by junbo on 3/11/2016.
 */

public interface BasePresenter<T> {
    public void attach(T view);
    public void start();
}