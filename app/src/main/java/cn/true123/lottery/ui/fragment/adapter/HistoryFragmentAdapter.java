package cn.true123.lottery.ui.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.true123.lottery.R;
import cn.true123.lottery.model.HistoryItem;
import cn.true123.lottery.model.LotteryHistory;
import cn.true123.lottery.ui.base.adapter.BaseRecyclerAdapter;
import cn.true123.lottery.utils.LotteryUtils;
import mlog.true123.cn.lib.MLog;

/**
 * Created by junbo on 7/11/2016.
 */

public class HistoryFragmentAdapter extends BaseRecyclerAdapter<LotteryHistory.ListEntity, HistoryFragmentAdapter.HistoryFragmentViewHolder> {
    private String id;


    public HistoryFragmentAdapter(List list, Context context) {
        super(list, context);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HistoryFragmentViewHolder createVH(View v, int viewType) {
        if (viewType == FOOTER_TYPE) {
            return new HistoryFragmentFooterViewHolder(v);
        }
        return new HistoryFragmentCommonViewHolder(v);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.history_detail_item;
    }

    @Override
    public void bindVH(HistoryFragmentViewHolder holder, int position) {
        MLog.i("position=" + position + ";" + getItemCount());

        HistoryFragmentCommonViewHolder historyHolder = (HistoryFragmentCommonViewHolder) holder;
        LotteryHistory.ListEntity o = list.get(position);
        HistoryItem item = LotteryUtils.convertHistoryItem(id, o);

        if (item == null) {
            historyHolder.time.setText("开奖日期" + LotteryUtils.getDate(o.getEndTime()));
            historyHolder.phase.setText("第" + o.getIssue() + "期");
            historyHolder.red.setText(o.getWinNumber());
            historyHolder.blue.setText(o.getBallNumber());
        } else {
            historyHolder.time.setText("开奖日期" + LotteryUtils.getDate(item.getTime()));
            historyHolder.phase.setText("第" + item.getIssue() + "期");
            historyHolder.red.setText(item.getRed());
            historyHolder.blue.setText(item.getBlue());
        }

    }

    @Override
    public void bindFooterVH(HistoryFragmentViewHolder holder, int position) {
        HistoryFragmentFooterViewHolder historyHolder = (HistoryFragmentFooterViewHolder) holder;
        if (isEnd) {
            historyHolder.progressBar.setVisibility(View.GONE);
            historyHolder.footerText.setVisibility(View.VISIBLE);
            historyHolder.footerText.setText("没有更多。。。");


        } else if (isFlushing) {
            historyHolder.progressBar.setVisibility(View.VISIBLE);
            historyHolder.footerText.setVisibility(View.GONE);
        } else {
            historyHolder.progressBar.setVisibility(View.GONE);
            historyHolder.footerText.setVisibility(View.VISIBLE);
            historyHolder.footerText.setText("加载更多");
        }
    }

    class HistoryFragmentViewHolder extends BaseRecyclerAdapter.BaseViewHoler {

        public HistoryFragmentViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HistoryFragmentCommonViewHolder extends HistoryFragmentViewHolder {
        @BindView(R.id.text_history_timedraw)
        TextView time;
        @BindView(R.id.text_history_phase)
        TextView phase;
        @BindView(R.id.text_history_red)
        TextView red;
        @BindView(R.id.text_history_blue)
        TextView blue;

        public HistoryFragmentCommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HistoryFragmentFooterViewHolder extends HistoryFragmentViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.footer_text)
        TextView footerText;

        public HistoryFragmentFooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
