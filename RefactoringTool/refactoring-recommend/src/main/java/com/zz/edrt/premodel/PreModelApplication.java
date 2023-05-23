package com.zz.edrt.premodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages="com.zz.edrt.premodel.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class PreModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreModelApplication.class, args);
	}

}
