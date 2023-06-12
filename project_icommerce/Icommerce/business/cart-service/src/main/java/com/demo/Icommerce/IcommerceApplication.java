package com.demo.Icommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcommerceApplication.class, args);
	}

}
