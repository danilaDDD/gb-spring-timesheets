package ru.gb.danila.timesheetrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TimesheetRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimesheetRestApplication.class, args);
    }
}