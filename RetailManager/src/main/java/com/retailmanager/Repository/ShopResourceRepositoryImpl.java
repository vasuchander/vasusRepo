package com.retailmanager.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.retailmanager.model.Shop;

@Repository
public class ShopResourceRepositoryImpl implements ShopResourceRepository {

	List<Shop> listOfShops = new ArrayList<>();

	@Override
	public void save(Shop shop) {
		listOfShops.add(shop);
	}

	@Override
	public List<Shop> getAllShops() {
		return listOfShops;
	}

}
