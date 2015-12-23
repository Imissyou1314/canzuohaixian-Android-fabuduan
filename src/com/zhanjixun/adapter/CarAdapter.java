package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain2.CarOrder;
import com.zhanjixun.domain2.Good;

public class CarAdapter extends BaseAdapter {
	private CarOrder carOrder;
	private List<Good> goods;
	private Context context;

	public CarAdapter(Context context, CarOrder carOrder) {
		this.carOrder = carOrder;
		this.context = context;
		goods = carOrder.getOrdersDetail();
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolderNormal vh = null;
		if (v == null) {
			vh = new ViewHolderNormal();
			v = View.inflate(context, R.layout.item_order_gooditem, null);

			vh.face = (ImageView) v.findViewById(R.id.face_item_gooditem);
			vh.goodName = (TextView) v
					.findViewById(R.id.goodname_item_gooditem);
			vh.goodSize = (TextView) v.findViewById(R.id.size_item_gooditem);
			vh.simplePrice = (TextView) v
					.findViewById(R.id.price_item_gooditem);
			vh.number = (TextView) v.findViewById(R.id.number_item_gooditem);
			v.setTag(vh);
		} else {
			vh = (ViewHolderNormal) v.getTag();
		}
		Good g = goods.get(position);
		IC.getInstance().setForegound(g.getGoodsPhoto(), vh.face);
		vh.goodName.setText(g.getGoodsName());
		vh.goodSize.setText(g.getSkuString());
		vh.simplePrice.setText("¥ " + g.getPrice() + "/" + g.getUnit());
		vh.number.setText("×" + g.getNumber());
		v.setTag(vh);
		return v;
	}

	class ViewHolderNormal {
		public ImageView face;
		public TextView goodName;
		public TextView goodSize;
		public TextView simplePrice;
		public TextView number;
	}

	@Override
	public int getCount() {
		if (goods != null)
			return goods.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return goods.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
