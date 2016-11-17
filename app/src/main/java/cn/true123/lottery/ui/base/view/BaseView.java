package cn.true123.lottery.ui.base.view;

import java.util.List;

import cn.true123.lottery.model.ILottery;

/**
 * Created by junbo on 16/11/2016.
 */

public interface BaseView<T extends ILottery> {
    public void showDialog();

    public void dismissDialog();

    public void update(List<T> list);
    public void fail();
}

