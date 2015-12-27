package com.zhanjixun.views;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public class MessageDialog {
	private Context context;
	private AlertDialog alertDialog;

	public MessageDialog(Context context, CharSequence message) {
		this.context = context;
		AlertDialog.Builder builder = new Builder(this.context);
		builder.setMessage(message);
		builder.setCancelable(true);
		builder.setPositiveButton("È·¶¨", null);
		alertDialog = builder.create();
	}

	public void show() {
		if (alertDialog != null) {
			alertDialog.show();
		}
	}

	public void dissmiss() {
		if (alertDialog != null && alertDialog.isShowing()) {
			alertDialog.dismiss();
		}
	}
}
