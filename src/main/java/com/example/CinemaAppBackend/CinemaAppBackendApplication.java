package com.example.CinemaAppBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;



@SpringBootApplication
@ComponentScan(basePackages = {"com.example.CinemaAppBackend"})
public class CinemaAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaAppBackendApplication.class, args);
	}

}
