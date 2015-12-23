package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.Address;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class AddAddressActivity extends BackActivity implements
		OnDataReturnListener {

	private EditText edit_userName;
	private EditText edit_userPhone;
	private EditText edit_userEmail;
	private EditText edit_userAddress;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	private LoadingDialog dialog;
	private MessageDialog messageDialog;
	private boolean Add = true;
	private Address address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_add_address);
		initViews();
	}

	public void onFinish(View v) {
		getEdit();
		if (!StringUtil.isEmptyString(userName)
				&& !StringUtil.isEmptyString(userPhone)
				&& !StringUtil.isEmptyString(userEmail)
				&& !StringUtil.isEmptyString(userAddress)) {

			dialog = new LoadingDialog(this);
			dialog.show();
			if (Add) {
				DC.getInstance().addAddress(this, Constants.user.getUserId(),
						userName, userPhone, userAddress, userEmail);
			} else {
				DC.getInstance().updataAddress(this, address.getGetAddressId(),
						Constants.user.getUserId(), userName, userPhone,
						userAddress, userEmail);
			}
		} else {
			new MessageDialog(this, "请输入完整信息！").show();
		}
	}

	private void initViews() {
		edit_userName = (EditText) findViewById(R.id.shopping_car_add_address_userName);
		edit_userPhone = (EditText) findViewById(R.id.shopping_car_add_address_phone);
		edit_userEmail = (EditText) findViewById(R.id.shopping_car_add_address_email);
		edit_userAddress = (EditText) findViewById(R.id.shopping_car_add_address_AddressEdit);

		try {
			String stringExtra = getIntent().getStringExtra("address");
			address = new Gson().fromJson(stringExtra, Address.class);
			edit_userName.setText(address.getUserName());
			edit_userPhone.setText(address.getPhone());
			edit_userEmail.setText(address.getPostcode());
			edit_userAddress.setText(address.getAddress());
			Add = false;
		} catch (Exception e) {
		}
	}

	private void getEdit() {
		userName = edit_userName.getText().toString();
		userPhone = edit_userPhone.getText().toString();
		userEmail = edit_userEmail.getText().toString();
		userAddress = edit_userAddress.getText().toString();
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (taskTag.equals(TaskTag.ADD_ADDRESS)) {
			if (result.getServiceResult()) {
				Toast.makeText(this, result.getResultInfo(), Toast.LENGTH_SHORT)
						.show();
				onBack(null);
			} else {
				messageDialog = new MessageDialog(this);
				messageDialog.setMessage(result.getResultInfo());
				messageDialog.show();
			}
		} else if (taskTag.equals(TaskTag.UPDATA_ADDRESS)) {
			messageDialog = new MessageDialog(this);
			messageDialog.setMessage(result.getResultInfo());
			messageDialog.show();
		}
	}

}
