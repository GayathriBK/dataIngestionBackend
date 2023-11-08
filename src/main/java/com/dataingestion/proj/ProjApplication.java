package com.dataingestion.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjApplication.class, args);
	}

}
