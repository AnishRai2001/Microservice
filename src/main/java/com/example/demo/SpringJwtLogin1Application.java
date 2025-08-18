package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//public class SpringJwtLogin1Application {
//    // ...
//
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringJwtLogin1Application.class, args);
//	}
//
//}
@SpringBootApplication(scanBasePackages = "com.example")
@EnableDiscoveryClient
public class SpringJwtLogin1Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringJwtLogin1Application.class, args);
    }
}


