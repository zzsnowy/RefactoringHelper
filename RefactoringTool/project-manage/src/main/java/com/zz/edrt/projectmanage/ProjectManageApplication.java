package com.zz.edrt.projectmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class ProjectManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManageApplication.class, args);
    }

}
