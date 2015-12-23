package com.zhanjixun.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.Good;
import com.zhanjixun.domain2.Order;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.UnitUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class OrderInfoActivity extends BackActivity implements
		OnDataReturnListener, OnClickListener {

	private LinearLayout goodContent;
	private TextView orderStateTv;
	private TextView orderStateMsgTv;
	private TextView geterNameTv;
	private TextView geterPhoneTv;
	private TextView addressTv;
	private TextView sellerName;
	private LoadingDialog dialog;
	private String orderId;
	private Order order = new Order();
	private Button btn;
	private ImageView sellerBg;
	private TextView goodSum;
	private TextView allPrice;
	private TextView postPrice;
	private MessageDialog messageDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info);
		initView();
	}

	private void initView() {
		orderStateTv = (TextView) findViewById(R.id.orderinfo_state_text);
		orderStateMsgTv = (TextView) findViewById(R.id.orderinfo_state_msg);
		sellerBg = (ImageView) findViewById(R.id.orderinfo_sellerface);
		geterNameTv = (TextView) findViewById(R.id.orderinfo__user_name);
		geterPhoneTv = (TextView) findViewById(R.id.orderinfo__user_phone);
		addressTv = (TextView) findViewById(R.id.orderinfo__user_address);
		sellerName = (TextView) findViewById(R.id.orderinfo_sellername_text);
		goodContent = (LinearLayout) findViewById(R.id.orderinfo_content);
		goodSum = (TextView) findViewById(R.id.order_home_item_shop_number2);
		allPrice = (TextView) findViewById(R.id.order_home_item_allmoney);
		postPrice = (TextView) findViewById(R.id.order_home_logistics_money);

		orderId = getIntent().getStringExtra("order_id");
		btn = (Button) findViewById(R.id.orderinfo_Btn);
		// 获取订单数据
		dialog = new LoadingDialog(this);
		dialog.show();
		DC.getInstance().getOrderInfo(this, orderId);

	}

	private void initData() {
		if (order.getOrdersId() != null) {
			setAdress();
			setGoods();
			setOrderState();
			IC.getInstance().setBlurForegound(this, order.getShopPhoto(),
					sellerBg);
		}
	}

	private void setGoods() {
		sellerName.setText(order.getShopKeeperName());
		List<Good> goods = order.getOrdersDetail();
		for (Good g : goods) {
			// 加载布局
			View v = View.inflate(this, R.layout.item_order_gooditem, null);
			ImageView face = (ImageView) v
					.findViewById(R.id.face_item_gooditem);
			TextView name = (TextView) v
					.findViewById(R.id.goodname_item_gooditem);
			TextView size = (TextView) v.findViewById(R.id.size_item_gooditem);
			TextView price = (TextView) v
					.findViewById(R.id.price_item_gooditem);
			TextView number = (TextView) v
					.findViewById(R.id.number_item_gooditem);

			IC.getInstance().setForegound(g.getGoodsPhoto(), face);
			name.setText(g.getGoodsName());
			size.setText(g.getSkuId());
			price.setText(UnitUtil.toRMB(g.getPrice()) + "/" + g.getUnit());
			number.setText("×" + g.getNumber());
			goodContent.addView(v);
		}
		goodSum.setText("共" + goods.size() + "件商品  合计：");
		allPrice.setText(UnitUtil.toRMB(order.getTotalprice()));
		postPrice.setText("(含运费" + UnitUtil.toRMB(order.getPostagePrice())
				+ ")");
	}

	private void setAdress() {
		geterNameTv.setText("收货人：" + order.getUserName());
		geterPhoneTv.setText(order.getPhone());
		addressTv.setText("收货地址：" + order.getSendAddress());
	}

	private void setOrderState() {
		int state = order.getState();

		switch (state) {
		case Order.state_un_pay:
			orderStateTv.setText("等待付款");
			orderStateMsgTv.setText("");
			btn.setText("付款");
			btn.setTag(Order.state_un_pay);
			btn.setOnClickListener(this);
			break;
		case Order.state_un_sent:
			orderStateTv.setText("等待卖家发货");
			orderStateMsgTv.setText("");
			btn.setText("提醒发货");
			btn.setTag(Order.state_un_sent);
			btn.setOnClickListener(this);
			break;
		case Order.state_un_get:
			orderStateTv.setText("卖家已发货");
			orderStateMsgTv.setText("");
			btn.setText("查看物流");
			btn.setTag(Order.state_un_get);
			btn.setOnClickListener(this);
			break;
		case Order.state_un_commet:
			orderStateTv.setText("已签收");
			orderStateMsgTv.setText("");
			btn.setText("去评论");
			btn.setTag(Order.state_un_commet);
			btn.setOnClickListener(this);
			break;
		case Order.state_finish:
			orderStateTv.setText("已完成");
			orderStateMsgTv.setText("");
			btn.setText("已完成");
			break;
		default:
			break;
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			order = new Gson().fromJson(result.getResultParam().get("orders"),
					Order.class);
			initData();
		} else {
			messageDialog = new MessageDialog(this, result.getResultInfo());
			messageDialog.show();
		}
	}

	@Override
	public void onClick(View v) {
		switch ((int) v.getTag()) {
		case Order.state_un_pay:
			Intent intent2 = new Intent(this, PayActivity.class);
			intent2.putExtra("order_id", orderId);
			this.startActivity(intent2);
			break;
		case Order.state_un_sent:
			break;
		case Order.state_un_get:
			Intent intent = new Intent(this, LogisiticeActivity.class);
			try {
				intent.putExtra("imageUrl", order.getOrdersDetail().get(0)
						.getGoodsPhoto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			intent.putExtra("order_id", orderId);
			this.startActivity(intent);
			break;
		case Order.state_un_commet:
			Intent intent1 = new Intent(this, AppraiseActivity.class);
			try {
				intent1.putExtra("imageUrl", order.getOrdersDetail().get(0)
						.getGoodsPhoto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			intent1.putExtra("order_id", orderId);
			intent1.putExtra("shop_id", order.getShopId());
			this.startActivity(intent1);
			break;
		case Order.state_finish:
			break;
		default:
			break;
		}
	}
}
