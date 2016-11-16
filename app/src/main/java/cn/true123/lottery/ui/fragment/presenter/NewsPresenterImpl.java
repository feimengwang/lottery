package cn.true123.lottery.ui.fragment.presenter;

import java.util.List;
import java.util.Map;

import cn.true123.lottery.ui.fragment.view.NewsView;
import cn.true123.lottery.model.News;
import cn.true123.lottery.data.NewsServiceManager;
import cn.true123.lottery.utils.Constants;
import mlog.true123.cn.lib.MLog;
import rx.Subscriber;

/**
 * Created by junbo on 14/11/2016.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView> implements NewsPresenter<NewsView> {
    int page = 0;
    int pageSize = 20;

    @Override
    public void loadMore() {
        load();
    }

    private void load() {
        NewsServiceManager.getInstance()
                .getNews("", Constants.HEADLINE_TYPE, Constants.LOTTERY_ID, getPage())
                .subscribe(new Subscriber<Map<String, List<News>>>() {
                    @Override
                    public void onCompleted() {
                        MLog.i("OnCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.i("onError"+e.getMessage());
                    }

                    @Override
                    public void onNext(Map<String, List<News>> stringListMap) {
                        MLog.i("onNext"+stringListMap);
                        if (view != null) {
                            view.update(stringListMap.get(Constants.LOTTERY_ID), page > 0);
                        }
                        page++;
                    }
                });
    }

    private int getPage() {
        return page * pageSize;
    }

    @Override
    public void refresh() {
        page = 0;
        load();
    }


    @Override
    public void start() {
        load();
    }
}
