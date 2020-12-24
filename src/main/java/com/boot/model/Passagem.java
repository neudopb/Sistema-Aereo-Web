package com.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passagem {

	private Long id;
	private Usuario idUsuario;
	private Assento idAssento;
	private Voo idVoo;
	private SituacaoPagamento idPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Assento getIdAssento() {
		return idAssento;
	}

	public void setIdAssento(Assento idAssento) {
		this.idAssento = idAssento;
	}

	public SituacaoPagamento getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(SituacaoPagamento idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Voo getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(Voo idVoo) {
		this.idVoo = idVoo;
	}
}
