package com.zhanjixun.data;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.zhanjixun.domain.Appraisal;
import com.zhanjixun.domain2.CarOrder;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;

public class DC extends DataCenter {

	private static DC dc;

	private DC() {
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	/**
	 * 用户登录
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void userLogin(OnDataReturnListener dataReturnListener, String id,
			String pw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		params.put("password", pw);
		getDatasFromServer(TaskTag.LOGIN, "fishshop/user_userLogin.action",
				params, dataReturnListener);
	}

	/**
	 * 用户注册请求短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void requestCodeForRegister(OnDataReturnListener dataReturnListener,
			String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.REGISTER_CODE,
				"fishshop/user_smsCodeOfRegister.action", params,
				dataReturnListener);
	}

	/**
	 * 用户登录
	 * 
	 * @param dataReturnListener
	 * @param phone
	 * @param checkCode
	 * @param password
	 */
	public void register(OnDataReturnListener dataReturnListener, String phone,
			String checkCode, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", phone);
		params.put("verifyCode", checkCode);
		params.put("password", password);
		getDatasFromServer(TaskTag.REGISTER, "fishshop/user_addUser.action",
				params, dataReturnListener);
	}

	/**
	 * 找回密码请求短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void requestCodeForFindPassword(
			OnDataReturnListener dataReturnListener, String id) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.REQUEST_MSG_CODE,
				"fishshop/user_smsCodeOfFound.action", params,
				dataReturnListener);
	}

	/**
	 * 验证码找回密码的短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param code
	 */
	public void checkMsgCode(OnDataReturnListener dataReturnListener,
			String id, String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("verifyCode", code);
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.CHECK_MSG_CODE,
				"fishshop/user_confirmSmsVerifyCode.action", params,
				dataReturnListener);
	}

	/**
	 * 找回密码
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void findPassword(OnDataReturnListener dataReturnListener,
			String id, String pw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("password", pw);
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.FIND_PASSWORD,
				"fishshop/user_findPassword.action", params, dataReturnListener);
	}

	/**
	 * 获取用户所有收货地址
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void getAddrees(OnDataReturnListener dataReturnListener, String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", id);
		getDatasFromServer(TaskTag.GET_ADDREES,
				"fishshop/getaddress_getUserAllAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 修改收货地址
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param addressId
	 * @param name
	 * @param adsPhone
	 * @param address
	 * @param mail
	 */
	public void changeAddress(OnDataReturnListener dataReturnListener,
			String id, String addressId, String name, String adsPhone,
			String address, String mail) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("address_id", addressId);
		params.put("UserId", id);
		params.put("consignee", name);
		params.put("address", address);
		params.put("phone", adsPhone);
		params.put("zip_code", mail);
		getDatasFromServer(TaskTag.CHANGE_ADDRESS,
				"fishshop/getaddress_updateGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 获取二级分类商品
	 * 
	 * @param dataReturnListener
	 * @param categoryId
	 * @param indexPageNum
	 * @param size
	 */
	public void getGoodList(OnDataReturnListener dataReturnListener,
			String categoryId, int indexPageNum, int size) {
		Map<String, String> params = new IdentityHashMap<String, String>();

		params.put(new String("categoryId"), categoryId);
		params.put(new String("pageInfo.indexPageNum"), indexPageNum + "");
		params.put(new String("pageInfo.size"), size + "");

		getDatasFromServer(TaskTag.GOOD_LIST,
				"fishshop/category_getNextLevelCategory.action", params,
				dataReturnListener);
	}

	/**
	 * 删除收货地址
	 * 
	 * @param dataReturnListener
	 * @param addressId
	 */
	public void deleteAdress(OnDataReturnListener dataReturnListener,
			String addressId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("getAddressId", addressId);
		getDatasFromServer(TaskTag.DELETE_ADDRESS,
				"fishshop/getaddress_deleteGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商品在售商家
	 * 
	 * @param dataReturnListener
	 * @param categoryId
	 * @param page
	 * @param item
	 */
	public void getAllGoodSellers(OnDataReturnListener dataReturnListener,
			String categoryId, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("categoryId"), categoryId);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.GOOD_SELLER,
				"fishshop/category_getGoodsShops.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家销售的商品
	 * 
	 * @param dataReturnListener
	 * @param shopId
	 * @param page
	 * @param item
	 */
	public void getSellerGoods(OnDataReturnListener dataReturnListener,
			String shopId, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), shopId);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.SELLER_GOODS,
				"fishshop/shop_getShopAllGoods.action", params,
				dataReturnListener);
	}

	/**
	 * 获取热门商品
	 * 
	 * @param dataReturnListener
	 */
	public void getHotItems(OnDataReturnListener dataReturnListener) {
		getDatasFromServer(TaskTag.MONTH_HOT,
				"fishshop/category_getHotCategory.action", null,
				dataReturnListener);
	}

	/**
	 * 获取商家全部评论
	 * 
	 * @param dataReturnListener
	 * @param Id
	 * @param page
	 * @param item
	 */
	public void getAllComments(OnDataReturnListener dataReturnListener,
			String Id, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.COMMENT_DETAIL,
				"fishshop/comment_getShopComment.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家好评
	 * 
	 * @param dataReturnListener
	 * @param Id
	 * @param page
	 * @param item
	 */
	public void getGoodComments(OnDataReturnListener dataReturnListener,
			String Id, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.COMMENT_DETAIL,
				"fishshop/comment_getGoodComment.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家中评
	 * 
	 * @param dataReturnListener
	 * @param Id
	 * @param page
	 * @param item
	 */
	public void getMidComments(OnDataReturnListener dataReturnListener,
			String Id, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.COMMENT_DETAIL,
				"fishshop/comment_getMidComment.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家差评
	 * 
	 * @param dataReturnListener
	 * @param Id
	 * @param page
	 * @param item
	 */
	public void getBadComments(OnDataReturnListener dataReturnListener,
			String Id, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.COMMENT_DETAIL,
				"fishshop/comment_getBedComment.action", params,
				dataReturnListener);
	}

	/**
	 * 评分提交
	 * 
	 * @author Imissyou
	 * @param appraiseActivity
	 * @param submitData
	 * @return void
	 */
	public void AddAppraise(OnDataReturnListener dataReturnListener,
			Map<String, Object> submitData) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("shopId", submitData.get("shopId").toString());
		params.put("userId", submitData.get("userId").toString());
		params.put("orderId", submitData.get("orderId").toString());
		params.put("commentType", submitData.get("commentType").toString());
		params.put("content", submitData.get("content").toString());
		params.put("weightQuality", submitData.get("weightQuality").toString());
		params.put("freshQuality", submitData.get("freshQuality").toString());
		params.put("speedQuality", submitData.get("speedQuality").toString());
		params.put("anonymity", submitData.get("anonymity").toString());
		getDatasFromServer(TaskTag.APPRAISE_COMMIT,
				"fishshop/comment_addComment.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户的评论
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param page
	 * @param item
	 */
	public void getMyComment(OnDataReturnListener dataReturnListener,
			String id, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", id);
		params.put("pageInfo.indexPageNum", page + "");
		params.put("pageInfo.size", item + "");
		getDatasFromServer(TaskTag.MY_COMMENT,
				"fishshop/comment_getUserComment.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户全部订单
	 * 
	 * @param dataReturnListener
	 * @param userId
	 * @param index
	 * @param pageSize
	 */
	public void getAllOrder(OnDataReturnListener dataReturnListener,
			String userId, int index, int pageSize) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", userId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", pageSize + "");
		getDatasFromServer(TaskTag.ORDER_ALL,
				"fishshop/orders_getAllUserOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户未付款订单
	 * 
	 * @param dataReturnListener
	 * @param userId
	 * @param index
	 * @param pageSize
	 */
	public void getUnPayOrder(OnDataReturnListener dataReturnListener,
			String userId, int index, int pageSize) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", userId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", pageSize + "");
		getDatasFromServer(TaskTag.ORDER_UN_PAY,
				"fishshop/orders_getUnPayOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户未收货订单
	 * 
	 * @param dataReturnListener
	 * @param userId
	 * @param index
	 * @param pageSize
	 */
	public void getUngetOrder(OnDataReturnListener dataReturnListener,
			String userId, int index, int pageSize) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", userId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", pageSize + "");
		getDatasFromServer(TaskTag.ORDER_UN_GET,
				"fishshop/orders_getUngetOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户未发送订单
	 * 
	 * @param dataReturnListener
	 * @param userId
	 * @param index
	 * @param pageSize
	 */
	public void getUnSentOrder(OnDataReturnListener dataReturnListener,
			String userId, int index, int pageSize) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", userId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", pageSize + "");
		getDatasFromServer(TaskTag.ORDER_UN_SENT,
				"fishshop/orders_getUnSentOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户未评价订单
	 * 
	 * @param dataReturnListener
	 * @param userId
	 * @param index
	 * @param pageSize
	 */
	public void getUnCommentOrder(OnDataReturnListener dataReturnListener,
			String userId, int index, int pageSize) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", userId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", pageSize + "");
		getDatasFromServer(TaskTag.ORDER_UN_COMMENT,
				"fishshop/orders_getUnCommentOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 获取订单信息
	 * 
	 * @param dataReturnListener
	 * @param orderId
	 */
	public void getOrderInfo(OnDataReturnListener dataReturnListener,
			String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", orderId);
		getDatasFromServer(TaskTag.ORDER_INFO, "orders_getPointOrders.action",
				params, dataReturnListener);
	}

	/**
	 * @author Imissyou
	 * @param string
	 * @param order_information_Activity
	 * @param order_id
	 *            支付功能接口
	 * @return void
	 */
	public void payOrders(String taskTag,
			OnDataReturnListener dataReturnListener, String order_id) {

		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("odersId", order_id);
		LogUtils.d(params.toString());
		getDatasFromServer(taskTag,
				"fishshop/orders_payForOrdersWithAlipay.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家评价概要
	 * 
	 * @param dataReturnListener
	 * @param Id
	 */
	public void getCommentsSummary(OnDataReturnListener dataReturnListener,
			String Id) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);

		getDatasFromServer(TaskTag.COMMENT_SUMMARY,
				"fishshop/shop_getShopCommentInfo.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家基础信息
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 */
	public void getSellerBaseInfo(OnDataReturnListener dataReturnListener,
			String sellerId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		getDatasFromServer(TaskTag.SELLER_INFO, "fishshop/shop_getShop.action",
				params, dataReturnListener);
	}

	/**
	 * 购物车提交订单
	 * 
	 * @param dataReturnListener
	 * @param addressId 
	 * @param userId
	 * @param shopId
	 * @param buyersName
	 * @param shopName
	 * @param seaRecordId
	 * @param order
	 */
	public void commitOrder(OnDataReturnListener dataReturnListener,
			CarOrder carOrder, String addressId) {

		Map<String, String> params = new HashMap<String, String>();
		String json = new Gson().toJson(carOrder);
		params.put("getAddressId", addressId);
		params.put("orders", json);
		LogUtils.v(json);
		getDatasFromServer(TaskTag.COMMIT_ORDER, "orders_addOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 获取订单的快递号
	 * 
	 * @param dataReturnListener
	 * @param ordersId
	 */
	public void orderPostCode(OnDataReturnListener dataReturnListener,
			String ordersId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", ordersId);
		getDatasFromServer(TaskTag.ORDER_POST_CODE,
				"orders_getOrderNumber.action", params, dataReturnListener);
	}

	/**
	 * 获取物流信息
	 * 
	 * @param dataReturnListener
	 * @param postid
	 * @param type
	 */
	public void getLogistic(OnDataReturnListener dataReturnListener,
			String postid) {
		// http://www.kuaidi100.com/query?type=zhongtong&postid=719121392152
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "zhongtong");
		params.put("postid", postid);
		getLogistics(TaskTag.LOGISTIC, params, dataReturnListener);
	}

	/**
	 * 得到指定鱼户的详情信息
	 * 
	 * @param dataReturnListener
	 * @param Id
	 */
	public void getFishmanDetialInfo(OnDataReturnListener dataReturnListener,
			String Id) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);

		getDatasFromServer(TaskTag.FISHMAN_DETAIL,
				"fishshop/fishman_getFishmanByShop.action", params,
				dataReturnListener);
	}

	/**
	 * 得到指定养殖户的详情信息
	 * 
	 * @param dataReturnListener
	 * @param Id
	 */
	public void getFarmerDetialInfo(OnDataReturnListener dataReturnListener,
			String Id) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);

		getDatasFromServer(TaskTag.FARMER_DETAIL,
				"fishshop/farmers_getFarmerByShop.action", params,
				dataReturnListener);
	}

	/**
	 * 支付接口
	 * 
	 * @author Imissyou
	 * @param payAction
	 * @param channel
	 * @param ordersId
	 */
	public void pay(OnDataReturnListener dataReturnListener, String channel,
			String ordersId) {
		Map<String, String> params = new HashMap<>();
		params.put("channel", channel);
		params.put("ordersId", ordersId);
		getDatasFromServer(TaskTag.PAY, "orders_payForOrdersWithAlipay.action",
				params, dataReturnListener);
	}

	/**
	 * 确认收货
	 * 
	 * @param dataReturnListener
	 * @param orderId
	 */
	public void ensureGet(OnDataReturnListener dataReturnListener,
			String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", orderId);
		getDatasFromServer(TaskTag.ENSUREGET, "orders_realGetOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 评分提交
	 * 
	 * @author Imissyou
	 * @param appraiseActivity
	 * @param submitData
	 */
	public void AddAppraise(OnDataReturnListener dataReturnListener,
			Appraisal appraisal) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("shopId", appraisal.getShop_id());
		params.put("userId", appraisal.getUser_id());
		params.put("ordersId", appraisal.getOrder_id());
		params.put("commentType", appraisal.getConmment_kind() + "");
		params.put("content", appraisal.getConmment_text());
		params.put("weightQuality", appraisal.getConmment_weight() + "");
		params.put("freshQuality", appraisal.getConmment_fresh() + "");
		params.put("speedQuality", appraisal.getConmment_speed() + "");
		params.put("anonymity", appraisal.getAnonymous() + "");
		getDatasFromServer(TaskTag.APPRAISE_COMMIT,
				"fishshop/comment_addComment.action", params,
				dataReturnListener);
	}

	/**
	 * 添加收货地址
	 * 
	 * @param dataReturnListener
	 * @param phone
	 * @param name
	 * @param adsPhone
	 * @param address
	 * @param mail
	 */
	public void addAddress(OnDataReturnListener dataReturnListener,
			String phone, String name, String adsPhone, String address,
			String mail) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", phone);
		params.put("userName", name);
		params.put("address", address);
		params.put("phone", adsPhone);
		params.put("postcode", mail);

		getDatasFromServer(TaskTag.ADD_ADDRESS,
				"fishshop/getaddress_addGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 修改收货地址
	 * 
	 * @param dataReturnListener
	 * @param addressId
	 * @param phone
	 * @param name
	 * @param adsPhone
	 * @param address
	 * @param mail
	 */
	public void updataAddress(OnDataReturnListener dataReturnListener,
			String addressId, String phone, String name, String adsPhone,
			String address, String mail) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("getAddressId", addressId);
		params.put("userId", phone);
		params.put("userName", name);
		params.put("address", address);
		params.put("phone", adsPhone);
		params.put("postcode", mail);
		getDatasFromServer(TaskTag.UPDATA_ADDRESS,
				"fishshop/getaddress_addGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 修改默认收货地址
	 * 
	 * @param dataReturnListener
	 * @param addressId
	 * @param userId
	 */
	public void defultaAdress(OnDataReturnListener dataReturnListener,
			String addressId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("getAddressId", addressId);
		// getaddress_updateUserAddress.action
		getDatasFromServer(TaskTag.DEFULT_AADRESS,
				"getaddress_updateUserAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 得到用默认收货地址
	 * 
	 * @param dataReturnListener
	 * @param userId
	 */
	public void getDefultAddress(OnDataReturnListener dataReturnListener,
			String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		getDatasFromServer(TaskTag.GET_DEFULT_ADDRESS,
				"getaddress_getDefaultAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 得到订单的邮费
	 * 
	 * @param dataReturnListener
	 * @param carOrder
	 * @param addressId
	 */
	public void getOrdersPostage(OnDataReturnListener dataReturnListener,
			CarOrder carOrder, String addressId) {
		Map<String, String> params = new HashMap<String, String>();
		String json = new Gson().toJson(carOrder);
		params.put("orders", json);
		params.put("getAddressId", addressId);
		LogUtils.v(json);
		getDatasFromServer(TaskTag.GET_ORDERS_POSTAGE,
				"orders_getOrdersPostage.action", params, dataReturnListener);
	}
	
	/**
	 * 更改用户名
	 */
	public void changeUserName(OnDataReturnListener dataReturnListener, String userId, String userName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("userName", userName);
		getDatasFromServer(TaskTag.CHANGE_USER_NAME,
				"fishshop/user_updateUser.action", params, dataReturnListener);
	}
	
	/**
	 * 更改用户密码
	 */
	public void changeUserPassword(OnDataReturnListener dataReturnListener, int phoneNumber, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", phoneNumber + "");
		params.put("password", password);
		getDatasFromServer(TaskTag.CHANGE_USER_PASSWORD,
				"fishshop/user_ findPassword.action", params, dataReturnListener);
	}
	
	/**
	 * 更改用户手机
	 */
	public void changeUserPhone(OnDataReturnListener dataReturnListener, int userId, String userName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId + "");
		params.put("userName", userName);
		getDatasFromServer(TaskTag.CHANGE_USER_NAME,
				"fishshop/user_updateUser.action", params, dataReturnListener);
	}
}
