package com.retailmanager.service;

import java.util.List;

import com.retailmanager.model.Shop;

public interface ShopResourceService {

	public void save(Shop shop);
	
	public List<Shop> getAllShops();

}
