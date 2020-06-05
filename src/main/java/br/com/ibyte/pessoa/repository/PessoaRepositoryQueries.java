package br.com.ibyte.pessoa.repository;

import java.util.List;

import br.com.ibyte.pessoa.model.Pessoa;

public interface PessoaRepositoryQueries {

	List<Pessoa> buscarPessoasPorSetor(Long setor_id);

}
