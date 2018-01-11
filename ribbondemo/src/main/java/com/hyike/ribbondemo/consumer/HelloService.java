package com.hyike.ribbondemo.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommandLineRunner commandLineRunner;

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String name) {
        try {
            commandLineRunner.run(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "Hi " + name + ", Sorry for Service Error!";
    }
}
