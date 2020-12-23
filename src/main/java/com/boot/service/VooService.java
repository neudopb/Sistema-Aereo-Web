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

	public Voo[] findVoo(String origem, String destino, LocalDate data, String classe) {

		Mono<Voo[]> monoV = this.webClient.get()
				.uri("/api/voo/findvoo/{origem}/{destino}/{data}",
						origem, destino, data)
				.retrieve()
				.bodyToMono(Voo[].class);
		
		Voo[] voo = monoV.block();
		
		Mono<Assento[]> monoA = this.webClient.get()
				.uri("/api/assento/findall")
				.retrieve()
				.bodyToMono(Assento[].class);
		
		Assento[] as = monoA.block();
		//List<Assento> assentos = Arrays.asList(as);
		Assento[] aux = new Assento[as.length];
		
		for (Voo v : voo) {
			int i = 0;
			for(Assento a : as) {
				if(a.isDisponibilidade() && a.getClasse().equals(classe)) {
					aux[i] = a;
					i++;
					System.out.println("bjhbjhvvjhvjhvjh"+a.getId() + a.getNome());
				}
			}
			v.setAssentos(Arrays.asList(aux));
			aux = null;
		}
		
		return voo;
	}
}
