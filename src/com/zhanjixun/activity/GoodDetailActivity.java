package com.zhanjixun.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.adapter.SellerListAdapter;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.Farmer;
import com.zhanjixun.domain2.Fisherman;
import com.zhanjixun.domain2.Seller;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;
import com.zhanjixun.views.ReflashListView;
import com.zhanjixun.views.ReflashListView.OnRefreshListener;
import com.zhanjixun.views.RoundImageView;

public class GoodDetailActivity extends BackActivity implements
		OnDataReturnListener, OnRefreshListener, OnItemClickListener {

	private String categoryId;
	private int pageIndex = 1;
	private final int PAGER_SIZE = 7;
	private TextView title;
	private LoadingDialog dialog;
	private RoundImageView image;
	private ArrayList<Seller> sellers = new ArrayList<Seller>();
	private ReflashListView dataLv;
	private SellerListAdapter adapter;
	private RelativeLayout breedWay_popMenu;
	private RelativeLayout sortWay_popMenu;
	private TextView breedWay_title;
	private TextView sortWay_title;
	private TextView simpleName;
	private TextView academicName;
	private TextView englishName;
	private ImageView imageBg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_good_detail);
		initView();
		initData();
	}

	private void initView() {
		// 设置返回页的名字
		TextView backTv = (TextView) findViewById(R.id.back_good_detail);
		backTv.setText(getIntent().getStringExtra("back"));

		image = (RoundImageView) findViewById(R.id.id_category_good_detail_seafoodImage);
		imageBg = (ImageView) findViewById(R.id.id_category_good_detail_seafoodImage_bg);
		breedWay_popMenu = (RelativeLayout) findViewById(R.id.id_popup1);
		sortWay_popMenu = (RelativeLayout) findViewById(R.id.id_popup2);
		breedWay_title = (TextView) findViewById(R.id.id_breadWay_title);
		sortWay_title = (TextView) findViewById(R.id.id_sortWay_title);
		simpleName = (TextView) findViewById(R.id.id_category_good_detail_seafoodPopularName); // 俗名
		academicName = (TextView) findViewById(R.id.id_category_good_detail_seafoodScienticName); // 学名
		englishName = (TextView) findViewById(R.id.id_category_good_detail_seafoodEnglishName); // 英文名
		title = (TextView) findViewById(R.id.text_gooddetailAty_title);

		dataLv = (ReflashListView) findViewById(R.id.id_list_seller);
		adapter = new SellerListAdapter(this, sellers);
		dataLv.setAdapter(adapter);
		dataLv.setOnRefreshListener(this);
		dataLv.setOnItemClickListener(this);
		initPopupMenu();
	};

	public void initData() {
		title.setText(getIntent().getStringExtra("simpleName"));
		simpleName.setText(getIntent().getStringExtra("simpleName"));
		academicName.setText(getIntent().getStringExtra("academicName"));
		englishName.setText(getIntent().getStringExtra("EnglishName"));
		categoryId = getIntent().getStringExtra("categoryId");

		IC.getInstance().setForegound(getIntent().getStringExtra("iamgeURL"),
				image);
		IC.getInstance().setBlurForegound(this,
				getIntent().getStringExtra("iamgeURL"), imageBg);

		if (!StringUtil.isEmptyString(categoryId)) {
			dialog = new LoadingDialog(this);
			dialog.show();
			DC.getInstance().getAllGoodSellers(this, categoryId, pageIndex++,
					PAGER_SIZE);
		} else {
			LogUtils.w("categoryId为空");
		}
	}

	public void initListData() {
		adapter.notifyDataSetChanged();
		dataLv.hideFooterView();

	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.GOOD_SELLER)) {
				Map<String, String> resultParm = result.getResultParam();

				List<Fisherman> fisher = new Gson().fromJson(
						resultParm.get("shopList"),
						new TypeToken<List<Fisherman>>() {
						}.getType());
				List<Farmer> farmer = new Gson().fromJson(
						resultParm.get("shopList"),
						new TypeToken<List<Farmer>>() {
						}.getType());

				List<Seller> ss = new ArrayList<Seller>();

				for (int i = 0; i < fisher.size(); i++) {
					if (fisher.get(i).getShopType() == Seller.TYPE_FARMER) {
						ss.add(farmer.get(i));
					} else {
						ss.add(fisher.get(i));
					}
				}
				sellers.addAll(ss);
				initListData();

			}
		} else {
			new MessageDialog(this, result.getResultInfo()).show();
		}
	}

	/**
	 * 初始化养殖方式和排序选项菜单
	 */
	public void initPopupMenu() {
		breedWay_popMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(GoodDetailActivity.this, v);
				popupMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.menu_all:
									breedWay_title.setText("全部");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了全部", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_wild:
									breedWay_title.setText("野生");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了野生", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_breed:
									breedWay_title.setText("养殖");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了养殖", Toast.LENGTH_SHORT)
											.show();
									return true;
								default:
									return false;
								}
							}
						});
				MenuInflater mi = popupMenu.getMenuInflater();
				mi.inflate(R.menu.catch_way_menu, popupMenu.getMenu());
				popupMenu.show();
			}
		});

		sortWay_popMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(GoodDetailActivity.this, v);
				popupMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.menu_comprehensive_ranking:
									sortWay_title.setText("综合排序");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了综合排序", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_comment_highest:
									sortWay_title.setText("评价最高");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了评价最高", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_speed_fastest:
									sortWay_title.setText("速度最快");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了速度最快", Toast.LENGTH_SHORT)
											.show();
									return true;
								default:
									return false;
								}
							}
						});
				MenuInflater mi = popupMenu.getMenuInflater();
				mi.inflate(R.menu.sort_way_menu, popupMenu.getMenu());
				popupMenu.show();
			}
		});
	}

	@Override
	public void onLoadingMore(View v) {
		DC.getInstance().getAllGoodSellers(this, categoryId, pageIndex++,
				PAGER_SIZE);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Seller seller = (Seller) parent.getAdapter().getItem(position);
		Intent intent = new Intent(this, ShopDetailActivity.class);
		intent.putExtra("shopId", seller.getShopId());
		if (seller instanceof Farmer) {
			intent.putExtra("sellerType", "farmer");
		} else {
			intent.putExtra("sellerType", "fishman");
		}
		intent.putExtra("back", title.getText());
		startActivity(intent);
	}
}
