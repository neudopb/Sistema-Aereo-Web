package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.boot.model.Assento;
import com.boot.model.Passagem;
import com.boot.model.SituacaoPagamento;

import reactor.core.publisher.Mono;

@Service
public class PassagemService {

	@Autowired
	private WebClient webClient;

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
		System.out.println("testetstetsetstest ASSENTO " + assento + " " + assento.getId() + " " + assento.getVoo());
		//O assento.getIdVoo() VEM NULO
		
		Mono<SituacaoPagamento> monoSit = this.webClient.get().uri("/api/sitpag/findid/{id}", 2).retrieve()
				.bodyToMono(SituacaoPagamento.class);
		SituacaoPagamento situacaoPagamento = monoSit.block();

		System.out.println("testetstetsetstest PAGAMENTO: " + situacaoPagamento + " " + situacaoPagamento.getId());
		
		Passagem passagem = new Passagem();
		passagem.setUsuario(usuario);
		passagem.setPagamento(situacaoPagamento);
		passagem.setAssento(assento);
		
		System.out.println("testetstetsetstest PASSAGEM: " + passagem.getUsuario() + " " + passagem.getAssento() + " " + passagem.getPagamento());
	
		assento.setDisponibilidade(false);
		Mono<Assento> monoAs = this.webClient.put().uri("/api/assento/update")
				.body(BodyInserters.fromValue(assento)).retrieve().bodyToMono(Assento.class);
		monoAs.block();
		//QUANDO ATUALIZA O assento.voo FICA NULO

		Mono<Passagem> monoPassagem = this.webClient.post().uri("/api/passagem/save")
				.body(BodyInserters.fromValue(passagem)).retrieve().bodyToMono(Passagem.class);
		monoPassagem.block();
		//QUANDO SALVA A passagem.assento e passagem.pagamento FICAM NULOS
		
		return passagemUser(usuario);

	}
}