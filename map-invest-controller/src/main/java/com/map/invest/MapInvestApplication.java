package com.map.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.map.invest"})
public class MapInvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapInvestApplication.class, args);
    }

}
