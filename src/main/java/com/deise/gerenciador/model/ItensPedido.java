package com.deise.gerenciador.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="itens_pedido")
public class ItensPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_itens_pedido")
	private Long idItensPedido;
	
	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;
	
	@Column(name="quantidade")
	private Long quantidade;
	
	@Column(name="id_produto")
	private Long idProduto;
	
	@Column(name="id_pedido")
	private Long idPedido;

	public Long getIdItensPedido() {
		return idItensPedido;
	}

	public void setIdItensPedido(Long idItensPedido) {
		this.idItensPedido = idItensPedido;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItensPedido == null) ? 0 : idItensPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensPedido other = (ItensPedido) obj;
		if (idItensPedido == null) {
			if (other.idItensPedido != null)
				return false;
		} else if (!idItensPedido.equals(other.idItensPedido))
			return false;
		return true;
	}

	
}
