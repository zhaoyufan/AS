package com.upic.asn.ui.main.store_detail.bean;


/**
 * Created by ZYF on 2016/12/23.
 */
public class StoreDetail {
    private String storeName;
    private String storePeople;
    private String storePrice;
    private String storePeopleCount;
    private String storeCostPrice;
    private String storeMember;
    private String storeAddress;

    public StoreDetail() {
    }

    public StoreDetail(String storeName, String storePeople, String storePrice, String storePeopleCount, String storeCostPrice, String storeMember, String storeAddress) {
        this.storeName = storeName;
        this.storePeople = storePeople;
        this.storePrice = storePrice;
        this.storePeopleCount = storePeopleCount;
        this.storeCostPrice = storeCostPrice;
        this.storeMember = storeMember;
        this.storeAddress = storeAddress;
    }

    public String getStorePeopleCount() {
        return storePeopleCount;
    }

    public void setStorePeopleCount(String storePeopleCount) {
        this.storePeopleCount = storePeopleCount;
    }

    public String getStoreCostPrice() {
        return storeCostPrice;
    }

    public void setStoreCostPrice(String storeCostPrice) {
        this.storeCostPrice = storeCostPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePeople() {
        return storePeople;
    }

    public void setStorePeople(String storePeople) {
        this.storePeople = storePeople;
    }

    public String getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(String storePrice) {
        this.storePrice = storePrice;
    }

    public String getStoreMember() {
        return storeMember;
    }

    public void setStoreMember(String storeMember) {
        this.storeMember = storeMember;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
