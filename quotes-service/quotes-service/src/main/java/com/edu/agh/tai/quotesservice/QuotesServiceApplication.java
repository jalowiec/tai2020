package com.edu.agh.tai.quotesservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

//TODO testy jednostkowe
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class QuotesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
}
