package cn.true123.lottery.ui.activities.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.ui.activities.view.RuleView;
import cn.true123.lottery.model.Rule;
import mlog.true123.cn.lib.MLog;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by junbo on 15/11/2016.
 */

public class RulePresenterImpl extends BasePresenterImpl<RuleView> implements RulePresenter<RuleView> {
    Context context;
    List<Rule> ruleList = new ArrayList<>();

    @Override
    public void loadRule(Context context) {
        this.context = context;
        Observable.create(new Observable.OnSubscribe<List<Rule>>() {

            @Override
            public void call(Subscriber<? super List<Rule>> subscriber) {
                subscriber.onNext(read());
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Rule>>() {
            @Override
            public void onCompleted() {
                MLog.i("onC");
            }

            @Override
            public void onError(Throwable e) {
                MLog.i("onE"+e.getMessage());
            }

            @Override
            public void onNext(List<Rule> rules) {
                MLog.i("onN"+rules.size());
                view.update(rules);
            }
        });
    }

    List<Rule> read() {
        if (ruleList.size() > 0)
            return ruleList;

        TypedArray ta = context.getResources().obtainTypedArray(R.array.rule_name);
        if (ta != null) {
            for (int i = 0; i < ta.length(); i++) {
                String name = context.getResources().getString(ta.getResourceId(i, 0));
                Rule rule = new Rule();
                rule.setName(name);
                ruleList.add(i, rule);

            }
        }
        TypedArray ruleContent = context.getResources().obtainTypedArray(R.array.rule);
        if (ruleContent != null) {
            for (int j = 0; j < ruleContent.length(); j++) {
                InputStream in = context.getResources().openRawResource(
                        (ruleContent.getResourceId(j, 0)));
                BufferedReader br;
                try {
                    br = new BufferedReader(new InputStreamReader(in, "utf-8"));

                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line.trim() + "\n");
                    }
                    Rule rule = ruleList.get(j);
                    rule.setContent(sb.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ruleList;
    }
}
