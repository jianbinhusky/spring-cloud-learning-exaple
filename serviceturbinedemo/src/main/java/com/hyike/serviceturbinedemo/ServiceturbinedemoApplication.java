package com.hyike.serviceturbinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class ServiceturbinedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceturbinedemoApplication.class, args);
	}
}
