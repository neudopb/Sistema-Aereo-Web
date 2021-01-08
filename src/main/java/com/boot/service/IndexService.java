package com.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Usuario;

import reactor.core.publisher.Mono;

@Service
public class IndexService {
	
	private WebClient wcPassagem = WebClient.create("https://projeto-pag-api.herokuapp.com");
	
	public Usuario save(Usuario usuario){
		
		try {
			Mono<Usuario> mono = this.wcPassagem.post()
					.uri(uriBuilder -> uriBuilder
						.path("api/usuarios/save")
						.queryParam("nome", usuario.getNome())
						.queryParam("email", usuario.getEmail())
						.queryParam("senha", usuario.getSenha())
						.build())
					.retrieve()
					.bodyToMono(Usuario.class);
			Usuario user = mono.block();
			return user;
		} catch (Exception e) {
			return null;
		}				
		
	}
	
	public Usuario login(Usuario usuario) {
		try {
			Mono<Usuario> mono = this.wcPassagem.post()
					.uri(uriBuilder -> uriBuilder
						.path("api/usuarios/login")
						.queryParam("email", usuario.getEmail())
						.queryParam("senha", usuario.getSenha())
						.build())
					.retrieve()
					.bodyToMono(Usuario.class);
			Usuario user = mono.block();
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}
}