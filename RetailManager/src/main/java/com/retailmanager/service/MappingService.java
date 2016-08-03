package com.retailmanager.service;

import com.retailmanager.model.Coordinates;

public interface MappingService {

	public Coordinates getGeoCoordinates(String locationAddress);

}
