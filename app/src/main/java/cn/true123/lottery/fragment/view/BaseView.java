package cn.true123.lottery.fragment.view;

import cn.true123.lottery.action.IAction;

/**
 * Created by junbo on 1/11/2016.
 */

public interface BaseView<T> {
    public void showProgress();
    public void dismissProgress();
    public void fail(String message);
    public void showDialog(String title, String message, IAction okAction,IAction noAction);
    public void dismissDialog();
}
