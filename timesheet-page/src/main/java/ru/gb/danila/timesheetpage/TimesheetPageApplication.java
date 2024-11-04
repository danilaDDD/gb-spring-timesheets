package ru.gb.danila.timesheetpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TimesheetPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimesheetPageApplication.class, args);
    }
}