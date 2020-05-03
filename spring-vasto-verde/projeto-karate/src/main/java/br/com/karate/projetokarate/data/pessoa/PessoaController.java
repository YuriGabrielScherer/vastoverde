package br.com.karate.projetokarate.data.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping("/cadastrar")
	public ResponseEntity<?> save(@RequestBody PessoaSaveInput payload) {
		LOGGER.info("Cadastrando pessoa...");
		return pessoaService.cadastrar(payload);
	}
	
	@PostMapping("existeEmail/{email}")
	public boolean existeEmail(@PathVariable("email") String email) {
		LOGGER.info("Verificandp se existe email da pessoa ...");
		Optional<Pessoa> pessoa = this.pessoaService.findByEmailWithoutThrow(email);
		return pessoa.isPresent() == true ? true : false;
	}

	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Pessoa payload) {
		LOGGER.info("Alterando pessoa...");
		return pessoaService.alterar(payload);
	}

	@PostMapping
	public @ResponseBody PesquisarPessoasOutput getAll(@RequestBody PesquisarPessoasInput filtro) {
		LOGGER.info("Retornando todas as pessoas...");
		Page<Pessoa> pagePessoa = this.pessoaService.findAll(filtro);

		PesquisarPessoasOutput output = new PesquisarPessoasOutput();
		output.setPessoas(PessoaConverter.toDto(pagePessoa));
		output.setPaginacao(new RecPaginacaoRetorno(pagePessoa.getNumber(), pagePessoa.getSize(),
				pagePessoa.getTotalElements(), pagePessoa.getTotalPages()));
		return output;
	}

	@GetMapping("/cadastrarAtleta")
	public List<PessoaDto> retornarPessoasCadastroAtleta() {
		LOGGER.info("Retornando pessoas para cadastro de atletas...");
		List<PessoaDto> output = new ArrayList<PessoaDto>();

		List<Pessoa> pessoas = this.pessoaService.findAll().stream().filter(p -> p.getAtleta() == null)
				.collect(Collectors.toList());

		pessoas.stream().forEach(p -> output.add(PessoaConverter.toDto(p)));
		
		return output;
	}

	@GetMapping("/{idPessoa}")
	public @ResponseBody PessoaDto getById(@PathVariable("idPessoa") int idPessoa) {
		Pessoa pessoa = this.pessoaService.findById(idPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@GetMapping("/email/{emailPessoa}")
	public PessoaDto buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {
		LOGGER.info("Encontrando pessoa por e-mail...");
		Pessoa pessoa = this.pessoaService.findByEmail(emailPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@GetMapping("/login/{login}")
	public PessoaDto findByLogin(@PathVariable("login") String login) {
		PessoaDto pessoa = PessoaConverter.toDto(this.pessoaService.findByLogin(login));
		return pessoa;
	}

	@GetMapping("/cpf/{cpfPessoa}")
	public PessoaDto buscarPorCpf(@PathVariable("cpfPessoa") String cpfPessoa) {
		Pessoa pessoa = this.pessoaService.findByCpf(cpfPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<String> delete(@PathVariable("cpf") String cpf) {
		return this.pessoaService.excluir(cpf);
	}

}