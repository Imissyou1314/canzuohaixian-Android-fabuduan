package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhanjixun.R;
import com.zhanjixun.adapter.TimelineAdapter;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.LogisticsInfo;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class LogisiticeActivity extends BackActivity implements
		OnDataReturnListener, OnClickListener {

	// 物流详情信息
	private ListView listView;
	// 商品图片
	private ImageView order_image;
	// 物流状态
	private TextView logistice_status;
	// 物流编号
	private TextView logistice_code;

	private Button logistics_Btn;
	// 页面hear的数据
	private String orderId;
	private LoadingDialog dialog;
	private String ordersNumber;
	// Btn状态
	private Integer integer = 0;
	private MessageDialog msgDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_logistics_state_information);
		orderId = getIntent().getStringExtra("order_id");
		initViews();
		initData();
	}

	private void initData() {
		dialog = new LoadingDialog(this);
		dialog.show();
		DC.getInstance().orderPostCode(this, orderId);
		IC.getInstance().setForegound(getIntent().getStringExtra("imageUrl"),
				order_image);
	}

	private void initState() {
		switch (integer) {
		case 0:
			logistice_status.setText("正在运输中");
			break;
		case 1:
			logistice_status.setText("已揽件");
			break;
		case 2:
			logistice_status.setText("寄送过程出现问题");
			break;
		case 3:
			logistice_status.setText("已签收");
			break;
		case 4:
			logistice_status.setText("货物退签");
			break;
		case 5:
			logistice_status.setText("正在派件");
			break;
		case 6:
			logistice_status.setText("货物退回中");
			break;
		default:
			logistice_status.setText("");
			break;
		}
		// 0：在途，即货物处于运输过程中；
		// 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；
		// 2：疑难，货物寄送过程出了问题；
		// 3：签收，收件人已签收；
		// 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；
		// 5：派件，即快递正在进行同城派件；
		// 6：退回，货物正处于退回发件人的途中
	}

	private void initViews() {
		msgDialog = new MessageDialog(this);
		listView = (ListView) findViewById(R.id.logistice_informantion_listview);
		order_image = (ImageView) findViewById(R.id.logistics_state_information_Orderimage);
		logistice_status = (TextView) findViewById(R.id.logistics_state_information);
		logistice_code = (TextView) findViewById(R.id.logistics_status_information_code);
		logistics_Btn = (Button) findViewById(R.id.logistice_information_status_ensureBtn);
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (TaskTag.ORDER_POST_CODE.equals(taskTag)) {
			ordersNumber = result.getResultParam().get("ordersNumber");
			if (!StringUtil.isEmptyString(ordersNumber)) {
				DC.getInstance().getLogistic(this, ordersNumber);
				logistice_code.setText(ordersNumber);
			} else {
				msgDialog.setMessage("没有获取到快递单号！");
				msgDialog.show();
				logistics_Btn.setVisibility(View.INVISIBLE);
			}
		} else if (TaskTag.LOGISTIC.equals(taskTag)) {
			if (!StringUtil.isEmptyString(json)) {
				LogisticsInfo i = new Gson()
						.fromJson(json, LogisticsInfo.class);
				if (i.getStatus() == 200) {
					TimelineAdapter timeAdapter = new TimelineAdapter(this, i);
					listView.setAdapter(timeAdapter);
					integer = i.getState();
					initState();
					logistics_Btn.setOnClickListener(this);
					setListViewHeightBasedOnChildren(listView);
				} else {
					msgDialog.setMessage(i.getMessage());
					msgDialog.show();
					logistics_Btn.setVisibility(View.INVISIBLE);
				}
			} else {
				msgDialog.setMessage("服务器连接失败！");
				msgDialog.show();
				logistics_Btn.setVisibility(View.INVISIBLE);
			}
		} else if (TaskTag.ENSUREGET.equals(taskTag)) {
			msgDialog.setMessage(result.getResultInfo());
			msgDialog.show();
		}
	}

	@Override
	public void onClick(View v) {
		dialog.show();
		DC.getInstance().ensureGet(this, orderId);
	}

	protected void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
