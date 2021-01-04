package com.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Usuario;

import reactor.core.publisher.Mono;

@Service
public class IndexService {
	
	private WebClient wcPassagem = WebClient.create("https://api-projetopagamento.herokuapp.com");
	
	public Usuario save(Usuario usuario){
		
		try {
			Mono<Usuario> mono = this.wcPassagem.post()
					.uri("api/usuarios/save")
					.body(BodyInserters.fromValue(usuario))
					.retrieve()
					.bodyToMono(Usuario.class);
			Usuario user = mono.block();
			return user;
		} catch (Exception e) {
			return null;
		}				
		
	}
	
	public String findEmailSenha(Usuario usuario) {
		try {
			Mono<String> mono = this.wcPassagem
					.post()
					.uri("api/usuarios/login")
					.body(BodyInserters.fromValue(usuario))
					.retrieve()
					.bodyToMono(String.class);
			String token = mono.block();
			return token;
		} catch (Exception e) {
			return null;
		}
		
	}
}