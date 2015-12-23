package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain.Appraisal;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class AppraiseActivity extends BackActivity implements
		OnDataReturnListener, OnClickListener, OnRatingBarChangeListener,
		OnCheckedChangeListener {

	private Appraisal appraisal;
	private ImageView faceImage;

	private RadioGroup radioGroup;

	private RatingBar weightQuality;
	private RatingBar freshQuality;
	private RatingBar speedQuality;

	private TextView weightText;
	private TextView freshText;
	private TextView speedText;

	private CheckBox anonymity;
	private EditText contentEdit;

	private Button submitBtn;
	private MessageDialog msg;
	private LoadingDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_grade_j);
		initViews();
		initData();

	}

	private void initViews() {
		appraisal = new Appraisal();
		msg = new MessageDialog(this);
		dialog = new LoadingDialog(this);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		contentEdit = (EditText) findViewById(R.id.grade_appraise_edit);
		weightQuality = (RatingBar) findViewById(R.id.order_grade_ratBar_zujzuc);
		freshQuality = (RatingBar) findViewById(R.id.order_grade_ratBar_xinxdu);
		speedQuality = (RatingBar) findViewById(R.id.order_grade_ratBar_fahuosdu);
		weightText = (TextView) findViewById(R.id.order_grade_ratBar_number1);
		freshText = (TextView) findViewById(R.id.order_grade_ratBar_number2);
		speedText = (TextView) findViewById(R.id.order_grade_ratBar_number3);
		anonymity = (CheckBox) findViewById(R.id.grade_anonymity_checkbox);
		faceImage = (ImageView) findViewById(R.id.face_gooditem);
		submitBtn = (Button) findViewById(R.id.grade_submit_Btn);
		//默认5星
		weightQuality.setRating(5.0f);
		freshQuality.setRating(5.0f);
		speedQuality.setRating(5.0f);
		weightText.setText(weightQuality.getRating() + "");
		freshText.setText(freshQuality.getRating() + "");
		speedText.setText(speedQuality.getRating() + "");

		radioGroup.setOnCheckedChangeListener(this);
		weightQuality.setOnRatingBarChangeListener(this);
		freshQuality.setOnRatingBarChangeListener(this);
		speedQuality.setOnRatingBarChangeListener(this);
		submitBtn.setOnClickListener(this);

	}

	/*
	 * 初始化数据
	 */
	private void initData() {
		appraisal.setOrder_id(getIntent().getStringExtra("order_id"));
		appraisal.setUser_id(Constants.user.getUserId());
		appraisal.setShop_id(getIntent().getStringExtra("shop_id"));
		// 默认好评
		appraisal.setConmment_kind(1);
		appraisal.setConmment_fresh(5.0f);
		appraisal.setConmment_speed(5.0f);
		appraisal.setConmment_weight(5.0f);
		IC.getInstance().setForegound(getIntent().getStringExtra("imageUrl"),
				faceImage);
	}

	/*
	 * 返回评论结果
	 */
	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		msg.setMessage(result.getResultInfo());
		msg.show();
	}

	/*
	 * submit评论
	 */
	@Override
	public void onClick(View v) {
		appraisal.setConmment_text(contentEdit.getText().toString());
		if (isChecked()) {
			msg.setMessage("请输入完整信息！");
			msg.show();
			return;
		}
		if (anonymity.isChecked()) {
			appraisal.setAnonymous(1);
		} else {
			appraisal.setAnonymous(0);
		}
		dialog.show();
		DC.getInstance().AddAppraise(this, appraisal);
	}

	/**
	 * 校证是否为满足提交要求
	 */
	private boolean isChecked() {
		return appraisal.getConmment_text() == null
				|| appraisal.getConmment_text().equals("");
	}

	/*
	 * 设置RatingBar的选择值
	 */
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {

		weightText.setText(weightQuality.getRating() + "");
		freshText.setText(freshQuality.getRating() + "");
		speedText.setText(speedQuality.getRating() + "");

		appraisal.setConmment_weight(weightQuality.getRating());
		appraisal.setConmment_fresh(freshQuality.getRating());
		appraisal.setConmment_speed(speedQuality.getRating());
	}

	/*
	 * 评论类型
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.good:
			appraisal.setConmment_kind(1);
			break;
		case R.id.mid:
			appraisal.setConmment_kind(0);
			break;
		case R.id.bad:
			appraisal.setConmment_kind(-1);
			break;
		default:
			break;
		}

	}

}
