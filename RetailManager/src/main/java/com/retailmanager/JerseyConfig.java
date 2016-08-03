package com.retailmanager;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.retailmanager.resource.ShopResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		register(ShopResource.class);
	}

}
