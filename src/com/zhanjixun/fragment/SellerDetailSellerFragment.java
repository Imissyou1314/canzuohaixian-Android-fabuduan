package com.zhanjixun.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.BrowseImageActivity;
import com.zhanjixun.activity.ShopDetailActivity;
import com.zhanjixun.adapter.SellerShowImageAdapter;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain.Farmers;
import com.zhanjixun.domain.Fishermen;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.JsonResultUtil;
import com.zhanjixun.utils.JsonUtil;
import com.zhanjixun.views.MessageDialog;

public class SellerDetailSellerFragment extends Fragment implements
		OnDataReturnListener {

	private String Id;
	private String sellerType;
	private Fishermen fishmen = null;
	private Farmers farmer = null;
	private LinearLayout parent;
	private List<String> showImageUrls;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_seller_detail, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
	}

	private void initView() {
		parent = (LinearLayout) getView().findViewById(
				R.id.id_fragment_seller_detail);

	}

	private void initData() {
		Id = ((ShopDetailActivity) getActivity()).getShop().getShopId();
		sellerType = getActivity().getIntent().getStringExtra("sellerType");
		if (Id != null) {
			// 如果是鱼户
			if (sellerType != null && sellerType.equals("fishman")) {
				DC.getInstance().getFishmanDetialInfo(this, Id);
			} else {
				// 如果是养殖户
				DC.getInstance().getFarmerDetialInfo(this, Id);
			}
		}
	}

	private void initSellerData() {
		if (sellerType.equals("fishman")) {
			initFishmanData();
		} else {
			initFarmerData();
		}
	}

	// 初始化鱼户信息
	@SuppressLint("InflateParams")
	private void initFishmanData() {
		LayoutInflater inflater = LayoutInflater.from(getContext());

		String[] textArr = {
				"靠岸点 : " + fishmen.getShipPort(),
				"预计靠岸时间 : "
						+ new SimpleDateFormat("yyyy-MM-dd").format(fishmen
								.getPortTime()),
				"捕捞方式 : " + fishmen.getGetType(),
				"主机功率 : " + fishmen.getEnginePower(),
				"船吨位数 : " + fishmen.getTonnage(), "商家展示" };
		for (String t : textArr) {
			View v = inflater.inflate(R.layout.seller_detail_infoitem, null);
			TextView tv = (TextView) v
					.findViewById(R.id.id_selller_detail_content);
			tv.setText(t);
			tv.setTextColor(0xFF000000);
			parent.addView(v);
		}
		// 初始化商家展示图片
		parent.addView(initShowImageView(fishmen.getShowImageUrls()));
	}

	private Context getContext() {
		return getActivity().getApplicationContext();
	}

	// 初始化养殖户信息
	@SuppressLint({ "InflateParams", "CutPasteId" })
	private void initFarmerData() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View child1 = inflater.inflate(R.layout.seller_detail_infoitem, null);
		TextView getType = (TextView) child1
				.findViewById(R.id.id_selller_detail_content);
		StringBuffer sb = new StringBuffer();
		for (String s : farmer.getGetTypes()) {
			sb.append(s + ",");
		}
		getType.setText("养殖方式  : " + sb.deleteCharAt(sb.length() - 1));
		parent.addView(child1);

		View child2 = inflater.inflate(R.layout.seller_detail_infoitem, null);
		TextView address = (TextView) child2
				.findViewById(R.id.id_selller_detail_content);
		address.setText("养殖地址  :  " + farmer.getAddress());
		parent.addView(child2);

		View child3 = inflater.inflate(R.layout.seller_detail_infoitem, null);
		TextView sellerShow = (TextView) child3
				.findViewById(R.id.id_selller_detail_content);
		sellerShow.setText("商家展示");
		parent.addView(child3);

		parent.addView(initShowImageView(farmer.getShowImageUrls()));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		// if (result.getServiceResult()) {
		// if (TaskTag.FISHMAN_DETAIL.equals(taskTag)) {
		//
		// } else if (TaskTag.FARMER_DETAIL.equals(taskTag)) {
		//
		// }
		// initSellerData();
		// } else {
		// new MessageDialog(getContext(), result.getResultInfo()).show();
		// }
		Map<String, Object> jr = null;
		try {
			jr = JsonUtil.getJosn(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (JsonResultUtil.state(jr)) {
			if (TaskTag.FISHMAN_DETAIL.equals(taskTag)) {
				Map<String, Object> parm = (Map<String, Object>) jr
						.get("resultParm");
				List<Map<String, Object>> fishmanList = (List<Map<String, Object>>) parm
						.get("fishman");
				for (Map map : fishmanList) {
					fishmen = new Fishermen();
					fishmen.setShipPort(map.get("homePort").toString());
					fishmen.setTonnage(map.get("tonnage").toString());
					fishmen.setEnginePower(map.get("enginePower") + "W");
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
					Date date = new Date(map.get("shipCreateTime").toString());
					fishmen.setPortTime(date);
					fishmen.setGetType(map.get("getType").toString());
					fishmen.setShowImageUrls((List<String>) map
							.get("shipPhoto"));
				}
			} else if (TaskTag.FARMER_DETAIL.equals(taskTag)) {
				Map<String, Object> parm = (Map<String, Object>) jr
						.get("resultParm");
				List<String> types = (List<String>) parm.get("getName");
				farmer = new Farmers();
				farmer.setGetTypes(types);// 养殖方式
				farmer.setAddress(parm.get("address").toString());// 地址
				Map<String, Object> fm = (Map<String, Object>) parm
						.get("farmer");
				farmer.setShowImageUrls((List<String>) fm.get("addressPhoto"));
			}
			initSellerData();
		} else {
			new MessageDialog(getContext(), JsonResultUtil.message(jr));
		}
	}

	private GridView initShowImageView(final List<String> urls) {
		showImageUrls = urls;
		GridView showImages = new GridView(getContext());
		showImages.setNumColumns(5);
		showImages.setPadding(10, 5, 10, 0);
		SellerShowImageAdapter ssiAdapter = new SellerShowImageAdapter(
				getContext(), urls);
		showImages.setAdapter(ssiAdapter);
		showImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getContext(),
						BrowseImageActivity.class);
				intent.putExtra("index", position);
				String[] iUrls = showImageUrls.toArray(new String[showImageUrls
						.size()]);
				intent.putExtra("urls", iUrls);
				startActivity(intent);
			}
		});

		return showImages;
	}

}
