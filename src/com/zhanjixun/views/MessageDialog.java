package com.zhanjixun.views;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MessageDialog {
	protected ThemeDialog dialog;
	protected TextView messageTv;

	public MessageDialog(Context context, String msg) {
		dialog = new ThemeDialog(context);
		messageTv = new TextView(context);
		LinearLayout.LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		messageTv.setText(msg);
		messageTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		messageTv.setLayoutParams(lp);
		messageTv.setGravity(Gravity.CENTER);
		dialog.setView(messageTv);
		dialog.setPositiveButton("确定", null);
		dialog.setDialogSize(0.8, 0.4);
	}

	public void setCancelable(boolean flag) {
		dialog.setCancelable(flag);
	}

	public MessageDialog(Context context) {
		this(context, "提示信息");
	}

	public void setTitle(CharSequence title) {
		dialog.setTitle(title);
	}

	public void setMessage(CharSequence msg) {
		messageTv.setText(msg);
	}

	public void show() {
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	public void dissmiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
