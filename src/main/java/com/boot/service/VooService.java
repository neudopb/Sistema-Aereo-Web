package com.boot.service;

import java.time.LocalDate;
import java.util.List;

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

	public Voo[] findAll() {

		Mono<Voo[]> mono = this.webClient.get()
				.uri("/api/voo/findall")
				.retrieve()
				.bodyToMono(Voo[].class);

		Voo[] voo = mono.block();
		return voo;
	}

	public Mono<Voo> findId(Long id) {

		return this.webClient.get()
				.uri("/api/voo/findid/{id}", id)
				.retrieve()
				.bodyToMono(Voo.class);
	}

	public Voo[] findVoo(String origem, String destino, LocalDate data) {

		Mono<Voo[]> mono = this.webClient.get()
				.uri("/api/voo/findvoo/{origem}/{destino}/{data}",
						origem, destino, data)
				.retrieve()
				.bodyToMono(Voo[].class);
		
		Voo[] voo = mono.block();
		
		return voo;
	}
}
