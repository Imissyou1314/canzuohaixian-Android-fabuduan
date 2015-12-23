package com.zhanjixun.views;

import com.zhanjixun.R;

import android.app.SearchManager.OnCancelListener;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * 更改用户信息
 * 
 * @author Imissyou
 *
 */
public class ChangePasswdDialog implements OnClickListener {

	private ThemeDialog dialog;
	private RelativeLayout view;
	private int id;
	
	private String name;
	private String password;
	private String phone;
	/*是否点击了提交*/
	private boolean isCheck =false;

	public ChangePasswdDialog(Context context, int id, CharSequence title) {
		dialog = new ThemeDialog(context);
		this.id = id;
		this.view = (RelativeLayout) LayoutInflater.from(context).inflate(id, null);
		LinearLayout.LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

		view.setLayoutParams(lp);
		view.setGravity(Gravity.CENTER);
		dialog.setView(view);
		dialog.setTitle(title);
		dialog.setPositiveButton("确定", this);
		dialog.setNegativeButton("取消", null);
		dialog.setDialogSize(0.8, 0.4);
	}

	/**
	 * 展示
	 */
	public void show() {
		isCheck = false;
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	/**
	 * 关掉
	 */
	public void dissmiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		isCheck = true;
		switch (id) {
		case R.layout.dialog_changename:
			EditText nameEt = (EditText) view.findViewById(R.id.dialog_changeName);
			this.name = nameEt.getText().toString();
			break;
		case R.layout.dialog_changepassword:
			EditText passwordEt = (EditText) view.findViewById(R.id.dialog_newPassword);
			EditText passwordEt1 = (EditText) view.findViewById(R.id.dialog_newPassword2);
			EditText passwordEt2 = (EditText) view.findViewById(R.id.dialog_oldPassword);
			String newpassword = passwordEt.getText().toString();
			String surepassword = passwordEt1.getText().toString();
			if (!vaildate(newpassword, surepassword) && passwordEt2.getText().toString() != null) {
				this.password = newpassword;
			}
			break;
		case R.layout.dialog_changephone:
			EditText phoneEt = (EditText) view.findViewById(R.id.dialog_changePhone);
			this.phone = phoneEt.getText().toString();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 检查两次输入的密码是否一致
	 * @param newpassword
	 * @param surepassword
	 * @return
	 */
	private boolean vaildate(String newpassword, String surepassword) {
		if (newpassword.equals(surepassword)) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public boolean getisCheck() {
		return isCheck;
	}
	
}
