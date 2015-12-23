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
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain2.Farmer;
import com.zhanjixun.domain2.Fisherman;
import com.zhanjixun.domain2.Location;
import com.zhanjixun.domain2.Seller;
import com.zhanjixun.utils.MyUtil;

public class SellerListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Seller> sellers;
	private Context context;

	public SellerListAdapter(Context context, List<Seller> sellerItemBeans) {
		this.inflater = LayoutInflater.from(context);
		this.sellers = sellerItemBeans;
		this.context = context;
	}

	@Override
	public int getCount() {
		return sellers.size();
	}

	@Override
	public Object getItem(int position) {
		return sellers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Seller seller = sellers.get(position);

		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.seller_listview_item, null);
			vh.image = (ImageView) convertView
					.findViewById(R.id.id_category_good_detail_listview_item_sellerImage);
			vh.shopName = (TextView) convertView
					.findViewById(R.id.id_seller_listview_item_sellerName);
			vh.item_1 = (TextView) convertView
					.findViewById(R.id.id_seller_listview_item_shipPort);
			vh.item_2 = (TextView) convertView
					.findViewById(R.id.id_seller_listview_item_returnTime);
			vh.item_3 = (TextView) convertView
					.findViewById(R.id.id_seller_listview_item_catchway);
			vh.sendPrice = (TextView) convertView
					.findViewById(R.id.id_seller_listview_lowest_price_of_sending);
			vh.view = convertView.findViewById(R.id.id_seller_include_credit);
			vh.creditValue = (TextView) vh.view
					.findViewById(R.id.id_credit_value_text);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.shopName.setText(seller.getShopName());
		vh.sendPrice.setText("￥ " + seller.getSendPrice() + "元");
		vh.creditValue.setText(seller.getGrade());
		IC.getInstance().setForegound(seller.getShopPhoto(), vh.image);

		if (seller instanceof Fisherman) {
			initFisherman(seller, vh);
		} else if (seller instanceof Farmer) {
			initFarmer(seller, vh);
		}
		return convertView;
	}

	private void initFarmer(Seller seller, ViewHolder vh) {
		Farmer farmers = (Farmer) seller;
		vh.item_1.setText(context.getResources().getString(
				R.string.farmer_address)
				+ farmers.getAddress());
		Location l2 = new Location();
		l2.setLongitude(seller.getLongitude());
		l2.setLatitude(seller.getLatitude());
		vh.item_2.setText(context.getResources().getString(
				R.string.farmer_distance)
				+ MyUtil.distance(Constants.location, l2) + "千米");
		vh.item_3.setText(context.getResources().getString(R.string.farmer_way)
				+ farmers.getGetTypeString());
	}

	private void initFisherman(Seller seller, ViewHolder vh) {
		Fisherman fishermen = (Fisherman) seller;
		vh.item_1.setText(context.getResources().getString(
				R.string.fishermen_ship_port)
				+ fishermen.getShipPort());

		vh.item_2.setText(context.getResources().getString(
				R.string.fishermen_return_time)
				+ MyUtil.getDays(fishermen.getPortTime()) + "天后");
		vh.item_3.setText(context.getResources().getString(
				R.string.fishermen_catch_way)
				+ fishermen.getGetTypeString());
	}

	class ViewHolder {
		public TextView shopName; // 商家名
		public TextView item_1; // 靠岸口
		public TextView item_2; // 靠岸时间
		public TextView item_3; // 捕捞方式
		public TextView sendPrice; // 起送价
		public TextView creditValue; // 信用值
		public ImageView image;
		public View view;
	}
}
