package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhanjixun.data.IC;

public class SellerShowImageAdapter extends BaseAdapter {

	private Context context;
	private List<String> imageUrls;

	public SellerShowImageAdapter(Context context, List<String> urls) {
		this.imageUrls = urls;
		this.context = context;

	}

	@Override
	public int getCount() {
		return imageUrls.size();
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
		ImageView image = new ImageView(context);
		LayoutParams params = new LayoutParams(100, 100);
		image.setLayoutParams(params);
		IC.getInstance().setForegound(imageUrls.get(position), image);
		return image;
	}

}
