package com.zhanjixun.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.LoginActivity;
import com.zhanjixun.data.Constants;

public class CheckLoginFragment extends Fragment {
	private boolean login;
	private Button btn_login;

	protected void checkLogin() {
		login = (Constants.user.getUserId() != null);
	}

	public boolean isLogin() {
		return login;
	}

	protected void initViewsLogin(String title) {
		TextView titleTv = (TextView) getActivity().findViewById(
				R.id.title_me_order_login_msg);
		titleTv.setText(title);
		btn_login = (Button) getActivity().findViewById(R.id.btn_me_login);
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				getActivity().startActivity(intent);
			}
		});
	}
}
