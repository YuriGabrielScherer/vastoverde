package br.com.karate.projetokarate.data.pessoa;

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
import br.com.karate.projetokarate.utils.pageable.RecPaginacaoRetorno;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	

	@PostMapping("/cadastrar")
	public ResponseEntity<?> save(@RequestBody Pessoa payload) {
		return pessoaService.cadastrar(payload);
	}

	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Pessoa payload) {
		return pessoaService.alterar(payload);
	}

	@PostMapping
	public @ResponseBody PesquisarPessoasOutput getAll(@RequestBody PesquisarPessoasInput filtro) {
		Page<Pessoa> pagePessoa = this.pessoaService.findAll(filtro);
		
		PesquisarPessoasOutput output = new PesquisarPessoasOutput();
		output.setPessoas(PessoaConverter.toDto(pagePessoa));
		output.setPaginacao(new RecPaginacaoRetorno(pagePessoa.getNumber(), pagePessoa.getSize(), pagePessoa.getTotalElements(), pagePessoa.getTotalPages()));
		return output;
	}

	@GetMapping("/{idPessoa}")
	public @ResponseBody PessoaDto getById(@PathVariable("idPessoa") int idPessoa) {
		Pessoa pessoa = this.pessoaService.findById(idPessoa);
		return PessoaConverter.toDto(pessoa);
	}

	@GetMapping("/email/{emailPessoa}")
	public PessoaDto buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {
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