package com.lyl.eureka;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@RestController
public class ConsumerController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/router")
    @ResponseBody
    public String router(){
        RestTemplate tpl = getRestTemplate();
        String json = tpl.getForObject("http://eureka-producer/call/2", String.class);
        return json;
    }

    @Autowired
    private LoadBalancerClient client;

    @RequestMapping(value = "/lb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceInstance lb() {
        ServiceInstance si = client.choose("eureka-producer");
        return si;
    }

    @Autowired
    private SpringClientFactory factory;

    @RequestMapping(value = "/fa", method = RequestMethod.GET)
    public String factory() {
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer)factory.getLoadBalancer("default");
        System.out.println(lb.getRule().getClass().getName());

        ZoneAwareLoadBalancer lb2 = (ZoneAwareLoadBalancer)factory.getLoadBalancer("spring-lb-provider");
        System.out.println(lb2.getRule().getClass().getName());
        return "";
    }
}
