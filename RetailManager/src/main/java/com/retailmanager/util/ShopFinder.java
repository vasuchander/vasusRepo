package com.retailmanager.util;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.retailmanager.Repository.ShopResourceRepository;
import com.retailmanager.model.Coordinates;
import com.retailmanager.model.Shop;

@Component
public class ShopFinder {

	@Inject
	private  ShopResourceRepository shopResourceRepository;

	public  Shop getNearestShop(Coordinates coordinates) {
		Shop shop = null;

		try {
			double distance = 0;
			double localdistance = 0;
			List<Shop> listOfShops = shopResourceRepository.getAllShops();

			if (!listOfShops.isEmpty()) {
				shop = listOfShops.get(0);
				distance = CoordintesDistance(coordinates, new Coordinates(shop.getLatitude(), shop.getLongitude()));
			}
			localdistance = distance;
			final int length = listOfShops.size();
			int index = -1;
			for (int i = 1; i < length; i++) {
				shop = listOfShops.get(i);
				distance = CoordintesDistance(coordinates, new Coordinates(shop.getLatitude(), shop.getLongitude()));
				if (Double.compare(distance,localdistance) < 0) {
					index = i;
					localdistance = distance;
				}
			}
			if (index != -1) {
				shop = listOfShops.get(index);
			}
		} catch (Exception e) {
		}

		return shop;
	}

	private  double CoordintesDistance(Coordinates c1, Coordinates c2) {

		final double distance = Math.sqrt(Math.pow((c1.getLatitude() - c2.getLatitude()), 2)
				+ Math.pow((c1.getLongitude() - c2.getLongitude()), 2));

		return distance;
	}
}
