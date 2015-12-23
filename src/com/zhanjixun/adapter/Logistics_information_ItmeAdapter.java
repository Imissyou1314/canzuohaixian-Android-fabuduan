package com.zhanjixun.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain.Order_GoodItem;

/**
 * 物流详情信息之
 * @author Imissyou
 *
 */
public class Logistics_information_ItmeAdapter extends BaseAdapter {
	//item的数据.
	private List<Order_GoodItem> listData;
	//获取上下文
	private Context context;
	private Order_GoodItem goods;
	     
	//默认构造方法
    public Logistics_information_ItmeAdapter(Context context,
    		List<Order_GoodItem> listData) {
		this.context = context;
		this.listData = listData;
	}
	
	
	
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int opsition) {
		return listData.get(opsition);
	}

	@Override
	public long getItemId(int opsition) {
		return opsition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int opsition, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		goods = listData.get(opsition);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context)
					.inflate(R.layout.order_logistics_information_item, null);
			viewHolder.item_shop_image = (ImageView) convertView.findViewById(
					R.id.order_logistics_information_Image);
			viewHolder.item_shop_name = (TextView) convertView.findViewById(
					R.id.order_information_item__goodnameText);
			viewHolder.item_shop_number = (TextView) convertView.findViewById(
					R.id.order_information_item__goodnumberText);
			viewHolder.item_shop_price = (TextView) convertView.findViewById(
					R.id.order_information_item__goodPriceText);
			viewHolder.item_shop_size = (TextView) convertView.findViewById(
					R.id.order_information_item__goodsizeText);
			viewHolder.item_shop_mode = (TextView) convertView.findViewById(
					R.id.order_information_item__goodmodeText);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			convertView.setTag(viewHolder);
		}
		//TODO
//		viewHolder.item_shop_image.setBackgroundResource(url);
		viewHolder.item_shop_mode.setText(goods.getGood_mode());
		viewHolder.item_shop_name.setText(goods.getGood_name());
		viewHolder.item_shop_size.setText(goods.getGood_size());
		viewHolder.item_shop_price.setText(goods.getGood_price());
		viewHolder.item_shop_number.setText(goods.getGood_number());
		return convertView;
	}
    
	
	
	final static class ViewHolder {
		TextView item_shop_mode;
		TextView item_shop_name;
		TextView item_shop_price;
		TextView item_shop_size;
		TextView item_shop_number;
		ImageView item_shop_image;
	}

}
