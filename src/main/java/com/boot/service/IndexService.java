package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Usuario;

import reactor.core.publisher.Mono;

@Service
public class IndexService {
	
	@Autowired
	private WebClient webClient;
	
	private WebClient wcPassagem = WebClient.create("https://api-projetopagamento.herokuapp.com");
	
	public Usuario save(Usuario usuario){
		
		try {
			Mono<Usuario> mono = this.wcPassagem.post()
					.uri("api/usuarios/save")
					.body(BodyInserters.fromValue(usuario))
					.retrieve()
					.bodyToMono(Usuario.class);
			System.out.println("TESTETESTE mono " + mono);
			Usuario user = mono.block();
			System.out.println("TESTETESTE user " + user.getNome());
			System.out.println("TESTETESTE user " + user.getEmail());
			System.out.println("TESTETESTE user " + user.getSenha());
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
			System.out.println("TESTE mono " + mono);
			String token = mono.block();
			System.out.println("TESTE user " + token);
			return token;
		} catch (Exception e) {
			System.out.println("ERRO ");
			e.printStackTrace();
			return null;
		}
		
	}
}