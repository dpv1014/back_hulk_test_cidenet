package com.hulkstore.kardix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan({"com.hulkstore.kardix.controller", 
				"com.hulkstore.kardix.service", 
				"com.hulkstore.kardix.config", 
				"com.hulkstore.kardix.exception"})
@EntityScan("com.hulkstore.kardix.model")
@EnableJpaRepositories("com.hulkstore.kardix.repository")
@SpringBootApplication
public class KardixApplication {

	public static void main(String[] args) {
		SpringApplication.run(KardixApplication.class, args);
	}

}
