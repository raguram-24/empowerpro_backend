package com.backend.empowerpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmpowerproApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpowerproApplication.class, args);
	}

}
