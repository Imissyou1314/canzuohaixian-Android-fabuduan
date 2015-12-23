package com.zhanjixun.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain2.Comment;

public class MyCommentAdapter extends BaseAdapter {
	private List<Comment> comments;
	private Context context;
	private LayoutInflater inflater;

	public MyCommentAdapter(List<Comment> comments, Context context) {
		super();
		this.comments = comments;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		return comments.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.my_comments_listview_item,
					null);
			vh.sellerNameTv = (TextView) convertView
					.findViewById(R.id.item_my_comment_seller);
			vh.commentTv = (TextView) convertView
					.findViewById(R.id.item_my_comment_context);
			vh.timeTv = (TextView) convertView
					.findViewById(R.id.item_my_comment_time);
			vh.commetnKind = (TextView) convertView
					.findViewById(R.id.item_my_comment_kind);
			vh.imageFace = (ImageView) convertView
					.findViewById(R.id.item_my_comment_face);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		Comment comment = comments.get(position);
		vh.sellerNameTv.setText(comment.getShopName());
		vh.commentTv.setText(comment.getContent());

		String time = new SimpleDateFormat("yyyy-MM-dd").format(comment
				.getCreateTime());
		vh.timeTv.setText(time);

		switch (comment.getCommentType()) {
		case Comment.TYPE_GOOD:
			vh.commetnKind.setText("好评");
			vh.imageFace.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.good_comment_pressed));
			break;
		case Comment.TYPE_BAD:
			vh.commetnKind.setText("中评");
			vh.imageFace.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.mid_comment_pressed));
			break;
		case Comment.TYPE_MID:
			vh.commetnKind.setText("差评");
			vh.imageFace.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.bad_comment_pressed));
			break;

		default:
			break;
		}
		return convertView;
	}

	private final class ViewHolder {
		/**
		 * 卖家名称
		 */
		TextView sellerNameTv;
		/**
		 * 评论正文
		 */
		TextView commentTv;
		/**
		 * 时间
		 */
		TextView timeTv;
		/**
		 * 评论类型
		 */
		TextView commetnKind;
		/**
		 * 评论类型的脸
		 */
		ImageView imageFace;
	}

}
