package cn.true123.lottery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.true123.lottery.model.Rule;
import cn.true123.lottery.adapter.RuleAdapter;

public class RuleFragment extends LotteryBaseFragment {

	private static final String TAG = "RuleFragment";
	ListView listview;
	List list = new ArrayList();
	RuleAdapter adapter;


	public static RuleFragment getInstance() {
		RuleFragment fragment = new RuleFragment();
		return fragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.rule_layout, null);
		initView(view);
		read();
		adapter = new RuleAdapter(list, getActivity());
		listview.setAdapter(adapter);
		return view;
	}

	private void initView(View v) {
		listview = (ListView) v.findViewById(R.id.listview_rule);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Rule rule = (Rule) list.get(position);
				show(rule);
			}

		});

	}

	void show(Rule rule) {
		AlertDialog dialog = new AlertDialog.Builder(getActivity())
				.setTitle(rule.getName()).setMessage(rule.getContent())
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				}).create();
		dialog.show();
	}

	void read() {
		if (list.size() > 0)
			return;
		TypedArray ta = getResources().obtainTypedArray(R.array.rule_name);
		if (ta != null) {
			for (int i = 0; i < ta.length(); i++) {
				String name = getResources().getString(ta.getResourceId(i, 0));
				Rule rule = new Rule();
				rule.setName(name);
				list.add(i, rule);

			}
		}
		TypedArray rulecontent = getResources().obtainTypedArray(R.array.rule);
		if (rulecontent != null) {
			for (int j = 0; j < rulecontent.length(); j++) {
				InputStream in = getResources().openRawResource(
						(rulecontent.getResourceId(j, 0)));
				BufferedReader br;
				try {
					br = new BufferedReader(new InputStreamReader(in, "utf-8"));

					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line.trim() + "\n");
					}
					Rule rule = (Rule) list.get(j);
					rule.setContent(sb.toString());
					// list.add(j, rule);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected String getTAGName() {
		return TAG;
	}
}
