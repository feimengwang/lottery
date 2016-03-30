package cn.true123.lottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.true123.lottery.R;
import cn.true123.lottery.model.Rule;

public class HistoryAdapter extends BaseAdapter {
	List<String> list;
	Context context;

	public HistoryAdapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vh = null;
		if(view==null){
			vh = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.history_item, null);
			vh.name = (TextView) view.findViewById(R.id.text_rule);
			view.setTag(vh);
		}else{
			vh = (ViewHolder) view.getTag();
		}
		String name = list.get(position);
		vh.name.setText(name);
		return view;
	}
	class ViewHolder{
		TextView name;
	}
}
