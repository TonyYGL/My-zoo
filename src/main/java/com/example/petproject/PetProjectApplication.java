package com.example.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class PetProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetProjectApplication.class, args);
	}

}
