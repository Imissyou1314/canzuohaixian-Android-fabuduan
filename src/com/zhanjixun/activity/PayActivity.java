/**
 * 
 */
package com.zhanjixun.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.pingplusplus.android.PaymentActivity;
import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;

/**
 * @author Imissyou
 * @Time  2015年12月2日
 *  
 *  支付功能
 *  
 *  类型:PayActivity
 */
public class PayActivity extends BackActivity implements OnClickListener, OnDataReturnListener{
	
	private ImageView zhifubaoImage;
	private ImageView weixiImage;
	private String channel = "";
    private String OrdersId = "";
	
	private static final int REQUEST_CODE_PAYMENT = 1;
	
	 /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "wx";
    private static final String CHANNEL_ALIPAY = "alipay";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		
		initViews();
	}

	private void initViews() {
		zhifubaoImage = (ImageView) findViewById(R.id.pay_zhifubao_image);
		weixiImage = (ImageView) findViewById(R.id.pay_weixi_image);
		zhifubaoImage.setOnClickListener(this);
		weixiImage.setOnClickListener(this);
		Intent intent = getIntent();
		OrdersId = intent.getStringExtra("order_id");
	}

	@Override
	public void onClick(View v) {
		//按键点击之后的禁用，防止重复点击
		weixiImage.setOnClickListener(null);
		zhifubaoImage.setOnClickListener(null);
		
		if (v.getId() == R.id.pay_zhifubao_image) {
			channel = CHANNEL_ALIPAY;
			
		} else if(v.getId() == R.id.pay_weixi_image) {
			channel = CHANNEL_WECHAT;
		} else {
			Toast.makeText(this, "请选择支付方式...", Toast.LENGTH_LONG).show();
			return ;
		}
		DC.getInstance().pay(this,channel,OrdersId);
	}

	/**
	 * 调用Ping++ 的SDK
	 */
	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		if(null==json){
			Toast.makeText(this, "支付失败...", Toast.LENGTH_LONG).show();
    		showMsg("请求出错", "请检查URL", "URL无法获取charge");
    		return;
    	}
		 /**
         * 获得服务端的charge ==>json，调用ping++ sdk。
         */
		int start = json.indexOf("charge");
		String charge =(String) json.subSequence(start + 8, json.length() -2);
		Log.v("test===>", charge);
		
		Intent intent = new Intent();
		String packageName = getPackageName();
		ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
		intent.setComponent(componentName);
		intent.putExtra(PaymentActivity.EXTRA_CHARGE, charge);
		startActivityForResult(intent, REQUEST_CODE_PAYMENT);
	}
	
	/**
	 * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
	 * 最终支付成功根据异步通知为准
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		zhifubaoImage.setOnClickListener(PayActivity.this);
		weixiImage.setOnClickListener(PayActivity.this);
        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                Log.d("miss", result);
                /* 返回值处理
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }
	
	/**
     * 显示返回的信息
     * 
     * @return void
     */
    public void showMsg(String title, String msg1, String msg2) {
    	String str = title;
    	if (null !=msg1 && msg1.length() != 0) {
    		str += "\n" + msg1;
    	}
    	if (null !=msg2 && msg2.length() != 0) {
    		str += "\n" + msg2;
    	}
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setMessage(str);
    	builder.setTitle("提示");
    	builder.setPositiveButton("OK", null);
    	builder.create().show();
    }

}
