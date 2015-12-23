package com.zhanjixun.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.OrderInfoActivity;
import com.zhanjixun.activity.ShopDetailActivity;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain2.Good;
import com.zhanjixun.domain2.Order;
import com.zhanjixun.utils.UnitUtil;

@SuppressLint("InflateParams")
public class OrderListAdapter extends BaseAdapter {
	private List<Order> orders;
	private Context context;

	public OrderListAdapter(Context context, List<Order> orders) {
		this.orders = orders;
		this.context = context;
	}

	@Override
	public int getCount() {
		return orders.size();
	}

	@Override
	public Object getItem(int position) {
		return orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		Order order = orders.get(position);
		ViewHolder vh = null;
		if (v == null) {
			vh = new ViewHolder();
			v = LayoutInflater.from(context).inflate(R.layout.item_order_home,
					null);
			vh.shopName = (TextView) v
					.findViewById(R.id.order_home_item_shopTitle_name);
			vh.Btn = (Button) v.findViewById(R.id.order_home_item_Btn);
			vh.goodImage = (ImageView) v
					.findViewById(R.id.order_home_item_shop_image);
			vh.allPiece = (TextView) v
					.findViewById(R.id.order_home_item_allmoney);
			vh.postagePrice = (TextView) v
					.findViewById(R.id.order_home_logistics_money);
			vh.goodName = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodname);
			vh.goodPirce = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodprice);
			vh.goodNumber = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodnumber);
			vh.goodAllNumber = (TextView) v
					.findViewById(R.id.order_home_item_shop_number2);
			vh.goodSize = (TextView) v
					.findViewById(R.id.order_home_item_shop_size);
			v.setTag(vh);

		} else {
			vh = (ViewHolder) v.getTag();
			v.setTag(vh);
		}
		vh.shopName.setText(order.getShopKeeperName());
		Good good = order.getOrdersDetail().get(0);
		IC.getInstance().setForegound(good.getGoodsPhoto(), vh.goodImage);

		vh.goodName.setText(good.getGoodsName() + "");
		vh.goodSize.setText(good.getSkuId() + "");
		vh.goodPirce.setText(UnitUtil.toRMB(good.getPrice()) + "/"
				+ good.getUnit());
		vh.goodNumber.setText("×" + good.getNumber());
		vh.goodAllNumber.setText("共" + order.getOrdersDetail().size()
				+ "件商品  合计：");
		vh.allPiece.setText(UnitUtil.toRMB(order.getTotalprice()));

		vh.postagePrice.setText("(含运费"
				+ UnitUtil.toRMB(order.getPostagePrice()) + ")");
		MyOnClickListener myOnClickListener = new MyOnClickListener(order);
		try {
			vh.Btn.setText(Order.STATE_STR[order.getState()]);
			vh.Btn.setOnClickListener(myOnClickListener);
		} catch (Exception e) {
		}
		v.setOnClickListener(myOnClickListener);
		vh.shopName.setTag("ShopName");
		vh.shopName.setOnClickListener(myOnClickListener);

		return v;
	}

	private class MyOnClickListener implements OnClickListener {
		private Order order;

		public MyOnClickListener(Order order) {
			this.order = order;
		}

		@Override
		public void onClick(View v) {
			if (v.getTag() != null && v.getTag().equals("ShopName")) {
				Intent i = new Intent(context, ShopDetailActivity.class);
				i.putExtra("shopId", order.getShopId());
				i.putExtra("back", "订单");
				context.startActivity(i);
			} else {
				Intent intent1 = new Intent(context, OrderInfoActivity.class);
				intent1.putExtra("order_id", order.getOrdersId());
				context.startActivity(intent1);
			}
		}
	}

	final class ViewHolder {
		/**
		 * 商品店名标题
		 */
		TextView shopName;
		/**
		 * 商品名
		 */
		TextView goodName;
		/**
		 * 商品数量
		 */
		TextView goodNumber;
		/**
		 * 商品价格
		 */
		TextView goodPirce;
		/**
		 * 商品所有价格
		 */
		TextView allPiece;
		/**
		 * 商品规格 中等大小
		 */
		TextView goodSize;
		/**
		 * 订单商品数量
		 */
		TextView goodAllNumber;
		/**
		 * 商品运费数量
		 */
		TextView postagePrice;
		/**
		 * 按钮
		 */
		Button Btn;
		/**
		 * 商品图片
		 */
		ImageView goodImage;
	}
}
