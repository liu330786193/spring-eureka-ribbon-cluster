package com.lyl.eureka;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class MyConfig {

    @Bean
    public IRule getRule(){
        return new MyRule();
    }

}
