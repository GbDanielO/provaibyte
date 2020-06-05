package br.com.ibyte.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibyte.pessoa.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

}
