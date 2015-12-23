package com.zhanjixun.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AdvertisePagerAdapter extends PagerAdapter {

	private List<ImageView> images;

	public AdvertisePagerAdapter(List<ImageView> list) {
		this.images = list;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView image = images.get(position % images.size());
		container.addView(image);
		return image;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(images.get(position % images.size()));
	}

	@Override
	public int getCount() {
		// 取超大的数，实现无线循环效果
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

}
