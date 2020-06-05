package br.com.ibyte.pessoa.negocio;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ibyte.pessoa.model.FuncaoPessoa;
import br.com.ibyte.pessoa.model.Pessoa;
import br.com.ibyte.pessoa.repository.PessoaRepository;

@Service
public class PessoaNgc {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listar() {
		return this.pessoaRepository.findAll();
	}

	public ResponseEntity<Pessoa> buscarPeloCodigo(Long codigo) {

		Optional<Pessoa> optional = this.pessoaRepository.findById(codigo);

		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Transactional
	public Pessoa criar(Pessoa pessoa, HttpServletResponse response) {

		try {
			// verifica preenchimento das composições
			if (pessoa.getLstFuncaoPessoa() != null && !pessoa.getLstFuncaoPessoa().isEmpty()) {
				for (FuncaoPessoa funcaoPessoa : pessoa.getLstFuncaoPessoa()) {
					if (funcaoPessoa != null) {
						funcaoPessoa.setPessoa(pessoa);
					}
				}
			}

			Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
					.buildAndExpand(pessoaSalva.getId()).toUri();

			response.setHeader("Location", uri.toASCIIString());

			return pessoaSalva;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar pessoa: " + e.getCause());
		}
	}

	@Transactional
	public Pessoa atualizar(Long codigo, Pessoa pessoa, HttpServletResponse response) {

		try {
			// verifica preenchimento das composições
			if (pessoa.getLstFuncaoPessoa() != null && !pessoa.getLstFuncaoPessoa().isEmpty()) {
				for (FuncaoPessoa funcaoPessoa : pessoa.getLstFuncaoPessoa()) {
					funcaoPessoa.setPessoa(pessoa);
				}
			}

			Optional<Pessoa> optional = this.pessoaRepository.findById(codigo);

			if (!optional.isPresent()) {
				throw new EmptyResultDataAccessException(1);
			}

			Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

			return pessoaSalva;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar pessoa: " + e.getCause());
		}
	}

	public void delete(Long codigo) {
		try {
			this.pessoaRepository.deleteById(codigo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Erro ao deletar pessoa: Talvez consiga apenas adicionar uma data de demissão devido as dependencias. "
							+ e.getCause());
		}
	}

	public Optional<Pessoa> buscarPessoaPeloCodigo(Long codigo) {
		return this.pessoaRepository.findById(codigo);
	}

	public List<Pessoa> getPessoas(Pessoa pessoa) {

		if (pessoa.getSetor() != null) {
			return this.pessoaRepository.buscarPessoasPorSetor(pessoa.getSetor().getId());
		}
		return null;
	}
}
