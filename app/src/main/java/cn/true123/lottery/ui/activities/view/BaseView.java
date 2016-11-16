package cn.true123.lottery.ui.activities.view;

/**
 * Created by junbo on 3/11/2016.
 */

public interface BaseView<T> {
    void update(T data);
    void showDialog();
    void dismissDialog();
}
