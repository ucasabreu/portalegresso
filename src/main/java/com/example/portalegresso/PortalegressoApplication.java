package com.example.portalegresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PortalegressoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalegressoApplication.class, args);
	}

}
