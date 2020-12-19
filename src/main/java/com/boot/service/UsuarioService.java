package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		Usuario user = mono.block();
		
		return user;
	}
	
	public Usuario findEmailSenha(Usuario usuario) {
		Mono<Usuario> mono = this.webClient.post()
				.uri("api/usuario/findemailsenha")
				.retrieve()
				.bodyToMono(Usuario.class);
		
		Usuario user = mono.block();
		
		return user;
	}
}