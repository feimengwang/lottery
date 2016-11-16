package cn.true123.lottery.ui.activities.presenter;

import android.content.Context;

/**
 * Created by junbo on 15/11/2016.
 */

public interface RulePresenter<RuleView> extends BasePresenter<RuleView> {
    public void loadRule(Context context);
}
