package com.retailmanager.Repository;

import java.util.List;

import com.retailmanager.model.Shop;

public interface ShopResourceRepository {

	public void save(Shop shop);
	
	public List<Shop> getAllShops();

}
