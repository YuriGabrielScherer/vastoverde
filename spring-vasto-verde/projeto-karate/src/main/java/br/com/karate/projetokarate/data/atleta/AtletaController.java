package br.com.karate.projetokarate.data.atleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.atleta.AtletaDto;
import br.com.karate.projetokarate.model.atleta.AtletaSaveInput;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("/atleta")
public class AtletaController {

	@Autowired
	private AtletaService atletaService;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody AtletaSaveInput atleta) {
		return this.atletaService.save(atleta);
	}

	@GetMapping
	public @ResponseBody List<AtletaDto> getAll() {
		List<Atleta> atletas = atletaService.findAll();
		List<AtletaDto> atletasDto = new ArrayList<>();
		atletas.stream().forEach(a -> atletasDto.add(AtletaConverter.toDto(a)));
		return atletasDto;
	}

	@GetMapping("/{idAtleta}")
	public AtletaDto getByid(@PathVariable("idAtleta") int id) {
		AtletaDto atleta = AtletaConverter.toDto(this.atletaService.findById(id));
		return atleta;
	}

	@RequestMapping(value = "/{idAtleta}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("idAtleta") int idAtleta) {
		return this.atletaService.delete(idAtleta);
	}

}