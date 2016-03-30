package cn.true123.lottery.listener;

import android.support.v4.app.Fragment;
import android.view.View;

import cn.true123.lottery.LotteryBaseFragment;

/**
 * Created by feimeng0530 on 2016/3/21.
 */
public interface OnFragmentChangeListener {
    public void updateTitle(String title);
    public void updateNavigationOnClickLister(View.OnClickListener listener);
    public void updateIcon(int resId);
    public void nextFragment(LotteryBaseFragment fragment);
}
