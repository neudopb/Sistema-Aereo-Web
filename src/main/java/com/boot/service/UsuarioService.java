package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
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
				.retrieve()
				.bodyToMono(Usuario.class);

		Mono<Usuario> user = Mono.just(mono).block();
				
		return user.block();
	}
	
	public Usuario findEmailSenha(Usuario usuario) {
		Mono<Usuario> mono = this.webClient
				.method(HttpMethod.POST)
				.uri("api/usuario/findemailsenha")
				.retrieve()
				.bodyToMono(Usuario.class);
		
		Usuario user = mono.block();
		
		return user;
	}
}