package cn.true123.lottery.ui.fragment.view;

import java.util.List;

/**
 * Created by junbo on 7/11/2016.
 */

public interface ConcreteLotteryView<K> extends BaseView {
    public void update(List<K> list, boolean isAdd);
}
