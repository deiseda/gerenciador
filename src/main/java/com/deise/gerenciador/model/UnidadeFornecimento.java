package com.deise.gerenciador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unidade_fornecimento")
public class UnidadeFornecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_unidade_fornecimento")
	private Long idUnidadeFornecimento;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="id_unidade_medida")
	private Long idUnidadeMedida;
	
	@Column(name="unidade_apresentacao")
	private String unidadeApresentacao;

	public Long getIdUnidadeFornecimento() {
		return idUnidadeFornecimento;
	}

	public void setIdUnidadeFornecimento(Long idUnidadeFornecimento) {
		this.idUnidadeFornecimento = idUnidadeFornecimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getIdUnidadeMedida() {
		return idUnidadeMedida;
	}

	public void setIdUnidadeMedida(Long idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}

	public String getUnidadeApresentacao() {
		return unidadeApresentacao;
	}

	public void setUnidadeApresentacao(String unidadeApresentacao) {
		this.unidadeApresentacao = unidadeApresentacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidadeFornecimento == null) ? 0 : idUnidadeFornecimento.hashCode());
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
		UnidadeFornecimento other = (UnidadeFornecimento) obj;
		if (idUnidadeFornecimento == null) {
			if (other.idUnidadeFornecimento != null)
				return false;
		} else if (!idUnidadeFornecimento.equals(other.idUnidadeFornecimento))
			return false;
		return true;
	}
	
	

	
}
