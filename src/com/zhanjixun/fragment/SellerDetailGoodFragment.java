package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.activity.ShopDetailActivity;
import com.zhanjixun.adapter.SellerGoodsListAdapter;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.Good;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.views.MessageDialog;
import com.zhanjixun.views.ReflashListView;
import com.zhanjixun.views.ReflashListView.OnRefreshListener;

public class SellerDetailGoodFragment extends Fragment implements
		OnDataReturnListener, OnRefreshListener {

	private int pageIndex = 1;
	private final int PAGE_SIZE = 7;
	private ReflashListView listGoods;
	private SellerGoodsListAdapter adapter;
	private String shopId;
	private List<Good> goods = new ArrayList<Good>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_seller_detail_goods,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
	}

	private void initView() {
		listGoods = (ReflashListView) getView().findViewById(
				R.id.listview_seller_detail_goods);
		adapter = new SellerGoodsListAdapter(getActivity(), goods);
		listGoods.setAdapter(adapter);
		listGoods.setOnRefreshListener(this);
	}

	private void initData() {
		shopId = ((ShopDetailActivity) getActivity()).getShop().getShopId();
		if (shopId != null) {
			DC.getInstance().getSellerGoods(this, shopId, pageIndex++,
					PAGE_SIZE);
		} else {
			LogUtils.v("shopId=null");
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.SELLER_GOODS)) {
				List<Good> g = new Gson().fromJson(
						result.getResultParam().get("goodsList"),
						new TypeToken<List<Good>>() {
						}.getType());
				if (g.size() != 0) {
					goods.addAll(g);
				}
				initListViewData();
				Toast.makeText(getActivity(),
						g.size() == 0 ? "没有更多数据了" : "加载成功", Toast.LENGTH_LONG)
						.show();
			}
		} else {
			new MessageDialog(getActivity(), result.getResultInfo()).show();
		}

	}

	private void initListViewData() {
		adapter.notifyDataSetChanged();
		listGoods.hideFooterView();
	}

	@Override
	public void onLoadingMore(View v) {
		DC.getInstance().getSellerGoods(this, shopId, pageIndex++, PAGE_SIZE);
	}
}
