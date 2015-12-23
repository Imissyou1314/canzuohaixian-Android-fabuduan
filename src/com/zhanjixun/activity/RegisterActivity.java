package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class RegisterActivity extends BackActivity implements
		OnDataReturnListener {
	private EditText passwordEdit;
	private EditText phoneEd;
	private EditText checkCodeEdit;
	private LoadingDialog dialog;
	@SuppressWarnings("unused")
	private Button getCodeBtn;
	private String phone;
	private String checkCode;
	private String password;
	private MessageDialog messageDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initViews();
	}

	private void initViews() {
		getCodeBtn = (Button) findViewById(R.id.btn_register_get_code);
		phoneEd = (EditText) findViewById(R.id.edit_register_phone);
		checkCodeEdit = (EditText) findViewById(R.id.edit_register_checkCode);
		passwordEdit = (EditText) findViewById(R.id.edit_register_password);
	}

	public void onClick(View v) {
		String tag = (String) v.getTag();
		phone = phoneEd.getText().toString();
		checkCode = checkCodeEdit.getText().toString();
		password = passwordEdit.getText().toString();
		if (tag.equals("checkCode")) {
			LogUtils.d(phone);
			if (StringUtil.isEmptyString(phone) || phone.length() != 11) {
				messageDialog = new MessageDialog(this, "请输入正确的手机号码！");
				messageDialog.show();
			} else {
				dialog = new LoadingDialog(this);
				dialog.show();
				DC.getInstance().requestCodeForRegister(this, phone);
			}
		} else if (tag.equals("commit")) {
			if (!StringUtil.isEmptyString(phone)
					&& !StringUtil.isEmptyString(checkCode)
					&& !StringUtil.isEmptyString(password)) {
				dialog = new LoadingDialog(this);
				dialog.show();
				DC.getInstance().register(this, phone, checkCode, password);

			} else {
				messageDialog = new MessageDialog(this, "请输入完整信息！");
				messageDialog.show();
			}
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.REGISTER_CODE)) {
				Toast.makeText(this, result.getResultInfo(), Toast.LENGTH_LONG)
						.show();
			} else if (taskTag.equals(TaskTag.REGISTER)) {
				messageDialog.setMessage(result.getResultInfo());
				messageDialog.show();
			}
		} else {
			messageDialog.setMessage(result.getResultInfo());
			messageDialog.show();
		}

	}

}
