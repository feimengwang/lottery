package cn.true123.lottery.ui.fragment.view;

import java.util.List;

import cn.true123.lottery.ui.base.view.BaseView;

/**
 * Created by junbo on 7/11/2016.
 */

public interface ConcreteLotteryView<K> extends BaseView {
    public void update(List<K> list, boolean isAdd);
}
