package br.com.ibyte.pessoa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Funcao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcao")
	private Long id;

	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "nivel_funcao")
	private NivelFuncaoEnum nivelFuncao;

	public Funcao() {
	}

	public Funcao(String descricao, NivelFuncaoEnum nivelFuncaoEnum) {
		super();
		this.descricao = descricao;
		this.nivelFuncao = nivelFuncaoEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public NivelFuncaoEnum getNivelFuncao() {
		return nivelFuncao;
	}

	public void setNivelFuncao(NivelFuncaoEnum nivelFuncaoEnum) {
		this.nivelFuncao = nivelFuncaoEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Funcao other = (Funcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
