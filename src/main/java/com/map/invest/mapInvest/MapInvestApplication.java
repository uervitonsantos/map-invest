package com.map.invest.mapInvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.map.invest.mapInvest", "com.map.invest.mapInvest.canonicoFactory"})
public class MapInvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapInvestApplication.class, args);
	}
}
