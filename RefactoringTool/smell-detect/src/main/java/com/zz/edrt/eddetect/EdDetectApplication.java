package com.zz.edrt.eddetect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@EnableFeignClients(basePackages="com.zz.edrt.eddetect.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class EdDetectApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EdDetectApplication.class, args);
    }

}
