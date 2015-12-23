package com.zhanjixun.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BrowseViewPagerAdapter extends PagerAdapter {

	private List<ImageView> images;
	
	public BrowseViewPagerAdapter(List<ImageView> images) {
		this.images = images;
	}


	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = images.get(position);
		container.addView(iv);
		return iv;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(images.get(position));
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

}
