package com.lyl.eureka;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "eureka-producer", configuration = MyConfig.class)
public class MyClient {
}
