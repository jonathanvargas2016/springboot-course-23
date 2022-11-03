package com.js.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMain {

	public static void main(String[] args) {
		System.out.println("API of movies is working....");
		SpringApplication.run(AppMain.class, args);
	}

}
