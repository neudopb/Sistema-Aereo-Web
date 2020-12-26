package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SistemaAereoWebApplication {
	
	@Bean
	public WebClient webCliente(WebClient.Builder builder) {
		return builder
			//.baseUrl("https://mncompany.herokuapp.com")
			.baseUrl("http://localhost:8081")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaAereoWebApplication.class, args);
	}

}
