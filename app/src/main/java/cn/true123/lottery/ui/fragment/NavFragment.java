package cn.true123.lottery.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.true123.lottery.R;
import cn.true123.lottery.ui.base.presenter.BasePresenter;
import cn.true123.lottery.ui.fragment.base.BaseFragment;
import cn.true123.lottery.listener.OnTagChangedListener;
import cn.true123.lottery.widget.NavButton;

/**
 * Created by junbo on 8/11/2016.
 */

public class NavFragment extends BaseFragment {
    @BindView(R.id.lottery)
    NavButton lottery;

    @BindView(R.id.history)
    NavButton history;
    @BindView(R.id.rule)
    NavButton rule;
    @BindView(R.id.me)
    NavButton me;
    FragmentManager fragmentManager;
    int contentId;
    Context context;
    NavButton currentButton;

    OnTagChangedListener onTagChangedListener;

    public void setOnTagChangedListener(OnTagChangedListener onTagChangedListener) {
        this.onTagChangedListener = onTagChangedListener;
    }

    @Override
    protected void initView() {
        lottery.initView(R.drawable.lottery_bottom, R.string.lottery_bottom, MainFragment.class.getName());
        history.initView(R.drawable.history_bottom, R.string.history_bottom, HistoryFragment.class.getName());
        rule.initView(R.drawable.news_bottom, R.string.news_bottom, NewsFragment.class.getName());
        me.initView(R.drawable.me_bottom, R.string.me_bottom, MeFragment.class.getName());


    }

    private void clearFragments() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment!=null &&fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void callBack(String title) {
        if (onTagChangedListener != null) {
            onTagChangedListener.setTitle(title);
        }
    }

    public void setUp(Context context, FragmentManager fragmentManager, int contentId) {
        this.contentId = contentId;
        this.context = context;
        this.fragmentManager = fragmentManager;
        currentButton = lottery;
        clearFragments();
        doSelect(lottery);
    }

    @OnClick({R.id.lottery, R.id.history, R.id.rule, R.id.me})
    void OnNavButtonClick(View view) {
        if (view instanceof NavButton) {
            doSelect((NavButton) view);
        }
    }

    private void doSelect(NavButton view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (view != null) {
            if (currentButton != view) {
                currentButton.setSelected(false);
                transaction.detach(currentButton.getFragment());
            }
            Fragment fragment = view.getFragment();
            if (view.getFragment() == null) {
                fragment = Fragment.instantiate(context, view.getClassName(), null);
                transaction.add(contentId, fragment, view.getClassName());
                view.setFragment(fragment);
            } else {
                transaction.attach(fragment);
            }
            callBack(((BaseFragment)fragment).getTitle());
            view.setSelected(true);
        }
        transaction.commit();

        currentButton = view;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_bottom;
    }

    @Override
    public String getTitle() {
        return null;
    }


    @Override
    public void fail() {

    }
}
