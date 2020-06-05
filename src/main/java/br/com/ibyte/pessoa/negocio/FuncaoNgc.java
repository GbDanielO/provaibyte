package br.com.ibyte.pessoa.negocio;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ibyte.pessoa.model.Funcao;
import br.com.ibyte.pessoa.repository.FuncaoRepository;

@Service
public class FuncaoNgc {

	@Autowired
	private FuncaoRepository funcaoRepository;

	public List<Funcao> listar() {
		return this.funcaoRepository.findAll();
	}

	public ResponseEntity<Funcao> buscarPeloCodigo(Long codigo) {

		Optional<Funcao> optional = this.funcaoRepository.findById(codigo);

		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Transactional
	public Funcao criar(Funcao funcao, HttpServletResponse response) {

		try {
			// verifica preenchimento das composições

			Funcao funcaoSalva = this.funcaoRepository.save(funcao);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
					.buildAndExpand(funcaoSalva.getId()).toUri();

			response.setHeader("Location", uri.toASCIIString());

			return funcaoSalva;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar Função: " + e.getCause());
		}
	}

	@Transactional
	public Funcao atualizar(Long codigo, Funcao funcao, HttpServletResponse response) {

		try {
			// verifica preenchimento das composições

			Optional<Funcao> optional = this.funcaoRepository.findById(codigo);

			if (!optional.isPresent()) {
				throw new EmptyResultDataAccessException(1);
			}

			Funcao funcaoSalva = this.funcaoRepository.save(funcao);

			return funcaoSalva;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao atualizar Função: " + e.getCause());
		}
	}

	public void delete(Long codigo) {
		this.funcaoRepository.deleteById(codigo);
	}

	public Optional<Funcao> buscarFuncaoPeloCodigo(Long codigo) {
		return this.funcaoRepository.findById(codigo);
	}

}
