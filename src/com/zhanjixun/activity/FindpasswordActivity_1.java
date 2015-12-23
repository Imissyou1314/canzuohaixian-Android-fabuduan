package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class FindpasswordActivity_1 extends BackActivity implements
		OnDataReturnListener {
	private Button btnNext;
	private EditText phoneEt;
	private Button codeBtn;
	private LoadingDialog dialog;
	private EditText passwordEt;
	private MessageDialog msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password1);
		initViews();
	}

	private void initViews() {
		phoneEt = (EditText) findViewById(R.id.edit_findpassword_phone);
		passwordEt = (EditText) findViewById(R.id.edit_findpassword_password);
		codeBtn = (Button) findViewById(R.id.btn_findpassword_get_code);
		btnNext = (Button) findViewById(R.id.btn_findpassword_next);
	}

	public void onClick(View v) {
		int viewId = v.getId();

		String id = phoneEt.getText().toString();
		String code = passwordEt.getText().toString();

		if (viewId == btnNext.getId()) {
			if (!StringUtil.isEmptyString(id)
					&& !StringUtil.isEmptyString(code)) {
				dialog = new LoadingDialog(this, "正在请求...");
				dialog.show();
				DC.getInstance().checkMsgCode(this, id, code);
			} else {
				msg = new MessageDialog(this, "请输入完整信息！");
				msg.show();
			}
		} else if (viewId == codeBtn.getId()) {
			if (!StringUtil.isEmptyString(id)) {
				dialog = new LoadingDialog(this, "正在请求...");
				dialog.show();
				DC.getInstance().requestCodeForFindPassword(this, id);
			} else {
				msg = new MessageDialog(this, "请输入完整信息！");
				msg.show();
			}
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.REQUEST_MSG_CODE)) {
				msg = new MessageDialog(this, result.getResultInfo());
				msg.show();
			} else if (taskTag.equals(TaskTag.CHECK_MSG_CODE)) {
				Intent intent = new Intent(this, FindpasswordActivity_2.class);
				intent.putExtra("phone", phoneEt.getText().toString());
				startActivity(intent);
			}
		} else {
			msg.setMessage(result.getResultInfo());
			msg.show();
		}
	}
}
