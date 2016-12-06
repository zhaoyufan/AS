package com.upic.asn.model;

import java.util.Date;

public class Store {

	/** 用户名 **/
	private String userName;
	/** 商家编号 **/
	private String storeNumber;
	/** 密码 **/
	private String passWord;
	/** 店铺名称 **/
	private String storeName;
	/** 主营商品 **/
	private String mainShops;
	/** 店铺logo **/
	private String logo;
	/** 店铺图片 **/
	private String picture;
	/** 地址ID **/
	private Integer addressId;
	/** 详细地址 **/
	private String detailAddress;
	/** 公司名称 **/
	private String companyName;
	/** 手机号 **/
	private String phone;
	/** 公司状态 **/
	private String status;
	/** 是否同意注册条款 **/
	private int isAgree;
	/** 认证类型 **/
	private String type;
	/** 商店简介 **/
	private String storeBrief;
	/** 联系人QQ号 **/
	private String linkmanQQ;
	/**联系人**/
	private String linkName;
	/**省**/
	private String provinceName;
	/**市**/
	private String cityName;
	/**区**/
	private String districtName;
	/**最后上架时间**/
	private Date lastGroundingTime;
	/** 添加字段1 **/
	private String field1;
	/** 添加字段2 **/
	private String field2;
	/** 添加字段3 **/
	private String field3;
	/** 添加字段4 **/
	private String field4;
	/** 添加字段5 **/
	private String field5;

	
	/** 申请日期开始时间 **/
	private String orderDateBegin;
	/** 申请日期结束时间 **/
	private String orderDateEnd;
	public Store() {
		super();
	}

	public Store(String userName, String passWord, String storeName, String mainShops, Integer addressId,
			String detailAddress, String companyName, String phone, String status, int isAgree, String type,
			String storeBrief, String linkmanQQ, String field1, String field2, String field3, String field4,
			String field5) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.storeName = storeName;
		this.mainShops = mainShops;
		this.addressId = addressId;
		this.detailAddress = detailAddress;
		this.companyName = companyName;
		this.phone = phone;
		this.status = status;
		this.isAgree = isAgree;
		this.type = type;
		this.storeBrief = storeBrief;
		this.linkmanQQ = linkmanQQ;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.field4 = field4;
		this.field5 = field5;
	}


	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMainShops() {
		return mainShops;
	}

	public void setMainShops(String mainShops) {
		this.mainShops = mainShops;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(int isAgree) {
		this.isAgree = isAgree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStoreBrief() {
		return storeBrief;
	}

	public void setStoreBrief(String storeBrief) {
		this.storeBrief = storeBrief;
	}

	public String getLinkmanQQ() {
		return linkmanQQ;
	}

	public void setLinkmanQQ(String linkmanQQ) {
		this.linkmanQQ = linkmanQQ;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getOrderDateBegin() {
		return orderDateBegin;
	}

	public void setOrderDateBegin(String orderDateBegin) {
		this.orderDateBegin = orderDateBegin;
	}

	public String getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(String orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Date getLastGroundingTime() {
		return lastGroundingTime;
	}

	public void setLastGroundingTime(Date lastGroundingTime) {
		this.lastGroundingTime = lastGroundingTime;
	}
}
