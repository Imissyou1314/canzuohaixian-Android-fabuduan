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

public class CarEditAdapter extends BaseAdapter {

	private CarOrder carOrder;
	private List<Good> goods;
	private Context context;

	public CarEditAdapter(Context context, CarOrder carOrder) {
		this.carOrder = carOrder;
		this.context = context;
		goods = carOrder.getOrdersDetail();
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolderEdit vh = null;
		if (v == null) {
			vh = new ViewHolderEdit();
			v = View.inflate(context, R.layout.item_car_edit, null);
			vh.face = (ImageView) v.findViewById(R.id.face_caredit);
			vh.reduce = (TextView) v.findViewById(R.id.image_caredit_reduce);
			vh.add = (TextView) v.findViewById(R.id.image_caredit_add);
			vh.number = (TextView) v.findViewById(R.id.text_caredit_number);
			v.setTag(vh);
		} else {
			vh = (ViewHolderEdit) v.getTag();
		}
		Good g = goods.get(position);
		IC.getInstance().setForegound(g.getGoodsPhoto(), vh.face);
		vh.number.setText(g.getNumber() + "");
		MyOnClickListener l = new MyOnClickListener(position, g.getNumber(),
				vh.number);
		vh.reduce.setOnClickListener(l);
		vh.add.setOnClickListener(l);
		v.setTag(vh);
		return v;
	}

	class ViewHolderEdit {
		public ImageView face;
		public TextView reduce;
		public TextView number;
		public TextView add;
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

	private class MyOnClickListener implements View.OnClickListener {
		private int position;
		private int number;
		private TextView numberTv;

		public MyOnClickListener(int position, int number, TextView numberTv) {
			this.position = position;
			this.number = number;
			this.numberTv = numberTv;
		}

		@Override
		public void onClick(View v) {
			String tag = (String) v.getTag();
			if (tag.equals("REDUCE")) {
				if (number > 0) {
					numberTv.setText(--number + "");
				}
			} else if (tag.equals("ADD")) {
				numberTv.setText(++number + "");
			}
			carOrder.getOrdersDetail().get(position).setNumber(number);
			CarOrder.saveCarOrder(carOrder, context);
		}

	}

}
