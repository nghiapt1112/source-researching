package com.demo.Icommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@PropertySource(value = {"classpath:messages.properties"}, encoding = "UTF-8")
public class IcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcommerceApplication.class, args);
	}

}
