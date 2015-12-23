package com.zhanjixun.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.Address;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.CarOrder;
import com.zhanjixun.domain2.Good;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.SPUtil;
import com.zhanjixun.utils.UnitUtil;
import com.zhanjixun.views.DoubleButtonMessageDialog;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class SubmitOrder extends BackActivity implements OnDataReturnListener {
	private LoadingDialog dialog;
	private TextView shellerName;
	private TextView goodNumber;
	private TextView price;
	private TextView postPrice;
	private CarOrder co;
	private LinearLayout goodContent;
	private MessageDialog msg;
	private Address address;
	private TextView buyer;
	private TextView phone;
	private TextView addressTv;
	private boolean ready = false;
	protected String orderId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dialog = new LoadingDialog(this);
		setContentView(R.layout.activity_submint_order);
		initViews();

	}

	@Override
	protected void onStart() {
		super.onStart();
		initData();
	}

	private void initViews() {
		// 收货地址
		buyer = (TextView) findViewById(R.id.order_user_name);
		phone = (TextView) findViewById(R.id.order_user_phone);
		addressTv = (TextView) findViewById(R.id.orderinfo__user_address);

		shellerName = (TextView) findViewById(R.id.order_sellername_text);
		goodNumber = (TextView) findViewById(R.id.order_item_shop_number2);
		price = (TextView) findViewById(R.id.order_item_allmoney);
		postPrice = (TextView) findViewById(R.id.order_logistics_money);
		goodContent = (LinearLayout) findViewById(R.id.orderinfo_content);
	}

	private void initData() {
		co = CarOrder.getCarOrder(this);
		initAddress();
		initGoods();
		initDetail();
	}

	private void initAddress() {

		dialog.show();
		DC.getInstance().getDefultAddress(this, Constants.user.getUserId());
	}

	private void initGoods() {
		List<Good> ordersDetail = co.getOrdersDetail();
		goodContent.removeAllViews();
		try {
			for (Good g : ordersDetail) {
				View v = View.inflate(this, R.layout.item_order_gooditem, null);
				ImageView face = (ImageView) v
						.findViewById(R.id.face_item_gooditem);
				TextView name = (TextView) v
						.findViewById(R.id.goodname_item_gooditem);
				TextView size = (TextView) v
						.findViewById(R.id.size_item_gooditem);
				TextView price = (TextView) v
						.findViewById(R.id.price_item_gooditem);
				TextView number = (TextView) v
						.findViewById(R.id.number_item_gooditem);
				IC.getInstance().setForegound(g.getGoodsPhoto(), face);
				name.setText(g.getGoodsName());
				size.setText(g.getSkuString());
				price.setText(UnitUtil.toRMB(g.getPrice()) + "/" + g.getUnit());
				number.setText("×" + g.getNumber());
				goodContent.addView(v);
			}
		} catch (Exception e) {
		}
	}

	private void initDetail() {
		shellerName.setText(co.getShopKeeperName());
		try {
			goodNumber.setText("共" + co.getOrdersDetail().size() + "件商品  合计：");
		} catch (Exception e) {
			e.printStackTrace();
		}
		price.setText(UnitUtil.toRMB(co.getPrice()));
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.GET_DEFULT_ADDRESS)) {
				try {
					address = new Gson().fromJson(
							result.getResultParam().get("getAddress"),
							Address.class);
					setAddress();
					dialog.show();
					DC.getInstance().getOrdersPostage(this, co,
							address.getGetAddressId());
				} catch (Exception e) {
					e.printStackTrace();

				}
			} else if (taskTag.equals(TaskTag.GET_ORDERS_POSTAGE)) {
				try {
					String s = result.getResultParam().get("postagePrice");
					postPrice.setText("(含运费" + UnitUtil.toRMB(s) + ")");
					ready = true;
				} catch (Exception e) {
					ready = false;
				}
			} else if (taskTag.equals(TaskTag.COMMIT_ORDER)) {
				SPUtil.deleteSP(this, Constants.XML_CAR);
				orderId = result.getResultParam().get("ordersId");
				DoubleButtonMessageDialog buttonMessageDialog = new DoubleButtonMessageDialog(
						this, result.getResultInfo());
				buttonMessageDialog.setCancelable(false);
				buttonMessageDialog.setNegativeButton("返回",
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(SubmitOrder.this,
										MainActivity.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
								SubmitOrder.this.finish();
							}
						});
				buttonMessageDialog.setPositiveButton("去付款",
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent2 = new Intent(SubmitOrder.this,
										PayActivity.class);
								intent2.putExtra("order_id", orderId);
								SubmitOrder.this.startActivity(intent2);
								SubmitOrder.this.finish();
							}
						});
				buttonMessageDialog.show();
			}
		} else {
			msg = new MessageDialog(this, result.getResultInfo());
			msg.show();
		}
	}

	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag.equals("Right")) {
			Intent intent = new Intent(this, AddressManageActivity.class);
			startActivity(intent);
		} else if (tag.equals("commint") && ready) {
			dialog.show();
			DC.getInstance().commitOrder(this, co, address.getGetAddressId());
		}
	}

	private void setAddress() {
		buyer.setText("收货人：" + address.getUserName());
		phone.setText(address.getPhone());
		addressTv.setText("收货地址：" + address.getAddress());
	}

}
