package com.pharmacy.drugmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DrugManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugManagementApplication.class, args);
	}

}
