package cn.true123.lottery.ui.fragment.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.true123.lottery.ui.base.presenter.BasePresenterImpl;
import cn.true123.lottery.ui.fragment.view.HistoryView;
import cn.true123.lottery.model.LotteryItem;
import cn.true123.lottery.utils.LotteryUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by junbo on 7/11/2016.
 */

public class HistoryPresenterImpl extends BasePresenterImpl<HistoryView> implements HistoryPresenter<HistoryView> {

    @Override
    public void start() {
        fetchLotteries();
    }

    void fetchLotteries() {
        Observable.create(new Observable.OnSubscribe<Map>() {

            @Override
            public void call(Subscriber<? super Map> subscriber) {
                subscriber.onNext(LotteryUtils.getAllAvailable());
            }
        }).map(new Func1<Map, List>() {
            @Override
            public List call(Map map) {
                List result = new ArrayList();
                if (map != null && map.size() > 0) {
                    Iterator<String> iterator = map.keySet().iterator();
                    while (iterator.hasNext()) {
                        String name = iterator.next();
                        String id = (String) map.get(name);
                        result.add(new LotteryItem(id, name, true));
                    }
                }
                return result;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List list) {
                        if (view != null) {
                            view.update(list);
                        }
                    }
                })
        ;


    }
}
