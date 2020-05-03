package br.com.karate.projetokarate.data.associacao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.associacao.PesquisarAssociacaoInput;
import br.com.karate.projetokarate.model.associacao.PesquisarAssociacaoOutput;
import br.com.karate.projetokarate.utils.pageable.RecPaginacaoRetorno;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/associacao")
public class AssociacaoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssociacaoController.class);

	@Autowired
	private AssociacaoService associacaoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> save(@RequestBody Associacao associacao) {
		return this.associacaoService.save(associacao);
	}

	@GetMapping("/{idAssociacao}")
	public Associacao getById(@PathVariable("idAssociacao") int idAssociacao) {
		return this.associacaoService.findById(idAssociacao);
	}

	@PostMapping()
	public PesquisarAssociacaoOutput findAll(@RequestBody PesquisarAssociacaoInput filtro) {
		LOGGER.info("Buscando todas as associações...");
		Page<Associacao> associacoes = this.associacaoService.findAll(filtro);

		PesquisarAssociacaoOutput output = new PesquisarAssociacaoOutput();
		output.setAssociacoes(AssociacaoConverter.toDto(associacoes));
		output.setPaginacao(new RecPaginacaoRetorno(associacoes.getNumber(), associacoes.getSize(),
				associacoes.getTotalElements(), associacoes.getTotalPages()));

		return output;
	}

	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody Associacao associacao) {
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping("/{idAssociacao}")
	public ResponseEntity<?> delete(@PathVariable("idAssociacao") int idAssociacao) {
		return this.associacaoService.delete(idAssociacao);
	}

}
