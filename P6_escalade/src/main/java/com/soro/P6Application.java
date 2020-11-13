package com.soro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.soro.storage.StorageProperties;

//Point de dépard de l'application

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class P6Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(P6Application.class, args);

	}
}
