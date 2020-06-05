package br.com.ibyte.pessoa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PessoaRepositoryImpl implements PessoaRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

}
