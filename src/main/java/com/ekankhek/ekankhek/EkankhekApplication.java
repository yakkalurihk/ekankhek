package com.ekankhek.ekankhek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EkankhekApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkankhekApplication.class, args);
	}

}
