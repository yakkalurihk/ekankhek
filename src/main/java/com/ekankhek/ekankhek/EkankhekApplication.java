package com.ekankhek.ekankhek;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EkankhekApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkankhekApplication.class, args);
	}

	@PostConstruct
	public void createFolder() {
		String path = "./uploads/";
		File pathAsFile = new File(path);

		if (!Files.exists(Paths.get(path))) {
			pathAsFile.mkdir();
		}
	}
}
