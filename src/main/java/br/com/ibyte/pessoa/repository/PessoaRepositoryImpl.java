package br.com.ibyte.pessoa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ibyte.pessoa.model.Pessoa;

public class PessoaRepositoryImpl implements PessoaRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> buscarPessoasPorSetor(Long setor_id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT pessoa FROM FuncaoPessoa fp ");
		sb.append(" inner join fp.setor setor ");
		sb.append(" inner join fp.pessoa pessoa ");
		sb.append(" WHERE setor.id = :setor_id ");

		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("setor_id", setor_id);

		return query.getResultList();
	}

}
