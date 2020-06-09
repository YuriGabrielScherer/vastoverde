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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.associacao.AssociacaoDto;
import br.com.karate.projetokarate.model.associacao.AssociacaoSaveInput;
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

	@Autowired
	private AssociacaoConverter associacaoConverter;

	@PostMapping("/salvar")
	public ResponseEntity<?> save(@RequestBody AssociacaoSaveInput associacao) {
		LOGGER.info("Salvando associação...");
		return this.associacaoService.save(associacao);
	}

	@GetMapping("/{codAssociacao}")
	public ResponseEntity<AssociacaoDto> getById(@PathVariable("codAssociacao") int codAssociacao) {
		Associacao a = this.associacaoService.findByCodigo(codAssociacao);
		AssociacaoDto dto = associacaoConverter.toDto(a);
		return new ResponseEntity<AssociacaoDto>(dto, HttpStatus.OK);
	}

	@PostMapping("/findAll")
	public PesquisarAssociacaoOutput findAll(@RequestBody PesquisarAssociacaoInput filtro) {
		LOGGER.info("Buscando todas as associações...");
		Page<Associacao> associacoes = this.associacaoService.findAll(filtro);

		PesquisarAssociacaoOutput output = new PesquisarAssociacaoOutput();
		output.setAssociacoes(associacaoConverter.toDto(associacoes));
		output.setPaginacao(new RecPaginacaoRetorno(associacoes.getNumber(), associacoes.getSize(),
				associacoes.getTotalElements(), associacoes.getTotalPages()));

		return output;
	}

	@DeleteMapping("/{codAssociacao}")
	public ResponseEntity<?> delete(@PathVariable("codAssociacao") int codAssociacao) {
		return this.associacaoService.delete(codAssociacao);
	}

}
