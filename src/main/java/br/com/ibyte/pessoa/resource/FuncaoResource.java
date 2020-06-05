package br.com.ibyte.pessoa.resource;

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

import br.com.ibyte.pessoa.model.Funcao;
import br.com.ibyte.pessoa.negocio.FuncaoNgc;

@RestController
@RequestMapping("/funcoes")
public class FuncaoResource {

	@Autowired
	private FuncaoNgc funcaoNgc;

	@GetMapping
	public List<Funcao> getTodosSetores() {
		return funcaoNgc.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcao criar(@RequestBody Funcao funcao, HttpServletResponse response) {
		return this.funcaoNgc.criar(funcao, response);
	}

	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public Funcao atualizar(@PathVariable Long codigo, @RequestBody Funcao funcao, HttpServletResponse response) {
		return this.funcaoNgc.atualizar(codigo, funcao, response);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		this.funcaoNgc.delete(codigo);
	}

}
