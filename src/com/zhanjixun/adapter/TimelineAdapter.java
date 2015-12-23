package com.zhanjixun.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain2.Logistics;
import com.zhanjixun.domain2.LogisticsInfo;

@SuppressLint("ResourceAsColor")
public class TimelineAdapter extends BaseAdapter {

	private Context context;
	private LogisticsInfo info;
	private List<Logistics> data = new ArrayList<Logistics>();

	public TimelineAdapter(Context context, LogisticsInfo info) {
		this.context = context;
		this.info = info;
		if (info.getData() != null) {
			data = info.getData();
		}
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.item_logistics_information, null);
			vh.time = (TextView) convertView.findViewById(R.id.tv_time);
			vh.title = (TextView) convertView.findViewById(R.id.title);
			vh.image = (ImageView) convertView.findViewById(R.id.image);
			vh.view = convertView.findViewById(R.id.view_2);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		Logistics l = data.get(position);
		vh.time.setText(l.getTime());
		vh.title.setText(l.getContext());
		if (position == 0) {
			vh.image.setImageResource(R.drawable.timeline_orange);
			int themeColor = context.getResources().getColor(R.color.theme);
			vh.title.setTextColor(themeColor);
			vh.time.setTextColor(themeColor);
		} else {
			vh.image.setImageResource(R.drawable.timeline_content);
			vh.title.setTextColor(0xff777777);
			vh.time.setTextColor(0xff777777);
		}
		if (position == getCount() - 1) {
			vh.view.setVisibility(View.INVISIBLE);
		} else {
			vh.view.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	static class ViewHolder {
		public TextView time;
		public TextView title;
		public ImageView image;
		public View view;
	}

}
