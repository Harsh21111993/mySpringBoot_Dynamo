package com.delta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.delta.config.CreateTable;

@SpringBootApplication
public class SpringBootDynamoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDynamoDbApplication.class, args);

		SpringApplication app = new SpringApplication(SpringBootDynamoDbApplication.class);
		app.addListeners(new CreateTable());
		app.run(args);
	}

}
