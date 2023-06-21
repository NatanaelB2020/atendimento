package com.suporte.atendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SuporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuporteApplication.class, args);
	}

}
