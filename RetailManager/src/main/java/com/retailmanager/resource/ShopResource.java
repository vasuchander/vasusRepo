package com.retailmanager.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.retailmanager.model.Coordinates;
import com.retailmanager.model.Shop;
import com.retailmanager.service.MappingService;
import com.retailmanager.service.ShopResourceService;
import com.retailmanager.util.ShopFinder;

@Component
@Path("/shop")
public class ShopResource {

	@Inject
	private MappingService mappingService;

	@Inject
	private ShopResourceService shopResourceServie;
	
	@Inject
	ShopFinder shopFinder;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJson() {

		Shop shop = new Shop();
		shop.setName("sp infocity");
		shop.setAddress("hadapsar");
		shop.setPostalCode("412308");

		return Response.status(200).entity(shop).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	public Response saveShop(Shop shop) {

		Coordinates coordinates = null;

		try {

			final String locationAddress = shop.getName() + shop.getAddress() + shop.getPostalCode();

			coordinates = mappingService.getGeoCoordinates(locationAddress);

			if (coordinates != null) {

				shop.setLatitude(coordinates.getLatitude());
				shop.setLongitude(coordinates.getLongitude());

				shopResourceServie.save(shop);
			}

		} catch (Exception e) {

		}
		return Response.status(200).entity(shop).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findNearestShop")
	public Response findNearestShop(Coordinates coordinates) {
		
		Shop shop = shopFinder.getNearestShop(coordinates);

		return Response.status(200).entity(shop).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/shops")
	public Response getAllShops() {

		List<Shop> list = shopResourceServie.getAllShops();

		return Response.status(200).entity(list).build();

	}

}
