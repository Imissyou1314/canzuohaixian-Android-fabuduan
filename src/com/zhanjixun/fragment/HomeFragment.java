package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.baidu.loc.LocationThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.activity.GoodDetailActivity;
import com.zhanjixun.activity.GoodListActivity;
import com.zhanjixun.activity.MainActivity;
import com.zhanjixun.adapter.AdvertisePagerAdapter;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.domain2.MonthHotGood;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class HomeFragment extends Fragment implements OnClickListener,
		OnDataReturnListener {
	private TextView locTv;
	private TextView fishTv;
	private TextView shrimpTv;
	private TextView cradTv;
	private TextView shellfishTv;
	private TextView squidTv;
	private TextView ginsengTv;
	@SuppressWarnings("unused")
	private TextView packTv;
	private TextView othersTv;

	private View hotItem[] = new View[6];

	private LinearLayout points;
	private static ViewPager viewPager;
	private int[] images = { R.drawable.home_ad_image1,
			R.drawable.home_ad_image2, R.drawable.home_ad_image3,
			R.drawable.home_ad_image4 };
	private List<ImageView> imageViews;
	private AdvertisePagerAdapter myAdapter;
	// 圆圈标志位
	private int pointIndex = 0;
	// 线程标志
	private boolean isStop = false;
	private MyPagerListener myListener;
	private AdvertiseThread adThread;
	private Handler handler;
	private LoadingDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		location();
		initAdvertiseView();
	}

	@Override
	public void onStart() {
		isStop = false;
		super.onStart();
	}

	@Override
	public void onResume() {
		// 重回界面时图片继续滚动
		isStop = false;
		super.onResume();
	}

	@Override
	public void onStop() {
		// 切换到其他页面是暂停进程，停止滚动
		isStop = true;
		adThread.pauseThread();
		Log.i("cc", "stop the ad thread------");
		super.onStop();
	}

	private void initViews() {
		locTv = (TextView) getActivity().findViewById(R.id.text_home_loc);
		fishTv = (TextView) getActivity().findViewById(R.id.text_home_fish);
		shrimpTv = (TextView) getActivity().findViewById(R.id.text_home_shrimp);
		cradTv = (TextView) getActivity().findViewById(R.id.text_home_crad);
		shellfishTv = (TextView) getActivity().findViewById(
				R.id.text_home_shellfish);
		squidTv = (TextView) getActivity().findViewById(R.id.text_home_squid);
		ginsengTv = (TextView) getActivity().findViewById(
				R.id.text_home_ginseng);
		packTv = (TextView) getActivity().findViewById(R.id.text_home_pack);
		othersTv = (TextView) getActivity().findViewById(R.id.text_home_others);

		points = (LinearLayout) getActivity().findViewById(R.id.home_points);
		viewPager = (ViewPager) getActivity().findViewById(R.id.home_viewpager);

		fishTv.setOnClickListener(this);
		shrimpTv.setOnClickListener(this);
		cradTv.setOnClickListener(this);
		shellfishTv.setOnClickListener(this);
		squidTv.setOnClickListener(this);
		ginsengTv.setOnClickListener(this);
		othersTv.setOnClickListener(this);

		initMonthHotViews();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent(getActivity(), GoodListActivity.class);
		switch (id) {
		case R.id.text_home_fish:
			intent.putExtra("kind", GoodListActivity.FISH);
			break;
		case R.id.text_home_shrimp:
			intent.putExtra("kind", GoodListActivity.SHRIMP);
			break;
		case R.id.text_home_crad:
			intent.putExtra("kind", GoodListActivity.CRAD);
			break;
		case R.id.text_home_shellfish:
			intent.putExtra("kind", GoodListActivity.SHELLFISH);
			break;
		case R.id.text_home_squid:
			intent.putExtra("kind", GoodListActivity.SQUID);
			break;
		case R.id.text_home_ginseng:
			intent.putExtra("kind", GoodListActivity.GINSENG);
			break;
		case R.id.text_home_others:
			intent.putExtra("kind", GoodListActivity.OTHERS);
			break;
		default:
			break;
		}
		getActivity().startActivity(intent);
	}

	private void location() {

		try {
			locTv.setText(Constants.location.getLocationStr());
		} catch (Exception e) {
			LocationThread locThread = new LocationThread(locTv, getActivity()
					.getApplication());
			locThread.start();
		}

	}

	// 初始化广告滚动图片
	private void initAdvertiseView() {
		imageViews = new ArrayList<ImageView>();
		View point;
		LayoutParams params;
		for (int i = 0; i < images.length; i++) {
			// 设置广告图
			ImageView image = new ImageView(getActivity()
					.getApplicationContext());
			image.setScaleType(ScaleType.FIT_XY);
			image.setImageResource(images[i]);
			imageViews.add(image);
			// 设置圆圈点
			point = new View(getActivity().getApplicationContext());
			params = new LayoutParams(20, 20);
			point.setBackgroundResource(R.drawable.point_bg);
			params.setMargins(0, 0, 10, 0);
			point.setLayoutParams(params);
			point.setEnabled(false);
			points.addView(point);
		}
		myAdapter = new AdvertisePagerAdapter(imageViews);
		viewPager.setAdapter(myAdapter);
		myListener = new MyPagerListener();
		viewPager.setOnPageChangeListener(myListener);
		// 取中间数来作为起始位置
		int index = (Integer.MAX_VALUE / 2)
				- (Integer.MAX_VALUE / 2 % images.length);
		System.out.println("index:" + index);
		// 用来触发监听器
		viewPager.setCurrentItem(index);
		points.getChildAt(pointIndex).setEnabled(true);

		MainActivity mainActivity = (MainActivity) getActivity();
		handler = mainActivity.hanler;
		adThread = new AdvertiseThread() {
			@Override
			public void run() {
				while (!isStop) {
					SystemClock.sleep(2000);
					handler.sendEmptyMessage(1);
				}
			}
		};
		adThread.start();
	}

	public ViewPager getViewPager() {
		return viewPager;
	}

	class MyPagerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {
			int newPosition = position % images.length;
			points.getChildAt(newPosition).setEnabled(true);
			points.getChildAt(pointIndex).setEnabled(false);
			// 更新标志位
			pointIndex = newPosition;
		}

	}

	class HotItemClickListener implements View.OnClickListener {
		private MonthHotGood good;

		public HotItemClickListener(MonthHotGood good) {
			this.good = good;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeFragment.this.getActivity(),
					GoodDetailActivity.class);
			intent.putExtra("back", "首页");
			intent.putExtra("iamgeURL", good.getFishPhoto());
			intent.putExtra("categoryId", good.getCategoryId());
			intent.putExtra("simpleName", good.getCategorySimpleName());
			intent.putExtra("academicName", good.getCategoryAcademicName());
			intent.putExtra("EnglishName", good.getCategoryEnglishName());
			startActivity(intent);
		}
	}

	private void initMonthHotViews() {
		hotItem[0] = getActivity().findViewById(R.id.hot_item1);
		hotItem[1] = getActivity().findViewById(R.id.hot_item2);
		hotItem[2] = getActivity().findViewById(R.id.hot_item3);
		hotItem[3] = getActivity().findViewById(R.id.hot_item4);
		hotItem[4] = getActivity().findViewById(R.id.hot_item5);
		hotItem[5] = getActivity().findViewById(R.id.hot_item6);

		dialog = new LoadingDialog(getActivity());
		dialog.show();
		DC.getInstance().getHotItems(this);
	}

	private void initMonthHotData(List<MonthHotGood> goods) {
		for (int i = 0; i < goods.size(); i++) {
			MonthHotGood good = goods.get(i);
			ImageView imag = (ImageView) hotItem[i]
					.findViewById(R.id.id_hotImage);
			TextView simpleName = (TextView) hotItem[i]
					.findViewById(R.id.id_hot_item_seafoodPopularName);
			TextView academicName = (TextView) hotItem[i]
					.findViewById(R.id.id_hot_item_seafoodScientificName);
			TextView englishName = (TextView) hotItem[i]
					.findViewById(R.id.id_hot_item_seafoodEnglishName);
			TextView sellNumber = (TextView) hotItem[i]
					.findViewById(R.id.id_hot_item_salesValue);

			simpleName.setText(good.getCategorySimpleName());
			academicName.setText(good.getCategoryAcademicName());
			englishName.setText(good.getCategoryEnglishName());
			sellNumber.setText(good.getTotalSellNumber() + "");
			// 加载图片
			IC.getInstance().setBackground(good.getFishPhoto(), imag);
			hotItem[i].setOnClickListener(new HotItemClickListener(good));
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.MONTH_HOT)) {
				List<MonthHotGood> goods = new ArrayList<MonthHotGood>();
				goods = new Gson().fromJson(
						result.getResultParam().get("categoryList"),
						new TypeToken<List<MonthHotGood>>() {
						}.getType());
				initMonthHotData(goods);
			}
		} else {
			new MessageDialog(getActivity(), result.getResultInfo()).show();
		}

	}
}