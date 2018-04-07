package com.lyl.eureka;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule {

    private ILoadBalancer lb;

    @Override
    public Server choose(Object o) {
        System.out.println("自定义规则");
        Random random = new Random();
        int randomNum = random.nextInt(10);
        List<Server> servers = lb.getAllServers();
        if (randomNum > 8){
            return getServerByPort(servers, 8080);
        }
        return getServerByPort(servers, 8081);
    }

    private Server getServerByPort(List<Server> servers, int port) {
        for (Server server :servers){
            if (server.getPort() == port){
                return server;
            }
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
