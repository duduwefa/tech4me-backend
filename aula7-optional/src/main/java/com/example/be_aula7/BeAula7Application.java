package com.example.be_aula7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BeAula7Application {

	public static void main(String[] args) {
		SpringApplication.run(BeAula7Application.class, args);
	}

}
