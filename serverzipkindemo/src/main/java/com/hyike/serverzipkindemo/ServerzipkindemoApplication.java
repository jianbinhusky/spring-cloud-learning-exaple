package com.hyike.serverzipkindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ServerzipkindemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerzipkindemoApplication.class, args);
	}
}
