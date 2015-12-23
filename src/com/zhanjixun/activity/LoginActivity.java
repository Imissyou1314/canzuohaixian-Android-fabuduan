package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.User;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class LoginActivity extends BackActivity implements OnDataReturnListener {
	private EditText pwEt;
	private EditText idEt;
	private Button btnReist;
	private Button btnLogin;
	private LoadingDialog dialog;
	private TextView forgetPwTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
	}

	private void initViews() {
		idEt = (EditText) findViewById(R.id.login_In_TelPhoneNumber);
		pwEt = (EditText) findViewById(R.id.login_In_Password);
		forgetPwTv = (TextView) findViewById(R.id.login_In_FindPasswordText);
		btnLogin = (Button) findViewById(R.id.login_in_DoLoginBtn);
		btnReist = (Button) findViewById(R.id.Login_In_free_registationBtn);
	}

	public void onClick(View v) {
		int ViewId = v.getId();
		if (ViewId == btnLogin.getId()) {
			String id = idEt.getText().toString();
			String pw = pwEt.getText().toString();
			if (!StringUtil.isEmptyString(id) && !StringUtil.isEmptyString(pw)) {

				dialog = new LoadingDialog(this, "正在登录...");
				dialog.show();

				DC.getInstance().userLogin(this, id, pw);
			} else {
				new MessageDialog(this, "请输入完整信息！").show();
			}
		} else if (ViewId == btnReist.getId()) {
			Intent intent = new Intent(this, RegisterActivity.class);
			this.startActivity(intent);

		} else if (ViewId == forgetPwTv.getId()) {
			Intent intent = new Intent(this, FindpasswordActivity_1.class);
			this.startActivity(intent);
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();

		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.LOGIN)) {
				Constants.user = new Gson().fromJson(result.getResultParam()
						.get("user"), User.class);
				LogUtils.v(Constants.user.getHeadImage());
				Constants.user.saveUserInfo(this);

				Intent intent = new Intent(this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				this.finish();
			}
		} else {
			new MessageDialog(this, "登录失败！原因：" + result.getResultInfo()).show();
		}
	}
}
