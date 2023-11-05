package com.sb2.demosb2security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Security 2.0 API",version = "2.0",description = "Security Info"))
public class Demosb2SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Demosb2SecurityApplication.class, args);
	}

}
