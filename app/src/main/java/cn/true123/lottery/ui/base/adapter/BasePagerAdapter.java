package cn.true123.lottery.ui.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.true123.lottery.ui.fragment.base.BaseFragment;

/**
 * Created by junbo on 1/11/2016.
 */

public class BasePagerAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> fragmentList;
    Context context;

    public BasePagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, Context context) {
        super(fm);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    public BasePagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

}
