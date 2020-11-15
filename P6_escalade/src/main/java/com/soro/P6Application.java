package com.soro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.soro.storage.StorageProperties;

//Point de dépard de l'application

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class P6Application extends SpringBootServletInitializer {

	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
	         return app.sources(P6Application.class);
	     }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(P6Application.class, args);

	}
}
