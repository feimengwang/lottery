package cn.true123.lottery;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import cn.true123.lottery.slidingtutorial.PageFragment;
import cn.true123.lottery.slidingtutorial.SimplePagerFragment;

public class SlidingFragment extends SimplePagerFragment {
    @Override
    protected boolean isInfiniteScrollEnabled() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if(R.id.tvSkip==v.getId()){
            Intent intent=new Intent(getActivity(),MainActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
        super.onClick(v);
    }

    @Override
    protected boolean onSkipButtonClicked(View skipButton) {
        return true;
    }

    @Override
    public int getLayoutResId() {
        return super.getLayoutResId();
    }

    @Override
    public int getButtonSkipResId() {
        return super.getButtonSkipResId();
    }

    @Override
    public int getIndicatorResId() {
        return super.getIndicatorResId();
    }

    @Override
    public int getViewPagerResId() {
        return super.getViewPagerResId();
    }

    @Override
    protected int getPagesCount() {
        return 3;
    }

    @Override
    protected PageFragment getPage(int position) throws IllegalArgumentException {
        PageFragment page = null;
        switch (position){
            case 0:
                page = new SlidingFirstFragment();
                break;
            case 1:
                page=new SlidingSecondFragment();
                break;
            case 2:
                page=new SlidingThirdFragment();
                break;
            default:
            throw new IllegalArgumentException("position");
        }
        return page;
    }

    @Override
    protected int getPageColor(int position) {
        if (position == 0)
            return ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);
        if (position == 1)
            return ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
        if (position == 2)
            return ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
        return Color.TRANSPARENT;
    }
}
