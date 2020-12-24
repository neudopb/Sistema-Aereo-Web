package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Passagem;

import reactor.core.publisher.Mono;

@Service
public class PassagemService {

	@Autowired
	private WebClient webClient;
	
	public Passagem[] passagemUser(Long id) {
		Mono<Passagem[]> mono = this.webClient.get()
				.uri("/api/passagem/finduser/{id}", id)
				.retrieve()
				.bodyToMono(Passagem[].class);
		
		Passagem[] passagens = mono.block();
		
		return passagens;
	}
	
}