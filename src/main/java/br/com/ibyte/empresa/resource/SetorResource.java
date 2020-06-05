package br.com.ibyte.empresa.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibyte.empresa.model.Setor;
import br.com.ibyte.empresa.negocio.SetorNgc;

@RestController
@RequestMapping("/setores")
public class SetorResource {

	@Autowired
	private SetorNgc setorNgc;

	@GetMapping
	public List<Setor> getTodosSetores() {
		return setorNgc.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Setor criar(@RequestBody Setor setor, HttpServletResponse response) {
		return this.setorNgc.criar(setor, response);
	}

	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public Setor atualizar(@PathVariable Long codigo, @RequestBody Setor setor, HttpServletResponse response) {
		return this.setorNgc.atualizar(codigo, setor, response);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		this.setorNgc.delete(codigo);
	}
}
