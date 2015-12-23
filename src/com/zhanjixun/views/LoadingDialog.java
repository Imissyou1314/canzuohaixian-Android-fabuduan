package com.zhanjixun.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhanjixun.R;

public class LoadingDialog extends Dialog {

	private TextView msgTv;

	public LoadingDialog(Context context) {
		super(context, R.style.loadingDialog);
		initDialog();
	}

	public LoadingDialog(Context context, String message) {
		super(context, R.style.loadingDialog);
		initDialog();
		setMessage(message);
	}

	private void initDialog() {
		View v = View.inflate(getContext(), R.layout.loading_dialog, null);
		msgTv = (TextView) v.findViewById(R.id.loading_dialog_text);
		setContentView(v);
		setCancelable(false);// 设置不可返回消失
		setCanceledOnTouchOutside(false);// 设置不可按外部消失
	}

	public void setMessage(CharSequence msg) {
		msgTv.setText(msg);
	}
}
