package com.zhanjixun.base;

import java.util.List;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.zhanjixun.domain2.CarOrder;
import com.zhanjixun.domain2.Good;

public class CheckCarFragment extends Fragment {

	private CarOrder carOrder;

	protected boolean hasData() {
		carOrder = CarOrder.getCarOrder(getActivity());
		List<Good> ordersDetail = carOrder.getOrdersDetail();
		return ordersDetail != null && ordersDetail.size() != 0;
	}

	protected CarOrder getData() {
		if (hasData()) {
			return carOrder;
		}
		return null;
	}

	protected void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
