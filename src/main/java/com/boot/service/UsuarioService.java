package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Usuario;

import reactor.core.publisher.Mono;

@Service
public class UsuarioService {
	
	@Autowired
	private WebClient webClient;
	
	public Usuario save(Usuario usuario){
		Mono<Usuario> mono = this.webClient.post()
				.uri("api/usuario/save")
				.body(BodyInserters.fromValue(usuario))
				.retrieve()
				.bodyToMono(Usuario.class);

		Mono<Usuario> user = Mono.just(mono).block();
				
		return user.block();
	}
	
	public Usuario findEmailSenha(Usuario usuario) {
		Mono<Usuario> mono = this.webClient
				.post()
				.uri("api/usuario/findemailsenha")
				.body(BodyInserters.fromValue(usuario))
				.retrieve()
				.bodyToMono(Usuario.class);
		
		Usuario user = mono.block();
		
		return user;
	}
}