package br.com.karate.projetokarate.data.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.pessoa.PesquisarPessoasInput;
import br.com.karate.projetokarate.model.pessoa.PesquisarPessoasOutput;
import br.com.karate.projetokarate.model.pessoa.PessoaDto;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;
import br.com.karate.projetokarate.utils.pageable.RecPaginacaoRetorno;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaConverter pessoaConverter;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> save(@RequestBody PessoaSaveInput payload) {
		LOGGER.info("Cadastrando pessoa...");
		return pessoaService.cadastrar(payload);
	}

	@GetMapping("/existeEmail/{email}")
	public boolean existeEmail(@PathVariable("email") String email) {
		LOGGER.info("Verificando se existe email cadastrado ...");
		return this.pessoaService.existsEmailWithoutThrow(email);
	}

	@PostMapping("/findAll")
	public @ResponseBody PesquisarPessoasOutput getAll(@RequestBody PesquisarPessoasInput filtro) {
//		TODO Implementar este método no Service e no Converter
		LOGGER.info("Retornando todas as pessoas...");
		Page<Pessoa> pagePessoa = this.pessoaService.findAll(filtro);

		PesquisarPessoasOutput output = new PesquisarPessoasOutput();
		output.setPessoas(pessoaConverter.toDto(pagePessoa));
		output.setPaginacao(new RecPaginacaoRetorno(pagePessoa.getNumber(), pagePessoa.getSize(),
				pagePessoa.getTotalElements(), pagePessoa.getTotalPages()));
		return output;
	}

	@GetMapping("/cadastrarAtleta")
	public List<PessoaDto> retornarPessoasCadastroAtleta() {
		LOGGER.info("Retornando pessoas para cadastro de atletas...");
		List<PessoaDto> output = new ArrayList<>();

		List<Pessoa> pessoas = this.pessoaService.findAll().stream().filter(pessoa -> pessoa.isAtivo())
				.collect(Collectors.toList());

		pessoas.stream().forEach(p -> output.add(pessoaConverter.toDto(p)));
		return output;
	}

	@GetMapping("/email/{emailPessoa}")
	public PessoaDto buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {
		LOGGER.info("Encontrando pessoa por e-mail...");
		return pessoaConverter.toDto(this.pessoaService.findByEmail(emailPessoa));
	}

	@GetMapping("/login/{login}")
	public PessoaDto findByLogin(@PathVariable("login") String login) {
		return pessoaConverter.toDto(this.pessoaService.findByLogin(login));
	}

	@GetMapping("/cpf/{cpfPessoa}")
	public PessoaDto buscarPorCpf(@PathVariable("cpfPessoa") String cpfPessoa) {
		return pessoaConverter.toDto(this.pessoaService.findByCpf(cpfPessoa));
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<String> delete(@PathVariable("cpf") String cpf) {
		LOGGER.info("Excluindo pessoa...");
		return this.pessoaService.excluir(cpf);
	}

}