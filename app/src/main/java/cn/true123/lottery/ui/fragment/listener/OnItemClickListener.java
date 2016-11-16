package cn.true123.lottery.ui.fragment.listener;

import android.view.View;

/**
 * Created by junbo on 3/11/2016.
 */

public interface OnItemClickListener {
    public void onItemClick(View view,long position);

    void onFooterClick(View v, int layoutPosition);
}
