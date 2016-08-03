package com.retailmanager.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.retailmanager.model.Coordinates;

@Service
public class GoogleMappingService implements MappingService {

	@Override
	public Coordinates getGeoCoordinates(final String locationAddress) {

		Coordinates coordinates = null;

		try {
			final Geocoder geocoder = new Geocoder();
			final GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(locationAddress)
					.getGeocoderRequest();
			final GeocodeResponse geocodeResponse = geocoder.geocode(geocoderRequest);
			final List<GeocoderResult> results = geocodeResponse.getResults();
			
			final double latitude = results.get(0).getGeometry().getLocation().getLat().doubleValue();
			final double longitude = results.get(0).getGeometry().getLocation().getLng().doubleValue();
			
			coordinates = new Coordinates(latitude, longitude);
		} catch (IOException e) {
		}

		return coordinates;
	}

}
