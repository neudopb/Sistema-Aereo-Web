package com.boot.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Assento;
import com.boot.model.Voo;
import reactor.core.publisher.Mono;

@Service
public class VooService {

	@Autowired
	private WebClient webClient;

	public Voo[] findAll() {

		Mono<Voo[]> mono = this.webClient.get().uri("/api/voo/findall").retrieve().bodyToMono(Voo[].class);

		Voo[] voo = mono.block();
		return voo;
	}

	public Mono<Voo> findId(Long id) {

		return this.webClient.get().uri("/api/voo/findid/{id}", id).retrieve().bodyToMono(Voo.class);
	}

	public Voo[] findVoo(String origem, String destino, LocalDate data, String classe) {

		try {
			Mono<Voo[]> monoV = this.webClient.get()
					.uri("/api/voo/findvoo/{origem}/{destino}/{data}",
							origem, destino, data)
					.retrieve()
					.bodyToMono(Voo[].class);
			
			Voo[] voo = monoV.block();
			
			for (Voo v : voo) {
				Mono<Assento[]> monoA = this.webClient.get()
						.uri("/api/assento/findassentodispclasse/{id}/{classe}", v.getId(), classe)
						.retrieve()
						.bodyToMono(Assento[].class);
				
				Assento[] assentos = monoA.block();
				v.setAssentos(Arrays.asList(assentos));
			}
			return voo;
		
		} catch (Exception e) {
			return null;
		}
	}
}
