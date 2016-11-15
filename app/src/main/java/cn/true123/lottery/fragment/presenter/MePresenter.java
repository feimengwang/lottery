package cn.true123.lottery.fragment.presenter;

import android.content.Context;

/**
 * Created by junbo on 11/11/2016.
 */

public interface MePresenter<MeView> extends BasePresenter<MeView> {
    public void checkVersion();
}
