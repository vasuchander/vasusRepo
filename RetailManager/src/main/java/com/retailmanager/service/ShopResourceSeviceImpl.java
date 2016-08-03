package com.retailmanager.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.retailmanager.Repository.ShopResourceRepository;
import com.retailmanager.model.Shop;

@Service
public class ShopResourceSeviceImpl implements ShopResourceService {
	
	@Inject
	private ShopResourceRepository shopResourceRepository;
	
	@Override
	public void save(Shop shop) {
		shopResourceRepository.save(shop);
	}
	
	@Override
	public List<Shop> getAllShops() {
		return shopResourceRepository.getAllShops();
	}
	
}
