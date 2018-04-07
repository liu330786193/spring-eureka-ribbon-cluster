package com.lyl.eureka;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String profiles = scanner.nextLine();
		new SpringApplicationBuilder(EurekaServerApplication.class).profiles(profiles).run(args);
	}
}
