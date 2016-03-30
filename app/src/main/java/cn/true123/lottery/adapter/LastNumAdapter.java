package cn.true123.lottery.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.utils.LotteryUtils;
import cn.true123.lottery.view.KJLineItemView;

public class LastNumAdapter extends BaseAdapter {
	List<Lottery.IEntity> list;
	Context context;
	public LastNumAdapter(List<Lottery.IEntity> list, Context context) {
		this.list = list;
		this.context=context;
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
		//if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.last_num_item, null);
			
			vh=new ViewHolder();
			vh.name = (TextView) view.findViewById(R.id.text_name);
			vh.phase = (TextView) view.findViewById(R.id.text_phase);
			vh.time = (TextView) view.findViewById(R.id.text_timedraw);
			vh.ball =(KJLineItemView) view.findViewById(R.id.view_ball);
		//	view.setTag(vh);
		//}else{
		//	vh = (ViewHolder) view.getTag();
		//}
			DisplayMetrics outMetrics=null;
			outMetrics = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		Lottery.IEntity o = list.get(position);
		vh.name.setText(o.getLotName());
		vh.phase.setText("第"+o.getIssue()+"期");
		vh.time.setText("开奖日期"+o.getDate());
		vh.ball.setWidthHeight(outMetrics.widthPixels, outMetrics.heightPixels);
		vh.ball.addViewList(LotteryUtils.getBall(o));
		return view;
	}

	class ViewHolder {
		TextView name;
		TextView phase;
		TextView time;
		KJLineItemView ball;
	}
}
