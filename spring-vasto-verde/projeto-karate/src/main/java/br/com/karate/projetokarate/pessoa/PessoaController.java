package br.com.karate.projetokarate.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@RequestMapping(value = "pessoa/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Pessoa payload) {
		LOGGER.info("Cadastrando pessoa...");
		return pessoaService.cadastrar(payload);
	}

	@RequestMapping(value = "pessoa/alterar", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody Pessoa payload) {
		LOGGER.info("Alterando pessoa...");
		return pessoaService.alterar(payload);
	}

	@RequestMapping(value = "/pessoa", method = RequestMethod.GET)
	public @ResponseBody List<PessoaDto> getAll() {
		List<PessoaDto> pessoasDto = new ArrayList<>();
		List<Pessoa> pessoas = this.pessoaService.findAll();
		pessoas.stream().filter(p -> p.isAtivo()).forEach(p -> pessoasDto.add(PessoaConverter.toDto(p)));
		return pessoasDto;
	}

	@RequestMapping(value = "pessoa/{idPessoa}", method = RequestMethod.GET)
	public @ResponseBody PessoaDto getById(@PathVariable("idPessoa") int idPessoa) {
		LOGGER.info("getPessoaById...");
		Pessoa pessoa = this.pessoaService.findById(idPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@RequestMapping(value = "pessoa/email/{emailPessoa}", method = RequestMethod.GET)
	public PessoaDto buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {
		LOGGER.info("getPessoaByEmail...");
		Pessoa pessoa = this.pessoaService.findByEmail(emailPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@RequestMapping(value = "pessoa/cpf/{cpfPessoa}", method = RequestMethod.GET)
	public PessoaDto buscarPorCpf(@PathVariable("cpfPessoa") String cpfPessoa) {
		LOGGER.info("getPessoaByCpf...");
		Pessoa pessoa = this.pessoaService.findByCpf(cpfPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@RequestMapping(value = "pessoa/{idPessoa}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idPessoa") int idPessoa) {
		LOGGER.info("Excluindo pessoa...");
		return this.pessoaService.excluir(idPessoa);
	}

}