package cn.true123.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.true123.lottery.adapter.HistoryAdapter;
import cn.true123.lottery.listener.OnFragmentChangeListener;
import cn.true123.lottery.slidingtutorial.PageFragment;
import cn.true123.lottery.slidingtutorial.SimplePagerFragment;
import cn.true123.lottery.utils.LotteryUtils;

public class SlidingFirstFragment extends PageFragment {


    @Override
    protected int getLayoutResId() {
        return R.layout.sliding_first_fragment;
    }
}
