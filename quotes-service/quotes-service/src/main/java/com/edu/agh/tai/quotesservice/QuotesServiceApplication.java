package com.edu.agh.tai.quotesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//TODO testy jednostkowe
@SpringBootApplication
@EnableDiscoveryClient
public class QuotesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesServiceApplication.class, args);
	}

}
