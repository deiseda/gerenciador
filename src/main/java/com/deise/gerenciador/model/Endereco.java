package com.deise.gerenciador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
	private Long idendereco;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="numero")
	private Long numero;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="referencia")
	private String referencia;

	public Long getIdendereco() {
		return idendereco;
	}

	public void setIdendereco(Long idendereco) {
		this.idendereco = idendereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idendereco == null) ? 0 : idendereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (idendereco == null) {
			if (other.idendereco != null)
				return false;
		} else if (!idendereco.equals(other.idendereco))
			return false;
		return true;
	}

	
	
}
