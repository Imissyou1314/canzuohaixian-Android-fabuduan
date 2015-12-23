package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.SPUtil;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.ChangePasswdDialog;
import com.zhanjixun.views.DoubleButtonMessageDialog;

public class MyInfoActivity extends BackActivity implements OnDataReturnListener{
	private TextView phoneTv;
	private TextView usernameTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_myinfo);
		initViews();

	}

	@Override
	protected void onStart() {
		super.onStart();
		initData();
	}

	private void initData() {
		phoneTv.setText(StringUtil.encryptPhoneNumber(Constants.user
				.getPhoneNumber()));
		usernameTv.setText(Constants.user.getUserName());
	}

	private void initViews() {
		phoneTv = (TextView) findViewById(R.id.text_myinfo_phone);
		usernameTv = (TextView) findViewById(R.id.text_myinfo_username);
	}

	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag.equals("headImage")) {
			
			//TODO
			Log.v("Name", "更新用户头像");
		} else if (tag.equals("name")) {
			//TODO
			Log.v("Name", "更新用户名");
			ChangePasswdDialog dialog = new ChangePasswdDialog(this, R.layout.dialog_changename, "更改用户名");
			dialog.show();
			if (dialog.getisCheck()) {
				DC.getInstance().changeUserName(this, Constants.user.getUserId(), dialog.getName());
			}
		} else if (tag.equals("password")) {
			ChangePasswdDialog dialog = new ChangePasswdDialog(this, R.layout.dialog_changepassword, "更改用户密码");
			dialog.show();
			if (dialog.getisCheck()) {
				DC.getInstance().changeUserName(this, Constants.user.getPhoneNumber(), dialog.getPassword());
			}
			
			//TODO
			Log.v("Name", "更新用户密码");
		} else if (tag.equals("phone")) {
			ChangePasswdDialog dialog = new ChangePasswdDialog(this, R.layout.dialog_changephone, "更改用户手机号");
			dialog.show();
			if (dialog.getisCheck()) {
				DC.getInstance().changeUserName(this, Constants.user.getPhoneNumber(), dialog.getPhone());
			}
			Log.v("Name", "更新用户手机号");
		} else if (tag.equals("exit")) {
			DoubleButtonMessageDialog dialog = new DoubleButtonMessageDialog(
					this, "确定要退出?");
			dialog.setPositiveButton("退出", new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					SPUtil.deleteSP(MyInfoActivity.this,
							Constants.XML_USER);
					Constants.user = null;

					Intent intent = new Intent(MyInfoActivity.this,
							MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					MyInfoActivity.this.startActivity(intent);
				}
			});
			dialog.setNegativeButton("返回", null);
			dialog.show();
		}
	}
	
	/**
	 * 更新返回状态
	 */
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		Log.v(taskTag, "更新成功");
	}
}
