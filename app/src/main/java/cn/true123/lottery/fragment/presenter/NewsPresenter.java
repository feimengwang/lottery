package cn.true123.lottery.fragment.presenter;

/**
 * Created by junbo on 14/11/2016.
 */

public interface NewsPresenter<NewsView> extends BasePresenter<NewsView> {
    public void loadMore();
    public void refresh();
}
