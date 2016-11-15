package cn.true123.lottery.fragment.view;


import java.util.List;

/**
 * Created by junbo on 7/11/2016.
 */

public interface HistoryView<K> extends BaseView {
    public void update(List<K> data);
}
