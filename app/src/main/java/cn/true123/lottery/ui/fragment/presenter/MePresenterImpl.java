package cn.true123.lottery.ui.fragment.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import cn.true123.lottery.App;
import cn.true123.lottery.ui.base.presenter.BasePresenterImpl;
import cn.true123.lottery.ui.fragment.view.MeView;
import cn.true123.lottery.model.ApiVersion;
import cn.true123.lottery.data.LotteryServiceManager;
import cn.true123.lottery.utils.Constants;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by junbo on 11/11/2016.
 */

public class MePresenterImpl extends BasePresenterImpl<MeView> implements MePresenter<MeView> {
    @Override
    public void checkVersion() {
        check();
    }


    @Override
    public void start() {

    }

    public void check() {
        LotteryServiceManager.getInstance()
                .getLastVersion(Constants.API_TOKEN)
                .map(new Func1<ApiVersion, String>() {

                    @Override
                    public String call(ApiVersion apiVersion) {
                        return checkTheVersion(apiVersion);
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.fail();
            }

            @Override
            public void onNext(String s) {
                if (view != null) {
                    view.newVersion(s);
                }
            }


        });
    }

    private String checkTheVersion(ApiVersion o) {
        if (o != null) {
            String version = o.getVersion();
            if (version != null) {
                int intVersion = 0;
                try {
                    intVersion = Integer.parseInt(version);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PackageManager pm = App.getAppContext().getPackageManager();
                try {
                    PackageInfo info = pm.getPackageInfo(App.getAppContext().getPackageName(), 0);
                    int currentVC = info.versionCode;
                    if (intVersion > currentVC) {
                        return o.getInstall_url();
                    }

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
