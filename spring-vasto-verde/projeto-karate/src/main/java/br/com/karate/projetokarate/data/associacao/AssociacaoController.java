package br.com.karate.projetokarate.data.associacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("/associacao")
public class AssociacaoController {

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

	@GetMapping()
	public List<Associacao> findAll() {
		List<Associacao> as = this.associacaoService.findAll().stream().filter(a -> a.isAtivo())
				.collect(Collectors.toList());
		return as;
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
