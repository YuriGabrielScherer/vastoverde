package br.com.karate.projetokarate.data.atleta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.atleta.AtletaSaveInput;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("atleta")
public class AtletaController {

	@Autowired
	private AtletaService atletaService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AtletaController.class);

	@PostMapping
	public ResponseEntity<?> save(@RequestBody AtletaSaveInput payload) {
		LOGGER.info("Cadastrando o atleta...");
		return this.atletaService.saveAtleta(payload);
	}

	// TODO Get List<AtletaDto> Paginado

	@DeleteMapping("/{cpfPessoa}")
	public ResponseEntity<?> delete(@PathVariable("cpfPessoa") String cpf) {
		atletaService.delete(cpf);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
