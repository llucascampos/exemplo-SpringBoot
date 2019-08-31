package br.com.fontedeestudo.cursoparaestudo.domain;

import javax.persistence.Entity;

import br.com.fontedeestudo.cursoparaestudo.domain.enums.EstadoPagamento;


@Entity
public class PagamentoComCartao extends Pagamento {
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,  Integer numeroDeParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
