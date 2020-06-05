package br.com.ibyte.pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibyte.pessoa.model.Pessoa;

/**
 * 
 * @author Daniel Oliveira
 * 
 *         calculadoradabolsa: 28-novembro-2019 12:21
 *
 *         Todos os direitos reservados. Nenhuma parte de código desse projeto
 *         pode ser copiada sem autorização expressa para quaisquer fins.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQueries {

	List<Pessoa> findByFuncaoPessoa_Setor_Id(Long setor_id);

	// Query Methods SpringJPA
	// Pessoa findBy(Long cpt, String cpf);

}
