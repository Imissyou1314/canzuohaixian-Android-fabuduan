package com.zhanjixun.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain2.Comment;

public class SellerCommentsAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Comment> comments;

	public SellerCommentsAdapter(Context context, List<Comment> comments) {
		inflater = LayoutInflater.from(context);
		this.comments = comments;
	}

	@Override
	public int getCount() {
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.seller_detail_comments_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.seller_detail_comments_listview_item_userName);
			viewHolder.time = (TextView) convertView
					.findViewById(R.id.seller_detail_comments_listview_item_commentTime);
			viewHolder.content = (TextView) convertView
					.findViewById(R.id.seller_detail_comments_listview_item_commentContent);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Comment comment = comments.get(position);
		viewHolder.name.setText(comment.getPhoneNumber());
		viewHolder.time.setText(new SimpleDateFormat("yyyy-MM-dd")
				.format(comment.getCreateTime()));
		viewHolder.content.setText(comment.getContent());
		return convertView;
	}

	private class ViewHolder {
		private TextView name;
		private TextView time;
		private TextView content;
	}
}
