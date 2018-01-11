package com.hyike.clientfunny;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ClientfunnyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientfunnyApplication.class, args);
	}

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "Hello " + name + "I'm am from port: " + port;
	}

	private static final Logger LOG = LoggerFactory.getLogger(ClientfunnyApplication.class);


	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/hello")
	public String callHome(){
		LOG.info("calling trace ClientfunnyApplication  ");
		return restTemplate.getForObject("http://localhost:8755/hi?name=tanxy", String.class);
	}
	@RequestMapping("/miya")
	public String info(){
		LOG.info( "calling trace ClientfunnyApplication : info ");

		return restTemplate.getForObject("http://localhost:8755/info",String.class);

	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
