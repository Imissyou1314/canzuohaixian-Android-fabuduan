package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class FindpasswordActivity_2 extends BackActivity implements
		OnDataReturnListener {
	private EditText newPassword_1;
	private EditText newPassword_2;
	private String id;
	private LoadingDialog dialog;
	private MessageDialog msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password2);
		initViews();
	}

	private void initViews() {
		msg = new MessageDialog(this);
		id = (String) getIntent().getExtras().get("phone");
		newPassword_1 = (EditText) findViewById(R.id.edit_findpassword_newpassword1);
		newPassword_2 = (EditText) findViewById(R.id.edit_findpassword_newpassword2);
	}

	public void onClick(View v) {
		int viewID = v.getId();

		String pw1 = newPassword_1.getText().toString();
		String pw2 = newPassword_2.getText().toString();

		if (viewID == R.id.btn_findpassword_commit) {
			if (StringUtil.isEmptyString(pw1) || StringUtil.isEmptyString(pw2)) {
				msg.setMessage("请输入完整信息");
				msg.show();
			} else if (!pw1.equals(pw2)) {
				new MessageDialog(this, "两次密码不相同！").show();
			} else {
				dialog = new LoadingDialog(this);
				dialog.show();
				DC.getInstance().findPassword(this, id, pw1);
			}
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			Toast.makeText(this, result.getResultInfo(), Toast.LENGTH_LONG)
					.show();
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			this.finish();
		} else {
			msg.setMessage(result.getResultInfo());
			msg.show();
		}
	}
}
