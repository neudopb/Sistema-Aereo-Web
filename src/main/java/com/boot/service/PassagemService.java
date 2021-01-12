package com.boot.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Assento;
import com.boot.model.Passagem;
import com.boot.model.SituacaoPagamento;
import com.boot.model.Usuario;

import reactor.core.publisher.Mono;

@Service
public class PassagemService {

	@Autowired
	private WebClient webClient;
	
	private WebClient wcPassagem = WebClient.create("https://projeto-pag-api.herokuapp.com");

	public Passagem[] passagemUser(String usuario) {
		Mono<Passagem[]> mono = this.webClient.get().uri("/api/passagem/finduser/{usuario}", usuario).retrieve()
				.bodyToMono(Passagem[].class);

		Passagem[] passagens = mono.block();
		return passagens;
	}

	public Passagem[] passagemSave(String usuario, Long idAssento) {

		Mono<Assento> monoAssento = this.webClient.get().uri("/api/assento/findid/{id}", idAssento).retrieve()
				.bodyToMono(Assento.class);
		Assento assento = monoAssento.block();

		Mono<SituacaoPagamento> monoSit = this.webClient.get().uri("/api/sitpag/findid/{id}", 2).retrieve()
				.bodyToMono(SituacaoPagamento.class);
		SituacaoPagamento situacaoPagamento = monoSit.block();

		Passagem passagem = new Passagem();
		passagem.setUsuario(usuario);
		passagem.setPagamento(situacaoPagamento);
		passagem.setAssento(assento);

		assento.setDisponibilidade(false);
		Mono<Assento> monoAs = this.webClient.put().uri("/api/assento/update").body(BodyInserters.fromValue(assento))
				.retrieve().bodyToMono(Assento.class);
		monoAs.block();

		Mono<Passagem> monoPassagem = this.webClient.post().uri("/api/passagem/save")
				.body(BodyInserters.fromValue(passagem)).retrieve().bodyToMono(Passagem.class);
		monoPassagem.block();

		return passagemUser(usuario);

	}
	
	public String passagemPaga(Long idUser, Long idPassagem, String token) {
		
		Mono<SituacaoPagamento> monoSit = this.webClient.get().uri("/api/sitpag/findid/{id}", 1).retrieve()
				.bodyToMono(SituacaoPagamento.class);
		SituacaoPagamento situacaoPagamento = monoSit.block();
		
		Mono<Passagem> monoPassagem = this.webClient.get().uri("/api/passagem/findid/{id}", idPassagem).retrieve()
				.bodyToMono(Passagem.class);
		Passagem passagem = monoPassagem.block();
		
		passagem.setPagamento(situacaoPagamento);
		monoPassagem = this.webClient.put().uri("/api/passagem/update")
				.body(BodyInserters.fromValue(passagem)).retrieve().bodyToMono(Passagem.class);
		monoPassagem.block();
			
		LocalDate atual = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String data = formatter.format(atual);
		
		try {
			Mono<Usuario> mono = this.wcPassagem.post()
					.uri(uriBuilder -> uriBuilder
						.path("api/compras/gerarLink")
						.queryParam("id", idUser)
						.queryParam("valor", passagem.getAssento().getPreco())
						.queryParam("data", data)
						.build())
					.header("Origem", "http://localhost:8080/minhasreservas")
					.header("Authorization", "Bearer " + token)
					.retrieve()
					.bodyToMono(Usuario.class);
			Usuario user = mono.block();
			
			return user.getLink();
		} catch (Exception e) {
			return null;
		}		
	}
}