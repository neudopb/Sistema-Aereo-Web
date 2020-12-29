package com.boot.service;

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

	public Passagem[] passagemUser(Long id) {
		Mono<Passagem[]> mono = this.webClient.get().uri("/api/passagem/finduser/{id}", id)
				.retrieve()
				.bodyToMono(Passagem[].class);

		Passagem[] passagens = mono.block();

		return passagens;
	}

	public Passagem[] passagemSave(Long idUser, Long idAssento) {
		Mono<Usuario> monoUser = this.webClient.get().uri("/api/usuario/findid/{id}", idUser).retrieve()
				.bodyToMono(Usuario.class);

		Mono<Assento> monoAssento = this.webClient.get().uri("/api/assento/findid/{id}", idAssento).retrieve()
				.bodyToMono(Assento.class);

		Mono<SituacaoPagamento> monoSit = this.webClient.get().uri("/api/sitpag/findid/{id}", 2).retrieve()
				.bodyToMono(SituacaoPagamento.class);
		
		Passagem pas = Mono.zip(monoUser, monoAssento, monoSit).map(tuple -> {
			Passagem passagem = new Passagem();
			passagem.setIdUsuario(tuple.getT1());
			passagem.setIdAssento(tuple.getT2());
			passagem.setIdPagamento(tuple.getT3());

			return passagem;
		}).block();
		
		Passagem passagem = new Passagem();
		passagem.setIdUsuario(pas.getIdUsuario());
		passagem.setIdAssento(pas.getIdAssento());
		passagem.setIdPagamento(pas.getIdPagamento());
		
		if(passagem.getIdAssento().isDisponibilidade()) {
			Mono<Passagem> monoPassagem = this.webClient.post().uri("/api/passagem/save")
					.body(BodyInserters.fromValue(passagem))
					.retrieve()
					.bodyToMono(Passagem.class);
					
			Assento assento = pas.getIdAssento();
			assento.setDisponibilidade(false);
			
			Mono<Assento> monoAs = this.webClient.put()
					.uri("/api/passagem/update")
					.body(BodyInserters.fromValue(assento))
					.retrieve()
					.bodyToMono(Assento.class);
			
			Mono.zip(monoPassagem, monoAs).block();
		}

		return passagemUser(idUser);

	}
}