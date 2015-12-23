package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain2.GoodListItem;

public class GoodListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<GoodListItem> goods;

	public GoodListAdapter(Context context, List<GoodListItem> goods) {
		inflater = LayoutInflater.from(context);
		this.goods = goods;
	}

	@Override
	public int getCount() {
		return goods.size();
	}

	@Override
	public Object getItem(int position) {
		return goods.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.category_listview_item,
					null);
			vh.academicName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodScientificName);
			vh.simpleName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodPopularName);
			vh.englishName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodEnglishName);
			vh.sendPrice = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_lowest_price);
			vh.image = (ImageView) convertView
					.findViewById(R.id.id_category_listview_item_image);
			vh.view = convertView.findViewById(R.id.id_sales);
			vh.sellNumber = (TextView) vh.view
					.findViewById(R.id.id_sales_salesValue);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		GoodListItem itemBean = goods.get(position);
		IC.getInstance().setForegound(itemBean.getFishPhoto(), vh.image);
		vh.academicName.setText(itemBean.getCategoryAcademicName());
		vh.simpleName.setText(itemBean.getCategorySimpleName());
		vh.englishName.setText(itemBean.getCategoryEnglishName());
		vh.sellNumber.setText(itemBean.getTotalSellNumber() + "");
		vh.sendPrice.setText("гд " + itemBean.getLowPrice() + "/"
				+ itemBean.getUnit());
		return convertView;
	}

	class ViewHolder {
		public TextView academicName;
		public TextView simpleName;
		public TextView englishName;
		public TextView sellNumber;
		public TextView sendPrice;
		public ImageView image;
		public View view;
	}
}
