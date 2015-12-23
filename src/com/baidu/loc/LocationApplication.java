package com.baidu.loc;

import android.app.Application;
import android.widget.TextView;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zhanjixun.data.Constants;
import com.zhanjixun.domain2.Location;
import com.zhanjixun.utils.LogUtils;

public class LocationApplication extends Application {
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	public TextView mLocationResult;

	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationClient.registerLocationListener(new MyLocationListener());
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			try {
				Address address = location.getAddress();
				StringBuffer buffer = new StringBuffer();
				if (address.city != null) {
					buffer.append(address.city);
				}
				if (address.district != null) {
					buffer.append(address.district);
				}
				if (address.street != null) {
					buffer.append(address.street);
				}
				if (address.streetNumber != null) {
					buffer.append(address.streetNumber);
				}
				String addressStr = buffer.toString();
				if (addressStr != null) {
					logMsg(addressStr);
					Constants.location = new Location();
					Constants.location.setLatitude(location.getLatitude());
					Constants.location.setLongitude(location.getLongitude());
					Constants.location.setLocationStr(addressStr);
					mLocationClient.stop();
				} else {
					logMsg("定位失败");
				}
			} catch (Exception e) {
				logMsg("定位失败");
			}
		}
	}

	public void logMsg(String str) {
		try {
			mLocationResult.setText(str);
			LogUtils.v(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
