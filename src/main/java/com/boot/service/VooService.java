package com.boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.boot.model.Voo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VooService {

	@Autowired
	private WebClient webClient;
	
	public Flux<Voo> findAll() {
		
		return this.webClient.get()
			.uri("/api/voo/findall")
			.retrieve()
			.bodyToFlux(Voo.class);
	}
	
	public Mono<Voo> findId(Long id) {
		
		return this.webClient.get()
			.uri("/api/voo/findid/{id}", id)
			.retrieve()
			.bodyToMono(Voo.class);
		
		//Voo voo = monoVoo.block();
		//return voo;
	}
}
