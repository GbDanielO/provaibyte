package br.com.ibyte.pessoa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ibyte.empresa.model.Setor;

@Entity
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6169971515892350499L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;

	private String nome;

	private String email;

	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dtNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_admissao")
	private Date dtAdmissao;

	@JsonIgnoreProperties("pessoa")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_funcao_pessoa")
	private FuncaoPessoa funcaoPessoa;

	@Transient
	private Setor setor;

	public Pessoa() {
	}

	public Pessoa(String nome, String email, String cpf, Date dtNascimento, Date dtAdmissao,
			FuncaoPessoa funcaoPessoa) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.dtAdmissao = dtAdmissao;
		this.funcaoPessoa = funcaoPessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public FuncaoPessoa getFuncaoPessoa() {
		return funcaoPessoa;
	}

	public void setFuncaoPessoa(FuncaoPessoa funcaoPessoa) {
		this.funcaoPessoa = funcaoPessoa;
	}

	@JsonIgnore
	public Setor getSetor() {
		return setor;
	}

	@JsonProperty
	public void setSetor(Setor setor) {
		this.setor = setor;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
