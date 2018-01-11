package com.hyike.ribbondemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class RibbondemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbondemoApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

    @Bean
    CommandLineRunner runner(DiscoveryClient dc) {
        return args -> {
            dc.getInstances("service-hi").forEach(si -> System.out.println(
                    String.format("Found %s %s : %s",si.getServiceId(),si.getHost(),si.getPort())));
        };
    }
}
