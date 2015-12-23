package com.zhanjixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.AddressManageActivity;
import com.zhanjixun.activity.MyCommentActivity;
import com.zhanjixun.activity.MyInfoActivity;
import com.zhanjixun.base.CheckLoginFragment;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.IC;
import com.zhanjixun.views.RoundImageView;

public class MeFragment extends CheckLoginFragment implements OnClickListener {

	private LinearLayout linear_address;
	private LinearLayout linear_comment;
	private TextView addressTv;
	private TextView commentTv;
	private ImageView addressImg;
	private ImageView commentImg;
	private ImageView rela_face_bg;
	private RoundImageView image_face;
	private TextView userName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		checkLogin();
		if (!isLogin()) {
			return inflater.inflate(R.layout.fragment_me_unlogin, container,
					false);
		} else {
			return inflater.inflate(R.layout.fragment_me, container, false);
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!isLogin()) {
			initViewsLogin(getActivity().getResources().getString(
					R.string.main_me));
		} else {
			initViews();
			initData();
		}
	}

	private void initData() {
		IC.getInstance().setForegound(Constants.user.getHeadImage(), image_face);
		IC.getInstance().setBlurForegound(getActivity(),
				Constants.user.getHeadImage(), rela_face_bg);
		userName.setText(Constants.user.getUserName());
	}

	private void initViews() {
		rela_face_bg = (ImageView) getActivity().findViewById(R.id.image_me_bg);
		image_face = (RoundImageView) getActivity().findViewById(
				R.id.img_me_face);
		image_face.setOnClickListener(this);

		linear_address = (LinearLayout) getActivity().findViewById(
				R.id.linear_me_address);
		linear_comment = (LinearLayout) getActivity().findViewById(
				R.id.linear_me_comment);
		linear_address.setOnClickListener(this);
		linear_comment.setOnClickListener(this);

		addressTv = (TextView) getActivity().findViewById(R.id.text_me_address);
		commentTv = (TextView) getActivity().findViewById(R.id.text_me_comment);
		userName = (TextView) getActivity().findViewById(R.id.text_me_username);
		addressTv.setOnClickListener(this);
		commentTv.setOnClickListener(this);

		addressImg = (ImageView) getActivity().findViewById(
				R.id.image_me_address);
		commentImg = (ImageView) getActivity().findViewById(
				R.id.image_me_comment);
		addressImg.setOnClickListener(this);
		commentImg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag.equals("address")) {
			Intent intent = new Intent(getActivity(),
					AddressManageActivity.class);
			getActivity().startActivity(intent);
		} else if (tag.equals("comment")) {
			Intent intent = new Intent(getActivity(), MyCommentActivity.class);
			getActivity().startActivity(intent);
		} else if (tag.equals("face")) {
			Intent intent = new Intent(getActivity(), MyInfoActivity.class);
			getActivity().startActivity(intent);
		}
	}

}