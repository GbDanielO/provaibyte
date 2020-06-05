package br.com.ibyte.empresa.negocio;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ibyte.empresa.model.Setor;
import br.com.ibyte.empresa.repository.SetorRepository;

@Service
public class SetorNgc {

	@Autowired
	private SetorRepository setorRepository;

	public List<Setor> listar() {
		return this.setorRepository.findAll();
	}

	@Transactional
	public Setor criar(Setor setor, HttpServletResponse response) {

		try {
			Setor setorSalvo = setorRepository.save(setor);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
					.buildAndExpand(setorSalvo.getId()).toUri();

			response.setHeader("Location", uri.toASCIIString());

			return setorSalvo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar Setor: " + e.getCause());
		}
	}

	@Transactional
	public Setor atualizar(Long codigo, Setor setor, HttpServletResponse response) {

		try {
			Optional<Setor> optional = this.setorRepository.findById(codigo);

			if (!optional.isPresent()) {
				throw new EmptyResultDataAccessException(1);
			}

			Setor setorSalvo = setorRepository.save(setor);

			return setorSalvo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar Setor: " + e.getCause());
		}
	}

	public void delete(Long codigo) {
		this.setorRepository.deleteById(codigo);
	}

}
