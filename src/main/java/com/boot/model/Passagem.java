package com.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passagem {

	private Long id;
	private String usuario;
	private Assento assento;
	private SituacaoPagamento pagamento;

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

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	public SituacaoPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(SituacaoPagamento pagamento) {
		this.pagamento = pagamento;
	}
}
