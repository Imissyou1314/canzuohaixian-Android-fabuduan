package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain2.Address;

public class AddressManageAdapter extends BaseAdapter {

	private List<Address> addresses;
	private Context context;

	public AddressManageAdapter(Context context, List<Address> addresses) {
		this.context = context;
		this.addresses = addresses;
	}

	@Override
	public int getCount() {
		return addresses.size();
	}

	@Override
	public Object getItem(int postion) {
		return addresses.get(postion);
	}

	@Override
	public long getItemId(int postion) {
		return postion;
	}

	@Override
	public View getView(final int postion, View convertView, ViewGroup arg2) {

		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.shopping_car_address_item, null);
			vh = new ViewHolder();
			vh.checkBox = (CheckBox) convertView
					.findViewById(R.id.shopping_car_address_checkBox);
			vh.userAddress = (TextView) convertView
					.findViewById(R.id.shopping_car_address_address);
			vh.userName = (TextView) convertView
					.findViewById(R.id.shopping_car_address_user_name);
			vh.userPhone = (TextView) convertView
					.findViewById(R.id.shopping_car_address_user_PhoneNumber);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		Address address = addresses.get(postion);

		vh.userAddress.setText(address.getAddress());
		vh.userName.setText(address.getUserName());
		vh.userPhone.setText(address.getPhone());
		// vh.checkBox.setVisibility(View.INVISIBLE);
		vh.checkBox.setChecked(address.isDefaultAddress());
		vh.checkBox.setClickable(false);
		return convertView;
	}

	final static class ViewHolder {
		public CheckBox checkBox;
		public TextView userName;
		public TextView userPhone;
		public TextView userAddress;
	}

}
