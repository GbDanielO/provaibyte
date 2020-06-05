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

import br.com.ibyte.pessoa.model.Pessoa;
import br.com.ibyte.pessoa.negocio.PessoaNgc;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaNgc pessoaNgc;

	@GetMapping
	public List<Pessoa> listar() {
		return this.pessoaNgc.listar();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa criar(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		return this.pessoaNgc.criar(pessoa, response);
	}

	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public Pessoa atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa, HttpServletResponse response) {
		return this.pessoaNgc.atualizar(codigo, pessoa, response);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		this.pessoaNgc.delete(codigo);
	}

	@PostMapping("/filter")
	@ResponseStatus(HttpStatus.OK)
	public List<Pessoa> getPessoas(@RequestBody Pessoa pessoa) {
		return this.pessoaNgc.getPessoas(pessoa);
	}

}
