package br.com.ibyte.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibyte.empresa.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long>, SetorRepositoryQueries {

}
