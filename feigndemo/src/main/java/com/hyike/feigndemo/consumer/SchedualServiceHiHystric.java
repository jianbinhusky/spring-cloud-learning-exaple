package com.hyike.feigndemo.consumer;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "Sorry " + name + " Service Error now!";
    }

}
