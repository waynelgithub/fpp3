package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TeamFpp20220725Application {

	public static void main(String[] args) {
		SpringApplication.run(TeamFpp20220725Application.class, args);
	}

}
