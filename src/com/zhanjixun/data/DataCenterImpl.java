package com.zhanjixun.data;

public class DataCenterImpl extends DataCenter {
	private static DataCenterImpl dc;

	private DataCenterImpl() {
	}

	// µ¥ÀýÄ£Ê½
	public static DataCenterImpl getInstance() {
		if (dc == null) {
			synchronized (DataCenterImpl.class) {
				if (dc == null)
					dc = new DataCenterImpl();
			}
		}
		return dc;
	}

}
