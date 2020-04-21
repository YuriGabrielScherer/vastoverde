package br.com.karate.projetokarate.data.campeonato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.campeonato.CampeonatoDto;
import br.com.karate.projetokarate.model.campeonato.CampeonatoSaveInput;

@RestController
@CrossOrigin(origins = "http:localhost:4200")
@RequestMapping("/camp")
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> save(@RequestBody CampeonatoSaveInput camp) {
		return this.campeonatoService.save(camp);
	}
	
	@GetMapping("/{id}")
	public CampeonatoDto getById(@PathVariable("id") int id) {
		CampeonatoDto camp = CampeonatoConverter.toDto(this.campeonatoService.findById(id));
		return camp;
	}
	
}
