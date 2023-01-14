package com.computacenter.carconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.computacenter.carconfig.internal"})
public class CarConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarConfigApplication.class, args);
    }
}
