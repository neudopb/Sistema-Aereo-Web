package com.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passagem {

	private Long id;
	private String usuario;
	private Assento idAssento;
	private SituacaoPagamento idPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
}
