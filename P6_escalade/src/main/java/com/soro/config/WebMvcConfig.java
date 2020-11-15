package com.soro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuration de l'import d'image  
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// public static String uploadDirectory= System.getProperty("user.home") +
	// "\\soro";
	public static String uploadDirectory = "D://OCR/P6_escalade/image";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//
		// Access Bootstrap static resource:
		//

		//
		// http://somedomain/SomeContextPath/jquery/jquery.min.css
		//
		registry.addResourceHandler("/jquery/**") //
				.addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");

		//
		// http://somedomain/SomeContextPath/popper/popper.min.js
		//
		registry.addResourceHandler("/popper/**") //
				.addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");

		// http://somedomain/SomeContextPath/bootstrap/css/bootstrap.min.css
		// http://somedomain/SomeContextPath/bootstrap/js/bootstrap.min.js
		//
		registry.addResourceHandler("/bootstrap/**") //
				.addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1/");

		//
		// http://somedomain/SomeContextPath/jquery/jquery.min.css
		//
		registry.addResourceHandler("/font-awesome/**") //
				.addResourceLocations("classpath:/META-INF/resources/webjars/font-awesome/4.7.0/");

		registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadDirectory + "\\");

	}

}