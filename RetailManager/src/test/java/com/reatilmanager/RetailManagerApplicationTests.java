package com.reatilmanager;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.retailmanager.RetailManagerApplication;
import com.retailmanager.model.Coordinates;
import com.retailmanager.model.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RetailManagerApplication.class)
@WebAppConfiguration
public class RetailManagerApplicationTests {

	Client client = null;

	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
	}

	@Test
	public void shopSaveTest() {
		WebTarget target = client.target("http://localhost:8080/");

		Shop shop = new Shop();
		shop.setName("shoppers stop");
		shop.setAddress("magarpatta");
		shop.setPostalCode("411028");

		Response response = target.path("shop/save").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(shop, MediaType.APPLICATION_JSON));

		assertThat(response.getStatus(), is(200));

	}
	
	
	/*
	 * before Running this Test make sure that there are Entries in the List of Shops
	 */
	@Test
	public void findShopTest() {

		Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(1.0);
		coordinates.setLongitude(1.0);

		WebTarget target = client.target("http://localhost:8080/");
		Response response = target.path("shop/findNearestShop").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(coordinates, MediaType.APPLICATION_JSON));

		assertThat(response.getStatus(), is(200));
	}

}
