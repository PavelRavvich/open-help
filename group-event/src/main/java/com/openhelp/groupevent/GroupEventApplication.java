package com.openhelp.groupevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GroupEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupEventApplication.class, args);
    }

}
