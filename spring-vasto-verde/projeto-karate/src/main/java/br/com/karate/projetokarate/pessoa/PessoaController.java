package br.com.karate.projetokarate.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.utils.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Pessoa payload) throws Exception {
		return pessoaService.save(payload);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	  public String errorPage() {
	    return "error";
	  }
	
	@RequestMapping( value = "/pessoa", method = RequestMethod.GET)
	public @ResponseBody List<Pessoa> getAll() {
		return this.pessoaService.findAll();
	}

	@RequestMapping(value = "pessoa/{idPessoa}", method = RequestMethod.GET)
	public @ResponseBody Optional<Pessoa> getById(@PathVariable("idPessoa") int idPessoa) {
		return this.pessoaService.findById(idPessoa);
	}

	@RequestMapping(value = "pessoa/email/{emailPessoa}", method = RequestMethod.GET)
	public Pessoa buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {
		return this.pessoaService.getByEmail(emailPessoa);
	}

	@RequestMapping(value = "pessoa/cpf/{cpfPessoa}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable("cpfPessoa") String cpfPessoa) {
		return this.pessoaService.getByCpf(cpfPessoa);
	}

	@RequestMapping(value = "pessoa/{idPessoa}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idPessoa") int idPessoa) {
		Logger log;
		log.
		return this.pessoaService.excluir(idPessoa);
	}
	
}