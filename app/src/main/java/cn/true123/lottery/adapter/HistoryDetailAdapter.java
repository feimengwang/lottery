package cn.true123.lottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.model.HistoryItem;
import cn.true123.lottery.model.LotteryHistory;
import cn.true123.lottery.utils.LotteryUtils;

public class HistoryDetailAdapter extends BaseAdapter {
    List<LotteryHistory.ListEntity> list;
    String lotId;
    Context context;

    public HistoryDetailAdapter(String lotId, List<LotteryHistory.ListEntity> list, Context context) {
        this.lotId = lotId;
        this.list = list;
        this.context = context;
    }

    public List getList() {
        return list;
    }

    public void updateList(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list != null ? list.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.history_detail_item, null);

            vh = new ViewHolder();
            vh.time = (TextView) view.findViewById(R.id.text_history_timedraw);
            vh.phase = (TextView) view.findViewById(R.id.text_history_phase);
            vh.red = (TextView) view.findViewById(R.id.text_history_red);
            vh.blue = (TextView) view.findViewById(R.id.text_history_blue);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        LotteryHistory.ListEntity o = list.get(position);
        //Toast.makeText(context, ""+o+";"+o!=null?o.getTimeDraw():"", Toast.LENGTH_SHORT).show();
        HistoryItem item = LotteryUtils.convertHistoryItem(lotId, o);
        if (item == null) {
            vh.time.setText("开奖日期" + LotteryUtils.getDate(o.getEndTime()));
            vh.phase.setText("第" + o.getIssue() + "期");
            vh.red.setText(o.getWinNumber());
            vh.blue.setText(o.getBallNumber());
        } else {
            vh.time.setText("开奖日期" + LotteryUtils.getDate(item.getTime()));
            vh.phase.setText("第" + item.getIssue() + "期");
            vh.red.setText(item.getRed());
            vh.blue.setText(item.getBlue());
        }
        return view;
    }

    class ViewHolder {
        TextView time;
        TextView phase;
        TextView red;
        TextView blue;
    }
}
